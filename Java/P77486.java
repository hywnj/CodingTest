package Java;

import java.util.*;

/**
 * 다단계 칫솔 판매
 * https://school.programmers.co.kr/learn/courses/30/lessons/77486
 */
public class P77486 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution(new String[]{"john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young"},
                new String[]{"-", "-", "mary", "edward", "mary", "mary", "jaimie", "edward"}, new String[]{"young", "john", "tod", "emily", "mary"},
                new int[]{12, 4, 2, 5, 10}))); // [360, 958, 108, 0, 450, 18, 180, 1080]
        System.out.println(Arrays.toString(solution(new String[]{"john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young"},
                new String[]{"-", "-", "mary", "edward", "mary", "mary", "jaimie", "edward"}, new String[]{"sam", "emily", "jaimie", "edward"},
                new int[]{2, 3, 5, 4}))); // [0, 110, 378, 180, 270, 450, 0, 0]
    }
    static Map<String, SellerInfo> sellerInfoMap = new HashMap<>();
    public static int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        // 1. 판매원 정보 저장
        for (int i=0; i<enroll.length; i++) {
            sellerInfoMap.put(enroll[i], new SellerInfo(referral[i]));
        }
        // 2. seller 판매액 분배
        for (int i=0; i<seller.length; i++) {
            distributeProfits(seller[i], amount[i] * 100);
        }
        // 3. 결과
        int[] answer = new int[enroll.length];
        for (int i=0; i<enroll.length; i++) {
            answer[i] = sellerInfoMap.get(enroll[i]).getProfits();
        }
        return answer;
    }
    public static void distributeProfits(String seller, int profits) {
        Queue<String> queue = new LinkedList<>();
        queue.offer(seller);
        int currentProfit = profits;

        while (!queue.isEmpty() && currentProfit > 0) { // 더이상 분배할 판매원이나 이익금이 없을때까지 반복
            String currentSeller = queue.poll();
            // 해당 판매원에게 할당되는 이익금의 10% 제외한 금액을 판매원 이익금에 누적합
            int excludingTen = currentProfit / 10;
            int netProfit = currentProfit - excludingTen;

            SellerInfo s = sellerInfoMap.get(currentSeller);
            s.addProfits(netProfit);

            currentProfit = excludingTen;
            if (!s.getReferral().equals("-")) { // 최상위가 아닌 경우에 Queue에 판매원 추가
                queue.offer(s.getReferral());
            }
        }
    }
    public static class SellerInfo {
        private String referral;
        private int profits;
        public SellerInfo(String referral) {
            this.referral = referral;
            this.profits = 0;
        }
        public void addProfits(int profits) {
            this.profits += profits;
        }
        public String getReferral() {
            return referral;
        }
        public int getProfits() {
            return profits;
        }
    }
}

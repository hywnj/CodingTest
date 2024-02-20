package Java;

/**
 * 이모티콘 할인행사
 * https://school.programmers.co.kr/learn/courses/30/lessons/150368
 */
public class P150368 {
    static int[] percent = {10, 20, 30, 40}; // 할인율 배열
    static int maxSubscribeCnt = 0; // 최대 이모티콘 플러스 가입자 수
    static int maxProfit = 0; // 최대 이모티콘 매출액

    public static void main(String[] args) {
        int[] answer1 = solution(new int[][]{{40, 10000},{25, 10000}}, new int[]{7000, 9000});
        int[] answer2 = solution(new int[][]{{40, 2900},{23, 10000},{11, 5200},{5, 5900},{40, 3100},{27, 9200},{32, 6900}}
                , new int[]{1300, 1500, 1600, 4900});

        System.out.println(answer1[0] +", "+ answer1[1]); // {1, 5400}
        System.out.println(answer2[0] +", "+ answer2[1]); // {4, 13860}
    }

    /**
     * 할인율 조합을 만들고 각 사용자의 구매 여부를 판단
     *
     */
    public static int[] solution(int[][] users, int[] emoticons) {
        findMaxValue(0, emoticons.length, new int[emoticons.length], users, emoticons);
        return new int[]{maxSubscribeCnt, maxProfit};
    }

    public static void findMaxValue(int index, int emotionsLength, int[] discounts, int[][] users, int[] emoticons) {
        // 이모티콘 개수만큼 할인율 조합이 생성되었다면 계산
        if (index == emotionsLength)
        {
            int subscribe = 0;
            int profit = 0;

            // 사용자별로 이모티콘의 할인율을 계산한 가격으로 판단
            for (int[] user : users) {
                int cost = 0; // 각 사용자가 지불할 금액

                for (int j=0; j<emotionsLength; j++) {
                    // 할인율이 사용자의 기준 비율 이상이면 이모티콘 구매
                    if (discounts[j] >= user[0]) {
                        cost +=  emoticons[j] * (100 - discounts[j])/100;
                    }
                }

                if (cost >= user[1]) subscribe++; // 사용자의 가격보다 총 합이 크면 구독 +1
                else profit += cost;  // 아니면 매출액에 포함
            }

            // 최대 값 갱신
            if (subscribe > maxSubscribeCnt) { // 가입자 수가 1순위이므로 먼저 체크
                maxSubscribeCnt = subscribe;
                maxProfit = profit;
            } else if (subscribe == maxSubscribeCnt) { // 최대 가입자 수와 계산한 가입자 수가 같으면, 매출액 갱신
                maxProfit = Math.max(maxProfit, profit);
            }
            return;
        }
        else
        {
            // 할인율 조합 생성
            for (int i=0; i<4; i++) {
                discounts[index] = percent[i];
                findMaxValue(index+1, emotionsLength, discounts, users, emoticons);
            }
        }

    }
}

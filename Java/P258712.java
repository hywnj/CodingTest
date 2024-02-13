package Java;

import java.util.*;

/**
 * 가장 많이 받은 선물
 * https://school.programmers.co.kr/learn/courses/30/lessons/258712
 */

public class P258712 {
    static class Friends {
        public int index; // 친구 이름을 받아와서 배열에 누가 누구에게 선물을 줬는지 기록할 때 각자의 인덱스를 지정
        public int given; // 선물을 준 개수
        public int received; // 선물을 받은 개수
        public int diff; // 선물 지수

        public Friends(int index) {
            this.index = index;
            this.given = 0;
            this.received = 0;
        }

        public void calDiff() {
            this.diff = this.given - this.received;
        }
    }

    public static void main(String[] args) {
        int answer1 = solution(new String[]{"muzi", "ryan", "frodo", "neo"}, new String[]{"muzi frodo", "muzi frodo", "ryan muzi", "ryan muzi", "ryan muzi", "frodo muzi", "frodo ryan", "neo muzi"});
        int answer2 = solution(new String[]{"joy", "brad", "alessandro", "conan", "david"}, new String[]{"alessandro brad", "alessandro joy", "alessandro conan", "david alessandro", "alessandro david"});
        int answer3 = solution(new String[]{"a", "b", "c"}, new String[]{"a b", "b a", "c a", "a c", "a c", "c a"});

        System.out.println(answer1);
        System.out.println(answer2);
        System.out.println(answer3);
    }

    public static int solution(String[] friends, String[] gifts) {
        /**
         * 1. 친구끼리 주고 받은 선물 비교
         *      - A가 B에게 더 많이 줬다면 다음 달에 A가 선물 하나 더 받음
         *      - A와 B가 똑같이 주고 받거 기록이 없다면 지수로 비교
         * 2. 각자의 선물지수 비교
         *      - A와 B가 선물을 똑같이 주고 받거나 기록이 없다면 지수로 비교
         *      - 선물 지수가 더 높은 사람이 다음 달에 하나 더 받음
         *      - 지수도 같다면 둘 다 선물을 받지 않음
         */
        int friendsLength = friends.length;
        int giftsLength = gifts.length;

        // 친구 정보 할당
        HashMap<String, Friends> friendsInfo = new HashMap<>(); // key: 친구 이름, value: Friends 객체
        int[][] giftInfo = new int[friendsLength][friendsLength]; // 행: 준 사람, 열: 받은 사람
        Integer[] answer = new Integer[friendsLength];
        for (int i=0; i<friendsLength; i++) {
            friendsInfo.put(friends[i], new Friends(i));
            answer[i] = 0;
            for (int j=0; j<friendsLength; j++) {
                giftInfo[i][j] = 0;
            }
        }

        // 선물 주고 받은 현황 배열에 할당 & 선물 준 개수와 받은 개수 갱신
        for (int i=0; i<giftsLength; i++) {
            StringTokenizer st = new StringTokenizer(gifts[i]);
            String giveGift = st.nextToken();
            String receiveGift = st.nextToken();

            // 친구 객체 찾기
            Friends giveFriend = friendsInfo.get(giveGift);
            Friends receiveFriend = friendsInfo.get(receiveGift);

            // 선물 주고 받은 개수 Friends 객체에 갱신
            giveFriend.given++;
            receiveFriend.received++;
            // 선물 주고 받은 현황
            giftInfo[giveFriend.index][receiveFriend.index]++;
        }

        // 선물 지수 계산
        int[] giftScore = new int[friendsLength];
        for (Friends fr: friendsInfo.values()) {
            fr.calDiff();
            giftScore[fr.index] = fr.diff;
        }

        // 다음 달에 가장 많이 받는 선물의 개수 계산
        for (int i=0; i<friendsLength-1; i++) {
            for (int j=i+1; j<friendsLength; j++) {
                if (giftInfo[i][j] > giftInfo[j][i]) { // i번째 친구가 선물을 더 많이 준 경우
                    answer[i]++;
                } else if (giftInfo[i][j] < giftInfo[j][i]) { // j번째 친구가 선물을 더 많이 준 경우
                    answer[j]++;
                }
                else { // 같거나 없는 경우 지수 비교
                    if (giftScore[i] > giftScore[j]) answer[i]++;
                    else if (giftScore[i] < giftScore[j]) answer[j]++;
                    else continue;
                }
            }
        }

        // 계산 배열 내림차순 정렬
        Arrays.sort(answer,Collections.reverseOrder());

        return answer[0];
    }
}

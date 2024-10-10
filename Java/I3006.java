package Java;

import java.util.*;

/**
 * 가장 많이 사용된 회의실
 */
public class I3006 {
    public int solution(int n, int[][] meetings){
        // meetings를 시작 시간 기준으로 오름차순 정렬
        Arrays.sort(meetings, ((o1, o2) -> o1[0] - o2[0]));

        PriorityQueue<Integer> availablePQ = new PriorityQueue<>(); // 사용 가능한 회의실
        for (int i = 0; i < n; i++) {
            availablePQ.offer(i);
        }

        int[] count = new int[n]; // 회의실 사용 횟수
        PriorityQueue<RoomInfo> pq = new PriorityQueue<>(); // 1: 회의실 비는 순서(끝나는 시간) 2: 회의실 번호가 빠른순
        for (int[] meeting : meetings) {
            // 사용가능한 회의실을 찾아야함
            int endTime = meeting[1];
            int roomNum = 0;
            if (!availablePQ.isEmpty()) {
                roomNum = availablePQ.poll();

            } else {
                // 사용가능한 회의실이 없으면 회의실이 비는때까지 기다렸다가 배정
                if (!pq.isEmpty()) {
                    RoomInfo roomInfo = pq.poll();
                    endTime = roomInfo.endTime + (meeting[1] - meeting[0]);
                    roomNum = roomInfo.num;
                }
            }
            pq.offer(new RoomInfo(endTime, roomNum));
            count[roomNum] += 1;
        }

        int max = 0;
        int answer = 0;
        for (int i = n - 1; i >= 0; i--) {
            if (max <= count[i]) {
                max = count[i];
                answer = i;
            }
        }
        return answer;
    }

    public class RoomInfo implements Comparable<RoomInfo> {
        int endTime; // 회의 끝나는 시간
        int num; // 회의실 번호
        public RoomInfo(int endTime, int num) {
            this.endTime = endTime;
            this.num = num;
        }

        @Override
        public int compareTo(RoomInfo o) {
            if (this.endTime == o.endTime) return this.num - o.num;
            else return this.endTime - o.endTime;
        }
    }

    public static void main(String[] args){
        I3006 T = new I3006();
        System.out.println(T.solution(2, new int[][]{{0, 5}, {2, 7}, {4, 5}, {7, 10}, {9, 12}}));
        System.out.println(T.solution(3, new int[][]{{3, 9}, {1, 10}, {5, 8}, {10, 15}, {9, 14}, {12, 14}, {15, 20}}));
        System.out.println(T.solution(3, new int[][]{{1, 30}, {2, 15}, {3, 10}, {4, 12}, {6, 10}}));
        System.out.println(T.solution(4, new int[][]{{3, 20}, {1, 25}, {5, 8}, {10, 15}, {9, 14}, {12, 14}, {15, 20}}));
    }
}

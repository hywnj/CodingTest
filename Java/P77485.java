package Java;

import java.util.*;

/**
 * 행렬 테두리 회전하기
 * https://school.programmers.co.kr/learn/courses/30/lessons/77485
 */
public class P77485 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution(6, 6, new int[][]{{2, 2, 5, 4}, {3, 3, 6, 6}, {5, 1, 6, 3}}))); // [8, 10, 25]
        System.out.println(Arrays.toString(solution(3, 3, new int[][]{{1, 1, 2, 2}, {1, 2, 2, 3}, {2, 1, 3, 2}, {2, 2, 3, 3}}))); // [1, 1, 5, 3]
        System.out.println(Arrays.toString(solution(100, 97, new int[][]{{1, 1, 100, 97}}))); // [1]
    }

    public static int[] solution(int rows, int columns, int[][] queries) {
        Map<Pointer, Pointer> originalMap = new HashMap<>(); // 회전 전 행렬 정보

        int[] answer = new int[queries.length];
        int index = 0; // answer 배열에 값 추가하기 위한 인덱스
        for (int[] querie : queries) {
            Map<Pointer, Pointer> updatedMap = new HashMap<>(); // 회전 후 행렬 정보 (회전마다 초기화)

            int x1 = querie[0];
            int y1 = querie[1];
            int x2 = querie[2];
            int y2 = querie[3];

            int totalMovedNumber = (x2 - x1 + 1) * 2 + (y2 - y1 + 1) * 2 - 4; // 확일할 개수 = 회전하는 개수

            int min = 10001; // rows, columns는 최대 100
            int nowX = x1;
            int nowY = y1;
            for (int i = 0; i < totalMovedNumber; i++) {
                Pointer nowPointer = new Pointer(nowX, nowY); // 현재 포인터
                Pointer movedInfo = new Pointer(); // 회전 정보 초기화

                if (originalMap.containsKey(nowPointer)) { // 이전에 회전한 적이 있다면, 회전 정보 가져오기
                    movedInfo = originalMap.get(nowPointer);
                }

                // 현 회전의 최솟값 계산
                int nowValue = (nowX - 1 + movedInfo.x) * columns + nowY + movedInfo.y;
                if (nowValue < min) min = nowValue;

                // 회전 방향마다 다른 회전 정보 갱신
                // 회전 순서는 시계 방향이므로 오른쪽 -> 아래쪽 -> 왼쪽 -> 위쪽
                if (nowX == x1 && nowY < y2) { // 1. 오른쪽
                    nowY++;
                    updatedMap.put(new Pointer(nowX, nowY), new Pointer(movedInfo.x, movedInfo.y - 1));
                } else if (nowY == y2 && nowX < x2) { // 2. 아래쪽
                    nowX++;
                    updatedMap.put(new Pointer(nowX, nowY), new Pointer(movedInfo.x - 1, movedInfo.y));
                } else if (nowX == x2 && nowY > y1) { // 3. 왼쪽
                    nowY--;
                    updatedMap.put(new Pointer(nowX, nowY), new Pointer(movedInfo.x, movedInfo.y + 1));
                } else if (nowY == y1 && nowX > x1) { // 4. 위쪽
                    nowX--;
                    updatedMap.put(new Pointer(nowX, nowY), new Pointer(movedInfo.x + 1, movedInfo.y));
                }


            }

            // 갱신된 포인터 정보는 다음 회전에 사용해야하므로 originalMap에 갱신
            for (Map.Entry entry : updatedMap.entrySet()) {
                originalMap.put((Pointer) entry.getKey(), (Pointer) entry.getValue());
            }
            // 결과 배열에 최솟값 저장
            answer[index] = min;
            index++;
        }

        return answer;
    }

    public static class Pointer {
        public int x;
        public int y;

        public Pointer(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pointer other = (Pointer) o;
            return other.x == x && other.y == y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        public Pointer() {
            this.x = 0;
            this.y = 0;
        }
    }
}

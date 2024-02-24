package Java;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 행렬 테두리 회전하기
 * https://school.programmers.co.kr/learn/courses/30/lessons/77485
 */
public class P77485 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution(6,6, new int[][]{{2, 2, 5, 4},{3, 3, 6, 6}, {5, 1, 6, 3}})));
    }

    /**
     * 배열을 직접 움직이지말고, 움직인 인덱스의 히스토리를 저장해서 계산하자
     *  - 확인할 인덱스 개수: (x2 - x1 + 1)*2 + (y2 - y1 + 1)*2 - 4
     *  - (i,j) 최초 기본식 = (i-1)*columns + j
     *  - 움직일때마다 갱신할 값
     *      - 오른쪽(0,-1): (i-1)*columns + j-1
     *      - 아래쪽(-1,0): (i-1-1)*columns + j
     *      - 왼쪽(0,1): (i-1)*columns + j+1
     *      - 위쪽(1,0): (i-1+1)*columns + j
     */
    public static int[] solution(int rows, int columns, int[][] queries) {
        Map<Pointer, Pointer> originalMap = new HashMap<>();    // 회전 전 행렬 정보

        int[] answer = new int[queries.length];
        int index = 0; // answer 배열에 값 추가하기 위한 인덱스
        for (int[] querie : queries) {
            Map<Pointer, Pointer> updatedMap = new HashMap<>(); // 회전 후 행렬 정보 (회전마다 초기화)

            int x1 = querie[0];
            int y1 = querie[1];
            int x2 = querie[2];
            int y2 = querie[3];

            int totalMovedNumber = (x2 - x1 + 1) * 2 + (y2 - y1 + 1) * 2 - 4; // 확일할 개수 = 회전하는 개수

            int min = 1100;
            int nowX = x1;
            int nowY = y1;
            for (int i = 0; i < totalMovedNumber; i++) {
                Pointer nowPointer = new Pointer(nowX, nowY); // 현재 포인터
                Pointer movedInfo = new Pointer(); // 회전 정보 초기화
                // @TODO: 체크 안되므로 확인
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
                    updatedMap.put(nowPointer, new Pointer(movedInfo.x, movedInfo.y - 1));
                } else if (nowY == y2 && nowX < x2) { // 2. 아래쪽
                    nowX++;
                    updatedMap.put(nowPointer, new Pointer(movedInfo.x - 1, movedInfo.y));
                } else if (nowX == x2 && nowY > y1) { // 3. 왼쪽
                    nowY--;
                    updatedMap.put(nowPointer, new Pointer(movedInfo.x, movedInfo.y + 1));
                } else if (nowY == y1 && nowX > x1) { // 4. 위쪽
                    nowX--;
                    updatedMap.put(nowPointer, new Pointer(movedInfo.x + 1, movedInfo.y));
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

        public Pointer() {
            this.x = 0;
            this.y = 0;
        }
    }
}

package Java;

import java.util.ArrayList;
import java.util.List;

/**
 * 기둥과 보 설치
 * https://school.programmers.co.kr/learn/courses/30/lessons/60061
 */
public class P60061 {
    public static void main(String[] args) {
        // result : [[1,0,0],[1,1,1],[2,1,0],[2,2,1],[3,2,1],[4,2,1],[5,0,0],[5,1,0]]
        int[][] sol1 = solution(5, new int[][]{{1, 0, 0, 1}, {1, 1, 1, 1}, {2, 1, 0, 1}, {2, 2, 1, 1}, {5, 0, 0, 1}, {5, 1, 0, 1}, {4, 2, 1, 1}, {3, 2, 1, 1}});
        System.out.println("=========== Test case 1 ===========");
        for (int[] arr : sol1) {
            System.out.print("[");
            for (int el : arr) {
                System.out.print(el + ",");
            }
            System.out.print("] ");
        }

        // result : [[0,0,0],[0,1,1],[1,1,1],[2,1,1],[3,1,1],[4,0,0]]
        int[][] sol2 = solution(5, new int[][]{{0, 0, 0, 1}, {2, 0, 0, 1}, {4, 0, 0, 1}, {0, 1, 1, 1}, {1, 1, 1, 1}, {2, 1, 1, 1}, {3, 1, 1, 1}, {2, 0, 0, 0}, {1, 1, 1, 0}, {2, 2, 0, 1}});
        System.out.println("\n=========== Test case 2 ===========");
        for (int[] arr : sol2) {
            System.out.print("[");
            for (int el : arr) {
                System.out.print(el + ",");
            }
            System.out.print("] ");
        }
    }

    static boolean[][] vertical;
    static boolean[][] horizontal;
    static int N;

    public static int[][] solution(int n, int[][] build_frame) {
        vertical = new boolean[n + 1][n + 1]; // 기둥
        horizontal = new boolean[n + 1][n + 1]; // 바
        N = n;

        for (int[] task : build_frame) {
            int x = task[0];
            int y = task[1];
            int structure = task[2];
            int command = task[3];

            if (command == 1) { // 설치
                if (canInstall(x, y, structure)) install(x, y, structure);
            } else { // 삭제
                uninstall(x, y, structure);
                if (!isValidStructure()) install(x, y, structure);
            }
        }


        List<int[]> answer = new ArrayList<>();
        for (int y = 0; y <= N; y++) {
            for (int x = 0; x <= N; x++) {
                if (vertical[y][x]) answer.add(new int[]{x, y, 0});
                if (horizontal[y][x]) answer.add(new int[]{x, y, 1});
            }
        }
        // x좌표 기준 오름차순 -> y좌표 기준 오름차순 -> 기둥이 보보다 우선(오름차순)
        answer.sort((o1, o2) -> {
            if (o1[0] != o2[0]) return o1[0] - o2[0];
            if (o1[1] != o2[1]) return o1[1] - o2[1];
            return o1[2] - o2[2];
        });

        return answer.toArray(new int[answer.size()][]);
    }

    private static void install(int x, int y, int structure) {
        if (structure == 0) vertical[y][x] = true; // 기둥
        else horizontal[y][x] = true; // 보
    }

    private static void uninstall(int x, int y, int structure) {
        if (structure == 0) vertical[y][x] = false; // 기둥
        else horizontal[y][x] = false; // 보
    }

    /**
     * 설치 가능여부 확인
     */
    private static boolean canInstall(int x, int y, int structure) {
        if (structure == 0) { // 기둥
            // 좌표가 바닥 - x좌표가 0인 경우 (y == 0)
            if (y == 0) return true;
            // 보의 한쪽 끝
            // - 설치하려는 x축 한 칸 왼쪽 좌표에 보가 설치된 경우
            if (x > 0 && horizontal[y][x - 1]) return true;
            // - 설치하려는 좌표에 보가 설치된 경우
            if (horizontal[y][x]) return true;
            // 다른 기둥 위 - 설치하려는 y축 한 칸 아래 좌표에 기둥이 설치된 경우
            if (y > 0 && vertical[y - 1][x]) return true;
        } else { // 보
            // 한쪽 끝 부분이 기둥 위
            // - 설치하려는 y축 한 칸 아래 좌표에 기둥이 설치된 경우
            if (y > 0 && vertical[y - 1][x]) return true;
            // - 설치하려는 x축 한칸 오른쪽 좌표에서 y축 한 칸 아래 좌표에 기둥이 설치된 경우
            if (y > 0 && x < N && vertical[y - 1][x + 1]) return true;
            // 양쪽 끝이 다른 보와 동시에 연결 (이 경우 양쪽 보가 모두 설치된 후에야 설치 가능)
            // - 설치하려는 x축 한칸 왼쪽과 한칸 오른쪽 좌표에 보가 설치된 경우
            if ((x < N && horizontal[y][x + 1]) && (x > 0 && horizontal[y][x - 1])) return true;
        }
        return false;
    }

    /**
     * 모든 구조물이 유효하게 설치되었는지 확인
     */
    private static boolean isValidStructure() {
        for (int y = 0; y <= N; y++) {
            for (int x = 0; x <= N; x++) {
                // 구조물이 설치 되어있지만, 유효하지 않은 경우 false
                if (vertical[y][x] && !canInstall(x, y, 0)) return false;
                if (horizontal[y][x] && !canInstall(x, y, 1)) return false;
            }
        }
        return true;
    }
}
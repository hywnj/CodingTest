package Java;

import java.util.*;

/**
 * [PCCP 기출문제] 2번 / 석유 시추
 * https://school.programmers.co.kr/learn/courses/30/lessons/250136
 */
public class P250136 {
    public static void main(String[] args) {
        System.out.println(solution(new int[][]{{0, 0, 0, 1, 1, 1, 0, 0}, {0, 0, 0, 0, 1, 1, 0, 0}, {1, 1, 0, 0, 0, 1, 1, 0}, {1, 1, 1, 0, 0, 0, 0, 0}, {1, 1, 1, 0, 0, 0, 1, 1}}));
        System.out.println(solution(new int[][]{{1, 0, 1, 0, 1, 1}, {1, 0, 1, 0, 0, 0}, {1, 0, 1, 0, 0, 1}, {1, 0, 0, 1, 0, 0}, {1, 0, 0, 1, 0, 1}, {1, 0, 0, 0, 0, 0}, {1, 1, 1, 1, 1, 1}}));
    }

    public static int solution(int[][] land) {
        int n = land.length; // 세로 길이
        int m = land[0].length; // 가로 길이

        boolean[][] visited = new boolean[n][m];
        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, 1, 0, -1};

        // 석유 덩어리 구하기
        Queue<Point> queue = new LinkedList<>();
        List<Oil> oils = new ArrayList<>();
        // BFS 탐색
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!visited[i][j] && land[i][j] == 1) { // 방문한적 없고, 석유가 있는 땅인 경우
                    visited[i][j] = true;

                    List<Point> pointList = new ArrayList<>(); // 현재 석유 덩어리의 좌표 리스트
                    pointList.add(new Point(i,j));
                    queue.offer(new Point(i,j));

                    while (!queue.isEmpty()) {
                        Point point = queue.poll();

                        for (int k = 0; k < 4; k++) {
                            int nx = point.x + dx[k];
                            int ny = point.y + dy[k];

                            if(nx >= 0 && nx < n && ny >= 0 && ny < m) {
                                if (!visited[nx][ny] && land[nx][ny] == 1) {
                                    visited[nx][ny] = true;
                                    queue.offer(new Point(nx, ny));
                                    pointList.add(new Point(nx, ny));
                                }
                            }
                        }
                    }
                    oils.add(new Oil(pointList)); // 석유 덩어리 리스트에 현재 석유 덩어리 정보 저장
                }
            }
        }

        // 가장 많은 석유를 뽑을 수 있는 시추관 찾기
        Drill[] drills = new Drill[m]; // 열 개수(=가로 길이)만큼 할당
        int oilCnt = oils.size();
        // 석유 덩어리 정보를 저장한 List를 순회하면서 해당 포인트의 열을 기준으로 석유 덩어리 사이즈 계산
        for (int o = 0; o < oilCnt; o++) {
            for (Point point : oils.get(o).pointList) {
                int y = point.y;
                // 아직 생성한적 없다면 석유 덩어리 개수를 매개변수로 넣어서 생성
                if (drills[y] == null) {
                    drills[y] = new Drill(oilCnt);
                }

                if (!drills[y].visitedOil[o]) { // 현재 석유 덩어리가 해당 좌표 열에 아직 누적합 되지 않은 경우
                    drills[y].visitedOil[o] = true; // 방문한 석유 덩어리니까 true 할당
                    drills[y].addOilSum(oils.get(o).pointList.size()); // 현재 석유 덩어리의 크기 누적
                }
            }
        }

        // Max 석유량 찾기
        int max = 0;
        for (Drill drill: drills) {
            // 석유가 없는 열이라면 drill이 null 일 수도 있으므로 null 검증
            if (drill != null && drill.oilSum > max) max = drill.oilSum;
        }
        return max;
    }

    public static class Point {
        int x;
        int y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    public static class Oil {
        List<Point> pointList;

        public Oil(List<Point> pointList) {
            this.pointList = pointList;
        }
    }

    public static class Drill {
        boolean[] visitedOil;
        int oilSum = 0;

        public Drill(int oilCnt) {
            this.visitedOil = new boolean[oilCnt];
        }
        public void addOilSum(int oilSize) {
            this.oilSum += oilSize;
        }
    }
}

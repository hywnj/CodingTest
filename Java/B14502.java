package Java;

import java.io.*;
import java.util.*;

/**
 * 연구소
 * https://www.acmicpc.net/problem/14502
 */
public class B14502 {
    /**
     * 1) 벽 세우기: 3군데를 골라서 1세우기
     * 2) 바이러스 퍼뜨리기: 2를 기준으로, BFS 탐색하면서 0인 부분만 2로 변환
     * 3) 안전영역 계산
     */
    static List<Point> virus = new ArrayList<>(); // 바이러스 위치 정보
    static int n, m;
    static int max = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        int[][] map = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < m; j++) {
                int val = Integer.parseInt(st.nextToken());
                if (val == 2) virus.add(new Point(i, j));
                map[i][j] = val;
            }
        }

        setWallAndCalculate(0, 0, map);

        System.out.println(max);
    }

    public static void setWallAndCalculate(int start, int depth, int[][] map) {
        if (depth == 3) {
            int[] dx = {1, 0, -1, 0};
            int[] dy = {0, 1, 0, -1};
            int[][] copyMap = new int[n][m]; // 현재 벽을 세운 상태에서의 map
            for (int i = 0; i < n; i++) for (int j = 0; j < m; j++) copyMap[i][j] = map[i][j];

            // 2. 바이러스 퍼뜨리기
            for (Point vp : virus) {
                Queue<Point> q = new LinkedList<>();
                q.offer(vp);
                while (!q.isEmpty()) {
                    Point p = q.poll();
                    for (int i = 0; i < 4; i++) {
                        int nx = p.x + dx[i];
                        int ny = p.y + dy[i];
                        if (nx >= 0 && nx < n && ny >= 0 && ny < m) {
                            // 빈칸이라면 2로 세팅하고 Q에 추가
                            if (copyMap[nx][ny] == 0) {
                                copyMap[nx][ny] = 2;
                                q.offer(new Point(nx, ny));
                            }

                        }
                    }
                }
            }
            // 3. 안전 구역 계산
            int cnt = calculateSafe(copyMap);
            if (cnt > max) max = cnt;
            return;
        }
        // 1. 벽 세우기: 3개
        for (int i = start; i < n * m; i++) {
            int x = i / m; // 열의 길이로 나누면 현재 행 위치
            int y = i % m; // 열의 길이로 나눈 나머지가 현재 열 위치
            if (map[x][y] == 0) {
                map[x][y] = 1; // 0인 경우에 벽 세우기
                setWallAndCalculate(i + 1, depth + 1, map);
                map[x][y] = 0;
            }
        }
    }

    // 안전 영역 계산
    public static int calculateSafe(int[][] map) {
        int cnt = 0;
        for (int[] row : map) {
            for (int val : row) {
                if (val == 0) cnt++;
            }
        }
        return cnt;
    }

    public static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}

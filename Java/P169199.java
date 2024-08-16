package Java;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 리코쳇 로봇
 * https://school.programmers.co.kr/learn/courses/30/lessons/169199
 */
public class P169199 {
    public static void main(String[] args) {
        System.out.println(solution(new String[]{"...D..R", ".D.G...", "....D.D", "D....D.", "..D...."})); // 7
        System.out.println(solution(new String[]{".D.R", "....", ".G..", "...D"})); // -1
    }

    public static int solution(String[] board) {
        int row = board.length;
        int col = board[0].length();
        int[][] cnt = new int[row][col];

        // 1. 시작 위치(R) 찾기
        Queue<Point> q = new LinkedList<>();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (board[i].charAt(j) == 'R') {
                    q.add(new Point(i, j));
                    cnt[i][j] = 1;
                    break;
                }
            }
        }

        // 2. BFS 탐색
        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, 1, 0, -1};
        int answer = -1; // 목표위치에 도달할 수 없다면 -1을 return
        while (!q.isEmpty()) {
            Point current = q.poll();
            // 현재 지점이 목표 위치인지
            if (board[current.x].charAt(current.y) == 'G') {
                answer = cnt[current.x][current.y] - 1; // 시작 위치를 1로 초기화 했으므로, 이동 횟수는 -1
                break;
            }
            // 4방향에 대해 BFS를 수행
            for (int i = 0; i < 4; i++) {
                int nx = current.x + dx[i];
                int ny = current.y + dy[i];
                // 해당 방향으로 최대한 이동
                while (true) {
                    if ((nx >= 0 && nx < row && ny >= 0 && ny < col) && board[nx].charAt(ny) != 'D') {
                        nx += dx[i];
                        ny += dy[i];
                    } else { // 벽이나 장애물에 부딪힌 경우
                        nx -= dx[i];
                        ny -= dy[i];
                        break;
                    }
                }
                // 최대한 움직인 지점을 방문한 적이 없으면, 해당 지점에서 탐색
                if (cnt[nx][ny] == 0) {
                    q.add(new Point(nx, ny));
                    cnt[nx][ny] = cnt[current.x][current.y] + 1; // 현재 탐색을 시작한 위치에서 이동 횟수 +1
                }
            }
        }
        return answer;
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

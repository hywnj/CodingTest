package Java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 배열 돌리기 1
 * https://www.acmicpc.net/problem/16926
 */
public class B16926 {
    /**
     * [2차 풀이방법]
     * 1. 회전시켜야 하는 그룹 수 계산
     *      - Math.min(N, M) / 2
     * 2. 구한 그룹의 수 만큼 반복
     *      - 윗변: 왼쪽으로 움직이는 연산
     *      - 오른쪽변: 아래쪽으로 움직이는 연산
     *      - 아랫변: 오른쪽으로 움직이는 연산
     *      - 왼쪽변: 위쪽으로 움직이는 연산
     */
    static int N, M, R;
    static int min;
    static int[][] map;

    /**
     * 시계 반대방향으로 넣는 순서니까, 시계방향으로 인덱스를 방문하면서 값 할당해주기
     *  - 인덱스 방문 순서: (0,0)부터 오른쪽 - 아래쪽 - 왼쪽 - 위쪽 순
     *  - 값 할당
     *      <아래와 같을때 반시계 방향으로 값이 들어감>
     *      - 오른쪽으로 방문시, 왼쪽으로 넣는
     *      - 아래쪽으로 방문시, 위로 넣는
     *      - 왼쪽으로 방문시, 오른쪽으로 넣는
     *      - 위쪽으로 방문시, 아래쪽으로 넣는
     */
    static int[] dx = {0, 1, 0, -1}; // 오른쪽, 아래쪽, 왼쪽, 위쪽
    static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        // 초기 입력값 세팅
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 행,열 중 더 작은 값 (짝수)
        min = Math.min(N,M);

        // 회전 횟수만큼 회전
        for (int i=0; i<R; i++) {
            rotate();
        }

        for (int row[] : map) {
            for (int col : row) {
                System.out.print(col + " ");
            }
            System.out.println();
        }
    }

    static void rotate() {
        // 그룹 수만큼 반복
        for (int i=0; i<min/2; i++) {
            int x = i;
            int y = i;

            int temp = map[x][y]; // 마지막에 넣을 값 저장 (=그룹에서 가장 첫번째에 있는 값)

            int idx = 0; // 우, 하, 좌, 상 방향으로 인덱스를 방문하면서 반시계 방향으로 값 넣을 인덱스
            while(idx < 4) { // 왼쪽으로 넣는, 위로 넣는, 오른쪽으로 넣는, 아래로 넣는 연산
                int nx = x + dx[idx];
                int ny = y + dy[idx];

                // 범위 안이라면
                if(nx < N-i && ny < M-i && nx >= i && ny >= i) {
                    map[x][y] = map[nx][ny];
                    x = nx;
                    y = ny;
                }
                // 범위를 벗어났다면 다음 방향으로
                else {
                    idx++;
                }
            }
            map[i+1][i] = temp; // 마지막으로 들어가는 값 할당 (가장 첫번째에 있는 값은 아래칸으로 이동)
        }
    }
}

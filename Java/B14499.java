package Java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 주사위 굴리기
 * https://www.acmicpc.net/problem/14499
 */
public class B14499 {

    /**
     * 시간 복잡도 고려 : 최대 1,000번의 명령 * dice 크기는 6 = 6,000
     */
    public static void main(String[] args) throws IOException {
        // 입력값 셋팅
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[][] map = new int[N][M];
        for (int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j=0; j<M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        st = new StringTokenizer(br.readLine(), " ");
        int[] command = new int[K];
        for (int i=0; i<K; i++) {
            command[i] = Integer.parseInt(st.nextToken());
        }

        /**
         * dice Index (바닥면: 6)
         *      2
         *   3  1  4
         *      5
         *      6
         */
        // dx, dy: 방향 명령어(1,2,3,4) 인덱스에 맞춰서 방향 설정(동,서,북,남)
        int[] dx = {0,0,0,-1,1};
        int[] dy = {0,1,-1,0,0};
        /**
         * roll: 주사위를 굴리는 방향별로 주사위 인덱스가 들어가는 순서
         *  - 동쪽: 1->3 | 3->6 | 6->4 | 4->1 (1번 인덱스 값은 3번 인덱스로 이동, 3번 인덱스 값은 6번 인덱스로 이동..)
         *  - 서쪽: 1->4 | 4->6 | 6->3 | 3->1
         *  - 북쪽: 1->2 | 2->6 | 6->5 | 5->1
         *  - 남쪽: 1->5 | 5->6 | 6->2 | 2->1
         */
        int[][] roll = {
                {0}
                ,{3,6,4,1}   // 동
                ,{4,6,3,1}  // 서
                ,{2,6,5,1}  // 북
                ,{5,6,2,1}  // 남
        };
        int[] dice = new int[7]; // 인덱스 1부터 시작
        for (int cmd : command) {
            // 주사위 범위 체크
            int nx = x + dx[cmd];
            int ny = y + dy[cmd];
            if (nx < 0 || nx > N-1 || ny < 0 || ny > M-1) continue;

            // 주사위 굴리기
            // roll[cmd] : 주사위를 cmd 방향으로 굴리면 바뀌는 인덱스 정보 배열
            int temp = dice[1];
            for (int i=3; i>0; i--) {
                dice[roll[cmd][i]] = dice[roll[cmd][i-1]];
            }
            dice[roll[cmd][0]] = temp; // 주사위를 굴리면서 1번 인덱스 값이 들어가는 인덱스에 1번 값 할당

            // 주사위 위치 갱신
            x = nx;
            y = ny;
            if (map[x][y] == 0) { // 주사위의 바닥면에 쓰여 있는 수가 칸에 복사
                map[x][y] = dice[6];
            } else { // 칸에 쓰여 있는 수가 주사위의 바닥면으로 복사 & 칸에 쓰여 있는 수는 0
                dice[6] = map[x][y];
                map[x][y] = 0;
            }

            // 주사위의 윗 면에 쓰여 있는 수를 출력
            System.out.println(dice[1]);
        }
    }
}

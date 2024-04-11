package Java;

import java.util.Scanner;

/**
 * 배열 돌리기 1
 * https://www.acmicpc.net/problem/16926
 */
public class B16926 {
    /**
     * 2 ≤ N, M ≤ 300
     * - 300 * 300 = 90,000
     * 1 ≤ R ≤ 1,000
     * - 회전을 1,000번까지도 할 수 있는데, 배열 크기는 최대 300씩
     * - [실제 회전 수 계산]
     * - 제자리로 돌아오는 경우가 있기에, 회전하는 배열 요소 수를 R로 mod 연산 (R % 요소 개수)
     * - 2*3 배열일 때, R = 14면 (1,1)은 (2,2) = 2번 움직인 것
     * R = 15면 (1,1)은 3번 움직인 것과 같고, R = 16이면 4번 움직인 것과 같음
     * 2*3 배열은 총 6칸이 회전하므로, 14 % 6 = 2, 15 % 6 = 3, 16 % 6 = 4로, 나머지 연산해서 나온 수로 회전하는 것과 같음
     * - [칸 개수 계산]
     * - N,M 중 하나는 짝수
     * - N이 짝수라 했을때, N이 2, 4, 6, 8.. 이면 회전해야하는 그룹수는 1, 2, 3, 4..로 1/2N으로 계산
     * - 각 그룹의 칸 개수
     * - N=6,M=11일때 3개의 그룹을 회전
     * 가장 바깥 그룹은, 6*11 - (6-2)(11-2)
     * 두번째 그룹은, (6-2)(11-2) - (6-4)(11-4)
     * 가장 안쪽 그룹은, (6-4)(11-4)
     * [정리]
     * - 1번 그룹(가장 바깥): NM - (N-2)(M-2)
     * - 2번 그룹: (N-2)(M-2) - (N-4)(M-4)
     * - k번 그룹: (N-(2(k-1)))(M-(2(k-1)))
     * => k번째 그룹(가장 안쪽)부터 반복하면서 개수 구하기
     * min(N, M) mod 2 = 0: N과 M 중 작은 값이 짝수
     * 1 ≤ Aij ≤ 10의 8승(=1억) : 원소 최대값은 1억 / int 최대(2147483647) 넘지 않음
     * <p>
     * [문제 풀이]
     * - 방향 순서: 아래, 오른쪽, 위, 왼쪽
     * - 회전해야하는 수(R)만큼 해당 방향으로 이동
     * (dx,dy): (i+R,j) (i,j+R), (i-R,j), (i,j-R)
     * - 범위가 초과한다면?
     * 1) 변화가 있는 행/열 기준, 해당 행/열을 가장 끝 범위로 설정
     * 2) (바뀐 행/열 - 이전 행/열)만큼 이동한 것이므로 R에서 그만큼 빼기
     * 3) R > 0 이면 다음 방향도 반복
     * 예시) R=2, (3,1)
     * (i,j) 아래로 2번 이동 // 아래로 2번 가면 범위 초과 (5,1)
     * if (i+R > N)
     * (N,j) & R -= (N - i)  // (4,1)로 이동 & R -= 1
     * *N을 i'라 함
     * if (R>0) // R = 1
     * (i', j) // 오른쪽으로 R만큼 이동: (4,1+1) = (4,2)
     * *이때 가장 바깥 그룹을 제외하고는 범위를 방향별로 -1씩 줄여줘야함
     * 2번째 그룹 범위: 2 ~ N-1 / 2 ~ M-1
     * 3번째 그룹 범위: 3 ~ N-2 / 3 ~ M-2
     * k번째 그룹 범위: k ~ N-(k-1) / k ~ M-(k-1)
     * * 그룹별 시작 인덱스는 1번 그룹은 (0,0) / 2번 그룹은 (1,1) / 3번 그룹은 (2,2)로, k번 그룹은 (k-1,k-1)
     */
    static final int dx[] = {1, 0, -1, 0};
    static final int dy[] = {0, 1, 0, -1};

    public static void main(String[] args) {
        // 초기 입력값 세팅
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int r = sc.nextInt();

        int[][] origin = new int[n][m];
        int[][] answer = new int[n][m]; // 정답 배열
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                origin[i][j] = sc.nextInt();
            }
        }
        int even = Math.min(n, m); // min(N, M) mod 2 = 0: N과 M 중 작은 값이 짝수
        int groupCnt = even / 2; // 회전해야하는 그룹수
        int beforeCnt = 0; // 이전 그룹 칸 개수
        for (int i = groupCnt; i > 0; i--) {
            // 그룹의 칸 개수 계산
            // k번 그룹: (N-(2(k-1)))(M-(2(k-1)))
            int boxCnt = (n - 2 * (i - 1)) * (m - 2 * (i - 1)) - beforeCnt; // 현재 그룹 칸 개수

            // 실제 회전 수
            int realRotate = r % boxCnt;

            // 그룹별 범위
            int minIdx = i - 1;
            int maxN = (n - 1) - (i - 1);
            int maxM = (m - 1) - (i - 1);

            // 회전 시작 인덱스 (si, sj)
            int si = i - 1;
            int sj = i - 1;
            int idx = 0; // 회전 방향

            int rotateCnt = 0; // 회전한 요소 개수
            // 배열 회전
            while (true) {
                // for (int j=0; j<boxCnt; j++) {
                // 각 요소별로 회전 횟수만큼 돌때까지 반복
                int[] rotateIdx = rotateBox(si, sj, realRotate, minIdx, maxN, maxM);
                // 회전해서 이동한 인덱스에 현재 인덱스 값 삽입
                answer[rotateIdx[0]][rotateIdx[1]] = origin[si][sj];

                rotateCnt++;
                if (rotateCnt == boxCnt) break; // 모든 요소를 회전시켰으면 탈출

                // 다음 인덱스
                int nx = si + dx[idx % 4];
                int ny = sj + dy[idx % 4];
                // 범위 초과시 다음 방향에 있는 다음 인덱스로 설정
                if ((nx < minIdx || nx > maxN) || (ny < minIdx || ny > maxM)) {
                    idx++;
                }
                si += dx[idx % 4];
                sj += dy[idx % 4];
            }
            beforeCnt = boxCnt; // 다음 계산을 위해 이전 그룹 칸 개수 갱신
        }

        for (int row[] : answer) {
            for (int col : row) {
                System.out.print(col + " ");
            }
            System.out.println();
        }
    }

    public static int[] rotateBox(int si, int sj, int realRotate, int minIdx, int maxN, int maxM) {
        // 방향 계산
        int direction = 0;
        if (si < maxN && sj == minIdx) direction = 0;
        else if (si == maxN && sj < maxM) direction = 1;
        else if (si > minIdx && sj == maxM) direction = 2;
        else if (si == minIdx && sj > minIdx) direction = 3;

        int nx = 0, ny = 0;
        // for (int k=0; k<4; k++) { // 이렇게 하면 다시 아래로 내려갈때 실패
        while (true) {
            nx = si + dx[direction % 4] * realRotate;
            ny = sj + dy[direction % 4] * realRotate;

            // 범위 체크
            if ((nx < minIdx || nx > maxN) || (ny < minIdx || ny > maxM)) {
                if (nx > maxN) {
                    realRotate -= maxN - si;
                    si = maxN;
                } else if (nx < minIdx) {
                    realRotate -= si - minIdx;
                    si = minIdx;
                } else if (ny > maxM) {
                    realRotate -= maxM - sj;
                    sj = maxM;
                } else if (ny < minIdx) {
                    realRotate -= sj - minIdx;
                    sj = minIdx;
                }
            } else {
                // 회전을 모두 하면 탈출
                break;
            }
            direction++;
        }

        return new int[]{nx, ny};
    }

    public static int calDirection(int si, int sj, int minIdx, int maxN, int maxM) {
        /**
         * 아래: 가장 왼쪽 = (x < maxN ,minIdx)
         * 오른쪽: 가장 아래쪽 = (maxN, y < maxM)
         * 위쪽: 가장 오른쪽 = (x > minIdx, maxM)
         * 왼쪽: 가장 위쪽 = (minIdx, y > minIdx)
         */
        if (si < maxN && sj == minIdx) return 0;
        else if (si == maxN && sj < maxM) return 1;
        else if (si > minIdx && sj == maxM) return 2;
        else if (si == minIdx && sj > minIdx) return 3;

        return 0;
    }
}

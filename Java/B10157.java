package Java;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 자리배정
 * https://www.acmicpc.net/problem/10157
 */
public class B10157 {

    /**
     * 가로로 C개, 세로로 R개 좌석 (5 ≤ C, R ≤ 1,000)
     * 어떤 관객의 대기번호 K (1 ≤ K ≤ 100,000,000)
     * K인 관객에게 배정될 준좌석번호 (x,y)를 구해서 두 값, x와 y를 하나의 공백을 사이에 두고 출력
     * 대기번호의 관객에게 좌석을 배정할 수 없는 경우에는 0을 출력
     *
     * 시간복잡도 고려: O(n^2)되면 시간 초과
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int c = sc.nextInt();
        int r = sc.nextInt();
        int k = sc.nextInt();

        if (r*c < k) {
            System.out.println(0);
            return;
        }

        // {위쪽, 오른쪽, 야래쪽, 왼쪽}
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};

        // (1,1)좌석은 (세로 끝 인덱스, 0)에 매칭
        int x = r-1;
        int y = 0;
        // cnt 증가시키면서 k와 같아질때까지 순회하여 좌석 출력
        int cnt = 1;
        int dir = 0; // 방향(처음엔 위쪽)
        boolean[][] hall = new boolean[r][c];
        hall[x][y] = true;
        // cnt == k될때까지 반복
        while (true) {
            if (cnt == k) break;

            hall[x][y] = true;

            int nx = x + dx[dir];
            int ny = y + dy[dir];

            // 더이상 갈 곳이 없을때 방향 전환
            if ((nx >= r || nx < 0) || (ny >= c || ny < 0) || hall[nx][ny]) {
                // 다음 방향으로 변경
                dir = (dir+1) % 4;
                continue;
            }
            cnt++;
            x = nx;
            y = ny;
        }
        // 좌석 x,y는 배열 i,j로 매칭하면, j+1, R-i
        System.out.println((y + 1) + " " + (r - x));
    }
}

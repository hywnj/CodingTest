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

        // 방향 순서 Map - Key: 현재 방향, Value: 다음 방향
        Map<Character, Character> directionMap = new HashMap<>();
        directionMap.put('u', 'r');
        directionMap.put('r', 'd');
        directionMap.put('d', 'l');
        directionMap.put('l', 'u');
        // 방향에 따른 dx, dy 인덱스
        Map<Character, Integer> directionIdx = new HashMap<>();
        directionIdx.put('u', 0);
        directionIdx.put('r', 1);
        directionIdx.put('d', 2);
        directionIdx.put('l', 3);
        // {u(위쪽), r(오른쪽), d(야래쪽), l(왼쪽)}
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};

        // (1,1)좌석은 (세로 끝 인덱스, 0)에 매칭
        int x = r-1;
        int y = 0;
        int hallX = 1;
        int hallY = 1;
        // cnt 증가시키면서 k와 같아질때까지 순회하여 좌석 출력
        int cnt = 1;
        char dir = 'u'; // 맨 처음 방향(위쪽)
        boolean[][] hall = new boolean[r][c];
        hall[x][y] = true;
        // cnt == k될때까지 반복
        while (true) {
            if (cnt == k) break;

            x += dx[directionIdx.get(dir)];
            y += dy[directionIdx.get(dir)];

            // 더이상 갈 곳이 없을때 방향 전환
            if ((x >= r || x < 0) || (y >= c || y < 0) || hall[x][y]) {
                // 위에서 더해준 값 다시 원복
                x -= dx[directionIdx.get(dir)];
                y -= dy[directionIdx.get(dir)];
                // 다음 방향으로 변경
                dir = directionMap.get(dir);
                continue;
            }

            // 위의 경우가 아니라면 방문
            hall[x][y] = true;
            // (hallX, hallY)에서
            //      - hallX는 배열 인덱스 (i,j)로 생각할때 j+1.
            //      - hallY는 배열 인덱스 (i,j)에서 i와 매칭할 수 있는데, 방향이 반대
            //        - 따라서 위, 아래로 이동하여 방문시에 위로 가면 +, 아래로 가면 -한게 현재 좌석 Y 위치
            hallX = y + 1;
            if (dir == 'u') hallY ++;
            else if (dir == 'd') hallY--;
            cnt++;
        }
        System.out.println(hallX+" "+hallY);
    }
}

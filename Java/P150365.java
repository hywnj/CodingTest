package Java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 미로 탈출 명령어
 * https://school.programmers.co.kr/learn/courses/30/lessons/150365
 */
public class P150365 {
    public static void main(String[] args) {
        System.out.println(solution(3,4,2,3,3,1,5)); // "dllrl"
        System.out.println(solution(2,2,1,1,2,2,2)); // "dr"
        System.out.println(solution(3,3,1,2,3,3,4)); // "impossible"
    }

    /**
     * n x m 격자 미로가 주어집니다. 당신은 미로의 (x, y)에서 출발해 (r, c)로 이동해서 탈출해야 합니다.
     * (x, y)에서 (r, c)까지 이동하는 거리가 총 k여야 합니다. 이때, (x, y)와 (r, c)격자를 포함해, 같은 격자를 두 번 이상 방문해도 됩니다.
     * 미로에서 탈출한 경로를 문자열로 나타냈을 때, 문자열이 사전 순으로 가장 빠른 경로로 탈출
     * l: 왼쪽으로 한 칸 이동
     * r: 오른쪽으로 한 칸 이동
     * u: 위쪽으로 한 칸 이동
     * d: 아래쪽으로 한 칸 이동
     *
     * 시작 지점(S)에서 상하 좌우를 탐색하여 이동할 수 있으면 이동
     *      - 시작 지점(S)에서 이동한 지점에서도 상하 좌우를 탐색하여 이동해야함
     *      - 재귀함수로 호출해서 타고 들어가야함
     *      - 경로 탐색시 경로 탐색 여부는 체크할 필요 없음
     *      - 이때, 끝 지점(E)을 k번 이전에 만나도, 다시 뒤로갔다가 갈 수 있는 경우도 있음
     * 이동 거리인 k를 초과하면 break
     * 이동 거리인 k만큼만에 끝 지점(E)에 도착하면 정답 리스트에 저장
     * 위 과정을 시작 지점(S)의 가능한 모든 방향(최대 4방향)으로의 탐색이 끝나면 마침
     * 사전 순으로 빠른 경로를 반환 => 탐색 시 알파벳 순으로 탐색(d->l->r->u)
     */
    static char[][] maze;
    static String[] direction = {"d", "l", "r", "u"}; // d(아래) l(왼쪽) r(오른) u(위)
    static int[] dx = {1, 0, 0, -1};
    static int[] dy = {0, -1, 1, 0};
    static int N, M, K;
    static List<String> list;
    public static String solution(int n, int m, int x, int y, int r, int c, int k) {
        list = new ArrayList<>();
        maze = new char[n][m];
        // 미로 배열 초기화
        for (char arr[]: maze) {
            Arrays.fill(arr, '.');
        }
        x -= 1;
        y -= 1;
        r -= 1;
        c -= 1;
        maze[x][y] = 'S';
        maze[r][c] = 'E';
        N = n;
        M = m;
        K = k;

        int nx, ny;
        for (int i = 0; i < 4; i++) {
            nx = x + dx[i];
            ny = y + dy[i];

            if (list.isEmpty()) dfs(nx, ny, direction[i]); // list에 담긴 값이 없으면 다시 dfs
            else break;
        }
        String answer = "impossible";
        if (!list.isEmpty()) answer = list.get(0);
        return answer;
    }

    public static void dfs(int x, int y, String route) {
        int nx, ny;
        for (int i = 0; i < 4; i++) {
            if (!list.isEmpty()) return; // 정답 경로를 찾았다면 return
            nx = x + dx[i];
            ny = y + dy[i];

            if ((nx >= 0 && nx < N) && (ny >= 0 && ny < M)) {
                int length = route.length() + 1;
                if (length == K) { // 경로 탐색 성공
                    if (maze[nx][ny] == 'E') {
                        list.add(route + direction[i]);
                        return;
                    }
                } else if (length < K) { // K번 미만이면 경로 저장 & 경로 탐색
                    dfs(nx, ny, route + direction[i]);
                } else {  // 경로 탐색 실패
                    return;
                }
            }
        }
    }
}


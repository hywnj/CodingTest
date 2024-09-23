package Java;

/**
 * DFS와 BFS
 * https://www.acmicpc.net/problem/1260
 */

import java.io.*;
import java.util.*;

public class B1260 {
    static boolean[][] node;
    static boolean[] visited;
    static List<String> answer;
    static int idx;
    static int n , v;
    public static void main(String[] args) throws IOException {
        // 단, 방문할 수 있는 정점이 여러 개인 경우에는 정점 번호가 작은 것을 먼저 방문하고, 더 이상 방문할 수 있는 점이 없는 경우 종료
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        v = Integer.parseInt(st.nextToken());

        node = new boolean[n+1][n+1]; // node 번호는 1부터 시작
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int first = Integer.parseInt(st.nextToken());
            int second = Integer.parseInt(st.nextToken());

            if (!node[first][second]) node[first][second] = true;
            if (!node[second][first]) node[second][first] = true;
        }

        // DFS
        init();
        dfs(v);
        System.out.println(String.join(" ", answer));

        // BFS
        init();
        Queue<Integer> q = new LinkedList<>();
        q.offer(v);
        while (!q.isEmpty()) {
            int num = q.poll();
            for (int i = 1; i <= n; i++) {
                if (i == 0 || i == num) continue;
                if (!visited[i] && node[num][i]) {
                    visited[i] = true;
                    answer.add(i + "");
                    q.offer(i);
                }
            }
        }
        System.out.println(String.join(" ", answer).trim());
    }

    public static void dfs(int num) {
        for (int i = 1; i <= n; i++) {
            if (i == 0 || i == num) continue; // 노드는 1번부터 , 자기자신은 탐색 안함
            if (!visited[i] && node[num][i]) {
                visited[i] = true;
                answer.add(i + "");
                dfs(i);
            }
        }
    }

    public static void init() {
        answer = new ArrayList<>();
        visited = new boolean[n+1];

        idx = 0;
        answer.add(v + "");
        visited[v] = true;
    }
}

package Java;

/**
 *  그래프 최단거리 : 최소 비행료
 */
import java.util.*;
public class I8001 {
    static ArrayList<Node>[] graph;

    public int solution(int n, int[][] flights, int s, int e, int k) {
        graph = new ArrayList[n + 1]; // 각 노드별로 연결된 노드와 비용을 저장
        for (int i = 0; i <= n; i++)  graph[i] = new ArrayList<>();

        for (int[] flight : flights) {
            graph[flight[0]].add(new Node(flight[1], flight[2])); // 단방향
        }
        // s -> e 로
        int[] dist = new int[n + 1]; // s부터 각 노드까지의 최단 비용
        Arrays.fill(dist, 1_000_000_000);

        boolean[] checked = new boolean[n + 1]; // 노드 방문 여부
        PriorityQueue<Info> pq = new PriorityQueue<>(); // Node 클래스의 compareTo 기준으로 정렬 (비용 기준)
        pq.offer(new Info(new Node(s, 0), 0)); // 처음에는 시작 노드와 비용 0을 넣고 시작
        while (!pq.isEmpty()) {
            Info info = pq.poll();
            Node node = info.node;

            if (info.k > k) continue; // 최대 k번 환승 가능
            if (checked[node.num]) continue; // 이미 방문한 노드
            checked[node.num] = true;

            for (Node next : graph[node.num]) { // 현재 노드랑 연결된 다음 노드 탐색
                // 다음 노드와 출발지 비용과 출발지 -> 현재 노드 -> 다음 노드 비용을 비교해서, 작은 값을 다음 노드까지의 비용 배열에 할당
                if (dist[next.num] > node.cost + next.cost) {
                    dist[next.num] = node.cost + next.cost;
                    pq.offer(new Info(new Node(next.num, dist[next.num]), info.k + 1));
                }
            }
        }

        if (dist[e] == 1_000_000_000) return -1;
        else return dist[e];
    }

    public class Info implements Comparable<Info>{
        Node node;
        int k;

        public Info(Node node, int k) {
            this.node = node;
            this.k = k;
        }

        @Override
        public int compareTo(Info o) {
            return this.node.cost - o.node.cost;
        }
    }
    public class Node{
        int num;
        int cost;

        public Node(int num, int cost) {
            this.num = num;
            this.cost = cost;
        }
    }

    public static void main(String[] args){
        I8001 T = new I8001();
        System.out.println(T.solution(5, new int[][]{{0, 1, 10}, {1, 2, 20}, {0, 2, 70}, {0, 3, 100}, {1, 3, 80}, {2, 3, 10}, {2, 4, 30}, {3, 4, 10}}, 0, 3, 1));
        System.out.println(T.solution(4, new int[][]{{0, 1, 10}, {0, 2, 10}, {1, 3, 5}, {2, 3, 3}}, 0, 3, 0));
        System.out.println(T.solution(8, new int[][]{{0, 3, 10}, {1, 5, 10}, {1, 7, 100}, {0, 1, 10}, {0, 2, 10}, {5, 7, 30}, {3, 7, 10}, {1, 3, 5}, {2, 3, 3}}, 1, 7, 2));
        System.out.println(T.solution(10, new int[][]{{1, 8, 50}, {0, 8, 30}, {1, 0, 10}, {2, 8, 10}, {0, 3, 10}, {1, 5, 10}, {1, 7, 100}, {0, 1, 10}, {0, 2, 10}, {5, 7, 30}, {3, 7, 10}, {1, 3, 5}, {2, 3, 3}}, 1, 8, 2));
        System.out.println(T.solution(4, new int[][]{{0, 3, 59},{2, 0, 83}, {3, 1, 16}, {1, 3, 16}}, 3, 0, 3));
    }

}

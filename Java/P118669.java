package Java;

import java.util.*;

/**
 * 등산코스 정하기
 * https://school.programmers.co.kr/learn/courses/30/lessons/118669
 */
public class P118669 {
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        /**
         * n은 최대 50,000이므로, 중복 for문은 시간초과 위험
         *  => 다익스트라 알고리즘을 사용해 출입구(gates)에서 시작하여 모든 산봉우리까지 가는 최적 경로를 한 번에 구하기
         */
        // 노드 연결 그래프 세팅
        ArrayList<Node>[] graph = new ArrayList[n + 1]; // 노드 연결 정보
        for (int i = 1; i <= n; i++) graph[i] = new ArrayList<>(); // 초기화
        for (int[] path : paths) {
            // 양방향 그래프
            graph[path[0]].add(new Node(path[1], path[2]));
            graph[path[1]].add(new Node(path[0], path[2]));
        }
        // 산봉우리
        Set<Integer> summitSet = new HashSet<>();
        for (int summit: summits) summitSet.add(summit);

        // 다익스트라 탐색
        int[] intensity = new int[n + 1]; // 해당 노드까지의 최단 intensity
        Arrays.fill(intensity, 1_000_000_000);
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.intensity)); // intensity 작은순
        // 출발점 PQ에 추가
        for (int gate : gates) {
            pq.offer(new Node(gate, 0));
            intensity[gate] = 0;
        }

        while (!pq.isEmpty()) {
            Node curr = pq.poll();
            // 현재 노드의 intensity가 기존 누적 저장된 intensity보다 크면 continue
            if (intensity[curr.num] < curr.intensity) continue;

            // 현재 노드의 연결 노드 탐색
            for (Node next : graph[curr.num]) {
                int max = Math.max(curr.intensity, next.intensity);
                // 더 작은 intensity 경로가 있을 때만 업데이트
                if (intensity[next.num] > max) {
                    intensity[next.num] = max;
                    // 다음 노드가 산봉우리가 아닌 경우에만 pq에 추가
                    if (!summitSet.contains(next.num)) pq.offer(new Node(next.num, max));
                }
            }
        }

        // 산봉우리 번호가 작은순
        Arrays.sort(summits);
        int min = 1_000_000_000;
        int num = 0;
        for (int summit : summits) {
            if (intensity[summit] < min) {
                min = intensity[summit];
                num = summit;
            }
        }

        return new int[]{num, min};
    }

    public class Node {
        int num;
        int intensity;

        public Node(int num, int intensity) {
            this.num = num;
            this.intensity = intensity;
        }
    }

    public static void main(String[] args) {
        P118669 T = new P118669();

        System.out.println(Arrays.toString(T.solution(6,
                new int[][]{{1, 2, 3}, {2, 3, 5}, {2, 4, 2}, {2, 5, 4}, {3, 4, 4}, {4, 5, 3}, {4, 6, 1}, {5, 6, 1}},
                new int[]{1, 3}, new int[]{5}))); // [5, 3]

        System.out.println(Arrays.toString(T.solution(7,
                new int[][]{{1, 4, 4}, {1, 6, 1}, {1, 7, 3}, {2, 5, 2}, {3, 7, 4}, {5, 6, 6}},
                new int[]{1}, new int[]{2, 3, 4}))); // [3, 4]

        System.out.println(Arrays.toString(T.solution(7,
                new int[][]{{1, 2, 5}, {1, 4, 1}, {2, 3, 1}, {2, 6, 7}, {4, 5, 1}, {5, 6, 1}, {6, 7, 1}},
                new int[]{3, 7}, new int[]{1, 5}))); // [5, 1]

        System.out.println(Arrays.toString(T.solution(5,
                new int[][]{{1, 3, 10}, {1, 4, 20}, {2, 3, 4}, {2, 4, 6}, {3, 5, 20}, {4, 5, 6}},
                new int[]{1, 2}, new int[]{5}))); // [5, 6]

    }
}

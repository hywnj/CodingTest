package Java;

import java.util.*;

/**
 * 등산코스 정하기
 * https://school.programmers.co.kr/learn/courses/30/lessons/118669
 */
public class P118669 {
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        /**
         * 각 경로별 산봉우리 정보 + intensity 정보
         * 산봉우리는 1번만
         * 출입구 기준 gates -> summits 도달하는 비용 계산해서 저장
         */
        // 노드 연결 그래프 세팅
        ArrayList<Node>[] graph = new ArrayList[n + 1]; // 노드 연결 정보
        for (int i = 1; i <= n; i++) graph[i] = new ArrayList<>(); // 초기화
        // 한 번만 지나야 하는 출입구, 산봉우리 표시
        boolean[] once = new boolean[n + 1];
        for (int gate: gates) once[gate] = true;
        for (int summit: summits) once[summit] = true;

        for (int[] path : paths) {
            // 양방향 그래프
            graph[path[0]].add(new Node(path[1], path[2]));
            graph[path[1]].add(new Node(path[0], path[2]));
        }
        List<Course> answers = new ArrayList<>(); // 정답 후보 배열
        for (int gate : gates) {
            // 모든 출입구에서 모든 산봉우리까지의 최대 휴식 시간을 구하기
            for (int summit : summits) {
                boolean[] visited = new boolean[n + 1];
                Queue<Course> q = new LinkedList<>();
                q.offer(new Course(gate, 0));

                while (!q.isEmpty()) {
                    Course course = q.poll();
                    // 산봉우리 도달시 정답 배열에 추가
                    if (course.num == summit) {
                        answers.add(new Course(summit, course.intensity));
                        continue;
                    }
                    // 방문 여부 체크 (양방향이 아니여도, 출입구부터 해당 산봉우리까지의 모든 단방향 루트를 구하면 모든 intensity를 구할 수 있음)
                    if (visited[course.num]) continue;
                    visited[course.num] = true;


                    // 현재 방문한 노드의 연결 노드
                    for (Node next : graph[course.num]) {
                        // 한번만 지나야하는 경로로 간 경우 제외
                        if (next.num != summit && once[next.num]) continue;
                        int max = Math.max(next.time, course.intensity); // 현재 intensity와 다음 노드 intensity 중 최댓값
                        q.offer(new Course(next.num, max));
                    }

                }
            }


        }

        answers.sort(Course::compareTo);
        return new int[]{answers.get(0).num, answers.get(0).intensity};
    }

    public class Node {
        int num;
        int time;

        public Node(int num, int time) {
            this.num = num;
            this.time = time;
        }
    }
    public class Course implements Comparable<Course> {
        int num;
        int intensity;

        public Course(int num, int intensity) {
            this.num = num;
            this.intensity = intensity;
        }

        @Override
        public int compareTo(Course o) {
            if (this.intensity == o.intensity) return this.num - o.num; // 같다면 노드 번호가 작은 순
            else return this.intensity - o.intensity; // intensity가 작은 순
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

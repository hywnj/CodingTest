package Java;

import java.util.*;
/**
 * 도넛과 막대 그래프
 * https://school.programmers.co.kr/learn/courses/30/lessons/258711
 *  - ver.1 : test case 22, 26 fail : 아마도 최대 재귀 깊이에 걸린듯 함
 *  - ver.2 : 그래프 별 정점의 규칙 찾아서 풀기 => 정답 & 풀이시간도 반이상 단축
 */
public class P258711 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution(new int[][]{{2, 3}, {4, 3}, {1, 1}, {2, 1}}))); // [2, 1, 1, 0]
        System.out.println(Arrays.toString(solution(new int[][]{{4, 11}, {1, 12}, {8, 3}, {12, 7}, {4, 2}, {7, 11}, {4, 8}, {9, 6}, {10, 11}, {6, 10}, {3, 5}, {11, 1}, {5, 3}, {11, 9}, {3, 8}}))); // [4, 0, 1, 2]
    }
    public static class Node {
        int in = 0; // 들어오는 간선
        int out = 0; // 나가는 간선
        boolean visited = false;
        List<Integer> childNodeList = new ArrayList<>(); // 자식 노드 (=나가는 간선이 가리키는 노드)
        public Node(int child) {
            if (child != 0) this.addChildNode(child); // 자식 노드가 있을때
            else this.in++;
        }
        public void addChildNode(int child) {
            this.out++;
            this.childNodeList.add(child);
        }
    }

    public static int[] solution(int[][] edges) {
        /**
         * 방문한 노드인데 자식 노드로 재방문하는 경우, 도넛 모양
         * 단방향이라 무조건 한번씩만 방문하는 막대 모양 그래프는, Leaf 노드가 in=1, out=0 | Root 노드는 in=0, out=1
         * in=2, out=2인 노드가 있으면 8자 모양
         */
        int donut = 0;
        int bar = 0;
        int eight = 0;
        Map<Integer, Node> nodeMap = new HashMap<>(); // key: Node num, value: Node 정보
        // Node Setting
        for (int[] edge: edges) {
            int node = edge[0];
            int childNode = edge[1];

            if (nodeMap.containsKey(node)) nodeMap.get(node).addChildNode(childNode);
            else nodeMap.put(node, new Node(childNode));

            if (nodeMap.containsKey(childNode)) nodeMap.get(childNode).in++;
            else nodeMap.put(childNode, new Node(0));
        }
        // 중앙 노드 찾기
        int middle = 0;
        Iterator<Integer> it = nodeMap.keySet().iterator();
        while (it.hasNext()) {
            int nodeNum = it.next();
            Node node = nodeMap.get(nodeNum);
            // 들어오는 간선(in), 나가는 간선(out) 계산
            if (node.in == 0 && node.out >= 2) {
                middle = nodeNum;
                break;
            }
        }
        Stack<Integer> searchStack = new Stack<>(); // 탐색할 노드 번호 (깊이 탐색을 해야하기 때문에 Stack)
        for (int child: nodeMap.get(middle).childNodeList) {
            searchStack.push(child);
            nodeMap.get(child).in--; // 중앙 노드에서 들어오는 선 제외
        }
        // Find Graph
        while (!searchStack.isEmpty()) {
            Node child = nodeMap.get(searchStack.pop());
            int in = child.in;
            int out = child.out;

            if (in == 2 && out == 2) {
                eight++;
            } else if ((in == 0 && out == 0) || (in == 1 && out == 0) || (in == 0 && out == 1)) {
                bar++;
            } else {
                if (child.visited && (in == 1 && out == 1)) donut++;
                else if (!child.childNodeList.isEmpty()) for (int num: child.childNodeList) searchStack.push(num);
            }
            child.visited = true;
        }
        return new int[]{middle, donut, bar, eight};
    }
}

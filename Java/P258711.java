package Java;

import java.util.*;

/**
 * 도넛과 막대 그래프
 * https://school.programmers.co.kr/learn/courses/30/lessons/258711
 * ver.1 : test case 22, 26 fail
 */
public class P258711 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution(new int[][]{{2, 3}, {4, 3}, {1, 1}, {2, 1}}))); // [2, 1, 1, 0]
        System.out.println(Arrays.toString(solution(new int[][]{{4, 11}, {1, 12}, {8, 3}, {12, 7}, {4, 2}, {7, 11}, {4, 8}, {9, 6}, {10, 11}, {6, 10}, {3, 5}, {11, 1}, {5, 3}, {11, 9}, {3, 8}}))); // [4, 0, 1, 2]
    }
    static Map<Integer, Node> nodeMap; // key: nodeNum, value: Node Info
    public static int[] solution(int[][] edges) {
        nodeMap = new HashMap<>();
        // Node Setting
        for (int[] edge: edges) {
            int node = edge[0];
            int linkedNode = edge[1];

            if (nodeMap.containsKey(node)) nodeMap.get(node).addChildNode(linkedNode);
            else nodeMap.put(node, new Node(node, linkedNode, true));

            if (node != linkedNode) { // 자기 자신이 아닌 경우만
                if (nodeMap.containsKey(linkedNode)) nodeMap.get(linkedNode).addParentNode(node);
                else nodeMap.put(linkedNode, new Node(linkedNode, node, false));
            }

        }
        // Find middle node
        int middleNodeNum = 0;
        Iterator<Integer> it = nodeMap.keySet().iterator();
        while (it.hasNext()) {
            Node node = nodeMap.get(it.next());
            // 들어오는 간선(inCnt), 나가는 간선(outCnt) 계산
            if (node.isMiddleNode()) {
                middleNodeNum = node.nodeNum;
                break;
            }
        }
        // Find Graph (middle node~)
        Node middleNode = nodeMap.get(middleNodeNum);
        int donut = 0;
        int bar = 0;
        int eight = 0;
        // 중앙 노드랑 연결된 노드 탐색
        for (int childNum : middleNode.childNodeSet) {
            Node childNode = nodeMap.get(childNum);
            // 연결된 그래프 판별 및 간선 수 갱신
            for (int num: childNode.childNodeSet) {
                childNode.visitedChildNode.put(num, true);
                findGraph(num, childNum);
            }
            // 그래프 모양 판별
            int nodeCnt = childNode.linkedNodeSet.size() - 1;
            int edgeCnt = childNode.edgeCnt - 1;
            if (nodeCnt == edgeCnt) donut++;
            else if (nodeCnt - 1 == edgeCnt) bar++;
            else eight++;
        }
        return new int[]{middleNodeNum, donut, bar, eight};
    }
    public static void findGraph(int childNodeNum, int startNodeNum) {
        Node child = nodeMap.get(childNodeNum);
        Node start = nodeMap.get(startNodeNum);

        if (childNodeNum == startNodeNum) return; // 처음 노드로 돌아오면 return

        // 연결 노드가 있는지 확인
        if (!child.childNodeSet.isEmpty()) {
            for (int num: child.childNodeSet) {
                if (!child.visitedChildNode.containsKey(num) && num != startNodeNum) {
                    child.visitedChildNode.put(num, true);
                    start.edgeCnt++; // 연결된 노드를 탐색하는 메서드이므로, 간선 수 +1
                    start.linkedNodeSet.add(num);
                    findGraph(num, startNodeNum);
                }
            }
        }
    }
    public static class Node {
        int nodeNum; // 현 노드 번호
        int edgeCnt = 0; // 간선 수
        Set<Integer> childNodeSet = new HashSet<>(); // 현 노드의 자식 노드
        Set<Integer> linkedNodeSet = new HashSet<>(); // 현 노드에 연결되어 있는 모든 노드
        Map<Integer, Boolean> visitedChildNode = new HashMap<>();
        public Node(int nodeNum, int linkedNodeNum, boolean parentFlag) {
            this.nodeNum = nodeNum;
            this.linkedNodeSet.add(nodeNum);

            // 연결되어있는 노드가 있는 경우 (뻗어나가는 화살표 왼쪽인 노드)
            if (parentFlag) this.addChildNode(linkedNodeNum);
            else this.addParentNode(linkedNodeNum);

            this.edgeCnt++; // 연결된 간선 수 +1
        }
        // 중앙 노드 판별 메서드
        public boolean isMiddleNode() {
            int outCnt = this.childNodeSet.size();
            int inCnt = this.edgeCnt - outCnt; // 자기자신을 제외한 전체 노드 - 자식 노드 = 부모 노드

            if (outCnt >= 2 && inCnt == 0) return true;
            else return false;
        }
        // 자식 노드 추가 메서드
        public void addChildNode(int linkedNodeNum) {
            if (this.edgeCnt != 0) this.edgeCnt++; // 생성자에서 호출하는 경우가 아닐때
            this.linkedNodeSet.add(linkedNodeNum);
            this.childNodeSet.add(linkedNodeNum);
        }
        // 부모 노드 추가 메서드
        public void addParentNode(int nodeNum) {
            if (this.edgeCnt != 0) this.edgeCnt++; // 생성자에서 호출하는 경우가 아닐때
            this.linkedNodeSet.add(nodeNum);
        }
    }
}

package Java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 트리의 순회
 * https://www.acmicpc.net/problem/2263
 */
public class B2263 {
    /**
     * n개의 정점을 갖는 이진 트리의 정점에 1부터 n까지의 번호가 중복 없이 매겨져 있다.
     * 이와 같은 이진 트리의 인오더와 포스트오더가 주어졌을 때, 프리오더를 구하는 프로그램을 작성하시오.
     *
     * 첫째 줄에 n(1 ≤ n ≤ 100,000)이 주어진다.
     * 다음 줄에는 인오더를 나타내는 n개의 자연수가 주어지고, 그 다음 줄에는 같은 식으로 포스트오더가 주어진다.
     *
     * Postorder 마지막 인덱스 = Root Node
     *  - Postorder에서 찾은 Root Node를 기준으로 Inorder에서 왼쪽 자식노드, 오른쪽 자식 노드 찾기
     * 자식노드의 Root 노드는 Postorder에서 위에서 찾은 오른쪽 자식노드의 수를 가지고 확인
     *  - 오른쪽 자식 노드의 수 = Inorder에서 확인한 오른쪽 자식노드 수 = n - (Root Index)
     *  - Postorder 배열에서 (n - right 개수)인덱스부터 오른쪽 자식 트리이고 그 전이 왼쪽 자식 트리
     * 이후에는 위 과정을 반복해서 트리의 프리오더를 구할 수 있음
     *
     * [트리 순회]
     *  Preorder(전위): root -> left -> right
     *  Inorder(중위): left -> root -> right
     *  Postorder(후위): left -> right -> root
     */
    // String vs StringBuilder 중 성능이 빠른 StringBuilder로 선언
    static StringBuilder pre = new StringBuilder();
    static int n;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        // contains로 value 찾기 위해 List 자료구조 사용
        List<Integer> in = new ArrayList<>();
        for (int i=0; i<n; i++) {
            in.add(Integer.parseInt(st.nextToken()));
        }

        st = new StringTokenizer(br.readLine(), " ");
        List<Integer> post = new ArrayList<>();
        for (int i=0; i<n; i++) {
            post.add(Integer.parseInt(st.nextToken()));
        }
        // 전위 순회
        makePreOrder(in, post);
        // 결과 출력
        System.out.println(pre);
    }

    /**
     * 전위 순회
     * @param in
     *  [inorder 배열 기준]
     *      - 왼쪽 트리: 시작 인덱스 ~ rootIdx(in의 root 노드 인덱스) - 1
     *      - 오른쪽 트리: rootIdx + 1 ~ 마지막 인덱스
     * @param post
     *  [postorder 배열 기준]
     *      - 왼쪽 트리: 시작 인덱스 ~ rootIdx(in의 root 노드 인덱스) - 1
     *      - 오른쪽 트리: rootIdx ~ 마지막 인덱스 - 1 (마지막 인덱스는 root)
     *  * 트리 순회는 left를 right보다 먼저 순회하기 때문에, 왼쪽 트리는 inorder, postorder 배열의 인덱스가 같음
     */
    public static void makePreOrder(List<Integer> in, List<Integer> post) {
        /**
         * 1. postorder에서 root 확인
         *      - post의 마지막 인덱스에서 알수 있음
         * 2. inorder에서 left, right 트리 확인
         *      - root index 기준으로 left, right 트리를 매개변수로 보내서, 트리별로 순회
         *      - 순회시 Node가 1개면 탈출
         */
        if (in.isEmpty() || post.isEmpty()) return;
        int root = post.get(post.size() - 1); // postorder의 마지막 요소가 root
        pre.append(root+ " "); // preorder에 root add

        if (in.size() == 1 || post.size() == 1) return; // 한개의 노드만 있다면 순회 종료

        // inorder의 root index 기준으로 left, right 나뉨
        int rootIdx = in.indexOf(root);
        // left sub tree
        makePreOrder(in.subList(0, rootIdx), post.subList(0, rootIdx));
        // right sub tree
        makePreOrder(in.subList(rootIdx + 1, in.size()), post.subList(rootIdx, in.size() - 1));
    }
}

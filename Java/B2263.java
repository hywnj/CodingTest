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
    // contains로 value 찾기 위해 List 자료구조 사용
    // static List<Integer> in = new ArrayList<>();
    // static List<Integer> post = new ArrayList<>();
    static int[] in; // 중위 순회 값의 인덱스를 value로 저장
    static int[] post;

    static int n;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        in = new int[n + 1];
        post = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        for (int i=0; i<n; i++) {
            // 중위 순회 배열에서는 root의 인덱스를 찾아야되기 때문에, 각 값의 인덱스를 value로 저장
            in[Integer.parseInt(st.nextToken())] = i;
            // in.add(Integer.parseInt(st.nextToken()));
        }

        st = new StringTokenizer(br.readLine(), " ");
        for (int i=0; i<n; i++) {
            post[i] = Integer.parseInt(st.nextToken());
            // post.add(Integer.parseInt(st.nextToken()));
        }
        // 전위 순회
        // makePreOrder(in, post);
        makePreOrderFromIdx(0,n-1,0,n-1);
        // 결과 출력
        System.out.println(pre);
    }

    /**
     * 인덱스로 트리 전위 순회
     * [inorder 배열]
     *  - left sub tree: in 시작 인덱스 ~ (root 인덱스 - 1) (root 인덱스 왼쪽 부분)
     *  - right sub tree: (root 인덱스 + 1) ~ in 배열 끝 인덱스 (root 인덱스 오른쪽 부분)
     * [postorder 배열]
     *  - left sub tree: post 시작 인덱스 ~ (post 끝 인덱스 - right Node 개수 - 1) (인덱스 계산을 위해 -1)
     *  - right sub tree: (post 끝 인덱스 - right node 개수) ~ post 끝 인덱스 - 1 (마지막 인덱스는 root)
     * @param in_start : inorder 배열 시작 인덱스
     * @param in_end : inorder 배열 끝 인덱스
     * @param post_start : postorder 배열 시작 인덱스
     * @param post_end : postorder 배열 끝 인덱스
     */
    public static void makePreOrderFromIdx(int in_start, int in_end, int post_start, int post_end) {
        if (in_start > in_end || post_start > post_end) return;

        int root = post[post_end];
        // int root = post.get(post_end);
        pre.append(root+ " "); // preorder에 root add

        // inorder의 root index 기준으로 left, right 나뉨
        int rootIdx = in[root];
        /**
         * List의 indexOf 함수는 원하는 값을 찾을때까지 배열을 순회하기 때문에, 그 시간이 추가로 걸림
         * 성능 최적화를 위해 inorder 배열 저장 방식을 수정함.
         */
        // int rootIdx = in.indexOf(root);
        int rightNodeCnt = in_end - rootIdx;

        // left subtree
        makePreOrderFromIdx(in_start, rootIdx - 1, post_start, post_end - rightNodeCnt - 1);

        // right subtree
        makePreOrderFromIdx(rootIdx + 1, in_end, post_end - rightNodeCnt, post_end - 1);
    }

    /**
     * 배열을 매개변수로 한 전위 순회
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
        // left subtree
        makePreOrder(in.subList(0, rootIdx), post.subList(0, rootIdx));
        // right subtree
        makePreOrder(in.subList(rootIdx + 1, in.size()), post.subList(rootIdx, in.size() - 1));
    }
}

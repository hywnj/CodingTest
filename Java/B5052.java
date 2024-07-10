package Java;

import java.io.*;
import java.util.*;

/**
 * 전화번호 목록
 * https://www.acmicpc.net/problem/5052
 */
public class B5052 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int t = Integer.parseInt(st.nextToken());

        boolean[] answers = new boolean[t];
        for (int i=0; i<t; i++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());

            // 한번에 다 받은 후에 길이가 짧은 순으로 정렬
            String[] numbers = new String[n];
            for (int j=0; j<n; j++) {
                st = new StringTokenizer(br.readLine());
                numbers[j] = st.nextToken();
            }
            Arrays.sort(numbers, (o1, o2) -> o1.length() - o2.length());

            // Trie 생성
            // Leaf 노드가 아닌데 endOfWord가 되면, 일관성 없는 목록
            Trie trie = new Trie();
            boolean flag = true;
            for (String number: numbers) {
                // 일관성 없는 목록으로 판단되면 해당 테스트 케이스 반복 종료
                if (flag) flag = trie.insert(number);
            }
            answers[i] = flag;
        }
        // 결과 출력
        for (boolean answer: answers) {
            if (answer) System.out.println("YES");
            else System.out.println("NO");
        }
    }
    public static class Node {
        public HashMap<Character, Node> childNode = new HashMap<>(); // 자식 노드
        public boolean endOfWord = false;
    }
    public static class Trie {
        private Node rootNode;
        public Trie() {
            rootNode = new Node();
        }
        public boolean insert(String number) {
            Node node = this.rootNode;

            for (int i=0; i<number.length(); i++) {
                // 자식 노드에 문자가 있고, 그 자식 노드가 단어의 끝(endOfWord)이면 일관성 없음
                if (node.childNode.containsKey(number.charAt(i))
                        && node.childNode.get(number.charAt(i)).endOfWord) {
                    return false;
                }

                // 자식 노드에 있는 문자라면 자식 노드를 가져오고, 자식 노드에 없다면 새로 생성
                // computeIfAbsent: Map에 Key가 없다면 Value를 새로 만들어줌
                node = node.childNode.computeIfAbsent(number.charAt(i), key -> new Node());
            }
            node.endOfWord = true; // 번호의 문자를 모두 순회하면 마지막 노드는 리프 노드
            return true;
        }
    }
}

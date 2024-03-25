package Java;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 괄호 회전하기
 * https://school.programmers.co.kr/learn/courses/30/lessons/76502
 */
public class P76502 {
    public static void main(String[] args) {
        System.out.println(solution("[](){}")); // 3
        System.out.println(solution("}]()[{")); // 2
        System.out.println(solution("[)(]")); // 0
        System.out.println(solution("}}}")); // 0
    }

    /**
     * [간략한 풀이 방식 생각]
     *  1. 괄호 회전
     *  2. 회전한 괄호가 올바른 괄호인지 확인
     *      - 괄호 문자열을 문자 Queue로 할당하여 확인
     * [시간복잡도 고려?]
     *  - s는 최대 1,000자 -> 1,000,000(n^2하면 10만)
     */
    public static int solution(String s) {
        int answer = 0;
        int index = 0; // 괄호 회전시 첫 인덱스
        // originQueue은 객체이고, 주소값으로 참조하기 때문에, Queue 객체를 그대로 = 할당하면 얕은 복사가 되어서 원본을 유지하지 못함
        // 그래서 makeQueue 메서드를 정의해서 사용
        for (int r=0; r<s.length(); r++) {
            if (checkedString(makeQueue(s, index))) answer++;
            index++;
        }
        return answer;
    }

    /**
     * 괄호 회전해서 Queue로 만드는 메서드
     * @param s : 원본 문자열
     * @param startIdx : 원본 문자열에서 가장 먼저 Queue에 들어가는 인덱스
     * @return 올바른 괄호 문자열인지 확인할 Queue
     */
    public static Queue<Character> makeQueue(String s, int startIdx) {
        Queue<Character> copyQ = new LinkedList<>();

        int index = startIdx;
        while (true) {
            if (s.length() == copyQ.size()) break;
            if (index >= s.length()) {
                index -= s.length();
            }
            copyQ.offer(s.charAt(index));
            index++;
        }
        return copyQ;
    }

    /**
     * 타겟 문자열이 올바른 괄호인지 확인
     */
    public static boolean checkedString(Queue<Character> originQueue) {
        Stack<Character> stack = new Stack<>(); // 완성이 안된 괄호를 저장할 스택

        while (!originQueue.isEmpty()) {
            char c = originQueue.poll();

            if (!stack.isEmpty()) {
                char storagedC = stack.peek();
                // 괄호인 경우에만 임시로 저장해둔 Stack에서 괄호 삭제
                if (storagedC == '[' && c == ']' || storagedC == '{' && c == '}' || storagedC == '(' && c == ')') {
                    stack.pop();
                    continue;
                }
            } else {
                // 저장되어있는 괄호가 없는데, 즉 괄호가 시작되어야 하는데 닫힌 괄호면 false
                if (c == ']' || c == '}' || c == ')') return false;
            }
            stack.push(c);
        }
        if (!stack.isEmpty()) return false;
        else return true;
    }
}
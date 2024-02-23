package Java;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 올바른 괄호
 * https://school.programmers.co.kr/learn/courses/30/lessons/12909
 */
public class P12909 {
    public static void main(String[] args) {
        System.out.println(solution("()()"));   // true
        System.out.println(solution("(())()")); // true
        System.out.println(solution(")()("));   // false
        System.out.println(solution("(()("));   // false
    }

    public static boolean solution(String s) {
        Stack<Character> stackInitial = new Stack<>(); // 문자열을 문자로 분할해서 넣을 Stack
        for (int i=0; i<s.length(); i++) {
            stackInitial.push(s.charAt(i));
        }

        char c;
        Stack<Character> stackStorage = new Stack<>(); // 괄호 완성이 안되는 경우 초기 Stack의 char 값을 저장할 Stack
        while (!stackInitial.isEmpty()) {
            c = stackInitial.pop();
            if (!stackStorage.isEmpty()) {
                char charStorage = stackStorage.peek();
                if (c == '(' && charStorage == ')') { // 괄호 완성하면 다음 문자로 넘어감
                    stackStorage.pop();
                    continue;
                }
            }
            stackStorage.push(c);
        }

        // 남은 문자열이 있으면 실패
        if (!stackStorage.isEmpty()) return false;
        else return true;
    }
}

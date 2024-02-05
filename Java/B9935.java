package Java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/**
 * 문자열 폭발 (Gold4)
 * https://www.acmicpc.net/problem/9935
 */
public class B9935 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        String target = br.readLine();

        int strLen = str.length();
        int targetLen = target.length();

        Stack<Character> st = new Stack<>();

        for (int i = 0; i < strLen; i++) {
            int count = 0;
            st.push(str.charAt(i));

            // Stack에 쌓은 문자열 길이와 폭탄 문자열 길이가 같아지면 탐색
            if (st.size() >= targetLen) {
                for (int j = 0; j < targetLen; j++) {
                    if (target.charAt(j) == st.get(st.size() - targetLen + j)) { // 같으면 count++
                        count++;
                    }
                    if (count == targetLen) { // 폭탄 문자열 제거
                        for (int k = 0; k < targetLen; k++) {
                            st.pop();
                        }
                    }
                }
            }
        }
        if (st.isEmpty()) {
            System.out.println("FRULA");
        } else {
            StringBuilder sb = new StringBuilder();
            for (char c : st) {
                sb.append(c);
            }
            System.out.println(sb.toString());
        }
    }

}

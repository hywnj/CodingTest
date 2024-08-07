package Java;

import java.io.*;
import java.util.*;

/**
 * 탑
 * https://www.acmicpc.net/problem/2493
 */
public class B2493 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        Deque<Building> stack = new ArrayDeque<>(); // 건물 정보를 담을 Stack
        int[] answers = new int[n];
        for (int i=0; i<n; i++) {
            int height = Integer.parseInt(st.nextToken()); // 현재 건물 높이

            while (!stack.isEmpty()) {
                Building building = stack.peek();
                // Stack의 최상단에 있는 높이 값이 현재 견물보다 높을때까지 stack.pop()
                if (building.height <= height) {
                    stack.pop();
                } else { // 현재 건물보다 높은 건물을 만나면 해당 건물 번호 정답으로 할당
                    answers[i] = building.num;
                    break;
                }
            }
            stack.push(new Building(i+1, height));
        }

        StringBuilder sb = new StringBuilder();
        for (int answer: answers) {
            sb.append(answer);
            sb.append(" ");
        }
        System.out.println(sb);
    }
    static class Building {
        int num;
        int height;
        public Building(int num, int height) {
            this.num = num;
            this.height = height;
        }
    }
}

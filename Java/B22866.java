package Java;

import java.io.*;
import java.util.*;

/**
 * 탑 보기
 * https://www.acmicpc.net/problem/22866
 */
public class B22866 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        // 건물 번호는 1부터 시작하므로, n+1로 사이즈 할당
        int[] heights = new int[n+1];
        int[] visibleCnt = new int[n+1];
        int[] nearest = new int[n+1];
        // 초기화
        st = new StringTokenizer(br.readLine(), " ");
        for (int i=1; i<=n; i++) {
            heights[i] = Integer.parseInt(st.nextToken());
            nearest[i] = -1;
        }

        Deque<Integer> stack = new ArrayDeque<>();
        // 왼쪽에서 볼 수 있는 건물 수와 가장 가까운 건물 번호
        for (int i=1; i<=n; i++) {
            // 현재 건물보다 작거나 같은 건물은 제외 = 스택의 최상위 요소보다 클 때까지 기존 요소를 제거
            // 최상위 값은 항상 작은 값이 들어감 (단조 감소 스택)
            while (!stack.isEmpty() && heights[stack.peek()] <= heights[i]) {
                stack.pop();
            }
            visibleCnt[i] = stack.size();

            if (!stack.isEmpty()) { // 보이는 건물이 1개 이상인 경우
                nearest[i] = stack.peek();
            }
            stack.push(i);
        }

        // 오른쪽에서 볼 수 있는 건물 수와 가장 가까운 건물 번호
        stack.clear();
        for (int i=n; i>0; i--) {
            while (!stack.isEmpty() && heights[stack.peek()] <= heights[i]) {
                stack.pop();
            }
            visibleCnt[i] += stack.size(); // 보이는 건물 개수 갱신

            // 보이는 건물이 할당이 되어있거나, 오른쪽에서 보이는 건물이 더 가까운 경우, 가까운 건물 갱신
            if (!stack.isEmpty() && (nearest[i] == -1 || stack.peek() - i < i - nearest[i])) {
                nearest[i] = stack.peek();
            }
            stack.push(i);
        }

        // 분기태워서 System.out.println 하는것보다 StringBuilder로 하는게 성능이 훨씬 향상됨
        StringBuilder sb = new StringBuilder();
        for (int i=1; i<=n; i++) {
            sb.append(visibleCnt[i]);
            if (visibleCnt[i] > 0) sb.append(" ").append(nearest[i]);
            sb.append("\n");
        }

        System.out.println(sb);
    }
}

package Java;

import java.io.*;

/**
 * 1, 2, 3 더하기 4
 * https://www.acmicpc.net/problem/15989
 */
public class B15989 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        int[] test = new int[t];
        for (int i = 0; i < t; i++) {
            test[i] = Integer.parseInt(br.readLine());
        }

        int[] dp = new int[10001]; // n 은 최대 10,000
        dp[1] = 1;
        for (int num : test) { // Test case 만큼 반복
            for (int i = 2; i <= num; i++) {
                if (dp[i] > 0) continue; // 이미 계산된 값은 넘어감
                int count = 0;
                int div = i / 2;
                for (int j = div; j >= 0; j--) { // 2로 나눈 몫만큼 반복하면서 2의 개수를 순차적으로 줄임
                    int diff = i - (j * 2);
                    // i가 2의 배수인 경우(diff == 0)
                    // 2를 i개 만큼 더한 후 남은 수를 3으로 나누어서, 나누어 떨어지면 3을 채워서 합으로 만들 수 있는 경우
                    if (diff == 0 || (diff >= 3 && diff % 3 == 0)) count++;
                }
                dp[i] = dp[i-1] + count; // dp[i] = dp[i-1] + (2,3으로만 이루어진 합)
            }
            System.out.println(dp[num]);
        }
    }
}

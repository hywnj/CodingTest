package Java;

import java.util.Scanner;

/**
 * 2차원 배열의 합
 * https://www.acmicpc.net/problem/2167
 */
public class B2167 {
    public static void main(String[] args) {
        /**
         * 포함배제원리를 이용한 DP 문제
         * dp(r,c) = dp(r-1,c) + dp(r,c-1) - dp(r-1,c-1) + arr(r,c)
         */
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        // 이전 값이 없는 경우 0으로 계산하기 위해 +1 크기로 할당 (주어지는 i,j,x,y도 실제 배열 인덱스 +1)
        int[][] dp = new int[n+1][m+1];
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < m + 1; j++) {
                // 입력받으면서 누적합 바로 할당
                dp[i][j] = dp[i-1][j] + dp[i][j-1] - dp[i-1][j-1] + sc.nextInt();
            }
        }

        int k = sc.nextInt();
        int i, j, x, y = 0; // 인덱스 +1 값
        int[] answers = new int[k];
        for (int z = 0; z < k; z++) {
            i = sc.nextInt();
            j = sc.nextInt();
            x = sc.nextInt();
            y = sc.nextInt();
            sc.nextLine();
            /**
             * dp(x,y) : (i,j)부터 (x,y)끼지의 합이므로,
             * - dp(i-1,y) : (i,j)부터의 합이므로 i이전 행의 y열까지의 합은 제외
             * - dp(x,j-1) : (i,j)부터의 합이므로 j이전 열의 x행까지의 합은 제외
             * + dp(i-1,j-1) : 위에서 중복 제외된 부분 더하기
             */
            answers[z] = dp[x][y] - dp[i-1][y] - dp[x][j-1] + dp[i-1][j-1];
        }
        for (int answer : answers) {
            System.out.println(answer);
        }
    }
}

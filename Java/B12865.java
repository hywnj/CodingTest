package Java;

import java.io.*;
import java.util.*;

/**
 * 평범한 배낭
 * https://www.acmicpc.net/problem/12865
 */
public class B12865 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        // 물품 무게, 가치 저장
        int[] W = new int[n+1];
        int[] V = new int[n+1];
        for (int i=1; i<=n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            W[i] = Integer.parseInt(st.nextToken());
            V[i] = Integer.parseInt(st.nextToken());
        }

        // DP로 최대 가치 계산
        int[][] dp = new int[n+1][k+1]; // 0번 행, 0번 열은 사용하지 않음
        for (int i=1; i<=n; i++) { // i: 물품
            for (int j=1; j<=k; j++) { // j: 무게
                // 각 물품의 무게가 현재 무게(j)보다 작거나 같을 경우에만 최댓값 비교
                if (W[i]<=j) dp[i][j] = Math.max(dp[i-1][j-W[i]] + V[i], dp[i-1][j]);
                else dp[i][j] = dp[i-1][j];
            }
        }
        System.out.println(dp[n][k]);
    }
}

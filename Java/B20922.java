package Java;

import java.io.*;
import java.util.StringTokenizer;

/**
 * 겹치는 건 싫어
 * https://www.acmicpc.net/problem/20922
 */
public class B20922 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine(), " ");
        int[] sequence = new int[n];
        for (int i=0; i<n; i++) {
            sequence[i] = Integer.parseInt(st.nextToken());
        }

        int[] cnt = new int[100001]; // 숫자는 최대 100_000
        int start = 0;
        int end = 0;
        int answer = 0;
        int max = 0;
        while (end < n) {
            if (cnt[sequence[end]] + 1 > k) { // 하나의 숫자가 연속 k개 초과
                if (max < answer) max = answer; // 최장 거리 갱신
                // while (sequence[start] != sequence[end]) { // 1개가 늘어서 연속 k개 초과했으므로, start가 end 숫자랑 같아질때까지 이동시키기
                while (cnt[sequence[end]] + 1 > k) { // 현재 숫자가 k개 이하가 될 때까지 start 이동
                    cnt[sequence[start]]--; // start 숫자 제외
                    answer--; // 수열 길이 -1
                    start++; // start pointer +1
                }
            } else { // 초과 하지 않는 경우, 1) 현재 숫자 카운팅 & 2) 숫자 연속 길이 증가
                cnt[sequence[end++]]++;
                answer++;
            }
        }
        if (max < answer) max = answer; // 마지막 갱신된 길이를 고려하여 최종 max 값 갱신
        System.out.println(max);
    }
}
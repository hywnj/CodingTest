package Java;

import java.io.*;
import java.util.*;

/**
 * 회전 초밥
 * https://www.acmicpc.net/problem/2531
 */
public class B2531 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        int[] sushi = new int[d+1]; // 초밥 모든 종류의 카운팅을 위한 배열
        int[] plates = new int[N]; // 벨트 위 접시에 있는 초밥
        for (int i=0; i<N; i++) {
            plates[i] = Integer.parseInt(br.readLine());
        }

        // 첫번째 윈도우 세팅
        // 첫번째 초밥부터 k번째 초밥까지 윈도우(범위)로 설정
        int cnt = 0; // 현재 윈도우의 종류 수
        for (int i=0; i<k; i++) {
            sushi[plates[i]]++; // 윈도우 내에 있는 초밥 카운트 up
            if (sushi[plates[i]] == 1) cnt++; // 새로운 초밥 종류인 경우에만 카운팅
        }

        int max = 0; // 최대 종류 수
        for (int i=0; i<N; i++) {
            if (sushi[c] == 0) max = Math.max(cnt+1, max); // 현 윈도우에 쿠폰 초밥이 없는 경우
            else max = Math.max(cnt, max);

            // 윈도우(범위) 이동
            // 첫번째 초밥을 제외할 때, 윈도우내에 중복된 초밥이 있었다면 종류 수를 빼면 안됨
            if (--sushi[plates[i]] == 0) cnt--;

            // 다음 초밥 가져올때, 중복된 초밥이라면 카운팅을 하면 안됨. 새로운 종류인 경우에만 카운팅
            if (sushi[plates[k++ % N]]++ == 0) cnt++;
        }
        System.out.println(max);
    }
}

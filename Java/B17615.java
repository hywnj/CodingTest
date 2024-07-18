package Java;

import java.io.*;

/**
 * 볼 모으기
 * https://www.acmicpc.net/problem/17615
 */
public class B17615 {
    public static void main(String[] args) throws IOException {
        /**
         * - N은 최대 500,000(50만)이므로, O(N^2)이면 안됨
         * - 빨간공(R), 파란공(B)을 각각 왼쪽, 오른쪽으로 모으는 경우에 대해 모두 확인하기 (총 4번)
         *      - 옮기려는 방향의 가장 끝 인덱스부터 있는, 현재 확인하려하는 공의 수를 해당 색상의 총 공의 수를 빼주면 횟수가 나옴
         */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        String ball = br.readLine();

        char[] balls = new char[N];
        int[] total = {0, 0}; // 빨간 공의 수, 파란 공의 수
        for(int i=0; i<N; i++) {
            char c = ball.charAt(i);
            balls[i] = c;
            // 색상별 공의 개수 카운팅
            if (c == 'R') total[0]++;
            else total[1]++;
        }
        // 한 종류의 공만 있는 경우
        if (total[0] == N || total[1] == N) {
            System.out.println(0);
            return;
        }

        int min = N+1;
        char[] color = {'R', 'B'};
        // 왼쪽 끝으로 모으는 경우
        for (int b = 0; b < 2; b++) {
            int idx = 0;
            int cnt = 0;
            while (balls[idx++] == color[b]) {
                cnt++;
            }
            int moved = total[b] - cnt;
            if (moved < min) min = moved;
        }
        // 오른쪽 끝으로 모으는 경우
        for (int b = 0; b < 2; b++) {
            int idx = N - 1;
            int cnt = 0;
            while(balls[idx--] == color[b]) {
                cnt++;
            }
            int moved = total[b] - cnt;
            if (moved < min) min = moved;
        }
        // 정답 출력
        System.out.println(min);
    }
}

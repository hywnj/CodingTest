package Java;

import java.io.*;
/**
 * 문자열 교환
 * https://www.acmicpc.net/problem/1522
 */
public class B1522 {
    public static void main(String[] args) throws IOException {
        /**
         * [알고리즘]
         *  - 문자열에 포함된 a의 길이만큼의 범위를 윈도우로 잡아서 'b'의 갯수 확인
         *  - 문자열은 최대 1,000 / 매번 탐색으로 하면 O(N^2) 소요
         *  - 슬라이딩 윈도우로해서, 범위에 따라 추가되고 삭제되는 문자를 확인해서 cnt를 -,+해서 교환해야하는 최소 횟수 구하기
         */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        int len = str.length();

        // a 개수 카운팅
        int range = 0; // a의 개수
        for (int s = 0; s < len; s++) {
            if (str.charAt(s) == 'a') range++;
        }
        // 첫번째 윈도우 세팅
        int cnt = 0; // 윈도우별 b의 개수
        for (int i = 0; i < range; i++) {
            if (str.charAt(i) == 'b') cnt++;
        }
        // 윈도우 확인
        int min = cnt;
        for (int i = 0; i < len * 2; i++) {
            // 최솟값 갱신
            if (min > cnt) min = cnt;

            // 범위 이동시, 빼는 요소가 b라면 cnt--, 추가되는 요소가 b라면 cnt++
            if (str.charAt(i % len) == 'b') cnt--;
            if (str.charAt(range++ % len) == 'b') cnt++;
        }
        System.out.println(min);
    }
}

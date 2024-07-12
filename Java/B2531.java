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

        int[] sushi = new int[N];
        for (int i=0; i<N; i++) {
            sushi[i] = Integer.parseInt(br.readLine());
        }
        List<Integer> list = new ArrayList<>();
        list.add(c); // 쿠폰 번호는 고정으로 추가
        for (int i=0; i<k; i++) {
            list.add(sushi[i]);
        }
        Set<Integer> set = new HashSet<>(list);
        int maxCnt = set.size(); // 최대 종류 수

        for (int i=k; i<N+k; i++) {
            list.remove(1);
            list.add(sushi[i % N]);

            set = new HashSet<>(list);
            if (maxCnt < set.size()) {
                maxCnt = set.size();
            }
        }
        System.out.println(maxCnt);
    }
}

package Java;

import java.io.*;
import java.util.*;

/**
 * 지름길
 * https://www.acmicpc.net/problem/1446
 */
public class B1446 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken());
        int D = Integer.parseInt(st.nextToken());

        Map<Integer, List<Shortcut>> shortcutInfo = new HashMap<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            // 고속도로 길이를 초과하면 저장 안함
            if (e > D) continue;
            // 끝 지점 - 시작 지점이 지름길 거리보다 짧으면 저장 안함 (즉, 지름길 거리가 짧은 경우에만 저장)
            if (e - s <= d) continue;

            // 지름길 정보 세팅
            Shortcut sc = new Shortcut(s, e, d);
            List<Shortcut> list = new ArrayList<>();
            // 이미 도착 지점 지름길 정보가 있으면, 저장된 리스트 정보에 추가해야됨
            if (shortcutInfo.containsKey(e)) list = shortcutInfo.get(e);

            list.add(sc);
            shortcutInfo.put(e, list);
        }

        int[] dp = new int[D + 1];
        dp[1] = 1;
        for (int i = 1; i <= D; i++) {
            int min = D;
            if (shortcutInfo.containsKey(i)) { // 지름길 루트가 있는 경우
                int val = 0;
                for (Shortcut s: shortcutInfo.get(i)) { // 도착 지점까지의 지름길이 여러개인 경우를 위한 반복문
                    val = dp[s.start] + s.distance; // 시작 지점까지의 최소 거리 + 해당 지름길 거리
                    if (val < min) min = val; // 도착 지점까지의 지름길 중 최솟값 갱신
                }
            }
            // 현재 지점까지의 최소 거리 = min(이전 지점까지의 최소 거리 + 1, 지름길 최소 거리(=지름길 시작 지점까지의 최소거리 + 지름길 거리)
            dp[i] = Math.min(dp[i-1] + 1, min); // 해당 지점까지의 최소 거리 계산
        }
        System.out.println(dp[D]);
    }

    public static class Shortcut {
        int start;
        int end;
        int distance;

        public Shortcut(int start, int end, int distance) {
            this.start = start;
            this.end = end;
            this.distance = distance;
        }
    }
}


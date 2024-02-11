package Java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * GBC
 * https://softeer.ai/practice/6270
 */
public class S6270 {
    /*
     * 0 ~ 100m를 1m마다 속도 저장해서 비교하여 차이 계산
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] limit = setArray(br, N); // 제한 속도
        int[] test = setArray(br, M); // 실제 운행 속도

        // 속도 비교
        int max = 0;
        for(int i=0; i<limit.length; i++) {
            int diff = limit[i] - test[i];
            if(diff < 0 && max > diff) max = diff;
        }

        System.out.println(Math.abs(max));
    }

    // 제한, 테스트 속도 배열 세팅
    public static int[] setArray(BufferedReader br, int n) throws IOException {
        int[] arr = new int[101];
        int before = 1;

        for(int i=0; i<n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int meter = Integer.parseInt(st.nextToken());
            int speed = Integer.parseInt(st.nextToken());

            for(int j=before; j< before+meter; j++) {
                arr[j] = speed;
            }
            before += meter;
        }

        return arr;
    }
}

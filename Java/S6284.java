package Java;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 바이러스
 * https://softeer.ai/practice/6284
 */
public class S6284 {
    public static void main(String[] args) throws IOException {
        /**
         * 1초당 P배씩 증가
         * K마리의 바이러스는 N초 후 총 몇 마리로 증가?
         *
         * 1번째 시도(실패): (K*P)*P의 N-1승
         *      1마리가 있었으면 증가율이 3인경우, 1초 지나면 3마리, 2초 지나면 3*3 = 9마리, 3초 지나면 9마리*3=27
         *      2마리면, 1초 지나면 2*3*1=6마리, 2초 지나면 (2*3)*3=18마리, 3초 지나면 (2*3*3)*3=54마리
         * 2번째 시도(성공)
         *      K,P,N의 범위가 1이상 10의 8승인 정수 이하이기 때문에 1번 방식으로 곱하면 int의 범위를 초과한다.
         *      따라서 계산 값을 할당할 변수를 long 으로 선언하거나, N초만큼 반복하면서 바로 나눠줘야한다.
         */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int K = Integer.parseInt(st.nextToken());
        int P = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        int answer = K;
        for(int i=0; i<N; i++) {
            answer *= P;
            answer %= 1000000007;
        }
        System.out.println((int)answer);
    }
}

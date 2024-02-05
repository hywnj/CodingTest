package Java;
import java.util.Scanner;

/**
 * 지도 자동 구축
 * https://softeer.ai/practice/6280
 */
public class S6280 {
    /*
     * 한 변에 존재하는 점의 개수의 제곱이 점의 개수가 됨
     * Start: 1변에 점 2개 => 총 4개의 점
     * 1 iteration: 1변에 점 3개 => 총 9개의 점
     * 2 iteration: 1변에 점 5개 => 총 25개의 점
     * 3 iteration: 1변에 점 9개 => 총 81개의 점
     * ====================================
     * 단계별 한 변에 존재하는 점의 개수의 증가 폭: 2의 n승
     * 0단계 -> 1단계 = 2의 0승 = 1
     * 1단계 -> 2단계 = 2의 1승 = 2
     * 2단계 -> 3단계 = 2의 2승 = 4
     * Bottom-up 방식
     * 기저 상태 저장: 0단계 = 2
     * 2단계부터는 이전 인덱스 값보다 2의 n승만큼 더한 값이 들어감
     * ====================================
     * 이전 값 * 2 - 1
     */
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        // double[] dp = new double[N+1];
        // dp[0] = 2; // 기저 상태 저장
        // for(int i=1; i<N+1; i++) {
        //     dp[i] = dp[i-1] + Math.pow(2,i-1);
        // }
        // System.out.println((int)Math.pow(dp[N],2));

        int answer = 2; // 0단계 한 변에 있는 점의 개수
        for(int i=0; i<N; i++) {
            answer *= 2;
            answer -= 1;
        }
        System.out.println(answer*answer);
    }
}

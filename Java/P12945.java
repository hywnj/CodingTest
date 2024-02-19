package Java;

/**
 * 피보나치 수
 * https://school.programmers.co.kr/learn/courses/30/lessons/12945
 */
public class P12945 {
    public static void main(String[] args) {
        System.out.println(solution(3)); // 2
        System.out.println(solution(5)); // 5
    }

    /**
     *  F(0) = 0, F(1) = 1일 때, 1 이상의 n에 대하여 F(n) = F(n-1) + F(n-2)
     */
    public static int solution(int n) {
        int[] fibonacci = new int[n+1];
        fibonacci[0] = 0;
        fibonacci[1] = 1;

        for (int i=2; i<=n; i++) { // 2이상의 n부터 입력되므로
            fibonacci[i] = (fibonacci[i-1] + fibonacci[i-2]) % 1234567; // 여기서 나누기 안하면 범위가 초과됨
        }

        return fibonacci[n];
    }
}

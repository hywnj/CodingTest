package Java;

/**
 * 소수 찾기 (Level 1)
 * https://school.programmers.co.kr/learn/courses/30/lessons/12921
 */
public class P12921 {
    public static void main(String[] args) {
        System.out.println(solution(10)); // 4 [2 3 5 7]
        System.out.println(solution(5)); // 3 [2 3 5]
    }
    public static int solution(int n) {
        if (n <= 2) return 1;

        // numbers: 합성수인 경우, true
        boolean[] numbers = new boolean[n+1];
        // n의 제곱근까지 확인
        for (int i = 2; i < n; i++) {
            if (numbers[i]) continue;
            for (int j = 2; j <= n; j++) {
                if (i*j > n) break;
                if (numbers[i*j]) continue; // 이미 합성수로 확인된 수는 넘김
                numbers[i*j] = true;
            }
        }

        int answer = -2; // 0,1은 제외
        for (boolean prime: numbers) {
            if (!prime) answer++;
        }
        return answer;
    }
}

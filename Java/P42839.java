package Java;

import java.util.*;

/**
 * 소수 찾기
 * https://school.programmers.co.kr/learn/courses/30/lessons/42839
 */
public class P42839 {
    public static void main(String[] args) {
        System.out.println(solution("178")); // 3
        System.out.println(solution("011")); // 2
    }

    /**
     * 완전탐색 + 백트래킹
     * 소수란, 1과 자기 자신 만을 약수로 가지는 수
     *  - 에라토스테네스의 체로 소수 여부 구하기
     */

    static Set<Integer> set;
    static boolean[] checked = new boolean[7]; // 조합한 수인지 (numbers는 최대 7자)
    public static int solution(String numbers) {
        set = new HashSet<>();
        // 문자열 numbers의 수 조합 찾기
        dfs(numbers, "", 0);

        int answer = 0;
        // 소수 판별
        for (int val: set) {
            if (isPrime(val)) answer++;
        }

        return answer;
    }

    public static void dfs(String numbers, String s, int depth) {
        if (depth > numbers.length()) return; // numbers 끝까지 순회하면 return

        for (int i=0; i<numbers.length(); i++) {
            if (!checked[i]) {
                checked[i] = true;
                set.add(Integer.parseInt(s + numbers.charAt(i)));
                dfs(numbers, s + numbers.charAt(i), depth+1);
                checked[i] = false; // 다음 조합을 위해 false로
            }
        }
    }

    public static boolean isPrime(int num) {
        if (num < 2) return false;
        for (int i=2; i <= (int)Math.sqrt(num); i++) { // num의 제곱근까지 확인
            if (num % i == 0) return false;
        }
        return true;
    }
}

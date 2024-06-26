package Java;

import java.util.Arrays;
/**
 * 인사고과
 * https://school.programmers.co.kr/learn/courses/30/lessons/152995
 */
public class P152995 {
    public static void main(String[] args) {
        System.out.println(solution(new int[][]{{2, 2}, {1, 4}, {3, 2}, {3, 2}, {2, 1}}));
    }
    public static int solution(int[][] scores) {
        // 완호 점수와 합계 저장
        int[] target = scores[0];
        int targetSum = target[0] + target[1];
        // 근무 태도 점수는 내림차순, 동료 평가 점수는 오름차순으로 정렬
        Arrays.sort(scores, (o1, o2) -> {
            return o1[0]!=o2[0] ? o2[0]-o1[0] : o1[1]-o2[1];
        });
        int answer = 1;
        int before = 0;
        for (int[] score : scores) {
            // 완호가 인센티브 대상자인지 확인
            if (target[0] < score[0] && target[1] < score[1]) return -1;
            // 현 사원의 동료 평가점수가 이전 사원의 점수보다 크거나 같은 경우만 인센티브 받는 대상
            if (before <= score[1]) {
                // 완호의 합계보다 큰 경우만 석차를 올린다
                if (targetSum < score[0] + score[1]) {
                    answer++;
                }
                before = score[1];
            }
        }
        return answer;
    }
}

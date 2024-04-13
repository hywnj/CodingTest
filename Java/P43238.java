package Java;

import java.util.Arrays;

/**
 * 입국심사
 * https://school.programmers.co.kr/learn/courses/30/lessons/43238
 */
public class P43238 {
    public static void main(String[] args) {
        System.out.println(solution(6, new int[]{7,10})); // 28
    }
    public static long solution(int n, int[] times) {
        Arrays.sort(times); // 심사 소요시간이 짧은 순으로 정렬
        long answer = 0; // 최소 심사 시간(최종 결과)

        /**
         * 심사 소요시간에 대한 이분탐색으로 계산
         * - 탐색 기준: 시간의 범위
         * - 기준이 되는 mid 값을 소요시간으로 두고, 그 시간에 몇명을 심사할 수 있는지 확인하여, 그게 n과 같다면 반환
         *      - 계산: mid=30, [7,10]이면, 30/7 + 30/10 = 4+3 = 7명
         *          ?? 28분, 29분은 모두 6명이 가능 -> 이건 어떻게 파악할것인가? 여기서 최소시간 28분이 나온다고 보장할 수 있는가?
         *          - while문의 기준을 (n == 계산한 값)으로 하면 안됨
         *          - 기준을 left <= right로 잡고해야, n명이 모두 심사를 받은 시간이라 해도 더 최솟값을 찾을 수 있다
         * - left, mid, right
         *  - mid 값보다 탐색 값이 작다면, right = mid - 1
         *  - mid 값보다 탐색 값이 크다면, left = mid + 1
         *  - mid = (left + right) / 2
         *
         *  - 계산한 심사한 사람 수가 n보다 크거나 같다면, right = mid - 1 & 반환할 최종 값에 mid 저장(: 더 최소값이 있을 수 있으므로 그 아래 값을 탐색)
         *  - 계산한 심사한 사람 수가 n보다 작다면, left = mid + 1 (=해당 시간안에 모든 사람을 심사할 수 없다는 뜻)
         */
        long left = 0;
        // 최대 소요시간 = 가장 긴 심사시간 * 심사 받아야하는 총 인원
        long right = (long)n * (long)times[times.length-1]; // int형 오버플로우 발생 가능성이 있으므로, 우항 변수를 long 타입으로 변환하여 계산
        // n명이 모두 심사가능한 시간이라해도, 그보다 더 최솟값이 있을 수 있으므로, while조건을 (n == complete)로 하지 않음
        while (left <= right) {
            long mid = (left + right) / 2;
            long complete = 0; // mid 분안에 심사를 할 수 있는 인원수
            // mid 분 안에 심사를 할 수 있는 인원수 계산
            for (int i=0; i < times.length; i++) {
                complete += mid / times[i]; // 각 심사 시간으로 나눠서 심사 가능 인원 계산
            }

            // 심사 가능 인원이 n보다 작다면, 해당 시간안에 모든 사람을 심사할 수 없다는 뜻이므로, mid보다 큰 값의 범위를 탐색
            if (complete < n) {
                left = mid + 1;
            } else { // 계산한 심사한 사람 수가 n보다 크거나 같다면
                right = mid - 1; // mid보다 작은 값의 범위를 탐색
                answer = mid; // 현재 값보다 최솟값이 있을 수 있으므로 우선 answer에 저장 후 진행
            }
        }
        return answer;
    }
}

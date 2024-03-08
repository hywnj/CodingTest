package Java;

/**
 * 연속 펄스 부분 수열의 합
 * https://school.programmers.co.kr/learn/courses/30/lessons/161988
 */
public class P161988 {
    public static void main(String[] args) {
        System.out.println(solution(new int[]{2, 3, -6, 1, 3, -1, 2, 4})); // 10
    }

    /**
     * 펄스 수열은 1, -1로 시작하는 2가지의 경우만 존재
     *  - 각각 plusSequence, minusSequence에 곱한 값 저장
     * 인덱스 위치별 최대값 갱신하면서 최대 값을 찾는, DP 문제
     *  - DP[K] = M : K번째 원소까지 탐색했을 때의 최댓값이 M
     *      - DP[K] = MAX(DP[K-1] + K번째 원소, K번째 원소)
     */
    public static long solution(int[] sequence) {
        int sequenceLen = sequence.length;
        long[] plusSequence = new long[sequenceLen];
        long[] minusSequence = new long[sequenceLen];
        int sign = 1;
        for (int i = 0; i < sequenceLen; i++) {
            plusSequence[i] = sequence[i] * sign;
            minusSequence[i] = sequence[i] * -sign;

            sign *= -1;
        }

        long max = Math.max(plusSequence[0], minusSequence[0]);

        long[] plusDp = new long[sequenceLen];
        long[] minusDp = new long[sequenceLen];
        plusDp[0] = plusSequence[0];
        minusDp[0] = minusSequence[0];
        for (int k = 1; k < sequenceLen; k++) {
            plusDp[k] = Math.max(plusDp[k-1] + plusSequence[k], plusSequence[k]);
            minusDp[k] = Math.max(minusDp[k-1] + minusSequence[k], minusSequence[k]);

            if (plusDp[k] > max) max = plusDp[k];
            if (minusDp[k] > max) max = minusDp[k];
        }

        return max;
    }
}

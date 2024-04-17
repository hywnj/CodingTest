package Java;

import java.util.Arrays;
/**
 * 표현 가능한 이진트리
 * https://school.programmers.co.kr/learn/courses/30/lessons/150367
 */
public class P150367 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution(new long[]{7, 42, 5})));
        System.out.println(Arrays.toString(solution(new long[]{63, 111, 95})));
    }

    private static int result;
    public static int[] solution(long[] numbers) {
        int[] answer = new int[numbers.length];
        String decimal;
        for (int i=0; i<numbers.length; i++) {
            decimal = Long.toBinaryString(numbers[i]); // 10진수 -> 2진수

            // 높이가 n인 포화 이진트리 전체 노드 수 = 2^n - 1
            int decimalLen = decimal.length();
            int depth = 1;
            int nodeCnt = 0;
            while (nodeCnt < decimalLen) {
                nodeCnt = (int) Math.pow(2, depth++) - 1;
            }

            boolean[] binary = new boolean[nodeCnt]; // 포화 이진트리 배열
            int realNodeIdx = nodeCnt - decimalLen; // 더미 노드가 아닌 노드 시작 인덱스
            for (int j=0; j<decimalLen; j++) {
                binary[realNodeIdx++] = (decimal.charAt(j) == '1'); // 1이면 binary에 true
            }

            result = 1;
            checkNode(binary, 0, nodeCnt-1 ); // 포화 이진트리 여부 확인
            answer[i] = result;
        }
        return answer;
    }

    public static void checkNode(boolean[] binary, int start, int end) {
        if (start == end) return; // 시작점과 끝점이 같으면 종료

        int root = (start + end) / 2;
        int leftRoot = (start + (root-1)) / 2;
        int rightRoot = ((root+1) + end) / 2;

        // Root가 0이면 자식 노드가 있을 수 없음
        if (!binary[root] && (binary[leftRoot] || binary[rightRoot])) {
            result = 0;
            return;
        }

        checkNode(binary, start, root-1); // 왼쪽 자식
        checkNode(binary, root+1, end); // 오른쪽 자식
    }
}

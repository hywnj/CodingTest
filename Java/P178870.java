package Java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 연속된 부분 수열의 합
 * https://school.programmers.co.kr/learn/courses/30/lessons/178870
 */
public class P178870 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution(new int[]{1, 2, 3, 4, 5}, 7))); // [2,3]
        System.out.println(Arrays.toString(solution(new int[]{1, 1, 1, 2, 3, 4, 5}, 5))); // [6,6]
        System.out.println(Arrays.toString(solution(new int[]{2, 2, 2, 2, 2}, 6))); // [0,2]
    }

    public static int[] solution(int[] sequence, int k) {
        int startPointer = 0;
        int endPointer = 0;
        List<SubSequence> answerList = new ArrayList<>(); // 정답 후보 저장할 리스트

        int sum = sequence[startPointer];
        int length = sequence.length;
        while (true) { // end pointer가 마지막 인덱스를 탐색할 때까지
            if (sum == k) answerList.add(new SubSequence(startPointer, endPointer, endPointer-startPointer+1));

            if (startPointer == length - 1 && endPointer == length - 1) break;

            if (sum < k && endPointer < length - 1) { // 누적 합이 k보다 작다면
                endPointer++; // end pointer 한칸 이동
                sum += sequence[endPointer]; // 누적 합 계산
            } else { // 누적 합이 k보다 크거나 end pointer가 마지막 인덱스라면
                sum -= sequence[startPointer]; // 움직인 start pointer에서 end pointer까지의 누적합은 움직이기 전 start pointer를 뺀 값
                startPointer++; // start pointer 이동
            }
        }
        // 정답 후보에서 1) 길이가 짧고 2) 인덱스가 작은 부분 수열 인덱스를 반환
        answerList.sort(SubSequence::compareTo);

        return answerList.get(0).getSequence();
    }

    public static class SubSequence implements Comparable<SubSequence> {
        int left, right, size;
        public SubSequence(int left, int right, int size) {
            this.left = left;
            this.right = right;
            this.size = size;
        }

        public int[] getSequence() {
            return new int[]{this.left, this.right};
        }

        @Override
        public int compareTo(SubSequence o) {
            if (this.size == o.size) return this.left - o.left;
            else return this.size - o.size;
        }
    }
}

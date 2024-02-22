package Java;

import java.util.*;

/**
 * 더 맵게
 * https://school.programmers.co.kr/learn/courses/30/lessons/42626
 */
public class P42626 {
    public static void main(String[] args) {
        System.out.println(solution(new int[]{10, 2, 3, 9, 1, 12}, 7)); // 2
    }

    /**
     * PriorityQueue 사용하여 Heap (Min-Heap) 으로 구현
     */
    public static int solution(int[] scoville, int K) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int val : scoville) {
            queue.add(val);
        }

        // 스코빌 지수 계산
        int first, second, mix;
        int count = 0; // 섞은 횟수
        // 루트 값이 K와 같거나 크면 종료
        while (queue.peek() < K) {
            if (queue.size() < 2) {
                return -1;
            } else {
                // 연산할 값 가져오기
                first = queue.poll();
                second = queue.poll();

                // 섞은 음식의 스코빌 지수를 삽입
                mix = first + (second * 2);
                queue.add(mix);
                count++;
            }
        }
        return count;
    }
}

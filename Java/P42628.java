package Java;

import java.util.*;

/**
 * 이중우선순위큐
 * https://school.programmers.co.kr/learn/courses/30/lessons/42628
 */
public class P42628 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution(new String[]{"I 16", "I -5643", "D -1", "D 1", "D 1", "I 123", "D -1"}))); // 	[0,0]
        System.out.println(Arrays.toString(solution(new String[]{"I -45", "I 653", "D 1", "I -642", "I 45", "I 97", "D 1", "D -1", "I 333"}))); // [333, -45]

    }
    public static int[] solution(String[] operations) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();

        StringTokenizer st;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (String op: operations) {
            st = new StringTokenizer(op, " ");
            char operation = st.nextToken().charAt(0);
            int num = Integer.parseInt(st.nextToken());

            if (operation == 'I') { // 큐에 삽입
                if (num < min) min = num;
                if (num > max) max = num;
                queue.offer(num);
            } else { // D
                // Queue에 요소가 있는 경우에만 삭제
                if (!queue.isEmpty()) {
                    if (num == 1) { // 최댓값 삭제
                        queue.remove(max);
                        max = Integer.MIN_VALUE;
                    } else { // 최솟값 삭제
                        //queue.remove(min);
                        queue.poll();
                        min = Integer.MAX_VALUE;
                    }
                }
            }
        }

        if (!queue.isEmpty()) {
            min = queue.peek();
            PriorityQueue<Integer> maxQueue = new PriorityQueue(Collections.reverseOrder());
            while (!queue.isEmpty()) {
                maxQueue.offer(queue.poll());
            }
            max = maxQueue.poll();

            return new int[]{max, min};
        }
        return new int[]{0,0};
    }
}

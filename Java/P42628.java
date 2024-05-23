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
        PriorityQueue<Integer> minQueue = new PriorityQueue<>();
        PriorityQueue<Integer> maxQueue = new PriorityQueue<>(Collections.reverseOrder());

        StringTokenizer st;
        int min = Integer.MAX_VALUE;
        for (String op: operations) {
            st = new StringTokenizer(op, " ");
            char operation = st.nextToken().charAt(0);
            int num = Integer.parseInt(st.nextToken());

            if (operation == 'I') { // 큐에 삽입
                if (num < min) { // 현재 최솟값보다 작다면 min 갱신 & minQueue에 저장
                    min = num;
                    minQueue.offer(num);
                } else { // 최솟값보다 작지 않은 경우 모두 maxQueue에 저장
                    maxQueue.offer(num);
                }
            } else { // D
                // Queue에 요소가 있는 경우에만 삭제
                if (num == 1) { // 최댓값 삭제
                    // maxQueue에 요소가 없다면 minQueue에 있는 값이 최댓값이므로 maxQueue로 옮긴 후 삭제
                    if (maxQueue.isEmpty()) while (!minQueue.isEmpty()) maxQueue.offer(minQueue.poll());
                    maxQueue.poll();
                } else { // 최솟값 삭제
                    // minQueue에 요소가 없다면 maxQueue에 있는 값이 최솟값이므로 minQueue로 옮긴 후 삭제
                    if (minQueue.isEmpty()) while (!maxQueue.isEmpty()) minQueue.offer(maxQueue.poll());
                    minQueue.poll();
                    // min 갱신
                    if (!minQueue.isEmpty()) min = minQueue.peek();
                }
                // 모든 Queue가 비어있다면 min 초기화
                if (minQueue.isEmpty() && maxQueue.isEmpty()) min = Integer.MAX_VALUE;
            }
        }

        if (minQueue.isEmpty() && maxQueue.isEmpty()) return new int[]{0,0};

        int max = 0;
        if (!minQueue.isEmpty()) {
            min = minQueue.peek();
            while (!minQueue.isEmpty()) {
                maxQueue.offer(minQueue.poll());
            }
            max = maxQueue.poll();
        } else {
            max = maxQueue.peek();
            while (!maxQueue.isEmpty()) {
                minQueue.offer(maxQueue.poll());
            }
            min = minQueue.poll();
        }

        return new int[]{max, min};
    }
}

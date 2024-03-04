package Java;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 두 큐 합 같게 만들기
 * https://school.programmers.co.kr/learn/courses/30/lessons/118667
 */
public class P118667 {
    public static void main(String[] args) {
        System.out.println(solution(new int[]{3, 2, 7, 2}, new int[]{4, 6, 5, 1})); // 2
        System.out.println(solution(new int[]{1, 2, 1, 2}, new int[]{1, 10, 1, 2})); // 7
        System.out.println(solution(new int[]{1, 1}, new int[]{1, 5})); // -1
    }

    public static int solution(int[] queue1, int[] queue2) {
        long q1Sum = 0;
        long q2Sum = 0;
        Queue<Integer> q1 = new LinkedList<>();
        Queue<Integer> q2 = new LinkedList<>();
        int qLength = queue1.length;
        for (int i=0; i<qLength; i++) {
            q1.offer(queue1[i]);
            q1Sum += (long)queue1[i];

            q2.offer(queue2[i]);
            q2Sum += (long)queue2[i];
        }

        int answer = -1;
        int cnt = 0;
        int el;
        while (cnt <= qLength * 4) {
            // 각 큐의 합이 같은 경우
            if (q1Sum == q2Sum) {
                answer = cnt;
                break;
            }

            if (q1Sum > q2Sum) { // q1의 총합이 더 크면
                el = q1.poll(); // q1에서 pop -> q2에 insert
                q2Sum += (long)el;
                q1Sum -= (long)el;
                q2.offer(el);
            } else { // q2의 총합이 더 크면
                el = q2.poll(); // q2에서 pop -> q1에 insert
                q1Sum += (long)el;
                q2Sum -= (long)el;
                q1.offer(el);
            }
            cnt++;
        }

        return answer;
    }
}

package Java;

import java.util.Arrays;

/**
 * 카펫
 * https://school.programmers.co.kr/learn/courses/30/lessons/42842
 */
public class P42842 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution(10,2))); // [4,3]
        System.out.println(Arrays.toString(solution(8,1))); // [3,3]
        System.out.println(Arrays.toString(solution(24,24))); // [8,6]
    }

    public static int[] solution(int brown, int yellow) {
        int[] answer = new int[2];
        int total = brown + yellow;

        // 가로 길이 <= 세로 길이 이므로, 가로 길이가 큰 것 부터 탐색
        for (int width=total; width>0; width--) {
            if (total % width != 0) continue; // 나누어 떨어지지 않는 경우 넘어가기

            int height = total / width;
            int y = (width-2) * (height-2);
            if (brown == total - y && yellow == y) {
                answer[0] = width;
                answer[1] = height;
                break;
            }
        }
        return answer;
    }
}

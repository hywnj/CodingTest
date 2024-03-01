package Java;

/**
 * 삼총사
 * https://school.programmers.co.kr/learn/courses/30/lessons/131705
 */
public class P131705 {
    public static void main(String[] args) {
        System.out.println(solution(new int[]{-2, 3, 0, 2, -5})); // 2
        System.out.println(solution(new int[]{-3, -2, -1, 0, 1, 2, 3})); // 5
        System.out.println(solution(new int[]{1, 1, -1, 1})); // 0
    }

    public static int solution(int[] number) {
        int numLength = number.length;
        int first, second, sum;
        int answer = 0;
        for (int i=0; i<numLength; i++) {
            first = number[i];
            for (int j=i+1; j<numLength-1; j++) { // i 인덱스 뒤 요소들을 탐색
                second = number[j];
                for (int k=j+1; k<numLength; k++) { // j 인덱스 뒤 요소들을 탐색
                    sum = first + second + number[k];
                    if (sum == 0) answer++;
                }
            }
        }
        return answer;
    }
}

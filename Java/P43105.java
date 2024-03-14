package Java;

/**
 * 정수 삼각형
 * https://school.programmers.co.kr/learn/courses/30/lessons/43105
 */
public class P43105 {
    public static void main(String[] args) {
        System.out.println(solution(new int[][]{{7}, {3, 8}, {8, 1, 0}, {2, 7, 4, 4}, {4, 5, 2, 6, 5}}));
    }

    /**
     * 최댓값은 정수 최댓값보다 한참 작음
     *  - 삼각형의 높이는 1 이상 500 이하
     *  - 삼각형을 이루고 있는 숫자는 0 이상 9,999 이하의 정수
     *   -최대 높이 500 * 최대 수 9,999 = 4,999,500 (약 500만)
     *
     * 삼각형에 구성되어있는 숫자를 노드라고 하면,
     * 루트 노드부터 시작해서 각 노드마다 최대 누적합을 저장하며 리프 노드까지 반복
     *  - 현 노드 번호(i)에 접근 가능한 이전 노드 = i-1, i
     *  - 양 끝에 있는 (i)에 접근 가능한 이전 노드
     *      - 맨 왼쪽 : i (즉, 0)
     *      - 맨 오른쪽 : i-1
     */
    public static int solution(int[][] triangle) {
        /**
         * 생각 1. dp[][] 생성해서 초기화시 문제
         *  - 행마다 크기가 다른데, 최대값으로 할당하기엔 메모리 낭비
         *      - 한 행에 사용되지 않는 부분이 최대 499개 (높이가 500일때 맨 첫번째 배열)
         *        첫번째 배열부터 499, 498, 497, ... , 1 의 합만큼 낭비
         *
         * 생각 2. 최종 누적합 중 최댓값을 찾는 것이므로 주어지는 triangle 배열에 바로 누적합 계산
         *  - 원본 값을 사용할 일이 없기 때문
         */
        for (int i = 1; i < triangle.length; i++) {
            for (int j = 0; j < triangle[i].length; j++) {
                // 양 끝 인덱스는 이전 배열의 양 끝에 있는 인덱스만 접근 가능
                if (j == 0) triangle[i][j] += triangle[i-1][j];
                else if (j == triangle[i].length - 1) triangle[i][j] += triangle[i-1][j-1];
                // 현재 인덱스의 최대값 = 현재 인덱스 값 + 현 인덱스에 접근 가능한 이전 배열 값 중 큰 값
                else triangle[i][j] += Math.max(triangle[i-1][j-1], triangle[i-1][j]);
            }
        }

        // 마지막 줄(배열)의 최대값 확인
        int max = 0;
        for (int val : triangle[triangle.length - 1]) {
            if (val > max) max = val;
        }

        return max;
    }
}

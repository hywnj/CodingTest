package Java;

import java.util.*;

/**
 * 귤 고르기
 * https://school.programmers.co.kr/learn/courses/30/lessons/138476
 */
public class P138476 {
    public static void main(String[] args) {
        System.out.println(solution(6, new int[]{1, 3, 2, 5, 4, 5, 2, 3})); // 3
        System.out.println(solution(4, new int[]{1, 3, 2, 5, 4, 5, 2, 3})); // 2
        System.out.println(solution(2, new int[]{1, 1, 1, 1, 2, 2, 2, 3})); // 1
    }

    /**
     * [문제 풀이]
     * - 크기별 개수가 가장 많은 순으로 k개를 채우기
     *      - tangerine 배열을 모두 순회하면서 각 크기별 개수 정보를 담은 Map 생성
     *          - key: 크기
     *          - value: 개수
     *      - tangerine 원소의 최대값은 10,000,000
     *          - 크기를 배열의 인덱스로 하면 안됨. 1, 10,000,000만 들어오는 경우 메모리 공간이 비효율적으로 사용됨
     *          => HashMap으로 구현
     */
    public static int solution(int k, int[] tangerine) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i : tangerine) {
            // map에 크기별로 개수 누적
            map.put(i, map.getOrDefault(i, 0) + 1);
        }

        // map value 기준 내림차순 정렬
        List<Integer> keys = new ArrayList<>(map.keySet());
        Collections.sort(keys, (o1, o2) -> (map.get(o2) -map.get(o1)));

        // keys list 순서대로 map에서 크기별 개수 가져와서 k개 채우기
        int answer = 0;
        for (Integer key : keys) {
            for (int i=0; i<map.get(key); i++) {
                k--;
            }
            answer++;
            if (k <= 0) break;
        }
        return answer;
    }
}

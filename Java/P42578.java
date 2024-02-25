package Java;

import java.util.*;

/**
 * 의상
 * https://school.programmers.co.kr/learn/courses/30/lessons/42578
 */
public class P42578 {
    public static void main(String[] args) {
        System.out.println(solution(new String[][]{{"yellow_hat", "headgear"}, {"blue_sunglasses", "eyewear"}, {"green_turban", "headgear"}})); // 5
        System.out.println(solution(new String[][]{{"crow_mask", "face"}, {"blue_sunglasses", "face"}, {"smoky_makeup", "face"}})); // 3
    }

    /**
     * 모든 경우의 수는
     * 1) 그 의상을 안입는 경우
     * 2) 그 의상 종류를 모두 각 한번씩 입는 경우
     *
     * 즉, headgear는 2가지 의상 종류가 있으므로 총 3가지 경우의 수가 나오고, eyewear는 1가지 의상 종류가 있으므로 총 2가지 경우의 수가 나옴
     * 총 경우의 수는 3*2=6인데, 여기서 둘 다 입지 않는 경우는 제외 해야하므로, 6-1 =5
     */
    public static int solution(String[][] clothes) {
        // 의상 종류별로 총 몇벌이 있는지 저장
        Map<String, Integer> map = new HashMap<>();
        for (String[] arr : clothes) {
            if (map.containsKey(arr[1])) map.put(arr[1], map.get(arr[1])+1); // 의상 종류가 이미 저장되어 있다면 Value에 추가 갱신
            else map.put(arr[1], 1);
        }

        // 모든 경우의 수 곱하기
        int answer = 1;
        for (Integer cnt : map.values()) {
            answer *= (cnt + 1);
        }

        return answer - 1;
    }
}

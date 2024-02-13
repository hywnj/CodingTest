package Java;

import java.util.*;

/**
 * 완주하지 못한 선수
 * https://school.programmers.co.kr/learn/courses/30/lessons/42576
 */
public class P42576 {
    public static void main(String[] args) {
        System.out.println(solution(new String[]{"leo", "kiki", "eden"}, new String[]{"eden", "kiki"}));
        System.out.println(solution(new String[]{"marina", "josipa", "nikola", "vinko", "filipa"}, new String[]{"josipa", "filipa", "marina", "nikola"}));
        System.out.println(solution(new String[]{"mislav", "stanko", "mislav", "ana"}, new String[]{"stanko", "ana", "mislav"}));
    }
    /**
     * - 경기에 참여한 선수(participant)를 key: name, value: 중복 이름 수로 HashMap에 저장
     * - 완주한 선수(completion)를 순회하면서 생성한 HashMap Value를 -1 연산하면서 갱신하여 저장
     * - HashMap 순회하면서 Value가 0이 아니면, 즉 완주하지 않은 인원이 남아있다면, 해당 Key값인 이름 반환
     */
    public static String solution(String[] participant, String[] completion) {
        HashMap<String, Integer> map = new HashMap<>();

        for (String name: participant) {
            map.put(name, map.getOrDefault(name, 0)+1);
        }

        for (String name: completion) {
            map.put(name, map.get(name)-1);
        }

        Iterator<Map.Entry<String, Integer>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Integer> entry = it.next();
            if (entry.getValue() != 0) return entry.getKey();
        }

        return "";
    }
}

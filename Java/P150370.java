package Java;

import java.util.*;

/**
 * 개인정보 수집 유효기간
 * https://school.programmers.co.kr/learn/courses/30/lessons/150370
 */
public class P150370 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution("2022.05.19",new String[]{"A 6", "B 12", "C 3"}, new String[]{"2021.05.02 A", "2021.07.01 B", "2022.02.19 C", "2022.02.20 C"}))); // [1, 3]
        System.out.println(Arrays.toString(solution("2020.01.01", new String[]{"Z 3", "D 5"}, new String[]{"2019.01.01 D", "2019.11.15 Z", "2019.08.02 D", "2019.07.01 D", "2018.12.28 Z"}))); // [1, 4, 5]
    }

    public static int[] solution(String today, String[] terms, String[] privacies) {
        // 오늘 일자 Y,M,D로 분리
        String[] todayInfo = today.split("\\.");
        int todayY = Integer.parseInt(todayInfo[0]);
        int todayM = Integer.parseInt(todayInfo[1]);
        int todayD = Integer.parseInt(todayInfo[2]);

        // 약관 종류
        Map<String, Integer> termsMap = new HashMap<>();
        for (String el : terms) {
            String[] temp = el.split(" ");
            termsMap.put(temp[0], Integer.parseInt(temp[1]));
        }

        // privacies 순회하면서 약관 유효기간 계산
        List<Integer> list = new ArrayList<>();
        int collectionY, collectionM, collectionD;
        for (int i=0; i<privacies.length; i++) {
            String[] privacyInfo = privacies[i].split(" ");
            String[] collectionDate = privacyInfo[0].split("\\.");
            collectionY = Integer.parseInt(collectionDate[0]);
            collectionM = Integer.parseInt(collectionDate[1]);
            collectionD = Integer.parseInt(collectionDate[2]);

            String typeOfTerms = privacyInfo[1];

            collectionM += termsMap.get(typeOfTerms);
            while (collectionM > 12) { // 계산한 달이 12월을 넘어가는 경우 연도에 1추가
                collectionM -= 12;
                collectionY += 1;
            }

            // 계산한 유효 일자랑 현재 일자 비교 (파기되지 않는 경우(=보관 가능한 경우)를 체크)
            if (collectionY > todayY) {
                continue;
            } else if (collectionY == todayY) {
                if (collectionM > todayM) {
                    continue;
                } else if (collectionM == todayM) {
                    if (collectionD > todayD) continue;
                }
            }
           list.add(i+1);
        }

        int[] answer = new int[list.size()];
        int idx = 0;
        for (int index : list) {
            answer[idx++] = index;
        }
        return answer;
    }
}

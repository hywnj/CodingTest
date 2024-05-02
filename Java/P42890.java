package Java;

import java.util.*;

/**
 * 후보키
 * https://school.programmers.co.kr/learn/courses/30/lessons/42890
 */
public class P42890 {
    public static void main(String[] args) {
        System.out.println(solution(new String[][] {{"100","ryan","music","2"},{"200","apeach","math","2"},{"300","tube","computer","3"},{"400","con","computer","4"},{"500","muzi","music","3"},{"600","apeach","music","2"}})); // 2
    }
    static int rowLen, colLen;
    static HashSet<String> candidateKey;
    static boolean[] visited;
    public static int solution(String[][] relation) {
        /**
         * 1. 모든 컬럼의 조합 구하기
         * 2. 조합별로 최소성, 유일성 확인
         */
        candidateKey = new HashSet<>(); // 중복 조합을 고려하여 HashSet 사용 | 탐색시에도 List보다 Set 속도가 더 빠름
        rowLen = relation.length;
        colLen = relation[0].length;
        visited = new boolean[colLen];

        // 1부터 m까지 사이즈만큼 조합 생성 & 조합별로 후보키 여부 확인
        for (int i = 1; i < colLen + 1; i++) {
            findCandidateKey(0, i, relation);
        }

        return candidateKey.size();
    }

    public static void findCandidateKey(int start, int r, String[][] relation) {
        if (r == 0) { // r개의 조합이 만들어진 경우
            Set<String> set = new HashSet<>(); // 중복 제거
            String str;
            String candidateStr = "";
            // 1. 유일성 체크
            for (int i=0; i<rowLen; i++) {
                str = "";
                candidateStr = "";
                // 선택된 컬럼의 각 행 데이터를 조합해서 중복 체크
                for (int c=0; c<colLen; c++) {
                    if (visited[c]) {
                        str += relation[i][c]; // 행 데이터 조합
                        candidateStr += c; // 후보키 컬럼
                    }
                }
                set.add(str);
            }
            // 중복된 값이 있다면 유일성 만족 못함
            if (set.size() != rowLen) return;

            // 2. 최소성 체크
            //      - 04가 후보키에 저장되어있다면, 014는 최소성을 만족하지 못함
            //          1) 014 길이만큼 순회하면서 0, 1, 4가 04에 포함되어있는지 확인
            //          2) 포함 여부가 비교하는 후보키인 04와 길이가 같다면, 최소성 만족 못함
            //              - 0은 04에 포함(+1) | 1은 04에 없음 | 4는 04에 포함(+1) = 2 = 04의 길이
            for (String key: candidateKey) {
                int matchingCnt = 0;
                if (candidateStr.length() > 1) { // 길이가 1이상인 다중 키 중에서
                    for (int s=0; s<candidateStr.length(); s++) {
                        // 유일성을 만족한 후보키 중에 현재 최소성을 확인하는 후보키가 가진 컬럼과 중복된 컬럼이 있는지 확인
                        if (key.contains(String.valueOf(candidateStr.charAt(s)))) matchingCnt++;
                    }
                    if (matchingCnt == key.length()) return; // 비교하는 후보키에 모든 컬럼이 중복되는 경우엔 최소성 만족 못함
                }
            }
            candidateKey.add(candidateStr); // 유일성과 최소성을 모두 만족하면, 후보키로 추가
            return;
        }
        // 컬럼 조합 생성 (백트래킹)
        for (int c=start; c<colLen; c++) {
            visited[c] = true;
            findCandidateKey(c+1, r - 1, relation);
            visited[c] = false;
        }
    }

}

package Java;

import java.util.Arrays;

/**
 * 서울에서 김서방 찾기
 * https://school.programmers.co.kr/learn/courses/30/lessons/12919
 */
public class P12919 {
    public static void main(String[] args) {
        System.out.println(solution(new String[]{"Jane", "Kim"})); // 김서방은 1에 있다
    }
    public static String solution(String[] seoul) {
        // 자바 배열은 indexOf()를 지원하지 않고, ArrayList 자료구조에서만 지원하므로 asList()를 통해 리스트로 변환시켜 인덱스 구하기
        int idx = Arrays.asList(seoul).indexOf("Kim");
        return "김서방은 "+ idx +"에 있다";
    }
}

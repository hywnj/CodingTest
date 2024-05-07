package Java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 전설
 * https://www.acmicpc.net/problem/19585
 */
public class B19585 {

    /**
     * 1차 시도: 모든 색상+닉네임 조합 저장해서 포함여부 확인 => 메모리 초과
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int c = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

        List<String> colorList = new ArrayList<>();
        List<String> nickNameList = new ArrayList<>();
        for (int i=0; i<c; i++) colorList.add(br.readLine());
        for (int i=0; i<n; i++) nickNameList.add(br.readLine());
        // 색상과 닉네임 리스트 조합 모두 저장
        Set<String> combination = new HashSet<>();
        for (String color: colorList) {
            for (String name: nickNameList) {
                combination.add(color+name);
            }
        }
        // 팀명이 생성된 조합에 있는지 확인
        int q = Integer.parseInt(br.readLine());
        boolean[] answer = new boolean[q];
        for (int i=0; i<q; i++) {
            String teamName = br.readLine();
            if (combination.contains(teamName)) answer[i] = true;
            else answer[i] = false;
        }

        for (boolean flag: answer) {
            if (flag) System.out.println("Yes");
            else System.out.println("No");
        }
    }
}

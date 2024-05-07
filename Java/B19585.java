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
     * 2차 시도: team명을 저장해두고 색상 배열, 닉네임 배열을 조합해가면서 team명이 존재하는지 확인 => 시간 초과
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

        // 팀명 저장한 후 색삭+닉네임 조합을 만들면서 저장된 값이 있는지 확인하여 판단
        int q = Integer.parseInt(br.readLine());
        String[] team = new String[q]; // 팀명을 순서대로 확인하기 위한 배열
        Map<String, Boolean> teamMap = new HashMap<>(); // 팀명의 조건 부합 여부를 저장하기 위한 map
        String teamName;
        for (int i=0; i<q; i++) {
            teamName = br.readLine();
            team[i] = teamName;
            teamMap.put(teamName, false);
        }
        String combination;
        for (String color: colorList) {
            for (String name: nickNameList) {
                combination = color+name;
                // 확인하는 단계를 조합을 만들때마다 하기 때문에 시간이 초과하는 것으로 예상
                if (teamMap.containsKey(combination)) teamMap.put(combination, true);
            }
        }
        for (String t: team) {
            if (teamMap.get(t)) System.out.println("Yes");
            else System.out.println("No");
        }
    }
}

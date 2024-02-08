package Java;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 붕대 감기
 * https://school.programmers.co.kr/learn/courses/30/lessons/250137
 */

class Attacks {
    public int time;
    public int damage;

    public Attacks(int time, int damage) {
        this.time = time;
        this.damage = damage;
    }
}
public class P250137 {

    public static void main(String[] args) {
        System.out.println(solution(new int[]{5, 1, 5},30, new int[][]{{2, 10}, {9, 15}, {10, 5}, {11, 5}}));
        System.out.println(solution(new int[]{3, 2, 7},20, new int[][]{{1, 15}, {5, 16}, {8, 6}}));
        System.out.println(solution(new int[]{4, 2, 7},20, new int[][]{{1, 15}, {5, 16}, {8, 6}}));
        System.out.println(solution(new int[]{1, 1, 1},5, new int[][]{{1, 2}, {3, 2}}));
    }

    public static int solution(int[] bandage, int health, int[][] attacks) {
        /**
         * - 몬스터의 마지막 공격시간까지 체력 계산
         *      if 체력 <= 0 then return -1
         *      if 체력 >= 최대 체력 then 체력 = 최대 체력
         * - attacks을 Queue에 넣고 총 시간동안 계산
         *
         * - 몬스터의 공격이 없으면, 초당 회복량이 체력에 추가
         *      if 연속 성공 == 시전시간 then 1) 추가 회복량 + 초당 회복량 2) 연속 성공 0으로 초기화
         * - 몬스터의 공격이 있으면, 1) 피해량만큼 체력 감소 2) 연속 성공 0으로 초기화
         * - 모든 공격이 끝난 후 남은 체력 반환 (몬스터의 마지막 공격시간)
         */
        Queue<Attacks> q = new LinkedList<>();
        for (int i=0; i<attacks.length; i++) {
            q.add(new Attacks(attacks[i][0], attacks[i][1]));
        }

        int t = bandage[0]; // 시전 시간
        int x = bandage[1]; // 초당 회복 체력
        int y = bandage[2]; // 추가 회복 체력

        int endTime = attacks[attacks.length-1][0]; // 마지막 공격 시간
        int success = 0; // 연속 성공
        int nowHealth = health; // 현재 체력
        Attacks attack = q.poll(); // 첫번째 몬스터 공격 정보

        for (int i=1; i<=endTime; i++) {
            if (i == attack.time) { // 몬스터 공격
                nowHealth -= attack.damage;
                success = 0;
                // 다음 공격 정보 할당
                attack = q.poll();
            } else {
                success++;

                int add = x;
                if (t == success) { // 연속 성공시
                    add += y;
                    success = 0;
                }
                nowHealth += add;
            }
            if (nowHealth <= 0) return -1;
            if (nowHealth >= health) nowHealth = health; // 최대 체력까지만 가능
        }

        return nowHealth;
    }
}

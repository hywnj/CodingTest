package Java;

import java.io.*;
import java.util.*;

/**
 * 컨베이어 벨트 위의 로봇
 * https://www.acmicpc.net/problem/20055
 */
public class B20055 {
    static int k;
    static List<Space> spaces = new ArrayList<>(); // 해당 칸의 정보
    public static void main(String[] args) throws IOException {
        // 입력 받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine(), " ");

        int[] belt = new int[2 * n + 1]; // 벨트에 올라간 칸 인덱스를 담을 배열
        spaces.add(null); // 0번 인덱스 사용 안함
        for (int i = 0; i < 2 * n; i++) {
            spaces.add(new Space((i+1), Integer.parseInt(st.nextToken()))); // 내구성 저장
            belt[i+1] = i+1;
        }

        // 로봇 이동
        int step = 1; // 수행 단계
        while (true) {
            // 1. 벨트 회전
            for (int i = 1; i <= 2 * n; i++) {
                int moved = belt[i] + (2 * n - 1);
                // 계산한 값이 n이 아니면 n보다 큰 값을 가지므로 % 연산으로 위치 바꿔주기
                belt[i] = (moved == 2 * n) ? moved : moved % (2 * n);
            }
            // 내리는 위치(n)에 로봇이 있으면 즉시 내리기
            spaces.get(belt[n]).robot = false;
            // moveRobot(belt[n], belt[n + 1]);

            // 2. 로봇 이동
            for (int i = 2 * n; i > 1; i--) {
                moveRobot(belt[i - 1], belt[i]); // 현재 벨트에 있는 칸 정보를 가져와야됨

                // 내리는 위치(n)에 로봇이 있으면 즉시 내리기
                if (i == n) spaces.get(belt[i]).robot = false;
            }
            // 2. 올리는 위치(1)에 로봇 올리기
            // 1번 칸에는 계속 로봇을 올림 && 1번 칸은 내구도가 0이 아니면 올림
            Space start = spaces.get(belt[1]);
            if (start.durability > 0 && !start.robot) {
                start.robot = true;
                start.durability--;
            }
            // 3. 내구성 체크 (내구성 0이 K개 이상이면 끝냄)
            if (getFinishFlag()) break;

            step++; // 수행단계 +1
        }
        System.out.println(step);
    }

    public static boolean moveRobot(int nowIdx, int nextIdx) {
        // 현재 칸에 로봇이 있고 && 다음 칸에는 로봇이 없고 && 다음 칸의 내구성이 1 이상일때, 이동 가능
        Space now = spaces.get(nowIdx);
        Space next = spaces.get(nextIdx);
        if (now.robot && !next.robot && next.durability >= 1) {
            // 로봇 이동
            now.robot = false;
            next.robot = true;
            // 내구성 1 감소
            next.durability--;
            return true;
        }
        return false;
    }

    public static boolean getFinishFlag() {
        int cnt = 0;
        for (int i = 1; i < spaces.size(); i++) {
            if (spaces.get(i).durability == 0) cnt++;
        }
        return (cnt >= k) ? true : false;
    }

    public static class Space {
        int idx;
        int durability;
        boolean robot = false;
        public Space(int idx, int durability) {
            this.idx = idx;
            this.durability = durability;
        }
        public Space(int durability) {
            this.durability = durability;
        }
    }
}

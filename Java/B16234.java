package Java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 인구 이동
 * https://www.acmicpc.net/problem/16234
 */
public class B16234 {
    static int[][] earth;
    static int n, l, r;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        // 인구수 입력
        earth = new int[n][n];
        for (int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j=0; j<n; j++) {
                earth[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 인구 이동
        int day = 0;
        while (true) {
            if (!movePeople()) break; // 인구 이동이 실패한 경우 반복문 종료
            day++; // 인구 이동 성공한 경우 일 수 증가
        }
        System.out.println(day);
    }

    public static boolean movePeople() {
        int[] dx = {0,1,0,-1};
        int[] dy = {1,0,-1,0};
        boolean[][] visited = new boolean[n][n];
        Queue<Pointer> q = new LinkedList<>();
        boolean availableFlag = false;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j]) { // 방문하지 않은 땅인 경우
                    q.offer(new Pointer(i, j)); // BFS Queue에 삽입
                    visited[i][j] = true;

                    // BFS Queue에 저장되어 아래 while문을 타면 하나의 연합국으로 계산
                    List<Pointer> pointers = new ArrayList<>(); // 하나의 연합국 정보를 담을 Pointer 리스트
                    int sum = earth[i][j]; // 연합의 인구수
                    int cnt = 1; // 연합을 이루고 있는 칸의 개수

                    while (!q.isEmpty()) {
                        Pointer nowPointer = q.poll();
                        pointers.add(nowPointer);

                        for (int k = 0; k < 4; k++) { // 4방향 탐색
                            int nx = nowPointer.x + dx[k];
                            int ny = nowPointer.y + dy[k];

                            if (nx >= 0 && nx < n && ny >= 0 && ny < n) { // 범위 체크
                                if (!visited[nx][ny]) { // 방문하지 않은 인접 땅인 경우
                                    int diff = Math.abs(earth[nowPointer.x][nowPointer.y] - earth[nx][ny]); // 현재 땅과 인접땅의 인구 차이 계산
                                    if (diff >= l && diff <= r) { // L명 이상 R명 이하인 경우 국경선 열림
                                        visited[nx][ny] = true; // 인접 땅 방문 표시

                                        // 연합국 처리
                                        sum += earth[nx][ny]; // 연합국 인구 수 누적
                                        cnt++; // 연합국 개수 증가

                                        q.add(new Pointer(nx, ny));
                                        pointers.add(new Pointer(nx, ny)); // 현재 연합국에 추가
                                    }
                                }
                            }
                        }
                    }
                    // 인구 이동을 할 수 없는 경우, 다음 땅에서 인접국 확인
                    if (pointers.size() == 1) continue;

                    // 현 연합국 인구 이동
                    int people = sum / cnt; // 연합에 분배될 인구 수
                    for (Pointer p : pointers) {
                        earth[p.x][p.y] = people;
                    }
                    availableFlag = true; // 인구 이동이 한번이라도 있다면 true 반환
                }
            }
        }
        return availableFlag;
    }

    public static class Pointer{
        int x;
        int y;
        public Pointer(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
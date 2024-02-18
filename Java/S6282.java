package Java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 장애물 인식 프로그램
 * https://softeer.ai/practice/6282
 */
public class S6282 {

    static int N;
    static int[][] map;
    static boolean[][] checked;
    static int sum; // dfs

    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};

    static List<Integer> list = new ArrayList<>(); // 블록 내 장애물 수 저장

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        map = new int[N][N];
        checked = new boolean[N][N];
        for(int i=0; i<N; i++) {
            String temp[] = br.readLine().split("");
            for(int j=0; j<N; j++) {
                map[i][j] = Integer.parseInt(temp[j]);
                checked[i][j] = false;
            }
        }

        // BFS
        Queue<Pointer> q = new LinkedList<>();
        int blockCnt = 0;
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                if(!checked[i][j] && map[i][j] == 1) {
                    int sum = 1;
                    blockCnt++;
                    checked[i][j] = true;
                    q.offer(new Pointer(i,j));

                    while(!q.isEmpty()) {
                        Pointer pointer = q.poll();

                        for(int k=0; k<4; k++) {
                            int nx = pointer.x + dx[k];
                            int ny = pointer.y + dy[k];
                            if(nx >= 0 && nx < N && ny >= 0 && ny < N) {
                                if(!checked[nx][ny] && map[nx][ny] == 1) {
                                    sum++;
                                    checked[nx][ny] = true;
                                    q.offer(new Pointer(nx, ny));
                                }
                            }
                        }
                    }
                    list.add(sum);
                }
            }
        }

        // DFS
        /** for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                if(!checked[i][j] && map[i][j] == 1) {
                    blockCnt++; // 블럭수 증가
                    checked[i][j] = true; // 방문 여부 true
                    sum = 1;
                    dfs(i, j);
                    list.add(sum);
                }
            }
        }
        */

        list.sort((o1, o2)->o1-o2);
        System.out.println(blockCnt);
        for(int val: list) {
            System.out.println(val);
        }
    }

    public static void dfs(int x, int y) {
        for(int i=0; i<4; i++) { // 상하좌우
            // 다음 방문 좌표
            int nx = x + dx[i];
            int ny = y + dy[i];

            if((nx >= 0 && nx < N) && (ny >= 0 && ny < N)) { // 범위 확인
                if(!checked[nx][ny]) { // 방문 여부 확인
                    if(map[nx][ny] == 1) { // 장애물인 경우
                        sum++; // 장애물 수 증가
                        checked[nx][ny] = true; // 방문 여부 true
                        dfs(nx, ny);
                    }
                }
            }
        }
    }

    static class Pointer {
        public int x;
        public int y;
        public Pointer(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

}
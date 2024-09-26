package Java;

/**
 * 시뮬레이션 & 구현 : 잃어버린 강아지
 */
public class I0003 {
    public int solution(int[][] board){
        int len = board.length; // 지도 크기는 10*10 고정

        // 현수, 강아지 정보 저장
        int x1 = 0, x2 = 0, y1 = 0, y2 = 0;
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                if (board[i][j] == 2) {
                    x1 = i;
                    y1 = j;
                } else if (board[i][j] == 3) {
                    x2 = i;
                    y2 = j;
                }
            }
        }

        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};

        int d1 = 0, d2 = 0, time = 0;
        while (time < 10000) {
            time++;
            int nx1 = x1 + dx[d1]; // 현수
            int ny1 = y1 + dy[d1];
            int nx2 = x2 + dx[d2]; // 강아지
            int ny2 = y2 + dy[d2];

            boolean flag1 = true, flag2 = true;
            // 지도 끝이거나 나무를 마주치면 회전
            if (nx1 < 0 || nx1 >= len || ny1 < 0 || ny1 >= len || board[nx1][ny1] == 1) {
                d1 = (d1 + 1) % 4;
                flag1 = false;
            }
            if (nx2 < 0 || nx2 >= len || ny2 < 0 || ny2 >= len || board[nx2][ny2] == 1) {
                d2 = (d2 + 1) % 4;
                flag2 = false;
            }

            // 한칸 이동
            if (flag1) {
                x1 = nx1;
                y1 = ny1;
            }
            if (flag2) {
                x2 = nx2;
                y2 = ny2;
            }

            if (x1 == x2 && y1 == y2) break; // 강아지를 만나면 완료
        }
        return time;
    }

    public static void main(String[] args){
        I0003 T = new I0003();
        int[][] arr1 = {{0, 0, 0, 0, 0, 0, 1, 0, 0, 0},
                {0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 1, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 1, 0, 0, 0, 2, 0, 0},
                {1, 0, 0, 0, 0, 0, 1, 0, 0, 0},
                {0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 3, 0, 0, 0, 1},
                {0, 0, 0, 1, 0, 1, 0, 0, 0, 0},
                {0, 1, 0, 1, 0, 0, 0, 0, 0, 0}};
        System.out.println(T.solution(arr1));
        int[][] arr2 = {{1, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 1, 0, 0, 0},
                {0, 0, 1, 1, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 1, 0, 1, 0, 0, 0, 0},
                {1, 0, 0, 0, 0, 0, 1, 0, 1, 0},
                {0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
                {0, 0, 1, 0, 0, 0, 0, 0, 2, 1},
                {0, 0, 0, 1, 0, 1, 0, 0, 0, 1},
                {0, 1, 0, 1, 0, 0, 0, 0, 0, 3}};
        System.out.println(T.solution(arr2));
    }
}

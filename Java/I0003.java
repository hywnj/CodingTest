package Java;

/**
 * 시뮬레이션 & 구현 : 잃어버린 강아지
 */
public class I0003 {
    static int len = 10; // 지도 크기는 10*10 고정
    static int answer;
    public int solution(int[][] board){
        answer = 0; // 총 걸린 시간 초기화
        // 현수, 강아지 정보 저장
        Point hyun = null, dog = null;
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                if (board[i][j] == 2) {
                    hyun = new Point(i, j);
                } else if (board[i][j] == 3) {
                    dog = new Point(i, j);
                }

                if (hyun != null && dog != null) break;
            }
        }

        // 강아지 찾기
        while (!findDog(hyun, dog)) {
            moveOrRotate(hyun, board); // 현수 이동|회전
            answer++;
            if (findDog(hyun, dog)) break; // 강아지를 찾았으면 종료

            moveOrRotate(dog, board); // 강아지 이동|회전
            if (findDog(hyun, dog)) break; // 강아지를 찾았으면 종료

            if (answer >= 10000) { // 10,000분 후에도 찾을 수 없으면 0 반환
                answer = 0;
                break;
            }
        }

        return answer;
    }

    public void moveOrRotate(Point point, int[][] board) {
        int[] dx = {-1, 0, 1, 0}; // 순서: 위 > 오른쪽 > 아래 > 왼쪽
        int[] dy = {0, 1, 0, -1};

        int nx = point.x + dx[point.direction];
        int ny = point.y + dy[point.direction];

        if (nx >= 0 && nx < len && ny >= 0 && ny < len) {
            if (board[nx][ny] == 1) point.changeDirection(); // 나무 마주쳤을때 회전
            else point.changePoint(nx, ny); // 이동
        } else { // 범위를 벗어나는 경우, 회전
            point.changeDirection(); // 지도의 끝일 때 회전
        }
    }

    public boolean findDog(Point hyun, Point dog) {
        if (hyun.x == dog.x && hyun.y == dog.y) return true;
        else return false;
    }

    public class Point{
        int x;
        int y;
        int direction = 0; // 먼저 북쪽으로 출발
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
        public void changePoint(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public void changeDirection() {
            this.direction = (this.direction + 1) % 4;
        }
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

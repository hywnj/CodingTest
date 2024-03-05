package Java;

import java.util.*;

/**
 * [1차] 프렌즈4블록
 * https://school.programmers.co.kr/learn/courses/30/lessons/17679
 */
public class P17679 {
    public static void main(String[] args) {
        System.out.println(solution(4,4, new String[]{"ASWD", "DERG", "DSWE", "FRJK"})); // 14
        System.out.println(solution(4,5, new String[]{"CCBDE", "AAADE", "AAABF", "CCBBF"})); // 14
        System.out.println(solution(6,6, new String[]{"TTTANT", "RRFACC", "RRRFCC", "TRRRAA", "TTMMMF", "TMMTTJ"})); // 15
    }

    static int[] dx = {0, 1, 1};
    static int[] dy = {1, 0, 1};
    static List<Point> pointList = new ArrayList<>(); // 2*2 블록이 완성된 인덱스를 저장할 리스트
    static char[][] boards;
    public static int solution(int m, int n, String[] board) {
        boards = new char[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                boards[i][j] = board[i].charAt(j);
            }
        }

        List<Point> tempList;
        int answer = 0;
        while (true) {
            /**
             * board 순회하면서 2*2 블록 찾기
             */
            for (int i = 0; i < m-1; i++) {
                for (int j = 0; j < n-1; j++) {
                    if (boards[i][j] == ' ') continue;

                    tempList = findBlocks(i, j, boards, m, n);
                    if (!tempList.isEmpty()) {
                        pointList.add(new Point(i,j)); // 기준 값
                        pointList.addAll(tempList); // 이외 값
                    };
                }
            }
            // 찾은 블록이 없다면, 끝
            if (pointList.isEmpty()) break;
            /**
             * 비어있는 곳에 블록 내리기
             */
            for (int j = 0; j < n; j++) {
                for (int i = m-1; i >= 0; i--) {
                    // 삭제된 블록이라면, answer에 1 추가 & 해당 열에서 삭제되지 않은 애를 찾아서 해당 인덱스에 넣음
                    if (pointList.contains(new Point(i,j))) {
                        answer++;
                        boards[i][j] = getDownBlock(i, j, boards);
                    }
                }
            }
            pointList.clear();
        }

        return answer;
    }

    /**
     * 비어있는 곳에 블록 내릴때, 해당 위치에 넣을 값 찾기
     */
    public static char getDownBlock(int row, int col, char[][] boards) {
        char result = ' ';
        for (int i = row-1; i >= 0; i--) {
            if (!pointList.contains(new Point(i, col)) && boards[i][col] != ' ') {
                result = boards[i][col];
                boards[i][col] = ' ';
            }
        }

        return result;
    }

    /**
     * 2*2 블록 찾기
     */
    public static List<Point> findBlocks(int x, int y, char[][] boards, int m, int n) {
        char targetVal = boards[x][y];

        /**
         * (i,j) 기준으로 (i,j+1), (i+1,j), (i+1,j+1)이 같으면 제거될 블럭
         */
        List<Point> collectList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            // 같은 값이 아니면 실패
            if (targetVal != boards[nx][ny]) {
                collectList.clear();
                break;
            }

            collectList.add(new Point(nx, ny));
        }

        return collectList;
    }

    public static class Point {
        int x, y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point other = (Point) o;
            return other.x == x && other.y == y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x,y);
        }
    }
}

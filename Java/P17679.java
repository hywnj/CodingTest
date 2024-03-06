package Java;

import java.util.*;

/**
 * [1차] 프렌즈4블록
 * https://school.programmers.co.kr/learn/courses/30/lessons/17679
 */
public class P17679 {
    public static void main(String[] args) {
        System.out.println(solution(4,5, new String[]{"CCBDE", "AAADE", "AAABF", "CCBBF"})); // 14
        System.out.println(solution(6,6, new String[]{"TTTANT", "RRFACC", "RRRFCC", "TRRRAA", "TTMMMF", "TMMTTJ"})); // 15
    }

    static char[][] boards;
    static boolean[][] checked; // Block 삭제 여부
    public static int solution(int m, int n, String[] board) {
        boards = new char[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                boards[i][j] = board[i].charAt(j);
            }
        }

        int answer = 0;
        int cnt = 0;
        while (true) {
            checked = new boolean[m][n]; // 매번 라운드를 돌 때마다 초기화
            /**
             * board 순회하면서 2*2 블록 찾기
             */
            for (int i = 0; i < m-1; i++) {
                for (int j = 0; j < n-1; j++) {
                    if (boards[i][j] == '-') continue;
                    findBlocks(i, j);
                }
            }
            /**
             * 블록 카운팅
             */
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (checked[i][j] && boards[i][j] != '-') { // 현 시점에서 삭제한 블록만 카운팅
                        cnt++;
                        boards[i][j] = '-';
                    }
                }
            }
            // 찾은 블록이 없다면 끝내기
            if (cnt == 0) break;
            answer += cnt;
            cnt = 0; // cnt 초기화
            /**
             * 비어있는 곳에 블록 내리기
             *  - 여기서는 checked 확인을 할 필요가 없다.
             *    checked는 블록 카운팅을 하기 위한 배열
             */
            for (int i = m-1; i >= 0; i--) {
                for (int j = 0; j < n; j++) {
                    if (boards[i][j] != '-') continue;
                    // 블록이 비어있다면('-'을 빈 블록으로 함), 해당 열을 순회하여 문자가 있는 블록을 내림
                    if (boards[i][j] == '-') {
                        for (int k = i; k >= 0; k--) {
                            if (!checked[k][j] && boards[k][j] != '-') {
                                boards[i][j] = boards[k][j];
                                checked[i][j] = false;
                                boards[k][j] = '-';

                                break;
                            }
                        }
                    }
                }
            }
        }

        return answer;
    }

    public static void findBlocks(int x, int y) {
        char target = boards[x][y];
        if (boards[x][y + 1] == target && boards[x + 1][y] == target && boards[x + 1][y + 1] == target) {
            checked[x][y] = true;
            checked[x][y + 1] = true;
            checked[x + 1][y] = true;
            checked[x + 1][y + 1] = true;
        }
    }
}

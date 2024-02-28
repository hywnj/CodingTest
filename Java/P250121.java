package Java;

import java.util.*;

/**
 * [PCCE 기출문제] 10번 / 데이터 분석
 * https://school.programmers.co.kr/learn/courses/30/lessons/250121
 */
public class P250121 {
    public static void main(String[] args) {
        // [
        //      [3,20300401,10,8],
        //      [1,20300104,100,80]
        // ]
        int[][] answer = solution(new int[][]{{1, 20300104, 100, 80},{2, 20300804, 847, 37},{3, 20300401, 10, 8}},"date",20300501,"remain");
        for (int[] arr : answer) {
            System.out.println(Arrays.toString(arr));
        }
    }

    /**
     * 1) data 순회하면서 ext가 val_ext보다 작은 데이터만 저장
     *      - Data class로 한 경우, 코드로 진행 잘 되는데, 프로그래머스에서 정렬이 적용되지 않음
     * 2) sort_by 기준으로 오름차순 정렬해서 반환
     */
    public static int[][] solution(int[][] data, String ext, int val_ext, String sort_by) {
        // data 항목에 대한 인덱스 저장
        Map<String, Integer> map = new HashMap<>();
        map.put("code", 0);
        map.put("date", 1);
        map.put("maximum", 2);
        map.put("remain", 3);

        // 기준에 부합한 data 추출
        List<int[]> list = new ArrayList<>();
        // List<Data> list = new ArrayList<>();
        for (int[] d : data) {
            if (d[map.get(ext)] < val_ext) { // ext 가 val_ext보다 작으면 Data로 저장
                // list.add(new Data(d[0], d[1], d[2], d[3], sort_by));
                list.add(d);
            }
        }
        // data 정렬
        // list.sort(Data::compareTo);
        list.sort(Comparator.comparingInt(o -> o[map.get(sort_by)]));

        int[][] answer = new int[list.size()][4];
        int idx = 0;
        for (int[] el: list) {
            // answer[idx++] = el.getData();
            answer[idx++] = el;
        }
        return answer;
    }

    // [코드 번호(code), 제조일(date), 최대 수량(maximum), 현재 수량(remain)]
    public static class Data implements Comparable<Data>{
        int code;
        int date;
        int maximum;
        int remain;
        String sort_by;
        public Data (int code, int date, int maximum, int remain, String sort_by) {
            this.code = code;
            this.date = date;
            this.maximum = maximum;
            this.remain = remain;
            this.sort_by = sort_by;
        }

        public int[] getData() {
            return new int[]{this.code, this.date, this.maximum, this.remain};
        }

        @Override
        public int compareTo(Data o) {
            if (this.sort_by == "date") return this.date - o.date;
            else if (this.sort_by == "maximum") return this.maximum - o.maximum;
            else if (this.sort_by == "remain") return this.remain - o.remain;
            else return this.code - o.code;
        }
    }
}

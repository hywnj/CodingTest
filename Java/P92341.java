package Java;

import java.util.*;

/**
 * 주차 요금 계산
 * https://school.programmers.co.kr/learn/courses/30/lessons/92341
 */
public class P92341 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution(new int[]{180, 5000, 10, 600}, new String[]{"05:34 5961 IN", "06:00 0000 IN", "06:34 0000 OUT", "07:59 5961 OUT", "07:59 0148 IN", "18:59 0000 IN", "19:09 0148 OUT", "22:59 5961 IN", "23:00 5961 OUT"}))); // [14600, 34400, 5000]
        System.out.println(Arrays.toString(solution(new int[]{120, 0, 60, 591}, new String[]{"16:00 3961 IN","16:00 0202 IN","18:00 3961 OUT","18:00 0202 OUT","23:58 3961 IN"}))); // [0, 591]
        System.out.println(Arrays.toString(solution(new int[]{1, 461, 1, 10}, new String[]{"00:00 1234 IN"}))); // [14841]
    }

    public static int[] solution(int[] fees, String[] records) {
        /**
         * 1. records 세팅
         *      - 이미 저장되어있는 차량번호 체크를 위해 map으로 선언
         */
        Map<String, ParkingInfo> map = new HashMap<>();
        for (String record : records) {
            StringTokenizer st = new StringTokenizer(record, " ");
            String time = st.nextToken().replace(":", "");
            String carNum = st.nextToken();
            String type =  st.nextToken();

            double inH, inM, outH, outM;
            if (map.containsKey(carNum)) {
                ParkingInfo carParkingInfo = map.get(carNum);
                if (type.equals("IN")) {
                    carParkingInfo.setInTime(time);
                } else { // type == "OUT"
                    // 이전에 저장되어있는 입차시간 가져와서 계산
                    String inTime = carParkingInfo.getInTime();
                    carParkingInfo.setTotalTime(calculateTime(inTime, time));
                }
            } else {
                // OUT이 먼저 들어오는 경우는 제한사항에서 제외되었기에 고려하지 않아도 됨
                map.put(carNum, new ParkingInfo(carNum, time));
            }
        }

        /**
         * 2. records 확인
         *      - 출차 시간이 입력되지 않은 경우 totalTime 계산해서 갱신
         *      - ParkingInfo를 리스트에 저장 : 정렬을 위함
         */
        List<ParkingInfo> infoList = new ArrayList<>();
        for (ParkingInfo mapInfo : map.values()) {
            String infoInTime = mapInfo.getInTime();
            if (!infoInTime.isBlank()) {
                mapInfo.setTotalTime(calculateTime(infoInTime, "2359"));
            }
            infoList.add(mapInfo);
        }
        infoList.sort(ParkingInfo::compareTo);

        /**
         * 3. 주차 요금 계산
         */
        double basicTime = fees[0];
        int basicPrice = fees[1];
        double unitTime = fees[2];
        int pricePerTime = fees[3];

        int[] answer = new int[map.size()];
        int idx = 0;
        for (ParkingInfo info : infoList) {
            int calPrice = basicPrice;
            if (info.getTotalTime() >= basicTime) {
                calPrice += Math.ceil((info.getTotalTime() - basicTime)/unitTime) * pricePerTime;
            }
            answer[idx++] = calPrice;
        }

        return answer;
    }

    public static double calculateTime(String in, String out) {
        double inH = Integer.parseInt(in.substring(0,2));
        double inM = Integer.parseInt(in.substring(2));
        double outH = Integer.parseInt(out.substring(0,2));
        double outM = Integer.parseInt(out.substring(2));

        return (outH * 60 + outM) - (inH * 60 + inM);
    }

    public static class ParkingInfo implements Comparable<ParkingInfo>{
        String carNum; // 차량번호

        String inTime; // 입차시간 (주차장에 이미 있는 차량(차량번호가 같은 차량)이 다시 입차되는 경우는 입력에 없기 때문에)
        double totalTime; // 총 누적시간

        public ParkingInfo(String carNum, String inTime) {
            this.carNum = carNum;
            this.inTime = inTime;
            this.totalTime = 0;
        }

        public void setInTime(String inTime) {
            this.inTime = inTime;
        }

        public String getInTime() {
            return inTime;
        }

        public void setTotalTime(double totalTime) {
            this.inTime = ""; // 입차시간 초기화 (출차시간 입력되는 경우에만 totalTime Setting)
            this.totalTime += totalTime;
        }

        public double getTotalTime() {
            return totalTime;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            ParkingInfo other = (ParkingInfo) o;
            return other.carNum == carNum;
        }

        @Override
        public int hashCode() {
            return Objects.hash(carNum);
        }

        @Override
        public int compareTo(ParkingInfo o) {
            int intCarNum = Integer.parseInt(this.carNum);
            int intOtherCarNum = Integer.parseInt(o.carNum);
            return intCarNum - intOtherCarNum;
        }
    }
}

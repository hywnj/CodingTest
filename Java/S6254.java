package Java;

import java.util.Scanner;

/**
 * 근무 시간
 * https://softeer.ai/practice/6254
 */
public class S6254 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int result = 0;
        for(int i=0;i<5;i++) {
            String start = sc.next();
            String end = sc.next();
            int startH = Integer.parseInt(start.substring(0,2));
            int startM = Integer.parseInt(start.substring(3));
            int endH = Integer.parseInt(end.substring(0,2));
            int endM = Integer.parseInt(end.substring(3));

            result += (endM - startM) + (endH - startH)*60;
        }
        System.out.println(result);
    }
}

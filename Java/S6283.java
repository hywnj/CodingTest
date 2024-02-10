package Java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 8단 변속기
 * https://softeer.ai/practice/6283
 */
public class S6283 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String strArr[] = br.readLine().split(" ");

        String result = "";
        int intArr[] = new int[8];
        for(int i=0; i<7; i++) {
            int previous = Integer.parseInt(strArr[i]);
            int next = Integer.parseInt(strArr[i+1]);

            if(previous - next == -1) {
                result = "ascending";
            } else if (previous - next == 1) {
                result = "descending";
            } else {
                result = "mixed";
                break;
            }
        }
        System.out.println(result);
    }
}

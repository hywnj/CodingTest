package Java;

import java.io.IOException;
import java.util.Scanner;

/**
 * 별 찍기 - 5
 * https://www.acmicpc.net/problem/2442
 */
public class B2442 {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int space = n-1;
        String[] stars = new String[n+1];
        for (int i=1; i<=n; i++) {
            stars[i] = "";
            for (int j=0; j<space; j++) {
                stars[i] += " ";
            }
            for (int j=0; j<(2*i)-1; j++) {
                stars[i] += "*";
            }
            space--;
        }

        for (int s=1; s<=n; s++) {
            System.out.println(stars[s]);
        }
    }
}

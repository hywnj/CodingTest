package Java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 금고털이
 * https://softeer.ai/practice/6288
 */
public class S6288 {

    public static void main(String[] args) throws IOException {
        // 귀금속 가격이 큰 순으로 나열
        // W를 채우지 못하지만, 남은 경우, 잘라서 가져갈 수 있음 (무게만큼 가치를 함)
        // 이 경우 배낭에 담을 수 있는만큼 가져와야됨 (잔여 무게만큼 가져오기)
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int W = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        int arr[][] = new int[N][2];
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken()); // weigth
            arr[i][1] = Integer.parseInt(st.nextToken()); // price
        }
        // 가격기준 내림차순 정렬
        Arrays.sort(arr, (o1, o2) -> o1[1] == o2[1] ? o1[0] - o2[0] : o2[1] - o1[1]);
        // Arrays.sort(arr, new Comparator<int[]>(){
        //    @Override
        //     public int compare(int[] o1, int[] o2) {
        //         if(o1[1] == o2[1]) return o1[0] - o2[0];
        //         else return o2[1] - o1[1];
        //     }
        // });

        int price = 0;
        int remainW = W;
        for(int i=0;i<N;i++) {
            // 남은 가능 무게가 귀금속 무게보다 작으면 남은 무게만큼만 추가
            if (arr[i][0] > remainW)
            {
                price += remainW * arr[i][1];
                break;
            }
            else // 남은 가능 무게가 귀금속 무게보다 크거나 같으면 추가
            {
                price += arr[i][0] * arr[i][1];
            }
            remainW -= arr[i][0];
        }
        System.out.println(price);
    }
}

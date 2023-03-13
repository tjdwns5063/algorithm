import java.io.*;
import java.math.*;

//time = 0.5sec
//space = 512mb
//n = 0 <= n <= 1,000,000
//timeComp = O(n)

public class boj12852_1로만들기2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] dp = new int[1000001];
        dp[1] = 0;
        dp[2] = 1;
        dp[3] = 1;

        for (int i = 4; i <= 1000000; ++i) {
            if (i % 2 == 0 && i % 3 == 0) {
                dp[i] = Math.min(dp[i/2], dp[i/3]);
                dp[i] = Math.min(dp[i], dp[i-1]) + 1;
            } else if (i % 2 == 0) {
                dp[i] = Math.min(dp[i/2], dp[i-1]) + 1;
            } else if (i % 3 == 0) {
                dp[i] = Math.min(dp[i/3], dp[i-1]) + 1;
            } else {
                dp[i] = dp[i-1] + 1;
            }
        }
        StringBuilder route = new StringBuilder();
        int back = n;
        route.append(back);
        route.append(" ");
        while (back != 1) {
            if (back % 2 == 0 && back % 3 == 0) {
                int curr = back;
                back = (dp[curr/2] > dp[curr/3]) ? curr/3 : curr/2;
                back = (dp[back] > dp[curr-1]) ? curr-1 : back;
                route.append(back);
                route.append(" ");
            } else if (back % 2 == 0) {
                back = (dp[back/2] > dp[back-1]) ? back-1 : back/2;
                route.append(back);
                route.append(" ");
            } else if (back % 3 == 0) {
                back = (dp[back/3] > dp[back-1]) ? back-1 : back/3;
                route.append(back);
                route.append(" ");
            } else {
                back = back-1;
                route.append(back);
                route.append(" ");
            }
        }

        System.out.println(dp[n]);
        System.out.println(route);
    }
}
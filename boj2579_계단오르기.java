//time : 1sec
//space : 128mb
//n : 1 <= n <= 300
//timeComplex = o(n)

import java.io.*;
import java.util.*;
import java.math.*;

public class boj2579_계단오르기 {
    static int n;
    static int[] stairs;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        initArgs();


        dp[0] = 0;
        dp[1] = stairs[1];
        dp[2] = Math.max(stairs[2], stairs[1] + stairs[2]);
        // dp[3] = Math.max(dp[1] + stairs[3], dp[0] + stairs[2] + stairs[3]);

        // 4 :1-> 2 -> 4 or 2->4
        // 1 -> 3 -> 4
        for (int i = 3; i <= n; ++i) {
            dp[i] = Math.max(dp[i-2] + stairs[i], dp[i-3] + stairs[i-1] + stairs[i]);
            // dp[i] = Math.max()
        }

        System.out.println(dp[n]);
    }

    public static void initArgs() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        n = Integer.parseInt(br.readLine());
        stairs = new int[302];
        dp = new int[302];

        for (int i = 1; i <= n; ++i) {
            stairs[i] = Integer.parseInt(br.readLine());
        }
    }
}
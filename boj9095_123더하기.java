//time = 1sec
//space = 512mb
//n = 1 <= n <= 10
//timeComp = o(n)

import java.io.*;
import java.util.*;
import java.math.*;


public class boj9095_123더하기 {
    static int[] n;
    static int t;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        initArgs();

        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 4;

        for (int i = 4; i <= 10; ++i) {
            dp[i] = dp[i-3] + dp[i-2] + dp[i-1];
        }
        
        for (int i = 0; i < n.length; ++i) {
            System.out.println(dp[n[i]]);
        }
    }

    public static void initArgs() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        t = Integer.parseInt(br.readLine());
        n = new int[t];
        dp = new int[11];

        for (int i = 0; i < t; ++i) {
            n[i] = Integer.parseInt(br.readLine());
        }
    }
}
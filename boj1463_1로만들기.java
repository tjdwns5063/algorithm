// time = 0.15sec
// memory = 128mb
// n = 1 <= n <= 1_000_000
// timeComp = o(n)

import java.io.*;
import java.util.*;
import java.math.*;

public class boj1463_1로만들기 {
    static int[] dp;
    static int n;
    public static void main(String[] args) throws IOException {
        initArgs();

        dp[1] = 0;
        dp[2] = 1;
        dp[3] = 1;
        
        for (int i = 4; i <= 1000000; ++i) {
            if (i % 2 == 0 && i % 3 == 0) {
                dp[i] = Math.min(dp[i / 2], dp[i / 3]);
                dp[i] = Math.min(dp[i], dp[i-1]) + 1;
            }else if (i % 2 == 0) {
                dp[i] = Math.min(dp[i / 2], dp[i-1]) + 1;
            } else if (i % 3 == 0) {
                dp[i] = Math.min(dp[i / 3], dp[i-1]) + 1;
            } else {
                dp[i] = dp[i-1] + 1;
            }
        }
        System.out.println(dp[n]);
    }

    public static void initArgs() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        n = Integer.parseInt(br.readLine());
        dp = new int[1000001];
    }
}
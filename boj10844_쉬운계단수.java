// time = 1sec
// space = 256mb
// n = 1 <= n <= 100
// timeComplex = O(n)

import java.io.*;
import java.util.*;
import java.math.*;

public class boj10844_쉬운계단수 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        long[][] dp = new long[101][10];

        Arrays.fill(dp[1], 1);
        dp[1][0] = 0; 

        for (int i = 2; i <= 100; ++i) {
            for (int j = 0; j < 10; ++j) {
                if (j == 0) {
                    dp[i][j+1] += dp[i-1][j]%1000000000;
                } else if (j == 9) {
                    dp[i][j-1] += dp[i-1][j]%1000000000;
                } else {
                    dp[i][j-1] += dp[i-1][j]%1000000000;
                    dp[i][j+1] += dp[i-1][j]%1000000000;
                }
            }
        }
        // printDp(dp, n);

        long ans = 0;
        for (int i = 0; i < 10; ++i) {
            ans += dp[n][i];
        }
    
        System.out.println(ans%1000000000);
    }

    private static void printDp(long[][] dp, int i) {
        for (int j = 0; j < 10; ++j) {
            System.out.print(dp[i][j] + " ");
        }
        System.out.println();
    }
}
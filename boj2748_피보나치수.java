import java.io.*;
import java.util.*;
import java.math.*;

// time = 1sec
// space = 128mb
// n = 0 <= n <= 90
// timeComplex = O(n)

public class boj2748_피보나치수 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long[] dp = new long[91];
        int n = Integer.parseInt(br.readLine());

        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 1;

        for (int i = 3; i <= 90; ++i) {
            dp[i] = dp[i-2] + dp[i-1];
        }
        System.out.println(dp[n]);
    }
}
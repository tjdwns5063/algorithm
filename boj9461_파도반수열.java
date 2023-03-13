import java.io.*;
import java.util.*;
import java.math.*;

//time = 1sec
//space = 128mb
// n = 1 <= n <= 100
// timeComplex = O(n)

public class boj9461_파도반수열 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        long[] dp = new long[101];

        dp[1] = 1;
        dp[2] = 1;
        dp[3] = 1;

        for (int i = 4; i <= 100; ++i) {
            dp[i] = dp[i-2] + dp[i-3];
        }

        StringBuilder ans = new StringBuilder();
        while (t > 0) {
            int n = Integer.parseInt(br.readLine());

            ans.append(dp[n]);
            ans.append("\n");
            --t;
        }
        System.out.print(ans);
    }
}
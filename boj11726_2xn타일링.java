import java.io.*;
import java.util.*;
import java.math.*;

public class boj11726_2xn타일링 {
    static int[] dp = new int[1001];
    static int n;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= 1000; ++i) {
            dp[i] = (dp[i-1] + dp[i-2]) % 10007;
        }
        System.out.println(dp[n]);
    }
}
import java.io.*;

//time = 1sec
//space = 256MB
//n = 1 <= n <= 1000
//timeComplex = O(n)

public class boj11727_2xn타일링2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] dp = new int[1001];

        dp[1] = 1;
        dp[2] = 3;

        for (int i = 3; i <= 1000; ++i) {
            dp[i] = (dp[i-1] + (dp[i-2] * 2)) % 10007;
        }
        System.out.println(dp[n]);
    }
}
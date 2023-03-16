import java.io.*;
import java.util.*;
import java.math.*;

// time = 2sec
// space = 512mb
// n = 1 <= n <= 1,500,000
// timeComplex = O(n)

public class boj15486_퇴사2 {
    static int n;
    static int[] dp;
    static int[] times;
    static int[] prices;
    
    public static void main(String[] args) throws IOException {
        initArgs();
        dp();
        // printDp();
        System.out.println(dp[n]);
    }

    private static void dp() {
        for (int i = 0; i < n; ++i) {
            if (i + times[i] <= n) {
                dp[i + times[i]] = Math.max(dp[i + times[i]], dp[i] + prices[i]);
            }
            dp[i+1] = Math.max(dp[i], dp[i+1]);
        }
    }

    private static void printDp() {
        for (int i = 0; i <= n; ++i) {
            System.out.print(dp[i] + " ");
        }
        System.out.println();
    }

    private static void initArgs() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        dp = new int[n + 1];
        times = new int[n];
        prices = new int[n];

        for (int i = 0; i < n; ++i) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            times[i] = Integer.parseInt(st.nextToken());
            prices[i] = Integer.parseInt(st.nextToken());
        }
    }
}
//time = 2sec
//space = 512mb
// n = 1 <= n <= 15
// timeComplex = O(n^2)

import java.io.*;
import java.util.*;
import java.math.*;

public class boj14501_퇴사 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] times = new int[16];
        int[] prices = new int[16];
        int[][] dp = new int[16][16];

        for (int i = 0; i < n; ++i) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int time = Integer.parseInt(st.nextToken());
            int price = Integer.parseInt(st.nextToken());

            times[i + 1] = time;
            prices[i + 1] = price;
        }

        for (int i = 1; i <= 15; ++i) {
            dp[i][0] = (i + times[i] <= n+1) ? prices[i] : 0;
        }

        for (int i = 1; i <= 15; ++i) {
            for (int j = 1; j <= 15; ++j) {
                if (i + times[i] < j && j + times[j] <= n + 1) {
                    if (j-1 + times[j-1] <= j) {
                        dp[i][j] = dp[i][j-1] + prices[j];
                    } else {
                        dp[i][j] = dp[i][i + times[i]] + prices[j];
                    }
                } else {
                    if (j == i + times[i] && j + times[j] <= n + 1)
                        dp[i][j] = dp[i][j-1] + prices[j];
                    else
                        dp[i][j] = dp[i][j-1];
                }
            }
        }

        // printDp(dp, n);
        int ans = 0;

        for (int i = 1; i <= n; ++i) {
            ans = Math.max(dp[i][n], ans);
        }
        System.out.println(ans);
    }

    private static void printDp(int[][] dp, int n) {
        for (int i = 1; i <= n; ++i) {
            for (int j = 0; j <= n; ++j) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }
    }
}
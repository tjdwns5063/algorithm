//time : 0.5sec
//space : 128mb
//n : 1 <= n <= 1000
//timeComplex: O(n)

import java.io.*;
import java.util.*;
import java.math.*;

public class boj1149_RGB거리 {
    static int n;
    static int[][] costs;
    static int[][] dp;
    
    public static void main(String[] args) throws IOException {
        initArgs();

        for (int i = 1; i <= 3; ++i) {
            dp[1][i] = costs[1][i];
        }

        for (int i = 2; i <= 1000; ++i) {
            dp[i][1] = Math.min(dp[i-1][2], dp[i-1][3]) + costs[i][1];
            dp[i][2] = Math.min(dp[i-1][1], dp[i-1][3]) + costs[i][2];
            dp[i][3] = Math.min(dp[i-1][1], dp[i-1][2]) + costs[i][3];
        }

        printDp(n);
        int ans = Integer.MAX_VALUE;

        for (int j = 1; j <= 3; ++j)
            ans = Math.min(ans, dp[n][j]);
        System.out.println(ans);
    }

    public static void printDp(int n) {
        for (int i = 1; i <= n; ++i) {
            for (int j = 1; j <= 3; ++j) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void initArgs() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        dp = new int[10004][4];
        costs = new int[1004][4];
        n = Integer.parseInt(br.readLine());

        for (int i = 1; i <= n; ++i) {
            st = new StringTokenizer(br.readLine());
            costs[i][1] = Integer.parseInt(st.nextToken());
            costs[i][2] = Integer.parseInt(st.nextToken());
            costs[i][3] = Integer.parseInt(st.nextToken());
        }
    }
}
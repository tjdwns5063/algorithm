import java.io.*;
import java.util.*;
import java.math.*;

public class boj2482_색상환 {
    static int n, k;
    static long[][] dp = new long[1001][1001];

    public static void main(String[] args) throws IOException {
        initArgs();

        for (int i = 1; i <= n; ++i) {
            dp[i][1] = i;
        }

        for (int i = 4; i <= n; ++i) {
            for (int j = 2; j <= n; ++j) {
                dp[i][j] = (dp[i-2][j-1] + dp[i-1][j]) % 1000000003;
            }
        }

        // printDp();
        System.out.println(dp[n][k]);
    }

    public static void printDp() {
        for (int i = 0; i <= n; ++i) {
            for (int j = 0; j <= k; ++j) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }


    public static void initArgs() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        k = Integer.parseInt(br.readLine());
    }
}
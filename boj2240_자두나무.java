import java.io.*;
import java.util.*;
import java.math.*;

// time = 2sec
// space = 128mb
// t = 0 <= t <= 1000
// w = 1 <= w <= 30
// timeCoplex = O(t*w);

public class boj2240_자두나무 {
    static int t, w; // 떨어지는 자두의 갯수, 이동 가능 횟수
    static int[] falls = new int[1001]; // index에 해당하는 시간에 자두가 떨어지는 나무의 번호
    static int[][][] dp = new int[3][1001][32]; // 나무번호, 떨어지는 자두의 갯수, , 이동 가능 횟수

    public static void main(String[] args) throws IOException {
        initArgs();

        dp[1][1][1] = (falls[1] == 1) ? 1 : 0;
        dp[2][1][2] = (falls[1] == 2) ? 1 : 0;

        for (int i = 2; i <= 1000; ++i) {
            for (int j = 1; j <= 31; ++j) {
                if (falls[i] == 1) {
                    dp[1][i][j] = Math.max(dp[1][i-1][j] + 1, dp[2][i-1][j-1] + 1);
                    dp[2][i][j] = Math.max(dp[1][i-1][j-1], dp[2][i-1][j]);
                } else {
                    dp[2][i][j] = Math.max(dp[2][i-1][j] + 1, dp[1][i-1][j-1] + 1);
                    dp[1][i][j] = Math.max(dp[2][i-1][j-1], dp[1][i-1][j]);
                }
            }
        }
        int ans = 0;
        for (int i = 1; i <= w+1; i++) {
            ans = Math.max(ans, Math.max(dp[1][t][i], dp[2][t][i]));
        }
        System.out.println(ans);
    }

    private static void initArgs() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        t = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());

        for (int i = 0; i < t; ++i) {
            falls[i+1] = Integer.parseInt(br.readLine());
        }
    }
}
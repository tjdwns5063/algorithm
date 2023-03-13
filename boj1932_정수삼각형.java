import java.io.*;
import java.util.*;
import java.math.*;

//time = 2sec
//space = 128mb
//n = 1 <= n <= 500
//timeComplex = O(n^2) n(n+1)/2

public class boj1932_정수삼각형 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] triangle = new int[501][501];
        int[][] dp = new int[501][501];

        for (int i = 0; i < n; ++i) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < i+1; ++j) {
                triangle[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp[0][0] = triangle[0][0];
        for (int i = 1; i < 500; ++i) {
            for (int j = 0; j < i+1; ++j) {
                int parentLeft = j - 1;
                int parentRight = j;

                if (parentLeft >= 0 && parentRight < i+1) {
                    dp[i][j] = Math.max(dp[i-1][parentLeft], dp[i-1][parentRight]) + triangle[i][j];
                } else if (parentLeft >= 0) {
                    dp[i][j] = dp[i-1][parentLeft] + triangle[i][j]; 
                } else if (parentRight < i + 1) {
                    dp[i][j] = dp[i-1][parentRight] + triangle[i][j];
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            ans = Math.max(dp[n-1][i], ans);
        }
        System.out.println(ans);
    }

    private static void printTriangle(int[][] triangle) {
        for (int i = 0; i < triangle.length; ++i) {
            for (int j = 0; j < i+1; ++j) {
                System.out.print(triangle[i][j] + " ");
            }
            System.out.println();
        }
    }
}
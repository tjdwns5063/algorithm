//time = 2sec
//space = 512mb
// n = 1 <= n <= 15
// timeComplex = O(n^2)

import java.io.*;
import java.util.*;
import java.math.*;

//dfs풀이
// public class boj14501_퇴사 {
//     static int n;
//     static int[] times = new int[16];
//     static int[] prices = new int[16];
//     static int ans = 0;
//     static int total = 0;
//     public static void main(String[] args) throws IOException {
//         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

//         n = Integer.parseInt(br.readLine());
//         for (int i = 0; i < n; ++i) {
//             StringTokenizer st = new StringTokenizer(br.readLine());

//             int time = Integer.parseInt(st.nextToken());
//             int price = Integer.parseInt(st.nextToken());

//             times[i + 1] = time;
//             prices[i + 1] = price;
//         }

//         for (int i = 1; i <= n; ++i) {
//             total = 0;
//             boolean[] visited = new boolean[16]; 
//             dfs(i, visited);
//         }
//         System.out.println(ans);
//     }

//     private static void dfs(int idx, boolean[] visited) {
//         if (idx > n) {
//             ans = Math.max(ans, total);
//             return ;
//         }
//         for (int i = idx; i <= n; ++i) {
//             if (visited[i])
//                 continue ;
//             visited[i] = true;
//             if (i + times[i] <= n + 1) {
//                 total += prices[i];
//             }
//             dfs(i + times[i], visited);
//             visited[i] = false;
//             if (i + times[i] <= n + 1) {
//                 total -= prices[i];
//             }
//         }
//     }
// }

//dp풀이
//time = 2sec
//space = 512mb
// n = 1 <= n <= 15
// timeComplex = O(n)


public class boj14501_퇴사 {
    static int n;
    static int[] times = new int[16];
    static int[] prices = new int[16];
    static int[] dp = new int[16];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; ++i) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int time = Integer.parseInt(st.nextToken());
            int price = Integer.parseInt(st.nextToken());

            times[i] = time;
            prices[i] = price;
        }

        for (int i = 0; i < n; ++i) {
            if (i + times[i] <= n) {
                dp[i + times[i]] = Math.max(dp[i + times[i]], dp[i] + prices[i]);
            }
            dp[i + 1] = Math.max(dp[i + 1], dp[i]);
        }        
        // printDp();
        System.out.println(dp[n]);
    }

    private static void printDp() {
        for (int i = 1; i <= n; ++i) {
            System.out.print(i + " ");
        }
        System.out.println();

        for (int i = 1; i <= n; ++i) {
            System.out.print(dp[i] + " ");
        }
    }
}
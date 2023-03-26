// time = 2.5sec
// space = 256mb
// 1 <= n <= 2,000
// 1 <= m <= 1,000,000
// timeComplex = O(n^2)

import java.io.*;
import java.util.*;
import java.math.*;

public class boj10942_팰린드롬 {
    static int n, m;
    static int[] nums = new int[2001];
    static boolean[][] dp = new boolean[2001][2001];
    static ArrayList<int[]> questions = new ArrayList<int[]>();
    public static void main(String[] args) throws IOException {
        initArgs();
        solve();
    }

    private static void initArgs() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; ++i) {
            nums[i + 1] = Integer.parseInt(st.nextToken());
        }

        m = Integer.parseInt(br.readLine());
        for (int i = 0; i < m; ++i) {
            int[] question = new int[2];
            st = new StringTokenizer(br.readLine());

            question[0] = Integer.parseInt(st.nextToken());
            question[1] = Integer.parseInt(st.nextToken());
            questions.add(question);
        }
    }

    private static void solve() {
        for (int i = 1; i <= n; ++i) {
            dp[i][i] = true;

            int curr = i;
            int next = i + 1;

            for (int j = 0; j <= n / 2; ++j) {
                int st = curr - j;
                int en = curr + j;

                if (st < 1 || en > n) {
                    break ;
                }
                if (nums[st] == nums[en])
                    dp[st][en] = true;
                else
                    break ;
            }

            for (int j = 0; j <= n / 2; ++j) {
                int st = curr - j;
                int en = next + j;

                if (st < 1 || en > n) {
                    break ;
                }
                if (nums[st] == nums[en])
                    dp[st][en] = true;
                else
                    break ;
            }            
        }

        StringBuilder ans = new StringBuilder();

        for (int i = 0; i < questions.size(); ++i) {
            int[] question = questions.get(i);

            if (!dp[question[0]][question[1]]) {
                ans.append("0\n");
            } else {
                ans.append("1\n");
            }
        }
        System.out.print(ans);
    }


    private static void printDp() {
        for (int i = 1; i <= n; ++i) {
            for (int j = 1; j <= n; ++j) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }
    }
}
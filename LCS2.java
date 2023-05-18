import java.io.*;
import java.util.*;

public class LCS2 {
    static int[][] dp = new int[1001][1001];
    static String s1;
    static String s2;
    public static void main(String[] args) throws IOException {
        initArgs();
        solve();
    }

    private static void initArgs() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        s1 = br.readLine();
        s2 = br.readLine();
    }

    private static void solve() {
        int cnt = 0;

        for (int i = 1; i <= s1.length(); ++i) {
            for (int j = 1; j <= s2.length(); ++j) {
                if (s1.charAt(i-1) == s2.charAt(j-1)) {
                    cnt = dp[i-1][j-1] + 1;
                } else {
                    cnt = Math.max(dp[i-1][j], dp[i][j-1]);
                }
                dp[i][j] = cnt;
            }
        }

        
        System.out.println(cnt);

        if (cnt != 0) {
            printLCS(cnt);
        }
    }

    private static void printLCS(int cnt) {
        List<Character> str = new ArrayList<>();
        int x = s1.length();
        StringBuilder ans = new StringBuilder();
        boolean stopFlag = false;

        while (!stopFlag && x > 0) {
            int y = s2.length();
            while (!stopFlag && x > 0 && y > 0) {
                if (str.size() == cnt) {
                    stopFlag = true;
                }
                if (dp[x-1][y] == dp[x][y]) {
                    --x;
                } else if (dp[x][y-1] == dp[x][y]) {
                    --y;
                } else {
                    str.add(s1.charAt(x-1));
                    --x;
                    --y;
                }
            }
        }
        for (Character c: str) {
            ans.append(c);
        }
        System.out.println(ans.reverse()); 
        return ;  
        
    }
}
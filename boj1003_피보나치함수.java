import java.io.*;

//time = 0.25sec
//space = 128mb
//n = 0 <= n <= 40
//timeComplex = o(n)

public class boj1003_피보나치함수 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        int[][] dp = new int[41][2];

        dp[0][0] = 1;
        dp[0][1] = 0;
        dp[1][0] = 0;
        dp[1][1] = 1;
        for (int i = 2; i <= 40; ++i) {
            dp[i][0] = dp[i-2][0] + dp[i-1][0];
            dp[i][1] = dp[i-2][1] + dp[i-1][1];
        }

        StringBuilder ans = new StringBuilder(); 
        while (t > 0) {
            int n = Integer.parseInt(br.readLine());
            
            ans.append(dp[n][0]);
            ans.append(" ");
            ans.append(dp[n][1]);
            ans.append("\n");
            --t;
        }
        System.out.print(ans);
    }
}
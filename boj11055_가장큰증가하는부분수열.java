import java.io.*;
import java.util.*;
import java.math.*;

//time = 1sec
//space = 256mb
// n = 1 <= n <= 1000
//timeComplex = O(n^2)

public class boj11055_가장큰증가하는부분수열 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] nums = new int[n+1];
        int[] dp = new int[n+1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        
        for (int i = 1; i <= n; ++i) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i <= n; ++i) {
            for (int j = 0; j <= n; ++j) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[j] + nums[i], dp[i]);
                }
            }
        }
        System.out.println(Arrays.stream(dp).max().getAsInt());
    }
}
import java.io.*;
import java.util.*;
import java.math.*;
import java.util.stream.Collectors;

//time = 1sec
//space = 128mb
//n = 1 <= n <= 100,000
//timeComplex = O(n)

public class boj1912_연속합 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] nums = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; ++i) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[n];
        dp[0] = nums[0];
        
        for (int i = 1; i < n; ++i) {
            dp[i] = (dp[i-1] + nums[i] > nums[i]) ? dp[i-1] + nums[i] : nums[i]; 
        }
        int ans = Integer.MIN_VALUE;
        for (int i = 0; i < n; ++i) {
            ans = Math.max(ans, dp[i]);
        }
        System.out.println(ans);
    }
}
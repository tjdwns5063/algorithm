import java.math.*;

class Solution {
    public int solution(int[] sticker) {
        int answer = 0;
        int len = sticker.length;
        int[] dp = new int[len];

        dp[0] = sticker[0];
        dp[1] = sticker[1];
        dp[2] = sticker[0] + sticker[2];

        for (int i = 3; i < sticker.length; ++i) {
            dp[i] = Math.max(dp[i-2] + sticker[i], dp[i-3] + sticker[i]);
            dp[i] = Math.max(dp[i-1], dp[i]);
        }
        // answer = dp[len-1];
        return dp[len-1];
    }
}

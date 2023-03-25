class test2 {
    public int solution(int n) {
        int answer = 0;
        int[] dp = new int[150000];
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 3;

        for (int i = 2; i <= 350; ++i) {
            dp[i * i] = 1;
            int prev = (i - 1) * (i - 1);
            int curr = i* i;
            int next = (i + 1) * (i + 1);
            for (int j = curr + 1; j < next; ++j) {
                dp[j] = Math.min(dp[curr] + dp[j - curr], dp[prev] + dp[j - prev]);
                for (int k = i; k >= 1; --k) {
                    if (j == 41) {
                        System.out.println(k);
                    }
                    int s = k*k;
                    dp[j] = Math.min(dp[j], dp[s] + dp[j - s]);
                }
            }
        }
        return dp[n];
    }
}
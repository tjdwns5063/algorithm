import java.io.*;
import java.util.*;
import java.math.*;

// time = 1sec
// space = 128mb
// 1 <= n <= 100,000
// 1 <= x <= 2,000,000
// timeComplex = O(n)

public class boj3273_두수의합 {
    static int n;
    static int x;
    static int[] nums;
    
    public static void main(String[] args) throws IOException {
        initArgs();
        solve();
    }

    private static void solve() {
        int ans = 0;

        Arrays.sort(nums);

        int start = 0;
        int end = n-1;

        while (start < end) {
            if (nums[start] + nums[end] == x) {
                ++ans;
                start += 1;
                end -= 1;
            } else if (nums[start] + nums[end] < x) {
                start += 1;
            } else {
                end -= 1;
            }
        }
        System.out.println(ans);
    }

    private static void initArgs() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        n = Integer.parseInt(br.readLine());
        nums = new int[n];
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; ++i) {
            nums[i] = Integer.parseInt(st.nextToken());
        }
        x = Integer.parseInt(br.readLine());
    }
}
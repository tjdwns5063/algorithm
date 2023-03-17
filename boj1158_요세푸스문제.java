import java.io.*;
import java.util.*;
import java.math.*;

// time = 2sec
// space = 256mb
// 1 <= k <= n <= 5,000
// timeComplex = O(n^2)

public class boj1158_요세푸스문제 {
    static int n, k;
    static int[] yose;
    static boolean[] deleted;
    public static void main(String[] args) throws IOException {
        initArgs();
        solve();
    }

    private static void solve() {
        int cnt = 0;
        int yoseIdx = 0;
        int currIdx = 0;
        
        while (yoseIdx < n) {
            if (currIdx == n) {
                currIdx = 0;
            }
            if (!deleted[currIdx]) {
                ++cnt;
            }
            if (cnt == k) {
                deleted[currIdx] = true;
                cnt = 0;
                yose[yoseIdx] = currIdx + 1;
                ++yoseIdx;
            }
            ++currIdx;
        }
        
        System.out.print("<");
        for (int i = 0; i < n; ++i) {
            if (i==n-1) {
                System.out.println(yose[i] + ">");
            } else {
                System.out.print(yose[i] + ", ");
            }
        }
    }

    private static void initArgs() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        yose = new int[n];
        deleted = new boolean[n];
    }
}
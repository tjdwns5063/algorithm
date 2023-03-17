import java.io.*;
import java.util.*;
import java.math.*;

// time =  1.5sec
// space = 128mb
// 1 <= n <= 500,000
// timeComplex = O(2n) ??? 

public class boj2493_탑 {
    static Stack<int[]> stk = new Stack<int[]>();
    static Stack<int[]> stk2 = new Stack<int[]>();
    static int[] towers;
    static int n;
    public static void main(String[] args) throws IOException {
        initArgs();
        solve();
    }

    private static void solve() {
        int[] ans = new int[n];
        int cnt = 0;

        while (!stk.isEmpty()) {
            int[] curr = stk.pop();

            if (!stk.isEmpty() && curr[0] <= stk.peek()[0]) {
                ans[curr[1]] = stk.peek()[1] + 1;
            } else {
                stk2.add(curr);
            }
            while (!stk2.isEmpty() && !stk.isEmpty() && stk2.peek()[0] <= stk.peek()[0]) {
                ans[stk2.peek()[1]] = stk.peek()[1] + 1;
                stk2.pop();
            }
        }

        System.out.println(cnt);
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < n; ++i) {
            sb.append(ans[i]);
            sb.append(" ");
        }
        System.out.println(sb);
    }


    private static void initArgs() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        towers = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; ++i) {
            towers[i] = Integer.parseInt(st.nextToken());
            int[] item = {towers[i], i};
            stk.add(item);
        }
    }
}
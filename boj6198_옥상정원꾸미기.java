import java.io.*;
import java.util.*;
import java.math.*;

// time = 1sec
// space = 128mb
// 1 <= n <= 80,000
// timeComplex = O(n)

public class boj6198_옥상정원꾸미기 {
    static int[] towers;
    static int n;
    static Stack<Integer> stk = new Stack<Integer>();
    
    public static void main(String[] args) throws IOException {
        initArgs();
        solve();
    }

    private static void solve() {
        long ans = 0;

        for (int i = 0; i < n; ++i) {
            while (!stk.isEmpty() && stk.peek() <= towers[i]) {
                stk.pop();
            }
            if (!stk.isEmpty() && stk.peek() > towers[i]) {
                ans += stk.size();
            }
            stk.add(towers[i]);
        }
        // System.out.println(stk);
        System.out.println(ans);
    }

    private static void initArgs() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        towers = new int[n];

        for (int i = 0; i < n; ++i) {
            towers[i] = Integer.parseInt(br.readLine());
        }
    }
}
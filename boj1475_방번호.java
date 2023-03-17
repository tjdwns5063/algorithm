import java.io.*;
import java.util.*;
import java.math.*;

// time = 2sec
// space = 128mb
// 1 <= n <= 1,000,000
// timeComplex = O(n)

public class boj1475_방번호 {
    static String n;
    static int[] cnts = new int[10];
    public static void main(String[] args) throws IOException {
        initArgs();
        solve();
    }

    private static void solve() {
        for (int i = 0; i < n.length(); ++i) {
            int idx = (int)(n.charAt(i) - '0');

            if (idx == 9) {
                cnts[6] += 1;
            } else {
                cnts[idx] += 1;
            }
        }

        cnts[6] = (cnts[6] / 2) + ((cnts[6] % 2 == 0) ? 0 : 1);
        
        int ans = 0;
        for (int i = 0; i < 10; ++i) {
            if (ans < cnts[i]) {
                ans = cnts[i];
            }
        }
        System.out.println(ans);
    }

    private static void initArgs() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = br.readLine();
    }
}
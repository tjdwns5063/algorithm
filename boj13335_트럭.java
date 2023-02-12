import java.io.*;
import java.util.*;
import java.math.*;

public class boj13335_트럭 {
    static BufferedReader br;
    static int n;
    static int w;
    static int L;
    static int[] trucks;

    public static void initVar() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        
        n = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        trucks = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; ++i) {
            trucks[i] = Integer.parseInt(st.nextToken());
        }
    }

    public static int moveTruck(int[] bridge, int currWeight) {
        for (int i = w-1; i >= 0; --i) {
            if (i == w-1) {
                currWeight -= bridge[w-1];
                bridge[w-1] = 0;
            } else {
                bridge[i+1] = bridge[i];
                bridge[i] = 0;
            }
        }
        return currWeight;
    }

    public static void solve() {
        int[] bridge = new int[w];

        int currWeight = 0;
        int idx = 0;
        int ans = 0;
        
        while (true) {
            if (idx < n && bridge[0] == 0 && currWeight + trucks[idx] <= L) {
                bridge[0] = trucks[idx];
                currWeight += trucks[idx];
                ++idx;
                currWeight = moveTruck(bridge, currWeight);
                ++ans;
            } else {
                currWeight = moveTruck(bridge, currWeight);
                ++ans;    
            }
            int cnt = 0;
            for (int i = 0; i < w; ++i) {
                if (bridge[i] == 0)
                    ++cnt;
            }
            if (idx >= n && cnt == w) {
                break;
            }
        }
        System.out.println(ans+1);
    }

    public static void main(String[] args) throws IOException {
        initVar();
        solve();
    }
}
import java.io.*;
import java.util.*;
import java.math.*;

public class boj13305_주유소 {
    static int n;
    static int[] dists;
    static int[] costs;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        dists = new int[n-1];
        costs = new int[n];

        for (int i = 0; i < n-1; ++i) {
            dists[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; ++i) {
            costs[i] = Integer.parseInt(st.nextToken());
        }

        long ans = 0;
        long currCost = costs[0];

        for (int i = 0; i < n-1; ++i) {
            if (currCost > costs[i])
                currCost = costs[i];
            ans += dists[i] * currCost;
        }
        System.out.println(ans);
    }
}
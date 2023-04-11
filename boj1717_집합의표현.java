import java.io.*;
import java.util.*;
import java.math.*;

// time = 2sec
// space = 128mb
// 1 <= n <= 1,000,000
// 1 <= m <= 100,000
// timeComplex = O(mlogn)

public class boj1717_집합의표현 {
    static int n, m;
    static int[] parent = new int[1_000_001];
    static int[] rank = new int[1_000_001];
    static ArrayList<int[]> orders = new ArrayList();

    public static void main(String[] args) throws IOException {
        initArgs();
        solve();
    }

    private static void initArgs() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        for (int i = 0; i <= 1_000_000; ++i) {
            parent[i] = i;
        }

        for (int i = 0; i < m; ++i) {
            st = new StringTokenizer(br.readLine());
            int[] order = new int[3];

            order[0] = Integer.parseInt(st.nextToken());
            order[1] = Integer.parseInt(st.nextToken());
            order[2] = Integer.parseInt(st.nextToken());

            orders.add(order);
        }
    }

    private static void solve() {
        StringBuilder ans =  new StringBuilder();

        for (int[] order: orders) {
            int orderType = order[0];
            int a = order[1];
            int b = order[2];

            if (orderType == 0) {
                union(a, b);
            } else {
                if (find(a) == find(b))
                    ans.append("YES\n");
                else
                    ans.append("NO\n");
            }
        }
        System.out.print(ans);
    }

    private static int find(int n) {
        if (n == parent[n])
            return n;
        return parent[n] = find(parent[n]);
    }

    private static void union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);

        if (rootA != rootB) {
            if (rank[rootA] < rank[rootB])
                parent[rootA] = rootB;
            else if (rank[rootB] < rank[rootA])
                parent[rootB] = rootA;
            else {
                parent[rootA] = rootB;
                ++rank[rootB];
             }
        }
    }

}
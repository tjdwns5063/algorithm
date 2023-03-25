import java.io.*;
import java.util.*;
import java.math.*;

public class boj1949_우수마을 {
    static int n;
    static ArrayList<Pair> populations;
    static ArrayList<ArrayList<Integer>> graph;
    static int[][] dp;

    public static class Pair {
        public int population;
        public int node;

        public Pair(int _population, int _node) {
            population = _population;
            node = _node;
        }
    }

    public static void main(String[] args) throws IOException {
        initArgs();
        dp = new int[n+1][n+1];

        // for (int i = 1 i <= n; ++i) {
            // dp[i]
        // }
        System.out.println(graph);
    }

    public static void initArgs() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        populations = new ArrayList<Pair>();
        for (int i = 1; i <= n; ++i) {
            populations.add(new Pair(Integer.parseInt(st.nextToken()), i));
        }
        graph = new ArrayList<ArrayList<Integer>>();

        for (int i = 0; i <= n; ++i) {
            graph.add(new ArrayList<Integer>());
        }
        
        for (int i = 1; i <= n-1; ++i) {
            st = new StringTokenizer(br.readLine());
            int first = Integer.parseInt(st.nextToken());
            int second = Integer.parseInt(st.nextToken());

            graph.get(first).add(second);
            graph.get(second).add(first);
        }
    }
}
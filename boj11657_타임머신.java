import java.io.*;
import java.util.*;
import java.math.*;

public class boj11657_타임머신 {
    static int n, m;
    static ArrayList<Triple> edges;
    static boolean[] visited;
    static long[] distTable;

    public static class Triple {
        public int first;
        public int second;
        public int third;

        public Triple(int _first, int _second, int _third) {
            first = _first;
            second = _second;
            third = _third;
        }
    }

    public static void main(String[] args) throws IOException {
        initArgs();
        if (bfs()) {
            System.out.println(-1);
            return;
        }
        for (int i = 2; i <= n; ++i) {
            if (distTable[i] == Long.MAX_VALUE) {
                System.out.println(-1);
            } else {
                System.out.println(distTable[i]);
            }
        }
    }

    public static boolean bfs() {
        distTable[1] = 0;

        for (int i = 0; i < n; ++i) {
            for (int j = 1; j <= m; ++j) {
                int curr = edges.get(j).first;
                int next = edges.get(j).second;
                int cost = edges.get(j).third;

                if (distTable[curr] != Long.MAX_VALUE && distTable[next] > distTable[curr] + cost)
                    distTable[next] = distTable[curr] + cost;
            }
        }

        for (int j = 1; j <= m; ++j) {
            int curr = edges.get(j).first;
            int next = edges.get(j).second;
            int cost = edges.get(j).third;

            if (distTable[curr] != Long.MAX_VALUE && distTable[next] > distTable[curr] + cost)
                return true;
        }
        return false;
    }

    public static void initArgs() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        edges = new ArrayList<Triple>();
        visited = new boolean[n+1];
        distTable = new long[n+1];

        for (int i = 0; i <= n; ++i) {
            distTable[i] = Long.MAX_VALUE;
        }

        edges.add(new Triple(0, 0, 0));

        for (int i = 0; i < m; ++i) {
            st = new StringTokenizer(br.readLine());

            int first = Integer.parseInt(st.nextToken());
            int second = Integer.parseInt(st.nextToken());
            int third = Integer.parseInt(st.nextToken());

            edges.add(new Triple(first, second, third));
        }
    }
 
}
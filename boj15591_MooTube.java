import java.io.*;
import java.util.*;
import java.math.*;

// n, q = 1 <= n,q <= 5000
// weight = 1 <= w <= 1_000_000_000
// timeLimit = 2sec
// spaceLimit = 512mb
// timeComplex = O(n^2)
// spaceComplex = O(n^2)

public class boj15591_MooTube {
    static int n, q;
    static ArrayList<ArrayList<Pair>> graph;
    static ArrayList<Pair> questions;
    static boolean[] visited;
    static int ans = 0;

    public static void main(String[] args) throws IOException {
        initArgs();

        for (int i = 0; i < questions.size(); ++i) {
            Pair question = questions.get(i);
            int k = question.first;
            int start = question.second;
            visited = new boolean[n+1];
            ans = 0;
            
            dfs(start, k, Integer.MAX_VALUE);
            System.out.println(ans);
        }
    }

    public static void dfs(int start, int k, int usado) {
        if (visited[start]) {
            return ;
        }
        visited[start] = true;
        for (Pair node: graph.get(start)) {
            int nextNode = node.first;
            int nextUsado = Math.min(node.second, usado);
            if (nextUsado >= k && !visited[nextNode]) {
                ++ans;
            }
            dfs(nextNode, k, Math.min(usado, nextUsado));
        }
        return ;
    }

    public static class Pair {
        public int first;
        public int second;

        public Pair(int _first, int _second) {
            first = _first;
            second = _second;
        }
    }

    public static void initArgs() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());

        graph = new ArrayList<ArrayList<Pair>>();
        for (int i = 0; i <= n; ++i) {
            graph.add(new ArrayList<Pair>());
        }

        for (int i = 0; i < n-1; ++i) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            graph.get(x).add(new Pair(y, w));
            graph.get(y).add(new Pair(x, w));
        }
        
        questions = new ArrayList();
        for (int i = 0; i < q; ++i) {
            st = new StringTokenizer(br.readLine());

            questions.add(new Pair(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        visited = new boolean[n+1];
    }
}
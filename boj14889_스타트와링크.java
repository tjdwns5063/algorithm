import java.io.*;
import java.util.*;
import java.math.*;

public class boj14889_스타트와링크 {
    static int n;
    static int[][] board;
    static boolean[] visited;
    static ArrayList<Integer> comb;
    static int ans = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        initArgs();
        dfs(0);
        System.out.println(ans);
    }

    public static void dfs(int depth) {
        if (depth >= n / 2) {
            solve();
            return ;
        }

        for (int i = 0; i < n; ++i) {
            if (visited[i])
                continue ;
            comb.add(i);
            visited[i] = true;
            dfs(depth + 1);
            comb.remove(comb.size() - 1);
            for (int j = i+1; j < n; ++j)
                visited[j] = false;
        }
    }

    public static void solve() {
        ArrayList<Integer> others = new ArrayList();
        int startScore = 0;
        int linkScore = 0;

        for (int i = 0; i < n; ++i) {
            if (!comb.contains(i))
                others.add(i);
        }

        for (int i = 0; i < comb.size(); ++i) {
            for (int j = i+1; j < comb.size(); ++j) {
                startScore += board[comb.get(i)][comb.get(j)];
                startScore += board[comb.get(j)][comb.get(i)];
            }
        }

        for (int i = 0; i < others.size(); ++i) {
            for (int j = i+1; j < others.size(); ++j) {
                linkScore += board[others.get(i)][others.get(j)];
                linkScore += board[others.get(j)][others.get(i)];
            }
        }
        ans = Math.min(ans, Math.abs(startScore - linkScore));
    }

    public static void initArgs() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        
        board = new int[n][n];
        for (int i = 0; i < n; ++i) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; ++j) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        visited = new boolean[n];
        comb = new ArrayList();
    }
}
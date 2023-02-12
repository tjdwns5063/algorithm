import java.io.*;
import java.util.*;
import java.math.*;

class boj15686_치킨배달 {
    static int n;
    static int m;
    static int[][] board;
    static boolean[] visited;
    static ArrayList<Coord> homes;
    static ArrayList<Coord> stores;
    static ArrayList<Integer> comb;
    static int ans;

    public static class Coord {
        public int x;
        public int y;

        public Coord(int _x, int _y) {
            x = _x;
            y = _y;
        }
    }

    public static void initArgs() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        board = new int[n][n];

        for (int i = 0; i < n; ++i) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; ++j) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        homes = new ArrayList();
        stores = new ArrayList();
        comb = new ArrayList();

        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (board[i][j] == 1)
                    homes.add(new Coord(i, j));
                if (board[i][j] == 2)
                    stores.add(new Coord(i, j));
            }
        }

        visited = new boolean[stores.size()];
        ans = Integer.MAX_VALUE;
    }

    public static void solve() {
        int chickenDist = 0;

        for (int i = 0; i < homes.size(); ++i) {
            int minDist = Integer.MAX_VALUE;
            for (int j = 0; j < comb.size(); ++j) {
                int idx = comb.get(j);
                int dist = Math.abs(homes.get(i).x - stores.get(idx).x) + Math.abs(homes.get(i).y - stores.get(idx).y);
                minDist = Math.min(minDist, dist);
            }
            chickenDist += minDist;
        }
        ans = Math.min(chickenDist, ans);
    }

    public static void dfs(int depth) {
        if (depth >= m) {
            // System.out.println(comb);
            solve();
            return ;
        }

        for (int i = 0; i < stores.size(); ++i) {
            if (visited[i]) {
                continue;
            }
            comb.add(i);
            visited[i] = true;
            dfs(depth + 1);
            comb.remove(comb.size() - 1);
            for (int j = i+1; j < stores.size(); ++j) {
                visited[j] = false;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        initArgs();
        dfs(0);
        System.out.println(ans);
    }
}
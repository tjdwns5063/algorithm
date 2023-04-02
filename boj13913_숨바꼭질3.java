import java.io.*;
import java.math.*;
import java.util.*;

// time = 2sec
// space = 512mb
// 0 <= n <= 100,000
// 0 <= k <= 100,000
// timeComplex = O(n*k)

public class boj13913_숨바꼭질3 {
    static int[] board = new int[200001];
    static int n, k;
    static ArrayList<Integer> route = new ArrayList();

    public static void main(String[] args) throws IOException {
        initArgs();
        solve();
    }

    private static void solve() {
        findShortest();
        findRoute(k);

        StringBuilder routeString = new StringBuilder();

        for (int i = route.size() - 1; i >= 0; --i) {
            routeString.append(route.get(i) + " ");
        }
        System.out.println(routeString);
    }

    private static void findShortest() {
        Queue<Integer> queue = new LinkedList<Integer>();

        queue.add(n);
        board[n] = 1;

        while (!queue.isEmpty()) {
            int curr = queue.poll();

            if (curr == k) {
                System.out.println(board[k] - 1);
                return ;
            }
            
            int[] dirs = {-1, 1, curr};
            for (int i = 0; i < 3; ++i) {
                int next = curr + dirs[i];

                if (next < 0 || next > 200000 || board[next] != 0)
                    continue ;

                board[next] = board[curr] + 1;
                queue.add(next);
            }
        }
    }

    private static void findRoute(int curr) {
        Stack<Integer> stk = new Stack<Integer>();
        stk.add(curr);
        
        while (!stk.isEmpty()) {
            int currIdx = stk.pop();

            route.add(currIdx);
            if (currIdx == n) {
                return ;
            }

            int[] dirs = {1, -1, (currIdx % 2 == 0) ? -(currIdx / 2) : -(currIdx * 2)};
            for (int i = 0; i < 3; ++i) {
                int next = currIdx + dirs[i];

                if (next < 0 || next > 200000 || board[currIdx] - 1 != board[next])
                    continue ;
                stk.add(next);
            }
        }
    }

    private static void initArgs() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
    }
}
import java.io.*;
import java.util.*;
import java.math.*;

public class boj14890_경사로 {
    static int n, L;
    static int[][] board;
    static int[][] constructed;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int ans = 0;

    public static void main(String[] args) throws IOException {
        initArgs();
        solve();
        System.out.println(ans);
    }

    public static void printBoard() {
         for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                System.out.print(constructed[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void solve() {
        for (int i = 0; i < n; ++i) {
            ArrayList<Integer> road = new ArrayList();
            int[] tempConstructed = new int[n];
    
            for (int j = 0; j < n; ++j) {
                road.add(board[i][j]);
                tempConstructed[j] = constructed[i][j];
            }
            for (int k = 0; k < n; ++k) {
                if (tempConstructed[k] != 0)
                    continue ;
                constructRoadRight(road, k, tempConstructed);
            }
            for (int k = n-1; k >= 0; --k) {
                if (tempConstructed[k] != 0)
                    continue ;
                constructRoadLeft(road, k, tempConstructed);
            }
            if (checkIsPass(road, tempConstructed)) {
                ++ans;
            }
        }

        for (int j = 0; j < n; ++j) {
            ArrayList<Integer> road = new ArrayList();
            int[] tempConstructed = new int[n];

            for (int i = 0; i < n; ++i) {
                road.add(board[i][j]);
            }
            for (int k = 0; k < n; ++k) {
                if (tempConstructed[k] != 0)
                    continue ;
                constructRoadRight(road, k, tempConstructed);
            }
            
            for (int k = n-1; k >= 0; --k) {
                if (tempConstructed[k] != 0)
                    continue ;
                constructRoadLeft(road, k, tempConstructed);
            }
            if (checkIsPass(road, tempConstructed)) {
                ++ans;
            }
        }
    }

    public static void constructRoadRight(ArrayList<Integer> road, int start, int[] tempConstructed) {
        int cnt = 0;
        int curr = start;

        while (curr < n && cnt < L) {
            if (road.get(start) != road.get(curr))
                return ;
            ++curr;
            ++cnt;
        }
        if (cnt == L && curr < n) {
            if (road.get(curr) - road.get(start) == 1) {
                for (int i = 0; i < L; ++i) {
                    tempConstructed[start + i] = 1;
                }
            }
        }
    }

    public static void constructRoadLeft(ArrayList<Integer> road, int start, int[] tempConstructed) {
        int cnt = 0;
        int curr = start;

        while (curr >= 0 && cnt < L) {
            if (road.get(start) != road.get(curr))
                return ;
            --curr;
            ++cnt;
        }
        if (cnt == L && curr >= 0) {
            if (road.get(curr) - road.get(start) == 1) {
                for (int i = 0; i < L; ++i) {
                    tempConstructed[start - i] = -1;
                }
            }
        }
    }

    public static boolean checkIsPass(ArrayList<Integer> road, int[] tempConstructed) {
        int stand = road.get(0);
        int idx = 0;
        while (idx < road.size()) {
            if (tempConstructed[idx] != 0) {
                stand += tempConstructed[idx];
                idx += L;
            } else {
                if (stand != road.get(idx))
                    return false;
                ++idx;
            }
        }
        return true;
    }

    public static void printConstructed(int[] tempConstructed) {
        for (int i = 0; i < n; ++i) {
            System.out.print(tempConstructed[i] + " ");
        }
        System.out.println();
    }
    
    public static void initArgs() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        board = new int[n][n];
        constructed = new int[n][n];
        for (int i = 0; i < n; ++i) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; ++j) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }
}
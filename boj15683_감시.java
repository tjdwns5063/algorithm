import java.math.*;
import java.util.*;
import java.io.*;

public class boj15683_감시 {
    static int n;
    static int m;
    static int[][] board;
    static ArrayList<Coord> cameraCoord;
    static ArrayList<Integer> dirs;
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
        board = new int[n][m];

        for (int i = 0; i < n; ++i) {
            st = new StringTokenizer(br.readLine());;
            for (int j = 0; j < m; ++j) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        dirs = new ArrayList();
        cameraCoord = new ArrayList<Coord>();
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                if (board[i][j] != 0 && board[i][j] != 6)
                    cameraCoord.add(new Coord(i, j));
            }
        }
        ans = Integer.MAX_VALUE;
    }

    public static void seeLeft(int[][] copy, Coord coord) {
        for (int j = coord.y; j >= 0; --j) {
            if (copy[coord.x][j] == 6)
                break;
            if (copy[coord.x][j] == 0)
                copy[coord.x][j] = 7;
        }
    }

    public static void seeRight(int[][] copy, Coord coord) {
        for (int j = coord.y; j < m; ++j) {
            if (copy[coord.x][j] == 6)
                break;
            if (copy[coord.x][j] == 0)
                copy[coord.x][j] = 7;
        }
    }

    public static void seeUp(int[][] copy, Coord coord) {
        for (int i = coord.x; i >= 0; --i) {
            if (copy[i][coord.y] == 6)
                break ;
            if (copy[i][coord.y] == 0)
                copy[i][coord.y] = 7;
        }
    }

    public static void seeDown(int[][] copy, Coord coord) {
        for (int i = coord.x; i < n; ++i) {
            if (copy[i][coord.y] == 6)
                break ;
            if (copy[i][coord.y] == 0)
                copy[i][coord.y] = 7;
        }
    }

    public static void camera1(int[][] copy, Coord coord, int dir) {
        if (dir == 0) {
            seeRight(copy, coord);
        } else if (dir == 1) {
            seeDown(copy, coord);
        } else if (dir == 2) {
            seeLeft(copy, coord);
        } else if (dir == 3) {
            seeUp(copy, coord);
        }
    }

    public static void camera2(int[][] copy, Coord coord, int dir) {
        if (dir == 0 || dir == 2) {
            seeRight(copy, coord);
            seeLeft(copy, coord);
        } else {
            seeUp(copy, coord);
            seeDown(copy, coord);
        }
    }

    public static void camera3(int[][] copy, Coord coord, int dir) {
        if (dir == 0) {
            seeRight(copy, coord);
            seeUp(copy, coord);
        } else if (dir == 1) {
            seeRight(copy, coord);
            seeDown(copy, coord);
        } else if (dir == 2) {
            seeDown(copy, coord);
            seeLeft(copy, coord);
        } else {
            seeLeft(copy, coord);
            seeUp(copy, coord);
        }
    }

    public static void camera4(int[][] copy, Coord coord, int dir) {
        if (dir == 0) {
            seeRight(copy, coord);
            seeUp(copy, coord);
            seeLeft(copy, coord);
        } else if (dir == 1) {
            seeRight(copy, coord);
            seeUp(copy,coord);
            seeDown(copy, coord);
        } else if (dir == 2) {
            seeRight(copy, coord);
            seeLeft(copy, coord);
            seeDown(copy, coord);
        } else {
            seeUp(copy, coord);
            seeDown(copy, coord);
            seeLeft(copy, coord);
        }
    }

    public static void camera5(int[][] copy, Coord coord) {
        seeUp(copy, coord);
        seeDown(copy, coord);
        seeLeft(copy, coord);
        seeRight(copy, coord);
    }

    public static void solve() {
        int[][] copy = new int[n][m];

        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                copy[i][j] = board[i][j];
            }
        }

        for (int i = 0; i < cameraCoord.size(); ++i) {
            int cameraNumber = board[cameraCoord.get(i).x][cameraCoord.get(i).y];

            if (cameraNumber == 1) {
                camera1(copy, cameraCoord.get(i), dirs.get(i));
            } else if (cameraNumber == 2) {
                camera2(copy, cameraCoord.get(i), dirs.get(i));
            } else if (cameraNumber == 3) {
                camera3(copy, cameraCoord.get(i), dirs.get(i));
            } else if (cameraNumber == 4) {
                camera4(copy, cameraCoord.get(i), dirs.get(i));
            } else {
                camera5(copy, cameraCoord.get(i));
            }
        }

        int cnt = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                if (copy[i][j] == 0)
                    ++cnt;
            }
        }
        ans = Math.min(ans, cnt);
    }

    public static void dfs(int depth) {
        if (depth >= cameraCoord.size()) {
            // System.out.println(dirs);
            solve();
            return ;
        }
        for (int i = 0; i < 4; ++i) {
            dirs.add(i);
            dfs(depth + 1);
            dirs.remove(dirs.size() - 1);
        }
    }

    public static void main(String[] args) throws IOException {
        initArgs();
        dfs(0);
        System.out.println(ans);
    }
}
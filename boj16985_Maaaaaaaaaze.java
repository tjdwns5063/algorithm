import java.io.*;
import java.util.*;
import java.math.*;

public class boj16985_Maaaaaaaaaze {
    static int[][][] maze;
    static ArrayList<Board> boards;
    static ArrayList<Integer> rotates;
    static ArrayList<Integer> orders;
    static boolean[] visited; 
    static int[] dz = {1, 0, -1, 0, 0, 0};
    static int[] dx = {0, 1, 0, -1, 0, 0};
    static int[] dy = {0, 0, 0, 0, 1, -1};
    static int ans = Integer.MAX_VALUE;

    public static class Coord {
        public int x;
        public int y;
        public int z;

        public Coord(int _z, int _x, int _y) {
            z = _z;
            x = _x;
            y = _y;
        }
    }

    public static class Board {
        public int[][] board;

        public Board() { board = new int[5][5]; }

        public Board(int[][] _board) {
            board = _board;
        }

        public void rotateBoard() {
            int[][] newBoard = new int[5][5];
            int hIdx = 0;
            for (int j = 4; j >= 0; --j) {
                int wIdx = 0;
                for (int i = 0; i < 5; ++i) {
                    newBoard[i][j] = board[hIdx][wIdx];
                    ++wIdx;
                }
                ++hIdx;
            }
            board = newBoard;
        }

        public void copyBoard(Board src) {
            for (int i = 0; i < 5; ++i) {
                for (int j = 0; j < 5;  ++j) {
                    board[i][j] = src.board[i][j];
                }
            }
        }
    }

    public static void dfsOrders(int depth) {
        if (depth >= 5) {
            dfsRotates(0);
            // System.out.println(orders);
            return ;
        }
        for (int i = 0; i < 5; ++i) {
            if (visited[i])
                continue ;
            orders.add(i);
            visited[i] = true;
            dfsOrders(depth + 1);
            visited[i] = false;
            orders.remove(orders.size()-1);
        }
    }

    public static void dfsRotates(int depth) {
        if (depth >= 5) {
            solve();
            return ;
        }

        for (int i = 0; i < 4; ++i) {
            rotates.add(i);
            dfsRotates(depth + 1);
            rotates.remove(rotates.size() - 1);
        }
    }

    public static void solve() {
        for (int i = 0; i < 5; ++i) {
            Board board = new Board();
            Board copySrc = boards.get(orders.get(i));
            board.copyBoard(copySrc);
            for (int j = 0; j < rotates.get(i); ++j) {
                board.rotateBoard();
            }
            for (int x = 0; x < 5;  ++x) {
                for (int y = 0; y < 5;  ++ y) {
                    maze[i][x][y] = board.board[x][y];
                }
            }
        }

        ans = Math.min(bfs(0,0,0), ans);
        ans = Math.min(bfs(0,0,4), ans);
        ans = Math.min(bfs(0,4,0), ans);
        ans = Math.min(bfs(0,4,4), ans);
    }

    public static int bfs(int z, int x, int y) {
        if (maze[z][x][y] == 0)
            return 126;
        int endX = 0;
        int endY = 0;
        int endZ = 0;

        if (z == 0)
            endZ = 4;
        if (y == 0)
            endY = 4;
        if (x == 0)
            endX = 4;
        if (maze[endZ][endX][endY] == 0)
            return 126;
        int[][][] copy = new int[5][5][5];
        for (int k = 0; k < 5; ++k) {
            for (int i = 0; i < 5; ++i) {
                for (int j = 0; j < 5; ++j) {
                    copy[k][i][j] = maze[k][i][j];
                }
            }
        }
        LinkedList<Coord> q = new LinkedList<Coord>();
        q.add(new Coord(z,x,y));

        while (!q.isEmpty()) {
            Coord curr = q.poll();

            for (int i = 0; i < 6; ++i) {
                int nz = curr.z + dz[i];
                int nx = curr.x + dx[i];
                int ny = curr.y + dy[i];

                if (nz < 0 || nx < 0 || ny < 0 || nz >= 5 || nx >= 5 || ny >= 5)
                    continue ;
                if (copy[nz][nx][ny] == 0 || copy[nz][nx][ny] > 1)
                    continue ;
                q.add(new Coord(nz, nx, ny));
                copy[nz][nx][ny] = copy[curr.z][curr.x][curr.y] + 1;
            }
        }

        // System.out.println(copy[endZ][endX][endY]);
        if (copy[endZ][endX][endY] == 1)
            return 126;
        return copy[endZ][endX][endY];
    }

    public static void initArg() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        maze = new int[5][5][5];
        boards = new ArrayList();
        for (int i = 0; i < 5; ++i) {
            int[][] board = new int[5][5];
            for (int j = 0; j < 5; ++j) {
                st = new StringTokenizer(br.readLine());
                for (int k = 0; k < 5; ++k) {
                    board[j][k] = Integer.parseInt(st.nextToken());
                }
            }
            boards.add(new Board(board));
        }
        rotates = new ArrayList();
        orders = new ArrayList();
        visited = new boolean[5]; 
    }

    public static void printBoard(int[][] board) {
        for (int i = 0; i < 5; ++i) {
            for (int j = 0; j < 5; ++j) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) throws IOException {
        initArg();

        dfsOrders(0);
        if (ans == 126)
            System.out.println(-1);
        else
            System.out.println(ans-1);
    }
}
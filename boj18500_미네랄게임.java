// var = 1 <= r,c,n <= 100
// time = 1sec
// space = 512mb
// timeComp = o(r*c*n)

import java.io.*;
import java.util.*;
import java.math.*;

public class boj18500_미네랄게임 {
    static int row, col, n;
    static int[] sticks;
    static char[][] board;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        initArgs();
        mineral2();
        printBoard();
    }

    public static class Coord {
        public int x;
        public int y;

        public Coord(int _x, int _y) {
            x = _x;
            y = _y;
        }
    }

    public static void mineral2() {
        for (int i = 0; i < n; ++i) {
            ArrayList<PriorityQueue<Coord>> minerals = new ArrayList();
            int height = row - sticks[i];
            throwStick(i);
            boolean[][] visited = new boolean[row][col];
            for (int x = 0; x < row; ++x) {
                for (int y = 0; y < col; ++y) {
                    if (visited[x][y] || board[x][y] == '.')
                        continue ;
                    minerals.add(bfs(x, y, visited));
                }
            }
            // printBoard();
            if (minerals.size() >= 2) {
                fallMineral(height, minerals);
            }
        }
    }

    public static void fallMineral(int height, ArrayList<PriorityQueue<Coord>> minerals) {
        for (int i = 0; i < 2; ++i) {
            PriorityQueue<Coord> pQ = minerals.get(i);

            if (pQ.peek().x == row - 1)
                continue ;
            ArrayList<Coord> coords = new ArrayList();
            while (!pQ.isEmpty()) {
                coords.add(pQ.poll());
            }
            while (true) {
                if (!checkFall(coords))
                    break ;
                for (int j = 0; j < coords.size(); ++j) {
                    Coord coord = coords.get(j);

                    board[coord.x][coord.y] = '.';
                    coord.x += 1;
                    board[coord.x][coord.y] = 'x';
                }
                // printBoard();
                // System.out.println();
            }
        }
        // printBoard();
    }

    public static boolean checkFall(ArrayList<Coord> coords) {
        for (Coord coord: coords) {
            if (coord.x + 1 == row)
                return false;
            if (board[coord.x+1][coord.y] == 'x') {
                int cnt = 0;
                for (Coord comp: coords) {
                    if (comp.x == coord.x + 1 && comp.y == coord.y)
                        ++cnt;
                }
                if (cnt == 0)
                    return false;
            }
        }
        return true;
    }

    public static void printVisit(boolean[][] visited) {
        for (int i = 0; i < row; ++i) {
            for (int j = 0; j < col; ++j) {
                System.out.print(visited[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static PriorityQueue<Coord> bfs(int x, int y, boolean[][] visited) {
        PriorityQueue<Coord> mineral = new PriorityQueue<Coord>(new Comparator<Coord>() {
            @Override
            public int compare(Coord first, Coord second) {
                return second.x - first.x;
            }
        });
        LinkedList<Coord> q = new LinkedList<Coord>();
        q.add(new Coord(x, y));
        
        while (!q.isEmpty()) {
            Coord curr = q.poll();
            mineral.add(curr);

            visited[curr.x][curr.y] = true;
            for (int i = 0; i < 4; ++i) {
                int nx = curr.x + dx[i];
                int ny = curr.y + dy[i];

                if (nx < 0 || ny < 0 || nx >= row || ny >= col)
                    continue ;
                if (visited[nx][ny] || board[nx][ny] == '.')
                    continue ;
                visited[nx][ny] = true;
                q.add(new Coord(nx, ny));
            }
        }
        return mineral;
    }

    public static void throwStick(int i) {
        int height = row - sticks[i];

        if (i % 2 == 0) {
            for (int y = 0; y < col; ++y) {
                if (board[height][y] == 'x') {
                    board[height][y] = '.';
                    return ;
                }
            }
        } else {
            for (int y = col - 1; y >= 0; --y) {
                if (board[height][y] == 'x') {
                    board[height][y] = '.';
                    return ;
                }
            }
        }
        return ;
    }

    public static void printBoard() {
        for (int i = 0; i < row; ++i) {
            for (int j = 0; j < col; ++j) {
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
        // System.out.println();
    }

    public static void initArgs() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());
        board = new char[row][col];

        for (int i = 0; i < row; ++i) {
            String s = br.readLine();
            for (int j = 0; j < col; ++j) {
                board[i][j] = s.charAt(j);
            }
        }
        n = Integer.parseInt(br.readLine());
        sticks = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; ++i) {
            sticks[i] = Integer.parseInt(st.nextToken());
        }
    }
}
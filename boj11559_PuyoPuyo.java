import java.io.*;
import java.util.*;
import java.math.*;

public class boj11559_PuyoPuyo {
    static char[][] board;
    static int width = 6;
    static int height = 12;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static boolean[][] visited;

    public static class Coord {
        public int x;
        public int y;

        public Coord(int _x, int _y) {
            x = _x;
            y = _y;
        }
    }

    public static void initArg() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        board = new char[height][width];
        for (int i = 0; i < height; ++i) {
            String input = br.readLine();
            for (int j = 0; j < width; ++j) {
                board[i][j] = input.charAt(j);
            }
        }
        visited = new boolean[height][width];
        for (int i = 0; i < height; ++i) {
            for (int j = 0; j <width; ++j) {
                visited[i][j] = false;
            }
        }
    }

    public static int bfs(int x, int y) {
        ArrayList<Coord> temp = new ArrayList();
        LinkedList<Coord> q = new LinkedList();

        q.add(new Coord(x, y));
        temp.add(new Coord(x, y));
        while (!q.isEmpty()) {
            Coord curr = q.poll();

            for (int i = 0; i < 4; ++i) {
                int nx = curr.x + dx[i];
                int ny = curr.y + dy[i];

                if (nx < 0 || ny < 0 || nx >= height || ny >= width)
                    continue ;
                if (visited[nx][ny] || board[nx][ny] != board[curr.x][curr.y])
                    continue ;
                visited[nx][ny] = true;
                q.add(new Coord(nx, ny));
                temp.add(new Coord(nx, ny));
            }
        }
        if (temp.size() > 4) {
            for (int i = 0; i < temp.size(); ++i) {
                Coord curr = temp.get(i);

                board[curr.x][curr.y] = '.';
            }
            return temp.size();
        }
        return 0;
    }

    public static void printBoard() {
        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < width; ++j) {
                System.out.print(board[i][j]);
                System.out.print(' ');
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void fallPuyo() {
        for (int i = height-1; i >= 0; --i) {
            for (int j = 0; j < width; ++j) {
                if (board[i][j] != '.') {
                    int currX = i;
                    int currY = j;
                    if (currX < height - 1) {
                        int nextX = i + 1;
                        char puyo = board[currX][currY];
                        while (nextX < height && board[nextX][currY] == '.') {
                            board[nextX][currY] = puyo;
                            board[nextX-1][currY] = '.';
                            ++nextX;
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        initArg();
        int ans = 0;
        while (true) {
            int cnt = 0;
            visited = new boolean[height][width];
            for (int i = 0; i < height; ++i) {
                for (int j = 0; j < width; ++j) {
                    if (visited[i][j] || board[i][j] == '.')
                        continue ;
                    cnt += bfs(i, j);
                }
            }
            if (cnt == 0)
                break ;
            // printBoard();
            fallPuyo();
            ++ans;
        }
        System.out.println(ans);
    }
}
import java.io.*;
import java.math.*;
import java.util.*;

public class boj16954_움직이는미로탈출 {
    static char[][][] board = new char[65][8][8];
    static boolean[][][] visited = new boolean[65][8][8];
    static int[] dx = {1, 0, -1, 0, 1, 1, -1, -1, 0};
    static int[] dy = {0, 1, 0, -1, -1, 1, -1, 1, 0};

    static class Pair {
        public int x;
        public int y;
        public int phase;

        public Pair(int _x, int _y, int _phase) {
            x = _x;
            y = _y;
            phase = _phase;
        }
    }
    
    public static void main(String[] args) throws IOException {
        initArgs();
        fallWalls();
        bfs();
    }

    private static void initArgs() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 8; ++i) {
            String line = br.readLine();
            for (int j = 0; j < 8; ++j) {
                board[0][i][j] = line.charAt(j);
            }
        }
    }

    private static void fallWalls() {
        for (int k = 1; k < 65; ++k) {
            for (int i = 7; i >= 0; --i) {
                for (int j = 0; j < 8; ++j) {
                    board[k][i][j] = board[k-1][i][j];
                    if (board[k][i][j] == '#') {
                        board[k][i][j] = '.';
                        if (i == 7)
                            continue ;
                        board[k][i+1][j] = '#';
                    }
                }
            }
        }
    }

    private static void bfs() {
        Queue<Pair> queue = new LinkedList<Pair>();

        queue.add(new Pair(7, 0, 0));
        
        while (!queue.isEmpty()) {
            Pair curr = queue.poll();

            if (curr.x == 0 && curr.y == 7) {
                System.out.println(1);
                return ;
            }

            for (int i = 0; i < 9; ++i) {
                int nx = curr.x + dx[i];
                int ny = curr.y + dy[i];
                int nphase = (curr.phase >= 64) ? curr.phase : curr.phase + 1;

                if (nx < 0 || ny < 0 || nx >= 8 || ny >= 8)
                    continue ;
                if (board[curr.phase][nx][ny] != '.')
                    continue ;
                if (board[nphase][nx][ny] != '.' || visited[nphase][nx][ny])
                    continue ;
                queue.add(new Pair(nx, ny, nphase));
                visited[nphase][nx][ny] = true;
            }
        }
        System.out.println(0);
    }
}
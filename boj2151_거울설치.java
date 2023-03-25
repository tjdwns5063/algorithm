//time = 2sec
//space = 128mb
//n = 2 <= n <= 50
//timeComp = O(n^2)

import java.io.*;
import java.util.*;
import java.math.*;

public class boj2151_거울설치 {
    static int n;
    static char[][] board;
    static boolean[][] visited;
    static LinkedList<Coord> queue;
    static int dir = 0;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    
    public static class Coord {
        public int x;
        public int y;
        public int cnt;

        public Coord(int _x, int _y, int _cnt) {
            x = _x;
            y = _y;
            cnt = _cnt;
        }
    }

    public static void main(String[] args) throws IOException {
        initArgs();
        // printBoard();

        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (board[i][j] == '#') {
                    queue.add(new Coord(i, j, 0));
                    break;
                }
            }
            if (queue.size() != 0)
                break;
        }        
        bfs();
    }

    public static void bfs() {
        int ans = Integer.MAX_VALUE;
        while (!queue.isEmpty()) {
            Coord curr = queue.poll();
            visited[curr.x][curr.y] = true;
            if (goToStraight(curr) == 1) {
                // System.out.println("hit");
                // System.out.println(curr.cnt);
                ans = Math.min(ans, curr.cnt);
            }
        }
        System.out.println(ans);
    }

    public static int goToStraight(Coord curr) {
        
        
        for (int dir = 0; dir < 4; ++dir) {
            int nx = curr.x;
            int ny = curr.y;
            for (int i = 0; i < n; ++i) {
                nx += dx[dir];
                ny += dy[dir];

                if (nx < 0 || ny < 0 || nx >= n || ny >= n) {
                    continue ;
                }
                if (board[nx][ny] == '#' && !visited[nx][ny])
                    return 1;
                if (board[nx][ny] == '*')
                    break ;
                if (visited[nx][ny] || board[nx][ny] != '!')
                    continue;
                
                queue.add(new Coord(nx, ny, curr.cnt + 1));
                visited[nx][ny] = true;
            }
        }
        
        return 0;
    }

    public static void printBoard() {
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void initArgs() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        board = new char[n][n];
        visited = new boolean[n][n];
        queue = new LinkedList<Coord>();
        for (int i = 0; i < n; ++i) {
            String s = br.readLine();
            for (int j = 0;  j < n; ++j) {
                board[i][j] = s.charAt(j);
            }
        }
    }
}
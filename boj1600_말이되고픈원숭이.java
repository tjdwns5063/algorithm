import java.io.*;
import java.util.*;
import java.math.*;

public class boj1600_말이되고픈원숭이 {
    static int[] horse_dx = {2, 2, -2, -2, 1, 1, -1, -1};
    static int[] horse_dy = {1, -1, 1, -1, 2, -2, 2, -2};
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int[][] map;
    static int[][][] visited;
    static int row, col;
    static int cnt;

    static class Locate {
        public int x;
        public int y;
        public int cnt;

        public Locate(int _x, int _y, int _cnt) {
            x = _x;
            y = _y;
            cnt = _cnt;
        }
    }

    public static void main(String[] args) throws IOException {
        initArgs();
        bfs();
    }

    public static void bfs() {
        Queue<Locate> queue = new LinkedList<Locate>();

        queue.add(new Locate(0, 0, cnt));

        for (int k = 0; k <= cnt; ++k)
            visited[k][0][0] = 1;

        while (!queue.isEmpty()) {
            Locate curr = queue.poll();

            if (curr.cnt > 0) {
                for (int i = 0; i < 8; ++i) {
                    int nx = curr.x + horse_dx[i];
                    int ny = curr.y + horse_dy[i];
                    int ncnt = curr.cnt - 1;

                    if (nx < 0 || ny < 0 || nx >= row || ny >= col)
                        continue ;
                    if (map[nx][ny] != 0 || visited[ncnt][nx][ny] != 0)
                        continue ;
                    queue.add(new Locate(nx, ny, ncnt));
                    visited[ncnt][nx][ny] = visited[curr.cnt][curr.x][curr.y] + 1;
                }
            }
            for (int i = 0; i < 4; ++i) {
                int nx = curr.x + dx[i];
                int ny = curr.y + dy[i];

                if (nx < 0 || ny < 0 || nx >= row || ny >= col)
                    continue ;
                if (map[nx][ny] != 0 || visited[curr.cnt][nx][ny] != 0)
                    continue ;
                queue.add(new Locate(nx, ny, curr.cnt));
                visited[curr.cnt][nx][ny] = visited[curr.cnt][curr.x][curr.y] + 1;
            }
        }

        int ans = Integer.MAX_VALUE;

        for (int k = 0; k <= cnt; ++k) {
            if (visited[k][row-1][col-1] == 0)
                continue ;
            ans = Math.min(visited[k][row-1][col-1], ans);
        }
        if (ans == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(ans-1);
        }
    }

    private static void initArgs() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        cnt = Integer.parseInt(br.readLine());
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        col = Integer.parseInt(st.nextToken());
        row = Integer.parseInt(st.nextToken());

        map = new int[row][col];
        visited = new int[cnt + 1][row][col];

        for (int i = 0; i < row; ++i) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < col; ++j) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    private static void printVisited(int k) {
        for (int i = 0; i < row; ++i) {
            for (int j = 0; j < col; ++j) {
                System.out.print(visited[k][i][j] + " ");
            }
            System.out.println();
        }
    }
}
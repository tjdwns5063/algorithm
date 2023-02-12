import java.io.*;
import java.util.*;
import java.math.*;

public class boj14499_주사위굴리기 {
    static int n, m, x, y, k;
    static int[][] board;
    static int[] orders;
    static int[] dice;
    static int[] diceNum;

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
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        board = new int[n][m];
        for (int i = 0; i < n; ++i) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; ++j) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        orders = new int[k];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < k; ++i) {
            orders[i] = Integer.parseInt(st.nextToken());
        }
        dice = new int[7];
        for (int i = 0; i < 7; ++i) {
            dice[i] = i;
        }
        diceNum = new int[7];
        for (int i = 0; i < 7; ++i) {
            diceNum[i] = 0;
        }
    }

    public static void roleNorth(Coord coord) {
        int up = dice[1];
        int down = dice[6];
        int front = dice[2];
        int back = dice[5];

        int nx = coord.x-1;
        int ny = coord.y;

        if (nx < 0)
            return ;

        dice[2] = up;
        dice[1] = back;
        dice[5] = down;
        dice[6] = front;
        System.out.println(diceNum[dice[1]]);

        if (board[nx][ny] == 0) {
            board[nx][ny] = diceNum[dice[6]];
        } else {
            diceNum[dice[6]] = board[nx][ny];
            board[nx][ny] = 0;
        }
        coord.x = nx;
        coord.y = ny;
        return ;
    }

    public static void roleSouth(Coord coord) {
        int up = dice[1];
        int down = dice[6];
        int front = dice[2];
        int back = dice[5];
        int nx = coord.x+1;
        int ny = coord.y;

        if (nx >= n)
            return ;

        dice[2] = down;
        dice[6] = back;
        dice[5] = up;
        dice[1] = front;
        System.out.println(diceNum[dice[1]]);


        if (board[nx][ny] == 0) {
            board[nx][ny] = diceNum[dice[6]];
        } else {
            diceNum[dice[6]] = board[nx][ny];
            board[nx][ny] = 0;
        }
        coord.x = nx;
        coord.y = ny;
    }

    public static void roleEast(Coord coord) {
        int up = dice[1];
        int east = dice[3];
        int west = dice[4];
        int down = dice[6];
        int nx = coord.x;
        int ny = coord.y+1;

        if (ny >= m)
            return ;

        dice[1] = west;
        dice[4] = down;
        dice[6] = east;
        dice[3] = up;

        System.out.println(diceNum[dice[1]]);

        if (board[nx][ny] == 0) {
            board[nx][ny] = diceNum[dice[6]];
        } else {
            diceNum[dice[6]] = board[nx][ny];
            board[nx][ny] = 0;
        }
        coord.x = nx;
        coord.y = ny;
    }

    public static void roleWest(Coord coord) {
        int up = dice[1];
        int east = dice[3];
        int west= dice[4];
        int down = dice[6];
        int nx = coord.x;
        int ny = coord.y-1;

        if (ny < 0)
            return ;

        dice[1] = east;
        dice[3] = down;
        dice[6] = west;
        dice[4] = up;
        System.out.println(diceNum[dice[1]]);

        if (board[nx][ny] == 0) {
            board[nx][ny] = diceNum[dice[6]];
        } else {
            diceNum[dice[6]] = board[nx][ny];
            board[nx][ny] = 0;
        }
        coord.x = nx;
        coord.y = ny;
    }

    public static void solve() {
        Coord currCoord = new Coord(x, y);
        for (int i = 0; i < k; ++i) {
            if (orders[i] == 1) {
                roleEast(currCoord);
            } else if (orders[i] == 2) {
                roleWest(currCoord);
            } else if (orders[i] == 3) {
                roleNorth(currCoord);
            } else {
                roleSouth(currCoord);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        initArgs();
        solve();
    }
}
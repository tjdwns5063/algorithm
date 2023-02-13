import java.io.*;
import java.util.*;
import java.math.*;

public class boj14503_로봇청소기 {
    static int n, m, x, y, dir;
    static int[][] board;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int ans = 0;

    public static void main(String[] args) throws IOException {
        initArgs();
        cleanUp();
    }

    public static void cleanUp() {
        while (true) {
            cleanOneStep();
            if (!checkPossibleClean()) {
                if (!cleanTwoStep()) {
                    break ;
                }
            } else {
                cleanThreeStep();
            }
        }
        System.out.println(ans);
    }

    public static void cleanOneStep() {
        if (board[x][y] == 0) {
            board[x][y] = -1;
            ++ans;
        }
    }

    public static boolean cleanTwoStep() {
        if (!checkBack()) {
            return false;
        }
        goBack();
        return true;
    }

    public static void cleanThreeStep() {
        rotate();
        if (checkFront())
            goFront();
    }

    public static void rotate() {
        if (dir == 0){
            dir = 3;
            return ;
        }
        dir -= 1;
    }

    public static boolean checkPossibleClean() {
        int nx;
        int ny;
        
        for (int i = 0; i < 4; ++i) {
            nx = x + dx[i];
            ny = y + dy[i];
            
            if (board[nx][ny] == 0) {
                return true;
            }
        }
        return false;
    }

    public static boolean checkFront() {
        if (dir == 0) {
            return board[x-1][y] == 0;
        } else if (dir == 1) {
            return board[x][y+1] == 0;
        } else if (dir == 2) {
            return board[x+1][y] == 0;
        }
        return board[x][y-1] == 0;
    }

    public static boolean checkBack() {
        if (dir == 0) {
            return board[x+1][y] != 1;
        } else if (dir == 1) {
            return board[x][y-1] != 1;
        } else if (dir == 2) {
            return board[x-1][y] != 1;
        }
        return board[x][y+1] != 1;
    }

    public static void goFront() {
        if (dir == 0) {
            x -= 1;
            return ;
        } else if (dir == 1) {
            y += 1;
            return ;
        } else if (dir == 2) {
            x += 1;
            return ;
        }
        y -= 1;   
    }

    public static void goBack() {
        if (dir == 0) {
            x += 1;
            return ;
        } else if (dir == 1) {
            y -= 1;
            return ;
        } else if (dir == 2) {
            x -= 1;
            return ;
        }
        y += 1;
    }

    public static void printBoard() {
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void initArgs() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        
        st = new StringTokenizer(br.readLine());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        dir = Integer.parseInt(st.nextToken());

        board = new int[n][m];
        for (int i = 0; i < n; ++i) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; ++j) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }
}
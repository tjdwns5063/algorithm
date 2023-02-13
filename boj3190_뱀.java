import java.io.*;
import java.util.*;
import java.math.*;

public class boj3190_뱀 {
    static int n, k, dir, L;
    static int[][] board;
    static ArrayList<Order> orders;

    public static class Order {
        public int time;
        public char dir;

        public Order(int _time, char _dir) {
            time = _time;
            dir = _dir;
        }
    }

    public static void main(String[] args) throws IOException {
        initArgs();
        moveSnake();
    }

    public static void moveSnake() {
        int x = 0;
        int y = 0;

        while (true) {

        }
    }

    public static void initArgs() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        k = Integer.parseInt(br.readLine());
        
        board = new int[n][n];
        
        for (int i = 0; i < k; ++i) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            board[x][y] = -1;
        }
        board[0][0] = 1;
        L = Integer.parseInt(br.readLine());
        orders = new ArrayList();
        for (int i = 0; i < L; ++i) {
            st = new StringTokenizer(br.readLine());

            int time = Integer.parseInt(st.nextToken());
            char dir = st.nextToken().charAt(0);
            orders.add(new Order(time, dir));
        }
    }
}
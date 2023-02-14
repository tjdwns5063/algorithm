import java.io.*;
import java.util.*;
import java.math.*;

public class boj3190_뱀 {
    static int n, k, L;
    static int dir = 1; // 북:0 동:1 남:2 서:3
    static int x = 0;
    static int y = 0;
    static LinkedList<Coord> snake = new LinkedList();

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

    public static class Coord {
        public int x;
        public int y;

        public Coord(int _x, int _y) {
            x = _x;
            y = _y;
        }
    }

    public static void main(String[] args) throws IOException {
        initArgs();
        moveSnake();
    }

    public static void moveSnake() {
        snake.add(new Coord(x, y));
        board[x][y] = snake.size();
        int orderIdx = 0;
        int time = 1;

        while (true) {
            Order order = orders.get(orderIdx);
            if (!checkFront())
                break ;  
            spanBodyAndMove();
            if (!checkIsApple()) {
                Coord tail = snake.poll();
                board[tail.x][tail.y] = 0;
                board[x][y] = snake.size();
            } else {
                board[x][y] = snake.size();
            }
            if (order.time == time) {
                rotateDir(order);
                if (orderIdx < orders.size()-1)
                    ++orderIdx;
            }
            ++time;
            // printBoard();
        }
        System.out.println(time);
    }

    public static void rotateDir(Order order) {
        if (order.dir == 'L') {
            if (dir == 0) {
                dir = 3;
                return;
            }
            dir -= 1;
            return ;
        } else if (order.dir == 'D') {
            if (dir == 3) {
                dir = 0;
                return ;
            }
            dir += 1;
            return ;
        }
    }
    
    public static boolean checkFront() {
        if (dir == 0)
            return x - 1 >= 0 && board[x-1][y] <= 0;
        else if (dir == 1)
            return y + 1 < n && board[x][y+1] <= 0;
        else if (dir == 2)
            return x + 1 < n && board[x+1][y] <= 0;
        else
            return y - 1 >= 0 && board[x][y-1] <= 0;
    }

    public static void spanBodyAndMove() {
        if (dir == 0) {
            x-=1;
        } else if (dir == 1) {
            y+=1;
        } else if (dir == 2) {
            x+=1;
        } else {
            y-=1;
        }
        snake.add(new Coord(x, y));
    }

    public static boolean checkIsApple() {
        return board[x][y] == -1;
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
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        k = Integer.parseInt(br.readLine());
        
        board = new int[n][n];
        
        for (int i = 0; i < k; ++i) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            board[x-1][y-1] = -1;
        }
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
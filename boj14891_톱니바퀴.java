import java.io.*;
import java.util.*;
import java.math.*;

public class boj14891_톱니바퀴 {
    static int[][] wheels;
    static int k;
    static ArrayList<Order> orders;

    public static class Order {
        public int wheelId;
        public int dir;

        public Order(int _id, int _dir) {
            wheelId = _id;
            dir = _dir;
        }
    }

    public static void initArgs() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        wheels = new int[4][8];
        for (int i = 0; i < 4; ++i) {
            String input = br.readLine();
            for (int j = 0; j < 8; ++j) {
                wheels[i][j] = Character.getNumericValue(input.charAt(j));
            }
        }
        k = Integer.parseInt(br.readLine());
        orders = new ArrayList();
        for (int i = 0; i < k; ++i) {
            st = new StringTokenizer(br.readLine());
            int id = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken());
           
            orders.add(new Order(id-1, dir));
        }
    }

    public static void turnLeft(int[] wheel) {
        int temp;

        temp = wheel[0];
        for (int i = 1; i < 8; ++i) {
            wheel[i-1] = wheel[i];
        }
        wheel[7] = temp;
    }

    public static void turnRight(int[] wheel) {
        int temp;

        temp = wheel[7];
        for (int i = 6; i >= 0; --i) {
            wheel[i+1] = wheel[i];
        }
        wheel[0] = temp;
    }

    public static void turnWheel(int[][] wheels, int wheelId, int dir) {
        boolean[] checked = new boolean[4];
        for (int i = wheelId; i < 3; ++i) {
            if (wheels[i][2] != wheels[i+1][6]) {
                checked[i] = true;
                checked[i+1] = true;
            } else {
                break ;
            }
        }
        for (int i = wheelId; i >= 1; --i) {
            if (wheels[i][6] != wheels[i-1][2]) {
                checked[i] = true;
                checked[i-1] = true;
            } else {
                break ;
            }
        }
        if (dir == 1) {
            turnRight(wheels[wheelId]);
        } else {
            turnLeft(wheels[wheelId]);
        }
        int currDir = dir * -1;
        for (int i = wheelId+1; i < 4; ++i) {
            if (checked[i]) {
                if (currDir == 1) {
                    turnRight(wheels[i]);
                } else {
                    turnLeft(wheels[i]);
                }
                currDir *= -1;
            }
        }
        currDir = dir * -1;
        for (int i = wheelId-1; i >= 0; --i) {
            if (checked[i]) {
                if (currDir == 1) {
                    turnRight(wheels[i]);
                } else {
                    turnLeft(wheels[i]);
                }
                currDir *= -1;
            }
        }
    }

    public static int calculateScore() {
        int result = 0;
        int score = 1;
        for (int i = 0; i < 4; ++i) {
            if (wheels[i][0] == 1) {
                result += score;
            }
            score *= 2;
        }
        return result;
    }

    public static void solve() {
        for (int i = 0; i < k; ++i) {
            Order currOrder = orders.get(i);
            
            turnWheel(wheels, currOrder.wheelId, currOrder.dir);
        }

        int ans = calculateScore();
        System.out.println(ans);
    }

    public static void main(String[] args) throws IOException {
        initArgs();
        solve();
    }
}
import java.io.*;
import java.util.*;
import java.math.*;

public class boj14888_연산자끼워넣기 {
    static int n;
    static int[] numbers;
    static int[] operators;
    static ArrayList<Integer> permutation;
    static int minAns = Integer.MAX_VALUE;
    static int maxAns = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        initArgs();
        
        dfs(0);
        System.out.println(maxAns);
        System.out.println(minAns);
    }

    public static void dfs(int depth) {
        if (depth >= n-1) {
            eval();
            return ;
        }
        if (operators[0] > 0) {
            permutation.add(0);
            operators[0] -= 1;
            dfs(depth+1);
            operators[0] += 1;
            permutation.remove(permutation.size() - 1);
        } 
        if (operators[1] > 0) {
            permutation.add(1);
            operators[1] -= 1;
            dfs(depth+1);
            operators[1] += 1;
            permutation.remove(permutation.size() - 1);
        }
        if (operators[2] > 0) {
            permutation.add(2);
            operators[2] -= 1;
            dfs(depth + 1);
            permutation.remove(permutation.size() - 1);
            operators[2] += 1;
        }
        if (operators[3] > 0) {
            permutation.add(3);
            operators[3] -= 1;
            dfs(depth+1);
            permutation.remove(permutation.size() - 1);
            operators[3] += 1;
        }
    }

    public static void eval() {
        int ret = numbers[0];
        for (int i = 0; i < n-1; ++i) {
            int operator = permutation.get(i);

            if (operator == 0)
                ret = plus(ret, numbers[i+1]);
            else if (operator == 1)
                ret = minus(ret, numbers[i+1]);
            else if (operator == 2)
                ret = mul(ret, numbers[i+1]);
            else
                ret = div(ret, numbers[i+1]);
        }
        minAns = Math.min(ret, minAns);
        maxAns = Math.max(ret, maxAns);
    }

    public static int plus(int lhs, int rhs) {
        return lhs + rhs;
    }

    public static int minus(int lhs, int rhs) {
        return lhs - rhs;
    }

    public static int div(int lhs, int rhs) {
        if (lhs < 0 && rhs > 0) {
            lhs *= -1;
            return (lhs / rhs) * -1;
        }
        return lhs / rhs;
    }

    public static int mul(int lhs, int rhs) {
        return lhs * rhs;
    }

    public static void initArgs() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        numbers = new int[n];
        operators = new int[4];

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; ++i) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < 4; ++i) {
            operators[i] = Integer.parseInt(st.nextToken());
        }

        permutation = new ArrayList();
    }
}
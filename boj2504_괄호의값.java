import java.io.*;
import java.util.*;
import java.math.*;

public class boj2504_괄호의값 {
    static String expr;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        expr = br.readLine();
        solve();
    }

    private static void solve() {
        if (!checkBracket()) {
            System.out.println(0);
            return ;
        }

        boolean[] visited = new boolean[expr.length()];

        calculateValue(0, 0);
        
    }

    private static int calculateValue(int curr, long cnt) {
        if (expr.charAt(curr) == ']') {
            if (cnt == 0)
                return 3;
            return 3 * cnt;
        }

        if (expr.charAt(curr) == ')') {
            if (cnt == 0)
                return 2;
            return 2 * cnt;
        }

        for (int i = curr; i < expr.length(); ++i) {
            if (expr.charAt(curr) == '(')
                calculateValue(curr + 1);
            if (expr.charAt(curr) == '[')
                calculateValue(curr + 1);
        }

        
    }

    private static boolean checkBracket() {
        Stack<Character> stk = new Stack<Character>();

        for (int i = 0; i < expr.length(); ++i) {
            char curr = expr.charAt(i);

            if (curr == '[' || curr == '(') {
                stk.add(curr);
            } else if (curr == ']') {
                if (stk.isEmpty() || stk.pop() != '[') {
                    return false;
                }
            } else if (curr == ')') {
                if (stk.isEmpty() || stk.pop() != '(') {
                    return false;
                }
            }
        }
        if (stk.isEmpty()) {
            return true;
        }
        return false;
    }
}
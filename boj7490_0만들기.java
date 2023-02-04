import java.io.*;
import java.util.*;
import java.math.*;

public class boj7490_0만들기 {
    static int n;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static char[] numbers = new char[9];
    static char[] symbols = new char[3];
    static StringBuilder sb = new StringBuilder();
    static int ans = 0;

    public static int eval() throws IOException {
        int idx = 0;
        int num = 0;
        ArrayList<Character> quiz = new ArrayList<Character>();
        ArrayList<Integer> nums = new ArrayList<Integer>();
        
        int sum = 0;

        quiz.add(numbers[0]);
        for (int i = 0; i < n-1; ++i) {
            quiz.add(sb.charAt(i));
            quiz.add(numbers[i+1]);
        }
        for (int i = 0; i < n-1; ++i) {
            num += Character.getNumericValue(numbers[i]);
            if (sb.charAt(i) == '+' || sb.charAt(i) == '-') {
                nums.add(num);
                num = 0;
            } else if (sb.charAt(i) == ' ') {
                num *= 10;
            }
        }
        num += Character.getNumericValue(numbers[n-1]);
        nums.add(num);


        sum = nums.get(0);
        idx = 0;
        for (int i = 0; i < n -1; ++i) {
            if (sb.charAt(i) == ' ')
                continue;
            if (sb.charAt(i) == '+') {
                sum += nums.get(idx+1);
            } else if (sb.charAt(i) == '-') {
                sum -= nums.get(idx+1);
            }
            ++idx;
        }
        if (sum == 0) {
            // System.out.println(sum);
            for (int i = 0; i < quiz.size(); ++i) {
                bw.write(quiz.get(i));
            }
            bw.write("\n");
        }
        return sum;
    }

    public static void dfs(int depth) throws IOException {
        if (depth >= n - 1) {
            // System.out.println(sb);
            eval();
            return ;
        }
        for (int i = 0; i < 3; ++i) {
            sb.append(symbols[i]);
            dfs(depth + 1);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
    public static void main(String[] args) throws IOException {
        symbols[0] = ' ';
        symbols[1] = '+';
        symbols[2] = '-';
        for (int i = 0; i < 9; ++i) {
            int c = i+1 + 48;
            numbers[i] = (char)c;
        }
        int t = Integer.parseInt(br.readLine());
        while (t > 0) {
            n = Integer.parseInt(br.readLine());
            dfs(0);
            bw.write("\n");
            --t;
        }        
        bw.flush();
        bw.close();
    }
}
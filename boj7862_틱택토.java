import java.util.*;
import java.io.*;
import java.math.*;

public class boj7862_틱택토 {
    public static class boj7862 {
        char[] empty = new char[9];
        boolean[] visited = new boolean[9];
        boolean flag = false;

        public boolean isGameEnd() {
            return (empty[0] != '.' && (empty[0] == empty[1] && empty[1] == empty[2])) || 
                (empty[3] != '.' && (empty[3] == empty[4] && empty[4] == empty[5])) ||
                (empty[6] != '.' && (empty[6] == empty[7] && empty[7] == empty[8])) || 
                (empty[0] != '.' && (empty[0] == empty[3] && empty[3] == empty[6])) ||
                (empty[1] != '.' && (empty[1] == empty[4] && empty[4] == empty[7])) || 
                (empty[2] != '.' && (empty[2] == empty[5] && empty[5] == empty[8])) ||
                (empty[0] != '.' && (empty[0] == empty[4] && empty[4] == empty[8])) || 
                (empty[2] != '.' && (empty[2] == empty[4] && empty[4] == empty[6]));
        }

        public void dfs(int depth, String comp) {
            if (depth >= 9 || isGameEnd()) {
                int idx = 0;
                while (idx < 9) {
                    if (empty[idx] != comp.charAt(idx))
                        break;
                    ++idx;
                }
                if (idx >= 9) {
                    flag = true;
                    System.out.println("valid");
                }
                return;
            }

            for (int i = 0; i < 9; ++i) {
                if (visited[i])
                    continue;
                if (flag)
                    return;
                if (depth % 2 == 0) {
                    visited[i] = true;
                    empty[i] = 'X';
                    dfs(depth + 1,comp);
                    visited[i] = false;
                    empty[i] = '.';
                } else {
                    visited[i] = true;
                    empty[i] = 'O';
                    dfs(depth+1, comp);
                    visited[i] = false;
                    empty[i] = '.';
                }
            }
        }

        public void solution(String comp) throws IOException {
            flag = false;
            for (int i = 0; i < 9; ++i) {
                empty[i] = '.';
                visited[i] = false;
            }
            dfs(0, comp);
            if (!flag)
                System.out.println("invalid");
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        boj7862 solve = new boj7862();
        
        while (true) {
            String input = br.readLine();
            if (input.equals("end"))
                break;
            solve.solution(input);
        }
    }
}
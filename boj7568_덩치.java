import java.io.*;
import java.util.*;
import java.math.*;

public class boj7568_덩치 {
    static int n;
    static ArrayList<Pair> arr;

    public static class Pair {
        public int x;
        public int y;

        public Pair(int _x, int _y) { 
            x = _x; 
            y = _y; 
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        arr = new ArrayList();
        StringBuilder ans = new StringBuilder();

        for (int i = 0; i<n; ++i) {
            String[] input = br.readLine().split(" ");
            Pair temp = new Pair(Integer.parseInt(input[0]), Integer.parseInt(input[1]));
            arr.add(temp);
        }
        for (int i = 0; i < n; ++i) {
            int cnt = 0;
            Pair me = arr.get(i);
            for (int j = 0; j < n; ++j) {
                if (i == j)
                    continue;
                Pair other = arr.get(j);
                if (me.x < other.x && me.y < other.y)
                    ++cnt;
            }
            ans.append(cnt + 1);
            ans.append(" ");
        }
        System.out.println(ans);
    }
}
import java.io.*;
import java.util.*;
import java.math.*;

// time = 1sec
// space = 256mb
// 1 <= t <= 100
// 1 <= n <= 100,000
// 1 <= p <= 100,000
// timeComplex = O(tp)

public class boj5403_AC {
    static int t;
    static int n;
    static String p;
    static ArrayDeque<Integer> dq = new ArrayDeque<Integer>();
    static StringBuilder ans = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        t = Integer.parseInt(br.readLine());

        while (t > 0) {
            p = br.readLine();
            n = Integer.parseInt(br.readLine());
            String arr = br.readLine();
            if (n == 0) {
                if (p.contains("D"))
                    ans.append("error\n");
                else
                    ans.append("[]\n");
                --t;
                continue ;
            }
            arr = arr.substring(1, arr.length()-1);
            String[] split = arr.split(",");
            for (int i = 0; i < split.length; ++i) {
                dq.add(Integer.parseInt(split[i]));
            }
            solve();
            --t;
        }
        System.out.print(ans);
    }

    private static void solve() {
        boolean reversed = false;

        for (int i = 0; i < p.length(); ++i) {
            char order = p.charAt(i);

            if (order == 'R') {
                reversed = !reversed;
            } else {
                if (dq.isEmpty()) {
                    ans.append("error\n");
                    return;
                } else {
                    if (reversed) {
                        dq.pollLast();
                    } else {
                        dq.pollFirst();
                    }
                }
            }
        }

        makeArr(reversed);
    }

    private static void makeArr(boolean reversed) {
        StringBuilder arr = new StringBuilder();
        arr.append("[");
        if (reversed) {
            while (!dq.isEmpty()) {
                if (dq.size() > 1) {
                    arr.append(dq.pollLast());
                    arr.append(",");
                } else {
                    arr.append(dq.pollLast());
                }
            }
        } else {
            while (!dq.isEmpty()) {
                if (dq.size() > 1) {
                    arr.append(dq.pollFirst());
                    arr.append(",");
                } else {
                    arr.append(dq.pollFirst());
                }
            }
        }
        arr.append("]\n");
        ans.append(arr);
    }
}
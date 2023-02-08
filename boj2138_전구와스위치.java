import java.util.*;
import java.io.*;
import java.math.*;

public class boj2138_전구와스위치 {
    static int n; 
    static int[] before;
    static int[] after;
    static int[] temp;
    
    public static boolean isEquals(int[] origin, int[] comp) {
        for (int i = 0; i < n; ++i) {
            if (origin[i] != comp[i]) {
                return false;
            }
        }
        return true;
    }

    public static void pressSwitch(int idx, int[] bulbs) {
        if (idx == 0) {
            if (bulbs[0] == 0) {
                bulbs[0] = 1;
            } else {
                bulbs[0] = 0;
            }
            if (bulbs[1] == 0) {
                bulbs[1] = 1;
            } else {
                bulbs[1] = 0;
            }
            return;
        } else if (idx == n -1) {
            if (bulbs[n-1] == 0) {
                bulbs[n-1] = 1;
            } else {
                bulbs[n-1] = 0;
            }
            if (bulbs[n-2] == 0) {
                bulbs[n-2] = 1;
            } else {
                bulbs[n-2] = 0;
            }
            return;
        }
        if (bulbs[idx] == 0) {
            bulbs[idx] = 1;
        } else {
            bulbs[idx] = 0;
        }
        if (bulbs[idx-1] == 0) {
            bulbs[idx-1] = 1;
        } else {
            bulbs[idx-1] = 0;
        }
        if (bulbs[idx+1] == 0) {
            bulbs[idx+1] = 1;
        } else {
            bulbs[idx+1] = 0;
        }
    }

    public static int solve(int flag) {
        int cnt = 0;
        temp = new int[n];

        for (int i = 0; i < n; ++i) {
            temp[i] = before[i];
        }

        if (flag == 1) {
            cnt += 1;
            pressSwitch(0, temp);
        }

        if (isEquals(after, temp)) {
            return cnt;
        }

        for (int i = 1; i < n; ++i) {
            if (temp[i-1] != after[i-1]) {
                pressSwitch(i, temp);
                cnt += 1;
            }
        }
        if (isEquals(after, temp)) {
            return cnt;
        }
        return -1;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        before = new int[n];
        after = new int[n];

        String input = br.readLine();
        for (int i = 0; i < input.length(); ++i) {
            before[i] = (int)(input.charAt(i) - '0');
        }
        input = br.readLine();
        for (int i = 0; i < input.length(); ++i) {
            after[i] = (int)(input.charAt(i) - '0');
        }

        int cnt0 = solve(0);
        int cnt1 = solve(1);

        if (cnt0 == -1 && cnt1 == -1) {
            System.out.println(-1);
            return;
        }
        if (cnt0 != -1 && cnt1 == -1) {
            System.out.println(cnt0);
            return;
        }
        if (cnt1 != -1 && cnt0 == -1) {
            System.out.println(cnt1);
            return;
        }
        System.out.println(Math.min(cnt1, cnt0));
    }
}
import java.io.*;
import java.util.*;
import java.math.*;

public class boj2671_잠수함식별 {
    static String sound;
    static String ans = "NOISE";
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        sound = br.readLine();

        solve();
        System.out.println(ans);
    }

    private static void solve() {
        dfs(0);
    }

    private static void dfs(int idx) {
        if (idx >= sound.length()) {
            ans = "SUBMARINE";
            return ;
        }

        int curr = idx;
        int next = idx + 1;

        if (sound.charAt(idx) == '0') {
            if (next < sound.length() && sound.charAt(next) == '1') {
                dfs(next + 1);
            }
        }

        if (sound.charAt(idx) == '1') {
            if (next >= sound.length() || sound.charAt(next) == '1') {
                return ;
            }
            int zeroCnt = 0;
            while (next < sound.length() && sound.charAt(next) == '0') {
                ++zeroCnt;
                ++next;
            }
            if (zeroCnt < 2)
                return ;
            while (next < sound.length() && sound.charAt(next) == '1') {
                dfs(next + 1);
                ++next;
            }
        }
    }
}
import java.io.*;
import java.util.*;
import java.math.*;

//time=0.5sec
//space=512mb
//n= 0 <= n <= 100000
//timeComplex = O(n)

public class boj11659_구간합구하기4 {
    static long[] sum = new long[100001];
    static int[] nums = new int[100001];
    static int n, m, q1, q2;
    static BufferedReader br;
    
    public static void main(String[] args) throws IOException {
        initArgs();        
        sum[1] = nums[1];
        for (int i = 2; i < 100001; ++i) {
            sum[i] = sum[i-1] + nums[i];
        }

        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < m; ++i) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            q1 = Integer.parseInt(st.nextToken());
            q2 = Integer.parseInt(st.nextToken());

            ans.append(sum[q2] - sum[q1-1]);
            ans.append("\n");
        }
        System.out.print(ans);
    }

    public static void initArgs() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; ++i) {
            nums[i] = Integer.parseInt(st.nextToken());
        }
    }
}
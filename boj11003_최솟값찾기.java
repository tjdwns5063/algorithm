import java.io.*;
import java.util.*;
import java.math.*;

// time = 2.6sec
// space = 512mb
// 1 <= L <= N <= 5,000,000

public class boj11003_최솟값찾기 {
    static int N, L;
    static int[] ans;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        ans = new int[N];
        PriorityQueue<Integer> pQ = new PriorityQueue<Integer>(N);
        for (int i = 0; i < N; ++i) {
            if (pQ.size() < L) {
                pQ.add(Integer.parseInt(st.nextToken()));
            }
        }
    }
}
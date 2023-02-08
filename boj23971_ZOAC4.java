import java.io.*;

public class boj23971_ZOAC4 {
    static int h,w,n,m;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = br.readLine().split(" ");
        h = Integer.parseInt(input[0]);
        w = Integer.parseInt(input[1]);
        n = Integer.parseInt(input[2]);
        m = Integer.parseInt(input[3]);

        int ans = 0;

        for (int i = 0; i < h; i += n+1) {
            for (int j = 0; j < w; j += m+1) {
                ++ans;
            }
        }
        System.out.println(ans);
    }
}
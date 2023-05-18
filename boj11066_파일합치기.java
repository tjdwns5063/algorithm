import java.io.*;
import java.util.*;
import java.util.stream.*;

public class boj11066_파일합치기 {
    static int n;
    static int[] nums;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());
        while (--t >= 0) {
            initArgs(br);

            System.out.println(Arrays.toString(nums));

            solve();
        }
    }

    private static void initArgs(BufferedReader br) throws IOException {
        n = Integer.parseInt(br.readLine());
        
        nums = Arrays.stream(br.readLine().split(" "))
            .map( str -> Integer.parseInt(str) )
            .mapToInt(Integer::intValue)
            .toArray();
    }

    private static void solve() {
        int[][][] dp = new int[501][501][501];

        //1. 0번 인덱스 

        for (int k = 0; k <= 500; ++k) {
            for (int i = 0; i <= 500; ++i) {
                for (int j = 0; j <= 500; ++j) {

                }
            }
        }
    }
}
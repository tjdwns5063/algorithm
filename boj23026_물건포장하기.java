// time = 2sec
// space = 512mb
// 1 <= t <= 10 -> t: 테케 수
// 1 <= n, B <= 4000 -> n: 물건의 수 B: 상자의 길이 
// 1 <= v[i] <= B  -> v[i]: 짐의 길이
// 1 <= R <= 10^15 -> R: 트럭의 수
// timeComplex = 

import java.io.*;
import java.util.*;
import java.math.*;

public class boj23026_물건포장하기 {
    static long t, n, B, R;
    static long[] v;

    private static long solve() {
    // 1 <= n, B <= 4000 -> n: 물건의 수 B: 상자의 길이 
    // 1 <= v[i] <= B  -> v[i]: 짐의 길이
    // 1 <= R <= 10^15 -> R: 트럭의 수
        int idx = 0;
        long total = 0;
        long currSum = 0;
        long perTruck = 1;
        long ans = 0;
        
        while (true) {
            if (idx >= n) {
                perTruck += 1;
                idx = 0;
            }

            currSum += v[idx];
            int next = (idx + 1 >= n) ? 0 : idx + 1;
            if (currSum + v[next] > B && idx == n - 1) {
                total += currSum;
                break ;
            } else if (currSum + v[next] > B) {
                total += currSum;
                currSum = 0;
            }
            ++idx;
        }

        long truckWeight = 0;

        for (int i = 0; i < n; ++i) {
            truckWeight += v[i];
        }
        long perBox = (total / B);
        perBox += (total%B==0) ? 0 : 1;


        // 트럭 2대 옮기는데 박스 3개 필요.
        ans = (R / perTruck) * perBox; // perBox*perTruck대만큼 빼면 남은건...
        if (R % perTruck != 0) {
            long restWeight = (R % perTruck) * truckWeight;
            ans += restWeight / B;
            ans += (restWeight % B == 0) ? 0 : 1;
        }
       return ans ;
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        t = Long.parseLong(br.readLine());
        while (t > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Long.parseLong(st.nextToken());
            B = Long.parseLong(st.nextToken());
            R = Long.parseLong(st.nextToken());
            v = new long[(int)n];
            st = new StringTokenizer(br.readLine());
            
            for (int i = 0; i < n; ++i) {
                v[i] = Long.parseLong(st.nextToken());
            }
            bw.write(Long.toString(solve()));
            bw.write("\n");
            --t;
        }
        bw.flush();
        bw.close();
    }
}

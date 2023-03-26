// time = 2sec
// space = 192mb
// 2 <= v <= 400
// 0 <= e <= v(v-1)
// timeComplex = O(v^3)

import java.io.*;
import java.math.*;
import java.util.*;

public class boj1956_운동 {
    static int v;
    static int e;
    static int[][] distTable = new int[403][403];
    public static void main(String[] args) throws IOException {
        for (int i = 0; i <= 400; ++i) {
            for (int j = 0; j <= 400; ++j) {
                distTable[i][j] = 40000001;
            }
        }

        initArgs();


        for (int k = 1; k <= v; ++k) {
            for (int i = 1; i <= v; ++i) {
                for (int j = 1; j <= v; ++j) {
                    distTable[i][j] = Math.min(distTable[i][j], distTable[i][k] + distTable[k][j]);
                }
            }
        }

        // printBoard();

        int ans = Integer.MAX_VALUE;

        for (int i = 1; i <= v; ++i) {
            if (distTable[i][i] != 0) {
                ans = Math.min(ans, distTable[i][i]);
            }
        }

        if (ans == 40000001) {
            System.out.println(-1);
        } else {
            System.out.println(ans);
        }
    }

    private static void printBoard() {
        for (int i = 1; i <= v; ++i) {
            for (int j = 1; j <= v; ++j) {
                System.out.print(distTable[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static void initArgs() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());

        for (int i = 0; i < e; ++i) {
            st = new StringTokenizer(br.readLine());

            int to = Integer.parseInt(st.nextToken());
            int from = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());

            distTable[to][from] = dist;
        }
    }
}
import java.util.*;
import java.io.*;
import java.math.*;

//time=1sec
//memory=512mb
//n= 1 <= n <= 100_000

public class boj24337_가희와탑 {
    static int n, a, b;
    static ArrayDeque<Integer> building;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        a = Integer.parseInt(input[1]);
        b = Integer.parseInt(input[2]);
        building = new ArrayDeque<Integer>();
        int cnt = 0;
        int curr = 0;

        if (a+b > n+1) {
            System.out.println(-1);
            return ;
        }

        if (a>b) {
            for (int i = 1; i <= a; ++i) {
                building.addLast(i);
            }
            if (b==1) {
                cnt = n-a;
                while (cnt > 0) {
                    building.addFirst(1);
                    --cnt;
                }
            } else {
                curr = b-1;
                cnt = b-1;
                while (building.size() < n && curr > 0) {
                    building.addLast(curr);
                    --curr;
                    --cnt;
                }
                while (building.size() < n) {
                    building.addFirst(1);
                }
            }
            // System.out.println(building);
        } else {

            if (a == 1) {
                cnt = n-b;
                for (int i = b-1; i >= 1; --i) {
                    building.addLast(i);
                }
                while (cnt > 0) {
                    building.addFirst(1);
                    --cnt;
                }
                building.addFirst(b);
            } else {
                for (int i = b; i>= 1; --i) {
                    building.addLast(i);
                }
                cnt = a-1;
                curr = a-1;
                while (building.size() < n && curr > 0) {
                    building.addFirst(curr);
                    --cnt;
                    --curr;
                }
                while (building.size() < n) {
                    building.addFirst(1);
                }
            }
            // System.out.println(building);
        }
        StringBuilder ans = new StringBuilder();
        while (!building.isEmpty()) {
            ans.append(building.peek());
            ans.append(" ");
            building.pop();
        }
        bw.write(ans.toString());
        bw.flush();
        bw.close();
    }
}
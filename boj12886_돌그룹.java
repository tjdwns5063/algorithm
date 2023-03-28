// time = 2sec
// space = 512mb
// 1 <= a,b,c <= 500

import java.io.*;
import java.util.*;
import java.math.*;

public class boj12886_돌그룹 {
    static int[] rocks = new int[3];
    static boolean[][] visited = new boolean[3001][3001];

    static LinkedList<ArrayList<Integer>> q = new LinkedList();
    static int ans = 0;
    
    public static void main(String[] args) throws IOException {
        initArgs();

        if ((rocks[0] + rocks[1] + rocks[2]) % 3 != 0) {
            System.out.println(0);
            return ;
        }

        ArrayList<Integer> arr = new ArrayList();


        arr.add(rocks[0]);
        arr.add(rocks[1]);
        arr.add(rocks[2]);
        Collections.sort(arr);
        q.add(arr);
        bfs(arr);
        System.out.println(ans);
    }

    private static void bfs(ArrayList<Integer> arr) {
        while (!q.isEmpty()) {
            ArrayList<Integer> curr = q.poll();

            Collections.sort(curr);

            int a = curr.get(0);
            int b = curr.get(1);
            int c = curr.get(2);

            if (a == b && b == c) {
                ans = 1;
                return ;
            }

            if (visited[a][c]) {
                continue ;
            }

            visited[a][c] = true;
            
            if (a > b) {
                ArrayList<Integer> newArr = new ArrayList();

                newArr.add(a - b);
                newArr.add(b + b);
                newArr.add(c);
                q.add(newArr);
            } else if (a < b) {
                ArrayList<Integer> newArr = new ArrayList();

                newArr.add(a + a);
                newArr.add(b - a);
                newArr.add(c);
                q.add(newArr);
            }

            if (a > c) {
                ArrayList<Integer> newArr = new ArrayList();

                newArr.add(a - c);
                newArr.add(b);
                newArr.add(c + c);
                q.add(newArr);
            } else if (a < c) {
                ArrayList<Integer> newArr = new ArrayList();

                newArr.add(a + a);
                newArr.add(b);
                newArr.add(c - a);
                q.add(newArr);
            }

            if (b > c) {
                ArrayList<Integer> newArr = new ArrayList();

                newArr.add(a);
                newArr.add(b - c);
                newArr.add(c + c);
                q.add(newArr);
            } else if (b < c) {
                ArrayList<Integer> newArr = new ArrayList();

                newArr.add(a);
                newArr.add(b + b);
                newArr.add(c - b);
                q.add(newArr);
            }
        }
    }

    private static void initArgs() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        rocks[0] = Integer.parseInt(st.nextToken());
        rocks[1] = Integer.parseInt(st.nextToken());
        rocks[2] = Integer.parseInt(st.nextToken());
    }
}
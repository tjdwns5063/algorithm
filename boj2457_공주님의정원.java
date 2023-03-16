import java.io.*;
import java.util.*;
import java.math.*;

public class boj2457_공주님의정원 {
    static int[][] flowers;
    static int n;
    static int marchFirst = parseDate(3, 1);
    static int decemberFirst = parseDate(12, 1);
    static int ans = 0;


    public static void main(String[] args) throws IOException {
        initArgs();

        Arrays.sort(flowers, new Comparator<int[]>() {
            @Override
            public int compare(int[] f1, int[] f2) {
                if (f1[0] == f2[0]) {
                    return f1[1] - f2[1];
                }
                return f1[0] - f2[0];
            }
        });

        int start = 0;
        //getStart
        for (int i = 0; i < n; ++i) {
            if (flowers[i][0] > marchFirst) {
                break ;
            }
            if (flowers[start][1] < flowers[i][1]) {
                start = i;
            }
        }

        int end = n-1;

        if (flowers[start][0] > marchFirst) {
            System.out.println(0);
            return ;
        }

        if (solve(start, end)) {
            System.out.println(ans);
        } else {
            System.out.println(0);
        }
    }

    private static boolean solve(int st, int en) {
        if (flowers[st][1] >= decemberFirst) {
            ++ans;
            return true;
        }
        int next = st;
        ++ans;
        for (int i = st+1; i <= en; ++i) {
            if (flowers[i][0] > flowers[st][1]) {
                break ;
            }
            if (flowers[next][1] <= flowers[i][1]) {
                next = i;
            }
        }
        if (next == st) {
            return false;
        }
        boolean ret = solve(next,en);
        return ret;
    }

    private static void initArgs() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        flowers = new int[n][2];

        for (int i = 0; i < n; ++i) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int[] flower = new int[2];

            int startMonth = Integer.parseInt(st.nextToken());
            int startDay = Integer.parseInt(st.nextToken());
            int endMonth = Integer.parseInt(st.nextToken());
            int endDay = Integer.parseInt(st.nextToken());

            flowers[i][0] = parseDate(startMonth, startDay);
            flowers[i][1] = parseDate(endMonth, endDay);
        }
    }

    private static int parseDate(int month, int day) {
        int date = 0;
// 4, 6, 9, 11월은 30일까지 있고, 1, 3, 5, 7, 8, 10, 12월은 31일까지 있으며, 2월은 28일
        if (month == 1) {
            date += 0;
        } else if (month == 2) {
            date += 31;
        } else if (month == 3) {
            date += 59;
        } else if (month == 4) {
            date += 90;
        } else if (month == 5) {
            date += 120;
        } else if (month == 6) {
            date += 151;
        } else if (month == 7) {
            date += 181;
        } else if (month == 8) {
            date += 212;
        } else if (month == 9) {
            date += 243;
        } else if (month == 10) {
            date += 273;
        } else if (month == 11) {
            date += 304;
        } else if (month == 12) {
            date += 334;
        }
        return date + day;
    }
}
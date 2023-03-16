import java.io.*;
import java.util.*;
import java.math.*;

<<<<<<< HEAD
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
=======
// time = 1sec
// space = 192mb
// n = 1 <= n <= 100,000
// timeCoplex = O(n) or O(nlogn)

public class boj2457_공주님의정원 {
    static int n;
    static ArrayList<Flower> flowers = new ArrayList<Flower>();

    public static class Flower {
        public Date start;
        public Date end;

        public Flower (Date _start, Date _end) {
            start = _start;
            end = _end;
        }
    }

    public static class Date implements Comparable<Date> {
        public int month;
        public int day;

        public Date(int _month, int _day) {
            month = _month;
            day = _day;
        }

        @Override
        public int compareTo(Date date) {
            if (this.month == date.month) {
                return this.day - date.day;
            }
            return this.month - date.month;
        }
    }
    
    public static void main(String[] args) throws IOException {
        initArgs();
        flowers.sort(new Comparator<Flower>() {
            @Override
            public int compare(Flower f1, Flower f2) {
                return f1.start.compareTo(f2.start);
            }
        });
        printFlowers();

        for (int i = 0; i < flowers.size(); ++i) {
            Flower flower = flowers.get(i);

            if ((flower.start.month == 3 && flower.start.day == 1) || flower.start.month < 3) {
                binarySearch(i, 0);
            }
        }
    }

    public static int binarySearch(int _start, int cnt) {
        int start = _start;
        int end = flowers.size() - 1;
        Flower startFlower = flowers.get(start);

        System.out.println("SM: " + startFlower.start.month + " SE: " + startFlower.start.day + " EM: " + startFlower.end.month + " ED: " + startFlower.end.day);
        
        if (startFlower.end.compareTo(new Date(11, 30)) > 0) {
            System.out.println("cnt : " + cnt);
            return cnt;
        }
        // mid.start < start.end && mid.end > start.end
        while (start < end) {
            int mid = (start + end) / 2;
            Flower midFlower = flowers.get(mid);
            // System.out.println("SM: " + midFlower.start.month + " SE: " + midFlower.start.day + " EM: " + midFlower.end.month + " ED: " + midFlower.end.day);
            
            if (midFlower.start.compareTo(startFlower.end) >= 0 || midFlower.end.compareTo(startFlower.end) <= 0) {
                mid = start + 1;
            } else {
                binarySearch(mid, cnt + 1);
            }
        }
        return 0;
    }

    public static void printFlowers() {
        for (int i = 0; i < flowers.size(); ++i) {
            Flower flower = flowers.get(i);
            System.out.println("SM: " + flower.start.month + " SE: " + flower.start.day + " EM: " + flower.end.month + " ED: " + flower.end.day);
        }
    }

    public static void initArgs() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; ++i) {
            StringTokenizer st = new StringTokenizer(br.readLine());
>>>>>>> 15580fcf8417a9694eed0c79f2b1cbc8f12aa88c

            int startMonth = Integer.parseInt(st.nextToken());
            int startDay = Integer.parseInt(st.nextToken());
            int endMonth = Integer.parseInt(st.nextToken());
            int endDay = Integer.parseInt(st.nextToken());

<<<<<<< HEAD
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
=======
            flowers.add(new Flower(new Date(startMonth, startDay), new Date(endMonth, endDay)));
        }
>>>>>>> 15580fcf8417a9694eed0c79f2b1cbc8f12aa88c
    }
}
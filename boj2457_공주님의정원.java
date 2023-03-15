import java.io.*;
import java.util.*;
import java.math.*;

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

            int startMonth = Integer.parseInt(st.nextToken());
            int startDay = Integer.parseInt(st.nextToken());
            int endMonth = Integer.parseInt(st.nextToken());
            int endDay = Integer.parseInt(st.nextToken());

            flowers.add(new Flower(new Date(startMonth, startDay), new Date(endMonth, endDay)));
        }
    }
}
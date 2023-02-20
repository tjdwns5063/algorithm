import java.io.*;
import java.util.*;
import java.math.*;

public class boj1911_흙길보수하기 {
    static int n, L;
    static PriorityQueue<Coord> pQ;


    public static class Coord {
        public long start;
        public long end;

        public Coord(long _start, long _end) {
            start = _start;
            end = _end;
        }
    }
    
    public static void main(String[] args) throws IOException {
        initArgs();
        solve();
    }

    public static void solve() {
        long currStick = pQ.peek().start;
        long ans = 0;

        while (!pQ.isEmpty()) {
            Coord pond = pQ.poll();
            if (currStick < pond.start)
                currStick = pond.start;
            long needLen = pond.end - currStick;
            // System.out.println("currStick: " + currStick + " end: " + pond.end + " needLen: " + needLen);
            if (needLen > 0) {
                long cnt = (needLen / L) + ((needLen % L != 0) ? 1L : 0L);
                ans += cnt;
                currStick += (cnt * L);
            }
        }
        // long ans = (total / L) + ((total % L != 0) ? 1L : 0L);
        System.out.println(ans);
    }

    public static void initArgs() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        
        n = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        pQ = new PriorityQueue<Coord>(new Comparator<Coord>() {
            @Override
            public int compare(Coord c1, Coord c2) {
                return (int)(c1.start - c2.start);
            }
        });
        for (int i = 0; i < n; ++i) {
            st = new StringTokenizer(br.readLine());

            Coord coord = new Coord(Long.parseLong(st.nextToken()), Long.parseLong(st.nextToken()));
            pQ.add(coord);
        }
    }
}
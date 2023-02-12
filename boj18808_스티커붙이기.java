import java.io.*;
import java.util.*;
import java.math.*;

public class boj18808_스티커붙이기 {
    static int[][] notebook;
    static int n, m, k, ans;
    static ArrayList<Sticker> stickers;

    public static class Sticker {
        public int[][] sticker;
        public int height;
        public int width;

        public Sticker(int _height, int _width, int[][] _sticker) {
            sticker = _sticker;
            height = _height;
            width = _width;
        }

        public void printSticker() {
            for (int i = 0; i < height; ++i) {
                for (int j = 0; j < width; ++j) {
                    System.out.print(sticker[i][j] + " ");
                }
                System.out.println();
            }
            System.out.println();
        }

        public void rotateSticker() {
            int[][] newSticker = new int[width][height];


            int tmp = height;
            height = width;
            width = tmp;


            for (int i = 0; i < height; ++i) {
                int idx = width-1;
                for (int j = 0; j < width; ++j) {
                    newSticker[i][j] = sticker[idx][i];
                    --idx;
                }
            }

            sticker = newSticker;
        }
    }

    public static void initArgs() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        ans = 0;

        stickers = new ArrayList();
        notebook = new int[n][m];

        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                notebook[i][j] = 0;
            }
        }

        for (int i = 0; i < k; ++i) {
            st = new StringTokenizer(br.readLine());
           
            int height = Integer.parseInt(st.nextToken());
            int width = Integer.parseInt(st.nextToken());
            int[][] sticker = new int[height][width];

            for (int x = 0; x < height;  ++x) {
                st = new StringTokenizer(br.readLine());
                for (int y = 0; y < width; ++y) {
                    sticker[x][y] = Integer.parseInt(st.nextToken());
                }
            }
            stickers.add(new Sticker(height, width, sticker));
        }
    }

    public static boolean checkOk(int x, int y, Sticker sticker) {
        if (x + sticker.height > n || y + sticker.width > m) {
            return false;
        }
        // System.out.println("nx: " + x + " ny: " + y);
        int hIdx = 0;
        for (int i = x; i < x + sticker.height; ++i) {
            int wIdx = 0;
            for (int j = y; j < y + sticker.width; ++j) {
                if (sticker.sticker[hIdx][wIdx] == 1 && notebook[i][j] != 0)
                    return false;
                ++wIdx;
            }
            ++hIdx;
        }
        return true;
    }

    public static void attachSticker(int x, int y, Sticker sticker) {
        int hIdx = 0;
        // System.out.println("x: " + x + " y: " + y);
        for (int i = x; i < x + sticker.height; ++i) {
            int wIdx = 0;
            for (int j = y; j < y + sticker.width; ++j) {
                if (sticker.sticker[hIdx][wIdx] == 1) {
                    notebook[i][j] = sticker.sticker[hIdx][wIdx];
                    ++ans;
                }
                ++wIdx;
            }
            ++hIdx;
        }
    }

    public static void printNotebook() {
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                System.out.print(notebook[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void solve() {
        for (int q = 0; q < k; ++q) {
            boolean okSignal = false;
            Sticker sticker = stickers.get(q);
            for (int p = 0; p < 4; ++p) {
                for (int i = 0; i < n; ++i) {
                    for (int j = 0; j < m; ++j) {
                        if (okSignal)
                            continue ;
                        okSignal = checkOk(i, j, sticker);
                        if (okSignal) {
                            attachSticker(i, j, sticker);
                        }
                    }
                }
                sticker.rotateSticker();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        initArgs();
        solve();
        System.out.println(ans);        
    }
}
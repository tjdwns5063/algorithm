import java.io.*;
import java.util.*;
import java.math.*;

public class boj14500_테트로미노 {
    static int n, m;
    static int[][] board;

    public static class Tetromino {
        public int height;
        public int width;
        public int[][] tetromino;
        public int type;

        public Tetromino(int _type) {
            type = _type;

            makeTetromino();
        }

        public void makeTetromino() {
            if (type == 0) {
                height = 1;
                width = 4;

                tetromino = new int[height][width];
                for (int i = 0; i < height; ++i) {
                    for (int j = 0; j < width; ++j) {
                        tetromino[i][j] = 1;
                    }
                }
            } else if (type==1) {
                height = 2;
                width = 2;
                tetromino = new int[height][width];
                for (int i = 0; i < height; ++i) {
                    for (int j = 0; j < width; ++j) {
                        tetromino[i][j] = 1;
                    }
                }
            } else if (type == 2) {
                height = 3;
                width = 2;
                tetromino = new int[height][width];

                for (int i = 0; i < height; ++i) {
                    for (int j = 0; j < width; ++j) {
                        if (j == 0 || j == 1 && i == 2)
                            tetromino[i][j] = 1;
                    }
                }
            } else if (type==3) {
                height = 3;
                width = 2;
                tetromino = new int[height][width];

                for (int i = 0; i < height; ++i) {
                    for (int j = 0; j < width; ++j) {
                        if ((j == 0 && i < 2) || (j == 1 && i > 0))
                            tetromino[i][j] = 1;
                    }
                }
            } else if (type==4) {
                height = 2;
                width = 3;
                tetromino = new int[height][width];

                for (int i = 0; i < height; ++i) {
                    for (int j = 0; j < width; ++j) {
                        if (i == 0 || (i == 1 && j == 1))
                            tetromino[i][j] = 1;
                    }
                }
            }
        }

        public void rotate() {
            int[][] newTetro = new int[width][height];
            int hIdx = 0;
            for (int j = width-1; j >= 0; --j) {
                int wIdx = 0;
                for (int i = 0; i < height; ++i) {
                    newTetro[hIdx][wIdx] = tetromino[i][j];
                    ++wIdx;
                }
                ++hIdx;
            }
            tetromino = newTetro;
            int temp = height;
            height = width;
            width = temp;
        }

        public void symmetry() {
            int[][] newTetro = new int[height][width];

            int hIdx = 0;
            for (int i = 0; i < height; ++i) {
                int wIdx = width - 1;
                for (int j = 0; j < width; ++j) {
                    newTetro[hIdx][wIdx] = tetromino[i][j];
                    --wIdx;
                }
                ++hIdx;
            }
            tetromino = newTetro;
        }

        public void printTetromino() {
            for (int i = 0; i < height; ++i) {
                for (int j = 0; j < width; ++j) {
                    System.out.print(tetromino[i][j] + " ");
                }
                System.out.println();
            }
            System.out.println();
        }
    }

    public static void main(String[] args) throws IOException {
        initArgs();
        solove();
    }

    public static void solove() {
        ArrayList<Tetromino> tetrominos = new ArrayList();
        int ans = 0;

        for (int i = 0; i < 5; ++i) {
            tetrominos.add(new Tetromino(i));
        }

        for (int i = 0; i < 5; ++i) {
            Tetromino curr = tetrominos.get(i);
            for (int k = 0; k < 4; ++k) {
                ans = Math.max(ans, cntNumber(curr));
                curr.rotate();
            }
        }
        System.out.println(ans);
    }

    public static int cntNumber(Tetromino curr) {
        int cnt = 0;
        for (int k = 0; k < 2; ++k) {
            for (int i = 0; i < n; ++i) {
                for (int j = 0; j < m; ++j) {
                    int subCnt = 0;
                    if (i + curr.height > n || j + curr.width > m)
                        continue ;
                    for (int x = 0; x < curr.height; ++x) {
                        for (int y = 0; y < curr.width; ++y) {
                            if (curr.tetromino[x][y] == 1)
                                subCnt += board[i+x][j+y];
                        }
                    }
                    cnt = Math.max(cnt, subCnt);
                }
            }
            curr.symmetry();
        }
        return cnt;
    }

    public static void initArgs() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        
        board = new int[n][m];
        for (int i = 0; i < n; ++i) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; ++j) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }
}
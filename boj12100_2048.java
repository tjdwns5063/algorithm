import java.io.*;
import java.util.*;
import java.math.*;

public class boj12100_2048 {
    static int n;
    static int[][][] board;
    static ArrayList<Integer> permutation;
    static int ans = 0;

    public static void initArg() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        board = new int[n][n][2];
        for (int i = 0; i < n; ++i) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; ++j) {
                board[i][j][0] = Integer.parseInt(st.nextToken());
                board[i][j][1] = 0;
            }
        }
        permutation = new ArrayList();
    }

    public static void moveUp(int[][][] copy) {
        for (int i = 1; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                for (int k = i; k >= 1; --k) {
                    if (copy[k][j][0] == 0)
                        continue;
                    if (copy[k-1][j][0] == copy[k][j][0] && copy[k][j][1] == 0 && copy[k-1][j][1] == 0) {
                        copy[k-1][j][0] = copy[k-1][j][0] + copy[k][j][0];
                        copy[k-1][j][1] = 1;
                        copy[k][j][0] = 0;
                        copy[k][j][1] = 0;
                    } else if (copy[k-1][j][0] == 0) {
                        copy[k-1][j][0] = copy[k-1][j][0] + copy[k][j][0];
                        copy[k-1][j][1] = copy[k][j][1];
                        copy[k][j][0] = 0;
                        copy[k][j][1] = 0;
                    }
                }
            }
        }
    }

    public static void moveDown(int[][][] copy) {
        for (int i = n-2; i >=0; --i) {
            for (int j = 0; j < n; ++j) {
                for (int k = i; k < n-1; ++k) {
                    if (copy[k][j][0] == 0)
                        continue;
                    if (copy[k+1][j][1] == 0 && copy[k][j][1] == 0 && copy[k+1][j][0] == copy[k][j][0]) {
                        copy[k+1][j][0] = copy[k+1][j][0] + copy[k][j][0];
                        copy[k+1][j][1] = 1;
                        copy[k][j][0] = 0;
                        copy[k][j][1] = 0;
                    } else if (copy[k+1][j][0] == 0) {
                        copy[k+1][j][1] = copy[k][j][1];
                        copy[k+1][j][0] = copy[k][j][0];
                        copy[k][j][0] = 0;
                        copy[k][j][1] = 0;
                    }
                }
            }
        }
    }

    public static void moveLeft(int[][][] copy) {
        for (int j = 1; j < n; ++j) {
            for (int i = 0; i < n; ++i) {
                for (int k = j; k >= 1; --k) {
                    if (copy[i][k][0] == 0)
                        continue ;
                    if (copy[i][k-1][1] == 0 && copy[i][k][1] == 0 && copy[i][k-1][0] == copy[i][k][0]) {
                        copy[i][k-1][0] = copy[i][k-1][0] + copy[i][k-1][0];
                        copy[i][k-1][1] = 1;
                        copy[i][k][0] = 0;
                        copy[i][k][1] = 0;
                    } else if (copy[i][k-1][0] == 0) {
                        copy[i][k-1][0] = copy[i][k][0];
                        copy[i][k-1][1] = copy[i][k][1];
                        copy[i][k][0] = 0;
                        copy[i][k][1] = 0;
                    }
                }
            }
        }
    }

    public static void moveRight(int[][][] copy) {
        for (int j = n-2; j >= 0; --j) {
            for (int i = 0; i < n; ++i) {
                for (int k = j; k < n-1; ++k) {
                    if (copy[i][k][0] == 0)
                        continue;
                    if (copy[i][k+1][1] == 0 && copy[i][k][1] == 0 && copy[i][k+1][0] == copy[i][k][0]) {
                        copy[i][k+1][0] = copy[i][k+1][0] + copy[i][k][0];
                        copy[i][k+1][1] = 1;
                        copy[i][k][0] = 0;
                        copy[i][k][1] = 0;
                    } else if (copy[i][k+1][0] == 0) {
                        copy[i][k+1][0] = copy[i][k][0];
                        copy[i][k+1][1] = copy[i][k][1];
                        copy[i][k][0] = 0;
                        copy[i][k][1] = 0;
                    }
                }
            }
        }
    }

    public static void solve() {
        int[][][] copy = new int[n][n][2];

        for (int i = 0; i < n; ++i) {
            for (int j =0; j < n; ++j) {
                copy[i][j][0] = board[i][j][0];
                copy[i][j][1] = board[i][j][1];
            }
        }
        for (int i = 0; i < 5; ++i) {
            int dir = permutation.get(i);

            if (dir == 0) {
                moveUp(copy);
            } else if (dir == 1) {
                moveDown(copy);
            } else if (dir == 2) {
                moveLeft(copy);
            } else if (dir == 3) {
                moveRight(copy);
            }
            for (int k = 0; k < n; ++k) {
                for (int j = 0; j < n; ++j) {
                    copy[k][j][1] = 0;
                }
            }
        }
        int mx = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                mx = Math.max(mx, copy[i][j][0]);
            }
        }
        ans = Math.max(ans, mx);
    }

    public static void dfs(int depth) {
        if (depth >= 5) {
            solve();
            return ;
        }
        for (int i = 0; i < 4; ++i) {
            permutation.add(i);
            dfs(depth + 1);
            permutation.remove(permutation.size()-1);
        }
    }

    public static void printBoard(int[][][] copy) {
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                System.out.print(copy[i][j][0] + " ");
            }
            System.out.println();
        }
        System.out.println();
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                System.out.print(copy[i][j][1] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) throws IOException {
        initArg();
        dfs(0);
        System.out.println(ans);
    }
}
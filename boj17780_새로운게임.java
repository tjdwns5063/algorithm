import java.io.*;
import java.util.*;
import java.math.*;

public class boj17780_새로운게임 {
    static int n, k;
    static int[][] board;
    static int[][] locate;
    static int[] dx = {0, 0, 0, -1, 1};
    static int[] dy = {0, 1, -1, 0, 0};
    static ArrayList<Player> players;

    public static class Player {
        public int id;
        public int x;
        public int y;
        public int dir;
        public ArrayList<Player> hangs;

        public Player(int _id, int _x, int _y, int _dir) {
            id = _id;
            x = _x;
            y = _y;
            dir = _dir;
            hangs = new ArrayList();
            hangs.add(this);
        }

        public int checkNext() {
            Player lowest = hangs.get(0);

            int nx = lowest.x + dx[lowest.dir];
            int ny = lowest.y + dy[lowest.dir];

            if (nx < 1 || ny < 1 || nx > n || ny > n)
                return 2;
            return board[nx][ny];
        }

        public int checkHang() {
            Player lowest = hangs.get(0);

            int nx = lowest.x + dx[lowest.dir];
            int ny = lowest.y + dy[lowest.dir];

            return locate[nx][ny];
        }

        public void hangUpAndMove(int toId, boolean flag) {
            Player toPlayer = players.get(toId);
            Player lowest = hangs.get(0);

            locate[lowest.x][lowest.y] = 0;
            if (!flag) {
                for (Player p: hangs) {
                    p.x = toPlayer.x;
                    p.y = toPlayer.y;

                    toPlayer.hangs.add(p);
                }
            } else {
                for (int i = hangs.size() - 1; i >= 0; --i) {
                    Player p = hangs.get(i);

                    p.x = toPlayer.x;
                    p.y = toPlayer.y;

                    toPlayer.hangs.add(p);
                }
            }
            hangs.clear();
        }

        public void move() {
            Player lowest = hangs.get(0);
            locate[lowest.x][lowest.y] = 0;

            int nx = lowest.x + dx[lowest.dir];
            int ny = lowest.y + dy[lowest.dir];

            for (Player p: hangs) {
                p.x = nx;
                p.y = ny;   
            }

            locate[nx][ny] = id;
        }

        public void doWhite() {
            int toId = checkHang();
            if (toId != 0) {
                hangUpAndMove(toId, false);
            } else {
                move();
            }
        }

        public void doRed() {
            int toId = checkHang();
            
            if (toId != 0) {
                hangUpAndMove(toId, true);
            } else {
                move();
                Collections.reverse(hangs);
            }
        }

        public void doBlue() {
            Player lowest = hangs.get(0);

            if (lowest.dir % 2 == 1) {
                lowest.dir += 1;
            } else {
                lowest.dir -= 1;
            }
            int check = checkNext();
            if (check == 1)
                doRed();
            else if (check == 0)
                doWhite();
        }
    }

    public static void printLocate() {
        for (int i = 1; i <= n; ++i) {
            for (int j = 1; j <= n; ++j) {
                System.out.print(locate[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) throws IOException {
        initArgs();
        playGame();
    }

    public static void playGame() {
        int turn = 0;

        while (true) {
            for (int i = 1; i <= k; ++i) {
                for (int j = 1; j <= k; ++j) {
                    Player player = players.get(j);
                    if (player.hangs.isEmpty())
                        continue ;
                    Player lowest = player.hangs.get(0);
                    if (i == lowest.id) {
                        int check = player.checkNext();
                        if (check == 0) {
                            player.doWhite();
                        } else if (check == 1) {
                            player.doRed();
                        } else if (check == 2) {
                            player.doBlue();
                        }
                    }
                }
            }
            ++turn;
            for (Player player: players) {
                if (player == null)
                    continue ;
                if (player.hangs.size() >= 4) {
                    System.out.println(turn);
                    return ;
                }
            }
            if (turn > 1000) {
                System.out.println(-1);
                return ;
            }
        }
    }

    public static void initArgs() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        board = new int[n+1][n+1];
        locate = new int[n+1][n+1];

        for (int i = 1; i <= n; ++i) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; ++j) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        players = new ArrayList();
        players.add(null);
        for (int i = 0; i < k; ++i) {
            st = new StringTokenizer(br.readLine());
            int id = i+1;
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken());
            Player player = new Player(id, x, y, dir);
            
            players.add(player);
            locate[x][y] = id;
        }
    }
}
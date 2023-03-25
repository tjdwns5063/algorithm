import java.math.*;
import java.util.*;

class Solution {
    public class Coord {
        public int x;
        public int y;
        public int cost;

        public Coord(int _x, int _y, int _cost) {
            x = _x;
            y = _y;
            cost = _cost;
        }
    }

    public int solution(int[][] board, int c) {
        int answer = 0;
        int row = board.length;
        int col = board[0].length;
        Coord goal = null;
        PriorityQueue<Coord> pQ = new PriorityQueue<Coord>(new Comparator<Coord>() {
            @Override
            public int compare(Coord c1, Coord c2) {
                return c1.cost - c2.cost;
            }
        });

        int[][] ansBoard = new int[row][col];
        boolean[][] visited = new boolean[row][col];


        for (int i = 0; i < row; ++i) {
            for (int j = 0; j < col; ++j) {
                if (board[i][j] == 2) {
                    pQ.add(new Coord(i, j, 0));
                }
                if (board[i][j] == 3) {
                    goal = new Coord(i, j, 0);
                }
            }
        }

        for (int i = 0; i < row; ++i) {
            for (int j = 0; j < col; ++j) {
                if (board[i][j] == 2) {
                    ansBoard[i][j] = 0;
                } else {
                    ansBoard[i][j] = Integer.MAX_VALUE;
                }
            }
        }

        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, 1, 0, -1};

        while (!pQ.isEmpty()) {
            Coord curr = pQ.poll();
            if (curr.cost != ansBoard[curr.x][curr.y])
                continue ;
            for (int i = 0; i < 4; ++i) {
                int nx = curr.x + dx[i];
                int ny = curr.y + dy[i];

                if (nx < 0 || ny < 0 || nx >= row || ny >= col)
                    continue ;
                int cost = (board[nx][ny] == 1) ? c + 1 : 1;
                if (ansBoard[nx][ny] > ansBoard[curr.x][curr.y] + cost) {
                    ansBoard[nx][ny] = ansBoard[curr.x][curr.y] + cost;
                    pQ.add(new Coord(nx, ny, ansBoard[curr.x][curr.y] + cost));
                }
            }
        }
        // printBoard(ansBoard);
        answer = ansBoard[goal.x][goal.y];
        return answer;
    }

    public void printBoard(int[][] board) {
        for (int i = 0; i < board.length; ++i) {
            for (int j = 0; j < board[0].length; ++j) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
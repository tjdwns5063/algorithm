import java.util.*;

public class programmers_표병합 {
    static String[][] board = new String[51][51];
    static ArrayList<HashSet<ArrayList<Integer>>> merges = new ArrayList();

    public static String[] solution(String[] commands) {
        ArrayList<String> answer = new ArrayList();

        for (int i = 0; i < commands.length; ++i) {
            String[] parsed = commands[i].split(" ");
            if (parsed[0].equals("UPDATE") && parsed.length == 4) {
                update(parsed);
            } else if (parsed[0].equals("UPDATE") && parsed.length == 3) {
                update2(parsed);
            } else if (parsed[0].equals("MERGE")) {
                merge(parsed);
            }
        }
        printBoard(4);
        return answer.toArray(new String[0]);
    }

    public static void update(String[] parsed) {
        ArrayList<Integer> coord = new ArrayList();
        coord.add(Integer.parseInt(parsed[1]));
        coord.add(Integer.parseInt(parsed[2]));

        for (int i = 0; i < merges.size(); ++i) {
            HashSet<ArrayList<Integer>> mergeSet = merges.get(i);
            if (mergeSet.contains(coord)) {
                for (ArrayList<Integer> c: mergeSet) {
                    board[c.get(0)][c.get(1)] = parsed[3];
                }
                return ;
            }
        }
        board[coord.get(0)][coord.get(1)] = parsed[3];
    }

    public static void update2(String[] parsed) {
        String value1 = parsed[1];
        String value2 = parsed[2];

        for (int i = 1; i <= 50; ++i) {
            for (int j = 1; j <= 50; ++j) {
                if (board[i][j] == null)
                    continue ;
                if (board[i][j].equals(value1))
                    board[i][j] = value2;
            }
        }
    }

    public static void merge(String[] parsed) {
        int r1 = Integer.parseInt(parsed[1]);
        int c1 = Integer.parseInt(parsed[2]);
        int r2 = Integer.parseInt(parsed[3]);
        int c2 = Integer.parseInt(parsed[4]);

        ArrayList<Integer> coord1 = new ArrayList();
        ArrayList<Integer> coord2 = new ArrayList();

        coord1.add(r1);
        coord1.add(c1);
        coord2.add(r2);
        coord2.add(c2);

        for (int i = 0; i < merges.size(); ++i) {
            HashSet<ArrayList<Integer>> mergeSet = merges.get(i);
            if (mergeSet.contains(coord)) {
                for (ArrayList<Integer> c: mergeSet) {
                    board[c.get(0)][c.get(1)] = parsed[3];
                }
                return ;
            }
        }
    }

    public static void printBoard(int n) {
        for (int i = 1; i <= n; ++i) {
            for (int j = 1; j <= n; ++j) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        String[] commands = {
            "UPDATE 1 1 menu", 
            "UPDATE 1 2 category", 
            "UPDATE 2 1 bibimbap", 
            "UPDATE 2 2 korean", 
            "UPDATE 2 3 rice", 
            "UPDATE 3 1 ramyeon", 
            "UPDATE 3 2 korean", 
            "UPDATE 3 3 noodle", 
            "UPDATE 3 4 instant", 
            "UPDATE 4 1 pasta", 
            "UPDATE 4 2 italian", 
            "UPDATE 4 3 noodle"};
        
        solution(commands);
    }
}

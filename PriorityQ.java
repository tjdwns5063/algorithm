import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.io.*;

public class PriorityQ {
    public static void main(String[] args) throws IOException {
        PriorityQueue<List<Integer>> pQ = new PriorityQueue(new Comparator<List<Integer>>() {
            public int compare(List<Integer> left, List<Integer> right) {
                if (left.get(1) == right.get(1)) {
                    return left.get(0) - right.get(0);
                }
                return left.get(1) - right.get(1);
            }
        });

        int[][] temp = {{3, 6, 4}, {4, 2, 5}, {1, 0, 5}, {5, 0, 5}};
        for (int i = 0; i < temp.length; ++i) {
            ArrayList<Integer> lst = new ArrayList();
            for (int j = 0; j < 3; ++j) {
                lst.add(temp[i][j]);
            }
            pQ.add(lst);
        }

        //0: priorityScore, 1: startTime, 2: execTime
        long[] answer = new long[11];
        while (!pQ.isEmpty()) {
            List<Integer> curr = pQ.poll();

            System.out.println(curr);
            answer[curr.get(0)] += answer[0] - curr.get(1);
            answer[0] += curr.get(2);
        }
        for (int i = 0; i < 11; ++i) {
            System.out.print(answer[i] + " ");
        }
        System.out.println();
    }
}
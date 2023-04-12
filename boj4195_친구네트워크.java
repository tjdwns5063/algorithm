import java.io.*;
import java.util.*;
import java.math.*;

// time = 3sec
// space = 256mb
// 1 <= n <= 100_000
// 1 <= strlen <= 20
// timeCoplex = O(t*n)

public class boj4195_친구네트워크 {
    static Map<String, String> parent = new HashMap<String, String>();
    static Map<String, Integer> rank = new HashMap<String, Integer>();
    static Map<String, Integer> cnt = new HashMap<String, Integer>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (t > 0) {
            int n = Integer.parseInt(br.readLine());
            String[][] relations = new String[100_004][2];

            for (int i = 0; i < n; ++i) {
                String[] relation = br.readLine().split(" ");
                parent.put(relation[0], relation[0]);
                parent.put(relation[1], relation[1]);
                rank.put(relation[0], 0);
                rank.put(relation[1], 0);
                cnt.put(relation[0], 1);
                cnt.put(relation[1], 1);
                relations[i] = relation;
            }
            for (int i = 0; i < n; ++i) {
                String[] relation = relations[i];
                union(relation[0], relation[1]);
                sb.append(cnt.get(find(relation[0])) + "\n");
            }
            parent.clear();
            rank.clear();
            --t;
        }
        System.out.print(sb);
    }

    private static void union(String a, String b) {
        String rootA = find(a);
        String rootB = find(b);

        if (!rootA.equals(rootB)) {
            int rankA = rank.get(rootA);
            int rankB = rank.get(rootB);
            int sum = cnt.get(rootA) + cnt.get(rootB);

            if (rankA < rankB) {
                parent.put(rootA, rootB);
            } else if (rankB < rankA) {
                parent.put(rootB, rootA);
            } else {
                parent.put(rootA, rootB);
                rank.put(rootB, rankB + 1);
            }
            cnt.put(rootA, sum);
            cnt.put(rootB, sum);
        }
    }

    private static String find(String name) {
        if (name.equals(parent.get(name))) {
            return name;
        }
        String ret = find(parent.get(name));
        parent.put(name, ret);
        return ret;
    }
}
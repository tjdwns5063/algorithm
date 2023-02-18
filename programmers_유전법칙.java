import java.math.*;

class programmers_유전법칙 {
    public String getMendel(int cnt) {
        if (cnt == 1)
            return "RR";
        else if (cnt == 0)
            return "rr";
        return "Rr";
    }
    
    public int[] findParents(int generation, int cnt) {
        int[] parents = new int[generation+1];
        parents[generation] = cnt;
        while (generation >= 2) {
            int parent = 0;
            for (int i = 0; i < cnt; i+=4) {
               ++parent;
            }
            parents[--generation] = parent;
            cnt = parent;
        }
        return parents;
    }
    
    public String getTrait(int[] parents) {
        int[] traits = {-1, 1, 0, 0};
        int curr = traits[parents[2]%4];
        
        for (int i = 2; i < parents.length; ++i) {
            curr = traits[parents[i]%4];
            
            if (curr == 1) {
                return "RR";
            } else if (curr == -1) {
                return "rr";
            }
        }
        return "Rr";
    }
    
    public String[] solution(int[][] queries) {
        String[] answer = new String[queries.length];
        
        for (int i = 0; i < queries.length; ++i) {
            int generation = queries[i][0];
            int cnt = queries[i][1];
            int[] parents = findParents(queries[i][0], queries[i][1]);
            
            if (generation == 1) {
                answer[i] = "Rr";
            } else {
                if (parents.length <= 2) {
                    answer[i]=getMendel(cnt);
                } else {
                    answer[i] = getTrait(parents);
                }
            }
        }
        return answer;
    }
}

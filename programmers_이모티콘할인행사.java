import java.util.*;

class Solution {
    ArrayList<Integer> discountPercent = new ArrayList<Integer>();
    int[] possible = {10, 20, 30, 40};
    int[] answer = new int[2];
    
    public int[] solution(int[][] users, int[] emoticons) {
        dfs(0, users, emoticons);
        return answer;
    }
    
    private void dfs(int depth, int[][] users,int[] emoticons) {
        if (depth >= emoticons.length) {
            solve(users, emoticons);
            return ;
        }
        
        for (int i = 0; i < possible.length; ++i) {
            discountPercent.add(possible[i]);
            dfs(depth + 1, users, emoticons);
            discountPercent.remove(discountPercent.size() - 1);
        }
    }
    
    private void solve(int[][] users, int[] emoticons) {
        int emoticonPlus = 0;
        int totalPrice = 0;
        
        for (int i = 0; i < users.length; ++i) {
            int[] user = users[i];
            int total = 0;
            for (int j = 0; j < emoticons.length; ++j) {
                int dc = discountPercent.get(j);
                int price = emoticons[j] - (int)(emoticons[j] * (dc * 0.01));
                
                if (dc >= user[0]) {
                    total += price;
                }
            }
            if (total >= user[1]) {
                emoticonPlus += 1;
            } else {
                totalPrice += total;
            }
        }

        if (emoticonPlus > answer[0]) {
            answer[0] = emoticonPlus;
            answer[1] = totalPrice;
        } else if (emoticonPlus == answer[0] && totalPrice > answer[1]) {
            answer[1] = totalPrice;
        }
        return;
    }
}
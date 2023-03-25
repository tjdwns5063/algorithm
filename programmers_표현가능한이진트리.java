class Solution {
    public int[] solution(long[] numbers) {
        int[] answer = new int[numbers.length];
        
        
        for (int i = 0; i < numbers.length; ++i) {
            String bin = makeBinary(numbers[i]);
            
            answer[i] = checkPossible(bin);
        }
        return answer;
    }
    
    private String makeBinary(long number) {
        StringBuilder sb = new StringBuilder();
        long num = number;
        
        while (num >= 2) {
            sb.append(num%2);
            num/=2;
        }
        sb.append(num);
        
        int len = 1;
        
        while (len - 1 < sb.length()) {
            len *= 2;
        }
        
        while (sb.length() < len - 1) {
            sb.append(0);
        }
        return (sb.toString());
    }
    
    private int checkPossible(String bin) {
        System.out.println(bin.length() / 2);
        if (check(bin.length() / 2, bin, (bin.length() + 1) / 2) == 0)
            return 0;
        return 1;
    }
    
    private int check(int idx, String bin, int size) {
        if (size == 0) {
            return 1;
        }
        if (bin.charAt(idx) == '0' && bin.charAt(idx - (size / 2)) == '1') {
            return 0;
        }
        if (bin.charAt(idx) == '0' && bin.charAt(idx + (size / 2)) == '1') {
            return 0;
        }
        if (check(idx - (size / 2), bin, size / 2) == 0)
            return 0;
        if (check(idx + (size / 2), bin, size / 2) == 0)
            return 0;
        return 1;
    }
}
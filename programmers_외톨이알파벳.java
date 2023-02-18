class programmers_외톨이알파벳 {
    public String solution(String input_string) {
        StringBuilder answer = new StringBuilder();
        int[] alphabetCnt = new int[26];
        int[] isSingleAlphabet = new int[26];
        
        for (int i = 0; i < input_string.length(); ++i) {
            int idx = (int)input_string.charAt(i) - (int)'a';
            alphabetCnt[idx] += 1;
        }
        int idx = 0;
        while (idx < input_string.length()) {
            int cnt = idx;
            isSingleAlphabet[(int)input_string.charAt(idx) - (int)'a'] += 1;
            while (cnt < input_string.length()) {
                if (input_string.charAt(idx) != input_string.charAt(cnt)) {     
                    break ;
                }
                ++cnt;
            }
            idx = cnt;
        }
        
        for (int i = 0; i < 26; ++i) {
            if (alphabetCnt[i] >= 2 && isSingleAlphabet[i] > 1) {
                char c = (char)(i + (int)'a');
                answer.append(c);
            }
        }
        if (answer.length() == 0) {
            answer.append('N');
        }
        return answer.toString();
    }
}
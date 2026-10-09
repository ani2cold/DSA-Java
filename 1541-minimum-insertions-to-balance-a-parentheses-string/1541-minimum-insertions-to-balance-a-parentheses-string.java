class Solution {
    public int minInsertions(String s) {

        int open = 0;
        int ans = 0;

        for (int i = 0; i < s.length(); i++) {

            if (s.charAt(i) == '(') {
                open++;
            } 
            else {
                // If next character is also ')',
                // we have a complete closing pair
                if (i + 1 < s.length() && s.charAt(i + 1) == ')') {
                    i++;
                } 
                else {
                    // Insert one ')' to complete the pair
                    ans++;
                }

                if (open > 0) {
                    open--;
                } 
                else {
                    // Insert '(' to match this closing pair
                    ans++;
                }
            }
        }

        // Every remaining '(' needs two ')'
        ans += open * 2;

        return ans;
    }
}

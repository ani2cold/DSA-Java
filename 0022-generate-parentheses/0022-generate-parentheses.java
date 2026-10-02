class Solution {
    public List<String> generateParenthesis(int n) {
        
        List<String> ans = new ArrayList<>();
        
        backtrack(ans, "", 0, 0, n);
        
        return ans;
    }
    
    void backtrack(List<String> ans, String current, int open, int close, int n) {
        
        // We have used all brackets
        if (current.length() == 2 * n) {
            ans.add(current);
            return;
        }
        
        // Add '('
        if (open < n) {
            backtrack(ans, current + "(", open + 1, close, n);
        }
        
        // Add ')'
        if (close < open) {
            backtrack(ans, current + ")", open, close + 1, n);
        }
    }
}
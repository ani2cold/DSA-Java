class Solution {
    public List<String> removeInvalidParentheses(String s) {
        
        List<String> ans = new ArrayList<>();
        
        int removeOpen = 0;
        int removeClose = 0;
        
        // Find minimum number of '(' and ')' to remove
        for (char c : s.toCharArray()) {
            if (c == '(') {
                removeOpen++;
            } 
            else if (c == ')') {
                if (removeOpen > 0) {
                    removeOpen--;
                } 
                else {
                    removeClose++;
                }
            }
        }
        
        backtrack(s, 0, removeOpen, removeClose, 0, "", ans);
        
        return new ArrayList<>(new HashSet<>(ans));
    }
    
    void backtrack(String s, int index, int removeOpen, int removeClose,
                   int balance, String current, List<String> ans) {
        
        // Invalid balance
        if (balance < 0) {
            return;
        }
        
        if (index == s.length()) {
            if (removeOpen == 0 && removeClose == 0 && balance == 0) {
                ans.add(current);
            }
            return;
        }
        
        char c = s.charAt(index);
        
        if (c == '(') {
            
            // Remove this '('
            if (removeOpen > 0) {
                backtrack(s, index + 1, removeOpen - 1, removeClose,
                          balance, current, ans);
            }
            
            // Keep this '('
            backtrack(s, index + 1, removeOpen, removeClose,
                      balance + 1, current + c, ans);
        }
        
        else if (c == ')') {
            
            // Remove this ')'
            if (removeClose > 0) {
                backtrack(s, index + 1, removeOpen, removeClose - 1,
                          balance, current, ans);
            }
            
            // Keep this ')' only if it has a matching '('
            if (balance > 0) {
                backtrack(s, index + 1, removeOpen, removeClose,
                          balance - 1, current + c, ans);
            }
        }
        
        else {
            // Normal letter
            backtrack(s, index + 1, removeOpen, removeClose,
                      balance, current + c, ans);
        }
    }
}
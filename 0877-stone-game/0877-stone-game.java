class Solution {
    Integer[][] dp ;
    public boolean stoneGame(int[] piles) {
        int n=piles.length;
        dp= new Integer[n][n];
        return solve(piles,0,n-1) >0;
    }
    private int solve(int[] piles,int left, int right){
        if(left==right){
            return piles[left];
        }
        if( dp[left][right]!=null){
            return dp[left][right];
        }

        int takeleft=piles[left]-solve(piles,left+1,right);
        int takeright=piles[right]-solve(piles,left,right-1);
        dp[left][right]=Math.max(takeleft,takeright);
    
    return dp[left][right];
}
}

// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna
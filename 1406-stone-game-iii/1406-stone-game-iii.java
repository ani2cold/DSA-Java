class Solution {

    Integer[] dp;

    public String stoneGameIII(int[] stoneValue) {

        int n = stoneValue.length;
        dp = new Integer[n];

        int diff = solve(stoneValue, 0);

        if (diff > 0)
            return "Alice";

        if (diff < 0)
            return "Bob";

        return "Tie";
    }

    private int solve(int[] stoneValue, int i) {

        if (i >= stoneValue.length)
            return 0;

        if (dp[i] != null)
            return dp[i];

        int ans = Integer.MIN_VALUE;
        int sum = 0;

        for (int k = 0; k < 3 && i + k < stoneValue.length; k++) {

            sum += stoneValue[i + k];

            ans = Math.max(ans, sum - solve(stoneValue, i + k + 1));
        }

        return dp[i] = ans;
    }
}

// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna
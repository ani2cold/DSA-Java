class Solution {

    int[][] dp;
    int[] prefix;

    public int stoneGameV(int[] stoneValue) {

        int n = stoneValue.length;

        dp = new int[n][n];
        prefix = new int[n + 1];

        // Prefix sum
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + stoneValue[i];
        }

        // -1 means not calculated yet
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j] = -1;
            }
        }

        return solve(0, n - 1, stoneValue);
    }

    private int solve(int i, int j, int[] stoneValue) {

        // Only one stone
        if (i == j) {
            return 0;
        }

        // Already calculated
        if (dp[i][j] != -1) {
            return dp[i][j];
        }

        int ans = 0;

        int leftSum = 0;
        int rightSum = prefix[j + 1] - prefix[i];

        for (int k = i; k < j; k++) {

            // Move stoneValue[k] from right to left
            leftSum += stoneValue[k];
            rightSum -= stoneValue[k];

            if (leftSum < rightSum) {

                ans = Math.max(
                    ans,
                    leftSum + solve(i, k, stoneValue)
                );

            } else if (leftSum > rightSum) {

                ans = Math.max(
                    ans,
                    rightSum + solve(k + 1, j, stoneValue)
                );

            } else {

                ans = Math.max(
                    ans,
                    Math.max(
                        leftSum + solve(i, k, stoneValue),
                        rightSum + solve(k + 1, j, stoneValue)
                    )
                );
            }
        }

        dp[i][j] = ans;

        return ans;
    }
}
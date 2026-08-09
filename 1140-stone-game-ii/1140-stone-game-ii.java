class Solution {

    int[] suffix;

    public int stoneGameII(int[] piles) {

        int n = piles.length;

        suffix = new int[n + 1];

        for (int i = n - 1; i >= 0; i--) {
            suffix[i] = piles[i] + suffix[i + 1];
        }

        return solve(piles, 0, 1);
    }

    private int solve(int[] piles, int index, int M) {

        if (index >= piles.length) {
            return 0;
        }

        if (2 * M >= piles.length - index) {
            return suffix[index];
        }

        int best = 0;

        for (int X = 1; X <= 2 * M; X++) {

            int opponent = solve(
                piles,
                index + X,
                Math.max(M, X)
            );

            int current = suffix[index] - opponent;

            best = Math.max(best, current);
        }

        return best;
    }
}

// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna
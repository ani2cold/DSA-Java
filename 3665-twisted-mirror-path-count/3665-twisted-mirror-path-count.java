class Solution {
    public int uniquePaths(int[][] grid) {
        int MOD = 1_000_000_007;
        int m = grid.length;
        int n = grid[0].length;

        long[][] right = new long[m][n];
        long[][] down = new long[m][n];

        // Start by moving right or down.
        right[0][0] = 1;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                long ways = (right[i][j] + down[i][j]) % MOD;

                if (i == 0 && j == 0) {
                    ways = 1;
                }

                if (i == m - 1 && j == n - 1) {
                    return (int) ways;
                }

                if (grid[i][j] == 1) {
                    // Mirror: right turns down, down turns right.
                    if (i + 1 < m) {
                        down[i + 1][j] =
                            (down[i + 1][j] + right[i][j]) % MOD;
                    }

                    if (j + 1 < n) {
                        right[i][j + 1] =
                            (right[i][j + 1] + down[i][j]) % MOD;
                    }
                } else {
                    // Empty cell: robot can move right or down.
                    if (j + 1 < n) {
                        right[i][j + 1] =
                            (right[i][j + 1] + ways) % MOD;
                    }

                    if (i + 1 < m) {
                        down[i + 1][j] =
                            (down[i + 1][j] + ways) % MOD;
                    }
                }
            }
        }

        return 0;
    }
}
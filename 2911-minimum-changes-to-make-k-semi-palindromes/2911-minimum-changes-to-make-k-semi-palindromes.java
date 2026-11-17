import java.util.*;

class Solution {
    public int minimumChanges(String s, int k) {
        int n = s.length();
        int[][] cost = new int[n][n];

        // Precompute minimum changes for every substring.
        for (int l = 0; l < n; l++) {
            for (int r = l + 1; r < n; r++) {
                int len = r - l + 1;
                cost[l][r] = Integer.MAX_VALUE;

                // Try every proper divisor of the substring length.
                for (int d = 1; d < len; d++) {
                    if (len % d != 0) continue;

                    int changes = 0;

                    // Build groups based on positions modulo d.
                    for (int rem = 0; rem < d; rem++) {
                        int count = (len - 1 - rem) / d + 1;

                        // Each group must be a palindrome.
                        for (int x = 0; x < count / 2; x++) {
                            int left = l + rem + x * d;
                            int right = l + rem + (count - 1 - x) * d;

                            if (s.charAt(left) != s.charAt(right)) {
                                changes++;
                            }
                        }
                    }

                    cost[l][r] = Math.min(cost[l][r], changes);
                }
            }
        }

        // dp[p][i] = minimum changes to partition
        // the first i characters into p semi-palindromes.
        int INF = 1_000_000_000;
        int[][] dp = new int[k + 1][n + 1];

        for (int[] row : dp) {
            Arrays.fill(row, INF);
        }

        dp[0][0] = 0;

        for (int p = 1; p <= k; p++) {
            for (int i = 2 * p; i <= n; i++) {
                for (int j = 2 * (p - 1); j <= i - 2; j++) {
                    if (dp[p - 1][j] == INF) continue;

                    dp[p][i] = Math.min(
                        dp[p][i],
                        dp[p - 1][j] + cost[j][i - 1]
                    );
                }
            }
        }

        return dp[k][n];
    }
}
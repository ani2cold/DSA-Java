import java.util.*;

class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int n = items.length;

        // Calculate the total value unlocked by buying each item type.
        int[] value = new int[n];

        for (int i = 0; i < n; i++) {
            value[i] = 1;

            for (int j = 0; j < n; j++) {
                if (i != j && items[j][0] % items[i][0] == 0) {
                    value[i]++;
                }
            }
        }

        // Group items by their purchase price.
        List<Integer>[] groups = new ArrayList[1501];

        for (int i = 0; i <= 1500; i++) {
            groups[i] = new ArrayList<>();
        }

        for (int i = 0; i < n; i++) {
            groups[items[i][1]].add(i);
        }

        // dp[c] = maximum value using items costing strictly
        // more than the current price, with total cost c.
        int[] dp = new int[budget + 1];

        int ans = 0;

        // Process prices from highest to lowest.
        for (int p = 1500; p >= 1; p--) {
            if (groups[p].isEmpty()) {
                continue;
            }

            // States containing at least one item costing p.
            int[] current = new int[budget + 1];
            Arrays.fill(current, -1);

            for (int idx : groups[p]) {
                int reward = value[idx];

                for (int cost = budget; cost >= p; cost--) {

                    // Add this item to a subset containing
                    // only more expensive items.
                    if (dp[cost - p] >= 0) {
                        current[cost] = Math.max(
                            current[cost],
                            dp[cost - p] + reward
                        );
                    }

                    // Add this item to an existing subset
                    // that already contains an item priced p.
                    if (current[cost - p] >= 0) {
                        current[cost] = Math.max(
                            current[cost],
                            current[cost - p] + reward
                        );
                    }
                }
            }

            // Evaluate subsets whose cheapest item costs p.
            for (int cost = 0; cost <= budget; cost++) {
                if (current[cost] >= 0) {
                    int extra = (budget - cost) / p;

                    ans = Math.max(
                        ans,
                        current[cost] + extra
                    );

                    dp[cost] = Math.max(dp[cost], current[cost]);
                }
            }
        }

        return ans;
    }
}
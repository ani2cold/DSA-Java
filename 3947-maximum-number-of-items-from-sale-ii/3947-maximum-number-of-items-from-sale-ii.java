import java.util.Arrays;
import java.util.Comparator;

class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int n = items.length;

        // Count frequency of each factor.
        int[] freq = new int[n + 1];
        int maxFactor = 0;

        for (int[] item : items) {
            freq[item[0]]++;
            maxFactor = Math.max(maxFactor, item[0]);
        }

        // Calculate the number of distinct free-item opportunities
        // for each factor.
        int[] degree = new int[maxFactor + 1];

        for (int f = 1; f <= maxFactor; f++) {
            for (int multiple = f; multiple <= maxFactor; multiple += f) {
                degree[f] += freq[multiple];
            }

            degree[f]--; // Exclude the item itself.
        }

        // Sort items by price.
        Arrays.sort(items, Comparator.comparingInt(a -> a[1]));

        int minPrice = items[0][1];
        long remaining = budget;
        long bonusCopies = 0;

        // Select the cheapest useful bonus-generating purchases.
        for (int[] item : items) {
            int factor = item[0];
            int price = item[1];

            // A bonus purchase costing more than 2 * minPrice
            // is never better than buying ordinary cheapest items.
            if (price > 2L * minPrice) {
                break;
            }

            int available = degree[factor];

            long take = Math.min(
                (long) available,
                remaining / price
            );

            bonusCopies += take;
            remaining -= take * price;
        }

        // Spend the remaining budget on cheapest ordinary copies.
        long answer = 2L * bonusCopies + remaining / minPrice;

        return (int) answer;
    }
}
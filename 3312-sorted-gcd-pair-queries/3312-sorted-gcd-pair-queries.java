import java.util.Arrays;

public class Solution {
    public int[] gcdValues(int[] nums, long[] queries) {
        // Step 1: Find the maximum value in nums to define our frequency limits
        int maxNum = 0;
        for (int num : nums) {
            maxNum = Math.max(maxNum, num);
        }

        // Step 2: Store the direct frequencies of elements in nums
        int[] freq = new int[maxNum + 1];
        for (int num : nums) {
            freq[num]++;
        }

        // countDivisor[d] stores how many elements in nums are multiples of d
        long[] countDivisor = new long[maxNum + 1];
        for (int d = 1; d <= maxNum; d++) {
            for (int multiple = d; multiple <= maxNum; multiple += d) {
                countDivisor[d] += freq[multiple];
            }
        }

        // countGcdPair[g] will store the exact number of pairs with GCD equal to g
        long[] countGcdPair = new long[maxNum + 1];
        
        // Step 3: Loop backwards to apply inclusion-exclusion principle
        for (int gcd = maxNum; gcd >= 1; gcd--) {
            long totalMultiples = countDivisor[gcd];
            // Total possible pairs that share 'gcd' as a divisor
            countGcdPair[gcd] = totalMultiples * (totalMultiples - 1) / 2;
            
            // Subtract over-counted pairs that have a strictly larger common divisor
            for (int largerGcd = 2 * gcd; largerGcd <= maxNum; largerGcd += gcd) {
                countGcdPair[gcd] -= countGcdPair[largerGcd];
            }
        }

        // Step 4: Build a prefix sum array of GCD pair counts
        long[] prefixCountGcdPair = new long[maxNum + 1];
        for (int gcd = 1; gcd <= maxNum; gcd++) {
            prefixCountGcdPair[gcd] = prefixCountGcdPair[gcd - 1] + countGcdPair[gcd];
        }

        // Step 5: Answer each query efficiently using Binary Search
        int[] answer = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            long targetIndex = queries[i];
            
            // Binary search to find the smallest GCD value where prefix sum > targetIndex
            int low = 1, high = maxNum, bestGcd = maxNum;
            while (low <= high) {
                int mid = low + (high - low) / 2;
                if (prefixCountGcdPair[mid] > targetIndex) {
                    bestGcd = mid;
                    high = mid - 1; // Try to find a smaller valid GCD
                } else {
                    low = mid + 1;
                }
            }
            answer[i] = bestGcd;
        }

        return answer;
    }
}


// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna
class Solution {
    public long[] resultArray(int[] nums, int k) {

        long[] result = new long[k];

        // dp[r] = number of subarrays ending at previous position
        // whose product % k == r
        long[] dp = new long[k];

        for (int num : nums) {

            int value = num % k;

            long[] next = new long[k];

            // Start a new subarray with nums[i]
            next[value]++;

            // Extend previous subarrays
            for (int r = 0; r < k; r++) {
                int newRemainder = (r * value) % k;
                next[newRemainder] += dp[r];
            }

            // Add all subarrays ending here to the answer
            for (int r = 0; r < k; r++) {
                result[r] += next[r];
            }

            dp = next;
        }

        return result;
    }
}
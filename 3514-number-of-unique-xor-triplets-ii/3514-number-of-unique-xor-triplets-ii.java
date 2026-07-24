class Solution {
    public int uniqueXorTriplets(int[] nums) {
        int n = nums.length;
        
        int maxVal = 0;
        for (int num : nums) {
            maxVal = Math.max(maxVal, num);
        }
        
        int limit = 1;
        while (limit <= maxVal) {
            limit <<= 1;
        }
        
        boolean[] s1 = new boolean[limit];
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                s1[nums[i] ^ nums[j]] = true;
            }
        }
        
        boolean[] s2 = new boolean[limit];
        for (int val = 0; val < limit; val++) {
            if (s1[val]) {
                for (int num : nums) {
                    s2[val ^ num] = true;
                }
            }
        }
        
        int uniqueCount = 0;
        for (int val = 0; val < limit; val++) {
            if (s2[val]) {
                uniqueCount++;
            }
        }
        
        return uniqueCount;
    }
}


// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna
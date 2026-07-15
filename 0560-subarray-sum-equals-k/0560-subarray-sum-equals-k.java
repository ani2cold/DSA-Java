class Solution {
    public int subarraySum(int[] nums, int k) {
        int[] prefix = new int[nums.length + 1];

for (int i = 0; i < nums.length; i++) {
    prefix[i + 1] = prefix[i] + nums[i];
}

int count = 0;

for (int i = 1; i <= nums.length; i++) {

    for (int j = 0; j < i; j++) {

        if (prefix[i] - prefix[j] == k)
            count++;
    }
}

return count;
        
    }
}

// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna
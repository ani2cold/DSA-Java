class Solution {

    public long gcdSum(int[] nums) {

        int n = nums.length;
        int[] prefix = new int[n];

        int mx = 0;

        for (int i = 0; i < n; i++) {
            mx = Math.max(mx, nums[i]);
            prefix[i] = gcd(nums[i], mx);
        }

        Arrays.sort(prefix);

        long ans = 0;

        int left = 0;
        int right = n - 1;

        while (left < right) {
            ans += gcd(prefix[left], prefix[right]);
            left++;
            right--;
        }

        return ans;
    }

    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }
}

// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna
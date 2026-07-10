class Solution {

    public int atmost(int[] nums, int k) {



        int left = 0;
        int count = 0;
        int odd = 0;

        for (int right = 0; right < nums.length; right++) {

            if (nums[right] % 2 == 1)
                odd++;

            while (odd > k) {

                if (nums[left] % 2 == 1)
                    odd--;

                left++;
            }

            count += right - left + 1;
        }

        return count;
    }

    public int numberOfSubarrays(int[] nums, int k) {

        return atmost(nums, k) - atmost(nums, k - 1);
    }
}

// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna
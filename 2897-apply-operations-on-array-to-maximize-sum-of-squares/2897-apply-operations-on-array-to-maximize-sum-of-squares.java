import java.util.*;

class Solution {
    public int maxSum(List<Integer> nums, int k) {
        int MOD = 1000000007;
        int[] count = new int[31];

        // Count how many numbers have each bit set
        for (int num : nums) {
            for (int b = 0; b < 31; b++) {
                if ((num & (1 << b)) != 0) {
                    count[b]++;
                }
            }
        }

        long ans = 0;

        // Construct k numbers using the available bits
        for (int i = 0; i < k; i++) {
            long num = 0;

            for (int b = 30; b >= 0; b--) {
                if (count[b] > 0) {
                    num |= (1L << b);
                    count[b]--;
                }
            }

            ans = (ans + num * num) % MOD;
        }

        return (int) ans;
    }
}
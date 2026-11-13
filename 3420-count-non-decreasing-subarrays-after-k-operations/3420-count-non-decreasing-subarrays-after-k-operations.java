import java.util.*;

class Solution {
    public long countNonDecreasingSubarrays(int[] nums, int k) {
        int n = nums.length;
        Deque<int[]> dq = new ArrayDeque<>();

        long cost = 0;
        long ans = 0;
        int j = n - 1;

        for (int i = n - 1; i >= 0; i--) {
            int num = nums[i];
            int count = 1;

            // Merge smaller groups into the current group.
            while (!dq.isEmpty() && dq.peekLast()[0] < num) {
                int[] p = dq.pollLast();

                count += p[1];
                cost += (long) (num - p[0]) * p[1];
            }

            dq.offerLast(new int[]{num, count});

            // Shrink the window until its cost is within k.
            while (cost > k) {
                int[] p = dq.peekFirst();

                cost -= (long) (p[0] - nums[j]);
                j--;

                p[1]--;

                if (p[1] == 0) {
                    dq.pollFirst();
                }
            }

            // All subarrays [i...j], [i...j-1], ... are valid.
            ans += j - i + 1;
        }

        return ans;
    }
}
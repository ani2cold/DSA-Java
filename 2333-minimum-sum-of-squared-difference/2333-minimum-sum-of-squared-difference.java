class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        int n = nums1.length;
        long[] diff = new long[n];

        long k = (long) k1 + k2;
        long max = 0;
        long sum = 0;

        for (int i = 0; i < n; i++) {
            diff[i] = Math.abs(nums1[i] - nums2[i]);
            max = Math.max(max, diff[i]);
            sum += diff[i];
        }

        if (sum <= k) {
            return 0;
        }

        long left = 0, right = max;

        while (left < right) {
            long mid = left + (right - left) / 2;
            long needed = 0;

            for (long d : diff) {
                if (d > mid) {
                    needed += d - mid;
                }
            }

            if (needed <= k) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        long limit = left;
        long answer = 0;
        long remaining = k;

        // Reduce all differences greater than limit
        for (long d : diff) {
            if (d > limit) {
                remaining -= d - limit;
                d = limit;
            }
            answer += d * d;
        }

        // Use remaining operations to reduce some differences by one
        for (long d : diff) {
            if (remaining == 0) {
                break;
            }

            if (d >= limit && d > 0) {
                // Difference limit becomes limit - 1
                answer -= 2 * limit - 1;
                remaining--;
            }
        }

        return answer;
    }
}
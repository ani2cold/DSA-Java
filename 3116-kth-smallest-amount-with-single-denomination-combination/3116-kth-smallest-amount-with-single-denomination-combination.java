class Solution {
    public long findKthSmallest(int[] coins, int k) {

        long left = 1;
        long right = (long) coins[0] * k;

        for (int coin : coins) {
            right = Math.min(right, (long) coin * k);
        }

        while (left < right) {

            long mid = left + (right - left) / 2;

            if (count(mid, coins) >= k) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    private long count(long x, int[] coins) {

        int n = coins.length;
        long total = 0;

        // All non-empty subsets
        for (int mask = 1; mask < (1 << n); mask++) {

            long lcm = 1;
            int bits = 0;
            boolean overflow = false;

            for (int i = 0; i < n; i++) {

                if ((mask & (1 << i)) != 0) {

                    bits++;

                    long g = gcd(lcm, coins[i]);

                    // Prevent overflow
                    lcm = lcm / g * coins[i];

                    if (lcm > x) {
                        overflow = true;
                        break;
                    }
                }
            }

            if (overflow) {
                continue;
            }

            long value = x / lcm;

            if (bits % 2 == 1) {
                total += value;
            } else {
                total -= value;
            }
        }

        return total;
    }

    private long gcd(long a, long b) {

        while (b != 0) {
            long temp = a % b;
            a = b;
            b = temp;
        }

        return a;
    }
}
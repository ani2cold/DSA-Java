class Solution {
    public int maxLength(int[] nums) {
        int n = nums.length;
        int ans = 1;

        for (int i = 0; i < n; i++) {
            long product = 1;
            int gcd = 0;
            long lcm = 1;

            for (int j = i; j < n; j++) {
                int x = nums[j];

                product *= x;
                gcd = findGCD(gcd, x);
                lcm = (lcm / findGCD((int) lcm, x)) * x;

                if (product == (long) gcd * lcm) {
                    ans = Math.max(ans, j - i + 1);
                }
            }
        }

        return ans;
    }

    private int findGCD(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}
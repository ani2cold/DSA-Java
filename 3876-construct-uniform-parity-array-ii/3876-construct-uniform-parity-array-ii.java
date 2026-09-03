class Solution {
    public boolean uniformArray(int[] nums1) {

        int min = nums1[0];
        boolean allEven = true;

        for (int num : nums1) {

            if (num < min) {
                min = num;
            }

            if (num % 2 != 0) {
                allEven = false;
            }
        }

        // All numbers can remain even
        if (allEven) {
            return true;
        }

        // Smallest number is odd,
        // so every even number can subtract it
        if (min % 2 != 0) {
            return true;
        }

        return false;
    }
}
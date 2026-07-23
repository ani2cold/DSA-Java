class Solution {
    public int uniqueXorTriplets(int[] nums) {
        
        int n=nums.length;

        if(n<=2){
            return n;
        }

        int bits = 32 - Integer.numberOfLeadingZeros(n);

        return 1 << bits;
    }
}

// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna
class NumArray {

    int[] prefix;

    public NumArray(int[] nums) {
        prefix=new int[nums.length + 1];

        for(int i=0;i<nums.length;i++){
            prefix[i+1]=prefix[i] + nums[i];
        }
    }
    
    public int sumRange(int left, int right) {
        return prefix[right+1] - prefix[left];
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * int param_1 = obj.sumRange(left,right);
 */

// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna
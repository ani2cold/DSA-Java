class Solution {
    public int removeDuplicates(int[] nums) {

        if(nums.length==0)return 0;

        int left=0;
        for(int right=0;right<nums.length;right++){
            if(nums[right]!=nums[left]){
                left++;
                nums[left]=nums[right];
            }
        }
        return left+1;
        
    }
}

// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna
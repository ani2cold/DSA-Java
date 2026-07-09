class Solution {
    public int atmost(int[] nums, int goal){
        int left=0;
        int sum=0;
        int count=0;
        if(goal<0) return 0;

        for(int right=0; right<nums.length;right++){
            sum+= nums[right];
            while(sum>goal){
                sum-=nums[left];
                left++;
            }
            count+=right-left+1;
        }
        return count;
    }
    public int numSubarraysWithSum(int[] nums, int goal) {
        //your code goes here
        return atmost(nums,goal) - atmost(nums,goal-1);
    }
}

// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna
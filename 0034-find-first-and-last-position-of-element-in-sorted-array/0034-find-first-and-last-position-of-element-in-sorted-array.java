class Solution {
    public int[] searchRange(int[] nums, int target) {
        
        int first=-1;
        int last=-1;

        int left=0;
        int right=nums.length-1;

        while(left<=right){

            int mid=left+ (right-left)/2;
            if(nums[mid]==target){
                first=mid;
                right=mid-1;
            }else if(nums[mid]<target){
                left=mid+1;
            }else{
                right=mid-1;
            }
        }

         left=0;
         right=nums.length-1;

        while(left<=right){
            
            int mid=left+ (right-left)/2;

            if(nums[mid]==target){


                last=mid;
                left=mid+1;
            }else if(nums[mid]<target){
                left=mid+1;
            }else{
                right=mid-1;
            }
        }
        return new int[]{first,last};
    }
}

// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna
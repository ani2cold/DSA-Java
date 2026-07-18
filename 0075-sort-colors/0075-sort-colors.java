class Solution {
    public void sortColors(int[] nums) {

        int left=0;
        int mid=0;
        int right=nums.length-1;

        while(mid<=right){

            if(nums[mid]==0){

                int temp=nums[left];
                nums[left]=nums[mid];
                nums[mid]=temp;
                left++;
                mid++;
            }
            
            else if(nums[mid]==1){
                mid++;
            }
            
            else{
                
                if(nums[mid]==2){

                    int temp=nums[mid];
                    nums[mid]=nums[right];
                    nums[right]=temp;
                    right--;
                }
            }
        }
        
    }
}

// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna
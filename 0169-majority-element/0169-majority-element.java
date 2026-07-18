class Solution {
    public int majorityElement(int[] nums) {
        
        int count=0;
        int curr=0;

        for(int num:nums){

            if(count==0){
                curr=num;
            }
            if(curr==num){
                count++;
            }else{
                count--;
            }
        }
        return curr;
    }
}

// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna
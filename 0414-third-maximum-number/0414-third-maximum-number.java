class Solution {
    public int thirdMax(int[] nums) {

        long first=Long.MIN_VALUE;
        long second=Long.MIN_VALUE;
        long third=Long.MIN_VALUE;
        for(int num:nums){

        if(num==first || num==second || num==third){
            continue;
        }

        if(num>first){
            third=second;
            second=first;
            first=num;
        }else if(num>second){
            third=second;
            second=num;

        }else if (num > third){
            third=num;
        }
        }
        return third==Long.MIN_VALUE ? (int) first : (int) third;
    }
}

// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna
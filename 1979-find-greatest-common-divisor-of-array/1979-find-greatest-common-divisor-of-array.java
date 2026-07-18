class Solution {
    public int findGCD(int[] nums) {

        int min=nums[0];
        int max=nums[0];

        for(int num:nums){

            if(num<min)
                min=num;
            

            if(num>max)
                max=num;
            
        }
            return gcd(max,min);

    }
        private int gcd(int a, int b){
            while(b!=0){

                int temp=b;
                b=a%b;
                a=temp;
            }
        
        return a;
    }
}


// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna
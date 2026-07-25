class Solution {
    public int maxProduct(int n) {

        int max1=0;
        int max2=0;

        while(n>0){
            int digit=n%10;
            n /=10;
        
        if(digit>=max1){
            max2=max1;
            max1=digit;
        }else if(digit>max2){
            max2=digit;
        }
        }
        return max1*max2;
    }
}

// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna
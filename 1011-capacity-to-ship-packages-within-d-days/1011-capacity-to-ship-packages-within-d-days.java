class Solution {
    public int shipWithinDays(int[] weights, int days) {

        int left=0;
        int right=0;

        for(int weight:weights){
            left=Math.max(left,weight);
            right+= weight;
        }

        while(left<right){
            int mid=left+(right-left)/2;

            int requireddays=1;
            int currLoad=0;

            for(int weight:weights){
                if(currLoad+weight>mid){
                    requireddays++;
                    currLoad=0;
                
                }
                currLoad+=weight;
            }
            if(requireddays<=days){
                right=mid;
            }else{
                left=mid+1;
            }
        }
        return left;
    }
}

// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna
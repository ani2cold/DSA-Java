class Solution {
    public int characterReplacement(String s, int k) {
                
        int left=0;
        int right=0;
        int[] count=new int[26];
        int maxf=0;
        int maxl=0;

        while(right<s.length()){
            count[s.charAt(right) - 'A']++;
            maxf=Math.max(maxf,count[s.charAt(right) - 'A']);

            if((right-left+1) - maxf > k){
                count[s.charAt(left) - 'A']--;
                left++;
            }
            maxl=Math.max(maxl,right-left+1);
            right++;
        }
         return maxl;
        
    }
}

// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna
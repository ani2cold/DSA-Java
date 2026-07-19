class Solution {
    public int myAtoi(String s) {

        int i=0;
        int n=s.length();
        int sign=1;
        long ans=0;

        while(i<n && s.charAt(i)== ' '){
            i++;
        }

        if(i<n &&  (s.charAt(i)=='+' || s.charAt(i)=='-')){
            if(s.charAt(i)=='-'){
                sign=-1;
            }
            i++;
        }
        while(i<n && Character.isDigit(s.charAt(i))){
            int digit=s.charAt(i) - '0';

            if(sign==1){

            if(ans>Integer.MAX_VALUE/10 || (ans==Integer.MAX_VALUE/10 && digit>7)){
                return Integer.MAX_VALUE;
             }
            }
            else{
                if(ans>Integer.MAX_VALUE/10 || (ans==Integer.MAX_VALUE/10 && digit>8)){
                    return Integer.MIN_VALUE;
                }

            }
            ans=ans*10+digit;
            i++;
        }
            return (int)(ans*sign);
    }
}

// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna
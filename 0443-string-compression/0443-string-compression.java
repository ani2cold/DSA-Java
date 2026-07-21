class Solution {
    public int compress(char[] chars) {

        int i=0;
        int index=0;

        while(i<chars.length){
            char current=chars[i];
            int count=0;

            while(i<chars.length && chars[i]==current){
                count++;
                i++;
            }
            chars[index++]=current;

            if(count>1){
                String num= String.valueOf(count);
                for(char c:num.toCharArray()){
                    chars[index++]=c;
                }
            }
        }
        return index;
    }
}

// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna
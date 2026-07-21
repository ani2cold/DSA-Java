class Solution {
    public int firstUniqChar(String s) {

        int[] freq= new int[26];

        for( char c :s.toCharArray()){
            freq[c- 'a']++;
        }
        for(int i=0;i<s.length();i++){
            if(freq[s.charAt(i)- 'a']==1)
            return i;
        }
        return -1;
    }
}

// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna
class Solution {
    public int minimumPushes(String word) {
        int[] freq= new int[26];

        for(char ch:word.toCharArray()){
            freq[ch - 'a']++;
        }

        Arrays.sort(freq);
        int push=0;
        int position=0;

        for(int i=25;i>=0;i--){
            if(freq[i]==0) break;

            push+=freq[i] * (position/8 + 1);
            position++;
        }
        return push;
    }
}

// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna
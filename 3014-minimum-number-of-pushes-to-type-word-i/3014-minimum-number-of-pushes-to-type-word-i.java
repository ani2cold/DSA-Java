class Solution {
    public int minimumPushes(String word) {
        int n=word.length();
        int push=0;

        for(int i=0;i<n;i++){
            push+=(i/8)+1;
        }
        return push;
    }
}

// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna
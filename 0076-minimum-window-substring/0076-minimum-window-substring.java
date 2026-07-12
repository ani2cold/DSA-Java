class Solution {
    public String minWindow(String s, String t) {
                int[] hash = new int[256];

        for (int i = 0; i < t.length(); i++) {
            hash[t.charAt(i)]++;
        }

        int left = 0;
        int right = 0;

        int count = 0;
        int minLen = Integer.MAX_VALUE;
        int startIndex = -1;

        while (right < s.length()) {

            if (hash[s.charAt(right)] > 0)
                count++;

            hash[s.charAt(right)]--;

            while (count == t.length()) {

                if (right - left + 1 < minLen) {
                    minLen = right - left + 1;
                    startIndex = left;
                }

                hash[s.charAt(left)]++;

                if (hash[s.charAt(left)] > 0)
                    count--;

                left++;
            }

            right++;
        }

        if (startIndex == -1)
            return "";

        return s.substring(startIndex, startIndex + minLen);
    }
}

// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna
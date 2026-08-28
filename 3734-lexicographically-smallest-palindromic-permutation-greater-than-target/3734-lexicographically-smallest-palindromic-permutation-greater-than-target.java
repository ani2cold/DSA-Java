class Solution {
    public String lexPalindromicPermutation(String s, String target) {
        int n = s.length();
        int half = n / 2;

        int[] count = new int[26];

        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }

        // More than one odd count -> no palindrome possible
        int odd = 0;
        char middle = 0;

        for (int i = 0; i < 26; i++) {
            if (count[i] % 2 == 1) {
                odd++;
                middle = (char) ('a' + i);
            }
        }

        if (odd > 1) {
            return "";
        }

        // Number of each character available in the left half
        int[] halfCount = new int[26];

        for (int i = 0; i < 26; i++) {
            halfCount[i] = count[i] / 2;
        }

        // Try to make left half equal to target's left half
        StringBuilder left = new StringBuilder();
        int[] temp = halfCount.clone();

        boolean possible = true;

        for (int i = 0; i < half; i++) {
            int c = target.charAt(i) - 'a';

            if (temp[c] == 0) {
                possible = false;
                break;
            }

            left.append(target.charAt(i));
            temp[c]--;
        }

        // If left half is exactly equal, build the palindrome
        // and check whether it is already greater than target.
        if (possible) {
            String palindrome = build(left.toString(), middle, n);

            if (palindrome.compareTo(target) > 0) {
                return palindrome;
            }
        }

        // Find the next possible left half greater than target's left half.
        // Start from the RIGHT so we get the smallest possible answer.
        for (int i = half - 1; i >= 0; i--) {

            temp = halfCount.clone();

            // Match target[0 ... i-1]
            boolean ok = true;

            for (int j = 0; j < i; j++) {
                int c = target.charAt(j) - 'a';

                if (temp[c] == 0) {
                    ok = false;
                    break;
                }

                temp[c]--;
            }

            if (!ok) {
                continue;
            }

            int cur = target.charAt(i) - 'a';

            // Choose the smallest character greater than target[i]
            for (int j = cur + 1; j < 26; j++) {

                if (temp[j] > 0) {

                    StringBuilder newLeft = new StringBuilder();

                    // Same prefix
                    newLeft.append(target.substring(0, i));

                    // First greater character
                    newLeft.append((char) ('a' + j));

                    temp[j]--;

                    // Smallest possible remaining characters
                    for (int x = 0; x < 26; x++) {
                        while (temp[x] > 0) {
                            newLeft.append((char) ('a' + x));
                            temp[x]--;
                        }
                    }

                    return build(newLeft.toString(), middle, n);
                }
            }
        }

        return "";
    }

    private String build(String left, char middle, int n) {
        StringBuilder ans = new StringBuilder();

        ans.append(left);

        if (n % 2 == 1) {
            ans.append(middle);
        }

        ans.append(new StringBuilder(left).reverse());

        return ans.toString();
    }
}
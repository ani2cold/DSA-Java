class Solution {
    public String lexGreaterPermutation(String s, String target) {
        int n = s.length();

        int[] count = new int[26];

        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }

        for (int i = n - 1; i >= 0; i--) {

            int[] temp = count.clone();

            // Use target[0 ... i-1]
            boolean possible = true;

            for (int j = 0; j < i; j++) {
                int x = target.charAt(j) - 'a';

                if (temp[x] == 0) {
                    possible = false;
                    break;
                }

                temp[x]--;
            }

            if (!possible) {
                continue;
            }

            // Find smallest character greater than target[i]
            int cur = target.charAt(i) - 'a';

            for (int j = cur + 1; j < 26; j++) {

                if (temp[j] > 0) {

                    StringBuilder ans = new StringBuilder();

                    // Same prefix as target
                    ans.append(target.substring(0, i));

                    // Make this position greater
                    ans.append((char) ('a' + j));

                    temp[j]--;

                    // Put remaining characters in sorted order
                    for (int x = 0; x < 26; x++) {
                        while (temp[x] > 0) {
                            ans.append((char) ('a' + x));
                            temp[x]--;
                        }
                    }

                    return ans.toString();
                }
            }
        }

        return "";
    }
}
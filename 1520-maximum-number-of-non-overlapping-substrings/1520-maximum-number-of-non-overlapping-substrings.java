import java.util.*;

class Solution {
    public List<String> maxNumOfSubstrings(String s) {

        int n = s.length();

        int[] first = new int[26];
        int[] last = new int[26];

        Arrays.fill(first, -1);

        // Find first and last occurrence of every character
        for (int i = 0; i < n; i++) {
            int c = s.charAt(i) - 'a';

            if (first[c] == -1) {
                first[c] = i;
            }

            last[c] = i;
        }

        List<int[]> intervals = new ArrayList<>();

        // Try to create a valid substring starting
        // at the first occurrence of each character
        for (int c = 0; c < 26; c++) {

            if (first[c] == -1) {
                continue;
            }

            int left = first[c];
            int right = last[c];

            boolean valid = true;

            for (int i = left; i <= right; i++) {

                int x = s.charAt(i) - 'a';

                // This character appeared before our left boundary
                if (first[x] < left) {
                    valid = false;
                    break;
                }

                // Need to include all occurrences of this character
                right = Math.max(right, last[x]);
            }

            if (valid) {
                intervals.add(new int[]{left, right});
            }
        }

        // Sort by ending position
        // If same ending, choose the shorter one
        Collections.sort(intervals, (a, b) -> {
            if (a[1] != b[1]) {
                return Integer.compare(a[1], b[1]);
            }
            return Integer.compare(b[0], a[0]);
        });

        List<String> answer = new ArrayList<>();

        int previousEnd = -1;

        // Greedy: take the substring that ends earliest
        for (int[] interval : intervals) {

            if (interval[0] > previousEnd) {

                answer.add(
                    s.substring(interval[0], interval[1] + 1)
                );

                previousEnd = interval[1];
            }
        }

        return answer;
    }
}
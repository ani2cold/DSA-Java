import java.util.*;

class Solution {
    public int smallestUniqueSubarray(int[] nums) {
        int n = nums.length;

        int[] sa = buildSuffixArray(nums);
        int[] lcp = buildLCP(nums, sa);

int ans = n;

for (int i = 0; i < n; i++) {
    int maxLcp = 0;

    if (i > 0) {
        maxLcp = Math.max(maxLcp, lcp[i]);
    }

    if (i < n - 1) {
        maxLcp = Math.max(maxLcp, lcp[i + 1]);
    }

    int required = maxLcp + 1;
    int remaining = n - sa[i];

    if (required <= remaining) {
        ans = Math.min(ans, required);
    }
}

return ans;
    }

    private int[] buildSuffixArray(int[] nums) {
        int n = nums.length;

        int[] sa = new int[n];
        int[] rank = new int[n];
        int[] newRank = new int[n];
        int[] temp = new int[n];

        // Initial ranks
        for (int i = 0; i < n; i++) {
            sa[i] = i;
            rank[i] = nums[i];
        }

        int maxRank = 100000;

        for (int len = 1; len < n; len *= 2) {
            countingSort(sa, temp, rank, len, maxRank);
            countingSort(temp, sa, rank, 0, maxRank);

            int classes = 1;
            newRank[sa[0]] = 0;

            for (int i = 1; i < n; i++) {
                int a = sa[i - 1];
                int b = sa[i];

                int a1 = rank[a];
                int b1 = rank[b];

                int a2 = a + len < n ? rank[a + len] : -1;
                int b2 = b + len < n ? rank[b + len] : -1;

                if (a1 != b1 || a2 != b2) {
                    classes++;
                }

                newRank[b] = classes - 1;
            }

            int[] swap = rank;
            rank = newRank;
            newRank = swap;

            maxRank = classes - 1;

            if (classes == n) {
                break;
            }
        }

        return sa;
    }

    private void countingSort(int[] input, int[] output,
                              int[] rank, int offset, int maxRank) {
        int n = input.length;
        int[] count = new int[maxRank + 2];

        for (int idx : input) {
            int key = idx + offset < n
                    ? rank[idx + offset] + 1
                    : 0;
            count[key]++;
        }

        for (int i = 1; i < count.length; i++) {
            count[i] += count[i - 1];
        }

        for (int i = n - 1; i >= 0; i--) {
            int idx = input[i];
            int key = idx + offset < n
                    ? rank[idx + offset] + 1
                    : 0;

            output[--count[key]] = idx;
        }
    }

    private int[] buildLCP(int[] nums, int[] sa) {
        int n = nums.length;

        int[] rank = new int[n];
        int[] lcp = new int[n];

        for (int i = 0; i < n; i++) {
            rank[sa[i]] = i;
        }

        int h = 0;

        for (int i = 0; i < n; i++) {
            int r = rank[i];

            if (r == 0) {
                continue;
            }

            int j = sa[r - 1];

            while (i + h < n && j + h < n
                    && nums[i + h] == nums[j + h]) {
                h++;
            }

            lcp[r] = h;

            if (h > 0) {
                h--;
            }
        }

        return lcp;
    }
}
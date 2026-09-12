import java.util.*;

class Solution {

    public int[] maximumWeight(List<List<Integer>> intervals) {

        int n = intervals.size();

        // start, end, weight, original index
        int[][] a = new int[n][4];

        for (int i = 0; i < n; i++) {
            a[i][0] = intervals.get(i).get(0);
            a[i][1] = intervals.get(i).get(1);
            a[i][2] = intervals.get(i).get(2);
            a[i][3] = i;
        }

        // Sort by starting point
        Arrays.sort(a, (x, y) -> Integer.compare(x[0], y[0]));

        // Find first interval that does not overlap
        int[] next = new int[n];

        for (int i = 0; i < n; i++) {

            int left = i + 1;
            int right = n;

            while (left < right) {

                int mid = (left + right) / 2;

                if (a[mid][0] > a[i][1]) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }

            next[i] = left;
        }

        State[][] dp = new State[n + 1][5];

        // Base case: 0 intervals allowed
        for (int i = 0; i <= n; i++) {
            dp[i][0] = new State(0, new ArrayList<>());
        }

        // Base case: no intervals left
        for (int j = 0; j <= 4; j++) {
            dp[n][j] = new State(0, new ArrayList<>());
        }

        // DP
        for (int i = n - 1; i >= 0; i--) {

            for (int j = 1; j <= 4; j++) {

                // Option 1: skip
                State skip = dp[i + 1][j];

                // Option 2: take
                State after = dp[next[i]][j - 1];

                List<Integer> takeIndices = new ArrayList<>();

                takeIndices.add(a[i][3]);
                takeIndices.addAll(after.indices);

                Collections.sort(takeIndices);

                State take = new State(
                    a[i][2] + after.score,
                    takeIndices
                );

                // Choose better option
                if (take.score > skip.score) {

                    dp[i][j] = take;

                } else if (take.score < skip.score) {

                    dp[i][j] = skip;

                } else {

                    // Same score -> lexicographically smaller
                    if (isSmaller(take.indices, skip.indices)) {
                        dp[i][j] = take;
                    } else {
                        dp[i][j] = skip;
                    }
                }
            }
        }

        List<Integer> answer = dp[0][4].indices;

        int[] result = new int[answer.size()];

        for (int i = 0; i < answer.size(); i++) {
            result[i] = answer.get(i);
        }

        return result;
    }

    class State {

        long score;
        List<Integer> indices;

        State(long score, List<Integer> indices) {
            this.score = score;
            this.indices = indices;
        }
    }

    boolean isSmaller(List<Integer> a, List<Integer> b) {

        for (int i = 0; i < Math.min(a.size(), b.size()); i++) {

            if (!a.get(i).equals(b.get(i))) {
                return a.get(i) < b.get(i);
            }
        }

        return a.size() < b.size();
    }
}
import java.util.*;

class Solution {
    public int largestOverlap(int[][] img1, int[][] img2) {

        int n = img1.length;

        List<int[]> a = new ArrayList<>();
        List<int[]> b = new ArrayList<>();

        // Store positions of 1s
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {

                if (img1[i][j] == 1) {
                    a.add(new int[]{i, j});
                }

                if (img2[i][j] == 1) {
                    b.add(new int[]{i, j});
                }
            }
        }

        HashMap<String, Integer> map = new HashMap<>();

        int answer = 0;

        // Compare every 1 in img1 with every 1 in img2
        for (int[] x : a) {
            for (int[] y : b) {

                int dx = x[0] - y[0];
                int dy = x[1] - y[1];

                String key = dx + "," + dy;

                int count = map.getOrDefault(key, 0) + 1;

                map.put(key, count);

                answer = Math.max(answer, count);
            }
        }

        return answer;
    }
}
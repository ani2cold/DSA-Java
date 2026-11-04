class Solution {
    public long minOperations(int[] nums, int x, int k) {
        int n = nums.length;
        long[] cost = new long[n - x + 1];

        MedianWindow window = new MedianWindow();

        for (int i = 0; i < x; i++) {
            window.add(nums[i]);
        }

        cost[0] = window.getCost();

        for (int i = x; i < n; i++) {
            window.remove(nums[i - x]);
            window.add(nums[i]);
            cost[i - x + 1] = window.getCost();
        }

        long INF = Long.MAX_VALUE / 4;

        // Zero selected windows always have zero cost.
        long[] prev = new long[n + 1];

        for (int j = 1; j <= k; j++) {
            long[] curr = new long[n + 1];
            Arrays.fill(curr, INF);

            for (int i = 1; i <= n; i++) {
                curr[i] = curr[i - 1];

                if (i >= x && prev[i - x] != INF) {
                    curr[i] = Math.min(
                        curr[i],
                        prev[i - x] + cost[i - x]
                    );
                }
            }

            prev = curr;
        }

        return prev[n];
    }

    class MedianWindow {
        TreeMap<Integer, Integer> low = new TreeMap<>();
        TreeMap<Integer, Integer> high = new TreeMap<>();

        int lowSize = 0, highSize = 0;
        long lowSum = 0, highSum = 0;

        void add(int num) {
            if (lowSize == 0 || num <= low.lastKey()) {
                insert(low, num);
                lowSize++;
                lowSum += num;
            } else {
                insert(high, num);
                highSize++;
                highSum += num;
            }
            balance();
        }

        void remove(int num) {
            if (low.containsKey(num)) {
                erase(low, num);
                lowSize--;
                lowSum -= num;
            } else {
                erase(high, num);
                highSize--;
                highSum -= num;
            }
            balance();
        }

        void balance() {
            int target = (lowSize + highSize + 1) / 2;

            while (lowSize > target) {
                int v = low.lastKey();
                erase(low, v);
                lowSize--;
                lowSum -= v;

                insert(high, v);
                highSize++;
                highSum += v;
            }

            while (lowSize < target) {
                int v = high.firstKey();
                erase(high, v);
                highSize--;
                highSum -= v;

                insert(low, v);
                lowSize++;
                lowSum += v;
            }
        }

        long getCost() {
            long median = low.lastKey();

            return median * lowSize - lowSum
                 + highSum - median * highSize;
        }

        void insert(TreeMap<Integer, Integer> map, int v) {
            map.put(v, map.getOrDefault(v, 0) + 1);
        }

        void erase(TreeMap<Integer, Integer> map, int v) {
            int c = map.get(v);

            if (c == 1) {
                map.remove(v);
            } else {
                map.put(v, c - 1);
            }
        }
    }
}
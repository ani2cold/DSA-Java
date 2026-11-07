class Solution {
    public int[] assignTasks(int[] servers, int[] tasks) {
        int n = servers.length;
        int m = tasks.length;

        PriorityQueue<int[]> available = new PriorityQueue<>(
            (a, b) -> {
                if (a[0] != b[0]) {
                    return Integer.compare(a[0], b[0]);
                }
                return Integer.compare(a[1], b[1]);
            }
        );

        PriorityQueue<long[]> busy = new PriorityQueue<>(
            (a, b) -> {
                if (a[0] != b[0]) {
                    return Long.compare(a[0], b[0]);
                }
                return Long.compare(a[1], b[1]);
            }
        );

        for (int i = 0; i < n; i++) {
            available.offer(new int[]{servers[i], i});
        }

        int[] ans = new int[m];
        long currentTime = 0;

        for (int i = 0; i < m; i++) {
            currentTime = Math.max(currentTime, i);

            // Release all servers that have finished.
            while (!busy.isEmpty() &&
                   busy.peek()[0] <= currentTime) {
                long[] server = busy.poll();

                int index = (int) server[1];
                available.offer(new int[]{servers[index], index});
            }

            // Wait until the earliest server becomes free.
            if (available.isEmpty()) {
                currentTime = busy.peek()[0];

                while (!busy.isEmpty() &&
                       busy.peek()[0] <= currentTime) {
                    long[] server = busy.poll();

                    int index = (int) server[1];
                    available.offer(new int[]{servers[index], index});
                }
            }

            int[] server = available.poll();
            int index = server[1];

            ans[i] = index;

            busy.offer(new long[]{
                currentTime + tasks[i],
                index
            });
        }

        return ans;
    }
}
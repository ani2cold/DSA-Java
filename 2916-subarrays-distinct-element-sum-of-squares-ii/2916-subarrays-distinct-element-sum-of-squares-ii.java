class Solution {
    static final int MOD = 1_000_000_007;

    long[] sum, squares, lazy;

    public int sumCounts(int[] nums) {
        int n = nums.length;
        sum = new long[4 * n];
        squares = new long[4 * n];
        lazy = new long[4 * n];

        int[] last = new int[100001];
        long ans = 0;

        for (int i = 0; i < n; i++) {
            int value = nums[i];
            int left = last[value];

            update(1, 0, n - 1, left, i);
            ans = (ans + squares[1]) % MOD;

            last[value] = i + 1;
        }

        return (int) ans;
    }

    private void update(int node, int l, int r, int ql, int qr) {
        if (ql > r || qr < l) return;

        if (ql <= l && r <= qr) {
            long oldSum = sum[node];
            int len = r - l + 1;

            squares[node] = (squares[node] + 2 * oldSum + len) % MOD;
            sum[node] = (oldSum + len) % MOD;
            lazy[node]++;
            return;
        }

        push(node, l, r);

        int mid = (l + r) / 2;
        update(node * 2, l, mid, ql, qr);
        update(node * 2 + 1, mid + 1, r, ql, qr);

        sum[node] = (sum[node * 2] + sum[node * 2 + 1]) % MOD;
        squares[node] = (squares[node * 2] + squares[node * 2 + 1]) % MOD;
    }

    private void push(int node, int l, int r) {
        if (lazy[node] == 0 || l == r) return;

        int mid = (l + r) / 2;
        apply(node * 2, l, mid, lazy[node]);
        apply(node * 2 + 1, mid + 1, r, lazy[node]);
        lazy[node] = 0;
    }

    private void apply(int node, int l, int r, long value) {
        long oldSum = sum[node];
        long len = r - l + 1L;

        squares[node] = (squares[node] + 2 * value % MOD * oldSum
                         + value * value % MOD * len) % MOD;
        sum[node] = (oldSum + value * len) % MOD;
        lazy[node] += value;
    }
}
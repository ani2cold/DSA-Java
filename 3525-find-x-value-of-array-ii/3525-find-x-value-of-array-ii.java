class Solution {

    int k;
    Node[] tree;

    class Node {
        long[] pre;
        long[] suf;
        long[] sub;
        int prod;

        Node() {
            pre = new long[k];
            suf = new long[k];
            sub = new long[k];
        }
    }

    public int[] resultArray(int[] nums, int k, int[][] queries) {

        this.k = k;

        int n = nums.length;
        tree = new Node[4 * n];

        build(1, 0, n - 1, nums);

        int[] answer = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {

            int index = queries[i][0];
            int value = queries[i][1];
            int start = queries[i][2];
            int x = queries[i][3];

            update(1, 0, n - 1, index, value);

            Node res = query(1, 0, n - 1, start, n - 1);

            answer[i] = (int) res.pre[x];
        }

        return answer;
    }

    void build(int node, int l, int r, int[] nums) {

        if (l == r) {

            tree[node] = new Node();

            int v = nums[l] % k;

            tree[node].prod = v;
            tree[node].pre[v] = 1;
            tree[node].suf[v] = 1;
            tree[node].sub[v] = 1;

            return;
        }

        int mid = (l + r) / 2;

        build(node * 2, l, mid, nums);
        build(node * 2 + 1, mid + 1, r, nums);

        tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
    }

    void update(int node, int l, int r, int index, int value) {

        if (l == r) {

            tree[node] = new Node();

            int v = value % k;

            tree[node].prod = v;
            tree[node].pre[v] = 1;
            tree[node].suf[v] = 1;
            tree[node].sub[v] = 1;

            return;
        }

        int mid = (l + r) / 2;

        if (index <= mid) {
            update(node * 2, l, mid, index, value);
        } else {
            update(node * 2 + 1, mid + 1, r, index, value);
        }

        tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
    }

    Node query(int node, int l, int r, int ql, int qr) {

        if (ql <= l && r <= qr) {
            return tree[node];
        }

        int mid = (l + r) / 2;

        if (qr <= mid) {
            return query(node * 2, l, mid, ql, qr);
        }

        if (ql > mid) {
            return query(node * 2 + 1, mid + 1, r, ql, qr);
        }

        Node left = query(node * 2, l, mid, ql, qr);
        Node right = query(node * 2 + 1, mid + 1, r, ql, qr);

        return merge(left, right);
    }

    Node merge(Node a, Node b) {

        Node c = new Node();

        c.prod = (a.prod * b.prod) % k;

        // Prefixes from left
        for (int r = 0; r < k; r++) {
            c.pre[r] += a.pre[r];
        }

        // Prefixes using all of left + prefix of right
        for (int r = 0; r < k; r++) {
            if (a.pre[r] > 0 && r == a.prod) {
                for (int x = 0; x < k; x++) {
                    int nr = (r * x) % k;
                    c.pre[nr] += b.pre[x];
                }
            }
        }

        // Suffixes from right
        for (int r = 0; r < k; r++) {
            c.suf[r] += b.suf[r];
        }

        // Suffixes from left + all of right
        for (int r = 0; r < k; r++) {
            if (a.suf[r] > 0) {
                int nr = (r * b.prod) % k;
                c.suf[nr] += a.suf[r];
            }
        }

        // Subarrays inside left/right
        for (int r = 0; r < k; r++) {
            c.sub[r] += a.sub[r];
            c.sub[r] += b.sub[r];
        }

        // Subarrays crossing the middle
        for (int x = 0; x < k; x++) {
            for (int y = 0; y < k; y++) {

                if (a.suf[x] == 0 || b.pre[y] == 0) {
                    continue;
                }

                int nr = (x * y) % k;

                c.sub[nr] += a.suf[x] * b.pre[y];
            }
        }

        return c;
    }
}
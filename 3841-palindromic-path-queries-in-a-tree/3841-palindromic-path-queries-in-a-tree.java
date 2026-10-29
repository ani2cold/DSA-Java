class Solution {
    List<Integer>[] tree;
    int[] tin, tout, depth, parent;
    int[][] up;
    int[] bit;
    int timer;

    public List<Boolean> palindromePath(int n, int[][] edges, String s, String[] queries) {
        tree = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int[] e : edges) {
            tree[e[0]].add(e[1]);
            tree[e[1]].add(e[0]);
        }

        tin = new int[n];
        tout = new int[n];
        depth = new int[n];
        parent = new int[n];
        timer = 0;

        int LOG = 17;
        up = new int[LOG][n];

        dfs(0, -1);

        for (int j = 1; j < LOG; j++) {
            for (int i = 0; i < n; i++) {
                up[j][i] = up[j - 1][up[j - 1][i]];
            }
        }

        bit = new int[n + 2];
        char[] chars = s.toCharArray();

        for (int i = 0; i < n; i++) {
            add(tin[i], 1 << (chars[i] - 'a'));
            add(tout[i] + 1, 1 << (chars[i] - 'a'));
        }

        List<Boolean> ans = new ArrayList<>();

        for (String q : queries) {
            String[] p = q.split(" ");
            int u = Integer.parseInt(p[1]);

            if (p[0].equals("update")) {
                char c = p[2].charAt(0);

                if (chars[u] != c) {
                    int delta = (1 << (chars[u] - 'a')) ^ (1 << (c - 'a'));
                    add(tin[u], delta);
                    add(tout[u] + 1, delta);
                    chars[u] = c;
                }
            } else {
                int v = Integer.parseInt(p[2]);
                int l = lca(u, v);

                int mask = prefix(tin[u]) ^ prefix(tin[v])
                         ^ (1 << (chars[l] - 'a'));

                ans.add((mask & (mask - 1)) == 0);
            }
        }

        return ans;
    }

    private void dfs(int u, int p) {
        parent[u] = (p == -1) ? u : p;
        up[0][u] = parent[u];
        tin[u] = ++timer;

        for (int v : tree[u]) {
            if (v == p) continue;
            depth[v] = depth[u] + 1;
            dfs(v, u);
        }

        tout[u] = timer;
    }

    private int lca(int u, int v) {
        if (depth[u] < depth[v]) {
            int t = u;
            u = v;
            v = t;
        }

        int diff = depth[u] - depth[v];

        for (int j = 0; j < up.length; j++) {
            if ((diff & (1 << j)) != 0) {
                u = up[j][u];
            }
        }

        if (u == v) return u;

        for (int j = up.length - 1; j >= 0; j--) {
            if (up[j][u] != up[j][v]) {
                u = up[j][u];
                v = up[j][v];
            }
        }

        return parent[u];
    }

    private void add(int i, int val) {
        while (i < bit.length) {
            bit[i] ^= val;
            i += i & -i;
        }
    }

    private int prefix(int i) {
        int res = 0;
        while (i > 0) {
            res ^= bit[i];
            i -= i & -i;
        }
        return res;
    }
}
import java.util.*;

class Solution {
    public int[] findSubtreeSizes(int[] parent, String s) {
        int n = parent.length;

        // Build original tree
        List<Integer>[] children = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            children[i] = new ArrayList<>();
        }

        for (int i = 1; i < n; i++) {
            children[parent[i]].add(i);
        }

        int[] newParent = parent.clone();
        int[] last = new int[26];
        Arrays.fill(last, -1);

        int[] previous = new int[n];
        int[] stack = new int[2 * n];
        int top = 0;

        stack[top++] = 0;

        // Find new parents using the original tree.
        while (top > 0) {
            int node = stack[--top];

            if (node < 0) {
                node = ~node;
                last[s.charAt(node) - 'a'] = previous[node];
                continue;
            }

            int c = s.charAt(node) - 'a';

            previous[node] = last[c];

            if (last[c] != -1) {
                newParent[node] = last[c];
            }

            last[c] = node;

            // Exit event
            stack[top++] = ~node;

            // Visit children
            for (int child : children[node]) {
                stack[top++] = child;
            }
        }

        // Build modified tree
        List<Integer>[] newChildren = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            newChildren[i] = new ArrayList<>();
        }

        for (int i = 1; i < n; i++) {
            newChildren[newParent[i]].add(i);
        }

        // Get traversal order of modified tree
        int[] order = new int[n];
        top = 0;
        int count = 0;
        stack[top++] = 0;

        while (top > 0) {
            int node = stack[--top];
            order[count++] = node;

            for (int child : newChildren[node]) {
                stack[top++] = child;
            }
        }

        // Calculate subtree sizes in reverse order
        int[] ans = new int[n];
        Arrays.fill(ans, 1);

        for (int i = n - 1; i > 0; i--) {
            int node = order[i];
            ans[newParent[node]] += ans[node];
        }

        return ans;
    }
}
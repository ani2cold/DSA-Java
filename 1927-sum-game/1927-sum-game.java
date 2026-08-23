class Solution {
    public boolean sumGame(String num) {

        int n = num.length();
        int mid = n / 2;

        int diff = 0;
        int qDiff = 0;

        for (int i = 0; i < n; i++) {

            char ch = num.charAt(i);

            if (ch == '?') {
                if (i < mid)
                    qDiff++;
                else
                    qDiff--;
            } else {
                if (i < mid)
                    diff += ch - '0';
                else
                    diff -= ch - '0';
            }
        }

        // Odd difference in number of '?' means Alice wins.
        if (qDiff % 2 != 0) {
            return true;
        }

        // Bob can win only if the fixed-digit difference
        // is exactly compensated by the '?'.
        return diff != -9 * qDiff / 2;
    }
}
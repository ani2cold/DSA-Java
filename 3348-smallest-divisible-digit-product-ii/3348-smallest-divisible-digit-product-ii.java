import java.util.*;

class Solution {
    public String smallestNumber(String num, long t) {
        // Step 1: Count prime factors of t (2, 3, 5, 7)
        long tempT = t;
        int[] targetFactors = new int[10]; // index 2, 3, 5, 7
        int[] primes = {2, 3, 5, 7};
        for (int p : primes) {
            while (tempT % p == 0) {
                targetFactors[p]++;
                tempT /= p;
            }
        }
        // If t has prime factors other than 2, 3, 5, 7, it's impossible
        if (tempT > 1) {
            return "-1";
        }

        int n = num.length();
        int[][] prefixFactors = new int[n + 1][10];
        int firstZero = -1;

        // Step 2: Compute prefix factor counts for the original 'num'
        for (int i = 0; i < n; i++) {
            int digit = num.charAt(i) - '0';
            System.arraycopy(prefixFactors[i], 0, prefixFactors[i + 1], 0, 10);
            if (digit == 0) {
                if (firstZero == -1) {
                    firstZero = i;
                }
            } else {
                addFactors(prefixFactors[i + 1], digit);
            }
        }

        // If 'num' itself is zero-free and satisfies the divisibility condition
        if (firstZero == -1 && satisfies(prefixFactors[n], targetFactors)) {
            return num;
        }

        // Step 3: Backtrack from right to left to find the first position we can increment
        int limit = (firstZero == -1) ? n - 1 : firstZero;
        for (int i = limit; i >= 0; i--) {
            int currentDigit = num.charAt(i) - '0';
            for (int nextDigit = currentDigit + 1; nextDigit <= 9; nextDigit++) {
                int[] currentFactors = new int[10];
                System.arraycopy(prefixFactors[i], 0, currentFactors, 0, 10);
                addFactors(currentFactors, nextDigit);

                int remainingLength = n - 1 - i;
                String suffix = getOptimalSuffix(currentFactors, targetFactors, remainingLength, false);
                if (suffix != null) {
                    return num.substring(0, i) + nextDigit + suffix;
                }
            }
        }

        // Step 4: If no solution exists with the same length, expand the length
        int[] emptyFactors = new int[10];
        // Find the absolute minimum length needed to satisfy all target factors (allow growth)
        String baseSuffix = getOptimalSuffix(emptyFactors, targetFactors, 0, true);
        int newLength = Math.max(n + 1, baseSuffix.length());
        
        return getOptimalSuffix(emptyFactors, targetFactors, newLength, false);
    }

    private void addFactors(int[] factors, int digit) {
        int d = digit;
        int[] primes = {2, 3, 5, 7};
        for (int p : primes) {
            while (d % p == 0) {
                factors[p]++;
                d /= p;
            }
        }
    }

    private boolean satisfies(int[] current, int[] target) {
        return current[2] >= target[2] && current[3] >= target[3] && 
               current[5] >= target[5] && current[7] >= target[7];
    }

    // Resolves the absolute optimal sequence of characters needed
    private String getOptimalSuffix(int[] current, int[] target, int targetLength, boolean allowGrowth) {
        int need2 = Math.max(0, target[2] - current[2]);
        int need3 = Math.max(0, target[3] - current[3]);
        int need5 = Math.max(0, target[5] - current[5]);
        int need7 = Math.max(0, target[7] - current[7]);

        StringBuilder sb = new StringBuilder();
        
        // 5 and 7 can only be satisfied by themselves
        while (need5 > 0) { sb.append('5'); need5--; }
        while (need7 > 0) { sb.append('7'); need7--; }

        // Bulk process 8s and 9s
        int count8 = need2 / 3;
        int rem2 = need2 % 3;
        
        int count9 = need3 / 2;
        int rem3 = need3 % 2;

        // Form optimally paired strings for the remaining small counts of 2 and 3
        String bestRemStr = "";
        if (rem2 == 0 && rem3 == 0) bestRemStr = "";
        else if (rem2 == 0 && rem3 == 1) bestRemStr = "3";
        else if (rem2 == 1 && rem3 == 0) bestRemStr = "2";
        else if (rem2 == 1 && rem3 == 1) bestRemStr = "6";
        else if (rem2 == 2 && rem3 == 0) bestRemStr = "4";
        else if (rem2 == 2 && rem3 == 1) bestRemStr = "26";

        for (int i = 0; i < count8; i++) sb.append('8');
        for (int i = 0; i < count9; i++) sb.append('9');
        sb.append(bestRemStr);

        // Convert to array and sort to keep lexicographically smallest
        char[] chars = sb.toString().toCharArray();
        Arrays.sort(chars);

        // Handle length limitations explicitly
        if (chars.length > targetLength) {
            if (allowGrowth) {
                return new String(chars);
            }
            return null;
        }

        // Pad with '1's from the left up to targetLength
        StringBuilder finalSuffix = new StringBuilder();
        int paddingAmount = targetLength - chars.length;
        for (int i = 0; i < paddingAmount; i++) {
            finalSuffix.append('1');
        }
        finalSuffix.append(new String(chars));

        return finalSuffix.toString();
    }
}


// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna
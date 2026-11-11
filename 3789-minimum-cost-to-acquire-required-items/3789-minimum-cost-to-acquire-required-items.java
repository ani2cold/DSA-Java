class Solution {
    public long minimumCost(int cost1, int cost2, int costBoth, int need1, int need2) {
        long common = Math.min(need1, need2);
        long extra = Math.max(need1, need2) - common;

        long commonCost = Math.min((long) cost1 + cost2, costBoth);

        long extraCost;

        if (need1 > need2) {
            extraCost = Math.min(cost1, costBoth);
        } else {
            extraCost = Math.min(cost2, costBoth);
        }

        return common * commonCost + extra * extraCost;
    }
}
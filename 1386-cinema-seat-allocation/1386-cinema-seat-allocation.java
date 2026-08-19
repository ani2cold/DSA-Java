class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {

        Map<Integer, Set<Integer>> map = new HashMap<>();

        // Store reserved seats for each row
        for (int[] seat : reservedSeats) {
            int row = seat[0];
            int col = seat[1];

            // Seats 1 and 10 don't affect any group
            if (col >= 2 && col <= 9) {
                map.computeIfAbsent(row, x -> new HashSet<>()).add(col);
            }
        }

        // Assume every row is completely empty
        int answer = 2 * n;

        // Check only rows having relevant reserved seats
        for (Set<Integer> reserved : map.values()) {

            boolean left = true;
            boolean middle = true;
            boolean right = true;

            // Check seats 2,3,4,5
            for (int seat = 2; seat <= 5; seat++) {
                if (reserved.contains(seat)) {
                    left = false;
                }
            }

            // Check seats 4,5,6,7
            for (int seat = 4; seat <= 7; seat++) {
                if (reserved.contains(seat)) {
                    middle = false;
                }
            }

            // Check seats 6,7,8,9
            for (int seat = 6; seat <= 9; seat++) {
                if (reserved.contains(seat)) {
                    right = false;
                }
            }

            if (left && right) {
                // Can fit two groups
                // Already counted 2, so no change
            }
            else if (left || middle || right) {
                // Can fit only one group
                answer--;
            }
            else {
                // Cannot fit any group
                answer -= 2;
            }
        }

        return answer;
    }
}
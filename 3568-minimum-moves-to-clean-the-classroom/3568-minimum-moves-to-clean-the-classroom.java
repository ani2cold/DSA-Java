class Solution {
    public int minMoves(String[] classroom, int energy) {

        int m = classroom.length;
        int n = classroom[0].length();

        int sr = 0;
        int sc = 0;
        int litter = 0;

        int[][] id = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                id[i][j] = -1;

                if (classroom[i].charAt(j) == 'S') {
                    sr = i;
                    sc = j;
                }

                if (classroom[i].charAt(j) == 'L') {
                    id[i][j] = litter;
                    litter++;
                }
            }
        }

        if (litter == 0) {
            return 0;
        }

        int total = 1 << litter;

        boolean[][][][] visited =
            new boolean[m][n][total][energy + 1];

        Queue<int[]> q = new LinkedList<>();

        // row, column, energy, mask, moves
        q.offer(new int[]{sr, sc, energy, 0, 0});

        visited[sr][sc][0][energy] = true;

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        int fullMask = (1 << litter) - 1;

        while (!q.isEmpty()) {

            int[] cur = q.poll();

            int r = cur[0];
            int c = cur[1];
            int e = cur[2];
            int mask = cur[3];
            int moves = cur[4];

            if (mask == fullMask) {
                return moves;
            }

            for (int d = 0; d < 4; d++) {

                int nr = r + dr[d];
                int nc = c + dc[d];

                if (nr < 0 || nr >= m || nc < 0 || nc >= n) {
                    continue;
                }

                if (classroom[nr].charAt(nc) == 'X') {
                    continue;
                }

                int ne = e - 1;

                if (ne < 0) {
                    continue;
                }

                int nmask = mask;

                if (classroom[nr].charAt(nc) == 'L') {
                    nmask |= (1 << id[nr][nc]);
                }

                if (classroom[nr].charAt(nc) == 'R') {
                    ne = energy;
                }

                if (ne == 0 &&
                    classroom[nr].charAt(nc) != 'R' &&
                    nmask != fullMask) {
                    continue;
                }

                if (!visited[nr][nc][nmask][ne]) {

                    visited[nr][nc][nmask][ne] = true;

                    q.offer(new int[]{
                        nr, nc, ne, nmask, moves + 1
                    });
                }
            }
        }

        return -1;
    }
}
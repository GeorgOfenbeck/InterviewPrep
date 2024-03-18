package leetcode.p1197;

class Solution {
    private static int[][] localRegion = {
            {0, 3, 2},
            {3, 2, 1},
            {2, 1, 4}
    };

    public int minKnightMoves(int x, int y) {
        x = Math.abs(x);
        y = Math.abs(y);
        if (x < y) {
            int tmp = x;
            x = y;
            y = tmp;
        }
        if (x <= 2 && y <= 2)
            return localRegion[x][y];

        int groupId;
        if ((x - 3) >= (y - 3) * 2)
            groupId = (x - 1) / 2 + 1;
        else
            groupId = (x + y - 2) / 3 + 1;

        return groupId + ((x + y + groupId) % 2);
    }
}
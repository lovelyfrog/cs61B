public class BubbleGrid {
    private int[][] grid;

    /* Create new BubbleGrid with bubble/space locations specified by grid.
     * Grid is composed of only 1's and 0's, where 1's denote a bubble, and
     * 0's denote a space. */
    public BubbleGrid(int[][] grid) {
        this.grid = grid;
    }

    /* Returns an array whose i-th element is the number of bubbles that
     * fall after the i-th dart is thrown. Assume all elements of darts
     * are unique, valid locations in the grid. Must be non-destructive
     * and have no side-effects to grid. */
    public int[] popBubbles(int[][] darts) {
        // TODO
        int[] res = new int[darts.length];
        int rowNum = grid.length;
        int colNum = grid[0].length;
        for (int i=0; i<res.length; i++) {
            res[i] = solve(grid, darts[i], rowNum, colNum);
        }
        return res;
    }

    public int solve(int[][] grid, int[] pop, int rowNum, int colNum) {
        int fallNumber = 0;
        grid[pop[0]][pop[1]] = 0;
        UnionFind tmp = new UnionFind(rowNum * colNum);
        for (int i=1; i<rowNum; i++) {
            for (int j=0; j<colNum; j++) {
                if (grid[i][j] == 1) {
                    if (j > 0 && grid[i][j-1] == 1)
                        tmp.union(i*colNum+j, i*colNum+(j-1), colNum);
                    if (grid[i-1][j] == 1)
                        tmp.union((i-1)*colNum+j, i*colNum+j, colNum);
                }
            }
        }

        for (int i=0; i<rowNum; i++) {
            for (int j=0; j<colNum; j++) {
                if (grid[i][j] == 1 && tmp.find(i*colNum+j) >= colNum) {
                    fallNumber += 1;
                    grid[i][j] = 0;
                }
            }
        }
        return fallNumber;
    }

}

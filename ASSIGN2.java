import java.util.*;

class WarehouseSummaryResult {
    private long totalItems;
    private int maxRow;
    private int maxCol;

    public WarehouseSummaryResult(long totalItems, int maxRow, int maxCol) {
        this.totalItems = totalItems;
        this.maxRow = maxRow;
        this.maxCol = maxCol;
    }

    public long getTotalItems() {
        return totalItems;
    }

    public int getMaxRow() {
        return maxRow;
    }

    public int getMaxCol() {
        return maxCol;
    }

    @Override
    public String toString() {
        return "(" + totalItems + ", (" + maxRow + ", " + maxCol + "))";
    }
}

public class M2 {

    /**
     * Processes a 2D warehouse layout grid to calculate total items and locate
     * the coordinate of the bin holding the maximum items.
     * 
     * Time Complexity: O(m * n) where m is rows and n is columns.
     * Space Complexity: O(1) auxiliary space beyond the output data structure.
     * 
     * @param grid 2D array of integers representing item counts in bins.
     * @return WarehouseSummaryResult containing total_items and max_coordinate tuple.
     */
    public static WarehouseSummaryResult warehouseSummary(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return new WarehouseSummaryResult(0, 0, 0);
        }

        long totalItems = 0;
        int maxItems = -1;
        int maxRow = 0;
        int maxCol = 0;

        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[r].length; c++) {
                int count = grid[r][c];
                totalItems += count;

                // Strictly greater than to preserve the FIRST encountered max bin scanning row by row
                if (count > maxItems) {
                    maxItems = count;
                    maxRow = r;
                    maxCol = c;
                }
            }
        }

        return new WarehouseSummaryResult(totalItems, maxRow, maxCol);
    }

    public static void main(String[] args) {
        System.out.println("=== Problem 2: Warehouse Grid Summary ===");

        int[][] grid = {
            {4, 9, 2},
            {7, 1, 6},
            {3, 12, 5}
        };

        WarehouseSummaryResult result = warehouseSummary(grid);
        System.out.println("Input Grid:");
        for (int[] row : grid) {
            System.out.println(Arrays.toString(row));
        }

        System.out.println("\nExpected Output: (49, (2, 1))");
        System.out.println("Actual Output:   " + result);
    }
}

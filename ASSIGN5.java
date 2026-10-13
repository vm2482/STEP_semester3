import java.util.*;

public class M5 {

    /**
     * Optimal Solution: Two-Pointer Approach
     * 
     * Time Complexity: O(n) - single pass with two pointers moving inward.
     * Auxiliary Space Complexity: O(1) - constant extra memory.
     * 
     * Rationale: Area is constrained by min(heights[left], heights[right]). 
     * Moving the pointer of the taller wall can never increase the area because width 
     * decreases while height remains bounded by the shorter wall. Moving the shorter wall 
     * gives the potential to find a taller boundary.
     * 
     * @param heights Array of non-negative integers representing line heights.
     * @return Maximum water container area.
     */
    public static int maxContainerArea(int[] heights) {
        if (heights == null || heights.length < 2) {
            return 0;
        }

        int maxArea = 0;
        int left = 0;
        int right = heights.length - 1;

        while (left < right) {
            int width = right - left;
            int minHeight = Math.min(heights[left], heights[right]);
            int currentArea = minHeight * width;

            maxArea = Math.max(maxArea, currentArea);

            // Move the pointer at the shorter wall inward
            if (heights[left] < heights[right]) {
                left++;
            } else {
                right--;
            }
        }

        return maxArea;
    }

    /**
     * Brute Force Solution: Check every pair of walls (i, j).
     * 
     * Time Complexity: O(n^2)
     * Auxiliary Space Complexity: O(1)
     */
    public static int maxContainerAreaBruteForce(int[] heights) {
        if (heights == null || heights.length < 2) {
            return 0;
        }

        int maxArea = 0;
        for (int i = 0; i < heights.length; i++) {
            for (int j = i + 1; j < heights.length; j++) {
                int area = Math.min(heights[i], heights[j]) * (j - i);
                maxArea = Math.max(maxArea, area);
            }
        }
        return maxArea;
    }

    public static void main(String[] args) {
        System.out.println("=== Problem 5: Maximize Area Between Two Boundaries ===");

        int[] heights = {1, 8, 6, 2, 5, 4, 8, 3, 7};

        int optimalResult = maxContainerArea(heights);
        int bruteForceResult = maxContainerAreaBruteForce(heights);

        System.out.println("Heights Array: " + Arrays.toString(heights));
        System.out.println("Expected Output: 49");
        System.out.println("Optimal O(n) Output:      " + optimalResult);
        System.out.println("Brute-Force O(n^2) Output: " + bruteForceResult);
    }
}

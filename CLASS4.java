import java.util.*;

public class M4 {

    /**
     * Primary Solution: Hash Set approach for single pass O(n) runtime.
     * 
     * Time Complexity: O(n) - visits each array element once.
     * Space Complexity: O(n) - stores up to n elements in the Hash Set.
     */
    public static boolean hasPairWithSum(int[] nums, int target) {
        if (nums == null || nums.length < 2) {
            return false;
        }

        Set<Integer> seen = new HashSet<>();
        for (int num : nums) {
            int complement = target - num;
            if (seen.contains(complement)) {
                return true;
            }
            seen.add(num);
        }
        return false;
    }

    /**
     * Alternative Solution: Return the actual pair values as an array [a, b], or null if none exists.
     */
    public static int[] findPairWithSumValues(int[] nums, int target) {
        if (nums == null || nums.length < 2) {
            return null;
        }

        Set<Integer> seen = new HashSet<>();
        for (int num : nums) {
            int complement = target - num;
            if (seen.contains(complement)) {
                return new int[]{complement, num};
            }
            seen.add(num);
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println("=== Problem 4: Pair With Target Sum (Unsorted Array) ===");

        // Sample 1
        int[] nums1 = {2, 7, 11, 15};
        int target1 = 9;
        boolean result1 = hasPairWithSum(nums1, target1);
        int[] pair1 = findPairWithSumValues(nums1, target1);
        System.out.println("Sample 1: nums = [2, 7, 11, 15], target = 9");
        System.out.println("Expected Output: true (because 2 + 7 = 9)");
        System.out.println("Actual Output:   " + result1);
        if (pair1 != null) {
            System.out.println("Found Pair:      (" + pair1[0] + ", " + pair1[1] + ")");
        }

        // Sample 2
        int[] nums2 = {3, 4, 6};
        int target2 = 20;
        boolean result2 = hasPairWithSum(nums2, target2);
        int[] pair2 = findPairWithSumValues(nums2, target2);
        System.out.println("\nSample 2: nums = [3, 4, 6], target = 20");
        System.out.println("Expected Output: false");
        System.out.println("Actual Output:   " + result2);
        if (pair2 == null) {
            System.out.println("Found Pair:      None");
        }
    }
}

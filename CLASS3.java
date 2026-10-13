import java.util.*;

public class M3 {

    /**
     * Optimal Approach 1: HashSet Lookup
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     * 
     * Iterates through the array, storing visited elements in a HashSet.
     * Checks if target - currentElement exists in the HashSet.
     */
    public static boolean hasPairWithSumHashSet(int[] nums, int target) {
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
     * Approach 2: Brute Force
     * Time Complexity: O(n^2)
     * Space Complexity: O(1)
     * 
     * Checks all possible distinct pairs (i, j) where i != j.
     */
    public static boolean hasPairWithSumBruteForce(int[] nums, int target) {
        if (nums == null || nums.length < 2) {
            return false;
        }

        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Approach 3: Sorting + Two Pointers
     * Time Complexity: O(n log n)
     * Space Complexity: O(n) to create a sorted copy (or O(1) if in-place sort allowed).
     */
    public static boolean hasPairWithSumTwoPointers(int[] nums, int target) {
        if (nums == null || nums.length < 2) {
            return false;
        }

        int[] sorted = nums.clone();
        Arrays.sort(sorted);

        int left = 0;
        int right = sorted.length - 1;

        while (left < right) {
            int sum = sorted[left] + sorted[right];
            if (sum == target) {
                return true;
            } else if (sum < target) {
                left++;
            } else {
                right--;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println("=== Problem 3: Pair With Target Sum (Unsorted) ===");

        // Example 1
        int[] nums1 = {2, 7, 11, 15};
        int target1 = 9;
        System.out.println("Test Case 1: nums = [2, 7, 11, 15], target = 9");
        System.out.println("HashSet Approach Result:      " + hasPairWithSumHashSet(nums1, target1));
        System.out.println("Brute Force Approach Result:  " + hasPairWithSumBruteForce(nums1, target1));
        System.out.println("Two Pointers Approach Result: " + hasPairWithSumTwoPointers(nums1, target1));
        System.out.println("Expected: true (because 2 + 7 = 9)\n");

        // Example 2
        int[] nums2 = {3, 4, 6};
        int target2 = 20;
        System.out.println("Test Case 2: nums = [3, 4, 6], target = 20");
        System.out.println("HashSet Approach Result:      " + hasPairWithSumHashSet(nums2, target2));
        System.out.println("Brute Force Approach Result:  " + hasPairWithSumBruteForce(nums2, target2));
        System.out.println("Two Pointers Approach Result: " + hasPairWithSumTwoPointers(nums2, target2));
        System.out.println("Expected: false");
    }
}

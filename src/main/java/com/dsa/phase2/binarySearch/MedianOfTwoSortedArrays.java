package com.dsa.phase2.binarySearch;

/**
 * ==========================================================
 * Problem    : LeetCode 4 - Median of Two Sorted Arrays
 * Difficulty : Hard
 * Pattern    : Binary Search on Partition
 * ==========================================================
 * <p>
 * Idea:
 * Instead of merging both sorted arrays and then finding the
 * median, we divide both arrays into two partitions:
 * <p>
 * - A left partition
 * - A right partition
 * <p>
 * The goal is to find a partition such that:
 * <p>
 * - The left partition contains half of the total elements.
 * - Every element in the left partition is less than or equal
 * to every element in the right partition.
 * <p>
 * For an odd total number of elements, the left partition
 * contains one extra element. This allows the median to always
 * be determined using the largest element of the left
 * partition.
 * <p>
 * Suppose {@code mid} elements are taken from {@code nums1}
 * and placed in the left partition.
 * <p>
 * Since the left partition must contain exactly
 * {@code leftHalf} elements, the number of elements that must
 * come from {@code nums2} is:
 * <p>
 * {@code y = leftHalf - mid}
 * <p>
 * Therefore, once a partition position is chosen in
 * {@code nums1}, the corresponding partition position in
 * {@code nums2} is automatically determined.
 * <p>
 * We binary search the partition position in {@code nums1}.
 * <p>
 * It is important to understand that {@code mid} represents a
 * partition position, or equivalently, the number of elements
 * taken from {@code nums1}. It does not represent the index of
 * an element.
 * <p>
 * For an array of length {@code n}, there are {@code n + 1}
 * possible partition positions:
 * <p>
 * {@code | 1 | 3 | 8 |}
 * <p>
 * Therefore, the binary search range is:
 * <p>
 * {@code 0 to nums1.length}
 * <p>
 * rather than:
 * <p>
 * {@code 0 to nums1.length - 1}
 * <p>
 * For each partition, we identify four boundary elements:
 * <p>
 * {@code nums1Left  | nums1Right}
 * <p>
 * {@code nums2Left  | nums2Right}
 * <p>
 * The partition is correct when:
 * <p>
 * {@code nums1Left <= nums2Right}
 * <p>
 * and:
 * <p>
 * {@code nums2Left <= nums1Right}
 * <p>
 * If:
 * <p>
 * {@code nums1Left > nums2Right}
 * <p>
 * then too many elements have been taken from {@code nums1}.
 * Therefore, the partition in {@code nums1} must move to the
 * left.
 * <p>
 * If:
 * <p>
 * {@code nums2Left > nums1Right}
 * <p>
 * then too few elements have been taken from {@code nums1}.
 * Therefore, the partition in {@code nums1} must move to the
 * right.
 * <p>
 * Before performing the binary search, we ensure that
 * {@code nums1} is the smaller array.
 * <p>
 * This guarantees that the corresponding partition position
 * {@code y} will remain within the valid range of
 * {@code nums2}.
 * <p>
 * Boundary partitions are handled using:
 * <p>
 * - {@code Integer.MIN_VALUE} when there is no element on the
 * left side of a partition.
 * <p>
 * - {@code Integer.MAX_VALUE} when there is no element on the
 * right side of a partition.
 * <p>
 * Once the correct partition is found:
 * <p>
 * - For an odd total length, the median is the largest element
 * in the left partition.
 * <p>
 * - For an even total length, the median is the average of the
 * largest element in the left partition and the smallest
 * element in the right partition.
 * <p>
 * Time Complexity  : O(log(min(m, n)))
 * Space Complexity : O(1)
 * <p>
 * ==========================================================
 */
public class MedianOfTwoSortedArrays {

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {

        // Always binary search the smaller array. This ensures
        // that the corresponding partition in nums2 remains valid.
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }

        int totalLength = nums1.length + nums2.length;

        // The number of elements that must be present in the
        // left partition. For an odd total length, the extra
        // element is placed in the left partition.
        int leftHalf = totalLength % 2 == 0
                ? totalLength / 2
                : (totalLength / 2) + 1;

        // We binary search partition positions rather than
        // element indices. Therefore, valid positions range
        // from 0 to nums1.length.
        int left = 0;
        int right = nums1.length;
        int mid = left + (right - left) / 2;

        int y;
        int nums1Left = Integer.MIN_VALUE;
        int nums1Right = Integer.MIN_VALUE;
        int nums2Left = Integer.MIN_VALUE;
        int nums2Right = Integer.MIN_VALUE;

        while (left <= right) {

            // If 'mid' elements are taken from nums1, the
            // remaining elements required for the left partition
            // must come from nums2.
            y = leftHalf - mid;

            // Determine the elements immediately surrounding
            // the partition in nums1.
            nums1Left = mid == 0 ? Integer.MIN_VALUE : nums1[mid - 1];
            nums1Right = mid == nums1.length ? Integer.MAX_VALUE : nums1[mid];

            // Determine the elements immediately surrounding
            // the partition in nums2.
            nums2Left = y == 0 ? Integer.MIN_VALUE : nums2[y - 1];
            nums2Right = y == nums2.length ? Integer.MAX_VALUE : nums2[y];

            // Too many elements have been taken from nums1,
            // so move the partition to the left.
            if (nums1Left > nums2Right) {
                right = mid - 1;

                // Too few elements have been taken from nums1,
                // so move the partition to the right.
            } else if (nums2Left > nums1Right) {
                left = mid + 1;

                // The partition is correct.
            } else {
                break;
            }

            // Calculate the next partition position.
            mid = left + (right - left) / 2;
        }

        // For an odd total number of elements, the median is
        // the largest element in the left partition.
        if (totalLength % 2 == 1) {
            return Math.max(nums1Left, nums2Left);
        }

        // For an even total number of elements, the median is
        // the average of the largest element in the left
        // partition and the smallest element in the right
        // partition.
        return (Math.max(nums1Left, nums2Left) + Math.min(nums1Right, nums2Right)) / 2.0;
    }
}
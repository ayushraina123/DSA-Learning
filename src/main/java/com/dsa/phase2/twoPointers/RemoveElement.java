package com.dsa.phase2.twoPointers;

public class RemoveElement {
    public int removeElement(int[] nums, int val) {
        int i = 0;
        int j = nums.length - 1;

        while (i < j) {
            if (nums[i] == val) {
                if (nums[j] == val) {
                    j--;
                    swap(nums, i, j);
                } else {
                    swap(nums, i, j);
                }
            }
            i++;
            j--;
        }

        return nums.length - j;
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}

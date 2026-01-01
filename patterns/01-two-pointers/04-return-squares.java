//https://leetcode.com/problems/squares-of-a-sorted-array/description/

import java.util.Arrays;

//Brute force
class Solution {
    //brute force
    public int[] sortedSquaresBrute(int[] nums) {
        int numsLength = nums.length;
        int[] tempArray = new int[numsLength];

        for (int i = 0; i < numsLength; i++) {
            tempArray[i] = nums[i] * nums[i];
        }

        Arrays.sort(tempArray);
        return tempArray;

    }

    //better / optimal
    public int[] sortedSquares(int[] nums) {
        int numsLength = nums.length;
        int[] squareArray = new int[numsLength];
        int leftPointer = 0;
        int rightPointer = numsLength - 1;
        int insertionIndex = numsLength - 1;

        while (leftPointer <= rightPointer) {
            if (Math.abs(nums[leftPointer]) < Math.abs(nums[rightPointer])) {
                squareArray[insertionIndex] = nums[rightPointer] * nums[rightPointer];
                rightPointer--;
                insertionIndex--;
            } else {
                squareArray[insertionIndex] = nums[leftPointer] * nums[leftPointer];
                leftPointer++;
                insertionIndex--;

            }

        }

        return squareArray;

    }

}

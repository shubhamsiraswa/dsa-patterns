//https://leetcode.com/problems/3sum/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Solution {
    public List<List<Integer>> threeSumBrute(int[] nums) {

        Set<List<Integer>> tripleSet = new HashSet<>();
        int n = nums.length;

        for (int i = 0; i < n - 2; i++) {
            for (int j = i + 1; j < n - 1; j++) {
                for (int k = j + 1; k < n; k++) {
                    if (nums[i] + nums[j] + nums[k] == 0) {
                        List<Integer> tempArray = new ArrayList<>();
                        tempArray.add(nums[i]);
                        tempArray.add(nums[j]);
                        tempArray.add(nums[k]);

                        Collections.sort(tempArray);
                        tripleSet.add(tempArray);

                    }
                }

            }
        }

        List<List<Integer>> ans = new ArrayList<>(tripleSet);

        return ans;

    }

    // Function to find triplets having sum equals to target
    public List<List<Integer>> threeSum(int[] nums) {

        // List to store the triplets that sum up to target
        List<List<Integer>> ans = new ArrayList<>();

        int n = nums.length;

        // Sort the input array nums
        Arrays.sort(nums);

        // Iterate through the array to find triplets
        for (int i = 0; i < n; i++) {
            // Skip duplicates
            if (i > 0 && nums[i] == nums[i - 1])
                continue;

            // Two pointers approach
            int j = i + 1;
            int k = n - 1;

            while (j < k) {
                int sum = nums[i] + nums[j] + nums[k];

                if (sum < 0) {
                    j++;
                } else if (sum > 0) {
                    k--;
                } else {
                    // Found a triplet that sums up to target
                    List<Integer> temp = new ArrayList<>();
                    temp.add(nums[i]);
                    temp.add(nums[j]);
                    temp.add(nums[k]);
                    ans.add(temp);

                    // Skip duplicates
                    j++;
                    k--;
                    while (j < k && nums[j] == nums[j - 1])
                        j++;
                    while (j < k && nums[k] == nums[k + 1])
                        k--;
                }
            }
        }

        return ans;
    }
}

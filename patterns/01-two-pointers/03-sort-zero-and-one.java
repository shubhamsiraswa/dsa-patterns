//https://www.geeksforgeeks.org/problems/segregate-0s-and-1s5106/1

class Solution {

    static void main(String[] args) {
        int[] arr = { 0, 1, 1, 0, 1, 0, 0, 1 };
        segregrate(arr);

        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
        }

    }

    static void segregrate(int[] arr) {
        int left = 0;
        int right = arr.length - 1;

        while (left < right) {
            while (left < right && arr[left] == 0) {
                left++;
            }

            while (left < right && arr[right] == 1) {
                right--;
            }

            if (left < right) {
                int temp = arr[left];
                arr[left] = arr[right];
                arr[right] = temp;
                left++;
                right--;
            }
        }

    }

}
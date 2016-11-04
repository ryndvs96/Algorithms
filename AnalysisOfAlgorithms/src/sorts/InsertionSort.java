package sorts;

import tools.Tools;

public class InsertionSort {
	public static void main(String[] args) {
		int[] arr1 = {3, 5, 9, 1, 2, 4, 8, 7, 6, 0};
		int[] arr2 = Tools.arrayRand(10, 0, 50);
		int[] arr3 = Tools.arrayRand(500, -1000, 1000);
		
		run(arr1);
		run(arr2);
		run(arr3);
	}
	
	public static void run(int[] arr) {
		System.out.println("Unsorted: " + Tools.arrayToString(arr));
		insertionSort(arr);
		System.out.println("Sorted: " + Tools.arrayToString(arr));
	}
	
	// O(n^2) inplace
	public static void insertionSort(int[] arr) {
		if (arr.length <= 1) {
			return;
		}
		
		// start at index 1, index 0 is already in the sorted set
		for (int i = 1; i < arr.length; i++) {
			// value we're comparing to the sorted set
			int val = arr[i];
			// last index of the sorted set
			int j = i - 1;
			// compare from right to left
			while (j >= 0 && arr[j] > val) {
				// move values one spot to the right for each compare
				// that's still greater than the compared value
				arr[j + 1] = arr[j];
				j--;
			}
			arr[j + 1] = val;
		}
	}
}

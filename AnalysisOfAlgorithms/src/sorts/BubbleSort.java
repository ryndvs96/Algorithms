package sorts;

import tools.Tools;

public class BubbleSort {
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
		bubbleSort(arr);
		System.out.println("Sorted: " + Tools.arrayToString(arr));
	}
	
	// O(n^2) inplace
	public static void bubbleSort(int[] arr) {
		if (arr.length <= 1) {
			return;
		}
		
		for (int i = 0; i < arr.length; i++) {
			for (int j = arr.length - 1; j > i; j--) {
				if (arr[j] < arr[j - 1]) {
					// swap
					int temp = arr[j - 1];
					arr[j - 1] = arr[j];
					arr[j] = temp;
				}
			}
		}
	}
}

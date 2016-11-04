package sorts;

import tools.Tools;

public class MergeSort {
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
		System.out.println("Sorted: " + Tools.arrayToString(mergeSort(arr)));
	}
	
	// O(nlogn) 
	public static int[] mergeSort(int[] arr) {
		// System.out.println("Arr: " + arrayToString(arr));
		// only do work if more than one element
		if (arr != null && arr.length > 1) {
			// get middle index floor
			int m = (int)((arr.length - 1) / 2);
			int[] arr1 = mergeSort(Tools.arraySlice(arr, 0, m));
			int[] arr2 = mergeSort(Tools.arraySlice(arr, m + 1, arr.length - 1));
			
			// now merge
			int[] ret = new int[arr1.length + arr2.length];
			int a = 0; // arr1
			int b = 0; // arr2
			for (int c = 0; c < ret.length; c++) {
				if (a >= arr1.length) {
					// flush out arr2
					ret[c] = arr2[b];
					b++;
				} else if (b >= arr2.length) {
					// flush out arr1
					ret[c] = arr1[a];
					a++;
				} else if (arr1[a] < arr2[b]) {
					ret[c] = arr1[a];
					a++;
				} else if (arr1[a] >= arr2[b]) {
					ret[c] = arr2[b];
					b++;
				}
			}
			return ret;
		} else {
			return arr;
		}
	}
}

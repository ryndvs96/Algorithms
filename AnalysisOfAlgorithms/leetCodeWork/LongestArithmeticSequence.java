import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class LongestArithmeticSequence {
	// http://codercareer.blogspot.com/2014/03/no-53-longest-arithmetic-sequence.html
	// http://www.geeksforgeeks.org/length-of-the-longest-arithmatic-progression-in-a-sorted-array/
	// http://jeffe.cs.illinois.edu/pubs/pdf/arith.pdf
	public static void main(String[] args) {
		System.out.println("Input list as one line with spaces inbetween numbers:");

		Scanner scan = new Scanner(System.in);
		String input = scan.nextLine();
		scan.close();

		String[] split = input.split(" ");
		ArrayList<Integer> list = new ArrayList<Integer>();

		try {
			for (String str : split)
				list.add(Integer.parseInt(str));
		} catch (NumberFormatException e) {
			System.err.println("Inalid input.");
		}

		int[] arr = new int[list.size()];
		for (int i = 0; i < list.size(); i++)
			arr[i] = list.get(i);

		System.out.println(llap(arr));
	}

	public static int llap(int[] arr) {
		// get preprocess hashmap
		HashMap<Integer, List<Pair>> map = process(arr);

		int[] dp = new int[arr.length];		// O(n) space
		int llap = 1;

		for (int diff : map.keySet()) {		// keys + values = O(n^2). 
			/*	keys + values = O(n^2)
			 * 	When hashing the differences there can be at most n^2 pairs
			 * 		of numbers and thus at most n^2 differences.
			 * 	If there's n^2 differences hashed as the keys, each key will
			 * 		only have one element in the list at it's value. That means
			 * 		O(n^2) iterations for the keys, and O(1) for each value, 
			 * 		thus O(n^2) * O(1) = O(n^2) overall.
			 * 	If there's only one distinct difference in the entire list,
			 * 		that key will have all n^2 pairs in it's list. That means
			 * 		O(1) iterations for the key, and O(n^2) for each value,
			 * 		thus O(1) * O(n^2) = O(n^2) overall.
			 * 	If there's n differences hashed as the keys, each key will have
			 * 		at most n pairs associated with it, and as we saw before
			 * 		it will have a total runtime of O(n^2).
			 * 	
			 * 	TL;DR the iterations will be based on how many values there are,
			 * 		there are always at most n^2 values
			 */
			
			List<Pair> list = map.get(diff);
			for (Pair p : list) {			 
				dp[p.j] = 1;
				dp[p.i] = 1;
			}
			for (Pair p : list) {
				dp[p.j] = dp[p.i] + 1;
				if (dp[p.j] > llap) {
					llap = dp[p.j];
				}
			}	
		}

		return llap;
	}

	/**
	 * time:	O(n^2)
	 * space:	O(n^2)
	 * @param arr
	 * @return
	 */
	public static HashMap<Integer, List<Pair>> process(int[] arr) {
		HashMap<Integer, List<Pair>> map = new HashMap<Integer, List<Pair>>();

		// insert all pairs of values [(i,j) | i < j] into map where
		// 	key = arr[i] - arr[j] 	// their difference
		// 	value = List<Pair> 		// list of pairs that have this difference

		for (int i = 0; i < arr.length - 1; i++) {			// O(n) time
			for (int j = i + 1; j < arr.length; j++) {		// O(n) time
				int diff = arr[j] - arr[i];
				if (map.containsKey(diff)) {				// O(1) avg
					map.get(diff).add(new Pair(i, j));		// O(1) avg
				} else {
					List<Pair> list = new ArrayList<Pair>();
					list.add(new Pair(i, j));
					map.put(diff, list);					// O(1) avg
				}
			}
		}

		return map;
	}
}

class Pair {
	public int i;
	public int j;
	public Pair(int i, int j) {
		this.i = i;
		this.j = j;
	}
}

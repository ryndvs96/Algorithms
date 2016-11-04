package tools;

public class Tools {
	public static int[] arraySlice(int[] arr, int i, int j) {
		if (j < i) {
			return null;
		} else if (j == i) {
			return new int[]{arr[i]};
		}
		int[] ret = new int[j - i + 1];
		for (int x = i, z = 0; x <= j; x++, z++) {
			ret[z] = arr[x];
		}
		return ret;
	}
	
	public static int[] arrayRand(int length, int min, int max) {
		int diff = max - min;
		if (diff <= 0) {
			return new int[]{min};
		}
		
		int[] arr = new int[length];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = min + (int)(Math.random() * diff);
		}
		
		return arr;
	}
	
	public static String arrayToString(int[] arr) {
		String str = "{";
		for (int x = 0; x < arr.length; x++) {
			if (x == arr.length - 1)
				str += "" + arr[x];
			else
				str += "" + arr[x] + ", ";
		}
		str += "}";
		return str;
	}
}

package tools;

public class Test {
	public static void main(String[] args) {
		for (int i = 16; i < 100000000; i *= 2) {
			System.out.println("n = " + i);
			int s = f(i);
			System.out.println("s = " + s);
			System.out.println("s / n = " + ((double) s / (double) i));
			System.out.println();
		}
	}
	public static int f(int n) {
		int s = 0;
		int p = (int) (Math.log10(n) / Math.log10(2));
		int j = n;
		for (int i = 1; i < p; i++) {
		  j /= 2;
		  for (int k = 1; k < j; k++)   
		    for (int m = 1; m <= i; m++)
		      s++;
		}
		return s;
	}
}

import java.util.Scanner;

public class EggDropping {
	/**
	 * http://www.geeksforgeeks.org/dynamic-programming-set-11-egg-dropping-puzzle/
	 * https://en.wikipedia.org/wiki/Dynamic_programming#Egg_dropping_puzzle
	 */
	public static void main(String... args) {
		Scanner scan = new Scanner(System.in);
		int n = 0;
		int k = 0;
		try {
			System.out.println("Input as n k:");
			n = scan.nextInt();
			k = scan.nextInt();
		} catch (NumberFormatException e) {
			System.err.println("Incorrect input format");
		} finally {
			scan.close();
		}
		
		System.out.println(eggDrop(n, k));		
	}
	
	public static int eggDrop(int eggs, int floors) {
		if (eggs < 1 || floors < 0) {
			return -1;
		}
		
		int[][] dp = new int[eggs][floors];
		
		for (int n = 0; n < eggs; n++) {
			dp[n][0] = 0;
			dp[n][1] = 1;
		}
		for (int k = 0; k < floors; k++) {
			dp[1][k] = k;
		}
		
		for (int n = 2; n < eggs; n++) {
			for (int k = 2; k < floors; k++) {
				int min = Integer.MAX_VALUE;
				for (int x = 1; x <= k; x++) {
					/*
					 * dp[n][k] =
					 * 	worst case of these: (max of these)
					 * 		egg breaks : dp[n - 1][k - x]
					 * 		egg is fine: dp[n][x - 1]
					 */
					int max = Integer.max(dp[n - 1][k - x], dp[n][x - 1]);
					min = Integer.min(min, max);
				}
				dp[n][k] = 1 + min;
			}
		}
		
		System.out.println("dp:");
		for (int j = 0; j < floors; j++) {
			System.out.print("-----");
		}
		System.out.println();
		for (int i = 0; i < eggs; i++) {
			for (int j = 0; j < floors; j++) {
				System.out.printf("| %2d ", dp[i][j]);
			}
			System.out.printf("|\n");
			for (int j = 0; j < floors; j++) {
				System.out.print("-----");
			}
			System.out.println();
		}
		
		return dp[eggs - 1][floors - 1];
	}
}

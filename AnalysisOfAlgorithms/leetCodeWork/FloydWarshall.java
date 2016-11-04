import java.util.Scanner;

public class FloydWarshall {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		System.out.println("num of vertices\nnum of edges\ni j weight\ni j weight\n...");
		int V = 0;
		int[][] w = null;
		try {
			// num of vertices
			V = scan.nextInt();

			// num of edges
			int E = scan.nextInt();

			// weight matrix
			w = new int[V][V];
			init(w);

			// get each edge and weight
			for (int k = 0; k < E; k++) {
				int i = scan.nextInt();
				int j = scan.nextInt();
				int weight = scan.nextInt();
				w[i - 1][j - 1] = weight;
			}
		} catch (NumberFormatException e) {
			System.err.println("Incorrect input format");
			return;
		} finally {
			scan.close();
		}

		floydWarshall(w, V);
	}

	public static void floydWarshall(int[][] w, int V) {
		// count of shortest paths
		int[][] n = new int[V][V];

		// shortest path length
		int[][] dp = new int[V][V];
		init(dp);

		for (int i = 0; i < V; i++) {
			for (int j = 0; j < V; j++) {
				// initializing loop
				dp[i][j] = w[i][j];
				if (w[i][j] != Integer.MAX_VALUE) {
					n[i][j] = 1;
				}
			}
		}

		for (int k = 0; k < V; k++) {
			for (int i = 0; i < V; i++) {
				for (int j = 0; j < V; j++) {
					if (i == j || i == k || j == k)
						continue;
					int left = dp[i][j];
					int right = dp[i][k] == Integer.MAX_VALUE || dp[k][j] == Integer.MAX_VALUE ? Integer.MAX_VALUE : dp[i][k] + dp[k][j];
					if (left > right) {
						// new shortest path
						dp[i][j] = right;
						n[i][j] = n[i][k] * n[k][j];
					} else if (dp[i][j] == right && dp[i][j] != Integer.MAX_VALUE) {
						// same shortest path, we can increment
						n[i][j] += n[i][k] * n[k][j];
					} else {
						// longer path, so we should do nothing
					}
				}
			}
		}

		System.out.println("weight matrix:");
		print(w);

		System.out.println("dp matrix:");
		print(dp);

		System.out.println("num matrix:");
		print(n);

		return;
	}

	public static void print(int[][] arr) {
		for (int i = 0; i < arr.length; i++) {
			System.out.print("[");

			for (int j = 0; j < arr[i].length; j++) {
				if (arr[i][j] == Integer.MAX_VALUE) {
					System.out.print(" inf");
				} else {
					System.out.printf("%4d", arr[i][j]);
				}
				if (j < arr[i].length - 1)
					System.out.print(", ");
			}

			System.out.println("]");
		}
	}

	public static void init(int[][] w) {
		for (int i = 0; i < w.length; i++) 
			for (int j = 0; j < w[i].length; j++) 
				w[i][j] = Integer.MAX_VALUE;
	}
}

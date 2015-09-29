
public class WordSearchSolve {
	static String[] wordList = {
			"FUCK", 
			"HOUSE", 
			"MAPLE", 
			"BRICK", 
			"SOCKS",
			"FRIDGE", 
			"HAMSTER", 
			"BRIDGE", 
			"STOOL", 
			"MAP"
	};
	static int r = 10;
	static char[][] grid = {
			{'Z', 'W', 'N', 'X', 'J', 'B', 'A', 'I', 'A', 'W'}, 
			{'I', 'H', 'E', 'B', 'R', 'I', 'D', 'G', 'E', 'K'}, 
			{'W', 'Z', 'O', 'I', 'E', 'M', 'W', 'Q', 'K', 'C'}, 
			{'M', 'P', 'C', 'U', 'T', 'E', 'B', 'S', 'G', 'E'}, 
			{'A', 'K', 'Z', 'P', 'S', 'L', 'M', 'F', 'G', 'M'}, 
			{'P', 'K', 'E', 'U', 'M', 'E', 'O', 'D', 'E', 'P'}, 
			{'L', 'C', 'F', 'I', 'A', 'J', 'I', 'O', 'A', 'T'}, 
			{'E', 'U', 'W', 'O', 'H', 'R', 'O', 'M', 'T', 'R'}, 
			{'V', 'F', 'S', 'Z', 'F', 'S', 'K', 'C', 'O', 'S'}, 
			{'W', 'V', 'G', 'J', 'E', 'Y', 'X', 'L', 'Y', 'S'}
	};
	static int n = 10;
	public static void main(String[] args) {
		System.out.println(east(grid));
		System.out.println(south(grid));
		System.out.println(southEast(grid));
		System.out.println(southWest(grid));
	}
	public static char[] east(char[][] grid) { 	// O(n^2)
		char[] east = new char[(n * n) + n]; // add n for the spaces
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				east[(i * n) + j + i] = grid[i][j];
			} 
			east[(i + 1) * n + i] = ' ';
		}
		return east;
	}
	public static char[] south(char[][] grid) {	// O(n^2)
		char[] south = new char[(n * n) + n]; // add n for the spaces
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				south[(i * n) + j + i] = grid[j][i];
			} 
			south[(i + 1) * n + i] = ' ';
		}
		return south;
	}
	public static char[] southEast(char[][] grid) {	// O(n^2)
		char[] southEast = new char[(n * n) + n + n - 1]; // add n for the spaces
		int count = 0;
		for (int i = 1; i < n; i++, count++) {
			for (int j = 0; j < i; j++, count++) {
				southEast[count] = grid[j][n - (i - j)];
			}
			southEast[count] = ' ';
			count++;
			for (int j = 0; j < i; j++, count++) {
				southEast[count] = grid[n - (i - j)][j];
			}
			southEast[count] = ' ';
		}
		for (int i = 0; i < n; i++, count++) {
			southEast[count] = grid[i][i];
		}
		return southEast;
	}
	public static char[] southWest(char[][] grid) {	// O(n^2)
		char[] southWest = new char[(n * n) + n + n - 1]; // add n for the spaces
		int count = 0;
		for (int i = 1; i < n; i++, count++) {
			for (int j = 0; j < i; j++, count++) {
				southWest[count] = grid[j][i - j - 1];
			}
			southWest[count] = ' ';
			count++;
			for (int j = 0; j < i; j++, count++) {
				southWest[count] = grid[n - i + j][n - j - 1];
			}
			southWest[count] = ' ';
		}
		for (int i = 0; i < n; i++, count++) {
			southWest[count] = grid[i][n - 1 - i];
		}
		return southWest;
	}
	public static char[] reverse(char[] forward) { // O(n^2) because the length is always ~n^2
		char[] reverse = new char[forward.length];
		for (int i = 0; i < forward.length; i++) {
			reverse[i] = forward[forward.length - 1 - i];
		}
		return reverse;
	}
}

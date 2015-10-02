import java.util.ArrayList;

public class WordSearchSolve {
	static ArrayList<String> wordList = new ArrayList<String>();
	static boolean capital = true;
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
		wordList.add("FUCK");
		wordList.add("HOUSE");
		wordList.add("MAPLE");
		wordList.add("BRICK");
		wordList.add("SOCKS");
		wordList.add("FRIDGE");
		wordList.add("HAMSTER");
		wordList.add("BRIDGE");
		wordList.add("STOOL");
		wordList.add("MAP");
		Object[] trie = getTrie(wordList);
		for (int i = 0; i < trie.length; i++) {
			Object obj = trie[i];
			System.out.println(((char) (i + 65)) + " : " + obj);
		}
	}
	public static ArrayList<Point> solve() {
		ArrayList<Point> solutions = new ArrayList<Point>();




		return solutions;
	}
	public static Object[] getTrie(ArrayList<String> wordList) { // O(n^2) it's limited by 8n^2
		Object[] trie = new Object[26];
		Object[] currentTrie;
		for (String word : wordList) {
			currentTrie = trie;
			char[] wordChar = word.toCharArray();
			for (int i = 0; i < wordChar.length - 1; i++) {
				char c = wordChar[i];
				int index = index(c);
				if (currentTrie[index] == null) {
					currentTrie[index] = new Object[26];
				}
				currentTrie = (Object[]) currentTrie[index];
			}
			currentTrie[index(wordChar[wordChar.length - 1])] = new Boolean(Boolean.TRUE);
		}
		return trie;
	}
	public static char[] east(char[][] grid) { 	// O(n^2)
		char[] east = new char[(n * n) + n - 1]; // add n for the spaces
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				east[(i * n) + j + i] = grid[i][j];
			} 
			if (i != n - 1) {
				east[(i + 1) * n + i] = ' ';
			}
		}
		return east;
	}
	public static char[] south(char[][] grid) {	// O(n^2)
		char[] south = new char[(n * n) + n - 1]; // add n for the spaces
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				south[(i * n) + j + i] = grid[j][i];
			} 
			if (i != n - 1) {
				south[(i + 1) * n + i] = ' ';
			}
		}
		return south;
	}
	public static char[] southEast(char[][] grid) {	// O(n^2)
		char[] southEast = new char[(n * n) + (2 * n) - 2]; // add n for the spaces
		int count = 0;
		for (int i = 1; i < n; i++, count++) {
			for (int j = 0; j < i; j++, count++) {
				southEast[count] = grid[j][n - (i - j)];
			}
			southEast[count] = ' ';
		}
		for (int i = 0; i < n; i++, count++) {
			southEast[count] = grid[i][i];
		}
		southEast[count] = ' ';
		count++;
		for (int i = n - 1; i > 0; i--, count++) {
			for (int j = 0; j < i; j++, count++) {
				southEast[count] = grid[n - (i - j)][j];
			}
			if (i != 1) {
				southEast[count] = ' ';
			}
		}
		return southEast;
	}
	public static char[] southWest(char[][] grid) {	// O(n^2)
		char[] southWest = new char[(n * n) + (2 * n) - 2]; // add n for the spaces
		int count = 0;
		for (int i = 1; i < n; i++, count++) { 	// first half
			for (int j = 0; j < i; j++, count++) {
				southWest[count] = grid[j][i - j - 1];
			}
			southWest[count] = ' ';
		}
		for (int i = 0; i < n; i++, count++) { 	// middle
			southWest[count] = grid[i][n - 1 - i];
		}
		southWest[count] = ' ';
		count++;
		for (int i = n - 1; i > 0; i--, count++) {	// second half
			for (int j = 0; j < i; j++, count++) {
				southWest[count] = grid[n - i + j][n - j - 1];
			}
			if (i != 1) {
				southWest[count] = ' ';
			}
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
	public static int index(char c) {
		return capital ? c - 65 : c - 97;
	}
}

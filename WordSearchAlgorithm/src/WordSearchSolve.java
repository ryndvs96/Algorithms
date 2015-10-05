import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class WordSearchSolve {
	static ArrayList<String> wordList = new ArrayList<String>();
	static char[][] grid;
	static boolean capital = true;
	static int r;
	static int n;
	static ArrayList<Point> solutions = new ArrayList<Point>();
	static Object[] currentTrie;
	static String currentWord;
	static char currentChar;
	static Object[] trie;
	public static void main(String[] args) {
		getGrid();
		getWordList();
		long start = System.currentTimeMillis();
		trie = getTrie(wordList);
		east();
		west();
		north();
		south();
		northWest();
		southEast();
		southWest();
		northEast();
		long end = System.currentTimeMillis();
		print(solutions);
		System.out.println(end - start);
	}
	public static void getWordList() {
		try {
			//Create object of FileReader
			FileReader inputFile = new FileReader("C:\\Users\\Ryan\\git\\Algorithms\\WordSearchAlgorithm\\src\\wordList.txt");

			//Instantiate the BufferedReader Class
			BufferedReader bufferReader = new BufferedReader(inputFile);

			String line;

			int i = 0;
			while ((line = bufferReader.readLine()) != null) {
				wordList.add(line.trim());
				i++;
			}
			r = i;
			bufferReader.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void getGrid() {
		try {
			//Create object of FileReader
			FileReader inputFile = new FileReader("C:\\Users\\Ryan\\git\\Algorithms\\WordSearchAlgorithm\\src\\grid.txt");

			//Instantiate the BufferedReader Class
			BufferedReader bufferReader = new BufferedReader(inputFile);

			String line;

			n = 40;
			grid = new char[n][n];
			int i = 0;
			int j = 0;
			while ((line = bufferReader.readLine()) != null) {
				line = line.replaceAll(" ", "");
				j = 0;
				for (char c : line.toCharArray()) {
					grid[i][j] = c;
					j++;
				}
				i++;
			}
			bufferReader.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void innerForLoop(int y, int x, int direction) {
		currentChar = grid[y][x];
		Object obj = currentTrie[index(currentChar)];
		if (obj == null) {
			Object tempObj = trie[index(currentChar)];
			if (tempObj != null) {
				currentWord = "" + currentChar;
				if (obj instanceof Boolean) {
					if ((Boolean) obj) {
						solutions.add(new Point(y, x, direction, currentWord));
					}
				}
				else {
					currentTrie = (Object[]) tempObj;
				}
			}
			else {
				currentWord = "";
				currentTrie = trie;
			}
		}
		else {
			currentWord += currentChar;
			if (obj instanceof Boolean) {
				if ((Boolean) obj) {
					currentTrie[index(currentChar)] = new Boolean(Boolean.FALSE);
					solutions.add(new Point(y, x, direction, currentWord));
				}
				currentTrie = trie;
			}
			else {
				currentTrie = (Object[]) obj;
			}
		}
	}
	public static void print(ArrayList<Point> solutions) {
		for (Point p : solutions) {
			String str = "";
			str += p.word + ": ";
			str += "(" + p.i + ", " + p.j + ") " + p.direction;
			System.out.println(str);
		}
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
				if (currentTrie[index] == null || currentTrie[index] instanceof Boolean) {
					currentTrie[index] = new Object[26];
				}
				currentTrie = (Object[]) currentTrie[index];
			}
			if (currentTrie[index(wordChar[wordChar.length - 1])] == null) {
				currentTrie[index(wordChar[wordChar.length - 1])] = new Boolean(Boolean.TRUE);
			}
		}
		return trie;
	}
	public static void east() {	// O(n^2)
		for (int i = 0; i < n; i++) {
			currentWord = "";
			currentTrie = trie;
			for (int j = 0; j < n; j++) { //currentChar = grid[i][j];
				innerForLoop(i, j, 3);
			} 
		}
	}
	public static void west() {	// O(n^2)
		for (int i = n - 1; i >= 0; i--) {
			currentWord = "";
			currentTrie = trie;
			for (int j = n - 1; j >= 0; j--) { //currentChar = grid[i][j];
				innerForLoop(i, j, 7);
			} 
		}
	}
	public static void south() { // O(n^2)
		for (int i = 0; i < n; i++) {
			currentWord = "";
			currentTrie = trie;
			for (int j = 0; j < n; j++) {//currentChar = grid[j][i];
				innerForLoop(j, i, 5);
			} 
		}
	}
	public static void north() { // O(n^2)
		for (int i = n - 1; i >= 0; i--) {
			currentWord = "";
			currentTrie = trie;
			for (int j = n - 1; j >= 0; j--) {
				innerForLoop(j, i, 1);
			} 
		}
	}
	public static void southEast() { // O(n^2)
		// Iterations of each for loop are listed; they total: n^2 iterations

		// This first set of nested for loops has a total of
		// (n^2 - n) / 2 operations
		for (int i = 1; i < n; i++) { // n - 1 iterations
			currentWord = "";
			currentTrie = trie;
			for (int j = 0; j < i; j++) { // //currentChar = grid[j][n - (i - j)];
				innerForLoop(j, n - (i - j), 4);
			}
		}
		// This next for loop does n iterations
		currentWord = "";
		currentTrie = trie;
		for (int i = 0; i < n; i++) { //currentChar = grid[i][i];
			innerForLoop(i, i, 4);
		}
		// This final set of nested for loops has a total of
		// (n^2 - n) / 2 iterations
		for (int i = n - 1; i > 0; i--) {
			currentWord = "";
			currentTrie = trie;
			for (int j = 0; j < i; j++) {//currentChar = grid[n - (i - j)][j];
				innerForLoop(n - (i - j), j, 8);
			}
		}
	}
	public static void northWest() {	// O(n^2)
		for (int i = n - 1; i >= 1; i--) {
			currentWord = "";
			currentTrie = trie;
			for (int j = i - 1; j >= 0; j--) { //currentChar = grid[j][n - (i - j)];
				innerForLoop(j, n - (i - j), 8);
			}
		}
		currentWord = "";
		currentTrie = trie;
		for (int i = n - 1; i >= 0; i--) { //currentChar = grid[i][i];
			innerForLoop(i, i, 8);
		}
		for (int i = 1; i < n; i++) {
			currentWord = "";
			currentTrie = trie;
			for (int j = i - 1; j >= 0; j--) { //currentChar = grid[n - (i - j)][j];
				innerForLoop(n - (i - j), j, 8);
			}
		}
	}
	public static void southWest() {	// O(n^2)
		for (int i = 1; i < n; i++) { 	// first half
			currentWord = "";
			currentTrie = trie;
			for (int j = 0; j < i; j++) { //currentChar = grid[j][i - j - 1];
				innerForLoop(j, i - j - 1, 6);
			}
		}
		currentWord = "";
		currentTrie = trie;
		for (int i = 0; i < n; i++) { 	// middle currentChar = grid[i][n - 1 - i];
			innerForLoop(i, n - i - 1, 6);
		}
		for (int i = n - 1; i > 0; i--) {	// second half 
			currentWord = "";
			currentTrie = trie;
			for (int j = 0; j < i; j++) { //currentChar = grid[n - i + j][n - j - 1];
				innerForLoop(n - i + j, n - j - 1, 6);
			}
		}
	}
	public static void northEast() {	// O(n^2)
		for (int i = n - 1; i >= 1; i--) { 	// first half
			currentWord = "";
			currentTrie = trie;
			for (int j = i - 1; j >= 0; j--) { //currentChar = grid[j][i - j - 1];
				innerForLoop(j, i - j - 1, 2);
			}
		}
		currentWord = "";
		currentTrie = trie;
		for (int i = n - 1; i >= 0; i--) { 	// middle currentChar = grid[i][n - 1 - i];
			innerForLoop(i, n - 1 - i, 2);
		}
		for (int i = 1; i < n; i++) {	// second half
			currentWord = "";
			currentTrie = trie;
			for (int j = i - 1; j >= 0; j--) { //currentChar = grid[n - i + j][n - j - 1];
				innerForLoop(n - i + j, n - j - 1, 2);
			}
		}
	}
	public static int index(char c) {
		return capital ? c - 65 : c - 97;
	}
}

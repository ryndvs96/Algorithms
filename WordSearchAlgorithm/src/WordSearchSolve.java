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
		ArrayList<Point> solutions = new ArrayList<Point>();
		solutions.addAll(east(grid, trie));
		solutions.addAll(west(grid, trie));
		solutions.addAll(north(grid, trie));
		solutions.addAll(south(grid, trie));
		solutions.addAll(northWest(grid, trie));
		solutions.addAll(southEast(grid, trie));
		solutions.addAll(southWest(grid, trie));
		solutions.addAll(northEast(grid, trie));
		print(solutions);
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
				if (currentTrie[index] == null) {
					currentTrie[index] = new Object[26];
				}
				currentTrie = (Object[]) currentTrie[index];
			}
			currentTrie[index(wordChar[wordChar.length - 1])] = new Boolean(Boolean.TRUE);
		}
		return trie;
	}
	public static ArrayList<Point> east(char[][] grid, Object[] trie) {	// O(n^2)
		ArrayList<Point> solutions = new ArrayList<Point>();
		Object[] currentTrie;
		String currentWord;
		char currentChar;
		for (int i = 0; i < n; i++) {
			currentWord = "";
			currentTrie = trie;
			for (int j = 0; j < n; j++) {
				currentChar = grid[i][j];
				Object obj = currentTrie[index(currentChar)];
				if (obj == null) {
					currentWord = "";
					currentTrie = trie;
					continue;
				}
				else {
					currentWord += currentChar;
					if (obj instanceof Boolean) {
						if ((Boolean) obj) {
							currentTrie[index(currentChar)] = new Boolean(Boolean.FALSE);
							solutions.add(new Point(i, j, 3, currentWord));
						}
						currentTrie = trie;
					}
					else {
						currentTrie = (Object[]) obj;
					}
				}
			} 
		}
		return solutions;
	}
	public static ArrayList<Point> west(char[][] grid, Object[] trie) {	// O(n^2)
		ArrayList<Point> solutions = new ArrayList<Point>();
		Object[] currentTrie;
		String currentWord;
		char currentChar;
		for (int i = n - 1; i >= 0; i--) {
			currentWord = "";
			currentTrie = trie;
			for (int j = n - 1; j >= 0; j--) {
				currentChar = grid[i][j];
				Object obj = currentTrie[index(currentChar)];
				if (obj == null) {
					currentWord = "";
					currentTrie = trie;
					continue;
				}
				else {
					currentWord += currentChar;
					if (obj instanceof Boolean) {
						if ((Boolean) obj) {
							currentTrie[index(currentChar)] = new Boolean(Boolean.FALSE);
							solutions.add(new Point(i, j, 7, currentWord));
						}
						currentTrie = trie;
					}
					else {
						currentTrie = (Object[]) obj;
					}
				}
			} 
		}
		return solutions;
	}
	public static ArrayList<Point> south(char[][] grid, Object[] trie) {	// O(n^2)
		ArrayList<Point> solutions = new ArrayList<Point>();
		Object[] currentTrie;
		String currentWord;
		char currentChar;
		for (int i = 0; i < n; i++) {
			currentWord = "";
			currentTrie = trie;
			for (int j = 0; j < n; j++) {
				currentChar = grid[j][i];
				Object obj = currentTrie[index(currentChar)];
				if (obj == null) {
					currentWord = "";
					currentTrie = trie;
					continue;
				}
				else {
					currentWord += currentChar;
					if (obj instanceof Boolean) {
						if ((Boolean) obj) {
							currentTrie[index(currentChar)] = new Boolean(Boolean.FALSE);
							solutions.add(new Point(j, i, 5, currentWord));
						}
						currentTrie = trie;
					}
					else {
						currentTrie = (Object[]) obj;
					}
				}
			} 
		}
		return solutions;
	}
	public static ArrayList<Point> north(char[][] grid, Object[] trie) {	// O(n^2)
		ArrayList<Point> solutions = new ArrayList<Point>();
		Object[] currentTrie;
		String currentWord;
		char currentChar;
		for (int i = n - 1; i >= 0; i--) {
			currentWord = "";
			currentTrie = trie;
			for (int j = n - 1; j >= 0; j--) {
				currentChar = grid[j][i];
				Object obj = currentTrie[index(currentChar)];
				if (obj == null) {
					Object tempObj = trie[index(currentChar)];
					if (tempObj != null) {
						currentWord = "" + currentChar; // TODO
						if (obj instanceof Boolean) {
							if ((Boolean) obj) {
								solutions.add(new Point(j, i, 1, currentWord));
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
					continue;
				}
				else {
					currentWord += currentChar;
					if (obj instanceof Boolean) {
						if ((Boolean) obj) {
							currentTrie[index(currentChar)] = new Boolean(Boolean.FALSE);
							solutions.add(new Point(j, i, 1, currentWord));
						}
						currentTrie = trie;
					}
					else {
						currentTrie = (Object[]) obj;
					}
				}
			} 
		}
		return solutions;
	}
	public static ArrayList<Point> southEast(char[][] grid, Object[] trie) {	// O(n^2)
		ArrayList<Point> solutions = new ArrayList<Point>();
		Object[] currentTrie;
		String currentWord;
		char currentChar;
		for (int i = 1; i < n; i++) {
			currentWord = "";
			currentTrie = trie;
			for (int j = 0; j < i; j++) {
				currentChar = grid[j][n - (i - j)];
				Object obj = currentTrie[index(currentChar)];
				if (obj == null) {
					currentWord = "";
					currentTrie = trie;
					continue;
				}
				else {
					currentWord += currentChar;
					if (obj instanceof Boolean) {
						if ((Boolean) obj) {
							currentTrie[index(currentChar)] = new Boolean(Boolean.FALSE);
							solutions.add(new Point(j, n - (i - j), 4, currentWord));
						}
						currentTrie = trie;
					}
					else {
						currentTrie = (Object[]) obj;
					}
				}
			}
		}
		currentWord = "";
		currentTrie = trie;
		for (int i = 0; i < n; i++) {
			currentChar = grid[i][i];
			Object obj = currentTrie[index(currentChar)];
			if (obj == null) {
				currentWord = "";
				currentTrie = trie;
				continue;
			}
			else {
				currentWord += currentChar;
				if (obj instanceof Boolean) {
					if ((Boolean) obj) {
						currentTrie[index(currentChar)] = new Boolean(Boolean.FALSE);
						solutions.add(new Point(i, i, 4, currentWord));
					}
					currentTrie = trie;
				}
				else {
					currentTrie = (Object[]) obj;
				}
			}
		}
		for (int i = n - 1; i > 0; i--) {
			currentWord = "";
			currentTrie = trie;
			for (int j = 0; j < i; j++) {
				currentChar = grid[n - (i - j)][j];
				Object obj = currentTrie[index(currentChar)];
				if (obj == null) {
					currentWord = "";
					currentTrie = trie;
					continue;
				}
				else {
					currentWord += currentChar;
					if (obj instanceof Boolean) {
						if ((Boolean) obj) {
							currentTrie[index(currentChar)] = new Boolean(Boolean.FALSE);
							solutions.add(new Point(n - (i - j), j, 4, currentWord));
						}
						currentTrie = trie;
					}
					else {
						currentTrie = (Object[]) obj;
					}
				}
			}
		}
		return solutions;
	}
	public static ArrayList<Point> northWest(char[][] grid, Object[] trie) {	// O(n^2)
		ArrayList<Point> solutions = new ArrayList<Point>();
		Object[] currentTrie;
		String currentWord;
		char currentChar;
		for (int i = n - 1; i >= 1; i--) {
			currentWord = "";
			currentTrie = trie;
			for (int j = i - 1; j >= 0; j--) {
				currentChar = grid[j][n - (i - j)];
				Object obj = currentTrie[index(currentChar)];
				if (obj == null) {
					currentWord = "";
					currentTrie = trie;
					continue;
				}
				else {
					currentWord += currentChar;
					if (obj instanceof Boolean) {
						if ((Boolean) obj) {
							currentTrie[index(currentChar)] = new Boolean(Boolean.FALSE);
							solutions.add(new Point(j, n - (i - j), 8, currentWord));
						}
						currentTrie = trie;
					}
					else {
						currentTrie = (Object[]) obj;
					}
				}
			}
		}
		currentWord = "";
		currentTrie = trie;
		for (int i = n - 1; i >= 0; i--) {
			currentChar = grid[i][i];
			Object obj = currentTrie[index(currentChar)];
			if (obj == null) {
				currentWord = "";
				currentTrie = trie;
				continue;
			}
			else {
				currentWord += currentChar;
				if (obj instanceof Boolean) {
					if ((Boolean) obj) {
						currentTrie[index(currentChar)] = new Boolean(Boolean.FALSE);
						solutions.add(new Point(i, i, 8, currentWord));
					}
					currentTrie = trie;
				}
				else {
					currentTrie = (Object[]) obj;
				}
			}
		}
		for (int i = 1; i < n; i++) {
			currentWord = "";
			currentTrie = trie;
			for (int j = i - 1; j >= 0; j--) {
				currentChar = grid[n - (i - j)][j];
				Object obj = currentTrie[index(currentChar)];
				if (obj == null) {
					currentWord = "";
					currentTrie = trie;
					continue;
				}
				else {
					currentWord += currentChar;
					if (obj instanceof Boolean) {
						if ((Boolean) obj) {
							currentTrie[index(currentChar)] = new Boolean(Boolean.FALSE);
							solutions.add(new Point(n - (i - j), j, 8, currentWord));
						}
						currentTrie = trie;
					}
					else {
						currentTrie = (Object[]) obj;
					}
				}
			}
		}
		return solutions;
	}
	public static ArrayList<Point> southWest(char[][] grid, Object[] trie) {	// O(n^2)
		ArrayList<Point> solutions = new ArrayList<Point>();
		Object[] currentTrie;
		String currentWord;
		char currentChar;
		for (int i = 1; i < n; i++) { 	// first half
			currentWord = "";
			currentTrie = trie;
			for (int j = 0; j < i; j++) {
				currentChar = grid[j][i - j - 1];
				Object obj = currentTrie[index(currentChar)];
				if (obj == null) {
					currentWord = "";
					currentTrie = trie;
					continue;
				}
				else {
					currentWord += currentChar;
					if (obj instanceof Boolean) {
						if ((Boolean) obj) {
							currentTrie[index(currentChar)] = new Boolean(Boolean.FALSE);
							solutions.add(new Point(j, i - j - 1, 6, currentWord));
						}
						currentTrie = trie;
					}
					else {
						currentTrie = (Object[]) obj;
					}
				}
			}
		}
		currentWord = "";
		currentTrie = trie;
		for (int i = 0; i < n; i++) { 	// middle
			currentChar = grid[i][n - 1 - i];
			Object obj = currentTrie[index(currentChar)];
			if (obj == null) {
				currentWord = "";
				currentTrie = trie;
				continue;
			}
			else {
				currentWord += currentChar;
				if (obj instanceof Boolean) {
					if ((Boolean) obj) {
						currentTrie[index(currentChar)] = new Boolean(Boolean.FALSE);
						solutions.add(new Point(i, n - 1 - i, 6, currentWord));
					}
					currentTrie = trie;
				}
				else {
					currentTrie = (Object[]) obj;
				}
			}
		}
		for (int i = n - 1; i > 0; i--) {	// second half
			currentWord = "";
			currentTrie = trie;
			for (int j = 0; j < i; j++) {
				currentChar = grid[n - i + j][n - j - 1];
				Object obj = currentTrie[index(currentChar)];
				if (obj == null) {
					currentWord = "";
					currentTrie = trie;
					continue;
				}
				else {
					currentWord += currentChar;
					if (obj instanceof Boolean) {
						if ((Boolean) obj) {
							currentTrie[index(currentChar)] = new Boolean(Boolean.FALSE);
							solutions.add(new Point(n - i + j, n - j - 1, 6, currentWord));
						}
						currentTrie = trie;
					}
					else {
						currentTrie = (Object[]) obj;
					}
				}
			}
		}
		return solutions;
	}
	public static ArrayList<Point> northEast(char[][] grid, Object[] trie) {	// O(n^2)
		ArrayList<Point> solutions = new ArrayList<Point>();
		Object[] currentTrie;
		String currentWord;
		char currentChar;
		for (int i = n - 1; i >= 1; i--) { 	// first half
			currentWord = "";
			currentTrie = trie;
			for (int j = i - 1; j >= 0; j--) {
				currentChar = grid[j][i - j - 1];
				Object obj = currentTrie[index(currentChar)];
				if (obj == null) {
					currentWord = "";
					currentTrie = trie;
					continue;
				}
				else {
					currentWord += currentChar;
					if (obj instanceof Boolean) {
						if ((Boolean) obj) {
							currentTrie[index(currentChar)] = new Boolean(Boolean.FALSE);
							solutions.add(new Point(j, i - j - 1, 2, currentWord));
						}
						currentTrie = trie;
					}
					else {
						currentTrie = (Object[]) obj;
					}
				}
			}
		}
		currentWord = "";
		currentTrie = trie;
		for (int i = n - 1; i >= 0; i--) { 	// middle
			currentChar = grid[i][n - 1 - i];
			Object obj = currentTrie[index(currentChar)];
			if (obj == null) {
				currentWord = "";
				currentTrie = trie;
				continue;
			}
			else {
				currentWord += currentChar;
				if (obj instanceof Boolean) {
					if ((Boolean) obj) {
						currentTrie[index(currentChar)] = new Boolean(Boolean.FALSE);
						solutions.add(new Point(i, n - 1 - i, 2, currentWord));
					}
					currentTrie = trie;
				}
				else {
					currentTrie = (Object[]) obj;
				}
			}
		}
		for (int i = 1; i < n; i++) {	// second half
			currentWord = "";
			currentTrie = trie;
			for (int j = i - 1; j >= 0; j--) {
				currentChar = grid[n - i + j][n - j - 1];
				Object obj = currentTrie[index(currentChar)];
				if (obj == null) {
					currentWord = "";
					currentTrie = trie;
					continue;
				}
				else {
					currentWord += currentChar;
					if (obj instanceof Boolean) {
						if ((Boolean) obj) {
							currentTrie[index(currentChar)] = new Boolean(Boolean.FALSE);
							solutions.add(new Point(n - i + j, n - j - 1, 2, currentWord));
						}
						currentTrie = trie;
					}
					else {
						currentTrie = (Object[]) obj;
					}
				}
			}
		}
		return solutions;
	}
	public static int index(char c) {
		return capital ? c - 65 : c - 97;
	}
}

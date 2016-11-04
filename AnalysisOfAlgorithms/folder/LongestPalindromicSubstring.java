import java.util.Scanner;

public class LongestPalindromicSubstring {
	public static void main(String[] args) {
		System.out.println("Input:");
		
		Scanner scan = new Scanner(System.in);
		String str = scan.nextLine();
		scan.close();
		
		System.out.println(longestPalindrome(str));
	}
	
    public static String longestPalindrome(String s) {
        int l = 0;
        int r = 0;
        
        boolean[][] dp = new boolean[s.length()][s.length()];
        
        // set up base case length 1
        for (int i = 0; i < s.length(); i++)
            dp[i][i] = true;
        
        
        for (int len = 1; len < s.length(); len++) {
            for (int i = 0, j = len; j < s.length(); i++, j++) {
                dp[i][j] = (dp[i+1][j-1] || len == 1) && (s.charAt(i) == s.charAt(j));
                if (dp[i][j] && (j - i > r - l)) {
                    r = j;
                    l = i;
                }
            }
        }
                    
        return s.substring(l, r + 1);
    }
}

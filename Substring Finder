import java.util.ArrayList;
import static org.junit.Assert.*;

/**
 * Description of solution: Uses recursion to find substrings of s. Appends characters to each substring to get substrings that are non-contiguous.
 */

public class substringFinder{

	
	// Return all substrings of the String s
	public static ArrayList<String> allSubstrings(String s){
		ArrayList<String> result = new ArrayList<String>();
		//Adds empty string to ArrayList, base case for recursion
		if (s.length() == 0) {
			result.add("");
			return result;
		}
		//Recursion
		ArrayList<String> substrings = allSubstrings(s.substring(0, s.length() - 1));
		//Adds substrings to result ArrayList
		result.addAll(substrings);
		//Loops through and adds the letter at end of String s to the substring
		for (String str: substrings) {
			result.add(str + (s.charAt(s.length() - 1)));
		}
		return result;
	}
			   
	
	public static void main(String[] args) {
		ArrayList<String> s = allSubstrings("abcde");
		System.out.println("Testing...");
		assertEquals(s.size(), 32);
		assertEquals(s.contains(""), true);
		assertEquals(s.contains("a"), true);
		assertEquals(s.contains("b"), true);
		assertEquals(s.contains("c"), true);
		assertEquals(s.contains("d"), true);
		assertEquals(s.contains("e"), true);
		assertEquals(s.contains("ab"), true);
		assertEquals(s.contains("ac"), true);
		assertEquals(s.contains("ad"), true);
		assertEquals(s.contains("ae"), true);
		assertEquals(s.contains("bc"), true);
		assertEquals(s.contains("bd"), true);
		assertEquals(s.contains("be"), true);
		assertEquals(s.contains("cd"), true);
		assertEquals(s.contains("ce"), true);
		assertEquals(s.contains("de"), true);
		assertEquals(s.contains("abc"), true);
		assertEquals(s.contains("abd"), true);
		assertEquals(s.contains("abe"), true);
		assertEquals(s.contains("acd"), true);
		assertEquals(s.contains("ace"), true);
		assertEquals(s.contains("ade"), true);
		assertEquals(s.contains("bcd"), true);
		assertEquals(s.contains("bce"), true);
		assertEquals(s.contains("bde"), true);
		assertEquals(s.contains("cde"), true);
		assertEquals(s.contains("abcd"), true);
		assertEquals(s.contains("abce"), true);
		assertEquals(s.contains("acde"), true);
		assertEquals(s.contains("abde"), true);
		assertEquals(s.contains("bcde"), true);
		assertEquals(s.contains("abcde"), true);
		System.out.println("Success!");
		
	}

}

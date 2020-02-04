import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import static org.junit.Assert.*;

/**
 * Uses recursion to find all possible sums of elements in ArrayList.
 * Adds single numbers, then adds all others numbers to that number, then recurses to next number.
 */

public class allSums {

	// Return all sums that can be formed from subsets of elements in arr
	public static ArrayList<Integer> allSums(ArrayList<Integer> arr ) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		//Adds 0 to ArrayList, base case for recursion
		if (arr.size() == 0) {
			result.add(0);
			return result;
		}
		//Recursion
		ArrayList<Integer> subints = allSums(new ArrayList<Integer> (arr.subList(0, arr.size()-1)));
		//Adds substring numbers to result ArrayList
		result.addAll(subints);
		//Loops through and adds the number at end of the ArrayList to the substring numbers
		for (Integer num: subints) {
			result.add(num + (arr.get(arr.size()-1)));
		}
		return result;
	}
	
	public static void main(String[] args) {
		ArrayList<Integer> result = new ArrayList<Integer>();//= Files.readAllLines(Paths.get("nums.txt"));
		try {
			BufferedReader br = new BufferedReader(new FileReader("/Users/harbe/Documents/nums.txt"));
			while (br.ready()) {
				result.add(Integer.parseInt(br.readLine()));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		ArrayList<Integer> sums = allSums(result);
		
		System.out.println("Testing...");
		assertEquals(sums.size(), 8);
		assertEquals(sums.contains(0), true);
		assertEquals(sums.contains(1), true);
		assertEquals(sums.contains(2), true);
		assertEquals(sums.contains(4), true);
		assertEquals(sums.contains(3), true);
		assertEquals(sums.contains(5), true);
		assertEquals(sums.contains(6), true);
		assertEquals(sums.contains(7), true);
		System.out.println("Success!");
	}
}

import static org.junit.Assert.*;

/**
 * Recursively returns n choose k by adding (decremented n elements choose k distinct elements) with (decremented elements choose decremented k distinct elements)
 */


public class subsetFinder {

	// Return the number of ways to choose a subset of k distinct elements from a set of n elements  
	public static int C( int n, int k ) {
		//if k equals n or k then all elements are the subset, so return 1.
		//if k equals 0 then there is only one subset with nothing, so return 1.
		if(k == n || k == 0) {
			return 1;
		}
		//Recursion
		return C(n-1, k) + C(n-1,k-1);
	}
	
	public static void main(String[] args) {
		System.out.println("Testing...");
		assertEquals(C(14,3), 364);
		assertEquals(C(14,11), 364);
		assertEquals(C(18,8), 43758);
		System.out.println("Success!");
	}

}

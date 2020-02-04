import static org.junit.Assert.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


/**
 * This is an implementation of a hash table.
 */
 
public class HashTable {


	/* Class containing next node and key and name value*/
	class Node { 
		private long key;

		public long getKey() {
			return key;
		}

		public void setKey(long key) {
			this.key = key;
		}

		private String value;

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}

		Node next; 

		public Node getNext() {
			return next;
		}

		public void setNext(Node next) {
			this.next = next;
		}

		Node(long key, String value) {
			this.key = key;
			this.value = value;
			next = null;
		}
	} 

	//The hash table array
	Node[] array = null;
	//hash table size. We typically choose m to be a power of 2
	int m = 0;

	// Constructor 
	HashTable(int size) {  
		m = size;
		array = new Node[size];  
	}

	HashTable() {  
		this(100);
	} 

	// Search for a given key in the hashtable
	// Note: you need to use hashFunction(long key) to determine the slot (array index) you will search in
	public Node search(long key) 
	{ 
		Node node = null;
		int i = hashFunction(key);
		node = array[i];
		while(node != null) {
			if(node.key == key) {
				return node;
			}
			node = node.next;
		}
		return null;
	}

	// Insert a new Node with a key (nameToKey(name)) and value (name) in the hashtable
	// Note: you need to use hashFunction(long key) to determine the slot (array index) you will insert into
	public void insert(long key, String value) {
		int i = hashFunction(key);
		Node head = array[i];
		Node newNode = new Node(key, value);
		newNode.next = head;
		array[i] = newNode;
		/*while(node != null) {
			if(node.key == key) {
				node.value = value;
				return;
			}
			node = node.next;
		}*/
	}


	/**
	 * This is the multiplication method as our hash function.
	 * @param key  It computes the hash function to find the slot (bucket) for key
	 * @return  the hash
	 */
	private int hashFunction(long key) {
		double k = (Math.sqrt(5) - 1.0) / 2.0;
		int hash = (int) (((double)m) * ((k*(double)key) % 1.0));
		return hash;
	}

	//Convert a name to a key using radix-26:
	//Use all of the letters of the alphabet as your digits.
	//Assume we only deal with lower-case letters for simplicity.
	//Then you can create the radix(base)-26 alphabet number system.
	//For example:
	// "alice" in radix-26 is 26^4*1 + 26^3*12 + 26^2*9 + 26^1*3 + 26^0*5 = 674055
	// "zoe" in radix-26 is 26^2*26 + 26^1*15 + 26^0*5 = 17971 
	public static long nameToKey(String name) {
		long key = 0;
		char[] chars = name.toCharArray();
		int j = 0;
		for (int i = chars.length-1; i >= 0; i--) {
			int k = (int)chars[i] - 96;
			key += (Math.pow(26, j) * k);
			j++;
		}
		return key;
	}


	public static void main(String[] args) {

		//For runtime computations
		long startTime, endTime;
		double duration = 0;

		//Assert nameToKey works right
		// "alice" in radix-26 is 26^4*1 + 26^3*12 + 26^2*9 + 26^1*3 + 26^0*5 = 674055
		// "zoe" in radix-26 is 26^2*26 + 26^1*15 + 26^0*5 = 17971 
		assertEquals(nameToKey("alice"), 674055);
		assertEquals(nameToKey("zoe"), 17971);


		/**
		 * _______HashTable - BIG arraysize of 2^10=1024
		 */
		System.out.println("_______HashTable - BIG arraysize of 2^10=1024");
		startTime = System.currentTimeMillis();
		HashTable ht = new HashTable((int)Math.pow(2, 10)); 
		//Assert hashFunction works right for m=2^10
		assertEquals(ht.hashFunction(1000), 34);
		try {
			File file = new File("C:\\Users\\harbe\\Documents\\names.txt");
			BufferedReader br = new BufferedReader(new FileReader(file)); 
			String line = br.readLine();
			while(line != null) {
				ht.insert(nameToKey(line), line);
				line = br.readLine();
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		endTime = System.currentTimeMillis();
		duration = ((double) (endTime - startTime));
		System.out.println("HashTable - BIG arraysize of 2^10=1024 - insertion runtime is " + duration);


		/**
		 * Verify that search returns the correct result
		 */
		startTime = System.currentTimeMillis();
		Node n = ht.search(nameToKey("aaaa"));
		assertEquals(n, null);
		endTime = System.currentTimeMillis();
		duration = ((double) (endTime - startTime));
		System.out.println("HashTable search runtime for aaaa is " + duration);

		startTime = System.currentTimeMillis();
		n = ht.search(nameToKey("alice"));
		assertEquals(n.getKey(), 674055);
		assertEquals(n.getValue(), "alice");
		endTime = System.currentTimeMillis();
		duration = ((double) (endTime - startTime));
		System.out.println("HashTable search runtime for alice is " + duration);

		startTime = System.currentTimeMillis();
		n = ht.search(nameToKey("zoe"));
		assertEquals(n.getKey(), 17971);
		assertEquals(n.getValue(), "zoe");
		endTime = System.currentTimeMillis();
		duration = ((double) (endTime - startTime));
		System.out.println("HashTable search runtime for zoe is " + duration);

		
		/**
		 * _______HashTable - MEDIUM arraysize of 2^7=128
		 */
		System.out.println("_______HashTable - MEDIUM arraysize of 2^7=128");
		startTime = System.currentTimeMillis();
		ht = new HashTable((int)Math.pow(2, 7)); 
		//Assert hashFunction works right for m=2^7
		assertEquals(ht.hashFunction(1000), 4);
		try {
			File file = new File("C:\\Users\\harbe\\Documents\\names.txt");
			BufferedReader br = new BufferedReader(new FileReader(file)); 
			String line = br.readLine();
			while(line != null) {
				ht.insert(nameToKey(line), line);
				line = br.readLine();
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		endTime = System.currentTimeMillis();
		duration = ((double) (endTime - startTime));
		System.out.println("HashTable - MEDIUM arraysize of 2^7=128 - insertion runtime is " + duration);


		/**
		 * Verify that search returns the correct result
		 */
		startTime = System.currentTimeMillis();
		n = ht.search(nameToKey("aaaa"));
		assertEquals(n, null);
		endTime = System.currentTimeMillis();
		duration = ((double) (endTime - startTime));
		System.out.println("HashTable search runtime for aaaa is " + duration);

		startTime = System.currentTimeMillis();
		n = ht.search(nameToKey("alice"));
		assertEquals(n.getKey(), 674055);
		assertEquals(n.getValue(), "alice");
		endTime = System.currentTimeMillis();
		duration = ((double) (endTime - startTime));
		System.out.println("HashTable search runtime for alice is " + duration);

		startTime = System.currentTimeMillis();
		n = ht.search(nameToKey("zoe"));
		assertEquals(n.getKey(), 17971);
		assertEquals(n.getValue(), "zoe");
		endTime = System.currentTimeMillis();
		duration = ((double) (endTime - startTime));
		System.out.println("HashTable search runtime for zoe is " + duration);

		
		/**
		 * _______HashTable - SMALL arraysize of 2^4=16
		 */
		System.out.println("_______HashTable - SMALL arraysize of 2^4=16");
		startTime = System.currentTimeMillis();
		ht = new HashTable((int)Math.pow(2, 4)); 
		//Assert hashFunction works right for m=2^4
		assertEquals(ht.hashFunction(1000), 0);
		try {
			File file = new File("C:\\Users\\harbe\\Documents\\names.txt");
			BufferedReader br = new BufferedReader(new FileReader(file)); 
			String line = br.readLine();
			while(line != null) {
				ht.insert(nameToKey(line), line);
				line = br.readLine();
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		endTime = System.currentTimeMillis();
		duration = ((double) (endTime - startTime));
		System.out.println("HashTable - SMALL arraysize of 2^4=16 - insertion runtime is " + duration);


		/**
		 * Verify that search returns the correct result
		 */
		startTime = System.currentTimeMillis();
		n = ht.search(nameToKey("aaaa"));
		assertEquals(n, null);
		endTime = System.currentTimeMillis();
		duration = ((double) (endTime - startTime));
		System.out.println("HashTable search runtime for aaaa is " + duration);

		startTime = System.currentTimeMillis();
		n = ht.search(nameToKey("alice"));
		assertEquals(n.getKey(), 674055);
		assertEquals(n.getValue(), "alice");
		endTime = System.currentTimeMillis();
		duration = ((double) (endTime - startTime));
		System.out.println("HashTable search runtime for alice is " + duration);

		startTime = System.currentTimeMillis();
		n = ht.search(nameToKey("zoe"));
		assertEquals(n.getKey(), 17971);
		assertEquals(n.getValue(), "zoe");
		endTime = System.currentTimeMillis();
		duration = ((double) (endTime - startTime));
		System.out.println("HashTable search runtime for zoe is " + duration);

	}
}

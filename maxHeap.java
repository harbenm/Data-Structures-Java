import static org.junit.Assert.*;
/**
 * Implements a max-Heap in Java with the operations max-heapify and Build-max-heap. 
 * Implements HeapSort to find and print out the sum of the smallest 2 numbers in the sorted array.
 */
public class maxHeap {

    private int[] HeapArray; 
    public int[] getHeapArray() {
		return HeapArray;
	}

	private int size; 
    private int maxsize; 
  
    private static final int FRONT = 1; 
  
    public MaxHeap(int maxsize) 
    { 
        this.maxsize = maxsize; 
        this.size = maxsize;
        HeapArray = new int[maxsize]; 
        //initialize heap array to a set of numbers, rearranged a little
        for (int i = FRONT; i < HeapArray.length; i++) {
        	HeapArray[i] = maxsize-i;
        }
    } 
  
    // Return the index of the parent of the node currently at pos 
    private int parent(int pos) 
    { 
        return (pos / 2); 
    } 
  
    // Return the index of the leftchild of the node currently at pos 
    private int leftChild(int pos) 
    { 
        return (2 * pos); 
    } 
  
    // Return the index of the rightchild of the node currently at pos 
    private int rightChild(int pos) 
    { 
        return (2 * pos) + 1; 
    } 


    //Function to heapify the node at position i, up to the position n 
    private void maxHeapify(int i, int n) 
    { 
    	int left = leftChild(i) + 1;
    	int right = rightChild(i) + 1;
    	int max = i;
    	
    	if (left < n && HeapArray[left] > HeapArray[max]) {
    		max = left;
    	}
    	if (right < n && HeapArray[right] > HeapArray[max]) {
    		max = right;
    	}
    	if (max != i) {
    		int temp = HeapArray[i];
        	HeapArray[i] = HeapArray[max];
        	HeapArray[max] = temp;
        	maxHeapify(max, n);
    	}
    	
    	
    }

    
    public void buildMaxHeap() {
    	if (HeapArray == null || HeapArray.length <= 1) {
    		return;
    	}
    	for (int i = ((HeapArray.length/2) -1); i >= 0; i++) {
    		maxHeapify(i, HeapArray.length-1);
    	}

    }

    public void sort() {
    	int n = HeapArray.length;
    	for (int i = n/2 -1; i >= 0; i--) {
    		maxHeapify(i,n);	
    		}
    	for (int i=n-1; i >=0; i--) {
    		int temp = HeapArray[0];
    		HeapArray[0] = HeapArray[i];
    		HeapArray[i] = temp;
    		maxHeapify(0, i);
    	}
    	System.out.println("The minimum sum is:");
    	System.out.println(HeapArray[FRONT] + " + " + HeapArray[FRONT+1] + " = " + (HeapArray[FRONT]+HeapArray[FRONT+1]));
    }

    
    
    // Swap two nodes of the heap at index first second
    private void swap(int first, int seconds) 
    { 
        int tmp; 
        tmp = HeapArray[first]; 
        HeapArray[first] = HeapArray[seconds]; 
        HeapArray[seconds] = tmp; 
    } 
    
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int SIZE = 30;
		MaxHeap heap = new MaxHeap(SIZE);
		heap.sort();
		int[] heapArr = heap.getHeapArray();
		assertEquals(heapArr[0], 0);
		assertEquals(heapArr[1], 1);
		assertEquals(heapArr[2], 2);
		assertEquals(heapArr[SIZE-1], SIZE-1);
		assertEquals(heapArr.length, SIZE);
	}

}

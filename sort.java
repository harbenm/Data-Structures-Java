import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import static org.junit.Assert.*;

/**
 * Template code provided by @author andreopo
 * Implements Insertion Sort, Selection Sort, Merge Sort, Heap Sort, Quick Sort, and Bucket Sort.
 * Prints the runtimes of each algorithm for input lists of sizes 100, 1000, 10000, and 100000.
 *
 */
public class Sort {

	/**
	 * Build a random array
	 * @param rand a Random object
	 * @param LENGTH The range of the integers in the array 
	 *             will be from 0 to LENGTH-1
	 * @return
	 */
	private static int[] build_random_array(Random rand, int LENGTH) {
		int[] array = new int[LENGTH];
		//set index 0 to 0 for consistency with CLRS, where sorting starts at index 1
		array[0] = 0; 
		for (int i = 1; i < LENGTH; i++) {
	        // Generate random integers in range 0 to 999 
	        int rand_int = rand.nextInt(LENGTH); 
	        array[i] = rand_int;
		}
		return array;
	}

	/**
	 * Assert the array is sorted correctly
	 * @param array_unsorted The original unsorted array
	 * @param array_sorted The sorted array
	 */
	public static void assert_array_sorted(int[] array_unsorted, int[] array_sorted) {
		int a_sum = array_unsorted[0];
		int b_sum = array_sorted[0];
		for (int i = 1; i < array_unsorted.length; i++) {
			a_sum += array_unsorted[i];
	    }
		for (int i = 1; i < array_sorted.length; i++) {
			b_sum += array_sorted[i];
			assertTrue(array_sorted[i-1] <= array_sorted[i]);
	    }
		assertEquals(a_sum, b_sum);
	}
	
	
	public static void insertionSort(int[] array) {
		for (int i = 1; i < array.length; i++) {
			int k = array[i];
			int j = i-1;
			while(j>=0 && array[j] > k) {
				array[j+1] = array[j];
				j = j-1;
			}
			array[j+1]=k;
		}
	}


	public static void selectionSort(int[] array) {
		for (int i=0; i <array.length-1; i++) {
			int min = i;
			for (int j = i+1; j<array.length; j++) {
				if(array[j] < array[min]) {
					min = j;
				}
			}
			int temp = array[min];
			array[min] = array[i];
			array[i] = temp;
		}
	}
	
	static void heapify(int array[], int x, int i) {
		int largest = i;
		int left = 2*i + 1;
		int right = 2*i +2;
		
		if(left < x && array[left] > array[largest] ) {
			largest = left;
		}
		
		if(right < x && array[right] > array[largest] ) {
			largest = right;
		}
		
		if (largest != i) 
        { 
            int swap = array[i]; 
            array[i] = array[largest]; 
            array[largest] = swap; 
            heapify(array, x, largest);
        }
    } 
	
	public static void heapSort(int[] array) {
    	for(int i = array.length/2-1; i >=0; i--) {
    		heapify(array,array.length,i);
    	}
    	for(int i = array.length-1; i>=0; i--) {
    		int temp = array[0];
    		array[0] = array[i];
    		array[i] = temp;
    		heapify(array,i,0);
    	}
	}
	
	public static int[] subLeft(int[] array) {
        int[] left = new int[array.length / 2];
        for (int i = 0; i < array.length / 2; i++) {
            left[i] = array[i];
        }
        return left;
    }

	public static int[] subRight(int[] array) {
        int size1 = array.length / 2;
        int size2 = array.length - size1;
        int[] right = new int[size2];
        for (int i = 0; i < size2; i++) {
            right[i] = array[i + size1];
        }
        return right;
    }
	
	public static void merge(int[] result, int[] left, int[] right) {
		int l = 0;
		int r = 0;
		for(int i=0; i < result.length; i++) {
			if(r >= right.length || (l < left.length && left[l] <= right[r])) {
				result[i] = left[l];
                l++;
			}
			else {
				result[i] = right[r];
                r++;
			}
		}
	}

	public static void mergeSort(int[] array) {
		if (array.length > 1) {
            int[] left = subLeft(array);
            int[] right = subRight(array);
            
            mergeSort(left);
            mergeSort(right);
            
            merge(array, left, right);
		}
	}
	
	public static void quickSort(int[] array) {
		quickSortHelper(array, 0, array.length-1);
	}
	
	public static void quickSortHelper(int[] array, int start, int finish) {
		if (start < finish) {
			int p = partition(array, start, finish);
			quickSortHelper(array, start, p-1);
			quickSortHelper(array,p+1,finish);
		}
	}
	public static int partition(int[] array, int left, int right) {
		int p = array[right];
		int i = left-1;
		for(int j = left; j < right; j++) {
			if(array[j]<=p) {
				i++;
				int temp = array[i];
				array[i] = array[j];
				array[j] = temp;
			}
		}
		int temp = array[i+1];
		array[i+1] = array[right];
		array[right] = temp;
		return i+1;
	}


	public static void bucketSort(int[] array) {
		int bucketCount = array.length/2;
		int minIntValue = 0;
		int maxIntValue = array.length - 1;
        // Create bucket array
        List<Integer>[] buckets = new List[bucketCount];
        // Associate a list with each index in the bucket array         
        for(int i = 0; i < bucketCount; i++){
            buckets[i] = new LinkedList<>();
        }
        
        
        // Assign integers from array to the proper bucket
        for (int x: array) {
        	buckets[hash(x)].add(x);
        }
        
        // sort buckets
        for(List<Integer> bucket : buckets){
            Collections.sort(bucket);
        }
        int i = 0;
        // Merge buckets to get sorted array
        for(List<Integer> bucket : buckets){
            for(int num : bucket){
                array[i++] = num;
            }
        }
	}
	
	private static int hash(int x) {
		return x/10;
	}


	public static void main(String[] args) {
		int NUM_RUNS = 3;
        // create instance of Random class 
        Random rand = new Random(); 
        
        /////////////////////////////////////////
		int LENGTH=100;
		System.out.println("_____________INPUT "+LENGTH+"_____________");
		int[] array_100 = build_random_array(rand, LENGTH);

		//For runtime computations
		long startTime, endTime;
		
		double duration = 0;
		for (int t = 0 ; t < NUM_RUNS ; t++) {
			int[] array_100_c = array_100.clone();
			startTime = System.currentTimeMillis();
			insertionSort(array_100_c);
			endTime = System.currentTimeMillis();
			duration += ((double) (endTime - startTime));
			assert_array_sorted(array_100, array_100_c);
		}
		duration = duration / (double) NUM_RUNS;
		System.out.println("InsertionSort mean runtime over " + NUM_RUNS + " runs is " + duration);
		
		
		duration = 0;
		for (int t = 0 ; t < NUM_RUNS ; t++) {
			int[] array_100_c = array_100.clone();
			startTime = System.currentTimeMillis();
			selectionSort(array_100_c);
			endTime = System.currentTimeMillis();
			duration += ((double) (endTime - startTime));
			assert_array_sorted(array_100, array_100_c);
		}
		duration = duration / (double) NUM_RUNS;
		System.out.println("SelectionSort mean runtime over " + NUM_RUNS + " runs is " + duration);

		duration = 0;
		for (int t = 0 ; t < NUM_RUNS ; t++) {
			int[] array_100_c = array_100.clone();
			startTime = System.currentTimeMillis();
			heapSort(array_100_c);
			endTime = System.currentTimeMillis();
			duration += ((double) (endTime - startTime));
			assert_array_sorted(array_100, array_100_c);
		}
		duration = duration / (double) NUM_RUNS;
		System.out.println("HeapSort mean runtime over " + NUM_RUNS + " runs is " + duration);

		duration = 0;
		for (int t = 0 ; t < NUM_RUNS ; t++) {
			int[] array_100_c = array_100.clone();
			startTime = System.currentTimeMillis();
			mergeSort(array_100_c);
			endTime = System.currentTimeMillis();
			duration += ((double) (endTime - startTime));
			assert_array_sorted(array_100, array_100_c);
		}
		duration = duration / (double) NUM_RUNS;
		System.out.println("MergeSort mean runtime over " + NUM_RUNS + " runs is " + duration);

		duration = 0;
		for (int t = 0 ; t < NUM_RUNS ; t++) {
			int[] array_100_c = array_100.clone();
			startTime = System.currentTimeMillis();
			quickSort(array_100_c);
			endTime = System.currentTimeMillis();
			duration += ((double) (endTime - startTime));
			assert_array_sorted(array_100, array_100_c);
		}
		duration = duration / (double) NUM_RUNS;
		System.out.println("QuickSort mean runtime over " + NUM_RUNS + " runs is " + duration);

		duration = 0;
		for (int t = 0 ; t < NUM_RUNS ; t++) {
			int[] array_100_c = array_100.clone();
			startTime = System.currentTimeMillis();
			bucketSort(array_100_c);
			endTime = System.currentTimeMillis();
			duration += ((double) (endTime - startTime));
			assert_array_sorted(array_100, array_100_c);
		}
		duration = duration / (double) NUM_RUNS;
		System.out.println("BucketSort mean runtime over " + NUM_RUNS + " runs is " + duration);

        /////////////////////////////////////////
		LENGTH=1000;
		System.out.println("_____________INPUT "+LENGTH+"_____________");
		int[] array_1000 = build_random_array(rand, LENGTH);

		duration = 0;
		for (int t = 0 ; t < NUM_RUNS ; t++) {
			int[] array_1000_c = array_1000.clone();
			startTime = System.currentTimeMillis();
			insertionSort(array_1000_c);
			endTime = System.currentTimeMillis();
			duration += ((double) (endTime - startTime));
		}
		duration = duration / (double) NUM_RUNS;
		System.out.println("InsertionSort mean runtime over " + NUM_RUNS + " runs is " + duration);
		
		
		duration = 0;
		for (int t = 0 ; t < NUM_RUNS ; t++) {
			int[] array_1000_c = array_1000.clone();
			startTime = System.currentTimeMillis();
			selectionSort(array_1000_c);
			endTime = System.currentTimeMillis();
			duration += ((double) (endTime - startTime));
		}
		duration = duration / (double) NUM_RUNS;
		System.out.println("SelectionSort mean runtime over " + NUM_RUNS + " runs is " + duration);

		duration = 0;
		for (int t = 0 ; t < NUM_RUNS ; t++) {
			int[] array_1000_c = array_1000.clone();
			startTime = System.currentTimeMillis();
			heapSort(array_1000_c);
			endTime = System.currentTimeMillis();
			duration += ((double) (endTime - startTime));
		}
		duration = duration / (double) NUM_RUNS;
		System.out.println("HeapSort mean runtime over " + NUM_RUNS + " runs is " + duration);

		duration = 0;
		for (int t = 0 ; t < NUM_RUNS ; t++) {
			int[] array_1000_c = array_1000.clone();
			startTime = System.currentTimeMillis();
			mergeSort(array_1000_c);
			endTime = System.currentTimeMillis();
			duration += ((double) (endTime - startTime));
		}
		duration = duration / (double) NUM_RUNS;
		System.out.println("MergeSort mean runtime over " + NUM_RUNS + " runs is " + duration);

		duration = 0;
		for (int t = 0 ; t < NUM_RUNS ; t++) {
			int[] array_1000_c = array_1000.clone();
			startTime = System.currentTimeMillis();
			quickSort(array_1000_c);
			endTime = System.currentTimeMillis();
			duration += ((double) (endTime - startTime));
		}
		duration = duration / (double) NUM_RUNS;
		System.out.println("QuickSort mean runtime over " + NUM_RUNS + " runs is " + duration);

		duration = 0;
		for (int t = 0 ; t < NUM_RUNS ; t++) {
			int[] array_1000_c = array_1000.clone();
			startTime = System.currentTimeMillis();
			bucketSort(array_1000_c);
			endTime = System.currentTimeMillis();
			duration += ((double) (endTime - startTime));
		}
		duration = duration / (double) NUM_RUNS;
		System.out.println("BucketSort mean runtime over " + NUM_RUNS + " runs is " + duration);

		
		
        /////////////////////////////////////////
		LENGTH=10000;
		System.out.println("_____________INPUT "+LENGTH+"_____________");
		int[] array_10000 = build_random_array(rand, LENGTH);

		duration = 0;
		for (int t = 0 ; t < NUM_RUNS ; t++) {
			int[] array_10000_c = array_10000.clone();
			startTime = System.currentTimeMillis();
			insertionSort(array_10000_c);
			endTime = System.currentTimeMillis();
			duration += ((double) (endTime - startTime));
		}
		duration = duration / (double) NUM_RUNS;
		System.out.println("InsertionSort mean runtime over " + NUM_RUNS + " runs is " + duration);
		
		
		duration = 0;
		for (int t = 0 ; t < NUM_RUNS ; t++) {
			int[] array_10000_c = array_10000.clone();
			startTime = System.currentTimeMillis();
			selectionSort(array_10000_c);
			endTime = System.currentTimeMillis();
			duration += ((double) (endTime - startTime));
		}
		duration = duration / (double) NUM_RUNS;
		System.out.println("SelectionSort mean runtime over " + NUM_RUNS + " runs is " + duration);

		duration = 0;
		for (int t = 0 ; t < NUM_RUNS ; t++) {
			int[] array_10000_c = array_10000.clone();
			startTime = System.currentTimeMillis();
			heapSort(array_10000_c);
			endTime = System.currentTimeMillis();
			duration += ((double) (endTime - startTime));
		}
		duration = duration / (double) NUM_RUNS;
		System.out.println("HeapSort mean runtime over " + NUM_RUNS + " runs is " + duration);

		duration = 0;
		for (int t = 0 ; t < NUM_RUNS ; t++) {
			int[] array_10000_c = array_10000.clone();
			startTime = System.currentTimeMillis();
			mergeSort(array_10000_c);
			endTime = System.currentTimeMillis();
			duration += ((double) (endTime - startTime));
		}
		duration = duration / (double) NUM_RUNS;
		System.out.println("MergeSort mean runtime over " + NUM_RUNS + " runs is " + duration);

		duration = 0;
		for (int t = 0 ; t < NUM_RUNS ; t++) {
			int[] array_10000_c = array_10000.clone();
			startTime = System.currentTimeMillis();
			quickSort(array_10000_c);
			endTime = System.currentTimeMillis();
			duration += ((double) (endTime - startTime));
		}
		duration = duration / (double) NUM_RUNS;
		System.out.println("QuickSort mean runtime over " + NUM_RUNS + " runs is " + duration);

		duration = 0;
		for (int t = 0 ; t < NUM_RUNS ; t++) {
			int[] array_10000_c = array_10000.clone();
			startTime = System.currentTimeMillis();
			bucketSort(array_10000_c);
			endTime = System.currentTimeMillis();
			duration += ((double) (endTime - startTime));
		}
		duration = duration / (double) NUM_RUNS;
		System.out.println("BucketSort mean runtime over " + NUM_RUNS + " runs is " + duration);

		
        /////////////////////////////////////////
		LENGTH=100000;
		System.out.println("_____________INPUT "+LENGTH+"_____________");
		int[] array_100000 = build_random_array(rand, LENGTH);

		duration = 0;
		for (int t = 0 ; t < NUM_RUNS ; t++) {
			int[] array_100000_c = array_100000.clone();
			startTime = System.currentTimeMillis();
			insertionSort(array_100000_c);
			endTime = System.currentTimeMillis();
			duration += ((double) (endTime - startTime));
		}
		duration = duration / (double) NUM_RUNS;
		System.out.println("InsertionSort mean runtime over " + NUM_RUNS + " runs is " + duration);
		
		
		duration = 0;
		for (int t = 0 ; t < NUM_RUNS ; t++) {
			int[] array_100000_c = array_100000.clone();
			startTime = System.currentTimeMillis();
			selectionSort(array_100000_c);
			endTime = System.currentTimeMillis();
			duration += ((double) (endTime - startTime));
		}
		duration = duration / (double) NUM_RUNS;
		System.out.println("SelectionSort mean runtime over " + NUM_RUNS + " runs is " + duration);

		duration = 0;
		for (int t = 0 ; t < NUM_RUNS ; t++) {
			int[] array_100000_c = array_100000.clone();
			startTime = System.currentTimeMillis();
			heapSort(array_100000_c);
			endTime = System.currentTimeMillis();
			duration += ((double) (endTime - startTime));
		}
		duration = duration / (double) NUM_RUNS;
		System.out.println("HeapSort mean runtime over " + NUM_RUNS + " runs is " + duration);

		duration = 0;
		for (int t = 0 ; t < NUM_RUNS ; t++) {
			int[] array_100000_c = array_100000.clone();
			startTime = System.currentTimeMillis();
			mergeSort(array_100000_c);
			endTime = System.currentTimeMillis();
			duration += ((double) (endTime - startTime));
		}
		duration = duration / (double) NUM_RUNS;
		System.out.println("MergeSort mean runtime over " + NUM_RUNS + " runs is " + duration);

		duration = 0;
		for (int t = 0 ; t < NUM_RUNS ; t++) {
			int[] array_100000_c = array_100000.clone();
			startTime = System.currentTimeMillis();
			quickSort(array_100000_c);
			endTime = System.currentTimeMillis();
			duration += ((double) (endTime - startTime));
		}
		duration = duration / (double) NUM_RUNS;
		System.out.println("QuickSort mean runtime over " + NUM_RUNS + " runs is " + duration);

		duration = 0;
		for (int t = 0 ; t < NUM_RUNS ; t++) {
			int[] array_100000_c = array_100000.clone();
			startTime = System.currentTimeMillis();
			bucketSort(array_100000_c);
			endTime = System.currentTimeMillis();
			duration += ((double) (endTime - startTime));
		}
		duration = duration / (double) NUM_RUNS;
		System.out.println("BucketSort mean runtime over " + NUM_RUNS + " runs is " + duration);

	}

}

package week36;


// This is the quick sort from the lecture. The mistakes have been fixed but they have not been highlighted (my bad)

/**
 * @author Erlend Raa Vågset
 */
public class LectureQuickSort {
	
	public static void main(String[] args) {
		
		int[] array = {3,2,4,1,5,2,3,5,2,2345,2,235,25,34,54,25,3,5,33,6,4,32,4,2,4,5,23,435,4,2,24,52,45,25,3,5,2,62,3,63,453,253};
		
		myQuickSort(array);
		
		for(int i : array)
			System.out.print(i + " ");
			
		}
	
	
	public static void myQuickSort(int[] array) {
		
		recurseQuickSort(0, array.length-1, array);
		
	}
	
	public static void recurseQuickSort(int small, int big, int[] array){
		
		
		// select pivot (this is the stupid way)
		
		int pivotIndex = small;
		int pivot = array[pivotIndex];
		
		
		// Counters
		
		int i = small;
		int j = big;
		
		
		// Moves pivot out of the way
		
		swap(pivotIndex, big, array);
		
		
		// While loop partitions the array into two parts 
		
		while(i != j) {
			
			if(array[i] < pivot) {
				i++;
				continue;
			}
			
			// The "<=" is not a good implementation:
			// This causes an array of repeated numbers to be sorted slowly
			
			if(array[j] >= pivot) {
				j--;
				continue;
			}
			
			
			swap(i, j, array);
		}
		
		// Places pivot back where it should be
		
		swap(j, big, array);
		
		
		// Recurse left side
		if((i-1)-small > 0)
			recurseQuickSort(small, i-1, array);
		
		// Recurse right side
		if((big - (j+1)) > 0)
			recurseQuickSort(j+1, big, array);
		
	}
	
	
	public static void swap(int i, int j, int[] array) {
		int a = array[i];
		array[i] = array[j];
		array[j] = a;
	}

}

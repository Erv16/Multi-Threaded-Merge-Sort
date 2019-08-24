package multithreadedHS.util;

/**
 * @author Erwin Palani
 */

import multithreadedHS.util.Results;
import multithreadedHS.util.MyLogger;
import multithreadedHS.util.MyLogger.DebugLevel;

import java.util.List;

public class MergeSort implements Strategy{

	/**
	 * The MergeSort class is a concrete strategy class which implements the Strategy interface.
	 * <p>
	 * It provides the Merge Sort implementation for the executeSort() method.
	 */
	
	public MergeSort(){

		/**
		 * The default constructor for MergeSort class
		 */

		MyLogger.writeMessage("Reached the constructor of class MergeSort.", DebugLevel.CONSTRUCTOR);
	}

	public void executeSort(List<Integer> arr){

		/**
		 * The executeSort() method uses the Merge sort algorithm for sorting the contents of the Results
		 * shared data structure.
		 * <p>
		 * This data structure is common to all the threads and it holds the data contained within each 
		 * created thread.
		 * The source url is as follows:
		 * https://stackoverflow.com/questions/34783815/java-recursive-mergesort-for-arraylists
		 * @param arr the Results data structure that needs to be sorted
		 */

		// if the list contains only one element it doesn't need to be sorted

		if(arr.size() <= 1){
			return;
		} // end of if

		// Calculating the middle position of the list

		Integer mid = arr.size()/2;

		Results left = new Results();
		Results right = new Results();

		// Inserting the elements on the left hand side of mid in the original list to the new list left

		for(int i = 0; i < mid; i++){
			left.storeNewResult(arr.remove(0));
		} // end of for block

		// Inserting the elements on the right hand side of mid in the original list to the new list right

		while(arr.size() != 0){
			right.storeNewResult(arr.remove(0));
		} // end of while block

		executeSort(left.result);
		executeSort(right.result);

		// Compares the first element in each of the lists and stores the lesser of them in the original list whilst
		// removing it from the respective list it was present in.

		while(left.result.size() != 0 && right.result.size() != 0){
			if(left.result.get(0).compareTo(right.result.get(0)) < 0){
				arr.add(left.result.remove(0));
			} // end of if block
			else{
				arr.add(right.result.remove(0));
			} // end of else block
		} // end of while block

		while(left.result.size() != 0){ 
			arr.add(left.result.remove(0));
		} // end of while block

		while(right.result.size() != 0){
			arr.add(right.result.remove(0));
		} // end of while block
	}
}
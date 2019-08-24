package multithreadedHS.util;

/**
 * @author Erwin Palani
 */

import multithreadedHS.util.MyLogger;
import multithreadedHS.util.MyLogger.DebugLevel;

import java.util.List;

public class BubbleSort implements Strategy{

	/**
	 * The BubbleSort class is a concrete strategy class which implements the Strategy interface.
	 * <p>
	 * It provides the Bubble Sort implementation for the executeSort() method.
	 */ 

	public BubbleSort(){

		/**
		 * The default constructor for class BubbleSort
		 */

		MyLogger.writeMessage("Reached the constructor of class BubbleSort.", DebugLevel.CONSTRUCTOR);
	}

	@Override
	public void executeSort(List<Integer> arr){

		/**
		 * The executeSort() method uses the bubble sort algorithm for sorting the contents of each 
		 * individual threads data structure.
		 * The source url is as follows:
		 * https://stackoverflow.com/questions/8121176/java-sort-array-list-using-bubblesort
		 * @param arr the thread's data structure that needs to be sorted
		 */

		for(int i = 0; i < arr.size(); i++){
			for(int j = 0; j < arr.size() - i - 1; j++){
				if(arr.get(j) > arr.get(j+1)){
						Integer temp = arr.get(j);
						arr.set(j, arr.get(j+1));
						arr.set(j+1,temp);
				} // end of nested if
			} // end of nested for block
		} // end of for block
	}
}
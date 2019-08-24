package multithreadedHS.util;

/**
 * @author Erwin Palani
 */

import java.util.List;

public interface Strategy{

	/**
	 * This is the Strategy interface which defines an action which is to execute one of the sorting 
	 * algorithms.
	 * The reference url is as follows:
	 * https://www.tutorialspoint.com/design_pattern/strategy_pattern
	 */

	/**
	 * executeSort() implements the sort algorithm strategy specific to the context in which it is being invoked
	 * @param arr the data structure that needs to be sorted
	 */

	public void executeSort(List<Integer> arr);
}
package multithreadedHS.util;

/**
 * @author Erwin Palani
 */

import java.util.List;

public class Context{

	/**
	 * The Context class simply uses one of the available strategies
	 */

	private Strategy strategy;
	public Context(Strategy strategyIN){

		/**
		 * The constructor is used for setting the strategy that needs to be used
		 * @param strategyIN the strategy that needs to be executed
		 */ 

		this.strategy = strategyIN;
	}

	public void executeStrategy(List<Integer> arr){

		/**
		 * The executeStrategy() method executes the required strategy
		 * @param arr the data structure on which the strategy needs to be executed
		 */

		 strategy.executeSort(arr);
	}
}
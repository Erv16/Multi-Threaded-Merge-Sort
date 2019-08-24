package multithreadedHS.driver;

/**
 * @author Erwin Palani
 */

import multithreadedHS.threads.ThreadWorker;
import multithreadedHS.util.FileProcessor;
import multithreadedHS.util.Results;
import multithreadedHS.util.Context;
import multithreadedHS.util.MergeSort;
import multithreadedHS.util.BubbleSort;
import multithreadedHS.util.MyLogger;
import multithreadedHS.util.MyLogger.DebugLevel;

import java.util.Arrays;

public class Driver{

	/**
	 * The Driver class is responsible for forwarding/invoking all the necessary required operations or methods.
	 * <p>
	 * The Driver class is responsible for validating command line arguments, creating/triggering
	 * the required threads and based on the context, executing the appropriate stratergy for sorting.
	 */

	/**
     * Returns a parsed Integer value. 
     * <p>
     * Parsing a String to an Integer value directly raises a NumberFormatException and terminates
	 * the execution of the program. To avoid this default exception from being raised, have created 
	 * the tryParseInt method that parses the String to an Integer value and returns the Integer value 
	 * everytime it reads an input from the file.
	 * <p>
   	 * The source url is as follows:
   	 * https://codereview.stackexchange.com/questions/19773/convert-string-to-integer-with-default-value
   	 * @param str the string value that needs to be parsed 
	 *            into an Integer value
	 * @return the parsed Integer value
     */

	public static Integer tryParseInt(String str){
	    	try{
	    		return Integer.parseInt(str);
	    	} // end of try block
	    	catch(NumberFormatException nfe){
	    		return null;
	    	} // end of catch block
	    }

	public static void main(String[] args){

		/**
		 * Instead of parsing the command line arguments as individual values, the program is parsing
		 * it as a line of arguments. The below validations are performed to check if the arguments
		 * passed are within certain defined ranges, if the number of arguments passed are correct
		 * and if they are valid arguments.
		 */

		// Validation for the first command line argument to check if it is in the range of 1 to 3
		int N = -1;

		try{
			N = tryParseInt(args[0]);

			if(N < 1 || N > 3){
				System.err.println("Error: Incorrect value for N. Please prvoide a value in the range of 1 to 3 and try again.");
				System.exit(1);
			} // end of try block
		}
		catch(ArrayIndexOutOfBoundsException arrE){
			System.err.println("Error: No command line arguments were passed. " + 
							   "Please pass command line arguments to proceed.");
			arrE.printStackTrace();
			System.exit(1);
		} // end of catch block

		
		String outputFile = args[args.length-2];
		Integer debugValue = tryParseInt(args[args.length-1]);

		// Validation for the number of arguments passed

		for(int i = 0; i < (N + 3); i++){
			if((args.length != (N + 3)) || args[i].equals("${arg"+i+"}")){
				System.err.println("Error: Incorrect number of input values passed. Program accepts " + (N + 3) + " values." +
					               " Try again with the correct number of input values using the following command: " +
					               "-Dargs='<Value of N> <inputFile1> <inputFile2>...<inputFileN> <outputFile> <Debug Value>");
				System.exit(1);
			} // end of if
		} // end of for

		//Validation to check if the debug value provided is within the range 0 - 4

		if(debugValue > 4 || debugValue < 0){
		    	System.err.println("Error: The debug value is out of scope. Please provide a value between the range of 0 to 4"+"\n"+
		    						"4 -> Print to stdout a confirmation that a particular thread has executed successfully. Writes the results to the output file."+"\n"+
      								"3 -> Print to stdout the content held by the Results data structure before sorting. Writes the results to the output file."+"\n"+
      								"2 -> Print to stdout everytime a constructor is called. Writes the results to the output file."+"\n"+
      								"1 -> Print to stdout updates on FileProcessor and the lines being read from the input file. Writes the results to the output file."+"\n"+
      								"0 -> No output is printed from the application to stdout. Writes the results to the output file.");
		    	System.exit(1);
		    } // end of if

		MyLogger.setDebugValue(debugValue);

		// Array of objects for FileProcessor to read from multiple input files

		FileProcessor fpArr[] = new FileProcessor[N];

		// Array of objects for Threads to read and sort values from respective input files

		ThreadWorker thrArr[] = new ThreadWorker[N];

		// Creating an instance of Results

		Results res = new Results();

		// Creating an instance of Context for performing Bubble sort on the threads data

		Context context = new Context(new BubbleSort()); 

		try{
			// for loop for instantiating file processor objects

			for(int i = 0; i < N; i++){
				fpArr[i] = new FileProcessor(args[i+1]);
			} // end of for

			// for loop for instantiating and running thread objects

			for(int j = 0; j < N; j++){
				thrArr[j] = new ThreadWorker(fpArr[j],res,context);
				thrArr[j].start();
			} // end of for

			try{

				/**
				 * Ensuring all threads complete execution before executing main thread
				 * The source url is as follows: 
				 * https://www.journaldev.com/1024/java-thread-join-example
				 */

				for(int k = 0; k < N; k++){
					thrArr[k].join();
					fpArr[k].closeFile();
				} // end of for block

			} // end of nested try block
			catch(InterruptedException ie){
				ie.printStackTrace();
			} // end of nested catch block


			if(debugValue == 3){
				MyLogger.writeMessage("The contents stored in Results data structure before sorting are as follows: ",DebugLevel.IN_RESULT);
				for(Integer i: res.result)
					MyLogger.writeMessage(Integer.toString(i),DebugLevel.IN_RESULT);
			}

			// Context instance for performing Merge sort on the Results shared data structure

			context = new Context(new MergeSort());
			context.executeStrategy(res.result);

			res.writeToFile(outputFile);

		} // end of try block 
		catch(Exception e){
			e.printStackTrace();
			System.exit(1);
		} // end of catch block	
		
	}
}
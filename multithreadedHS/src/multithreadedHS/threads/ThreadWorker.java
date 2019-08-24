package multithreadedHS.threads;

/**
 * @author Erwin Palani
 */

import multithreadedHS.util.FileProcessor;
import multithreadedHS.util.Results;
import multithreadedHS.util.Context;
import multithreadedHS.util.BubbleSort;
import multithreadedHS.util.MyLogger;
import multithreadedHS.util.MyLogger.DebugLevel;

import java.util.List;
import java.util.ArrayList;

public class ThreadWorker extends Thread{

	/**
	 * The ThreadWorker class extends the Thread class to create threads and overrides the run() method.
	 * <p>
	 * Everytime an object of this class is created and the start() mthod is invoked, the thread is executed.
	 * <p>
	 * The purpose of the class is to execute the thread and store the data read from the input file into the
	 * each thread's data structure and also in the shared insatnce of the Results data structure.
	 */

	private FileProcessor fp = null;
	public List<Integer> arrThread = null;
	private String inputStr = "";
	private Results res = null;
	private Context context = null;

	public ThreadWorker(FileProcessor fpIN, Results resIN, Context contextIN){

		/**
		 * Parameterized constructor
		 * <p>
		 * Receives a FileProcessor instance for reading input from a file and an instance of Results
		 * that is shared among all created threads to store the read data into its data structure.
		 * @param fpIN the FileProcessor instance to help reading from the respective input file
		 * @param resIN the shared Results instance
		 */

		this.fp = fpIN;
		this.arrThread = new ArrayList<Integer>();
		this.res = resIN;
		this.context = contextIN;
		MyLogger.writeMessage("Constructor of class ThreadWorker reached.",DebugLevel.CONSTRUCTOR);
	}

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

	    /**
	     * The run() method executes the thread created.
	     * <p>
	     * The run() method reads in data from the respective input file and stores it in the 
	     * reapective threads data structure. It also saves the data in the Results shared data structure.	
	     */

		public void run(){
			try{
				while((inputStr = fp.readLine()) != null){

					if(!inputStr.matches("[0-9]{4}")){
					System.err.println("The number " + inputStr + " is invalid. It should be a four digit number. Please provide a valid input and try again.");
					MyLogger.writeMessage("The number " + inputStr + " is invalid. It should be a four digit number. Please provide a valid input and try again.",DebugLevel.FILE_PROCESSOR);
					System.exit(1);
					} // end of if

					arrThread.add(tryParseInt(inputStr));
					res.storeNewResult(tryParseInt(inputStr));
				} // end of while block
				context.executeStrategy(arrThread);
				MyLogger.writeMessage("Thread has executed successfully " + Thread.currentThread().getId(),DebugLevel.THREAD_EXEC);
			} // end of try block
			catch(Exception e){
				System.err.println("Error: Thread's execution was interrupted");
				MyLogger.writeMessage("Thread " + Thread.currentThread().getId() + " execution was interrupted",DebugLevel.THREAD_EXEC);
				e.printStackTrace();
				System.exit(1);
			} // end of catch block
		}
}
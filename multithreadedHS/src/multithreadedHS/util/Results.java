package multithreadedHS.util;

/**
 * @author Erwin Joshua Palani
 */

import multithreadedHS.util.MyLogger;
import multithreadedHS.util.MyLogger.DebugLevel;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;


public class Results implements FileDisplayInterface, StdoutDisplayInterface {
	
	/**
	 * The Results class contains the implementation of the shared data structure that stores data common
	 * to all threads created. It implements the FileDisplayInterface and StdoutDisplayInterface.
	 */

	public List<Integer> result = null;

	public Results(){

		/**
		 * A non-parameterized constructor that instantiates a Results instance.
		 * It is also used for debugging.
		 * <p>
		 * Array Lists by default aren't synchronized. By making use of the Collections.synchronizedList() method
		 * we get a List that is synchronized. The methods of this list are synchronized.
		 * <p>
		 * A thread won't be able to modify the list while another thread is currently running a method from this list
		 * Lists won't be merged randomly, ie. data will be added to the list synchronously therefore making the add()
		 * method synchronized.
		 * The source url for this is as follows:
		 * https://stackoverflow.com/questions/40930861/what-is-the-use-of-collections-synchronizedlist-method-it-doesnt-seem-to-syn	
		 */

		result = Collections.synchronizedList(new ArrayList<Integer>());
		MyLogger.writeMessage("Reached the constructor of Results Class",DebugLevel.CONSTRUCTOR);
	}

	public void storeNewResult(Integer s){

		/**
		 *
		 * This method adds the string values to the Result data structure 
		 * @param s a string value passed to be added to the Results 
		 *          data structure
		 */
		synchronized(result){
			result.add(s);
		}
	}

	public void writeToFile(String outputFile){

		/**
	 	 *
	 	 * This method helps write the output to the file. 
	 	 * Write to file is implemented using BufferedWriter. 
	 	 * The source url is as follows: 
	 	 * https://stackoverflow.com/questions/6548157/how-to-write-an-arraylist-of-strings-into-a-text-file
	 	 * @param outputFile the name of the output file to be written to.        
	 	 */

		BufferedWriter output = null;

		try{
			File file = new File(outputFile);
			try{
				if(!file.exists())
					file.createNewFile();
			} // end of inner try block
			catch(IOException ioe1){
				MyLogger.writeMessage("Could not create an output file",DebugLevel.FILE_PROCESSOR);
				System.err.println("Error: Could not create an output file");
				ioe1.printStackTrace();
				System.exit(1);
			} // end of inner catch block
			output = new BufferedWriter(new FileWriter(file));
			output.write("The shared data structure contains the following sorted content: \r\n");
			for(Integer num: result){
				output.write(String.format("%04d",num)+"\r\n");
			} // end of for block
		} // end of outer try block
		catch(IOException ioe2){
			MyLogger.writeMessage("Could not write to output file",DebugLevel.FILE_PROCESSOR);
			System.err.println("Error: Could not write to output file");
			ioe2.printStackTrace();
			System.exit(1);
		} // end of catch block
		finally{
			if(output != null){
				try{
					output.close();
				} // end of try block
				catch(IOException ioe3){
					MyLogger.writeMessage("Could not close output file",DebugLevel.FILE_PROCESSOR);
					System.err.println("Error: Could not close output file");
					ioe3.printStackTrace();
					System.exit(1);
				} // end of catch block
			} // end of if
		} // end of finally block
	}

	public void writeToStdout(String str){

			/**
			 * This method accepts String as a parameter and prints the value 
			 * of the parameter directly onto the console.
			 * @param str a string value that is to be displayed directly onto 
			 *            the console.
			 */

			System.out.println(str);
	}

	public void clear(){
		result.clear();
	}

	@Override
	public String toString(){

		/**
		 * This method helps print all the elements of the Results List.
		 * @return a string containing the final output    
		 */

		System.out.println("The elements in the Result List are as follows:");
		for(Integer s: result)
			System.out.println(Integer.toString(s));
		return null;
	}
}

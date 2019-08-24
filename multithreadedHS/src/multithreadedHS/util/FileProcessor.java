package multithreadedHS.util;

/**
 * @author Erwin Joshua Palani
 */

import multithreadedHS.util.MyLogger;
import multithreadedHS.util.MyLogger.DebugLevel;

import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileProcessor {

	/**
	 * The FileProcessor class contains the implementation for reading a file line
	 * by line and closing the file after completion of reading.
	 */

	private BufferedReader br;

	public FileProcessor() {

		/**
		 * A non-parameterized FileProcessor constructor that is used for debugging.
		 */

		MyLogger.writeMessage("Reached the non-parameterized constructor of the FileProcessor class",DebugLevel.CONSTRUCTOR);
	}

	public FileProcessor(String inputFile) throws IOException{

		/**
		 * A parameterized FileProcessor constructor. It helps in validating if the input file
		 * exists or not. If it does exist, it creates an instance of BufferedReader for reading.
		 * It is also used for debugging.
		 * @param inputFile name of the input file to be read from.
		 */

		MyLogger.writeMessage("Reached the parameterized constructor of the FileProcessor class",DebugLevel.CONSTRUCTOR);
		
		try{
			File f = new File(inputFile);

			// A check to see if the input exists.

			if(!f.exists()){
				MyLogger.writeMessage("The provided file "+inputFile+" does not exist",DebugLevel.FILE_PROCESSOR);

				System.err.println("Error: The required file "+inputFile+" does not exist." + 
					               " Please provide the required file and try again");
				System.exit(1);
			} // end of if

			// Validation to check if the file is empty or not.

			if(f.exists() && f.length() == 0){

				MyLogger.writeMessage("The provided file "+inputFile+" is empty",DebugLevel.FILE_PROCESSOR);

				System.err.println("Error: The provided file "+inputFile+" is empty. " + 
								   "Please provide the required data in the file and try again.");
				System.exit(1);	
			} // end of if
			
			br = new BufferedReader(new FileReader(f));
			MyLogger.writeMessage("The provided file has been accepted",DebugLevel.FILE_PROCESSOR);
		} // end of try block
		catch(IOException e){
			System.err.println("Error: File does not exist");
			e.printStackTrace();
			System.exit(1);
		} // end of catch block
	}

	public String readLine() throws IOException{

		/**
		 * This method reads the content of a file line by line and returns the read line
		 * using BufferedReader.
		 * The source url is as follows: 
		 * https://www.journaldev.com/709/java-read-file-line-by-line
		 * @return returns the line read from the input file which is a String value
		 */

		try{

 			// Reads each line provided in the input file

			String line = null;

			while((line = br.readLine()) != null){
				return line;
			} // end of while block
		} // end of try block
		catch(IOException e){
			System.err.println("Error: Cannot fetch File");
			MyLogger.writeMessage("The file could not be fetched",DebugLevel.FILE_PROCESSOR);
			e.printStackTrace();
			System.exit(1);
		} // end of catch block
		catch(Exception e){
			System.err.println("Error: An exception occured while trying to parse the file");
			MyLogger.writeMessage("An exception occured while trying to parse the file",DebugLevel.FILE_PROCESSOR);
			e.printStackTrace();
			System.exit(1);
		} // end of catch block
		return null;
	}

	public void closeFile(){

		/**
		 * This method closes the file.
		 */

		try{
			br.close();
		} // end of try block
		catch(IOException e){
			System.err.println("Error: Failed to close File");
			MyLogger.writeMessage("Could not close file successfully",DebugLevel.FILE_PROCESSOR);
			e.printStackTrace();
			System.exit(1);
		} // end of catch block
	}
	
}

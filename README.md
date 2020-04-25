# Multi-Threaded-Merge-Sort

Following are the commands and the instructions to run ANT on the  project.
#### Note: build.xml is present in multithreadedHS/src folder.

-----------------------------------------------------------------------
#### Note: Assuming you are in the erwin_palani_assign_4/multithreadedHS folder

## Instruction to clean:

####Command: 
ant -buildfile src/build.xml clean

Description: It cleans up all the .class files that were generated when you compiled your code.

-----------------------------------------------------------------------
## Instruction to compile:

####Command: ant -buildfile src/build.xml all

Description: Compiles your code and generates .class files inside the BUILD folder.

-----------------------------------------------------------------------
## Instruction to run:
This program accepts an entire line of command line arguments rather than individual command line arguments

####Command: 
ant -buildfile src/build.xml run -Dargs='<Value of N> <inputFile1.txt> <inputFile2.txt> ... <inputFileN.txt> <outputFile.txt> <Logger-Value>' 

Example:

The last argument is the Logger value. 

ant -buildfile src/build.xml run -Dargs='3 input1.txt input2.txt input3.txt output.txt 0'



-----------------------------------------------------------------------
## Description:

The program spawns N threads and each thread reads input from its respective input file. The data read by each thread is stored in its own data structure and in a shared data structure. The thread's own data structure is sorted using the Bubble Sort algorithm while the shared Results data structure is sorted using the Merge Sort algorithm.

To implement this kind of behaviour, the Strategy pattern is used for the program's design. Depending on the context, a corresponding strategy is selected for implementation. If a thread's data structure needs to be sorted, then Bubble sort is used and if the Results data structure needs to be sorted then Merge Sort is used.

The data structure used (both for Threads and Results) is the Array List. ArrayList is chosen as the data structure to be used for this program. It has O(n) time complexity for adding/removing elements. Although Vector is synchronized, an ArrayList grows by 50% whereas a Vector doubles its size. Explicit synchronization can be provided for an ArrayList whenever required and therefore reduces the overhead caused by Vectors. An ArrayList's elements can be accessed directly using get and set methods. An ArrayList can also hold duplicate values which is a requirement for the assignment.

Now, Array Lists by default aren't synchronized, therefore the Collections.synchronizedList() is used - it returns a synchronized list backed by the specified list (which is an ArrayList). Also as the add() method is placed within a synchronized block, it guarantees that at a time only one thread will be able to access the list and will avoid random inserts from corresponding threads.


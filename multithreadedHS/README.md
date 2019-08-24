# CSX42: Assignment 4
## Name: Erwin Joshua Palani

-----------------------------------------------------------------------
-----------------------------------------------------------------------


Following are the commands and the instructions to run ANT on your project.
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

To implement this kind of behaviour, the State pattern is used for the program's design. Depending on the context, a corresponding strategy is selected for implementation. If a thread's data structure needs to be sorted, then Bubble sort is used and if the Results data structure needs to be sorted then Merge Sort is used.

The data structure used (both for Threads and Results) is the Array List. ArrayList is chosen as the data structure to be used for this program. It has O(n) time complexity for adding/removing elements. Although Vector is synchronized, an ArrayList grows by 50% whereas a Vector doubles its size. Explicit synchronization can be provided for an ArrayList whenever required and therefore reduces the overhead caused by Vectors. An ArrayList's elements can be accessed directly using get and set methods. An ArrayList can also hold duplicate values which is a requirement for the assignment.

Now, Array Lists by default aren't synchronized, therefore the Collections.synchronizedList() is used - it returns a synchronized list backed by the specified list (which is an ArrayList). Also as the add() method is placed within a synchronized block, it guarantees that at a time only one thread will be able to access the list and will avoid random inserts from corresponding threads.

Source or References used:

1. https://stackoverflow.com/questions/34783815/java-recursive-mergesort-for-arraylists

The above source has been used for implementing the Merge Sort algorithm

2. https://stackoverflow.com/questions/8121176/java-sort-array-list-using-bubblesort

The above source has been used for implementing the Bubble Sort
algorithm

3. https://www.journaldev.com/1024/java-thread-join-example

This source was used for understanding the signifance of join()

4. https://www.tutorialspoint.com/design_pattern/strategy_pattern

This source was used to understand the State Pattern structure

5. https://stackoverflow.com/questions/40930861/what-is-the-use-of-collections-synchronizedlist-method-it-doesnt-seem-to-syn

This source was used as a reference to understand how to make an array list synchronized and the significance of a synchronized block

6. https://codereview.stackexchange.com/questions/19773/convert-string-to-integer-with-default-value

Reference for converting a String to an Integer

7. https://www.journaldev.com/709/java-read-file-line-by-line

Reference for reading from a file line by line

8. https://stackoverflow.com/questions/6548157/how-to-write-an-arraylist-of-strings-into-a-text-file

Reference for writing to a File


-----------------------------------------------------------------------
### Academic Honesty statement:
-----------------------------------------------------------------------

"I have done this assignment completely on my own. I have not copied it, nor have I given my solution to anyone else. I understand that if I am involved in plagiarism or cheating I will have to sign an official form that I have cheated and that this form will be stored in my official university record. I also understand that I will receive a grade of 0 for the involved assignment for my first offense and that I will receive a grade of F for the course for any additional offense."

Date: 07/22/2019
Name: Erwin Joshua Palani 





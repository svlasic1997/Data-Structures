package edu.iastate.cs228.proj2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * 
 * @author Scott Vlasic
 * 
 * Discussion:
 * Looking at the different types of sorts implemented in this project and the time it took them to run the sorts,
 * I have come to the conclusion that Selection Sort is very inefficient for lists that are very large in size.  
 * Comparing the time it took Selection Sort to sort the lists as compared to Merge Sort or Quick Sort it is easy to 
 * see that it performs well when the lists are small.  Quick Sort and Merge Sort had similar run times for their sorts 
 * but most of the time Quick Sort was able to edge out Merge Sort.  Looking at the Big O notation for all of these sorts,
 * it makes sense why Merge Sort and Quick Sort were able to sort faster than Selection Sort.  Selection Sort has a Big O
 * time of O(n^2), which is very slow for large lists, while Merge Sort has O(n*logn) which is much faster.  Something that
 * was interesting  that I noticed was that Quick SOrt performed very well even though its Big O is O(n^2) which I assume 
 * happened because it performed its best case sorting time which is O(n*logn).  In all, I learned from these tests and this 
 * project that Selection Sort is a generally inefficient sorting method and using Quick Sort or Merge Sort can save massive 
 * amounts of time. 
 *
 */
public class EvalSorts {
	
	public static final int kNumberOfWordsToSort = 10000;

	/**
	 main is responsible only for extracting fileNames from args,
     reading the files, and constructing an instance of the this 
     class configured with the input data.
	 FileNotFoundException and FileConfigurationException exceptions 
	 should be handled in main, i.e., print out appropriate message
	 to the user.
	*/
	public static void main(String args[]) {
		char[] alphabet   = null; //ref to the Lexicon it creates. 
		String[] wordList = null;  //ref to the list of words to be sorted. 
		EvalSorts theApp  = null;  //ref to the app. 
		LexiconImpl comp  = null;  //the concrete lexicon your app uses. 
		
		// Extracts file names from args, opens and reads data
		try {
			
			alphabet = readCharacterOrdering(args[0]); // Get the character ordering of the arg
			comp = new LexiconImpl(alphabet); // Create new lexicon based on character ordering
			wordList = readWordsFile(args[1], comp); // Reads the words from the String file
			
		} catch (FileNotFoundException | FileConfigurationException e) {
			
			System.err.println("Error Detected");
		}
		
		
		/*
		 * 
		 *      Here you should add code that extracts the file names from the args array,
		 *      opens and reads the data from the files,constructs an instance of Lexicon from the character order file, 
		 *      and then create an instance of this class (EvalSorts) to act as a configured
		 *      instance of the application. After you have constructed the configured
		 *      instance, you should start it running (see below). 
		 *      
		 *      
		 *   
		 *  
		*/		
		

		//configure an instance of the app
		theApp = new EvalSorts(comp, wordList, kNumberOfWordsToSort);
		//now execute that instance
		theApp.runSorts();
		
	}

	
	private String[] words; //ref to the word lit
	private Lexicon lex;    //ef to the relevant lexicon	
	private int numWordsToSort = kNumberOfWordsToSort;
	
	/**
	 * This constructor configures an instance of EvalSorts to sort input read
	 * my main, using the character order read by main and now embedded in
	 * an instance of Lexicon
	 * @param lex the instance of lexicon to be used
	 * @param wordList the wordlist (as array of string)  to be sorted
	 * @param numWordsToSort each sort will be repeated until it has sorted
	 *                       this many words. 
	 */
	public EvalSorts(Lexicon lex, String[] wordList, int numWordsToSort) {
		this.lex = lex;
		words = wordList;
		this.numWordsToSort = numWordsToSort;
		
	}

	/**
	 * runSorts() performs the sort evaluation. 
	 * 
	 * Note: The three sorters extend a common base
	 * so they share the same interface for starting the sort and collecting statistics. 
	 * Thus, you should create instances of the sorter and save references to each in an 
	 * array of base type. This allows you to use a simple loop to run all the reports and 
	 * collect the statistics.   
	 */
	public void runSorts(){
		
		SorterWithStatistics[] sorters = new SorterWithStatistics[3];
		
		//Declares each element as a different type of sort
		sorters[0] = new SelectionSort();
		sorters[1] = new QuickSort();
		sorters[2] = new MergeSort();
		
		// Loops through each sort, sorts the wordList based on the type of sort, and returns the report of the sort
		for(int i = 0; i < sorters.length; i++){
			
			sorters[i].sort(words, lex);
			System.out.println(sorters[i].getReport());
			
		}
		
	}
	
	/**
	 * Reads the characters contained in filename and returns them as a character array.
	 * @param filename the file containing the list of characters
	 * @returns an char array representing the ordering of characters to be used 
	 *          or null if there is a FileNotFoundException.
	 */
	public static char[] readCharacterOrdering(String filename) 
			throws FileNotFoundException, FileConfigurationException {
		
		
		// Declares File and Scanner 
		File file = new File(filename);
		Scanner scan = new Scanner(file);
		
		
		ArrayList<Character> list = new ArrayList<Character>();
		
		
		// While scanner hasNextLine, input char values into String variable
		while(scan.hasNextLine()){
			
			String temp = scan.nextLine();
			
			if(temp.length() > 1 || list.contains(temp.charAt(0))){
				
				scan.close(); // Closes scanner 
				throw new FileConfigurationException();
			}
			
			list.add(temp.charAt(0));
		}
		
		char[] fileArray = new char[list.size()];
		
		for(int i = 0; i < list.size(); i++){
			
			fileArray[i] = list.get(i);
		}
		
		return fileArray; 
	}
	
	/**
	 * Reads the words from the file and returns them as a String array.
	 * @param filename file containing words
	 * @return the words contained in the file or null if there was a FileNotFoundException.
	 */
	public static String[] readWordsFile(String filename, Lexicon comp)
			throws FileNotFoundException, FileConfigurationException {
		
		// Declares File and Scanner
		File file = new File(filename);
		Scanner scan = new Scanner(file);
		
		// ArrayList of String type to hold the values as they are read in
		ArrayList<String> list = new ArrayList<String>();
		
		// While Scanner hasNextLine, add values to the ArrayList
		while(scan.hasNextLine()){
			
			String temp = scan.nextLine();
			
			if(comp.isValid(temp) == false){
				
				scan.close(); // Closes scanner
				throw new FileConfigurationException();
			}
			
			list.add(temp);
			
		}
		
		
		// Convert ArrayList into String array 
		String[] fileArray = list.toArray(new String[0]);
		
		return fileArray; 
	}

}

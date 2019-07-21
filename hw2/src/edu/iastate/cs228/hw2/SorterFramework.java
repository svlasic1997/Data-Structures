package edu.iastate.cs228.hw2;


import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.Scanner;


/**
 * An class that compares various methods of sorting.
 *
 * @author Scott Vlasic
 */
public class SorterFramework {
  /**
   * Loads data necessary to run the sorter statistics output, and runs it.
   * The only logic within this method should be that necessary to use the
   * given file names to create the {@link AlphabetComparator},
   * {@link WordList}, and sorters to use, and then using them to run the
   * sorter statistics output.
   *
   * @param args
   *   an array expected to contain two arguments:
   *    - the name of a file containing the ordering to use to compare
   *      characters
   *    - the name of a file containing words containing only characters in the
   *      other file
 * @throws FileNotFoundException 
 * @throws NullPointerException 
   */
  public static void main(String[] args) throws NullPointerException, FileNotFoundException {
    // TODO check arguments

	   	Scanner scan = new Scanner(System.in); // Scanner for the input
	    System.out.println("Alphabet File: ");
	    String alphabetFile = scan.next();
	    
	    Alphabet alphabet = new Alphabet(alphabetFile);
	    AlphabetComparator comparator = new AlphabetComparator(alphabet);
	    System.out.println("WordList File: ");
	    String wordListFile= scan.next();
	    WordList words = new WordList(wordListFile);
	    
	    Sorter[] sorters = new Sorter[] {new MergeSorter(), new QuickSorter(),new InsertionSorter()}; 
	    System.out.println("Enter number toSort: ");
	    int num = scan.nextInt();
	    scan.close();

    // TODO create appropriate values
	    
	    SorterFramework toRun = new SorterFramework(sorters,comparator,words,num);
	    toRun.run();
  }


  /**
   * The comparator to use for sorting.
   */
  private Comparator<String> comparator;

  /**
   * The words to sort.
   */
  private WordList words;

  /**
   * The array of sorters to use for sorting.
   */
  private Sorter[] sorters;

  /**
   * The total amount of words expected to be sorted by each sorter.
   */
  private int totalToSort;


  /**
   * Constructs and initializes the SorterFramework.
   *
   * @param sorters
   *   the array of sorters to use for sorting
   * @param comparator
   *   the comparator to use for sorting
   * @param words
   *   the words to sort
   * @param totalToSort
   *   the total amount of words expected to be sorted by each sorter
   * @throws NullPointerException
   *   if any of {@code sorters}, {@code comparator}, {@code words}, or
   *   elements of {@code sorters} are {@code null}
   * @throws IllegalArgumentException
   *   if {@code totalToSort} is negative
   */
  public SorterFramework(Sorter[] sorters, Comparator<String> comparator,WordList words, int totalToSort)throws NullPointerException,IllegalArgumentException{
    
	this.sorters = sorters;
    this.comparator = comparator;
    this.words = words; 
    this.totalToSort = totalToSort;
    
  }


  /**
   * Runs all sorters using
   * {@link Sorter#sortWithStatistics(WordList, Comparator, int)
   * sortWithStatistics()}, and then outputs the following information for each
   * sorter:
   *  - the name of the sorter
   *  - the length of the word list sorted each time
   *  - the total number of words sorted
   *  - the total time used to sort words
   *  - the average time to sort the word list
   *  - the number of elements sorted per second
   *  - the total number of comparisons performed
   */
  public void run(){
	
	// Loop to run through each sorting algorithm and print the outputs
	for(int i = 0; i < sorters.length; i++){
		sorters[i].sortWithStatistics(words, comparator, totalToSort);
		System.out.println("Type of sort algorithm: " + sorters[i].getName());
		System.out.println("Length of the word list: " + words.length());
		System.out.println("Total Number of words sorted: " + sorters[i].getTotalWordsSorted());
		System.out.println("The total time used to sort words(ms): " + sorters[i].getTotalSortingTime());
		System.out.println("The average time to sort the word list(ms): " + sorters[i].getTotalSortingTime() / words.length() );
		System.out.println("The number of elements sorted per second: " + sorters[i].getTotalSortingTime()/sorters[i].getTotalWordsSorted());
		System.out.println("The total number of comparisons performed: " + sorters[i].getTotalComparisons());
	}
  }
}

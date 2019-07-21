package edu.iastate.cs228.proj2;

import java.util.Comparator;

public abstract class SorterWithStatistics implements Sorter {

	private Stopwatch timer = new Stopwatch();
	
	/**
	 * Time elapsed during the sort
	 */
	private long timeElapsed;
	
	/***
	 * Default constructor
	 */
	public SorterWithStatistics() {
		// Left empty as per specifications on Piazza
	}

	/***
	 * Public interface to sortHelper that keeps track of performance
	 * statistics, including counting words sorted and timing sort instances.
	 * 
	 * @param words
	 *            input array to be sorted.
	 * @param comp
	 *            Comparator used to sort the input array.
	 */
	public void sort(String[] words, Comparator<String> comp) {
		timer.start(); // Starts timer
		sortHelper(words, comp); // Calls sortHelper on the sort
		timer.stop(); // Stops timer
		timer.getElapsedTime(); // Gets elapsed time of the sort
		
		timeElapsed = timer.getElapsedTime(); // Sets the instance variable equal to the elapsed time of the sort
	}

	/**
	 * Sorts the array words.
	 * 
	 * @param words
	 *            input array to be sorted.
	 * @param comp
	 *            Comparator used to sort the input array.
	 */
	protected abstract void sortHelper(String[] words, Comparator<String> comp);

	/**
	 * Returns number of words sorted in last sort. Throws IllegalStateException
	 * if nothing has been sorted.
	 * 
	 * @return number of words sorted in last sort.
	 */
	public int getWordsSorted() {
		// No need to implement based on clarifications from Canvas
		return 0;
	}

	/**
	 * Returns time the last sort took. Throws IllegalStateException if nothing
	 * has been sorted.
	 * 
	 * @return time last sort took.
	 */
	public long getTimeToSortWords() {
		// No need to implement based on clarifications from Canvas
		return 0;
	}

	/**
	 * Returns total words sorted by this instance.
	 * 
	 * @return total number of words sorted.
	 */
	public int getTotalWordsSorted() {
		// No need to implement based on clarifications from Canvas
		return 0;
	}

	/**
	 * Returns the total amount of time spent sorting by this instance.
	 * 
	 * @return total time spent sorting.
	 */
	public long getTotalTimeToSortWords() {
		
		return timeElapsed; // Time elapsed during the sort
	}

	/**
	 * @return a summary of statistics for the last sorting run.
	 * 
	 *         See the project description for details about what to include.
	 *         This method should NOT generate output directly.
	 */
	public String getReport() {
		
		String s = ""; // String to hold the report 
		
		// Returns the type of sort and the total time to sort the words (Converts from nanoseconds to seconds)
		s += this.getClass().getSimpleName() + ": " + this.getTotalTimeToSortWords()/(Math.pow(10.0, 9.0)) + "\n";
		
		return s;
	}
}

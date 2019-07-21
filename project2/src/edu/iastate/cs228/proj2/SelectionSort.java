package edu.iastate.cs228.proj2;

import java.util.Comparator;

public class SelectionSort extends SorterWithStatistics {
	
	//This method will be called by the base class sort() method to 
	// actually perform the sort. 
	@Override
	public void sortHelper(String[] words, Comparator<String> comp) {
	
		// Implemented using code from lecture slides with modifications
		if(words == null){
			
			throw new NullPointerException();
		}
		
		if(words.length == 0){
			
			throw new IllegalArgumentException();
		}
		
		if(words.length == 1){
			
			return;
		}
		
		for(int i = 0; i < words.length - 1; i++){
			
			String minValue = words[i];
			int minIndex = i;
			
			for(int j = i + 1; j < words.length; j++){
				
				//minValue.compareTo(words[j])
				if(comp.compare(minValue, words[j]) > 0){ 
					
					minValue = words[j];
					minIndex = j;
					
				}
				
			}
			
			if(minIndex != i){
				
				words[minIndex] = words[i];
				words[i] = minValue;
				
			}
			
		}
	}
}

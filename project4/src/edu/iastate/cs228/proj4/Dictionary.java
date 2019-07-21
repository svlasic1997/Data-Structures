package edu.iastate.cs228.proj4;

import java.util.*;
import java.io.*;

/**
 * @author Scott Vlasic
 * 
 * 
 * An application class that uses EntryTree class to process a file of
 * commands (one per line). Each command line consists of the name of
 * a public method of the EntryTree class followed by its arguments in
 * string form if the method has arguments. The name of the file is 
 * available to the main method from its String[] parameter at index 0.
 * You can assume that the command file is always in correct format. The 
 * main method creates an object of the EntryTree class with K being 
 * Character and V being String, reads each line from the command file, 
 * decodes the line by splitting into String parts, forms the corresponding 
 * arguments, and calls the public method from the EntryTree object with 
 * the arguments, and prints out the result on the console. Note that the 
 * name of a public method in the EntryTree class on each command line 
 * specifies that the public method should be called from the EntryTree 
 * object. A sample input file of commands and a sample output file are 
 * provided. 
 * 
 * The sample output file was produced by redirecting the console output
 * to a file, i.e.,
 * 
 * java Dictionary infile.txt > outfile.txt
 *  
 * 
 * NOTE that all methods of EntryTree class can be present as commands
 * except for getAll method inside the input file.
 * 
 * 
 */
public class Dictionary 
{
 public static void main(String[] args) 
 {
  // TODO
	 
	 try{
	 File f = new File(args[0]);
	 Scanner scan = new Scanner(f);
	 
	 EntryTree<Character, String> tree = new EntryTree<Character, String>();
	 
	 String s1;
	 String s2;
	 
	 while(scan.hasNext()){
		 
		 s1 = scan.next();
		 
		 if(s1.equals("showTree")){
			 
			 System.out.println("Command: " + s1 + "\n");
			 
			 System.out.println("Result from a showTree:");
			 tree.showTree();
			 System.out.println("\n");
		 }
		 
		 else if(s1.equals("add")){
			 String s = s1;
			 s1 = scan.next();
			 s2 = scan.next();
			 System.out.println("Command: " + s + " "+ s1 + " " + s2);
			 System.out.println("Result from an add: " + tree.add(toCharArray(s1), s2));
			 tree.add(toCharArray(s1), s2);
			 System.out.println("\n");
		 }
		 
		 else if(s1.equals("remove")){
			 String s = s1;
			 s1 = scan.next();
			 System.out.println("Command: " + s + " " + s1);
			 System.out.println("Result from a remove: " + tree.remove(toCharArray(s1)) + "\n");
		 }
		 
		 else if(s1.equals("search")){
			 String s = s1;
			 s1 = scan.next();
			 System.out.println("Command: " + s + " "+ s1);
			 System.out.println("Result from a search: " + tree.search(toCharArray(s1)) + "\n");
		 }
		 
		 else if (s1.equals("prefix")){
			 String result = "Result from a prefix: ";
			 String s = s1;
			 s1 = scan.next();
			 System.out.println("Command: " + s + " " + s1);
			 Character[] converted = tree.prefix(toCharArray(s1));
			 for(int i = 0; i < converted.length; i++){
				 result += converted[i];
			 }
			 System.out.println(result);
			 System.out.println("\n");
		 }
		 
		 else{
			 System.out.println("Unrecognizeable command");
		 }
	 }
	 scan.close();
	 }
	 catch(FileNotFoundException e){
		 System.out.println(e.getMessage());
	 }
	 
 }
 
 
 /**
  * Turns a given string into a Character Array
  * @param str
  * String to be converted to array
  * @return
  * Character Array
  */
 private static Character[] toCharArray(String str){
	 
	 Character[] charArray = new Character[str.length()];
	 for(int i = 0; i < charArray.length; i++){
		 
		 charArray[i] = new Character(str.charAt(i));
	 }
	 
	 return charArray;
 }
 
}

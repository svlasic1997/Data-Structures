//package edu.iastate.cs228.proj2;
//
//import static org.junit.Assert.assertArrayEquals;
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertFalse;
//import static org.junit.Assert.assertTrue;
//import static org.junit.Assert.fail;
//import static org.junit.Assert.assertNotNull;
//
//import java.io.BufferedWriter;
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.nio.file.Files;
//import java.util.List;
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//
///**
// * @author Kyle Vetsch
// * Test suite for most things in project 2.
// */
//public class Tester {
//    /**
//     * Directory where alphabet and wordslist files are located.
//     * INCLUDE THE DASH AT THE END OF THE FILE PATH!
//     */
//    private String directory;
//
//    /**
//     * Custom alphabet.
//     */
//    private char[] alphabet;
//
//    /**
//     * Custom words list.
//     */
//    private String[] wordsList;
//
//    /**
//     * Number (unused internally).
//     */
//    private static final int NUM = 15;
//
//    /**
//     * Set up test fixture.
//     */
//    @Before
//    public void setUp() {
//        // SET THIS TO THE ABSOLUTE FILE PATHS
//        directory = "/cs228/project2/";
//
//
//        /*
//
//        EXAMPLE (linux):
//        // include the last slash!
//
//        directory = "/home/user/java/coms228/project2/";
//
//        */
//
//
//        if (directory.equals("")) {
//            fail("Put the directory for the words lists!");
//        }
//
//
//        alphabet = new char[] {'x', 'b', '_', 'q'};
//        wordsList = new String[] {
//                // all possible permutations
//                "xb_q", "xbq_", "x_bq", "x_qb", "xqb_", "xq_b", "bx_q", "bxq_",
//                "b_xq", "b_qx", "bqx_", "bq_x", "_xbq", "_xqb", "_bxq", "_bqx",
//                "_qxb", "_qbx", "qxb_", "qx_b", "qbx_", "qb_x", "q_xb", "q_bx",
//                "qb_x" // duplicate entry
//        };
//    }
//
//    /**
//     * Tear down test fixture.
//     */
//    @After
//    public void tearDown() {
//        directory = null;
//        alphabet = null;
//        wordsList = null;
//    }
//
//    /*
//
//    +=================+
//    | EvalSorts tests |
//    +=================+
//
//     */
//
//    /**
//     * Test EvalSorts constructor.
//     */
//    @Test
//    public void evalSorts() {
//        final EvalSorts e = new EvalSorts(new LexiconImpl(alphabet), wordsList,
//                NUM);
//        assertNotNull(e);
//    }
//
//    /**
//     * Test functionality of main method.
//     */
//    @Test
//    public void main() {
//        // if you get an error here, correct the directory variable in setUp
//        final String[] files = new String[] {
//            directory + "10000.alphabet.txt",
//            directory + "10000.wordlist.txt"
//        };
//
//        // this should work fine and print the times
//        EvalSorts.main(files);
//
//        // expect these not to work: only one argument
//        EvalSorts.main(new String[]{});
//        EvalSorts.main(new String[] {directory + "10000.alphabet.txt"});
//    }
//
//    /**
//     * Test readCharacterOrdering method.
//     */
//    @Test
//    public void readCharacterOrdering() {
//        final char[] expected = new char[] {
//                'V', 'S', 'W', '5', 'I', 'q', '^', 's', '"', 'P', 'm', 'F', 'f',
//                '}', '%', 'D', 'X', '@', 'r', 'n', 'B', '*', 'c', 'Z', 'z', '2',
//                '_', 'R', 'Q', '\'', 'T', '|', '0', '6', 'd', '/', ';', '#',
//                'u', '9', '.', 'U', ',', '7', 'O', '-', 'K', '(', 'i', 'Y', 'o',
//                ':', 'G', '3', '8', 'H', 'h', '<', ' ', 'C', '&', ']', '>', 'g',
//                '1', '{', 'A', '=', 'J', 'v'
//        };
//
//        char[] ret = null;
//        try {
//            ret = EvalSorts
//                    .readCharacterOrdering(directory + "10.alphabet.txt");
//        } catch (FileNotFoundException ex) {
//            fail("Read character ordering was unable to file the "
//                    + "10.alphabet.txt file.");
//        } catch (FileConfigurationException ex) {
//            fail("Read character ordering marked the 10.alphabet.txt file as "
//                    + "incorrect, even though it was fine.");
//        }
//
//        assertArrayEquals(expected, ret);
//    }
//
//    /**
//     * Test readCharacterOrdering with invalid length.
//     * @throws FileConfigurationException (expected)
//     * @throws IOException if file wasn't found
//     */
//    @Test(expected = FileConfigurationException.class)
//    public void readCharacterOrderingLength()
//            throws FileConfigurationException, IOException {
//        final File tmp = File.createTempFile("tmpfile", ".tmp");
//        final BufferedWriter bw = new BufferedWriter(new FileWriter(tmp));
//
//        bw.write("A\nll\n");
//        bw.close();
//
//        try {
//            EvalSorts.readCharacterOrdering(tmp.getAbsolutePath());
//        } finally {
//            if (!tmp.delete()) {
//                fail("Unable to delete temp file. Make sure you're closing "
//                        + "your scanner when you're done reading the file! "
//                        + "Or search how to do scanning with "
//                        + "\"try-with-resources\"");
//            }
//        }
//
//    }
//
//    /**
//     * Test readCharacterOrdering with duplicate characters.
//     * @throws FileConfigurationException (expected)
//     * @throws IOException if file wasn't found
//     */
//    @Test(expected = FileConfigurationException.class)
//    public void readCharacterOrderingDuplicate()
//            throws FileConfigurationException, IOException {
//        final File tmp = File.createTempFile("tmpfile", ".tmp");
//        final BufferedWriter bw = new BufferedWriter(new FileWriter(tmp));
//
//        bw.write("A\nb\nA\n");
//        bw.close();
//
//        try {
//            EvalSorts.readCharacterOrdering(tmp.getAbsolutePath());
//        } finally {
//            if (!tmp.delete()) {
//                fail("Unable to delete temp file. Make sure you're closing "
//                        + "your scanner when you're done reading the file! "
//                        + "Or search how to do scanning with "
//                        + "\"try-with-resources\"");
//            }
//        }
//
//    }
//
//    /**
//     * Test readWordsFile method.
//     */
//    @Test
//    public void readWordsFile() {
//        char[] a = null;
//        try {
//            a = EvalSorts.readCharacterOrdering(directory + "10.alphabet.txt");
//        } catch (FileNotFoundException ex) {
//            fail("Read character ordering was unable to read the file "
//                    + "10.alphabet.txt file.");
//        } catch (FileConfigurationException ex) {
//            fail("Read character ordering marked the 10.alphabet.txt file as "
//                    + "incorrect, even though it was fine.");
//        }
//
//        final LexiconImpl l = new LexiconImpl(a);
//
//        final String[] expected = new String[] {
//                "6&BK>",
//                "SsX.X,'/i\"sHK",
//                "\'Hh6g\"",
//                "r_&\"d=gmSW|{7@:",
//                "zQ-D\"",
//                "]\"|d22{i_0rS(RCu{%u",
//                "C16*fdJ9/i(oQvQB37u*",
//                "K:h/7%dS/rh5:c2QX7cC",
//                "Z5##oHZA; *^IsQq>",
//                "##nXzc"
//        };
//
//        String[] ret = null;
//        try {
//            ret = EvalSorts.readWordsFile(directory + "10.wordlist.txt", l);
//        } catch (FileNotFoundException ex) {
//            fail("Read words file was unable to read the file "
//                    + "10.wordlist.txt file.");
//        } catch (FileConfigurationException ex) {
//            fail("Read words file marked the 10.wordlist.txt file as "
//                    + "incorrect, even though it was fine.");
//        }
//
//        assertArrayEquals(expected, ret);
//    }
//
//    /**
//     * Test readWordsFile with invalid words.
//     * @throws FileConfigurationException (expected)
//     * @throws IOException if file wasn't found
//     */
//    @Test(expected = FileConfigurationException.class)
//    public void readWordsFileInvalid() throws FileConfigurationException,
//            IOException {
//        final File tmp = File.createTempFile("tmpfile", ".tmp");
//        final BufferedWriter bw = new BufferedWriter(new FileWriter(tmp));
//        bw.write("Axy\nbay\naQx");
//        bw.close();
//
//        final LexiconImpl l = new LexiconImpl(new char[] {'A', 'x', 'y', 'b'});
//
//        try {
//            EvalSorts.readWordsFile(tmp.getAbsolutePath(), l);
//        } finally {
//            if (!tmp.delete()) {
//                fail("Unable to delete temp file. Make sure you're closing "
//                        + "your scanner when you're done reading the file! "
//                        + "Or search how to do scanning with "
//                        + "\"try-with-resources\"");
//            }
//        }
//    }
//
//    /**
//     * Test runSorts method. You should notice output to STDOUT (terminal).
//     */
//    @Test
//    public void runSorts() {
//        final EvalSorts e = new EvalSorts(new LexiconImpl(alphabet),
//                wordsList, NUM);
//        e.runSorts();
//    }
//
//    /*
//
//    +=================================+
//    | FileConfigurationException test |
//    +=================================+
//
//     */
//
//    /**
//     * Test constructor of FileConfigurationException with exception message.
//     */
//    @Test
//    public void fileConfiguratinException() {
//        final FileConfigurationException ex =
//                new FileConfigurationException("msg");
//        assertEquals("msg", ex.getMessage());
//    }
//
//    /*
//
//    +==================+
//    | LexiconImp tests |
//    +==================+
//
//     */
//
//    /**
//     * Test LexiconImpl constructor.
//     */
//    @Test
//    public void lexiconImpl() {
//        final LexiconImpl.CharacterValue[] expected =
//                new LexiconImpl.CharacterValue[] {
//                        new LexiconImpl.CharacterValue(2, '_'),
//                        new LexiconImpl.CharacterValue(1, 'b'),
//                        new LexiconImpl.CharacterValue(3, 'q'),
//                        new LexiconImpl.CharacterValue(0, 'x'),
//                };
//
//        final LexiconImpl l = new LexiconImpl(alphabet);
//
//        assertArrayEquals(expected, l.characterOrdering);
//
//        assertTrue(l.compare("_b", "xq") > 0);
//        assertTrue(l.compare("xq", "_b") < 0);
//        assertTrue(l.compare("_b", "_b") == 0);
//        assertTrue(l.compare("bqxx", "bqx") < 0);
//        assertTrue(l.compare("___", "___x") > 0);
//    }
//
//    /**
//     * Test compare method.
//     */
//    @Test
//    public void compare() {
//        final LexiconImpl l = new LexiconImpl(alphabet);
//
//        assertTrue(l.compare("_b", "xq") > 0);
//        assertTrue(l.compare("xq", "_b") < 0);
//        assertTrue(l.compare("_b", "_b") == 0);
//        assertTrue(l.compare("bqxx", "bqx") < 0);
//        assertTrue(l.compare("___", "___x") > 0);
//    }
//
//    /**
//     * Test getCharacterOrdering method.
//     */
//    @Test
//    public void getCharacterOrdering() {
//        final LexiconImpl l = new LexiconImpl(alphabet);
//
//        // NOTE: If you wrote getCharacterOrdering to return the INDEX where
//        // the element is located, I would change it to return the .value of the
//        // item at that index. It simplifies your code in the end.
//
//        assertEquals(0, l.getCharacterOrdering('x'));
//        assertEquals(1, l.getCharacterOrdering('b'));
//        assertEquals(2, l.getCharacterOrdering('_'));
//        assertEquals(3, l.getCharacterOrdering('q'));
//    }
//
//    /**
//     * Test isValid method.
//     */
//    @Test
//    public void isValid() {
//        final LexiconImpl l = new LexiconImpl(alphabet);
//
//        assertTrue(l.isValid("xb_"));
//        assertTrue(l.isValid("xb_qx_xqbb__x"));
//        assertFalse(l.isValid("xb x"));
//    }
//
//    /*
//
//    +================+
//    | MergeSort test |
//    +================+
//
//     */
//
//    /**
//     * Test MergeSort sortHelper method.
//     * @throws FileConfigurationException if file passed was incorrect
//     * @throws IOException if file wasn't found
//     */
//    @Test
//    public void mergeSortHelper() throws FileConfigurationException,
//            IOException {
//        final char[] a = EvalSorts
//                .readCharacterOrdering(directory + "10000.alphabet.txt");
//
//        final LexiconImpl l = new LexiconImpl(a);
//
//        String[] w = EvalSorts.readWordsFile(directory + "10000.wordlist.txt", l);
//
//        new MergeSort().sortHelper(w, l);
//
//        List<String> s = Files.readAllLines(
//                new File(directory + "10000.sortedlist.txt").toPath()
//        );
//
//        assertArrayEquals(s.toArray(new String[] {}), w);
//    }
//
//    /*
//
//    +================+
//    | QuickSort test |
//    +================+
//
//     */
//
//    /**
//     * Test QuickSort sortHelper method.
//     * @throws FileConfigurationException if file passed was incorrect
//     * @throws IOException if file wasn't found
//     */
//    @Test
//    public void quickSortHelper() throws FileConfigurationException,
//            IOException {
//        final char[] a = EvalSorts
//                .readCharacterOrdering(directory + "10000.alphabet.txt");
//
//        final LexiconImpl l = new LexiconImpl(a);
//
//        String[] w = EvalSorts.readWordsFile(directory + "10000.wordlist.txt", l);
//
//        new QuickSort().sortHelper(w, l);
//
//        List<String> s = Files.readAllLines(
//                new File(directory + "10000.sortedlist.txt").toPath()
//        );
//
//        assertArrayEquals(s.toArray(new String[] {}), w);
//    }
//
//    /*
//
//    +====================+
//    | SelectionSort test |
//    +====================+
//
//     */
//
//    /**
//     * Test SelectionSort sortHelper method.
//     * @throws FileConfigurationException if file passed was incorrect
//     * @throws IOException if file wasn't found
//     */
//    @Test
//    public void selectionSortHelper() throws FileConfigurationException,
//            IOException {
//        final char[] a = EvalSorts
//                .readCharacterOrdering(directory + "10000.alphabet.txt");
//
//        final LexiconImpl l = new LexiconImpl(a);
//
//        String[] w = EvalSorts.readWordsFile(directory + "10000.wordlist.txt", l);
//
//        new SelectionSort().sortHelper(w, l);
//
//        List<String> s = Files.readAllLines(
//                new File(directory + "10000.sortedlist.txt").toPath()
//        );
//
//        assertArrayEquals(s.toArray(new String[] {}), w);
//    }
//
//    /*
//
//    +============================+
//    | SorterWithStatistics tests |
//    +============================+
//
//     */
//
//    /**
//     * Test sort method.
//     * @throws FileConfigurationException if file passed wasn't correct
//     * @throws FileNotFoundException if file wasn't found
//     */
//    @Test
//    public void sort() throws FileConfigurationException,
//            FileNotFoundException {
//        final char[] a = EvalSorts
//                .readCharacterOrdering(directory + "10000.alphabet.txt");
//
//        final LexiconImpl l = new LexiconImpl(a);
//
//        String[] w = EvalSorts.readWordsFile(directory + "10000.wordlist.txt", l);
//
//        final SorterWithStatistics[] s = new SorterWithStatistics[] {
//                new MergeSort()
//        };
//
//        s[0].sort(w, l);
//
//        assertTrue(s[0].getTotalTimeToSortWords() > 0);
//
//        assertTrue(s[0].getReport().length() > 0);
//        assertTrue(s[0].getReport().contains("MergeSort"));
//    }
//}



package edu.iastate.cs228.proj2;

import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.runners.MethodSorters;
import org.junit.FixMethodOrder;
import org.junit.Rule;

/**
 * @author Cory Smith
 * 
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Tester {
	
	@Rule
	public ExpectedException ex = ExpectedException.none();
	
	@Test
	public void test01_LexiconImplConstructor() {
		char[] chars = new char[] {'q', 'z', 'a'};
		LexiconImpl lex = new LexiconImpl(chars);
		assertEquals(3, lex.characterOrdering.length);
		
		LexiconImpl.CharacterValue[] expected = new LexiconImpl.CharacterValue[] 
				{new LexiconImpl.CharacterValue(2, 'a'), new LexiconImpl.CharacterValue(0, 'q'), new LexiconImpl.CharacterValue(1, 'z')};
		// I can't seem to get assertArrayEquals to work here, so I'm using a for loop instead
		for (int i = 0; i < 3; i++) {
			assertEquals("Error at index: " + i, expected[i].character, lex.characterOrdering[i].character);
			assertEquals("Error at index: " + i, expected[i].value, lex.characterOrdering[i].value);
		}
	}
	
	@Test
	public void test02_LexiconImplCompare() {
		char[] chars = new char[] {'q', 'z', 'a'};
		LexiconImpl lex = new LexiconImpl(chars);
		assertEquals(0, lex.compare("qza", "qza"));
		assertEquals(-1, lex.compare("qza", "zqa"));
		assertEquals(1, lex.compare("qaz", "qza"));
	}
	
	@Test
	public void test03_LexiconImplGetCharacterOrdering() {
		char[] chars = new char[] {'q', 'z', 'a'};
		LexiconImpl lex = new LexiconImpl(chars);
		assertEquals(-1, lex.getCharacterOrdering('.'));
		assertEquals(0, lex.getCharacterOrdering('q'));
		assertEquals(1, lex.getCharacterOrdering('z'));
		assertEquals(2, lex.getCharacterOrdering('a'));
	}
	
	@Test
	public void test04_LexiconImplIsValid() {
		char[] chars = new char[] {'q', 'z', 'a'};
		LexiconImpl lex = new LexiconImpl(chars);
		assertTrue(lex.isValid("aqzzaqzaqzqzaqzaqz"));
		assertFalse(lex.isValid("saazqazqazq"));
		assertFalse(lex.isValid("aazqazqazqs"));
	}
	
	@Test
	// I'll sort these in reverse alphabetical order just for convenience
	public void test05_SelectionSort() {
		char[] chars = "ZYXWVUTSRQPONMLKJIHGFEDCBA".toCharArray();
		String[] words = new String[] {"G", "A", "Q", "W", "X", "B", "F", "A"};
		String[] expected = new String[] {"X", "W", "Q", "G", "F", "B", "A", "A"};
		LexiconImpl lex = new LexiconImpl(chars);
		new SelectionSort().sortHelper(words, lex);
		assertArrayEquals(expected, words);
	}
	
	@Test
	// I'll sort these in reverse alphabetical order just for convenience
	public void test06_QuickSort() {
		char[] chars = "ZYXWVUTSRQPONMLKJIHGFEDCBA".toCharArray();
		String[] words = new String[] {"G", "A", "Q", "W", "X", "B", "F", "A"};
		String[] expected = new String[] {"X", "W", "Q", "G", "F", "B", "A", "A"};
		LexiconImpl lex = new LexiconImpl(chars);
		new QuickSort().sortHelper(words, lex);
		assertArrayEquals(expected, words);
	}
	
	@Test
	// I'll sort these in reverse alphabetical order just for convenience
	public void test07_MergeSort() {
		char[] chars = "ZYXWVUTSRQPONMLKJIHGFEDCBA".toCharArray();
		String[] words = new String[] {"G", "A", "Q", "W", "X", "B", "F", "A"};
		String[] expected = new String[] {"X", "W", "Q", "G", "F", "B", "A", "A"};
		LexiconImpl lex = new LexiconImpl(chars);
		new MergeSort().sortHelper(words, lex);
		assertArrayEquals(expected, words);
	}
	
	@Test
	// This is just making sure that your sort actually starts and stops the timer properly, since the actual sorting was tested earlier
	public void test08_SorterWithStatisticsSort() {
		char[] chars = "ZYXWVUTSRQPONMLKJIHGFEDCBA".toCharArray();
		LexiconImpl lex = new LexiconImpl(chars);
		String[] words = new String[] {"G", "A", "Q", "W", "X", "B", "F", "A"};
		SorterWithStatistics sorter = new MergeSort();
		sorter.sort(words, lex);
		assertTrue(sorter.getTotalTimeToSortWords() > 0);
	}
	
	@Test
	// This makes sure that your report has the right stuff in it
	public void test09_SorterWithStatisticsGetReport() {
		char[] chars = "ZYXWVUTSRQPONMLKJIHGFEDCBA".toCharArray();
		LexiconImpl lex = new LexiconImpl(chars);
		String[] words = new String[] {"G", "A", "Q", "W", "X", "B", "F", "A"};
		SorterWithStatistics sorter = new MergeSort();
		sorter.sort(words, lex);
		assertEquals("MergeSort: ", sorter.getReport().substring(0, 11));
		assertTrue(Double.parseDouble(sorter.getReport().substring(11)) > 0);
		
		sorter = new QuickSort();
		sorter.sort(words, lex);
		assertEquals("QuickSort: ", sorter.getReport().substring(0, 11));
		assertTrue(Double.parseDouble(sorter.getReport().substring(11)) > 0);
		
		sorter = new SelectionSort();
		sorter.sort(words, lex);
		assertEquals("SelectionSort: ", sorter.getReport().substring(0, 15));
		assertTrue(Double.parseDouble(sorter.getReport().substring(15)) > 0);
	}
	
	@Test
	public void test10_EvalSortsReadCharacterOrderingEx() throws FileNotFoundException {
		ex.expect(FileNotFoundException.class);
		try {
			EvalSorts.readCharacterOrdering("VeryFakeFileName");
		} catch (FileConfigurationException e) {
			fail("You shouldn't be getting this exception here");
		}
	}
	
	@Test
	public void test11_EvalSortsReadWordsFileEx() throws FileNotFoundException {
		ex.expect(FileNotFoundException.class);
		LexiconImpl lex = new LexiconImpl(new char[] {'A'});
		try {
			EvalSorts.readWordsFile("VeryFakeFileName", lex);
		} catch (FileConfigurationException e) {
			fail("You shouldn't be getting this exception here");
		}
	}
}
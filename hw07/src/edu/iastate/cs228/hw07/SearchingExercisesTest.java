package edu.iastate.cs228.hw07;

import edu.iastate.cs228.hw07.SearchingExercises;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * @author  Jamie Sampson
 */

public class SearchingExercisesTest {

    @Test
    public void findMinInterval_given_example() {
        final Integer[] sorted = new Integer[] {5, 8, 10, 13, 15, 20, 22, 26};
        final ArrayList<Integer> values = new ArrayList<>();

        values.add(8);
        values.add(2);
        values.add(9);
        values.add(17);

        final Integer[] ret = SearchingExercises
                .findMinInterval(sorted, values);

        assertArrayEquals(new Integer[]{-1, 5}, ret);
    }

    @Test
    public void findMinInterval_min_oneValue(){
        final Integer[] sorted = new Integer[] {8, 10, 13};
        final ArrayList<Integer> values = new ArrayList<>();

        values.add(8);

        final Integer[] ret = SearchingExercises
                .findMinInterval(sorted, values);

        assertArrayEquals(new Integer[]{0, 0}, ret);
    }

    @Test
    public void findMinInterval_max_oneValue(){
        final Integer[] sorted = new Integer[] {8, 10, 13};
        final ArrayList<Integer> values = new ArrayList<>();

        values.add(13);

        final Integer[] ret = SearchingExercises
                .findMinInterval(sorted, values);

        assertArrayEquals(new Integer[]{2, 2}, ret);
    }

    @Test
    public void findMinInterval_min_multiValues(){
        final Integer[] sorted = new Integer[] {8, 10, 13};
        final ArrayList<Integer> values = new ArrayList<>();

        values.add(8);
        values.add(10);

        final Integer[] ret = SearchingExercises
                .findMinInterval(sorted, values);

        assertArrayEquals(new Integer[]{0, 1}, ret);
    }

    @Test
    public void findMinInterval_max_multiValues(){
        final Integer[] sorted = new Integer[] {8, 10, 13};
        final ArrayList<Integer> values = new ArrayList<>();

        values.add(10);
        values.add(13);

        final Integer[] ret = SearchingExercises
                .findMinInterval(sorted, values);

        assertArrayEquals(new Integer[]{1, 2}, ret);
    }

    @Test
    public void findMinInterval_multiValues_neg(){
        final Integer[] sorted = new Integer[] {8, 10, 13};
        final ArrayList<Integer> values = new ArrayList<>();

        values.add(8);
        values.add(10);
        values.add(0);

        final Integer[] ret = SearchingExercises
                .findMinInterval(sorted, values);

        assertArrayEquals(new Integer[]{-1, 1}, ret);
    }

    @Test
    public void findMinInterval_multiValues_length(){
        final Integer[] sorted = new Integer[] {8, 10, 13};
        final ArrayList<Integer> values = new ArrayList<>();

        values.add(8);
        values.add(19);

        final Integer[] ret = SearchingExercises
                .findMinInterval(sorted, values);

        assertArrayEquals(new Integer[]{0, 3}, ret);
    }

    @Test
    public void findMinInterval_multiValues_neg_length(){
        final Integer[] sorted = new Integer[] {8, 10, 13};
        final ArrayList<Integer> values = new ArrayList<>();

        values.add(2);
        values.add(20);

        final Integer[] ret = SearchingExercises
                .findMinInterval(sorted, values);

        assertArrayEquals(new Integer[]{-1, 3}, ret);
    }

    @Test
    public void findMinInterval_multiValues_middle(){
        final Integer[] sorted = new Integer[] {8, 10, 13};
        final ArrayList<Integer> values = new ArrayList<>();

        values.add(11);
        values.add(12);

        final Integer[] ret = SearchingExercises
                .findMinInterval(sorted, values);

        assertArrayEquals(new Integer[]{1, 2}, ret);
    }

    @Test
    public void findMinInterval_multiValues_mid_oneValue(){
        final Integer[] sorted = new Integer[] {3, 9, 11};
        final ArrayList<Integer> values = new ArrayList<>();

        values.add(10);

        final Integer[] ret = SearchingExercises
                .findMinInterval(sorted, values);

        assertArrayEquals(new Integer[]{1, 2}, ret);
    }

    @Test
    public void findMinInterval_multiValues_mix_onevValue(){
        final Integer[] sorted = new Integer[] {8, 10, 13};
        final ArrayList<Integer> values = new ArrayList<>();

        values.add(2);

        final Integer[] ret = SearchingExercises
                .findMinInterval(sorted, values);

        assertArrayEquals(new Integer[]{-1, 0}, ret);
    }

    @Test
    public void findMinInterval_largeDataSets(){
        final Integer[] sorted = new Integer[] {5, 8, 10, 13, 23, 45};
        final ArrayList<Integer> values = new ArrayList<>();

        values.add(15);
        values.add(4);
        values.add(32);
        values.add(8);
        values.add(13);

        final Integer[] ret = SearchingExercises
                .findMinInterval(sorted, values);

        assertArrayEquals(new Integer[]{-1, 5}, ret);
    }

    @Test
    public void findMinInterval_largeDataSets_length(){
        final Integer[] sorted = new Integer[] {5, 8, 10, 13, 23, 45};
        final ArrayList<Integer> values = new ArrayList<>();

        values.add(15);
        values.add(4);
        values.add(32);
        values.add(8);
        values.add(50);
        values.add(13);

        final Integer[] ret = SearchingExercises
                .findMinInterval(sorted, values);

        assertArrayEquals(new Integer[]{-1, 6}, ret);
    }

    @Test
    public void findMinInterval_negativeNumbers(){
        final Integer[] sorted = new Integer[] {-8, -5, 10, 13, 23, 45};
        final ArrayList<Integer> values = new ArrayList<>();

        values.add(15);
        values.add(-3);
        values.add(32);
        values.add(8);
        values.add(50);
        values.add(13);

        final Integer[] ret = SearchingExercises
                .findMinInterval(sorted, values);

        assertArrayEquals(new Integer[]{1, 6}, ret);
    }

    @Test
    public void findMinInterval_largeDataSets_part2(){
        final Integer[] sorted = new Integer[] {5, 8, 10, 13, 23, 45};
        final ArrayList<Integer> values = new ArrayList<>();

        values.add(15);
        values.add(12);
        values.add(21);
        values.add(11);
        values.add(13);

        final Integer[] ret = SearchingExercises
                .findMinInterval(sorted, values);

        assertArrayEquals(new Integer[]{2, 4}, ret);
    }

    //Adapted from Kyle Vetsch
    @Test
    public void find2D() {
        final Integer[][] arr = new Integer[][]{
                {1, 4, 55, 88},
                {7, 15, 61, 91},
                {14, 89, 90, 99},
        };
        assertTrue(SearchingExercises.find2D(arr, 61));
        assertFalse(SearchingExercises.find2D(arr, 95));

        // corners
        assertTrue(SearchingExercises.find2D(arr, 1));
        assertTrue(SearchingExercises.find2D(arr, 88));
        assertTrue(SearchingExercises.find2D(arr, 14));
        assertTrue(SearchingExercises.find2D(arr, 99));

        // checkas all values
        assertTrue(SearchingExercises.find2D(arr, 4));
        assertTrue(SearchingExercises.find2D(arr, 55));
        assertTrue(SearchingExercises.find2D(arr, 7));
        assertTrue(SearchingExercises.find2D(arr, 15));
        assertTrue(SearchingExercises.find2D(arr, 91));
        assertTrue(SearchingExercises.find2D(arr, 89));
        assertTrue(SearchingExercises.find2D(arr, 90));
    }
    @Test
    public void find2D_singleValue() {
        final Integer[][] arr = new Integer[][]{
                {42}
        };

        assertTrue(SearchingExercises.find2D(arr, 42));
        assertFalse(SearchingExercises.find2D(arr, 19));
    }
}
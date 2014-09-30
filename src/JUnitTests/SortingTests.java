package JUnitTests;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import Sorting.AbstractSorter;
import Sorting.MergeSorter;
import Sorting.QuickSorter;


public class SortingTests
{
	public static final int LARGE_ELEMENT_COUNT = 50000;
	public static final int MEDIUM_ELEMENT_COUNT = 1000;
	public static final int SMALL_ELEMENT_COUNT = 10;
	private static final Random random = new Random();


	Integer[] zeroToNineUnsorted;
	Integer[] zeroToNine;
	Integer[] random_large_unsorted;
	Integer[] random_large_sorted;
	Integer[] random_medium_unsorted;
	Integer[] random_medium_sorted;
	Integer[] random_small_unsorted;
	Integer[] random_small_sorted;
	

	
	private static void fillWithRandom( Integer[] arr )
	{
		for( int i = 0; i < arr.length; i++ )
		{
			arr[i] = new Integer( random.nextInt() );
		}
	}
	
	@Before
	public void setup()
	{
		zeroToNineUnsorted = new Integer[] { 5, 2, 3, 1, 0, 9, 8, 4, 7, 6 };
		zeroToNine = Arrays.copyOf( zeroToNineUnsorted, zeroToNineUnsorted.length );
		Arrays.sort( zeroToNine );
		
		random_large_unsorted = new Integer[LARGE_ELEMENT_COUNT];
		random_medium_unsorted = new Integer[MEDIUM_ELEMENT_COUNT];
		random_small_unsorted = new Integer[SMALL_ELEMENT_COUNT];
		
		//fill random unsorted arrays with data
		fillWithRandom( random_large_unsorted );
		fillWithRandom( random_medium_unsorted );
		fillWithRandom( random_small_unsorted );
		
		//copy random unsorted arrays
		random_large_sorted = Arrays.copyOf( random_large_unsorted, random_large_unsorted.length );
		random_medium_sorted = Arrays.copyOf( random_medium_unsorted, random_medium_unsorted.length );
		random_small_sorted = Arrays.copyOf( random_small_unsorted, random_small_unsorted.length );
		
		//sort random unsorted arrays with well-tested library sorting method
		Arrays.sort( random_large_sorted );
		Arrays.sort( random_medium_sorted );
		Arrays.sort( random_small_sorted );
	}
	
	
	@Test
	public void testGenericBuildFromInteger()
	{
		Integer[] unsorted = new Integer[10];
		for( int i = 0; i < 10; i++ )
		{
			unsorted[i] = new Integer( 10 - i );
		}
		Integer[] sorted = Arrays.copyOf( unsorted, unsorted.length );
		Arrays.sort( sorted );
		AbstractSorter<Integer> quick = AbstractSorter.buildFrom( unsorted, QuickSorter.class );
		
		verifySorting( quick, unsorted, sorted );
	}

	private <C extends Comparable<? super C>> void verifySorting( AbstractSorter<C> sorter, C[] unsorted, C[] sorted )
	{
		assertEquals( Arrays.toString( unsorted ), sorter.getUnsortedString() );
		assertEquals( Arrays.toString( unsorted ), sorter.getSortedString() );
		sorter.sort();
		assertEquals( Arrays.toString( unsorted ), sorter.getUnsortedString() );
		assertEquals( Arrays.toString( sorted ), sorter.getSortedString() );
	}

	
	
	@Test
	public void quickSortSmallUnsorted()
	{
		AbstractSorter<Integer> quick = AbstractSorter.buildFrom( random_small_unsorted , QuickSorter.class );
		verifySorting( quick, random_small_unsorted, random_small_sorted );
	}
	
	@Test
	public void quickSortSmallSorted()
	{
		AbstractSorter<Integer> quick = AbstractSorter.buildFrom( random_small_sorted , QuickSorter.class );
		verifySorting( quick, random_small_sorted, random_small_sorted );
	}

	@Test
	public void quickSortMediumUnsorted()
	{
		AbstractSorter<Integer> quick = AbstractSorter.buildFrom( random_medium_unsorted , QuickSorter.class );
		verifySorting( quick, random_medium_unsorted, random_medium_sorted );
	}
	
	@Test
	public void quickSortMediumSorted()
	{
		AbstractSorter<Integer> quick = AbstractSorter.buildFrom( random_medium_sorted , QuickSorter.class );
		verifySorting( quick, random_medium_sorted, random_medium_sorted );
	}

	@Test
	public void quickSortLargeUnsorted()
	{
		AbstractSorter<Integer> quick = AbstractSorter.buildFrom( random_large_unsorted , QuickSorter.class );
		verifySorting( quick, random_large_unsorted, random_large_sorted );
	}
	
	@Test
	public void quickSortLargeSorted()
	{
		AbstractSorter<Integer> quick = AbstractSorter.buildFrom( random_large_sorted , QuickSorter.class );
		verifySorting( quick, random_large_sorted, random_large_sorted );
	}


	
	@Test
	public void mergeSortBasic()
	{
		AbstractSorter<Integer> merge = AbstractSorter.buildFrom( zeroToNineUnsorted , MergeSorter.class );
		verifySorting( merge, zeroToNineUnsorted, zeroToNine  );
	}
	
	@Test
	public void mergeSortSmallUnsorted()
	{
		AbstractSorter<Integer> merge = AbstractSorter.buildFrom( random_small_unsorted , MergeSorter.class );
		verifySorting( merge, random_small_unsorted, random_small_sorted );
	}
	
	@Test
	public void mergeSortSmallSorted()
	{
		AbstractSorter<Integer> merge = AbstractSorter.buildFrom( random_small_sorted , MergeSorter.class );
		verifySorting( merge, random_small_sorted, random_small_sorted );
	}

	@Test
	public void mergeSortMediumUnsorted()
	{
		AbstractSorter<Integer> merge = AbstractSorter.buildFrom( random_medium_unsorted , MergeSorter.class );
		verifySorting( merge, random_medium_unsorted, random_medium_sorted );
	}
	
	@Test
	public void mergeSortMediumSorted()
	{
		AbstractSorter<Integer> merge = AbstractSorter.buildFrom( random_medium_sorted , MergeSorter.class );
		verifySorting( merge, random_medium_sorted, random_medium_sorted );
	}

	@Test
	public void mergeSortLargeUnsorted()
	{
		AbstractSorter<Integer> merge = AbstractSorter.buildFrom( random_large_unsorted , MergeSorter.class );
		verifySorting( merge, random_large_unsorted, random_large_sorted );
	}
	
	@Test
	public void mergeSortLargeSorted()
	{
		AbstractSorter<Integer> merge = AbstractSorter.buildFrom( random_large_sorted , MergeSorter.class );
		verifySorting( merge, random_large_sorted, random_large_sorted );
	}

}

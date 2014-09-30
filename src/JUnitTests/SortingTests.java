package JUnitTests;
import static org.junit.Assert.assertEquals;
import AbstractSorter;
import QuickSorter;

import java.util.Arrays;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;


public class SortingTests
{
	public static final int LARGE_ELEMENT_COUNT = 100000;
	public static final int MEDIUM_ELEMENT_COUNT = 1000;
	public static final int SMALL_ELEMENT_COUNT = 10;
	private static final Random random = new Random();


	int[] random_large_unsorted;
	int[] random_large_sorted;
	int[] random_medium_unsorted;
	int[] random_medium_sorted;
	int[] random_small_unsorted;
	int[] random_small_sorted;
	

	
	private static void fillWithRandom( int[] arr )
	{
		for( int i = 0; i < arr.length; i++ )
		{
			arr[i] = random.nextInt();
		}
	}
	
	@Before
	public void setup()
	{
		random_large_unsorted = new int[LARGE_ELEMENT_COUNT];
		random_medium_unsorted = new int[MEDIUM_ELEMENT_COUNT];
		random_small_unsorted = new int[SMALL_ELEMENT_COUNT];
		
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
	public void quickSortSmallUnsorted()
	{
		AbstractSorter<Integer> quick = AbstractSorter.buildFrom( random_small_unsorted , QuickSorter.class );
		assertEquals( Arrays.toString( random_small_unsorted ), quick.getUnsortedString() );
		assertEquals( Arrays.toString( random_small_unsorted ), quick.getSortedString() );
		quick.sort();
		assertEquals( Arrays.toString( random_small_unsorted ), quick.getUnsortedString() );
		assertEquals( Arrays.toString( random_small_sorted ), quick.getSortedString() );
	}
	
	@Test
	public void quickSortSmallSorted()
	{
		AbstractSorter<Integer> quick = AbstractSorter.buildFrom( random_small_sorted , QuickSorter.class );
		assertEquals( Arrays.toString( random_small_sorted ), quick.getUnsortedString() );
		assertEquals( Arrays.toString( random_small_sorted ), quick.getSortedString() );
		quick.sort();
		assertEquals( Arrays.toString( random_small_sorted ), quick.getUnsortedString() );
		assertEquals( Arrays.toString( random_small_sorted ), quick.getSortedString() );
	}

	@Test
	public void quickSortMediumUnsorted()
	{
		AbstractSorter<Integer> quick = AbstractSorter.buildFrom( random_medium_unsorted , QuickSorter.class );
		assertEquals( Arrays.toString( random_medium_unsorted ), quick.getUnsortedString() );
		assertEquals( Arrays.toString( random_medium_unsorted ), quick.getSortedString() );
		quick.sort();
		assertEquals( Arrays.toString( random_medium_unsorted ), quick.getUnsortedString() );
		assertEquals( Arrays.toString( random_medium_sorted ), quick.getSortedString() );
	}
	
	@Test
	public void quickSortMediumSorted()
	{
		AbstractSorter<Integer> quick = AbstractSorter.buildFrom( random_medium_sorted , QuickSorter.class );
		assertEquals( Arrays.toString( random_medium_sorted ), quick.getUnsortedString() );
		assertEquals( Arrays.toString( random_medium_sorted ), quick.getSortedString() );
		quick.sort();
		assertEquals( Arrays.toString( random_medium_sorted ), quick.getUnsortedString() );
		assertEquals( Arrays.toString( random_medium_sorted ), quick.getSortedString() );
	}

	@Test
	public void quickSortLargeUnsorted()
	{
		AbstractSorter<Integer> quick = AbstractSorter.buildFrom( random_large_unsorted , QuickSorter.class );
		assertEquals( Arrays.toString( random_large_unsorted ), quick.getUnsortedString() );
		assertEquals( Arrays.toString( random_large_unsorted ), quick.getSortedString() );
		quick.sort();
		assertEquals( Arrays.toString( random_large_unsorted ), quick.getUnsortedString() );
		assertEquals( Arrays.toString( random_large_sorted ), quick.getSortedString() );
	}
	
	@Test
	public void quickSortLargeSorted()
	{
		AbstractSorter<Integer> quick = AbstractSorter.buildFrom( random_large_sorted , QuickSorter.class );
		assertEquals( Arrays.toString( random_large_sorted ), quick.getUnsortedString() );
		assertEquals( Arrays.toString( random_large_sorted ), quick.getSortedString() );
		quick.sort();
		assertEquals( Arrays.toString( random_large_sorted ), quick.getUnsortedString() );
		assertEquals( Arrays.toString( random_large_sorted ), quick.getSortedString() );
	}

}

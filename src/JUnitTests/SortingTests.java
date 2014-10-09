package JUnitTests;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import Sorting.AbstractSorter;
import Sorting.BubbleSorter;
import Sorting.CocktailShakerSorter;
import Sorting.GnomeSorter;
import Sorting.HeapSorter;
import Sorting.InsertionSorter;
import Sorting.MergeSorter;
import Sorting.QuickSorter;
import Sorting.SelectionSorter;


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
	Integer[] shuffled_large_unsorted;
	Integer[] shuffled_large_sorted;
	Integer[] shuffled_medium_unsorted;
	Integer[] shuffled_medium_sorted;
	Integer[] shuffled_small_unsorted;
	Integer[] shuffled_small_sorted;
	

	
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
		
		//initialize shuffle arrays
		shuffled_large_sorted = new Integer[LARGE_ELEMENT_COUNT];
		shuffled_medium_sorted = new Integer[MEDIUM_ELEMENT_COUNT];
		shuffled_small_sorted = new Integer[SMALL_ELEMENT_COUNT];

		//fill shuffled arrays with ascending values
		int i;
		for( i = 0; i < shuffled_small_sorted.length; ++i )
		{
			shuffled_large_sorted[i] = i;
			shuffled_medium_sorted[i] = i;
			shuffled_small_sorted[i] = i;
		}
		for( ; i < shuffled_medium_sorted.length; ++i )
		{
			shuffled_large_sorted[i] = i;
			shuffled_medium_sorted[i] = i;
		}
		for( ; i < shuffled_large_sorted.length; ++i )
		{
			shuffled_large_sorted[i] = i;
		}

		//shuffle the arrays to create unsorted copies
		shuffled_large_unsorted = shuffle( shuffled_large_sorted );
		shuffled_medium_unsorted = shuffle( shuffled_medium_sorted );
		shuffled_small_unsorted = shuffle( shuffled_small_sorted );
		
		//copy random unsorted arrays
		random_large_sorted = Arrays.copyOf( random_large_unsorted, random_large_unsorted.length );
		random_medium_sorted = Arrays.copyOf( random_medium_unsorted, random_medium_unsorted.length );
		random_small_sorted = Arrays.copyOf( random_small_unsorted, random_small_unsorted.length );
		
		//sort random unsorted arrays with well-tested library sorting method
		Arrays.sort( random_large_sorted );
		Arrays.sort( random_medium_sorted );
		Arrays.sort( random_small_sorted );
	}
	
	
	private static <T extends Comparable<? super T>> T[] shuffle( T[] shuffled_small_sorted )
	{
		T[] toReturn = Arrays.copyOf( shuffled_small_sorted, shuffled_small_sorted.length );
		for( int i = toReturn.length - 1; i > 0; --i )
		{
			int index = random.nextInt( i );
			swap( toReturn, i, index );
		}
		return toReturn;
	}
	
	private static <T extends Comparable<? super T>> void swap( T[] arr, int firstIndex, int secondIndex )
	{
		T temp = arr[secondIndex];
		arr[secondIndex] = arr[firstIndex];
		arr[firstIndex] = temp;
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
		
		sortAndVerify( quick, unsorted, sorted );
	}

	private <C extends Comparable<? super C>> void sortAndVerify( AbstractSorter<C> sorter, C[] unsorted, C[] sorted )
	{
		assertEquals( Arrays.toString( unsorted ), sorter.getUnsortedString() );
		assertEquals( Arrays.toString( unsorted ), sorter.getSortedString() );
		sorter.sort();
		assertEquals( Arrays.toString( unsorted ), sorter.getUnsortedString() );
		assertEquals( Arrays.toString( sorted ), sorter.getSortedString() );
	}



	@Test
	public void quickSortBasic()
	{
		AbstractSorter<Integer> quick = AbstractSorter.buildFrom( zeroToNineUnsorted , QuickSorter.class );
		sortAndVerify( quick, zeroToNineUnsorted, zeroToNine  );
	}

	@Test
	public void quickSortShuffledSmall()
	{
		AbstractSorter<Integer> quick = AbstractSorter.buildFrom( shuffled_small_unsorted , QuickSorter.class );
		sortAndVerify( quick, shuffled_small_unsorted, shuffled_small_sorted  );
	}

	@Test
	public void quickSortShuffledMedium()
	{
		AbstractSorter<Integer> quick = AbstractSorter.buildFrom( shuffled_medium_unsorted , QuickSorter.class );
		sortAndVerify( quick, shuffled_medium_unsorted, shuffled_medium_sorted  );
	}

	@Test
	public void quickSortShuffledLarge()
	{
		AbstractSorter<Integer> quick = AbstractSorter.buildFrom( shuffled_large_unsorted , QuickSorter.class );
		sortAndVerify( quick, shuffled_large_unsorted, shuffled_large_sorted  );
	}
	
	@Test
	public void quickSortSmallUnsorted()
	{
		AbstractSorter<Integer> quick = AbstractSorter.buildFrom( random_small_unsorted , QuickSorter.class );
		sortAndVerify( quick, random_small_unsorted, random_small_sorted );
	}
	
	@Test
	public void quickSortSmallSorted()
	{
		AbstractSorter<Integer> quick = AbstractSorter.buildFrom( random_small_sorted , QuickSorter.class );
		sortAndVerify( quick, random_small_sorted, random_small_sorted );
	}

	@Test
	public void quickSortMediumUnsorted()
	{
		AbstractSorter<Integer> quick = AbstractSorter.buildFrom( random_medium_unsorted , QuickSorter.class );
		sortAndVerify( quick, random_medium_unsorted, random_medium_sorted );
	}
	
	@Test
	public void quickSortMediumSorted()
	{
		AbstractSorter<Integer> quick = AbstractSorter.buildFrom( random_medium_sorted , QuickSorter.class );
		sortAndVerify( quick, random_medium_sorted, random_medium_sorted );
	}

	@Test
	public void quickSortLargeUnsorted()
	{
		AbstractSorter<Integer> quick = AbstractSorter.buildFrom( random_large_unsorted , QuickSorter.class );
		sortAndVerify( quick, random_large_unsorted, random_large_sorted );
	}
	
	@Test
	public void quickSortLargeSorted()
	{
		AbstractSorter<Integer> quick = AbstractSorter.buildFrom( random_large_sorted , QuickSorter.class );
		sortAndVerify( quick, random_large_sorted, random_large_sorted );
	}


	
	
	
	@Test
	public void mergeSortBasic()
	{
		AbstractSorter<Integer> merge = AbstractSorter.buildFrom( zeroToNineUnsorted , MergeSorter.class );
		sortAndVerify( merge, zeroToNineUnsorted, zeroToNine  );
	}

	@Test
	public void mergeSortShuffledSmall()
	{
		AbstractSorter<Integer> merge = AbstractSorter.buildFrom( shuffled_small_unsorted , MergeSorter.class );
		sortAndVerify( merge, shuffled_small_unsorted, shuffled_small_sorted  );
	}

	@Test
	public void mergeSortShuffledMedium()
	{
		AbstractSorter<Integer> merge = AbstractSorter.buildFrom( shuffled_medium_unsorted , MergeSorter.class );
		sortAndVerify( merge, shuffled_medium_unsorted, shuffled_medium_sorted  );
	}

	@Test
	public void mergeSortShuffledLarge()
	{
		AbstractSorter<Integer> merge = AbstractSorter.buildFrom( shuffled_large_unsorted , MergeSorter.class );
		sortAndVerify( merge, shuffled_large_unsorted, shuffled_large_sorted  );
	}
	
	@Test
	public void mergeSortSmallUnsorted()
	{
		AbstractSorter<Integer> merge = AbstractSorter.buildFrom( random_small_unsorted , MergeSorter.class );
		sortAndVerify( merge, random_small_unsorted, random_small_sorted );
	}
	
	@Test
	public void mergeSortSmallSorted()
	{
		AbstractSorter<Integer> merge = AbstractSorter.buildFrom( random_small_sorted , MergeSorter.class );
		sortAndVerify( merge, random_small_sorted, random_small_sorted );
	}

	@Test
	public void mergeSortMediumUnsorted()
	{
		AbstractSorter<Integer> merge = AbstractSorter.buildFrom( random_medium_unsorted , MergeSorter.class );
		sortAndVerify( merge, random_medium_unsorted, random_medium_sorted );
	}
	
	@Test
	public void mergeSortMediumSorted()
	{
		AbstractSorter<Integer> merge = AbstractSorter.buildFrom( random_medium_sorted , MergeSorter.class );
		sortAndVerify( merge, random_medium_sorted, random_medium_sorted );
	}

	@Test
	public void mergeSortLargeUnsorted()
	{
		AbstractSorter<Integer> merge = AbstractSorter.buildFrom( random_large_unsorted , MergeSorter.class );
		sortAndVerify( merge, random_large_unsorted, random_large_sorted );
	}
	
	@Test
	public void mergeSortLargeSorted()
	{
		AbstractSorter<Integer> merge = AbstractSorter.buildFrom( random_large_sorted , MergeSorter.class );
		sortAndVerify( merge, random_large_sorted, random_large_sorted );
	}


	
	
	
	@Test
	public void insertionSortBasic()
	{
		AbstractSorter<Integer> insertion = AbstractSorter.buildFrom( zeroToNineUnsorted , InsertionSorter.class );
		sortAndVerify( insertion, zeroToNineUnsorted, zeroToNine  );
	}

	@Test
	public void insertionSortShuffledSmall()
	{
		AbstractSorter<Integer> insertion = AbstractSorter.buildFrom( shuffled_small_unsorted , InsertionSorter.class );
		sortAndVerify( insertion, shuffled_small_unsorted, shuffled_small_sorted  );
	}

	@Test
	public void insertionSortShuffledMedium()
	{
		AbstractSorter<Integer> insertion = AbstractSorter.buildFrom( shuffled_medium_unsorted , InsertionSorter.class );
		sortAndVerify( insertion, shuffled_medium_unsorted, shuffled_medium_sorted  );
	}

	@Test
	public void insertionSortShuffledLarge()
	{
		AbstractSorter<Integer> insertion = AbstractSorter.buildFrom( shuffled_large_unsorted , InsertionSorter.class );
		sortAndVerify( insertion, shuffled_large_unsorted, shuffled_large_sorted  );
	}
	
	@Test
	public void insertionSortSmallUnsorted()
	{
		AbstractSorter<Integer> insertion = AbstractSorter.buildFrom( random_small_unsorted , InsertionSorter.class );
		sortAndVerify( insertion, random_small_unsorted, random_small_sorted );
	}
	
	@Test
	public void insertionSortSmallSorted()
	{
		AbstractSorter<Integer> insertion = AbstractSorter.buildFrom( random_small_sorted , InsertionSorter.class );
		sortAndVerify( insertion, random_small_sorted, random_small_sorted );
	}

	@Test
	public void insertionSortMediumUnsorted()
	{
		AbstractSorter<Integer> insertion = AbstractSorter.buildFrom( random_medium_unsorted , InsertionSorter.class );
		sortAndVerify( insertion, random_medium_unsorted, random_medium_sorted );
	}
	
	@Test
	public void insertionSortMediumSorted()
	{
		AbstractSorter<Integer> insertion = AbstractSorter.buildFrom( random_medium_sorted , InsertionSorter.class );
		sortAndVerify( insertion, random_medium_sorted, random_medium_sorted );
	}

	@Test
	public void insertionSortLargeUnsorted()
	{
		AbstractSorter<Integer> insertion = AbstractSorter.buildFrom( random_large_unsorted , InsertionSorter.class );
		sortAndVerify( insertion, random_large_unsorted, random_large_sorted );
	}
	
	@Test
	public void insertionSortLargeSorted()
	{
		AbstractSorter<Integer> insertion = AbstractSorter.buildFrom( random_large_sorted , InsertionSorter.class );
		sortAndVerify( insertion, random_large_sorted, random_large_sorted );
	}



	
	
	
	@Test
	public void selectionSortBasic()
	{
		AbstractSorter<Integer> selection = AbstractSorter.buildFrom( zeroToNineUnsorted , SelectionSorter.class );
		sortAndVerify( selection, zeroToNineUnsorted, zeroToNine  );
	}

	@Test
	public void selectionSortShuffledSmall()
	{
		AbstractSorter<Integer> selection = AbstractSorter.buildFrom( shuffled_small_unsorted , SelectionSorter.class );
		sortAndVerify( selection, shuffled_small_unsorted, shuffled_small_sorted  );
	}

	@Test
	public void selectionSortShuffledMedium()
	{
		AbstractSorter<Integer> selection = AbstractSorter.buildFrom( shuffled_medium_unsorted , SelectionSorter.class );
		sortAndVerify( selection, shuffled_medium_unsorted, shuffled_medium_sorted  );
	}

	@Test
	public void selectionSortShuffledLarge()
	{
		AbstractSorter<Integer> selection = AbstractSorter.buildFrom( shuffled_large_unsorted , SelectionSorter.class );
		sortAndVerify( selection, shuffled_large_unsorted, shuffled_large_sorted  );
	}
	
	@Test
	public void selectionSortSmallUnsorted()
	{
		AbstractSorter<Integer> selection = AbstractSorter.buildFrom( random_small_unsorted , SelectionSorter.class );
		sortAndVerify( selection, random_small_unsorted, random_small_sorted );
	}
	
	@Test
	public void selectionSortSmallSorted()
	{
		AbstractSorter<Integer> selection = AbstractSorter.buildFrom( random_small_sorted , SelectionSorter.class );
		sortAndVerify( selection, random_small_sorted, random_small_sorted );
	}

	@Test
	public void selectionSortMediumUnsorted()
	{
		AbstractSorter<Integer> selection = AbstractSorter.buildFrom( random_medium_unsorted , SelectionSorter.class );
		sortAndVerify( selection, random_medium_unsorted, random_medium_sorted );
	}
	
	@Test
	public void selectionSortMediumSorted()
	{
		AbstractSorter<Integer> selection = AbstractSorter.buildFrom( random_medium_sorted , SelectionSorter.class );
		sortAndVerify( selection, random_medium_sorted, random_medium_sorted );
	}

	@Test
	public void selectionSortLargeUnsorted()
	{
		AbstractSorter<Integer> selection = AbstractSorter.buildFrom( random_large_unsorted , SelectionSorter.class );
		sortAndVerify( selection, random_large_unsorted, random_large_sorted );
	}
	
	@Test
	public void selectionSortLargeSorted()
	{
		AbstractSorter<Integer> selection = AbstractSorter.buildFrom( random_large_sorted , SelectionSorter.class );
		sortAndVerify( selection, random_large_sorted, random_large_sorted );
	}





	@Test
	public void bubbleSortBasic()
	{
		AbstractSorter<Integer> bubble = AbstractSorter.buildFrom( zeroToNineUnsorted , BubbleSorter.class );
		sortAndVerify( bubble, zeroToNineUnsorted, zeroToNine  );
	}

	@Test
	public void bubbleSortShuffledSmall()
	{
		AbstractSorter<Integer> bubble = AbstractSorter.buildFrom( shuffled_small_unsorted , BubbleSorter.class );
		sortAndVerify( bubble, shuffled_small_unsorted, shuffled_small_sorted  );
	}

	@Test
	public void bubbleSortShuffledMedium()
	{
		AbstractSorter<Integer> bubble = AbstractSorter.buildFrom( shuffled_medium_unsorted , BubbleSorter.class );
		sortAndVerify( bubble, shuffled_medium_unsorted, shuffled_medium_sorted  );
	}

	@Test
	public void bubbleSortShuffledLarge()
	{
		AbstractSorter<Integer> bubble = AbstractSorter.buildFrom( shuffled_large_unsorted , BubbleSorter.class );
		sortAndVerify( bubble, shuffled_large_unsorted, shuffled_large_sorted  );
	}
	
	@Test
	public void bubbleSortSmallUnsorted()
	{
		AbstractSorter<Integer> bubble = AbstractSorter.buildFrom( random_small_unsorted , BubbleSorter.class );
		sortAndVerify( bubble, random_small_unsorted, random_small_sorted );
	}
	
	@Test
	public void bubbleSortSmallSorted()
	{
		AbstractSorter<Integer> bubble = AbstractSorter.buildFrom( random_small_sorted , BubbleSorter.class );
		sortAndVerify( bubble, random_small_sorted, random_small_sorted );
	}

	@Test
	public void bubbleSortMediumUnsorted()
	{
		AbstractSorter<Integer> bubble = AbstractSorter.buildFrom( random_medium_unsorted , BubbleSorter.class );
		sortAndVerify( bubble, random_medium_unsorted, random_medium_sorted );
	}
	
	@Test
	public void bubbleSortMediumSorted()
	{
		AbstractSorter<Integer> bubble = AbstractSorter.buildFrom( random_medium_sorted , BubbleSorter.class );
		sortAndVerify( bubble, random_medium_sorted, random_medium_sorted );
	}

	@Test
	public void bubbleSortLargeUnsorted()
	{
		AbstractSorter<Integer> bubble = AbstractSorter.buildFrom( random_large_unsorted , BubbleSorter.class );
		sortAndVerify( bubble, random_large_unsorted, random_large_sorted );
	}
	
	@Test
	public void bubbleSortLargeSorted()
	{
		AbstractSorter<Integer> bubble = AbstractSorter.buildFrom( random_large_sorted , BubbleSorter.class );
		sortAndVerify( bubble, random_large_sorted, random_large_sorted );
	}






	@Test
	public void heapSortBasic()
	{
		AbstractSorter<Integer> heap = AbstractSorter.buildFrom( zeroToNineUnsorted , HeapSorter.class );
		sortAndVerify( heap, zeroToNineUnsorted, zeroToNine  );
	}

	@Test
	public void heapSortShuffledSmall()
	{
		AbstractSorter<Integer> heap = AbstractSorter.buildFrom( shuffled_small_unsorted , HeapSorter.class );
		sortAndVerify( heap, shuffled_small_unsorted, shuffled_small_sorted  );
	}

	@Test
	public void heapSortShuffledMedium()
	{
		AbstractSorter<Integer> heap = AbstractSorter.buildFrom( shuffled_medium_unsorted , HeapSorter.class );
		sortAndVerify( heap, shuffled_medium_unsorted, shuffled_medium_sorted  );
	}

	@Test
	public void heapSortShuffledLarge()
	{
		AbstractSorter<Integer> heap = AbstractSorter.buildFrom( shuffled_large_unsorted , HeapSorter.class );
		sortAndVerify( heap, shuffled_large_unsorted, shuffled_large_sorted  );
	}
	
	@Test
	public void heapSortSmallUnsorted()
	{
		AbstractSorter<Integer> heap = AbstractSorter.buildFrom( random_small_unsorted , HeapSorter.class );
		sortAndVerify( heap, random_small_unsorted, random_small_sorted );
	}
	
	@Test
	public void heapSortSmallSorted()
	{
		AbstractSorter<Integer> heap = AbstractSorter.buildFrom( random_small_sorted , HeapSorter.class );
		sortAndVerify( heap, random_small_sorted, random_small_sorted );
	}

	@Test
	public void heapSortMediumUnsorted()
	{
		AbstractSorter<Integer> heap = AbstractSorter.buildFrom( random_medium_unsorted , HeapSorter.class );
		sortAndVerify( heap, random_medium_unsorted, random_medium_sorted );
	}
	
	@Test
	public void heapSortMediumSorted()
	{
		AbstractSorter<Integer> heap = AbstractSorter.buildFrom( random_medium_sorted , HeapSorter.class );
		sortAndVerify( heap, random_medium_sorted, random_medium_sorted );
	}

	@Test
	public void heapSortLargeUnsorted()
	{
		AbstractSorter<Integer> heap = AbstractSorter.buildFrom( random_large_unsorted , HeapSorter.class );
		sortAndVerify( heap, random_large_unsorted, random_large_sorted );
	}
	
	@Test
	public void heapSortLargeSorted()
	{
		AbstractSorter<Integer> heap = AbstractSorter.buildFrom( random_large_sorted , HeapSorter.class );
		sortAndVerify( heap, random_large_sorted, random_large_sorted );
	}






	@Test
	public void gnomeSortBasic()
	{
		AbstractSorter<Integer> gnome = AbstractSorter.buildFrom( zeroToNineUnsorted , GnomeSorter.class );
		sortAndVerify( gnome, zeroToNineUnsorted, zeroToNine  );
	}

	@Test
	public void gnomeSortShuffledSmall()
	{
		AbstractSorter<Integer> gnome = AbstractSorter.buildFrom( shuffled_small_unsorted , GnomeSorter.class );
		sortAndVerify( gnome, shuffled_small_unsorted, shuffled_small_sorted  );
	}

	@Test
	public void gnomeSortShuffledMedium()
	{
		AbstractSorter<Integer> gnome = AbstractSorter.buildFrom( shuffled_medium_unsorted , GnomeSorter.class );
		sortAndVerify( gnome, shuffled_medium_unsorted, shuffled_medium_sorted  );
	}

	@Test
	public void gnomeSortShuffledLarge()
	{
		AbstractSorter<Integer> gnome = AbstractSorter.buildFrom( shuffled_large_unsorted , GnomeSorter.class );
		sortAndVerify( gnome, shuffled_large_unsorted, shuffled_large_sorted  );
	}
	
	@Test
	public void gnomeSortSmallUnsorted()
	{
		AbstractSorter<Integer> gnome = AbstractSorter.buildFrom( random_small_unsorted , GnomeSorter.class );
		sortAndVerify( gnome, random_small_unsorted, random_small_sorted );
	}
	
	@Test
	public void gnomeSortSmallSorted()
	{
		AbstractSorter<Integer> gnome = AbstractSorter.buildFrom( random_small_sorted , GnomeSorter.class );
		sortAndVerify( gnome, random_small_sorted, random_small_sorted );
	}

	@Test
	public void gnomeSortMediumUnsorted()
	{
		AbstractSorter<Integer> gnome = AbstractSorter.buildFrom( random_medium_unsorted , GnomeSorter.class );
		sortAndVerify( gnome, random_medium_unsorted, random_medium_sorted );
	}
	
	@Test
	public void gnomeSortMediumSorted()
	{
		AbstractSorter<Integer> gnome = AbstractSorter.buildFrom( random_medium_sorted , GnomeSorter.class );
		sortAndVerify( gnome, random_medium_sorted, random_medium_sorted );
	}

	@Test
	public void gnomeSortLargeUnsorted()
	{
		AbstractSorter<Integer> gnome = AbstractSorter.buildFrom( random_large_unsorted , GnomeSorter.class );
		sortAndVerify( gnome, random_large_unsorted, random_large_sorted );
	}
	
	@Test
	public void gnomeSortLargeSorted()
	{
		AbstractSorter<Integer> gnome = AbstractSorter.buildFrom( random_large_sorted , GnomeSorter.class );
		sortAndVerify( gnome, random_large_sorted, random_large_sorted );
	}






	@Test
	public void cocktailShakerSortBasic()
	{
		AbstractSorter<Integer> cocktailShaker = AbstractSorter.buildFrom( zeroToNineUnsorted , CocktailShakerSorter.class );
		sortAndVerify( cocktailShaker, zeroToNineUnsorted, zeroToNine  );
	}

	@Test
	public void cocktailShakerSortShuffledSmall()
	{
		AbstractSorter<Integer> cocktailShaker = AbstractSorter.buildFrom( shuffled_small_unsorted , CocktailShakerSorter.class );
		sortAndVerify( cocktailShaker, shuffled_small_unsorted, shuffled_small_sorted  );
	}

	@Test
	public void cocktailShakerSortShuffledMedium()
	{
		AbstractSorter<Integer> cocktailShaker = AbstractSorter.buildFrom( shuffled_medium_unsorted , CocktailShakerSorter.class );
		sortAndVerify( cocktailShaker, shuffled_medium_unsorted, shuffled_medium_sorted  );
	}

	@Test
	public void cocktailShakerSortShuffledLarge()
	{
		AbstractSorter<Integer> cocktailShaker = AbstractSorter.buildFrom( shuffled_large_unsorted , CocktailShakerSorter.class );
		sortAndVerify( cocktailShaker, shuffled_large_unsorted, shuffled_large_sorted  );
	}
	
	@Test
	public void cocktailShakerSortSmallUnsorted()
	{
		AbstractSorter<Integer> cocktailShaker = AbstractSorter.buildFrom( random_small_unsorted , CocktailShakerSorter.class );
		sortAndVerify( cocktailShaker, random_small_unsorted, random_small_sorted );
	}
	
	@Test
	public void cocktailShakerSortSmallSorted()
	{
		AbstractSorter<Integer> cocktailShaker = AbstractSorter.buildFrom( random_small_sorted , CocktailShakerSorter.class );
		sortAndVerify( cocktailShaker, random_small_sorted, random_small_sorted );
	}

	@Test
	public void cocktailShakerSortMediumUnsorted()
	{
		AbstractSorter<Integer> cocktailShaker = AbstractSorter.buildFrom( random_medium_unsorted , CocktailShakerSorter.class );
		sortAndVerify( cocktailShaker, random_medium_unsorted, random_medium_sorted );
	}
	
	@Test
	public void cocktailShakerSortMediumSorted()
	{
		AbstractSorter<Integer> cocktailShaker = AbstractSorter.buildFrom( random_medium_sorted , CocktailShakerSorter.class );
		sortAndVerify( cocktailShaker, random_medium_sorted, random_medium_sorted );
	}

	@Test
	public void cocktailShakerSortLargeUnsorted()
	{
		AbstractSorter<Integer> cocktailShaker = AbstractSorter.buildFrom( random_large_unsorted , CocktailShakerSorter.class );
		sortAndVerify( cocktailShaker, random_large_unsorted, random_large_sorted );
	}
	
	@Test
	public void cocktailShakerSortLargeSorted()
	{
		AbstractSorter<Integer> cocktailShaker = AbstractSorter.buildFrom( random_large_sorted , CocktailShakerSorter.class );
		sortAndVerify( cocktailShaker, random_large_sorted, random_large_sorted );
	}
}

package Sorting;

/**
 * lol, bubblesort.
 * @author Jesse Frush
 */
public class BubbleSorter<T extends Comparable<? super T>> extends AbstractSorter<T>
{

	public BubbleSorter( T[] values )
	{
		super( values );
	}

	@Override
	protected void _sort()
	{
		for( int i = 0; i < sorted.length; ++i )
		{
			boolean swaps = false;
			for( int j = 0; j < sorted.length - 1 - i; ++j )
			{
				if( sorted[j].compareTo( sorted[j + 1] ) > 0 )
				{
					swap( j, j + 1 );
					swaps = true;
				}
			}
			if( !swaps )
			{
				break;
			}
		}
	}
}

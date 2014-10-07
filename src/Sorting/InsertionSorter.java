package Sorting;

public class InsertionSorter<E extends Comparable<? super E>> extends AbstractSorter<E>
{

	public InsertionSorter( E[] values )
	{
		super( values );
	}

	@Override
	protected void _sort()
	{
		for( int i = 1; i < sorted.length; ++i )
		{
			int j = i;
			while( j > 0 && sorted[j].compareTo( sorted[ j - 1 ] ) < 0 )
			{
				swap( j, j - 1 );
				j--;
			}
		}
	}

}

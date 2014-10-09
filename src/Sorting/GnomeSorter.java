package Sorting;

public class GnomeSorter<T extends Comparable<? super T>> extends AbstractSorter<T>
{

	public GnomeSorter( T[] values )
	{
		super( values );
	}

	@Override
	protected void _sort()
	{
		for( int i = 0; i < sorted.length; )
		{
			if( i == 0 || sorted[i - 1].compareTo( sorted[i] ) <= 0 ) ++i;
			else swap( i, --i );
		}
	}

}

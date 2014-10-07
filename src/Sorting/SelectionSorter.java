package Sorting;

public class SelectionSorter<E extends Comparable<? super E>> extends AbstractSorter<E>
{

	public SelectionSorter( E[] values )
	{
		super( values );
	}

	@Override
	protected void _sort()
	{
		for( int i = 0; i < sorted.length; ++i )
		{
			E min = sorted[i];
			int minIndex = i;
			for( int j = i + 1; j < sorted.length; ++j )
			{
				if( sorted[j].compareTo( min ) < 0 )
				{
					min = sorted[j];
					minIndex = j;
				}
			}
			swap( i, minIndex );
		}
	}
}

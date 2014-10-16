package Sorting;

public class InsertionSorter<T extends Comparable<? super T>> extends AbstractSorter<T>
{

	public InsertionSorter( T[] values )
	{
		super( values );
	}

	@Override
	protected void _sort()
	{
		for( int i = 1; i < sorted.length; ++i )
		{
			int j = i;
			T data = sorted[i];
			while( j > 0 && sorted[j - 1].compareTo( data ) > 0 )
			{
				sorted[j] = sorted[j - 1];
				j--;
			}
			sorted[j] = data;
		}
	}

}

package Sorting;

public class CocktailShakerSorter<T extends Comparable<? super T>> extends AbstractSorter<T>
{

	public CocktailShakerSorter( T[] values )
	{
		super( values );
	}

	@Override
	protected void _sort()
	{
		for( int front = 0, back = sorted.length - 1; front <= back; ++front )
		{
			int i;
			
			for( i = front + 1; i <= back; ++i )
			{
				if( sorted[ i - 1 ].compareTo( sorted[i] ) > 0 )
				{
					swap( i - 1, i );
				}
			}
			--back;
			i -= 2;
			
			for( ; i >= front; --i )
			{
				if( sorted[ i + 1 ].compareTo( sorted[i] ) < 0 )
				{
					swap( i + 1, i );
				}
			}
		}
	}

}

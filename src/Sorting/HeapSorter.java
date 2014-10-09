package Sorting;

public class HeapSorter<T extends Comparable<? super T>> extends AbstractSorter<T>
{
	//represents the last index of the heap section of the array
	private int heapEnd;

	public HeapSorter( T[] values )
	{
		super( values );
		heapEnd = sorted.length - 1;
	}

	@Override
	protected void _sort()
	{
		heapify();
		for( int i = sorted.length - 1; i > 0; --i )
		{
			swap( 0, heapEnd-- );
			percolateDown( 0 );
		}
//		swap( 0, heapEnd-- );
	}

	/**
	 * creates a max heap out of this array
	 */
	private void heapify()
	{
		for( int i = ( sorted.length - 2 ) / 2; i >= 0; --i )
		{
			percolateDown( i );
		}
	}

	private void percolateDown( int i )
	{
		int leftChild = i * 2 + 1;
		while( leftChild <= heapEnd )
		{
			int swapChild = ( ( leftChild + 1 ) > heapEnd || sorted[ leftChild ].compareTo( sorted[ leftChild + 1 ] ) > 0 ? leftChild : leftChild + 1 );
			if( sorted[ i ].compareTo( sorted[ swapChild ] ) < 0 )
			{
				swap( i, swapChild );
				i = swapChild;
				leftChild = i * 2 + 1;
			}
			else break;
		}
	}

}

public class QuickSorter<E extends Comparable<? super E>> extends AbstractSorter<E>
{

	public QuickSorter( E[] values )
	{
		super( values );
	}

	@Override
	protected void _sort()
	{
		quicksort( 0, unsorted.length - 1 );
	}

	private void quicksort( int left, int right )
	{
		if( left > right ) return;
		int i = partition( left, right );
		quicksort( left, i - 1 );
		quicksort( i + 1, right );
	}

	/**
	 * The method selects a pivotal element in the index range [left..right] of
	 * the subarray and partitions the subarray into two subarrays such that all
	 * the elements in the left subarray are less than or equal to the pivotal
	 * element and all the elements in the right subarray are greater than or
	 * equal to the pivotal element. The method returns the index of the
	 * rightmost element in the left subarray.
	 * 
	 * @param left
	 *            starting index of the subarray
	 * @param right
	 *            ending index of the subarray
	 * @return index of the rightmost element in the left subarray
	 */
	private int partition( int left, int right )
	{
		if( left >= right ) return left;
		E pivot = sorted[left];
		int pivotIndex = left, i = ( left + 1 ), j = right;
		while( i <= j )
		{
			while( i < right && sorted[i].compareTo( pivot ) < 0 )
				i++;
			while( j > left && sorted[j].compareTo( pivot ) >= 0 )
				j--;
			if( i < j )
			{
				swap( i++, j-- );
			}
			else if( i == j )
			{
				break;
			}
			
		}
		swap( pivotIndex, j );
		return j;
	}

	private void swap( int firstIndex, int secondIndex )
	{
		E temp = sorted[secondIndex];
		sorted[secondIndex] = sorted[firstIndex];
		sorted[firstIndex] = temp;
	}
}

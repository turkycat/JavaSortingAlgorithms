package Sorting;

import java.util.Arrays;

public class MergeSorter<E extends Comparable<? super E>> extends AbstractSorter<E>
{

	public MergeSorter( E[] values )
	{
		super( values );
	}

	@Override
	protected void _sort()
	{
		mergesort( 0, sorted.length - 1 );
	}


	private void mergesort( int left, int right )
	{
		if( left >= right ) return;
		
		int mid = left + (right - left) / 2;
		mergesort( left, mid );
		mergesort( mid + 1, right );
		merge( left, mid, right );
	}
	
	private void merge( int left, int mid, int right )
	{
		E[] cache = Arrays.copyOfRange( sorted, left, right + 1 );
		
		mid = mid - left;
		right = right - left;
		int i = 0;
		int j = mid + 1;
		int k = left;
		while( i <= mid && j <= right )
		{
			if( cache[i].compareTo( cache[j] ) < 0 )
			{
				sorted[k++] = cache[i++];
			}
			else
			{
				sorted[k++] = cache[j++];
			}
		}
		
		while( i <= mid )
		{
			sorted[k++] = cache[i++];
		}
		
		while( j <= right )
		{
			sorted[k++] = cache[j++];
		}
	}
}


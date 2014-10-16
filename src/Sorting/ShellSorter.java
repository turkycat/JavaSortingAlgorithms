package Sorting;

import java.util.LinkedList;
import java.util.ListIterator;

public class ShellSorter<T extends Comparable<? super T>> extends AbstractSorter<T>
{
	//gap generation modes with decreasing time complexities further down the list
	public enum GapMode
	{
		Shell,				// O( n^2 )
		Pratt,				// O( n * log(n)^2 )
		Frank_Lazarus,		// O( n^(3/2) )
		Hibbard,			// O( n^(3/2) )
		Knuth,				// O( n^(3/2) )
		SedgewickOne,			// O( n^(4/3)
		SedgewickTwo,			// O( n^(4/3)
		Gonnet_BaezaYates,	// O( ? )
		Tokuda,				// O( ? )
		Ciura				// O( ? )
	}
	
	private int[] gaps;
	
	public ShellSorter( T[] values )
	{
		this( values, GapMode.Ciura );
	}
	
	public ShellSorter( T[] values, GapMode mode )
	{
		super( values );

		//we will generate our gap array now so that calculation time is not factored into sorting time.
		generateGaps( mode );
	}

	
	@Override
	protected void _sort()
	{
		
	}
	
	
	/**
	 * for testing, verification, and fun
	 */
	public int[] getGaps()
	{
		return gaps;
	}

	
	/**
	 * generates a series of gaps (usually) based upon a mathematical formula
	 */
	private void generateGaps( GapMode mode )
	{
		int n = sorted.length, k = 1, gap;
		LinkedList<Integer> calculatedGaps = new LinkedList<Integer>();
		switch( mode )
		{
		case Shell:
			
			// floor( N / 2^k )
			do
			{
				gap = (int) Math.floor( n / Math.pow( 2, k++ ) );
				calculatedGaps.add( gap );
			} while( gap > 1 );
			
			break;

		case Pratt:
			
			// reverse of sequence starting from k = l = 0: 2^k * 3^l
			gap = 0;
			calculatedGaps.add( 1 );
			for( int i = 0; gap < n; i++ )
			{
				int index = calculatedGaps.size() - i - 1;
				Integer current = calculatedGaps.get( index );
				gap = current * 2;
				insertIntoGapList( calculatedGaps, gap, true );
				gap = current * 3;
				insertIntoGapList( calculatedGaps, gap, true );
			}

			break;
			
		case Frank_Lazarus:
			
			// 2 * floor( N / 2^( k + 1 ) ) + 1
			do
			{
				gap = (int) ( 2 * Math.floor( n / Math.pow( 2, ++k ) ) ) + 1;
				calculatedGaps.add( gap );
			} while( gap > 1 );
			
			break;
			
		case Hibbard:

			// reverse of sequence: 2^k - 1
			do
			{
				gap = (int) Math.pow( 2, k++ ) - 1;
				calculatedGaps.addFirst( gap );
			} while( gap < n );
			
			break;

		case Knuth:
			
			// reverse of sequence: ( 3^k - 1 ) / 2
			int done = n / 3;
			do
			{
				gap = ( ( (int)( Math.pow( 3, k++ ) -1 ) ) / 2 );
				calculatedGaps.addFirst( gap );
			} while( gap < done );
			
			break;

		case SedgewickOne:
			
			// {1} UNION reverse of sequence: 4^k + 3 * 2^(k-1) + 1
			calculatedGaps.add( 1 );
			gap = 1;
			while( gap < n )
			{
				gap = (int) ( Math.pow( 4, k ) + ( 3 * Math.pow( 2, k - 1 ) ) + 1 );
				calculatedGaps.addFirst( gap );
				k++;
			}
			break;

		case SedgewickTwo:

			// reverse of sequence: 9 * ( 4^(k-1) - 2^(k-1) ) + 1 AND 4^(k+1) - 6 * 2^k + 1
			do
			{
			gap = (int) ( ( 9 * ( Math.pow( 4, k - 1 ) - Math.pow( 2, k - 1 ) ) ) + 1 );
			calculatedGaps.addFirst( gap );
			gap = (int) ( Math.pow( 4, k + 1 ) - ( 6 * ( Math.pow( 2, k ) ) ) + 1 );
			calculatedGaps.addFirst( gap );
			k++;
			} while( gap < n );
			
			break;
			
		case Gonnet_BaezaYates:
			
			// F(i) = max( floor( 5 * F(i-1) / 11 ), 1 ) F(0) = N
			
			gap = n;
			do
			{
				gap = (int) Math.max( ( 5 * gap ) / 11, 1 );
				calculatedGaps.add( gap );
			} while( gap < n );
			
			break;
			
		case Tokuda:
			break;
			
		default:
		case Ciura:		//Ciura's method is not a formula, but a magic sequence of numbers
			gaps = new int[]{ 701, 301, 132, 57, 23, 10, 4, 1 };
			break;
		}
		
		if( gaps == null )
		{
			gaps = new int[calculatedGaps.size()];
			int i = 0;
			for( Integer val : calculatedGaps )
			{
				gaps[i++] = val;
			}
		}
	}
	
	
	
	private void insertIntoGapList( LinkedList<Integer> list, int gap, boolean reverse )
	{
//		if( reverse )
//		{
			ListIterator<Integer> iter = ( reverse ? list.listIterator() : list.listIterator( list.size() ) );
			while( ( reverse ? iter.hasNext() : iter.hasPrevious() ) )
			{
				int current = ( reverse ? iter.next() : iter.previous() );
				if( current == gap ) break;
				else if( current < gap )
				{
					if( reverse ) iter.previous();
					else iter.next();
					
					iter.add( gap );
					break;
				}
			}
//		}
//		else
//		{
//			ListIterator<Integer> iter = list.listIterator( list.size() );
//			while( iter.hasPrevious() )
//			{
//				int current = iter.previous();
//				if( current == gap ) break;
//				else if( current < gap )
//				{
//					iter.next();
//					iter.add( gap );
//					break;
//				}
//			}
//		}
	}

	public static void main(String[] args)
	{
		ShellSorter s = new ShellSorter( new Integer[1000], GapMode.Gonnet_BaezaYates );
	}
}

package Sorting;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;


public abstract class AbstractSorter<T extends Comparable<? super T>>
{
	public T[] unsorted;
	public T[] sorted;
	public boolean sortingComplete;
	public long computeTime;
	
	//all extended classes must only implement this function
	protected abstract void _sort();
	
	
	@SuppressWarnings( "unchecked" )
	public AbstractSorter( T[] values )
	{
		computeTime = 0;
		sortingComplete = false;
		if( values == null || values.length < 1 )
		{
			throw new IllegalArgumentException( "must provide a valid array to sort." );
		}
		this.unsorted = (T[]) new Comparable[ values.length ];
		this.sorted = (T[]) new Comparable[ values.length ];
		for( int i = 0; i < values.length; ++i )
		{
			this.unsorted[i] = values[i];
			this.sorted[i] = values[i];
		}
	}
	
	public void sort()
	{
		if( sortingComplete ) return;
		
		long startTime = System.currentTimeMillis();
		_sort();
		computeTime = System.currentTimeMillis() - startTime;
		sortingComplete = true;
	}
	
	
	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		String sort = getClass().getName();
		String t = unsorted[0].getClass().getName();
		sb.append( "Sorting for " ).append( sort ).append( " with value type: " ).append( t ).append( "\n" );
		sb.append( "unsorted: \t" ).append( getUnsortedString() ).append( "\n" );
		if( sortingComplete )
		{
			sb.append( "sorting time: " ).append( computeTime ).append( "\n" );
			sb.append( "sorted: \t" ).append( getSortedString() );
		}
		
		return sb.toString();
	}
	
	
	public String getUnsortedString()
	{
		return Arrays.toString( unsorted );
	}
	
	
	public String getSortedString()
	{
		return Arrays.toString( sorted );
	}
	

	public long getComputeTimeMillis()
	{
		return computeTime;
	}
	

	
//	@SuppressWarnings( { "rawtypes", "unchecked" } )
//	public static <T extends AbstractSorter<?>> AbstractSorter<Integer> buildFrom( int[] arr, Class<T> cla )
//	{
//		if( arr == null || arr.length < 1 ) throw new IllegalArgumentException();
//		Integer[] data = new Integer[ arr.length ];
//		for( int i = 0; i < arr.length; ++i )
//		{
//			data[i] = new Integer( arr[i] );
//		}
//
//		Constructor<?> cons = null;
//		try
//		{
//			Class[] type = { Comparable[].class };
//			cons = cla.getConstructor( type );
//			
//			if( cons != null )
//			{
//				Object[] cdata = { data };
//				return (AbstractSorter<Integer>) cons.newInstance( cdata );
//			}
//		}
//		catch( NoSuchMethodException e )
//		{
//			e.printStackTrace();
//		}
//		catch( SecurityException e )
//		{
//			e.printStackTrace();
//		}
//		catch( InstantiationException e )
//		{
//			e.printStackTrace();
//		}
//		catch( IllegalAccessException e )
//		{
//			e.printStackTrace();
//		}
//		catch( IllegalArgumentException e )
//		{
//			e.printStackTrace();
//		}
//		catch( InvocationTargetException e )
//		{
//			e.printStackTrace();
//		}
//		
//		return null;
//	}

	
	@SuppressWarnings( { "rawtypes", "unchecked" } )
	public static <C extends Comparable<? super C>, T extends AbstractSorter<?>> AbstractSorter<C> buildFrom( C[] arr, Class<T> cla )
	{
		if( arr == null || arr.length < 1 ) throw new IllegalArgumentException();
		

		Constructor<?> cons = null;
		try
		{
			Class[] type = { Comparable[].class };
			cons = cla.getConstructor( type );
			
			if( cons != null )
			{
				Object[] cdata = { Arrays.copyOf( arr, arr.length ) };
				return (AbstractSorter<C>) cons.newInstance( cdata );
			}
		}
		catch( NoSuchMethodException e )
		{
			e.printStackTrace();
		}
		catch( SecurityException e )
		{
			e.printStackTrace();
		}
		catch( InstantiationException e )
		{
			e.printStackTrace();
		}
		catch( IllegalAccessException e )
		{
			e.printStackTrace();
		}
		catch( IllegalArgumentException e )
		{
			e.printStackTrace();
		}
		catch( InvocationTargetException e )
		{
			e.printStackTrace();
		}
		
		return null;
	}
}

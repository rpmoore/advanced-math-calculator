package linear.services.vector;

import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import linear.exceptions.VectorEmptyException;
import linear.exceptions.VectorSizeIncorrect;
import linear.type.ops.DoubMult;
import linear.type.ops.IntMult;
import linear.type.ops.Op;
import linear.vector.Vector;

public class MultService {
	private static final ExecutorService threadPool = Executors.newCachedThreadPool();
	private static final HashMap<Class,Op> operations = new HashMap<Class, Op>();
	
	//The static constructor for setting up the hashmap with the ops.
	static {
		operations.put(Integer.class, new IntMult());
		operations.put(Double.class,new DoubMult());
	}
	
	
	/**
	 * This performs a vector addition of vector a and vector b concurrently.  While the computation is
	 * going on the add call is blocking, as soon as the computation is complete the call will return 
	 * with a new vector containing the result.  If either of the parameters are null, then null is returned.
	 * If the vectors do not have the same size an VectorSizeIncorrect is thrown stating so.
	 * @param a The left argument for the addition.
	 * @param b The right argument for the addition.
	 * @return The result of the vector addition..
	 */
	public static <U> Vector<U> multConcurrent(Vector<U> a, Vector<U> b) throws VectorSizeIncorrect
	{
		
		if(a == null || b == null)
		{
			return null;
		}
		if(a.size() != b.size())
		{
			throw new VectorSizeIncorrect();
		}
		
		return null;
	}
	
	@SuppressWarnings("unchecked") //Need this for when performing the op
	public static <U> Vector<U> multSerial(Vector<U> a, Vector<U> b) throws VectorSizeIncorrect,VectorEmptyException
	{
		if(a == null || b == null)
		{
			return null;
		}
		if(a.size() != b.size())
		{
			throw new VectorSizeIncorrect();
		}		
		if(a.size() == 0 || b.size() == 0)
		{
			throw new VectorEmptyException();
		}
		Op currentOp = operations.get(a.get(0).getClass()); //get the op for the class that has been passed in. 
		final Vector<U> vec = new Vector<U>();
		for(int i =0; i < a.size();++i)
		{
			vec.insertLast((U)currentOp.performOp(a.get(i), b.get(i)));
		}
		
		return vec;
	}
	
	@SuppressWarnings("unchecked") //Need this for when performing the op
	public static <U> Vector<U> multSerialSame(U constant, Vector<U> a) throws VectorEmptyException
	{
		if(a == null || constant == null)
		{
			return null;
		}
		if(a.size() == 0)
		{
			throw new VectorEmptyException();
		}
		Op currentOp = operations.get(a.get(0).getClass()); //get the op for the class that has been passed in. 
		final Vector<U> vec = new Vector<U>();
		for(int i =0; i < a.size();++i)
		{
			vec.insertLast((U)currentOp.performOp(a.get(i), constant));
		}
		
		return vec;
	}	
	@SuppressWarnings("unchecked") //Need this for when performing the op
	public static <U,T> Vector<U> multSerialDifferent(T constant, Vector<U> a) throws VectorEmptyException
	{
		if(a == null || constant == null)
		{
			return null;
		}
		if(a.size() == 0)
		{
			throw new VectorEmptyException();
		}
		
		Op currentOp = operations.get(a.get(0).getClass()); //get the op for the class that has been passed in. 
		final Vector<U> vec = new Vector<U>();
		for(int i =0; i < a.size();++i)
		{
			vec.insertLast((U)currentOp.performOp(a.get(i), constant));
		}
		
		return vec;
		
	}
}

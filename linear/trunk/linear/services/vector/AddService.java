package linear.services.vector;

import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import linear.type.Type;
import linear.type.ops.MathOp;
import linear.type.ops.Op;
import linear.vector.Vector;
import linear.vector.VectorSizeIncorrect;

/**
 * This service is to provide support for the adding of vectors concurrently.  A vector
 * addition will be a blocking call as the service performs the addition until the service
 * finishes the addition at which point the service will return a new vector with the result
 * of the addition.  
 * @author Ryan
 *
 */
public class AddService {

	private static final ExecutorService threadPool = Executors.newCachedThreadPool();
	private static final HashMap<Type, HashMap<MathOp,Op>> operations = new HashMap<Type, Op>();
	
	/**
	 * This performs a vector addition of vector a and vector b concurrently.  While the computation is
	 * going on the add call is blocking, as soon as the computation is complete the call will return 
	 * with a new vector containing the result.  If either of the parameters are null, then null is returned.
	 * If the vectors do not have the same size an VectorSizeIncorrect is thrown stating so.
	 * @param a The left argument for the addition.
	 * @param b The right argument for the addition.
	 * @return The result of the vector addition..
	 */
	public static <U> Vector<U> addConcurrent(Vector<U> a, Vector<U> b,Type type) throws VectorSizeIncorrect
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
	
	public static <U> Vector<U> addSerial(Vector<U> a, Vector<U> b,Type type) throws VectorSizeIncorrect
	{
		if(a == null || b == null)
		{
			return null;
		}
		if(a.size() != b.size())
		{
			throw new VectorSizeIncorrect();
		}		
		final Vector<U> vec = new Vector<U>();
		for(int i =0; i < a.size();++i)
		{
			
		}
		
		return null;
	}
}

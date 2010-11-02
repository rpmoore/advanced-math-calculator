package linear.services.matrix;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import linear.matrix.Matrix;
import linear.matrix.MatrixSizeIncorrect;

/**
 * This service is to provide support for the adding of matrices concurrently.  A matrices
 * addition will be a blocking call as the service performs the addition until the service
 * finishes the addition at which point the service will return a new matrices with the result
 * of the addition.  
 * @author Ryan
 *
 */
public class AddService {

	private static final ExecutorService threadPool = Executors.newCachedThreadPool();
	
	/**
	 * This performs a matrix addition of matrices a and matrices b concurrently.  While the computation is
	 * going on the add call is blocking, as soon as the computation is complete the call will return 
	 * with a new matrices containing the result.  If either of the parameters are null, then null is returned.
	 * If the matrices do not match with the proper dimensions an MatrixSizeIncorrect is thrown stating so.
	 * @param a The left argument for the addition.
	 * @param b The right argument for the addition.
	 * @return The result of the vector addition..
	 */
	public static Matrix addConcurrent(Matrix a, Matrix b) throws MatrixSizeIncorrect
	{
		
		if(a == null || b == null)
		{
			return null;
		}
		if(a.width() != b.height())
		{
			throw new MatrixSizeIncorrect();
		}
		
		return null;
	}
	
	public static Matrix addSerial(Matrix a, Matrix b) throws MatrixSizeIncorrect
	{
		if(a == null || b == null)
		{
			return null;
		}
		if(a.width() != b.height())
		{
			throw new MatrixSizeIncorrect();
		}		
		
		return null;
	}
}

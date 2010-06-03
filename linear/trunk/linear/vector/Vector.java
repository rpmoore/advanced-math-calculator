package linear.vector;

/**
 * Matrices and Vectors can implement this method.  
 * If everything is a Vector assume the first one is Transpose.
 * So for example, if there are 2 Vectors A,B then a call to A.mult(B)
 * will be A Transpose B. 
 * @author Ryan
 *
 */
public interface Vector extends java.lang.Iterable  {
	public Vector add(Vector obj) throws VectorSizeIncorrect;
	public Vector mult(Vector obj) throws VectorSizeIncorrect;
	public Vector mult(double val);
	public Vector mult(int val);
	public Vector sub(Vector obj) throws VectorSizeIncorrect;
	public Vector div(int val);
	public Vector div(double val);
	public int size();
	public Object get(int i) throws IndexOutOfBoundsException;
}
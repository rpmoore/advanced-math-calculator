package linear.exceptions;

public class VectorSizeIncorrect extends VectorException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2520632031582447028L;

	public VectorSizeIncorrect()
	{
		super("The vectors are not the same size.");
	}
}

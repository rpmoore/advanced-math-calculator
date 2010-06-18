package linear.matrix;

public class MatrixSizeIncorrect extends MatrixException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2520632031582447028L;

	public MatrixSizeIncorrect()
	{
		super("The vectors are not the same size.");
	}
}

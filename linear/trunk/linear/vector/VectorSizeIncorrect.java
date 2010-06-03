package linear.vector;

public class VectorSizeIncorrect extends VectorException {
	public VectorSizeIncorrect()
	{
		super("The vectors are not the same size.");
	}
}

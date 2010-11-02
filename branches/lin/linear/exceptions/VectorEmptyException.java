package linear.exceptions;

public class VectorEmptyException extends VectorException {

	private static final long serialVersionUID = -2916101619623806954L;

	public VectorEmptyException() {
		super("A non-empty vector is required for the op.");
	}

}

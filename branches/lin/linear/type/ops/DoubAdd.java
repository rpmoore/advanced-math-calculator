package linear.type.ops;

public class DoubAdd implements Op<Double> {

	@Override
	public Double performOp(Double left, Double right) {
		return left+right;
	}
	
}

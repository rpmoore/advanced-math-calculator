package linear.type.ops;

public class IntAdd implements Op<Integer> {

	@Override
	public Integer performOp(Integer left, Integer right) {
		return left+right;
	}

}

package linear.type.ops;

public class IntMult implements Op<Integer> {

	@Override
	public Integer performOp(Integer left, Integer right){
		return left*right;
	}

}

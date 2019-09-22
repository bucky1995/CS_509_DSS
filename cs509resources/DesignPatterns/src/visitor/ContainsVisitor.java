package visitor;

public class ContainsVisitor implements IVisitor {

	final Number target;
	boolean result = false;
	
	public boolean getResult () {
		return result;
	}
	
	public ContainsVisitor (Number n) {
		target = n;
	}
	
	@Override
	public void visitNumber(Number n) {
		if (n == target) {
			result = true;
		}
	}

	@Override
	public void visitSet(Set s) {
		
	}

}

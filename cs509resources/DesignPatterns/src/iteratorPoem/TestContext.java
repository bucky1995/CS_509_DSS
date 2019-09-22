package iteratorPoem;

import java.util.Iterator;

import junit.framework.TestCase;

public class TestContext extends TestCase {
	public void testIterator() {
		String s = "'Tis thee,       myself, that for myself I praise,";

		Iterator<Context> it = new ContextIterator(s);

		while (it.hasNext()) {
			System.out.println (it.next());
		}
	}
}

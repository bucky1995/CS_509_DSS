import java.util.ArrayList;

public class Bowling {
	ArrayList<Frame> frames = new ArrayList<Frame>();
	boolean isGameOver = false;
	
	public Bowling () {
		for (int i = 0; i < 10; i++) {
			frames.add(new Frame());
		}
		frames.add(new Frame(true));  // bonus frame. Might not be needed
		frames.get(0).setActive(true);
	}
	
	public String scores() {
		int total = 0;
		String str = "";
		boolean stillActive = true;
		boolean firstActiveFound = false;
		for (int i = 0; i < 10; i++) {
			Frame f = frames.get(i);
			if (firstActiveFound && !f.isActive) { break; } // stop once done
			if (stillActive) {
				if (f.numRolls == 2) { // active but no rolls yet. Ignore!
					break;
				}
				total += f.score;
				str += total;
				if (f.isStrike) { str += "X"; }
				if (f.isSpare) { str += "/"; } 
				str +=  ",";
			}
			if (f.isActive) { firstActiveFound = true; } 
			
		}
		if (str.length() == 0) { return str; }
		str = str.substring(0, str.length()-1); // remove final ,
		
		// when done, return in brackets
		if (isGameOver) { return "[" + str + "]"; }
		return str;
	}
	
	// done once ALL ten frames are no longer active
	public void roll(int ball) {
		// has to be added to all the active frames
		
		for (int i = 0; i < 10; i++) {
			Frame f = frames.get(i);
			if (f.isActive) {
				if (f.addRoll(ball)) {
					frames.get(i+1).setActive(true);
					break;
				}
			}
		}

		// can only check DONE status here, after roll has been processed.
		for (int i = 0; i < 10; i++) {
			Frame f = frames.get(i);
			if (f.isActive) { isGameOver = false; return; }
		}
		isGameOver = true;
	}
	
	public int getScore() {
		int total = 0;
		for (Frame f : frames) {
			total += f.getScore();
		}
		
		return total;
	}
}

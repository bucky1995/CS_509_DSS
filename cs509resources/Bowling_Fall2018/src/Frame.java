
public class Frame {
	Frame previous;
	int score;
	boolean isActive;
	int numBonusRolls;
	int numRolls = 2;
	boolean activateNext = true;
	boolean isStrike = false;
	boolean isSpare = false;
	boolean isBonus = false; // is this the 11th "Bonus" frame, in case there is a strike/spare thrown on final toss?
	public Frame(boolean isBonus) {
		this();
		isBonus = true;
	}
	
	public Frame () {
		isBonus = false;
		isActive = false;
		numBonusRolls = 0;
	}
	
	// return TRUE IF time to activate next frame BUT ONLY ONCE
	// this matters for STRIKES
	public boolean addRoll(int r) {
		numRolls--;
		isActive = true;
		score += r;
		if (r == 10 && numRolls == 1) {  // strike on first throw
			numBonusRolls = 2;
			isStrike = true;
			activateNext = false;
			return true;
		} else if (!isStrike && score == 10 && numRolls == 0) { // spare on second throw BUT NOT AFTER A STRIKE!
			numBonusRolls = 1;
			isSpare = true;
			activateNext = false;
			return true;
		} else if (numBonusRolls > 0) {
			numBonusRolls--;
			if (numBonusRolls == 0) { isActive = false; } // we have completed our bonus rolls
			activateNext = false;   // we are already in bonus situation. No need to activate next one
			return false;
		}
		
		// if we had any 
		// if numRolls is ZERO
		if (numRolls <= 0) { isActive = false; return activateNext; }
		return false;		
	}

	public int getScore() {
		return score;
	}

	public void setActive(boolean b) {
		isActive = b;
	}
}

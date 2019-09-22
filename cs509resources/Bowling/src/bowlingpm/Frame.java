package bowlingpm;

public class Frame {
	int numRolls;
	int rolls[] = new int[3];
	
	int numBonusRolls;
	
	int maxRolls;
	
	Frame prevFrame;
	
	public Frame (Frame prevFrame) {
		this(2, prevFrame);
	}
	
	public boolean isCompleted() {
		return numRolls >= maxRolls;
	}
	
	public Frame(int numRollsAllowed, Frame prevFrame) {
		this.maxRolls = numRollsAllowed;
		this.prevFrame = prevFrame;
	}
	
	public boolean isSpare () {
		return rolls[0] + rolls[1] == 10;
	}
	
	public boolean isStrike () {
		return rolls[0] ==10;
	}
	
	void roll (int pins) {
		if (prevFrame != null) {
			if (prevFrame.numBonusRolls > 0) {
				prevFrame.roll(pins);
				prevFrame.numBonusRolls--;
			}
		}
		
		rolls[numRolls++] = pins;
		if (isStrike()) {
			numBonusRolls = 2;
		} else if (isSpare()) {
			numBonusRolls = 1;
		}
	}
	
	int score() {
		return rolls[0] + rolls[1] + rolls[2];
	}
	
}

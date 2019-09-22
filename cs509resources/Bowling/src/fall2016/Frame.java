package fall2016;

import bowling.IFrame;

public class Frame implements IFrame {
	int totalRolls = 0;
	int rolls[] = new int[3];
	
	
	public void roll(int pins) {
		rolls[totalRolls++] = pins;
	}
	
	@Override
	public boolean isComplete() {
		if (totalRolls == 2 && (rolls[0] + rolls[1] < 10)) {
			return true;
		}
		if (isStrike()) {
			return totalRolls == 3;
		}
		if (isSpare()) {
			return totalRolls == 3;
		}
		
		// not complete
		return false;
	}

	@Override
	public boolean isStrike() {
		return rolls[0] == 10;
	}

	@Override
	public boolean isSpare() {
		if (rolls[0] == 10) { return false; }
		
		return rolls[0] + rolls[1] == 10;
	}

	@Override
	public int getRoll(int n) {
		return rolls[n];
	}

	@Override
	public int getNumRolls() {
		return totalRolls;
	}

	@Override
	public int score() {
		return rolls[0] + rolls[1] + rolls[2];
	}
	
	public String toString() {
		String type = "";
		if (isStrike()) { type = "Strike"; }
		if (isSpare()) { type = "Spare"; }
		return "[" + rolls[0] + "," + rolls[1] + "," + rolls[2] + "]" + type;
	}

}

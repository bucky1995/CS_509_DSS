package bowling2008;

class Frame {
	int throwNumber;     /** Throw number: Start counting from 1. */
	int pinTotal;        /** Total number of pins for this frame to score. */
	int bonusTotal;      /** Total number of pins bowled extra. */
	int bonusThrows;

	Frame () {
		throwNumber = 1;
		pinTotal = 0;
		bonusTotal = 0;
	}

	int score() {
		return pinTotal + bonusTotal;
	}

	boolean  isDone() {
		if (throwNumber <= 2 && pinTotal < 10) { return false; }      // still waiting for up to two throws
		if (pinTotal < 10) { return true; }          // normal frame is now complete.
		if (bonusThrows > 0) { return false; }       // waiting for bonus throws.
		return true;                                 // done
	}

	boolean isBonus() { return isStrike() || isSpare(); }
	boolean isStrike() { return pinTotal == 10 && throwNumber == 2; }
	boolean isSpare()  { return pinTotal == 10 && throwNumber == 3; }


	// add number of pins to this frame, and advance throw number. 
	//	add number of pins to this frame, and advance throw number. Once throw
	// advances to bonus, only add points to the bonus total.
	void addPins (int p) {
		if (isBonus()) {
			if (--bonusThrows < 0) {
				throw new RuntimeException ("Frame received too many bonus pins");
			}

			bonusTotal += p;
		} else {
			pinTotal += p;
			throwNumber++;

			if (isStrike()) {
				bonusThrows = 2;
			} else if (isSpare()) {
				bonusThrows = 1;
			}
		}
	}
}
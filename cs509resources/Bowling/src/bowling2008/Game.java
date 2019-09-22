package bowling2008;

public class Game {

	public static final int NUM_FRAMES = 10;     // # of frames

	int currentIndex;                            // 0 <= n < 10
	int previousIndex;                           // 0 <= n < 9 OR sentinel frame of 10

	Frame []frames;     // 0-9 store valid ones; 10 is sentinel     

	public Game () { 
		frames = new Frame [NUM_FRAMES+1];
		for (int i = 0; i < frames.length; i++) {
			frames[i] = new Frame();
		}
		currentIndex = 0;
		previousIndex = NUM_FRAMES;
	}
	/**
	 * Record that on the "next" throw of the game, this was the number
	 * of pins that were knocked over.
	 */
	public void addThrow (int p) {
		// if previous was a bonus and not done, add to previous frame
		if (frames[previousIndex].isBonus() && !frames[previousIndex].isDone()) {
			for (int i = previousIndex; i >= 0; i--) {
				if (!frames[i].isDone()) {
					frames[i].addPins(p);
				}
			}
		}

		// add to current frame. If in doing so, currentFrame becomes a bonus, 
		// then we advance the previousIndex/currentIndex references.
		frames[currentIndex].addPins(p);
		if (frames[currentIndex].isBonus() || frames[currentIndex].isDone()) {
			previousIndex = currentIndex;
			currentIndex++;
		}
	}

	/** Return the score of the nth frame, where 0 <= n < 10. */
	public int score (int n) {
		if (n < 0 || n >= NUM_FRAMES) { throw new RuntimeException ("invalid frame number"); }

		int sum = 0;
		for (int i = 0; i <= n; i++) {   // note <= for accurate reporting
			sum += frames[i].score();
		} 

		return sum;
	}  

	/** Return the score of the current frame. */
	public int score () {
		if (currentIndex == NUM_FRAMES) { return score (currentIndex-1); }

		return score(currentIndex);   // nice reuse of score(n) function.
	}
}
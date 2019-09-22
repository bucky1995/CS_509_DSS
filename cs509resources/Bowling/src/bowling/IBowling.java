package bowling;

/**
 * Standard interface to ensure can work with any implementation.
 * 
 * Goal is to allow client to retrieve the end-of-game score (or current score)
 * but also frame by frame results. Note that the number of frames returned is
 * based on the number of pins rolled (i.e., it won't always be 10).
 */
public interface IBowling extends Iterable<IFrame> {
	/** 
	 * Roll a set of pins, if the game is not over.
	 * 
	 * If the current pins is not a valid roll then a RunTimeException should be thrown.
	 * Note that the default implementation does not do this.
	 */
	void roll(int pins);
	
	/** Determine if game is over. */
	boolean isGameOver();
	
	/** 
	 * Return score at the end of the game.
	 * 
	 * If called while a game is in progress, it must return the score that would result
	 * if player rolled zero (0) for all remaining rolls in the game.
	 */
	int score (); 
	
	/** Return 0 for 1st frame and 9 for final frame. When returned >= 10, game over*/
	int currentFrame(); 
}

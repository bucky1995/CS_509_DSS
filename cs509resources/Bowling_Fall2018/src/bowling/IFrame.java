package bowling;

/**
 * Generic interface to extract meaningful information about a frame.
 * 
 * Frames are numbered from 0 to 9.
 */
public interface IFrame {
	/** Only show scores for complete frames. */
	boolean isComplete();
	
	/** Is current frame a strike. */
	boolean isStrike();
	
	/** Is current frame a spare. */
	boolean isSpare();
	
	/** 
	 * Return the nth roll for this frame.
	 * 
	 * The value of n is indexed from 0 and must be smaller than {@link #getNumRolls()}.
	 */
	int getRoll(int n);
	
	/** How many rolls in frame. */
	int getNumRolls();
	
	/** Score for the frame (if complete). */
	int score();
}

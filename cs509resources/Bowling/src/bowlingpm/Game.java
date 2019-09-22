package bowlingpm;

import iterator.ArrayIterator;

import java.util.Iterator;

import bowling.IBowling;
import bowling.IFrame;

/**
 * IMPORTANT CONCEPTS
 * 
 * 1. Rolls are recorded with the game
 * 2. Get score at end of game via score method
 * 3. Game is composed of collection of TEN Frames
 * 4. Each frame has 0..2/3 rolls
 * 5. Rolling ten is a strike
 * 6. Previous Frame.... Current Frame.... nExt frame.... LastFrame
 * 7. MAJOR ISSUE: HOW TO PREVENT INVALID ROLLS
 */
public class Game implements IBowling {
	Frame [] frames = new Frame[MAXFRAME];
	int currentFrame = 0;
	
	public static final int MAXFRAME = 10;
	
	public Game () {
		Frame prev = null;
		for (int i =0 ; i < MAXFRAME; i++) {
			frames[i] = new Frame(prev);
			prev = frames[i];
		}
	}
	
	/** Add a roll to the game. */
	public void roll(int pins) {
		// find last uncompleted frame...
		
		frames[currentFrame].roll(pins);
		
		
		if (frames[currentFrame].isCompleted()) {
			currentFrame++;
		}
		
	}
	
	/** Return the score of the game. */
	public int score() {
		int score = 0;
		
		for (int i = 0; i < MAXFRAME; i++) {
			score += frames[i].score();
		}
		
		return score;
	}

	@Override
	public Iterator<IFrame> iterator() {
		return new ArrayIterator(frames);
	}

	@Override
	public boolean isGameOver() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int currentFrame() {
		// TODO Auto-generated method stub
		return 0;
	}
}

package fall2016;

import java.util.Iterator;

import bowling.IBowling;
import bowling.IFrame;
import iterator.ArrayIterator;

public class BaseGame implements IBowling {
	
	int currentIndex = 0;
	Frame[] frames;
	
	public BaseGame () {
		frames = new Frame[10];
		for (int i = 0; i < frames.length; i++) {
			frames[i] = new Frame();
		}
	}
	
	@Override
	public Iterator<IFrame> iterator() {
		return new ArrayIterator(frames);
	}

	@Override
	public void roll(int pins) {
		if (isGameOver()) {
			throw new RuntimeException ("Game is over");
		}
		if (currentIndex < 10) {
			frames[currentIndex].roll(pins);
		} else {
			// deal with mop up bonus rolls
			frames[currentIndex-1].roll(pins);
			return;
		}
		
		// how to handle BONUS rolls for previous frame
		if (currentIndex > 0) {
			if (!frames[currentIndex-1].isComplete()) {
				frames[currentIndex-1].roll(pins);
				
				// But wait! If that frame isn't complete, must pass through
				// this case happens when three strikes in a row
				int prevFrame = currentIndex-2;
				while (prevFrame > 0 && !frames[prevFrame].isComplete()) {
					frames[prevFrame].roll(pins);
					prevFrame--;
				}
			}
		}
		
		if (frames[currentIndex].isComplete() || frames[currentIndex].isStrike() || frames[currentIndex].isSpare()) {
			currentIndex++;
		}
	}

	@Override
	public boolean isGameOver() {
		return (currentIndex == 10) && frames[currentIndex-1].isComplete();
	}

	@Override
	public int score() {
		int score = 0;
		for (int i = 0; i < frames.length; i++) {
			score += frames[i].score();
		}
		
		return score;
	}

	@Override
	public int currentFrame() {
		return currentIndex;
	}

}

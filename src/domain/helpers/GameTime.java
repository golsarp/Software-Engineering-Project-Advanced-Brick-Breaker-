package domain.helpers;

import java.time.Duration;
import java.time.Instant;
import static java.lang.Math.toIntExact;

public class GameTime {

	private Instant start;
	private Instant finish;
	private int CurrentTime;

	public GameTime() {
		CurrentTime = 0;
	}

	public int getCurrentTime() {
		return CurrentTime;
	}

	public void setCurrentTime(int currentTime) {
		CurrentTime = currentTime;
	}

	public void startTime() {
		start = Instant.now();
	}

	public void stopTime() {
		finish = Instant.now();
	}

	public int measureBetween() {
		long timeElapsed = Duration.between(start, finish).toMillis() / 1000;
		int timeElapsedToInt = toIntExact(timeElapsed);
		return timeElapsedToInt;
	}

	public void addCurrentTime() {
		setCurrentTime(measureBetween() + getCurrentTime());
	}

}

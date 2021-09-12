package util;

public class Score {
	private int _score;
	public Score() {
		_score = 0;
	}

	public void incrementScore() {
		_score++;
	}

	public void incrementScore(int value) {
		_score += value;
	}

	public int getScore() {
		return _score;
	}


}

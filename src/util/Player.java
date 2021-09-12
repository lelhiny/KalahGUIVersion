package util;
import java.util.ArrayList; 

import com.qualitascorpus.testsupport.IO;

public abstract class Player {
	protected int _num;
	protected Score _score;
	protected ArrayList<House> myHouses;
	public abstract int chooseAHouse(ArrayList<Player> players);
	protected IO io;
	public Player(int n, ArrayList<House> h,Score s) {
		_num = n;
		_score = s;
		myHouses = h;
	}
	public void incrementScore() {
		_score.incrementScore();
	}
	public void incrementScore(int value) {
		_score.incrementScore(value);
	}
	public int getScore() {
		return _score.getScore();
	}
	public int getNumber() {
		return _num;
	}
	public ArrayList<House> getHouses(){
		return myHouses;
	}
	public int getPlayerIndex() {
		return _num-1;
	}
}

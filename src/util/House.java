package util;


/**
 * This class represents the house in khalah, it has two attributes a num (representing house number) and the seed (number of seeds 
 * in the house)
 */
public class House {
	private int _num;
	private int _seed;
	public House(int n) {
		_num = n;
		_seed = 4;
	}
	public void setZero() {
		// Replace what's below with your implementation
		_seed = 0;
	}
	public int getSeed(){
		return _seed;
	} 
	public int getNum(){
		return _num;
	} 
	public void incrementSeed() {
		_seed++;
	}
}

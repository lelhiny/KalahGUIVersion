package util;

import java.util.ArrayList;

import com.qualitascorpus.testsupport.IO;

public class VerticalDisplay extends Display{
	private Player playerOne;
	private Player playerTwo;
	private ArrayList<House> P1Houses;
	private ArrayList<House> P2Houses;
	public VerticalDisplay(IO io) {
		_io = io;
	}
	public void displayKalah(ArrayList<Player> players) {
		initializeParameters(players);
		printKalah();
		
	}
	private void initializeParameters(ArrayList<Player> players) {
		playerOne = (Player) players.get(Constants.P1_INDEX);
		playerTwo = (Player) players.get(Constants.P2_INDEX);
		P1Houses = playerOne.getHouses();
		P2Houses = playerTwo.getHouses();
	}
	private void printKalah() {
		_io.println("+---------------+");
		_io.println("|       | P"+ playerTwo.getNumber()+ " "+ _decimalFormat.formatDigits( playerTwo.getScore()) +" |");
		_io.println("+-------+-------+");
		for(int i = 0; i < Constants.MAX_NUM_HOUSES; i++)
		{
			_io.println("| "+P1Houses.get(i).getNum()+"["+ _decimalFormat.formatDigits(P1Houses.get(i).getSeed())+"] | "+
		     P2Houses.get(Constants.MAX_NUM_HOUSES-i-1).getNum()+"["+
			_decimalFormat.formatDigits(P2Houses.get(Constants.MAX_NUM_HOUSES-i-1).getSeed())+"] |");
		}
		_io.println("+-------+-------+");
		_io.println("| P"+playerOne.getNumber()+" "+ _decimalFormat.formatDigits( playerOne.getScore())+" |       |");
		_io.println("+---------------+");
	}
}

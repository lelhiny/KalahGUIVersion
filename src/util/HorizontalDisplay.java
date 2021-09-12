package util;

import java.util.ArrayList;

import com.qualitascorpus.testsupport.IO;

public class HorizontalDisplay extends Display {
	private Player playerOne;
	private Player playerTwo;
	String P2HouseStr;
	String P1HouseStr;
	String firstRowStr;
	String secondRowStr;
	public HorizontalDisplay(IO io) {
		_io = io;
	}
	public void displayKalah(ArrayList<Player> players) {
		initializeParameters(players);
		printKalah();
	}
	private void initializeParameters(ArrayList<Player> players) {
		playerOne = (Player) players.get(Constants.P1_INDEX);
		playerTwo = (Player) players.get(Constants.P2_INDEX);
		P2HouseStr = createHousesStrDesc(playerTwo.getHouses());
		P1HouseStr = createHousesStrAsc(playerOne.getHouses());
		firstRowStr = "| P"+ playerTwo.getNumber()+ P2HouseStr+" ";
		secondRowStr = "| ";
		secondRowStr += _decimalFormat.formatDigits( playerTwo.getScore()) ;
		secondRowStr += P1HouseStr +" P"+playerOne.getNumber()+" |";
		firstRowStr +=  _decimalFormat.formatDigits(playerOne.getScore());
		firstRowStr += " |";
		
	}
	private void printKalah() {
		_io.println("+----+-------+-------+-------+-------+-------+-------+----+");
		_io.println(firstRowStr);
		_io.println("|    |-------+-------+-------+-------+-------+-------|    |");
		_io.println(secondRowStr);
		_io.println("+----+-------+-------+-------+-------+-------+-------+----+");
	}
	//Method used to convert houses array to String  required to display Kalah Game
	private String createHousesStrAsc (ArrayList<House> h) {
		int counter = h.size();
		String res ="";
		for (int i = 0; i < counter; i++) {
			House house = (House)h.get(i); 
			res += " | ";
			res+= house.getNum()+ "[";
			res+= _decimalFormat.formatDigits( house.getSeed());
			res+="]";
		}
		res+=" |";
		return res;   

	}
	//Method used to convert houses array to String  required to display Kalah Game
	private String createHousesStrDesc (ArrayList<House> h) {
		int counter = h.size();
		String res ="";
		for (int i = counter-1; i >=0; i--) {
			House house = (House)h.get(i);
			res += " | ";
			res += house.getNum()+ "[";
			res+= _decimalFormat.formatDigits( house.getSeed());
			res+= "]";
		}
		res+=" |";
		return res; 
	}

}

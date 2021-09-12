package kalah;

import util.*;
import com.qualitascorpus.testsupport.IO;
import com.qualitascorpus.testsupport.MockIO;
import java.util.ArrayList;

/**
 * Play a game of Kalah.
 * @param io There to get input from and direct output to
 * @param vertical If true, then play the game between two humans but orient the board vertically.
 * If it is false AND bmf is false then orient the board horizontally.
 * @param bmf If vertical if false AND this is true then play the game where the second player
 * (P2) is the "best first move" robot.
 */
public class Kalah {

	private int _currentPlayerIndex;
	private  ArrayList<Player> _players;
	private Display _display;
	private Player _currentPlayer;
	private Player _otherPlayer = null; 
	private int winnerScore = -1;
	private int winnerIndex = 0;
	private boolean tie = false;
	private boolean gui;
	private IO io;
	private ButtonSample bs;
	public static void main(String[] args) {
		new Kalah().play(new MockIO(), false, true, true);
	}
	//Play method the game starting point 
	public void play(IO io, boolean vertical, boolean bmf, boolean gui) {
		int suppliedInput = 0;
		InitializeFields(io, vertical, bmf, gui);
		while(!checkEndOfGame() && !checkForQuit(suppliedInput)) {
			_display.displayKalah(_players);
			suppliedInput = _currentPlayer.chooseAHouse(_players);
			playMoveIfAny(suppliedInput);
			_currentPlayer = (Player)_players.get(_currentPlayerIndex);
		}
		if(checkEndOfGame()) {
			endGame();
			
		}
	}
	private void InitializeFields(IO io, boolean vertical, boolean bmf, boolean gui) {
		ArrayList<House> P1Houses = initializeHouse(Constants.MAX_NUM_HOUSES);
		ArrayList<House> P2Houses = initializeHouse(Constants.MAX_NUM_HOUSES);

		Score scoreOne = new Score();
		Score scoreTwo = new Score();

		PlayerFactory playerFactory = new PlayerFactoryImpl();

		Player playerOne = playerFactory.makePlayer(io, Constants.P1_NUM, P1Houses, scoreOne, false);
		Player playerTwo = playerFactory.makePlayer(io, Constants.P2_NUM, P2Houses, scoreTwo, bmf);

		_players= new ArrayList<Player>();
		_players.add(playerOne);
		_players.add(playerTwo);

		_currentPlayerIndex = 0;
		_currentPlayer = (Player) _players.get(Constants.P1_INDEX);
		_otherPlayer = (Player) _players.get(Constants.P2_INDEX);
		this.io = io;
		this.gui = gui;

		DisplayFactory displayFactory = new DisplayFactoryImpl();
		_display = displayFactory.makeDisplay(io, vertical, gui);
		
	}
	private ArrayList<House> initializeHouse(int size){
		ArrayList<House> h = new ArrayList<House>();
		for (int i = 0; i < Constants.MAX_NUM_HOUSES; i++)
		{
			House theHouse = new House(i+1);
			h.add(theHouse);
		}
		return h;
	}
	private void endGame() {
		displayWinner();
		_display.displayKalah(_players);
		displayGameOver();
		_display.displayKalah(_players);
		//displayWinner();
	}

	private void playMoveIfAny(int suppliedInput) {
		boolean success = false;
		if(!checkForQuit(suppliedInput)) {
			success = playTurn(suppliedInput);
		} else {
			displayGameOver();
			_display.displayKalah(_players);
		}
		if(!success && !checkForQuit(suppliedInput))
			displayMoveAgainMsg();
	}
	private void displayWinner() {
		displayScore();
		if(!tie)
		{
			io.println(Message.PLAYER_MSG +_players.get(winnerIndex).getNumber()+ Message.WINNER);
			GUIDisplay.winnerMessage += Message.PLAYER_MSG +_players.get(winnerIndex).getNumber()+ Message.WINNER + " <br />";
		}
		else
		{
			io.println(Message.TIE);
			GUIDisplay.winnerMessage += Message.TIE + " <br />";
		}
	}
	private void displayScore()
	{
		for(int i = 0; i < _players.size(); i++)
		{
			Player player = _players.get(i);
			int score = calculateScore(player);
			io.println(Message.SCORE_MSG_1 + player.getNumber() + Message.SCORE_MSG_2 + score);
			GUIDisplay.winnerMessage += Message.SCORE_MSG_1 + player.getNumber() + Message.SCORE_MSG_2 + score + " <br />";
			checkForWinner(score, i);

		}
	}
	private void displayMoveAgainMsg() {
		io.println(Message.EMPTY_HOUSE);
	}
	private void displayGameOver() {
		io.println(Message.GAME_OVER);
	}
	private boolean checkForQuit(int input) {
		return input == Constants.QUIT;
	}
	private void checkForWinner(int score, int index) {
		if(winnerScore < score)
		{
			winnerScore = score;
			winnerIndex = index;
		}
		else if(winnerScore == score)
			tie = true;
	}
	private boolean checkEndOfGame() {
		ArrayList<House> h1 = _currentPlayer.getHouses();
		boolean allHousesEmpty = true;
		for(int i = 0; i < h1.size() && allHousesEmpty; i++) {
			allHousesEmpty = checkIfHouseEmpty(h1.get(i));
		}
		return allHousesEmpty;
	}
	private boolean checkIfHouseEmpty (House h) {
		if(h.getSeed() != 0) 
			return false;
		return true;
	}

	private int calculateScore(Player player) {
		int score = 0;
		ArrayList<House> houses = player.getHouses();
		for (int i = 0; i < houses.size(); i++)
			score += houses.get(i).getSeed();
		score += player.getScore();
		return score;
	}

	private boolean playTurn(int choice) {
		ArrayList<House> houses = _currentPlayer.getHouses();
		House h = (House) houses.get(choice - 1);
		if(h.getSeed() > 0) {
			int seeds = h.getSeed() + choice - 1;
			h.setZero();
			int i = choice;
			updateOtherPlayer();
			i = firstWrap (i,houses, seeds);
			i = playOtherWraps(houses, i, seeds);
			return true;

		}
		return false;
	} 
	private void updateOtherPlayer () {
		if(_currentPlayerIndex == Constants.P1_INDEX)
			_otherPlayer = (Player) _players.get(Constants.P2_INDEX);
		else
			_otherPlayer = (Player) _players.get(Constants.P1_INDEX);
	}
	private int playOtherWraps(ArrayList<House>houses, int plantedSeeds, int seeds) {
		int i = plantedSeeds;
		while(i <= seeds) {
			i = secondWrap(i,houses,seeds);
		}
		return i;
	}
	//Method used to perform the first wrap of the game (difference player starts from any house not necessarily first one 
	private int firstWrap (int houseNum, ArrayList<House> houses, int totalSeeds) {
		int plantedSeeds = plantSeedsPHouses1Wrap(houseNum , totalSeeds, houses);
		if(plantedSeeds <= totalSeeds) { //seeds in player' store
			_currentPlayer.incrementScore();
			plantedSeeds++;
		}
		if(plantedSeeds <= totalSeeds) { //seeds in opposite player houses
			ArrayList<House> otherPlayerHouses = _otherPlayer.getHouses();
			plantedSeeds = plantSeedsOtherPHouses(plantedSeeds, totalSeeds, otherPlayerHouses);
		}
		return plantedSeeds;
	}
	private int plantSeedsPHouses1Wrap(int plantedSeeds, int seeds, ArrayList<House> houses) {
		int i = plantedSeeds;
		for(; i <= seeds && i < houses.size(); i++) { 
			House temp = (House) houses.get(i);
			plantSeedInHouse(temp, seeds);
		}
		return i;
	}

	private void plantSeedInHouse(House h, int seeds) {
		int houseIndex = h.getNum() - 1;
		boolean capture = false;
		if( checkIfLastSeed(houseIndex, seeds)  && h.getSeed() > 0) { //last seed switch turn
			_currentPlayerIndex = updateCurrentPlayerIndex(_currentPlayerIndex);

		} else if(houseIndex == seeds && h.getSeed() == 0) {
			capture = checkForCapture(houseIndex);
			_currentPlayerIndex = updateCurrentPlayerIndex(_currentPlayerIndex);
		}
		if(!capture)
			h.incrementSeed();
	}
	private void plantSeedInHouse(House h,int plantedSeeds, int seeds) {
		int houseIndex = h.getNum() - 1;
		boolean capture = false;
		if( checkIfLastSeed(plantedSeeds,seeds)  && h.getSeed() > 0) { //last seed switch turn
			_currentPlayerIndex = updateCurrentPlayerIndex(_currentPlayerIndex);

		} else if(plantedSeeds == seeds && h.getSeed() == 0) {
			capture = checkForCapture(houseIndex);
			_currentPlayerIndex = updateCurrentPlayerIndex(_currentPlayerIndex);
		}
		if(!capture)
			h.incrementSeed();
	}
	private boolean checkForCapture (int houseIndex) {
		boolean capture = false;
		ArrayList<House> otherPlayerHouses = _otherPlayer.getHouses();
		House otherHouse = (House) otherPlayerHouses.get(getOppositeHouseIndex(houseIndex));
		if(otherHouse.getSeed() != 0) {
			performCapture(otherHouse);
			capture = true;
		}
		return capture;
	}
	private int getOppositeHouseIndex (int index) {
		return Constants.MAX_NUM_HOUSES - index - 1;
	}
	public void performCapture(House house) {
		int otherSeed = house.getSeed();
		house.setZero();
		_currentPlayer.incrementScore(otherSeed + 1);
	}
	private int plantSeedsOtherPHouses(int plantedSeeds, int totalSeeds, ArrayList<House> otherPlayerHouses) {
		int i = plantedSeeds;
		for(int j = 0;i <= totalSeeds && j <otherPlayerHouses.size(); i++,j++) {
			House temp = (House) otherPlayerHouses.get(j);
			if(checkIfLastSeed(i, totalSeeds)) { 
				_currentPlayerIndex = updateCurrentPlayerIndex(_currentPlayerIndex);
			}
			temp.incrementSeed();
		}
		return i;
	}
	//Method used to perform all consecutive  wraps
	private int secondWrap (int plantedSeeds, ArrayList<House> houses, int seeds) {
		ArrayList<House> playerHouses = _currentPlayer.getHouses();
		plantedSeeds = plantSeedsPHouses2Wrap(plantedSeeds, seeds, playerHouses);
		if(plantedSeeds <= seeds) { //seeds in player' store
			_currentPlayer.incrementScore();
			plantedSeeds++;
		}
		if(plantedSeeds <= seeds) { //seeds in opposite player houses
			ArrayList<House> otherPlayerHouses = _otherPlayer.getHouses();
			plantedSeeds = plantSeedsOtherPHouses2Wrap(plantedSeeds, seeds, otherPlayerHouses);
		}
		return plantedSeeds;
	}
	int plantSeedsPHouses2Wrap (int plantedSeeds, int totalSeeds, ArrayList<House> playerHouses) {
		int i = plantedSeeds;
		for(int j = 0;i <= totalSeeds && j <playerHouses.size(); i++,j++) {
			House temp = (House) playerHouses.get(j);
			plantSeedInHouse(temp, i, totalSeeds);
		}
		return i;
	}
	int plantSeedsOtherPHouses2Wrap (int plantedSeeds, int seeds, ArrayList<House> otherPlayerHouses) {
		int i = plantedSeeds;
		for(int j = 0;i <= seeds && j <otherPlayerHouses.size(); i++,j++) {
			House temp = (House) otherPlayerHouses.get(j);
			if(checkIfLastSeed(i, seeds)) {
				_currentPlayerIndex = updateCurrentPlayerIndex(_currentPlayerIndex);
			}
			temp.incrementSeed();
		}
		return i;
	}
	boolean checkIfLastSeed(int plantedSeeds, int seeds) {
		return plantedSeeds == seeds;
	}
	int updateCurrentPlayerIndex(int index) {
		if(index == Constants.P1_INDEX)
			index = Constants.P2_INDEX;
		else
			index = Constants.P1_INDEX;
		return index;
	}



}




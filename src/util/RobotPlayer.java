package util;

import java.util.ArrayList;

import com.qualitascorpus.testsupport.IO;

import kalah.GUIDisplay;

public class RobotPlayer extends Player {
	public RobotPlayer(IO io,int n, ArrayList<House> h,Score s){
		super(n, h, s);
		this.io = io;
	}
	public int chooseAHouse(ArrayList<Player> players) {
		int choice;
		choice = findRobotMove(players);
		return choice;
	}
	private int findRobotMove(ArrayList<Player> players) {
		ArrayList<House> robotHouses = players.get(getPlayerIndex()).getHouses();
		ArrayList<House> p1Houses = players.get(getPlayerIndex() - 1).getHouses();
		int robotChoice = findRobotAdditionalMove(robotHouses);
		if(robotChoice == Constants.NO_BEST_MOVE) {
			robotChoice = findRobotCaptureMove(robotHouses,  p1Houses);
			robotChoice = findRobotMoveIfNotFound(robotChoice, robotHouses);

		} else {
			displayRobotExtraMoveMsg(robotChoice);
		}
		return robotChoice;
	}
	private int findRobotMoveIfNotFound(int robotChoice, ArrayList<House> robotHouses) {
		if(robotChoice == Constants.NO_BEST_MOVE) {
			robotChoice = findRobotFirstLegalMove(robotHouses);
			displayRobotMoveMsg(robotChoice);

		} else {
			displayRobotCaptureMsg(robotChoice);
		}
		return robotChoice;
	}
	private void displayRobotMoveMsg(int choice) {
		io.println(Message.ROBOT_MSG_1_1 + choice + Message.ROBOT_MSG_1_2);
		GUIDisplay.GUIMessage += Message.ROBOT_MSG_1_1 + choice + Message.ROBOT_MSG_1_2 +  " <br />";  //To support GUI to be updated
	}
	private void displayRobotCaptureMsg(int choice) {
		io.println(Message.ROBOT_MSG_2_1 + choice + Message.ROBOT_MSG_2_2);
		GUIDisplay.GUIMessage += Message.ROBOT_MSG_2_1 + choice + Message.ROBOT_MSG_2_2 +  " <br />"; //To support GUI to be updated
	}
	private void displayRobotExtraMoveMsg(int choice) {
		io.println(Message.ROBOT_MSG_3_1 + choice + Message.ROBOT_MSG_3_2);
		GUIDisplay.GUIMessage += Message.ROBOT_MSG_3_1 + choice + Message.ROBOT_MSG_3_2 +  " <br />"; //To support GUI to be updated
	}

	private int findRobotAdditionalMove(ArrayList<House> P2Houses) {
		int size = P2Houses.size();
		for(int i = 0; i < size; i++) {
			House house = P2Houses.get(i);
			if(house.getSeed() == size-i) {
				return i+1;
			}
		}
		return Constants.NO_BEST_MOVE; 

	}
	private int findRobotCaptureMove(ArrayList<House> P2Houses, ArrayList<House> P1Houses) { //More than two level of indentation requires more refactoring
		int size = P2Houses.size();
		for(int i = 0; i < size; i++) {
			House house = P2Houses.get(i);
			if(house.getSeed() > 0) {
				int seeds = house.getSeed(); 
				for(int j=i+1; j < size; j++) {
					House lastHouse = P2Houses.get(j);
					seeds--;
					House otherHouse = P1Houses.get(getOppositeHouseIndex(j));
					if(checkForCapturefirstWrap(seeds, lastHouse, otherHouse)) {
						return i+1;
					}

				}
				seeds = seedsLeftAfterPass(seeds);
				if(seeds > 0) {
					for(int k = 0; k < i; k++) {
						House lastHouse = P2Houses.get(k);
						seeds--;
						House otherHouse = P1Houses.get(getOppositeHouseIndex(k));
						if(checkForCapturesecondWrap(seeds, lastHouse, otherHouse)) {
							return i+1;
						}

					}

				}
			}
		}
		return Constants.NO_BEST_MOVE; 
	}
	private boolean checkForCapturefirstWrap(int seeds, House lastHouse, House otherHouse) {
		return seeds == 0 && lastHouse.getSeed() == 0 && otherHouse.getSeed() > 0;
	}
	private boolean checkForCapturesecondWrap(int seeds, House lastHouse, House otherHouse) {
		return seeds == 0 && lastHouse.getSeed() == 0 && seedsPlantedInMove(otherHouse) > 0;
	}
	private int seedsPlantedInMove (House h) {
		return h.getSeed() + 1;	
	}
	private int getOppositeHouseIndex (int index) {
		return Constants.MAX_NUM_HOUSES - index - 1;
	}
	private int seedsLeftAfterPass(int seeds) {
		return seeds - 7;
	}
	private int findRobotFirstLegalMove(ArrayList<House> P2Houses) {
		int moveIndex = Constants.NO_BEST_MOVE;
		int size = P2Houses.size();
		for(int i = 0; i < size && moveIndex == Constants.NO_BEST_MOVE; i++) {
			House house = P2Houses.get(i);
			moveIndex = checkForSeeds(house);
		}
		return moveIndex; 	
	}
	private int checkForSeeds(House h) {
		int index = Constants.NO_BEST_MOVE;
		if(h.getSeed() > 0) {
			index = h.getNum();
		}
		return index;
	}

}

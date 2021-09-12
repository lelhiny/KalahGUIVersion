package util;

import java.util.ArrayList;

import com.qualitascorpus.testsupport.IO;

public class PlayerFactoryImpl implements PlayerFactory{
	public Player makePlayer(IO io,int n, ArrayList<House> h,Score s, boolean bmf) {
		Player player;
		if(!bmf) {
			player = new HumanPlayer(io, n, h, s);
		} else {
			player = new RobotPlayer(io, n, h, s);
		}
		return player;
	}
}
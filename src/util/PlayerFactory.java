package util;

import java.util.ArrayList;

import com.qualitascorpus.testsupport.IO;

public interface PlayerFactory {
	public Player makePlayer(IO io,int n, ArrayList<House> h,Score s, boolean bmf);
}
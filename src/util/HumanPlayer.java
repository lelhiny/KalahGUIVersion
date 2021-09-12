package util;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.qualitascorpus.testsupport.IO;

public class HumanPlayer extends Player {
	int house;
	int index = 0;
	FileReader f; 
	public HumanPlayer(IO io,int n, ArrayList<House> h,Score s){
		super(n, h, s);
		this.io = io;
		try {
			f =  new FileReader("input");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public int chooseAHouse(ArrayList<Player> players)  {
		int choice = -1;
		while(choice == -1) {
			 try {
					choice = f.read();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	
		
		index++;
		
		
		return choice;
	}
}

package util;

import com.qualitascorpus.testsupport.IO;

import kalah.ButtonSample;
import kalah.GUIDisplay;

public class DisplayFactoryImpl implements DisplayFactory{
	public Display makeDisplay(IO io, boolean vertical, boolean gui) {
		Display display;
		if(gui)
		{
			//ButtonSample bs = new ButtonSample();
			display = new GUIDisplay();
			
		}
		else if(vertical) {
			display = new VerticalDisplay(io);
		} else {
			display = new HorizontalDisplay(io);
		}
		
		return display;
	}
}

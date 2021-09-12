package util;

import com.qualitascorpus.testsupport.IO;

public interface DisplayFactory {
	public Display makeDisplay(IO io, boolean vertical, boolean gui);
}

package util;
import java.util.ArrayList;
import com.qualitascorpus.testsupport.IO;

public abstract class Display {
	protected IO _io;
	protected static final String DECIMAL_FORMAT_PATTERN = "%1$2s";
	protected DecimalFormat _decimalFormat;
	public Display() {
		_decimalFormat = new DecimalFormat(DECIMAL_FORMAT_PATTERN);
	}
	public abstract void displayKalah( ArrayList<Player> playersList);
}

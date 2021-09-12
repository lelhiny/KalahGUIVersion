package util;

public class DecimalFormat {
	String _pattern;
	public DecimalFormat(String p) {
		_pattern = p;
	}
	public String formatDigits(int digit) {
		return String.format(_pattern, digit);
		
	}

}

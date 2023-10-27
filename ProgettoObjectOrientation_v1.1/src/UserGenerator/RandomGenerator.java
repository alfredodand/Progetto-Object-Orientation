package UserGenerator;

import java.awt.event.ActionEvent;
import java.sql.SQLException;

import javax.swing.JButton;

public class RandomGenerator {
	String username;
	static final int n = 10;
	public boolean checked;
	public RandomGenerator() {
		Generate();
	}

	static String Generate() {
		String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789" + "abcdefghijklmnopqrstuvxyz" + "!%$£&";
		StringBuilder sb = new StringBuilder(n);
		for (int i = 0; i < n; i++) {
			int index = (int) (AlphaNumericString.length() * Math.random());
			sb.append(AlphaNumericString.charAt(index));
		}
		return sb.toString();
	}
	
}


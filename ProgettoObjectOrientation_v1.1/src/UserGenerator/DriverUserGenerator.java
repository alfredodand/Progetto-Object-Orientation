package UserGenerator;

import java.awt.EventQueue;

public class DriverUserGenerator 
{
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserGeneratorWindow window = new UserGeneratorWindow();
					window.getFrame().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}

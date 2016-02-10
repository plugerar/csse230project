package csse230project;

import javax.swing.JFrame;

public class PopoutMain {

	public static void main(String[] args) {
		// City identifier
		String cityName = "Seattle";
		
		// creates a frame
		PopoutFrame frame = new PopoutFrame(cityName);
		frame.setResizable(false);
		
		// normal frame operations
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);;
	}

}

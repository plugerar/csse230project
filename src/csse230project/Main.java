package csse230project;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Main {
	public static void main(String[] args) {
		final JFrame frame = new JFrame();
		Dimension size = new Dimension(900, 850);
		frame.setTitle("Map");
		frame.setSize(size);
		frame.setResizable(false);
		frame.add(new InfoPanel());
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

}
 
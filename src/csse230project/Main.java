package csse230project;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Main {
	public static void main(String[] args) {
		final JFrame frame = new JFrame();
		frame.setTitle("Map");
		frame.setResizable(false);
		frame.add(new MapPanel(),BorderLayout.CENTER);
		frame.add(new InfoPanel(), BorderLayout.PAGE_END);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

}

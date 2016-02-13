package csse230project;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;

public class Main {
	
	public static void main(String[] args) {
		final JFrame frame = new JFrame();
		Dimension size = new Dimension(1000, 1000);
		frame.setTitle("Map");
		frame.setSize(size);
		//frame.setResizable(false);
		//frame.setLayout(new FlowLayout());
		frame.add(new MapPanel(), BorderLayout.CENTER);
		frame.add(new InfoPanel(), BorderLayout.WEST);


		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

}

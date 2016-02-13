package csse230project;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class Main {
	
	public static void main(String[] args) throws IOException {
		final JFrame frame = new JFrame();
		Dimension size = new Dimension(1800, 1000);
		frame.setTitle("Map");
		frame.setSize(size);
		frame.add(new InfoPanel(), BorderLayout.EAST);
		MapLayeredPane map = new MapLayeredPane();
        frame.add(map, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

}

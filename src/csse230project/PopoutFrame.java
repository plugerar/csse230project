package csse230project;

import java.awt.BorderLayout;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class PopoutFrame extends JFrame {
	
	public PopoutFrame(String cityID) {
		setTitle(cityID);
		String content = "";
		JTextArea text = null;
				 
		// the image		
		JLabel imageLabel = new JLabel(new ImageIcon(".\\CityImages\\" + cityID + ".png"));
		JPanel imagePanel = new JPanel();
		imagePanel.add(imageLabel);
		
		// the description		
		try {
		text = new JTextArea(8, 100);
		BufferedReader in = new BufferedReader(new FileReader(".\\CityDescriptions\\" + cityID + ".txt"));
		String line = in.readLine();
		while(line != null){
		  text.append(line + "\n");
		  line = in.readLine();
		}
		} catch (IOException exception) {
			exception.printStackTrace();
		}
		
		text.setFont(new Font("Serif", Font.PLAIN, 20));
		text.setLineWrap(true);
		text.setWrapStyleWord(true);
	
		JPanel descriptionPanel = new JPanel();
		descriptionPanel.add(text);
				
		// add panels to the frame
		add(imagePanel, BorderLayout.NORTH);
		add(descriptionPanel, BorderLayout.SOUTH);
		
		pack();
	}
}

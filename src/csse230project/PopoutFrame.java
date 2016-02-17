package csse230project;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

/**
 * Makes a popout frame for the city descriptions
 */
public class PopoutFrame extends JFrame {
	private static Color BACKGROUND_COLOR = Color.DARK_GRAY;

	/**
	 * Creates the popout frame for the specified city
	 *
	 * @param cityID - name of city to access
	 */
	public PopoutFrame(String cityID) {
		setTitle(cityID);
		getContentPane().setBackground(BACKGROUND_COLOR);
		JTextArea text = null;

		// the image
		ImageIcon imageIcon = new ImageIcon(".\\CityImages\\" + cityID + ".png"); // load the image to a imageIcon
		Image image = imageIcon.getImage(); // transform it 
		Image newimg = image.getScaledInstance(900, 600,  java.awt.Image.SCALE_REPLICATE); // scale it the smooth way  
		
		JLabel imageLabel = new JLabel(new ImageIcon(newimg));		
		JPanel imagePanel = new JPanel();

		// set padding on the image
		imagePanel.setBorder(new CompoundBorder(imagePanel.getBorder(), new EmptyBorder(45, 0, 30, 0)));
		imagePanel.setBackground(BACKGROUND_COLOR);
		imagePanel.add(imageLabel);

		// the description
		try {
			text = new JTextArea(9, 75);
			BufferedReader in = new BufferedReader(new FileReader(".\\CityDescriptions\\" + cityID + ".txt"));
			String line = in.readLine();
			while (line != null) {
				text.append(line + "\n");
				line = in.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		text.setEditable(false);
		text.setBackground(BACKGROUND_COLOR);
		text.setForeground(Color.WHITE);
		text.setFont(new Font("Serif", Font.BOLD, 24));
		text.setLineWrap(true);
		text.setWrapStyleWord(true);

		JPanel descriptionPanel = new JPanel();
		descriptionPanel.setBorder(new EmptyBorder(0, 25, 0, 25));
		descriptionPanel.setBackground(BACKGROUND_COLOR);
		descriptionPanel.add(text);

		// add colored panels to the frame
		add(imagePanel, BorderLayout.NORTH);
		add(descriptionPanel, BorderLayout.SOUTH);

		pack();
	}
}

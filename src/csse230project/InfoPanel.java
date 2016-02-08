package csse230project;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class InfoPanel extends JPanel{
	
	public InfoPanel() {
		this.setBounds(0, 50, 950, 950);
		this.setSize(950, 950);
		City[] CityList = { new City("New York"), new City("California"), new City("Terre Haute"), new City("Washington"), new City("Boston") };
		JComboBox cityStart = new JComboBox(CityList);
		cityStart.setSelectedIndex(4);
		cityStart.addActionListener((ActionListener) this);
		JComboBox cityEnd = new JComboBox(CityList);
		cityEnd.setSelectedIndex(4);
		cityEnd.addActionListener((ActionListener) this);
		JLabel label = new JLabel("Shows shortest path along with the distance and time info");
		JButton calcRoute = new JButton("calculateRoute");
		JButton pointsOfInterest = new JButton("pointsOfInterest");
		JButton cityRating  = new JButton("cityRating");
	}

}

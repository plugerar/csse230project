package csse230project;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class InfoPanel extends JPanel{
	
	public InfoPanel() {
		this.setPreferredSize(new Dimension(250, 1000));
		this.setAlignmentX(LEFT_ALIGNMENT);
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.calculateRoute();
		JButton pointsOfInterest = new JButton("pointsOfInterest");
		JButton cityRating  = new JButton("cityRating");
		this.setLayout(new FlowLayout());
		this.setBorder(BorderFactory.createLineBorder(Color.black));
		
		JOptionPane prnt = new JOptionPane();
		prnt.showMessageDialog(null, "The features of the software");
		
		this.add(pointsOfInterest);
		this.add(cityRating);
	}
	
	private void calculateRoute(){
		String[] CityList = { "New York", "California", "Terre Haute", "Washington", "Boston"};
		JComboBox cityStart = new JComboBox(CityList);
		cityStart.setSelectedIndex(4);
		cityStart.addActionListener((ActionListener) cityStart);
		JComboBox cityEnd = new JComboBox(CityList);
		cityEnd.setSelectedIndex(4);
		cityEnd.addActionListener((ActionListener) cityEnd);
		JLabel label = new JLabel("Shows shortest path along with the distance and time info");
		JButton calcRoute = new JButton("calculateRoute");
		this.add(cityStart);
		this.add(cityEnd);
		this.add(label);
		this.add(calcRoute);
	}

}

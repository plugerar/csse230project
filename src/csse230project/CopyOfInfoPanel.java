package csse230project;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

//CardsLayoutDemo with InfoPanel

public class CopyOfInfoPanel extends JPanel implements ItemListener{
    JPanel cards; //a panel that uses CardLayout
    final static String BUTTONPANEL = "Card with JButtons";
    final static String TEXTPANEL = "Card with JTextField";
	
	public CopyOfInfoPanel() throws Exception {
		this.setPreferredSize(new Dimension(250, 1000));
		this.setAlignmentX(LEFT_ALIGNMENT);
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setLayout(new FlowLayout());
		this.setBorder(BorderFactory.createLineBorder(Color.black));
		
		 //Put the JComboBox in a JPanel to get a nicer look.
        JPanel comboBoxPane = new JPanel(); //use FlowLayout
        String comboBoxItems[] = { BUTTONPANEL, TEXTPANEL };
        JComboBox cb = new JComboBox(comboBoxItems);
        cb.setEditable(false);
        cb.addItemListener(this);
        comboBoxPane.add(cb);
		
		 //Create the "cards".
        JPanel card1 = new JPanel();
        card1.add(new JButton("Button 1"));
        card1.add(new JButton("Button 2"));
        card1.add(new JButton("Button 3"));
        
        JPanel card2 = new JPanel();
        card2.add(new JTextField("TextField", 20));
        
        //Create the panel that contains the "cards".
        cards = new JPanel(new CardLayout());
        cards.add(card1, BUTTONPANEL);
        cards.add(card2, TEXTPANEL);
        
        this.add(comboBoxPane, BorderLayout.PAGE_START);
        this.add(cards, BorderLayout.CENTER);
		
//		this.startWindow();
//		this.calculateRoute();
//		this.addButtons();
	}
	
	@Override
	public void itemStateChanged(ItemEvent evt) {
		CardLayout cl = (CardLayout)(cards.getLayout());
        cl.show(cards, (String)evt.getItem());
	}
	
	private void startWindow(){
		JOptionPane prnt = new JOptionPane();
		prnt.showMessageDialog(null, "The features of the software");
	}
	
	private void addButtons(){
		JButton calcRoute = new JButton("calculateRoute");
		JButton pointsOfInterest = new JButton("pointsOfInterest");
		JButton cityRating  = new JButton("cityRating");
//		calcRoute.addActionListener(new MouseListener(){
//			
//		});
		this.add(pointsOfInterest);
		this.add(cityRating);
		this.add(calcRoute);
	}
	
	private void calculateRoute() throws Exception{
        CityStructure struct = WriteDomain.read("usdomain.xml");
		int numberOfCities = struct.getCityInterestList().size();
		Object[] cityNames = new String[numberOfCities];
		Iterator iter = struct.getCityInterestList().iterator();
		for(int i = 0; i < numberOfCities; i++){
			City c = (City) iter.next();
			cityNames[i] = c.getName();
		}
		JComboBox cityStart = new JComboBox(cityNames);
		cityStart.setSelectedIndex(numberOfCities - 1);
		cityStart.addActionListener((ActionListener) cityStart);
		JComboBox cityEnd = new JComboBox(cityNames);
		cityEnd.setSelectedIndex(numberOfCities - 1);
		cityEnd.addActionListener((ActionListener) cityEnd);
		JButton calcRoute = new JButton("calculateRoute");
		this.add(cityStart);
		this.add(cityEnd);
		this.add(calcRoute);
	}
}

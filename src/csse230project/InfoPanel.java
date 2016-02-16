package csse230project;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
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



public class InfoPanel extends JPanel implements ActionListener{
    JPanel cards; //a panel that uses CardLayout
    private JButton cityRating;
    final static String cr = "calculateRoute";
    final static String poi = "pointsOfInterest";
    final static String cRate = "cityRating";

    //final static String BUTTONPANEL = "Card with JButtons";
    //final static String TEXTPANEL = "Card with JTextField";
	
	public InfoPanel() throws Exception {
		this.setPreferredSize(new Dimension(250, 1000));
		this.setAlignmentX(LEFT_ALIGNMENT);
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setLayout(new FlowLayout());
		//this.setLayout(new CardLayout());
		this.setBorder(BorderFactory.createLineBorder(Color.black));
		
		createCards();
		
		 //Put the JComboBox in a JPanel to get a nicer look.
//        JPanel comboBoxPane = new JPanel(); //use FlowLayout
//        String comboBoxItems[] = { BUTTONPANEL, TEXTPANEL };
//        JComboBox cb = new JComboBox(comboBoxItems);
//        cb.setEditable(false);
//        cb.addItemListener(this);
//        comboBoxPane.add(cb);
		
		 //Create the "cards".
//        JPanel card1 = new JPanel();
//        card1.add(new JButton("Button 1"));
//        card1.add(new JButton("Button 2"));
//        card1.add(new JButton("Button 3"));
//        
//        JPanel card2 = new JPanel();
//        card2.add(new JTextField("TextField", 20));
//        
//        //Create the panel that contains the "cards".
//        cards = new JPanel(new CardLayout());
//        cards.add(card1, BUTTONPANEL);
//        cards.add(card2, TEXTPANEL);
        
//        this.add(comboBoxPane, BorderLayout.PAGE_START);
//        this.add(cards, BorderLayout.CENTER);
		
//		this.startWindow();
//		this.calculateRoute();
//		this.addButtons();
	}
	
	public void createCards() throws Exception{
		cards = new JPanel(new CardLayout());
		createHome();
	}
	
	public void createHome() throws Exception{
		JPanel cardCalulcateRoute = new JPanel();
		JPanel cardPointsOfInterest = new JPanel();
		JPanel cardCityRating = new JPanel();
		createCalculateRoute(cardCalulcateRoute);
		//createPointsOfInterest(cardPointsOfInterest);
		createCityRatings(cardCityRating);
		
        JButton calcRoute = new JButton(cr);
		JButton pointsOfInterest = new JButton(poi);
		JButton cityRating  = new JButton(cRate);
		
		calcRoute.addActionListener(this);
		pointsOfInterest.addActionListener(this);
		cityRating.addActionListener(this);
		
		calcRoute.setActionCommand(cr);
		pointsOfInterest.setActionCommand(poi);
		cityRating.setActionCommand(cRate);
				
		cards.add(cardCalulcateRoute, cr);
		cards.add(cardPointsOfInterest, poi);
		cards.add(cardCityRating, cRate); 
		
		this.add(calcRoute);
		this.add(pointsOfInterest);
		this.add(cityRating);
		this.add(cards);
	}
	
	public void createCityRatings(JPanel panel) throws Exception{
		System.out.println("cRtae");
		CityStructure struct = WriteDomain.read("usdomain.xml");
		JLabel label;
		for(int i = 0; i < struct.getCityNames().size(); i++){
			label = new JLabel(i + ". " + struct.getCityNames());
			panel.add(label);
		}
	}
	
//	public void itemStateChanged(ActionListener evt) {
//		CardLayout cl = (CardLayout)(cards.getLayout());
//        cl.show(cards, (String)evt.getActionCommand());
//	}
//	
	private void startWindow(){
		JOptionPane prnt = new JOptionPane();
		prnt.showMessageDialog(null, "The features of the software");
	}
	
	public void createCalculateRoute(JPanel panel){
		
	}
	//public void createPointsOfInterest(JPanel panel){// throws Exception{
//		CityStructure struct = WriteDomain.read("usdomain.xml");
//		int numberOfCities = struct.getCityInterestList().size();
//		Object[] cityNames = new String[numberOfCities];
//		Iterator iter = struct.getCityInterestList().iterator();
//		for(int i = 0; i < numberOfCities; i++){
//			City c = (City) iter.next();
//			cityNames[i] = c.getName();
//		}
//		JComboBox city = new JComboBox(cityNames);
//		city.setSelectedIndex(numberOfCities - 1);
//		city.addActionListener((ActionListener) city);
	//}
	
//	private void addButtons(){
//		JButton calcRoute = new JButton("calculateRoute");
//		JButton pointsOfInterest = new JButton("pointsOfInterest");
//		JButton cityRating  = new JButton("cityRating");
////		calcRoute.addActionListener(new MouseListener(){
////			
////		});
//		this.add(pointsOfInterest);
//		this.add(cityRating);
//		this.add(calcRoute);
//	}
	
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

//	@Override
//	public void itemStateChanged(ItemEvent e) {
//		// TODO Auto-generated method stub
//		
//	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		CardLayout cl = (CardLayout) cards.getLayout();
        cl.show(cards, arg0.getActionCommand());
	}
}

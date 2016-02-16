package csse230project;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;



public class InfoPanel extends JPanel implements ActionListener{// implements ActionListener{
	MapPanel map;
    JPanel cards; //a panel that uses CardLayout
    JPanel cardPointsOfInterest;
    private JButton cityRating;
    private JButton pointsOfInterest;
    private JButton calculateRoute;

    final static String cr = "calculateRoute";
    final static String poi = "pointsOfInterest";
    final static String cRate = "cityRating";

    //final static String BUTTONPANEL = "Card with JButtons";
    //final static String TEXTPANEL = "Card with JTextField";
	
	public InfoPanel(MapPanel map) throws Exception {
		this.map = map;
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
        JTabbedPane tabbedPane = new JTabbedPane();

		JPanel cardCalulcateRoute =new JPanel() ;//{
//            //Make the panel wider than it really needs, so
//            //the window's wide enough for the tabs to stay
//            //in one row.
//            public Dimension getPreferredSize() {
//                Dimension size = super.getPreferredSize();
//                size.width += 100;
//                return size;
//            }
//        };
		cardPointsOfInterest = new JPanel() ;
		JPanel cardCityRating = new JPanel();
		cardCityRating.setLayout(new BoxLayout(cardCityRating, BoxLayout.Y_AXIS));
		createCalculateRoute(cardCalulcateRoute);
		createPointsOfInterest(cardPointsOfInterest);
		createCityRatings(cardCityRating);
		
        calculateRoute = new JButton(cr);
		pointsOfInterest = new JButton(poi);
		cityRating  = new JButton(cRate);
		
		calculateRoute.addActionListener(this);
		pointsOfInterest.addActionListener(this);
		cityRating.addActionListener(this);
		
		calculateRoute.setActionCommand(cr);
		pointsOfInterest.setActionCommand(poi);
		cityRating.setActionCommand(cRate);
				
		cards.add(cardCalulcateRoute, cr);
		cards.add(cardPointsOfInterest, poi);
		cards.add(cardCityRating, cRate); 
//		tabbedPane.addTab(cr, cardCalulcateRoute);
//		tabbedPane.addTab(poi, cardPointsOfInterest);
//		tabbedPane.addTab(cRate, cardCityRating);

		this.add(calculateRoute, BorderLayout.NORTH);
		this.add(pointsOfInterest, BorderLayout.WEST);
		this.add(cityRating, BorderLayout.EAST);
		this.add(cards, BorderLayout.SOUTH);
		//this.add(tabbedPane, BorderLayout.CENTER);
	}
	
	public void createCityRatings(JPanel panel) throws Exception{
		System.out.println("cRtae");
		CityStructure struct = WriteDomain.read("usdomain.xml");
		JLabel label;
		ArrayList<City> cities = struct.cityInterestToArrayList();
		Iterator<City> iCity= cities.iterator();
		Iterator<String> i = struct.getCityNames().iterator();
		for(int index = 0; index < struct.getCityNames().size(); index++){
			label = new JLabel(index + ". " + i.next() + iCity.next().getInterestLevel());
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
	public void createPointsOfInterest(JPanel panel) throws Exception{// throws Exception{
		CityStructure struct = WriteDomain.read("usdomain.xml");
		City now = MainFrame.mapPanel.getCurrentCity();
		
		if(now != null){
			String name = now.getName();
			System.out.println(name);
			
			City pointer = struct.getCity(name);
			//System.out.println(pointer);
			JLabel label;
			System.out.println(pointer.getPointsOfInterest().size());
		//Iterator<Attraction> i = pointer.getPointsOfInterest().iterator();
		for(int index = 0; index < 1;/*pointer.getPointsOfInterest().size();*/ index++){
			System.out.println("here");
			//Attraction a = i.next();
			label = new JLabel(index + ". "/* + a.getName() + a.getInterestLevel()*/);
			panel.add(label);
		}
		}
	}
	
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
	
	public JPanel getCardPointsOfInterest(){
		return this.cardPointsOfInterest;
	}

//	@Override
//	public void actionPerformed(ActionEvent arg0) {
//		// TODO Auto-generated method stub
//		
//	}

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
//=======
//package csse230project;
//import java.awt.BorderLayout;
//import java.awt.CardLayout;
//import java.awt.Color;
//import java.awt.Dimension;
//import java.awt.FlowLayout;
//import java.awt.event.ActionListener;
//import java.util.Iterator;
//
//import javax.swing.BorderFactory;
//import javax.swing.BoxLayout;
//import javax.swing.JButton;
//import javax.swing.JComboBox;
//import javax.swing.JOptionPane;
//import javax.swing.JPanel;
//import javax.swing.JTabbedPane;
//
//
//
//public class InfoPanel extends JPanel{// implements ActionListener{
//    JPanel cards; //a panel that uses CardLayout
//    private JButton cityRating;
//    private JButton pointsOfInterest;
//    private JButton calculateRoute;
//    private JButton home;
//    private JButton tripPlanner;
//
//    final static String cr = "Calculate Route";
//    final static String poi = "Points Of Interest";
//    final static String cRate = "City Rating";
//    final static String hom = "Home";
//    final static String tPlan = "Trip Planner";
//
//    //final static String BUTTONPANEL = "Card with JButtons";
//    //final static String TEXTPANEL = "Card with JTextField";
//	
//	public InfoPanel() throws Exception {
//		this.setPreferredSize(new Dimension(250, 1000));
//		this.setAlignmentX(LEFT_ALIGNMENT);
//		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
//		this.setLayout(new FlowLayout());
//		//this.setLayout(new CardLayout());
//		this.setBorder(BorderFactory.createLineBorder(Color.black));
//		
//		
//		createCards();
//		
//		 //Put the JComboBox in a JPanel to get a nicer look.
////        JPanel comboBoxPane = new JPanel(); //use FlowLayout
////        String comboBoxItems[] = { BUTTONPANEL, TEXTPANEL };
////        JComboBox cb = new JComboBox(comboBoxItems);
////        cb.setEditable(false);
////        cb.addItemListener(this);
////        comboBoxPane.add(cb);
//		
//		 //Create the "cards".
////        JPanel card1 = new JPanel();
////        card1.add(new JButton("Button 1"));
////        card1.add(new JButton("Button 2"));
////        card1.add(new JButton("Button 3"));
////        
////        JPanel card2 = new JPanel();
////        card2.add(new JTextField("TextField", 20));
////        
////        //Create the panel that contains the "cards".
////        cards = new JPanel(new CardLayout());
////        cards.add(card1, BUTTONPANEL);
////        cards.add(card2, TEXTPANEL);
//        
////        this.add(comboBoxPane, BorderLayout.PAGE_START);
////        this.add(cards, BorderLayout.CENTER);
//		
////		this.startWindow();
////		this.calculateRoute();
////		this.addButtons();
//	}
//	
//	public void createCards() throws Exception{
//		cards = new JPanel(new CardLayout());
//		createHome();
//	}
//	
//	public void createHome() throws Exception{
//        JTabbedPane tabbedPane = new JTabbedPane();
//
//		JPanel cardCalulcateRoute =new JPanel() ;//{
////            //Make the panel wider than it really needs, so
////            //the window's wide enough for the tabs to stay
////            //in one row.
////            public Dimension getPreferredSize() {
////                Dimension size = super.getPreferredSize();
////                size.width += 100;
////                return size;
////            }
////        };
//		JPanel cardPointsOfInterest = new JPanel() ;
//		JPanel cardCityRating = new JPanel();
//		cardCityRating.setLayout(new BoxLayout(cardCityRating, BoxLayout.Y_AXIS));
//		createCalculateRoute(cardCalulcateRoute);
//		//createPointsOfInterest(cardPointsOfInterest);
//		createCityRatings(cardCityRating);
//		
////        calculateRoute = new JButton(cr);
////		pointsOfInterest = new JButton(poi);
////		cityRating  = new JButton(cRate);
////		
////		calculateRoute.addActionListener(this);
////		pointsOfInterest.addActionListener(this);
////		cityRating.addActionListener(this);
////		
////		calculateRoute.setActionCommand(cr);
////		pointsOfInterest.setActionCommand(poi);
////		cityRating.setActionCommand(cRate);
////				
////		cards.add(cardCalulcateRoute, cr);
////		cards.add(cardPointsOfInterest, poi);
////		cards.add(cardCityRating, cRate); 
//		tabbedPane.addTab(cr, cardCalulcateRoute);
//		tabbedPane.addTab(poi, cardPointsOfInterest);
//		tabbedPane.addTab(cRate, cardCityRating);
////		tabbedPane.addTab(hom, home);
//
////		this.add(calculateRoute, BorderLayout.NORTH);
////		this.add(pointsOfInterest, BorderLayout.WEST);
////		this.add(cityRating, BorderLayout.EAST);
////		this.add(cards, BorderLayout.SOUTH);
//		this.add(tabbedPane, BorderLayout.CENTER);
//	}
//	
//	public void createCityRatings(JPanel panel) throws Exception{
////		CityStructure struct = WriteDomain.read("usdomain.xml");
////		ArrayList<City> cities = struct.cityInterestToArrayList();
////		Iterator<City> iCity= cities.iterator();
////		Iterator<String> i = struct.getCityNames().iterator();
////		String[] columnNames = {"City Name","Rating"};
////		Object[][] data = new Object[50][2];
////		
////		for(int index = 0; index < struct.getCityNames().size(); index++){
////			data[index][0] = i.next();
////			data[index][1] = iCity.next().getInterestLevel();
////		}
////		JTable table = new JTable(data, columnNames);
////		table.setEnabled(false);
////		TableColumn column = null;
////	    column = table.getColumnModel().getColumn(0);
////	    column.setPreferredWidth(100);
////	    column = table.getColumnModel().getColumn(1);
////	    column.setPreferredWidth(50);
////		panel.setLayout(new BorderLayout());
////		panel.add(table.getTableHeader(), BorderLayout.PAGE_START);
////		panel.add(table, BorderLayout.CENTER);
//		
//	}
//	
////	public void itemStateChanged(ActionListener evt) {
////		CardLayout cl = (CardLayout)(cards.getLayout());
////        cl.show(cards, (String)evt.getActionCommand());
////	}
////	
//	private void startWindow(){
//		JOptionPane prnt = new JOptionPane();
//		prnt.showMessageDialog(null, "The features of the software");
//	}
//	
//	public void createCalculateRoute(JPanel panel){
//		JButton button = new JButton("Calculate Route");
//		panel.add(button);
//	}
//	//public void createPointsOfInterest(JPanel panel){// throws Exception{
////		CityStructure struct = WriteDomain.read("usdomain.xml");
////		int numberOfCities = struct.getCityInterestList().size();
////		Object[] cityNames = new String[numberOfCities];
////		Iterator iter = struct.getCityInterestList().iterator();
////		for(int i = 0; i < numberOfCities; i++){
////			City c = (City) iter.next();
////			cityNames[i] = c.getName();
////		}
////		JComboBox city = new JComboBox(cityNames);
////		city.setSelectedIndex(numberOfCities - 1);
////		city.addActionListener((ActionListener) city);
//	//}
//	
////	private void addButtons(){
////		JButton calcRoute = new JButton("calculateRoute");
////		JButton pointsOfInterest = new JButton("pointsOfInterest");
////		JButton cityRating  = new JButton("cityRating");
//////		calcRoute.addActionListener(new MouseListener(){
//////			
//////		});
////		this.add(pointsOfInterest);
////		this.add(cityRating);
////		this.add(calcRoute);
////	}
//	
//	private void calculateRoute() throws Exception{
//        CityStructure struct = WriteDomain.read("usdomain.xml");
//		int numberOfCities = struct.getCityInterestList().size();
//		Object[] cityNames = new String[numberOfCities];
//		Iterator iter = struct.getCityInterestList().iterator();
//		for(int i = 0; i < numberOfCities; i++){
//			City c = (City) iter.next();
//			cityNames[i] = c.getName();
//		}
//		JComboBox cityStart = new JComboBox(cityNames);
//		cityStart.setSelectedIndex(numberOfCities - 1);
//		cityStart.addActionListener((ActionListener) cityStart);
//		JComboBox cityEnd = new JComboBox(cityNames);
//		cityEnd.setSelectedIndex(numberOfCities - 1);
//		cityEnd.addActionListener((ActionListener) cityEnd);
//		JButton calcRoute = new JButton("calculateRoute");
//		this.add(cityStart);
//		this.add(cityEnd);
//		this.add(calcRoute);
//	}
//
////	@Override
////	public void itemStateChanged(ItemEvent e) {
////		// TODO Auto-generated method stub
////		
////	}
//
////	@Override
////	public void actionPerformed(ActionEvent arg0) {
////		CardLayout cl = (CardLayout) cards.getLayout();
////        cl.show(cards, arg0.getActionCommand());
////	}
//}
//

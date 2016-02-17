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
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.TableColumn;



public class InfoPanel extends JPanel implements ActionListener{// implements ActionListener{
	MapPanel map;
    JPanel cards; //a panel that uses CardLayout
    JPanel cardPointsOfInterest;
    JPanel cardCalulcateRoute;
    private JButton cityRating;
    private JButton pointsOfInterest;
    private JButton calculateRoute;

    final static String cr = "calculateRoute";
    final static String poi = "pointsOfInterest";
    final static String cRate = "cityRating";
    
    String currentCard;

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
		this.cards = new JPanel(new CardLayout());
		createHome();
	}
	
	public void createHome() throws Exception{
        JTabbedPane tabbedPane = new JTabbedPane();

		cardCalulcateRoute =new JPanel() ;//{
//            //Make the panel wider than it really needs, so
//            //the window's wide enough for the tabs to stay
//            //in one row.
//            public Dimension getPreferredSize() {
//                Dimension size = super.getPreferredSize();
//                size.width += 100;
//                return size;
//            }
//        };
		this.cardPointsOfInterest = new JPanel() ;
		JPanel cardCityRating = new JPanel();
		cardCityRating.setLayout(new BoxLayout(cardCityRating, BoxLayout.Y_AXIS));
		createCalculateRoute(cardCalulcateRoute);
		createPointsOfInterest(this.cardPointsOfInterest);
		createCityRatings(cardCityRating);
		
        this.calculateRoute = new JButton(cr);
		this.pointsOfInterest = new JButton(poi);
		this.cityRating  = new JButton(cRate);
		
		this.calculateRoute.addActionListener(this);
		this.pointsOfInterest.addActionListener(this);
		this.cityRating.addActionListener(this);
		
		this.calculateRoute.setActionCommand(cr);
		this.pointsOfInterest.setActionCommand(poi);
		this.cityRating.setActionCommand(cRate);
				
		this.cards.add(cardCalulcateRoute, cr);
		this.cards.add(this.cardPointsOfInterest, poi);
		this.cards.add(cardCityRating, cRate); 
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
		CityStructure struct = WriteDomain.read("usdomain.xml");
		ArrayList<City> cities = struct.cityInterestToArrayList();
		Iterator<City> iCity = cities.iterator();
		Iterator<String> i = struct.getCityNames().iterator();
		String[] columnNames = { "City Name", "Rating" };
		Object[][] data = new Object[struct.getCityMap().size()][2];

		for (int index = 0; index < struct.getCityNames().size(); index++) {
			data[index][0] = i.next();
			data[index][1] = iCity.next().getInterestLevel();
		}
		JTable table = new JTable(data, columnNames);
		table.setEnabled(false);
		panel.setLayout(new BorderLayout());
		TableColumn column = null;
		column = table.getColumnModel().getColumn(0);
		column.setPreferredWidth(100);
		column = table.getColumnModel().getColumn(1);
		column.setPreferredWidth(50);
		JScrollPane scroll = new JScrollPane(table, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scroll.setPreferredSize(new Dimension(200,800));
		scroll.setEnabled(false);
		panel.add(table.getTableHeader(), BorderLayout.PAGE_START);
		panel.add(scroll, BorderLayout.CENTER);
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
	
	public void createCalculateRoute(JPanel panel) throws Exception{
		panel.removeAll();
		panel.revalidate();
		System.out.println("enterd calcRoute");
		CityStructure struct = WriteDomain.read("usdomain.xml");
		ArrayList<Edge> path = struct.calculateRoute(MainFrame.mapPanel.getClickedCities());
		if(!path.isEmpty()){
			Iterator<Edge> i = path.iterator();
			for(int index = 0; index < path.size(); index++){
				System.out.println("enterd calcRoute for loop");
				Edge edge = i.next();
				JLabel cityName = new JLabel(edge.getCity1().getName());
				JLabel distance = new JLabel(((Integer)edge.getDistance()).toString());
				JLabel time = new JLabel(((Double)edge.getTime()).toString());
				panel.add(cityName);
				panel.add(distance);
				panel.add(time);
			}
			panel.repaint();
			MainFrame.mapPanel.setClickedCities(new ArrayList<City>());
		}
	}
	public void createPointsOfInterest(JPanel panel) throws Exception{// throws Exception{
		System.out.println("cresatePonitsofInterest");
		panel.removeAll();
		panel.revalidate();
		CityStructure struct = WriteDomain.read("usdomain.xml");
		ArrayList<City> arr = MainFrame.mapPanel.getClickedCities();
		if(!arr.isEmpty()){
			int lastIndex = arr.size() - 1;
			City now = MainFrame.mapPanel.getClickedCities().get(lastIndex);
		
		//if(now != null){
			String name = now.getName();
			System.out.println(name);
			
			City pointer = struct.getCity(name);
			//System.out.println(pointer);
			JLabel label;
			System.out.println(pointer.getPointsOfInterest().size());
		Iterator<Attraction> i = pointer.getPointsOfInterest().iterator();
		for(int index = 0; index < pointer.getPointsOfInterest().size(); index++){
			System.out.println("here");
			Attraction a = i.next();
			label = new JLabel(index + 1 + ". " + a.getName() + a.getInterestLevel());
			panel.add(label);
		}
		}
		panel.repaint();
		//MainFrame.mapPanel.setClickedCities(new ArrayList<City>());

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
		currentCard = arg0.getActionCommand();
        cl.show(cards, currentCard);
	}
	
	public String getCurrentCard(){
		return currentCard;
	}
	
	public JPanel getCardCalulcateRoute(){
		return cardCalulcateRoute;
	}

//	@Override
//	public void actionPerformed(ActionEvent e) {
//		// TODO Auto-generated method stub
//		
//	}
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

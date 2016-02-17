package csse230project;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;



public class InfoPanel extends JPanel implements ActionListener, MouseListener{// implements ActionListener{
	MapPanel map;
	
	private JPanel panelStart;
    private JPanel cards; //a panel that uses CardLayout
    private JPanel cardPointsOfInterest;
    private JPanel cardCalulcateRoute;
    private JPanel cardTripPlanner;
    private JPanel cardCityDescriptions;

    private JButton start;
    private JButton cityRating;
    private JButton pointsOfInterest;
    private JButton calculateRoute;
    private JButton tripPlanner;
    private JButton cityDescriptions;

    private final static String starting = "start";
    private final static String cr = "Calculate Route";
    private final static String poi = "Points Of Interest";
    private final static String cRate = "City Rating";
    private final static String tp = "Trip Planner";
    private final static String cd = "City Descriptions";

    private String currentCard;

    //final static String BUTTONPANEL = "Card with JButtons";
    //final static String TEXTPANEL = "Card with JTextField";
	
	public InfoPanel(MapPanel map) throws Exception {
		this.map = map;
		this.setPreferredSize(new Dimension(250, 1000));
		//this.setAlignmentX(LEFT ALIGNMENT);
		//this.setLayout(new BoxLayout(this, BoxLayout.Y AXIS));
		//this.setLayout(new FlowLayout());
		//this.setLayout(new CardLayout());
		//this.setBorder(BorderFactory.createLineBorder(Color.black));
		
		panelStart = new JPanel();
		panelStart.setPreferredSize(new Dimension(250, 1000));
		panelStart.setAlignmentX(LEFT_ALIGNMENT);
		panelStart.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		panelStart.setLayout(new FlowLayout());
		//this.setLayout(new CardLayout());
		panelStart.setBorder(BorderFactory.createLineBorder(Color.black));
		
		startWindow();
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
        
//        this.add(comboBoxPane, BorderLayout.PAGE START);
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
        //JTabbedPane tabbedPane = new JTabbedPane();
		this.cardCalulcateRoute = new JPanel() ;//{
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
		this.cardTripPlanner = new JPanel();
		this.cardCityDescriptions = new JPanel();

		cardCityRating.setLayout(new BoxLayout(cardCityRating, BoxLayout.Y_AXIS));
		
		createCalculateRoute(this.cardCalulcateRoute);
		createPointsOfInterest(this.cardPointsOfInterest);
		createCityRatings(cardCityRating);
		createTripPlanner(this.cardTripPlanner);
		createCityDescriptions(this.cardCityDescriptions);

		this.start = new JButton(starting);
        this.calculateRoute = new JButton(cr);
		this.pointsOfInterest = new JButton(poi);
		this.cityRating  = new JButton(cRate);
		this.tripPlanner = new JButton(tp);
		this.cityDescriptions = new JButton(cd);

		this.calculateRoute.addActionListener(this);
		this.pointsOfInterest.addActionListener(this);
		this.cityRating.addActionListener(this);
		this.tripPlanner.addActionListener(this);
		this.cityDescriptions.addActionListener(this);

		
		this.calculateRoute.setActionCommand(cr);
		this.pointsOfInterest.setActionCommand(poi);
		this.cityRating.setActionCommand(cRate);
		this.tripPlanner.setActionCommand(tp);
		this.cityDescriptions.setActionCommand(cd);
				
		this.cards.add(this.cardCalulcateRoute, cr);
		this.cards.add(this.cardPointsOfInterest, poi);
		this.cards.add(cardCityRating, cRate); 
		this.cards.add(this.cardTripPlanner, tp);
		this.cards.add(this.cardCityDescriptions, cd);

		this.cards.getComponent(0).setName(cr);
		this.cards.getComponent(1).setName(poi);
		this.cards.getComponent(2).setName(cRate);
		this.cards.getComponent(3).setName(tp);
		this.cards.getComponent(4).setName(cd);
//		tabbedPane.addTab(cr, cardCalulcateRoute);
//		tabbedPane.addTab(poi, cardPointsOfInterest);
//		tabbedPane.addTab(cRate, cardCityRating);
		start.addMouseListener(this);
		this.add(start);
		panelStart.add(calculateRoute);//, BorderLayout.NORTH);
		panelStart.add(pointsOfInterest);//, BorderLayout.WEST);
		panelStart.add(cityRating);//, BorderLayout.EAST);
		panelStart.add(tripPlanner);
		panelStart.add(cityDescriptions);
		panelStart.add(cards);//, BorderLayout.SOUTH);
		//this.add(tabbedPane, BorderLayout.CENTER);
	}
	
	private void createTripPlanner(JPanel panel) {
		System.out.println("trip planner!");
		panel.removeAll();
		panel.revalidate();
		JButton selectCity = new JButton("Select City");
		this.cityDescriptions.addActionListener(this);
		
		this.calculateRoute.setActionCommand(cr);
		
		ArrayList<City> arr = MainFrame.mapPanel.getClickedCities();
		if(!arr.isEmpty()){
			int lastIndex = arr.size() - 1;
			City now = MainFrame.mapPanel.getClickedCities().get(lastIndex);
			
			if(now != null) {
				String cityName = now.getName();
				//////////////////////////////////////////
				
			}
		}
		
	}

	public void createCityDescriptions(JPanel panel) {
		System.out.println("create city descriptions");
		panel.removeAll();
		panel.revalidate();
		ArrayList<City> arr = MainFrame.mapPanel.getClickedCities();
		if(!arr.isEmpty()){
			int lastIndex = arr.size() - 1;
			City now = MainFrame.mapPanel.getClickedCities().get(lastIndex);
			
			if(now != null) {
				String cityName = now.getName();
				
				// creates a frame
				PopoutFrame frame = new PopoutFrame(cityName);
				frame.setResizable(false);
				
				// normal frame operations
				frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
				frame.setVisible(true);
				
			}
		}
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

	private void startWindow(){
//		JOptionPane prnt = new JOptionPane();
//		prnt.showMessageDialog(null, "The features of the software");
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
		
		if(now != null){
			String name = now.getName();
			System.out.println(name);
			
			City pointer = struct.getCity(name);
			String[] columnNames = {"Rating","POI"};
			Object[][] data = new Object[pointer.getPointsOfInterest().size()][2];
			Iterator<Attraction> i = pointer.getPointsOfInterest().iterator();

			for (int index = 0; index < pointer.getPointsOfInterest().size(); index++) {
				Attraction a = i.next();
				data[index][0] = a.getInterestLevel();
				data[index][1] = a.getName();
			}
			JTable table = new JTable(data, columnNames);
			((DefaultTableCellRenderer)table.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.LEFT);
			table.setEnabled(false);
			panel.setLayout(new BorderLayout());
			TableColumn column = null;
			column = table.getColumnModel().getColumn(0);
			column.setPreferredWidth(60);
			column = table.getColumnModel().getColumn(1);
			column.setPreferredWidth(250);
			JScrollPane scroll = new JScrollPane(table, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
			scroll.setPreferredSize(new Dimension(200,200));
			scroll.setEnabled(false);
			table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			panel.add(table.getTableHeader(), BorderLayout.PAGE_START);
			panel.add(scroll, BorderLayout.CENTER);
		}
		}
		panel.repaint();
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
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		CardLayout cl = (CardLayout) this.cards.getLayout();
		this.currentCard = arg0.getActionCommand();
        cl.show(this.cards, this.currentCard);
	}
	
	public String getCurrentCard(){
		JPanel card = null;
		for (Component comp : this.cards.getComponents()) {
		    if (comp.isVisible() == true) {
		        card = (JPanel) comp;
		    }
		}
		return card.getName();
	}
	
	public JPanel getCardCalulcateRoute(){
		return this.cardCalulcateRoute;
	}
	
	public JPanel getCardPointsOfInterest(){
		return this.cardPointsOfInterest;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		this.removeAll();
		this.revalidate();
		this.add(panelStart);
		this.repaint();
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
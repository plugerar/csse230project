package csse230project;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;



public class InfoPanel extends JPanel implements ActionListener, MouseListener{// implements ActionListener{
	MapPanel map;
	
	private MyBoolean booValue = new MyBoolean();
	
	private JPanel panelStart;
    private JPanel cards; //a panel that uses CardLayout
    private JPanel cardPointsOfInterest;
    private JPanel cardCalulcateRoute;
    private JPanel cardCityRating;
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
    private boolean tripSelect = true;
    private int tripWeight;
    protected JTextField tripTextField;
    protected JRadioButton tripDistanceBox;
    protected JRadioButton tripTimeBox;

    //final static String BUTTONPANEL = "Card with JButtons";
    //final static String TEXTPANEL = "Card with JTextField";
	
	public InfoPanel(MapPanel map) throws Exception {
		this.map = map;
		this.setPreferredSize(new Dimension(250, 1000));

		formatPanelStart();
		startWindow();
		this.cards = new JPanel(new CardLayout());
		create();
	}
	
	public void create() throws Exception{
		makeJPanels();
		cardCityRating.setLayout(new BoxLayout(cardCityRating, BoxLayout.Y_AXIS));
		createCards();
		makeJButtons();
		addActionListeners();
		setActionCommands();
		addPanelsToCard();
		this.cards.getComponent(0).setName(cr);
		this.cards.getComponent(1).setName(poi);
		this.cards.getComponent(2).setName(cRate);
		this.cards.getComponent(3).setName(tp);
		this.cards.getComponent(4).setName(cd);

		start.addMouseListener(this);
		addButtonsToPanels();
	}
	
	
	public void setupTripPlanner(JPanel panel) {
        this.tripDistanceBox = new JRadioButton("Distance");
        this.tripDistanceBox.setActionCommand("Distance");
        this.tripTimeBox = new JRadioButton("Time");
        this.tripTimeBox.setActionCommand("Time");
        ButtonGroup group = new ButtonGroup();
        group.add(this.tripDistanceBox);
        group.add(this.tripTimeBox);
        this.tripDistanceBox.addActionListener(this);
        this.tripTimeBox.addActionListener(this);        
        JPanel radioPanel = new JPanel(new GridLayout(0, 1));
        radioPanel.add(this.tripDistanceBox);
        radioPanel.add(this.tripTimeBox);
        
        this.tripTextField = new JTextField(10);
        this.tripTextField.addActionListener(this);        
        JPanel tempPanel = new JPanel(new GridLayout(1,0));
    	tempPanel.add(radioPanel);
    	tempPanel.add(this.tripTextField);
    	
        panel.add(tempPanel,BorderLayout.NORTH);
	}
	
	public void createTripPlanner(JPanel panel) {
		System.out.println("create trip planner");
		panel.removeAll();
		panel.revalidate();
		panel.setLayout(new BorderLayout());
		setupTripPlanner(panel);

		ArrayList<City> arr = MainFrame.mapPanel.getClickedCities();
		if (!arr.isEmpty()) {
			int lastIndex = arr.size() - 1;
			City now = MainFrame.mapPanel.getClickedCities().get(lastIndex);
			if (now != null && this.tripWeight != 0) {
				ArrayList<Trip> trips = TripPlanner.planTrip(now, this.tripWeight, this.tripSelect);
				JTabbedPane tabbedPane = new JTabbedPane();
				for (int i = 0; i < trips.size(); i++) {
					String[] columnNames = { now.getName() };
					Object[][] data = new Object[trips.get(i).getTripCities().size() + 2][1];
					String time = String.format("%.1f", trips.get(i).getTotalTime());
					data[0][0] = "Total Distance: " + trips.get(i).getTotalDistance() + " miles";
					data[1][0] = "Total Time: " + time + " hours";

					Iterator<City> it = trips.get(i).getTripCities().iterator();
					for (int j = 0; j < trips.get(i).getTripCities().size(); j++) {
						City city = it.next();
						data[2 + j][0] = city.getName();
					}

					JTable table = new JTable(data, columnNames);
					((DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.LEFT);
					table.setEnabled(false);
					TableColumn column = null;
					column = table.getColumnModel().getColumn(0);
					column.setPreferredWidth(200);
					table.getTableHeader().setFont(new Font("Arial", Font.PLAIN, 18));	
					table.setFont(new Font("Arial", Font.PLAIN, 16));					
					table.setRowHeight(table.getRowHeight()+5);
					JScrollPane scroll = new JScrollPane(table, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
					scroll.setPreferredSize(new Dimension(200, 700));
					scroll.setEnabled(false);
					tabbedPane.add(scroll, ((Integer) i).toString());
				}
				panel.add(tabbedPane, BorderLayout.SOUTH);

			}
		}
		panel.repaint();
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
		ArrayList<City> cities = MainFrame.struct.cityInterestToArrayList();
		Iterator<City> iCity = cities.iterator();
		Iterator<String> i = MainFrame.struct.getCityNames().iterator();
		String[] columnNames = { "City Name", "Rating" };
		Object[][] data = new Object[MainFrame.struct.getCityMap().size()][2];

		for (int index = 0; index < MainFrame.struct.getCityNames().size(); index++) {
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
		table.getTableHeader().setFont(new Font("Arial", Font.PLAIN, 18));	
		table.setFont(new Font("Arial", Font.PLAIN, 16));					
		table.setRowHeight(table.getRowHeight()+5);
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
		ArrayList<Edge> path = MainFrame.struct.calculateRoute(MainFrame.mapPanel.getClickedCities());
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
		}
		panel.repaint();
	}
	
	
	public void createPointsOfInterest(JPanel panel) throws Exception{// throws Exception{
		System.out.println("cresatePonitsofInterest");
		panel.removeAll();
		panel.revalidate();
		ArrayList<City> arr = MainFrame.mapPanel.getClickedCities();
		if(!arr.isEmpty()){
			int lastIndex = arr.size() - 1;
			City now = MainFrame.mapPanel.getClickedCities().get(lastIndex);
		
		if(now != null){
			String name = now.getName();
			System.out.println(name);
			
			City pointer = MainFrame.struct.getCity(name);
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
			table.getTableHeader().setFont(new Font("Arial", Font.PLAIN, 18));	
			table.setFont(new Font("Arial", Font.PLAIN, 16));					
			table.setRowHeight(table.getRowHeight()+5);
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
	
	private void formatPanelStart(){
		panelStart = new JPanel();
		panelStart.setPreferredSize(new Dimension(250, 1000));
		panelStart.setAlignmentX(LEFT_ALIGNMENT);
		panelStart.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		panelStart.setLayout(new FlowLayout());
		panelStart.setBorder(BorderFactory.createLineBorder(Color.black));
	}
	
	private void makeJPanels(){
		this.cardCalulcateRoute = new JPanel() ;
		this.cardPointsOfInterest = new JPanel() ;
		this.cardCityRating = new JPanel();
		this.cardTripPlanner = new JPanel();
		this.cardCityDescriptions = new JPanel();
	}
	private void createCards() throws Exception{		
		createCalculateRoute(this.cardCalulcateRoute);
		createPointsOfInterest(this.cardPointsOfInterest);
		createCityRatings(cardCityRating);
		createTripPlanner(this.cardTripPlanner);
		createCityDescriptions(this.cardCityDescriptions);
	}
	private void makeJButtons(){
		this.start = new JButton(starting);
        this.calculateRoute = new JButton(cr);
		this.pointsOfInterest = new JButton(poi);
		this.cityRating  = new JButton(cRate);
		this.tripPlanner = new JButton(tp);
		this.cityDescriptions = new JButton(cd);
	}
	private void addActionListeners(){
		this.calculateRoute.addActionListener(this);
		this.pointsOfInterest.addActionListener(this);
		this.cityRating.addActionListener(this);
		this.tripPlanner.addActionListener(this);
		this.cityDescriptions.addActionListener(this);
	}
	private void setActionCommands(){
		this.calculateRoute.setActionCommand(cr);
		this.pointsOfInterest.setActionCommand(poi);
		this.cityRating.setActionCommand(cRate);
		this.tripPlanner.setActionCommand(tp);
		this.cityDescriptions.setActionCommand(cd);		
	}
	private void addPanelsToCard(){
		this.cards.add(this.cardCalulcateRoute, cr);
		this.cards.add(this.cardPointsOfInterest, poi);
		this.cards.add(this.cardCityRating, cRate); 
		this.cards.add(this.cardTripPlanner, tp);
		this.cards.add(this.cardCityDescriptions, cd);
	}
	private void addButtonsToPanels(){
		this.add(start);
		panelStart.add(calculateRoute);
		panelStart.add(pointsOfInterest);
		panelStart.add(cityRating);
		panelStart.add(tripPlanner);
		panelStart.add(cityDescriptions);
		panelStart.add(cards);
	}
	
	//Getters and Setters and Actions and Mouse Listeners---------------------------------------------
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		booValue.setBoo(true);
		CardLayout cl = (CardLayout) this.cards.getLayout();
		this.currentCard = arg0.getActionCommand();
        cl.show(this.cards, this.currentCard);
        
        if (getCurrentCard().equals(tp)) {
        	if (this.tripDistanceBox.isSelected()) this.tripSelect = true;
        	if (this.tripTimeBox.isSelected()) this.tripSelect = false;
        	try {
        		this.tripWeight = Integer.parseInt(this.tripTextField.getText());
        	} catch (NumberFormatException e) {
        		System.out.println("you suck at strings");
        	}
        }
	}
	
	public MyBoolean getBooValue(){
		return booValue;
	}
	
	public void setBooValue(boolean bo){
		booValue.setBoo(bo);
	}
	
	public String getCurrentCard(){
		JPanel card = null;
		for (Component comp : this.cards.getComponents()) {
		    if (comp.isVisible() == true) {
		        card = (JPanel) comp;
		    }
		}
		this.currentCard = card.getName();
		return card.getName();
	}
	
	public JPanel getCardCalulcateRoute(){
		return this.cardCalulcateRoute;
	}
	
	public JPanel getCardPointsOfInterest(){
		return this.cardPointsOfInterest;
	}

	public JPanel getCardTripPlanner(){
		return this.cardTripPlanner;
	}
	
	public JPanel getCardCityDescriptions(){
		return this.cardCityDescriptions;
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		this.removeAll();
		this.revalidate();
		this.add(this.panelStart);
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
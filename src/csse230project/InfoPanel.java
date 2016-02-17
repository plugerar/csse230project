package csse230project;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
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


/** 
 * Describes the info panel to the right of the main screen that allows
 * the user to interact with the map
 */
public class InfoPanel extends JPanel implements ActionListener {
	MapPanel map;
	
	private MyBoolean booValue = new MyBoolean();
	private ArrayList<Edge> path = new ArrayList<>();
	
    private JPanel cards; //a panel that uses CardLayout
    private JPanel cardPointsOfInterest;
    private JPanel cardCalulcateRoute;
    private JPanel cardCityRating;
    private JPanel cardTripPlanner;
    private JPanel cardCityDescriptions;

    private JButton cityRating;
    private JButton pointsOfInterest;
    private JButton calculateRoute;
    private JButton tripPlanner;
    private JButton cityDescriptions;

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

    private boolean calcSelect = true;
    protected JTextField calcStartTextField;
    protected JTextField calcGoalTextField;
    protected JList calcAdditionalTextField;
    protected JRadioButton calcDistanceBox;
    protected JRadioButton calcTimeBox;
	
	public InfoPanel(MapPanel map) throws Exception {
		this.map = map;
		this.setPreferredSize(new Dimension(250, 1000));

		startWindow();
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.cards = new JPanel(new CardLayout());
		create();
	}
	
	public void create() throws Exception{
		makeJPanels();
		this.cardCityRating.setLayout(new BoxLayout(this.cardCityRating, BoxLayout.Y_AXIS));
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

		addButtonsToPanels();
	}

	private void startWindow(){
//		JOptionPane prnt = new JOptionPane();
//		prnt.showMessageDialog(null, "The features of the software");
	}
	
	// Describing the cards-----------------------------------------------------------------
	
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
		panel.removeAll();
		panel.revalidate();
		panel.setLayout(new BorderLayout());
		setupTripPlanner(panel);

		ArrayList<City> arr = MainFrame.mapPanel.getClickedCities();
		if (!arr.isEmpty()) {
			int lastIndex = arr.size() - 1;
			City now = MainFrame.mapPanel.getClickedCities().get(lastIndex);
			if (now != null && this.tripWeight != 0) {
				TripPlanner tripPlanner = new TripPlanner();
				ArrayList<Trip> trips = tripPlanner.planTrip(now, this.tripWeight, this.tripSelect);
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
					((DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer())
							.setHorizontalAlignment(SwingConstants.LEFT);
					table.setEnabled(false);
					TableColumn column = null;
					column = table.getColumnModel().getColumn(0);
					column.setPreferredWidth(200);
					table.getTableHeader().setFont(new Font("Arial", Font.PLAIN, 18));
					table.setFont(new Font("Arial", Font.PLAIN, 16));
					table.setRowHeight(table.getRowHeight() + 5);
					JScrollPane scroll = new JScrollPane(table, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
							ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
					scroll.setPreferredSize(new Dimension(200, 600));
					scroll.setEnabled(false);
					tabbedPane.add(scroll, ((Integer) i).toString());
				}
				panel.add(tabbedPane, BorderLayout.CENTER);

			}
		}
		panel.repaint();
	}

	public void createCityDescriptions(JPanel panel) {
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

	public void setupCalculateRoute(JPanel panel) {
        this.calcDistanceBox = new JRadioButton("Distance");
        this.calcDistanceBox.setActionCommand("Distance");
        this.calcTimeBox = new JRadioButton("Time");
        this.calcTimeBox.setActionCommand("Time");
        ButtonGroup group = new ButtonGroup();
        group.add(this.calcDistanceBox);
        group.add(this.calcTimeBox);
        this.calcDistanceBox.addActionListener(this);
        this.calcTimeBox.addActionListener(this);        
        if (this.calcSelect) {
        	this.calcDistanceBox.setSelected(true);
        	this.calcTimeBox.setSelected(false);
        } else {
        	this.calcTimeBox.setSelected(true);
        	this.calcDistanceBox.setSelected(false);
        }
        JPanel radioPanel = new JPanel(new GridLayout(0, 1));
        radioPanel.add(this.calcDistanceBox);
        radioPanel.add(this.calcTimeBox);

		Font field = new Font("Arial", Font.BOLD, 12);	
        this.calcStartTextField = new JTextField(10);
        this.calcStartTextField.setFont(field);
        this.calcStartTextField.setEditable(false);       
        this.calcStartTextField.setBackground(Color.WHITE);
        this.calcGoalTextField = new JTextField(10);
        this.calcGoalTextField.setFont(field);
        this.calcGoalTextField.setEditable(false);
        this.calcGoalTextField.setBackground(Color.WHITE);
        this.calcAdditionalTextField = new JList();
        JScrollPane scrollPane = new JScrollPane(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setViewportView(this.calcAdditionalTextField);
        scrollPane.setPreferredSize(new Dimension(10,50));
        scrollPane.setEnabled(false);
        JPanel tempPanel = new JPanel(new GridLayout(3,1));
    	tempPanel.add(this.calcStartTextField);
    	tempPanel.add(this.calcGoalTextField);  	
    	tempPanel.add(scrollPane);
    	panel.add(radioPanel,BorderLayout.NORTH);
        panel.add(tempPanel,BorderLayout.NORTH);
	}
	
	public void createCalculateRoute(JPanel panel) throws Exception {
		panel.removeAll();
		panel.revalidate();
		setupCalculateRoute(panel);
		CityStructure struct = WriteDomain.read("usdomain.xml");
		DefaultListModel listModel = new DefaultListModel();
		ArrayList<City> arr = MainFrame.mapPanel.getClickedCities();
		if (!arr.isEmpty()) {
			int lastIndex = arr.size() - 1;
			this.calcStartTextField.setText("Start: " + arr.get(0).getName());
			if (!arr.get(0).equals(arr.get(lastIndex)))
				this.calcGoalTextField.setText("Goal: " + arr.get(lastIndex).getName());
			for (int i = 1; i < lastIndex; i++) {
				listModel.addElement(arr.get(i).getName());
			}
			this.calcAdditionalTextField.setModel(listModel);

			ArrayList<Edge> path = struct.calculateRoute(arr, this.calcSelect);
			String[] columnNames = { "City Name", "Time", "Distance" };
			Object[][] data = new Object[path.size() + 4][3];
			if (!path.isEmpty()) {
				Iterator<Edge> i = path.iterator();
				Edge edge = i.next();
				int totalDistance = 0;
				double totalTime = 0;
				data[0][0] = edge.getCity1().getName();
				data[0][1] = 0;
				data[0][2] = 0;
				data[1][1] = edge.getTime();
				data[1][2] = edge.getDistance();
				totalDistance += edge.getDistance();
				totalTime += edge.getTime();
				for (int index = 1; index < path.size(); index++) {
					edge = i.next();
					data[index][0] = edge.getCity1().getName();
					data[index + 1][1] = edge.getTime();
					data[index + 1][2] = edge.getDistance();
					totalDistance += edge.getDistance();
					totalTime += edge.getTime();

				}
				data[path.size()][0] = edge.getCity2().getName();

				data[path.size() + 2][0] = "Total";
				data[path.size() + 2][1] = totalTime;
				data[path.size() + 2][2] = totalDistance;

				JTable table = new JTable(data, columnNames);
				table.setEnabled(false);
				TableColumn column = null;
				column = table.getColumnModel().getColumn(0);
				column.setPreferredWidth(100);
				column = table.getColumnModel().getColumn(1);
				column.setPreferredWidth(50);
				table.getTableHeader().setFont(new Font("Arial", Font.PLAIN, 16));
				table.setFont(new Font("Arial", Font.PLAIN, 16));
				table.setRowHeight(table.getRowHeight() + 5);
				JScrollPane scroll = new JScrollPane(table, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
						ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
				scroll.setPreferredSize(new Dimension(240, 700));
				scroll.setEnabled(false);
				panel.setPreferredSize(new Dimension(240, 1000));
				panel.add(scroll, BorderLayout.SOUTH);

			}
		}
		this.booValue.setBoo(true);
		panel.repaint();
		MainFrame.mapPanel.repaint();
	}

	
	public void createPointsOfInterest(JPanel panel) throws Exception{
		panel.removeAll();
		panel.revalidate();
		ArrayList<City> arr = MainFrame.mapPanel.getClickedCities();
		if(!arr.isEmpty()){
			int lastIndex = arr.size() - 1;
			City now = MainFrame.mapPanel.getClickedCities().get(lastIndex);
		
		if(now != null){
			String name = now.getName();
			
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

	//Making the panel-----------------------------------------------------------------
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
		createCityRatings(this.cardCityRating);
		createTripPlanner(this.cardTripPlanner);
		createCityDescriptions(this.cardCityDescriptions);
	}
	private void makeJButtons(){
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
		this.add(this.calculateRoute);
		this.add(this.pointsOfInterest);
		this.add(this.cityRating);
		this.add(this.tripPlanner);
		this.add(this.cityDescriptions);
		this.add(this.cards);
	}
	
	//Getters and Setters and Actions and Mouse Listeners---------------------------------------------
	
	public ArrayList getPath(){
		return this.path;
	}
	
	@Override

	public void actionPerformed(ActionEvent ae) {
		CardLayout cl = (CardLayout) this.cards.getLayout();
		this.currentCard = ae.getActionCommand();
        cl.show(this.cards, this.currentCard);
        
        if (ae.getSource().equals(this.calculateRoute)) {
        	MainFrame.mapPanel.getClickedCities().clear();
        	try {
				createCalculateRoute(this.cardCalulcateRoute);
			} catch (Exception exception) { 
				// will never have a problem
			}
        }
        
        if (getCurrentCard().equals(tp)) {
        	if (this.tripDistanceBox.isSelected()) this.tripSelect = true;
        	if (this.tripTimeBox.isSelected()) this.tripSelect = false;
        	try {
        		this.tripWeight = Integer.parseInt(this.tripTextField.getText());
        	} catch (NumberFormatException e) {
				// we don't care about the problems
        	}
        }

        if (getCurrentCard().equals(cr)) {
        	if (this.calcDistanceBox.isSelected()) this.calcSelect = true;
        	if (this.calcTimeBox.isSelected()) this.calcSelect = false;
        }
	}
	
	public MyBoolean getBooValue(){
		return this.booValue;
	}
	
	public void setBooValue(boolean bo){
		this.booValue.setBoo(bo);
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

}
package csse230project;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class InfoPanel extends JPanel{
	City[] cityList = { new City("New York",10,10), new City("California",30,20), new City("Terre Haute",20,30), new City("Washington",60,10), new City("Boston",50,40), new City("Olympia",60,50) };

	MapPanel map = new MapPanel();
	CityStructure cityStructure=new CityStructure();
	public InfoPanel() {
		this.setBounds(0, 50, 950, 950);
		this.setSize(950, 950);
		populateCityStructure();
		
		JComboBox cityStart = new JComboBox(cityList);
		cityStart.setSelectedIndex(4);
		cityStart.addActionListener(map);
		JComboBox cityEnd = new JComboBox(cityList);
		cityEnd.setSelectedIndex(4);
		cityEnd.addActionListener(map);
		JLabel label = new JLabel("Shows shortest path along with the distance and time info");
		JButton calcRoute = new JButton("calculateRoute");
		JButton pointsOfInterest = new JButton("pointsOfInterest");
		JButton cityRating  = new JButton("cityRating");
		//this.add()
		this.add(cityStart);
		this.add(cityEnd);
		this.add(label);
		this.add(calcRoute);
		this.add(pointsOfInterest);
		this.add(cityRating);
	}
	public void populateCityStructure()
	{
		cityList[0].addNeighbor(new Edge(cityList[0], cityList[3], 50, 50)); //NY to Wash
		cityList[0].addNeighbor(new Edge(cityList[0], cityList[5], 20, 20)); //NY to Olymp
		cityList[0].addNeighbor(new Edge(cityList[0], cityList[2], 10, 10)); //NY to TH
		
		cityList[1].addNeighbor(new Edge(cityList[1], cityList[3], 10, 10)); //cal to Wash
		cityList[1].addNeighbor(new Edge(cityList[1], cityList[4], 16, 16)); //cal to Boston
		cityList[1].addNeighbor(new Edge(cityList[1], cityList[2], 5, 5)); //cal to TerreHaute
		
		cityList[2].addNeighbor(new Edge(cityList[2], cityList[1], 10, 10)); //TerreHaute to Cal
		cityList[2].addNeighbor(new Edge(cityList[2], cityList[4], 5, 5)); //TerreHaute to Boston
		cityList[2].addNeighbor(new Edge(cityList[2], cityList[0], 10, 10)); //TerreHaute to NY
		
		cityList[3].addNeighbor(new Edge(cityList[3], cityList[1], 10, 10)); //WA to Cal
		cityList[3].addNeighbor(new Edge(cityList[3], cityList[5], 8, 8)); //WA to Olymp
		cityList[3].addNeighbor(new Edge(cityList[3], cityList[0], 50, 50)); //WA to NY
		
		cityList[4].addNeighbor(new Edge(cityList[4], cityList[1], 16, 16)); //Boston to cal
		cityList[4].addNeighbor(new Edge(cityList[4], cityList[2], 5, 5)); //TerreHaute to Boston

		cityList[5].addNeighbor(new Edge(cityList[5], cityList[3], 8, 8)); //Olymp to WA
		cityList[5].addNeighbor(new Edge(cityList[5], cityList[0], 20, 20)); //Olymp to NY
		for(int i=0;i<=5;i++)
		{
		this.cityStructure.add(this.cityList[i]);
		}
	}
//	   create the open list of nodes, initially containing only our starting node
//	   create the closed list of nodes, initially empty
//	   while (we have not reached our goal) {
//	       consider the best node in the open list (the node with the lowest f value)
//	       if (this node is the goal) {
//	           then we're done
//	       }
//	       else {
//	           move the current node to the closed list and consider all of its neighbors
//	           for (each neighbor) {
//	               if (this neighbor is in the closed list and our current g value is lower) {
//	                   update the neighbor with the new, lower, g value 
//	                   change the neighbor's parent to our current node
//	               }
//	               else if (this neighbor is in the open list and our current g value is lower) {
//	                   update the neighbor with the new, lower, g value 
//	                   change the neighbor's parent to our current node
//	               }
//	               else this neighbor is not in either the open or closed list {
//	                   add the neighbor to the open list and set its g value
//	               }
//	           }
//	       }
//	   }

}
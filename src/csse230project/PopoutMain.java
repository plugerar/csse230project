package csse230project;

import java.util.ArrayList;

import javax.swing.JFrame;

public class PopoutMain {

	public static void main(String[] args) {
		// City identifier
		String cityName = "Harrisburg";
		
		// creates a frame
		PopoutFrame frame = new PopoutFrame(cityName);
		frame.setResizable(false);
		
		// normal frame operations
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
		
	private void testing() {
		ArrayList<String> cities = new ArrayList<>();
		cities.add("Albany");
		cities.add("Annapolis");
		cities.add("Atlanta");
		cities.add("Augusta");
		cities.add("Austin");
		cities.add("Baton Rouge");
		cities.add("Bismarck");
		cities.add("Boise");
		cities.add("Boston");
		cities.add("Carson City");
		cities.add("Charleston");
		cities.add("Cheyenne");
		cities.add("Chicago");
		cities.add("Columbia");
		cities.add("Columbus");
		cities.add("Concord");
		cities.add("Denver");
		cities.add("Des Moines");
		cities.add("Dover");
		cities.add("Frankfort");
		cities.add("Harrisburg");
		cities.add("Hartford");
		cities.add("Helena");
		cities.add("Indianapolis");
		cities.add("Jackson");
		cities.add("Jefferson City");
		cities.add("Lansing");
		cities.add("Lincoln");
		cities.add("Little Rock");
		cities.add("Los Angeles");
		cities.add("Madison");
		cities.add("Montgomery");
		cities.add("Montpelier");
		cities.add("Nashville");
		cities.add("New York City");
		cities.add("Oklahoma City");
		cities.add("Olympia");
		cities.add("Phoenix");
		cities.add("Pierre");
		cities.add("Providence");
		cities.add("Raleigh");
		cities.add("Richmond");
		cities.add("Sacramento");
		cities.add("Salem");
		cities.add("Salt Lake City");
		cities.add("San Francisco");
		cities.add("Santa Fe");
		cities.add("Seattle");
		cities.add("Springfield");
		cities.add("St. Louis");
		cities.add("St. Paul");
		cities.add("Tallahassee");
		cities.add("Terre Haute");
		cities.add("Topeka");
		cities.add("Trenton");
		cities.add("Washington D.C.");
		
		for (String city : cities) {
			// creates a frame
			PopoutFrame frame = new PopoutFrame(city);
			frame.setResizable(false);
			
			// normal frame operations
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);
		}
	}
}

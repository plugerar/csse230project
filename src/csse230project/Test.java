package csse230project;

import java.util.ArrayList;
import java.util.Iterator;

public class Test {

	public static void main(String[] args) {
		CityStructure map = new CityStructure();
		
		map.addCity("Terre Haute", 50, 50);
		
		ArrayList<Attraction> seattlePOIs = new ArrayList<>();
		seattlePOIs.add(new Attraction("Space Needle", 10));
		seattlePOIs.add(new Attraction("Chihuly Glass", 8));
		seattlePOIs.add(new Attraction("Pike Place Market", 5));
		map.addCity("Seattle", 0, 0, seattlePOIs);
		
		ArrayList<Attraction> nycPOIs = new ArrayList<>();
		nycPOIs.add(new Attraction("Empire State Building", 10));
		nycPOIs.add(new Attraction("Central Park", 8));
		nycPOIs.add(new Attraction("Statue of Liberty", 10));
		nycPOIs.add(new Attraction("Ellis Island", 8));
		nycPOIs.add(new Attraction("Times Square", 5));
		map.addCity("NYC", 0, 100, nycPOIs);
		
		ArrayList<Attraction> laPOIs = new ArrayList<>();
		laPOIs.add(new Attraction("DisneyLand", 10));
		laPOIs.add(new Attraction("Universal Studios Hollywood", 5));
		laPOIs.add(new Attraction("Hollywood Wax Museum", 5));
		map.addCity("LA", 100, 0, laPOIs);

		map.linkCity("Terre Haute", "LA", 29, 1993);
		map.linkCity("LA", "Seattle", 17, 1137);
		map.linkCity("Terre Haute", "Seattle", 32, 2184);
		map.linkCity("Terre Haute", "NYC", 12, 787);
		
		System.out.println(map.getCityList().toString());
		Iterator<City> interestList = map.getCityInterestList().iterator();
		while (interestList.hasNext()) {
			System.out.println(interestList.next());			
		}

	}

}

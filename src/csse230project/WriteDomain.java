package csse230project;
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

/**
 * Run this file to generate the XML file
 */
public class WriteDomain {
	private static CityStructure map = new CityStructure();

	/**
	 * Run this to generate the usdomain.xml file
	 */
    public static void main(String [] args) throws Exception {
    	loadCities();
    	loadPOI();
    	loadLinks();
		
        write(map, "usdomain.xml");
        CityStructure test = read("usdomain.xml");
		System.out.println(test.getCityMap().toString());        
        System.out.println(test.cityInterestToArrayList().toString());
     }

	/**
	 * Loads the cities from the given text file
	 */
    public static void loadCities() {
        String line = null;
        String fileName = "states.txt";
        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);            
            while((line = bufferedReader.readLine()) != null) {
            	String[] ar=line.split(",");
            	String cityName = ar[0];
            	int xCoord = Integer.parseInt(ar[1]);
            	int yCoord = Integer.parseInt(ar[2]);
            	map.addCity(cityName, xCoord, yCoord);
            }   
            bufferedReader.close();         
        }
        catch(FileNotFoundException ex) {
            System.out.println("Unable to open file '" + fileName + "'");                
        }
        catch(IOException ex) {
            System.out.println("Error reading file '" + fileName + "'");
        }
    }

	/**
	 * Loads the POIs from the given text file
	 */
    public static void loadPOI() {
        String line = null;
        String fileName = "poi.txt";
        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);            
            while((line = bufferedReader.readLine()) != null) {
            	String[] ar=line.split(",");
            	String cityName = ar[0];                
            	for (int i = 1; i < ar.length; i = i + 2) {
            		String poi = ar[i];
                	int interestLevel = Integer.parseInt(ar[i+1]);
                	Attraction attraction = new Attraction(poi,interestLevel);
                	map.getCity(cityName).setPointOfInterest(attraction);
            	}
            }  
            bufferedReader.close();         
        }
        catch(FileNotFoundException ex) {
            System.out.println("Unable to open file '" + fileName + "'");                
        }
        catch(IOException ex) {
            System.out.println("Error reading file '" + fileName + "'");
        }
    }

	/**
	 * Loads the links from the given text file
	 */
    public static void loadLinks() {
        String line = null;
        String fileName = "links.txt";
        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);            
            while((line = bufferedReader.readLine()) != null) {
            	String[] ar=line.split(",");
            	String city1 = ar[0];
            	String city2 = ar[1];
            	int distance = Integer.parseInt(ar[2]);
            	double time = Double.parseDouble(ar[3]);
            	map.linkCity(city1, city2, distance, time); 
            }   
            bufferedReader.close();         
        }
        catch(FileNotFoundException ex) {
            System.out.println("Unable to open file '" + fileName + "'");                
        }
        catch(IOException ex) {
            System.out.println("Error reading file '" + fileName + "'");
        }
    }

	/**
	 * Writer for the XML document
	 */
    public static void write(CityStructure map, String filename) throws Exception{
        XMLEncoder encoder =
           new XMLEncoder(
              new BufferedOutputStream(
                new FileOutputStream(filename)));
        encoder.writeObject(map);
        encoder.close();
    }

	/**
	 * Reader for the XML document
	 */
    public static CityStructure read(String filename) throws Exception {
        XMLDecoder decoder = 
        	new XMLDecoder(
        			new BufferedInputStream(
        					new FileInputStream(filename)));
        CityStructure map =(CityStructure) decoder.readObject();
        decoder.close();
        return map;
    }
}
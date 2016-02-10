package csse230project;

/**
 * Defines an attraction object (POI)
 * Has a name and an interest level
 */
public class Attraction {
	private String name;
	private Integer interestLevel;
	
	/**
	 * Default constructor for file i/o
	 */
	public Attraction() {
	}
	
	/**
	 * Constructor with name and interest level
	 */
	public Attraction(String name, Integer interestLevel) {
		this.name = name;
		this.interestLevel = interestLevel;
	}
	
	/**
	 * Getter for name
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Setter for name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Getter for interest level
	 */
	public Integer getInterestLevel() {
		return this.interestLevel;
	}
	
	/**
	 * Setter for interest level
	 */
	public void setInterestLevel(Integer interestLevel) {
		this.interestLevel = interestLevel;
	}
	
	@Override
	public String toString(){
		String s = "";
		s += "POI: " + this.name + "\n";
		s += "InterestLevel: " + this.interestLevel + "\n";
		return s;
	}
	
}
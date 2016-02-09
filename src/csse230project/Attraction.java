package csse230project;

public class Attraction {
	private String name;
	private Integer interestLevel;
	
	public Attraction(String name, Integer interestLevel) {
		this.name = name;
		this.interestLevel = interestLevel;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Integer getInterestLevel() {
		return this.interestLevel;
	}
	
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
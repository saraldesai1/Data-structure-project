
public class Country {
	private String Countryname;
	private String Capitol;
	private int Population;
	private double GDP;
	private int Cases;
	private int Death;
	private double CFR;
	
	/**
	 * this where i assign all private class. also i get all the information from the project 2 
	 * class that scan and transfer csv file in here.
	 * @param countryname 
	 * @param capitol
	 * @param population
	 * @param gdp
	 * @param cases
	 * @param death
	 */
	public Country(String countryname, String capitol, int population, double gdp, int cases, int death) {
		
		Countryname = countryname;
		Capitol = capitol;
		Population = population;
		GDP = gdp;
		Cases = cases;
		Death = death;
	}
	/**
	 * @return Countryname return countries name.
	 */
	public String getCountryname() {
		return Countryname;
	}
	/**
	 * 
	 * @param countryname  countryname get assign to Countryname from private call.
	 */
	public void setCountryname(String countryname) {
		Countryname = countryname;
	}
	/**
	 * 
	 * @return Capitol return Capitol from each country. 
	 */
	public String getCapitol() {
		return Capitol;
	}
	/**
	 * 
	 * @param capitol  capitol get assign to Capitol from private call.
	 */
	public void setCapitol(String capitol) {
		Capitol = capitol;
	}
	/**
	 * 
	 * @return Population this return population in number for each country.
	 */
	public int getPopulation() {
		return Population;
	}
	/**
	 * 
	 * @param population this population get assign to Population from private call.
	 */
	public void setPopulation(int population) {
		Population = population;
	}
	/**
	 * 
	 * @return GDP return GDP in number for each country.
	 */
	public double getGDP() {
		return GDP;
	}
	/**
	 * 
	 * @param gdp this gdp get assign to GDP from private call.
	 */
	public void setGDP(double gdp) {
		GDP = gdp;
	}
	/**
	 * 
	 * @return Cases return cases in the number for each county.
	 */
	public int getCases() {
		return Cases;
	}
	/**
	 * 
	 * @param cases this cases get assign to Cases from private call.
	 */
	public void setCases(int cases) {
		Cases = cases;
	}
	/**
	 * 
	 * @return Death return death in number for each country.
	 */
	public int getDeath() {
		return Death;
	}
	/**
	 * 
	 * @param death this death get assign to Death from private call.
	 */
	public void setDeath(int death)
	{
		Death = death;
	}
	/**
	 * In here i get the percentage of CFR for each country.
	 * @return CFR this return CFR number.
	 */
	public double getCFR()
	{
		CFR=((double)Death/Cases)*100;
		return CFR;
	}
	@Override
	/**
	 * I can call this function to different class and it already formated so i don't need keep format print function for print call for different class.
	 * return String format this just return each country information in formated order. 
	 */
	public String toString()
	{
		return String.format("%-35s %20s %20s %20s %20s %20s\n", getCountryname(),getCapitol(),getPopulation(),getGDP(),getCases(),getDeath());
	}
}

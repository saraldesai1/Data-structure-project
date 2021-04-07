
public class Country {
	private String Countryname;
	private String Capitol;
	private int Population;
	private double GDP;
	private int Cases;
	private int Death;
	private double GDPTOTAL;
	private double CFR;
	public Country(String countryname, String capitol, int population, double gdp, int cases, int death) {
		
		Countryname = countryname;
		Capitol = capitol;
		Population = population;
		GDP = gdp;
		Cases = cases;
		Death = death;
	}
	public String getCountryname() {
		return Countryname;
	}
	public void setCountryname(String countryname) {
		Countryname = countryname;
	}
	public String getCapitol() {
		return Capitol;
	}
	public void setCapitol(String capitol) {
		Capitol = capitol;
	}
	public int getPopulation() {
		return Population;
	}
	public void setPopulation(int population) {
		Population = population;
	}
	public double getGDP() {
		return GDP;
	}
	public void setGDP(double gdp) {
		GDP = gdp;
	}
	public int getCases() {
		return Cases;
	}
	public void setCases(int cases) {
		Cases = cases;
	}
	public int getDeath() {
		return Death;
	}
	public void setDeath(int death)
	{
		Death = death;
	}
	public double getCFR()
	{
		CFR=(double)Death/Cases;
		return CFR;
	}
	public double getGDPTOTAL()
	{
		GDPTOTAL=(double)GDP/Population;
		return GDPTOTAL;
	}
	public int compare(Country country)
	{
		return this.Countryname.compareTo(country.Countryname);
	}
	@Override
	public String toString()
	{
		return String.format("%-35s %20s %20s %20s %20s %20s %20s\n", getCountryname(),getCapitol(),getPopulation(),getGDP(),getCases(),getDeath(),getCFR());
	}
}

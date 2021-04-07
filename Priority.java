/**
 * This class priorities the cfr rates from the csv file and put them into 
 * different order.
 * @author saral
 *@version 10/01/2020
 */
public class Priority {
	private int maxsize;
	private Country[] country;
	private int nitems;
	
	/**
	 * In this function i am assign maxsize to size of the file
	 * @param size this is get it value from the main class.
	 * @param maxsize this is a size of each priority class.
	 * @param country assign the maxsize of an array for priority.
	 * @param nitems this tells how many item are in each priority class.
	 */
	public Priority(int size)
	{
		maxsize=size;
		country = new Country[maxsize];
		nitems=0;
	}
	/**
	 * in here i insert each priority and swap them into ascending order for printing. 
	 * @param country2 in here i get each country CFR from project 2 class.
	 */
	public void insert(Country country2)
	{
		int i=0;
		if(nitems==0)
		{
			country[nitems++]=country2;
		}
		else
		{
			for(i=nitems-1;i>=0;i--)
			{
				if(country2.getCFR()<country[i].getCFR())
				{
					country[i+1]=country[i];
				}
				else
				{
					break;
				}
			}
			country[i+1]=country2;
			nitems++;
		}
	}
	/**
	 * in here i can remove each priority into the call.
	 * @return country[--nitems] when i call this function in project 2 class i can remove each priority individual
	 */
	public Country remove()
	{
		return country[--nitems];
	}
	/**
	 * this just prints each priority.
	 */
	public void print()
	{
		System.out.printf("%-35s %20s %20s %20s %20s %20s\n","Country_name","Country_capital","Population","GDP","COVID19_Cases","COVID19_Deaths");
		System.out.println("_______________________________________________________________________________________________________________________________________________");
		for(int i=0;i<nitems;i++)
		{
			System.out.print(country[i].toString());
		}
	}
	/**
	 * this check if the priority items are empty.
	 * @return nitems this return a zero. 
	 */
	public boolean isEmpty()
	{
		return (nitems==0);
	}
	/**
	 * if the priority class get full with the length that is assign to maxsize it means that the priority class is full and something is wrong.
	 * @return nitems this just returns maxzise in number.
	 */
	public boolean isFull()
	{
		return (nitems==maxsize);
	}

}

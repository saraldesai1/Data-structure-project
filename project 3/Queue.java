
public class Queue 
{
	private Link first;
	private Link last;
	/**
	 * In this constructor the Link are getting initialized to null.
	 * @param first it is a reference to first link
	 * @param last it is a reference to last link.
	 */
	public Queue()
	{
		first=null;
		last=null;
	}
	/**
	 * In this method all the country inserted from the main class get link to last if it is empty. Otherwise
	 * it checks first.previous becomes the old link and newlink.next becomes first.
	 * @param country is passing into Link class.
	 */
	public void insertfront(Country country)
	{
		Link newLink = new Link(country);
		if(isEmpty())
		{
			last=newLink;
		}
		else
		{
			first.previous=newLink;
		}
			newLink.next=first;	
			first=newLink;	
	}
	/**
	 * In this method all the link getting inserted get check if the this class is empty if so the newlink become first. Otherwise
	 * the newlink become last.
	 * @param country is getting pass into Link class.
	 */
	public void insertend(Country country)
	{
		Link newLink = new Link(country);
		if(isEmpty())
		{
			first=newLink;
		}
		else
		{
			last.next=newLink;
		}
			newLink.previous=last;
			last=newLink;
	}
	/**
	 * this removes all the country from the first.
	 * @return temp.county it return each county link one at a time.
	 * @return null means that the all country are remove from the frist link.
	 */
	public Country removefront()
	{
		Link temp = first;
			
			if(first.next==null)
			{
				last=null;
			}
			else 
				first.next.previous=null;
				first=first.next;
				return temp.country;
	}
	/**
	 * this removes all the country from the last.
	 * @return temp.county it return each link one at a time.
	 * @return null this means that all country are removed from the last.
	 */
	public Country removeend() 
	{
		Link temp = last;
		if(first.next==null)
		{
			first=null;
		}
		else
			last.previous.next=null;
			last=last.previous;
			return temp.country;	
	}
	/**
	 * this method delete all the the country CFR between 2.5 to 3.5. 
	 * @param CFR_low this is a reference from main class is 2.5%.
	 * @param CFR_high this is a reference from main class is 3.5%.
	 * @return i if the country is delete from the link it returns true otherwise false for not deleted countries.
	 */
	public boolean delet(double CFR_low, double CFR_high)
	{
			boolean i=true;
			Link current=first;
			if(i==true)
			{
			while(current!=null)
			{
				if(current.country.getCFR()>=CFR_low && current.country.getCFR()<CFR_high)
				{
					if(current==first)
					{
						first=current.next;
					}
					else
					{
						current.previous.next=current.next;
					}
					if(current==last)
					{
						last=current.previous;
					}
					else
					{
						current.next.previous=current.previous;
					}
				}
				current=current.next;
			}
			return i;
		}
			else
				return i;
	}
	/**
	 * this just prints the all object of the stack class from first to last.
	 */
	public void print()
	{
			System.out.printf("%-35s %20s %20s %20s %20s %20s\n","Country_name","Country_capital","Population","GDP","COVID19_Cases","COVID19_Deaths");
			System.out.println("_______________________________________________________________________________________________________________________________________________");
			Link current = first;
			while(current!=null)
			{
			System.out.print(current.country.toString());
			current=current.next;
			}
	}
	/**
	 * this method checks for if this class is empty .
	 * @return true this means that the whole class is empty.
	 * @return false this means that the class has items on it. 
	 */
	public boolean isEmpty()
	{
		if(first==null)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	/**
	 * @return false this always return false.
	 */
	public boolean isFull()
	{
		return false;
	}
}

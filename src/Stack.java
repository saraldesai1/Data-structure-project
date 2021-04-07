/**
 * The link class stores all the variable needed for the link 
 * @author Saral Desai
 * @version 10/24/2020
 */
class Link
{
	public Country country;
	public Link next;
	public Link previous;
	/**
	 * In this constructor all the public variable gets initialized
	 * @param Countr is getting assign to country object so it can call in different classes.
	 * @param next this goes to next link when you insert or remove.
	 * @param previous this goes back to last link so it can make link go back and front.
	 */
	public Link(Country Countr)
	{
		country=Countr;
		next=null;
		previous=null;
	}
}
/**
 * in this class every item get link and it just print from first to last without changing any order.
 * 
 */
public class Stack 
{
	private Link first;
	private Link last;
	/**
	 * in this constructor all the variable are getting initialized.
	 * @param first is the first link in list 
	 * @param last is the last link in list.
	 */
	public Stack()
	{
		first=null;
		last=null;
	}
	/**
	 * here i just push whole CSV into a link.
	 * <p> this check if class is full. The first link is a last link. Where after that the newlink.next
	 *  become first link and keeps going until the whole CSV file is scanned.
	 * @param co is a countries the where each country becomes their own link and get assign to newlink variable.
	 */
	public void push(Country co)
	{
		Link newLink = new Link(co);
		if(isFull())
		{
			last=newLink;
		}
		else
		newLink.next=first;
		first=newLink;
	}
	/**
	 * this class pop every items from push function and clear the stack class. 
	 * <p>in here i create a link where it start from first and keeps going until first and last become null.
	 * @return temp.country is return each country from first to last.
	 */
	public Country pop()
	{
			Link temp = first;
			if(first.next==null)
			{
				last=null;
			}
			first=first.next;
			return temp.country;
	}
	/**
	 * this just prints the all object of the stack class from first to last.
	 */
	public void print()
	{
		Link current = first;
		System.out.printf("%-35s %20s %20s %20s %20s %20s\n","Country_name","Country_capital","Population","GDP","COVID19_Cases","COVID19_Deaths");
		System.out.println("_______________________________________________________________________________________________________________________________________________");
		while(current!=null)
		{
			System.out.print(current.country.toString());
			current = current.next;
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
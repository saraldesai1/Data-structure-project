
public class Stack {
	private int maxsize;
	private Country[] country;
	private int top;
	/**
	 * in this function everything get assign to their private call.
	 * @param size this just tells the size of the file.
	 * @param maxsize this is same a size of the CSV file.
	 * @param country in here i get the maxsize of the CSV file.
	 * @param top this just tell what is in the top of CSV file it it return -1 that means that the CSV file is empty.
	 */
	public Stack(int size)
	{
		maxsize = size;
		country = new Country[maxsize];
		top=-1;
	}
	/**
	 * here i just push whole CSV file into this funtion.
	 * @param count this the country array from the CSV file and is call in project 2 class.
	 */
	public void push(Country count) 
	{
		country[++top]=count;
		
	}
	/**
	 * this class pop every items from push function and clear the stack class. 
	 * @return null this means that there want anything to began with when we scanned the CSV file
	 * @return country[top--] this just clear the each data separately and it keeps going until it removes everything.
	 */
	public Country pop()
	{
		if(isEmpty())
		{
			
			return null;
		}
		else {
		return country[top--];
		}
	}
	/**
	 * this just prints the all object of the stack class from top to bottom.
	 */
	public void printStack()
	{
		System.out.printf("%-35s %20s %20s %20s %20s %20s\n","Country_name","Country_capital","Population","GDP","COVID19_Cases","COVID19_Deaths");
		System.out.printf("_______________________________________________________________________________________________________________________________________________\n");
		for(int i=0;i<maxsize;i++)
		{
			System.out.print(country[i].toString());
		}
	}
	/**
	 * this checks if the CSV file is empty or not.
	 * @return top==-1 if this reutrn -1 that means that the CSV file is empty.
	 */
	public boolean isEmpty()
	{
		return (top==-1);
	}
	/**
	 * check if the CSV file is completely scanned.
	 * @return top==maxsize-1 this returns completely size of the file to make sure we get all the data from CSV file.
	 */
	public boolean isFull()
	{
		return (top ==maxsize-1);
	}
}

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
/**
 * Project 3 class call all the other class. this project 2 class is created for 
 * stack and queue. show how everything prints from stack and queue with links.
 * <p>in the stack class i am making double ended singly link list. It prints from first 
 * to last.
 * <p>in the queue class i am making double ended doubly link list and when the first item is inserted and the second 
 * item is last and keeps going until all the file are inserted.  
 * 
 * @author Saral Desai
 * @version 10/24/2020
 *
 */
public class Project3 {
	/**
	 * The loadfile function ask user that file they want to scan into this code for stack and priority queue. 
	 * <p>this function calls the country class to transfer content of the CSV file into country class.
	 * 
	 *  @param UserFileName //ask user to input the file name.
	 *  @param line It reads each line from CSV.
	 *  @param lines it count the records in the file.
	 *  @param list It breaks each line into separate array for country class.
	 *  @param country it is a country class array call to transfer CSV file into different parts after comma separated.
	 *  @return lines this returns a number which tell how much data is in CSV file.
	 *  @exception if user puts wrong file name it put throw exception or if the file is empty.
	 */
	public static int loadfile(Country[] country)
	{
		String UserFileName;
		String line;
		int lines = 0;
		Scanner Input = new Scanner(System.in);
		System.out.println("Linked lists:");
		System.out.print("Enter your filename:");	
		
		UserFileName=Input.nextLine();
		UserFileName = "src/" + UserFileName;
		try
		{
			BufferedReader br = new BufferedReader(new FileReader(UserFileName));
			br.readLine();
			while((line=br.readLine())!=null)
			{
				
				String [] list = line.split(","); 
				country[lines]=new Country(list[0],list[1],	Integer.parseInt(list[2]),Double.parseDouble(list[3]),Integer.parseInt(list[4]),Integer.parseInt(list[5]));
				lines++;	
			}
			
			br.close();
			
		}
		catch (FileNotFoundException e) 
		{
			lines=-1;
		}
		catch(IOException e)
		{
			System.out.printf("Cant read file");
		}
		Input.close();
		return lines;
		
	}
	/**
	 * In the main class there are two more classes are created stack and queue. The only thing the main class do is 
	 * call all other class.<p>While creating stack and queue class nothing has been passes because we aren't sure how
	 * many items are going to be there.<p>The stack class makes double ended singly link list where there it only can go on
	 * one direction. when the stack class prints it prints from first to last item. While removing it removes from last to first.
	 * All the country pushed are between 1% to 10% CFR.
	 * <p>In the queue class there is a double ended doubly link list is created where link can go back and forth. While insert in an items there are 
	 * two call insert front and end what is does when i pop first item from the stack it inser front of link and second at the end of link and keeps 
	 * going until the whole file is pop out of stack class. Also it does the same thing for remove front and back but it keeps going until the queue class is empty.
	 * While printing queue the every even number inserted is at first and odd at bottom.<p>In main class there is method call delete where it get
	 * two parameters to delete countries between 2.5 to 3.5 CFR.  
	 * @param args
	 * @param Country i am creating country class array where all the data is store from csv file.
	 * @param length this call the loadfile function and get length of the record
	 * @param stack creating a class call stack.
	 * @param Queue creating a class call queue.
	 * @param CFR_low is to pass the reference into queue class.
	 * @param CFR_high is to pass the reference into queue class.
	 * @param items is there to insert item first or second in queue it checks if item%2==0 then first otherwise second.
	 * @param items2 does the same things as item but for the remove method.
	 */
	public static void main(String[] args) {
		Country[] country = new Country[1000];
		int length = loadfile(country);
		Stack stack = new Stack();
		Queue queue = new Queue();
		double CFR_low=2.5;
		double CFR_high=3.5;
		int items=0,items2=0;
		for(int i=0;i<length;i++)
		{
			
			if(country[i].getCFR()>=1.0 && country[i].getCFR()<10)
			{
				stack.push(country[i]);
			}
		}
		System.out.print("\nStack Contents: \n");
		stack.print();
		while(!stack.isEmpty())
		{
			if(items%2==0)
			{
				queue.insertfront(stack.pop());
			}
			else
			{
				queue.insertend(stack.pop());
			}
			items++;
		}
		System.out.print("\nQueue Contents: \n");
		queue.print();;
		queue.delet(CFR_low,CFR_high);
		System.out.print("\nQueue Contents after deleting: \n");
		queue.print();;
		while(!queue.isEmpty())
		{
			if(items2%2==0)
			{
				stack.push(queue.removefront());
			}
			else
			{
				stack.push(queue.removeend());
			}
			items2++;
		}
		System.out.print("\nStack Contents: \n");
		stack.print();
	}
}

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;


public class Project2 {
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
		System.out.println("Stack or Priority Queue Contents");
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
	 * Main function only call all the other classes. In here i also have created different classes for this project.
	 * <p>The stack call prints the information in last in first out order means that whatever was scanned first will 
	 * print last and last will print first. Stack push call put the country class CSV file and push that into in Stack class.
	 * Stack pop call clears everything from that class so I can push new items otherwise it will run out of bound because its an assign length. 
	 * <p>The priority class prints everything in ascending order. it scan each country and see what are the CFR percentage is 
	 * then it breaks them into different priority groups. Priority class get it CFR percentage from country class. after that 
	 * it pushes every priority into stack class and then stack class prints final report. 
	 *
	 * @param args
	 * @param Country i am creating country class array where all the data is store from csv file.
	 * @param length this call the loadfile function and get length of the record
	 * @param stack creating a class call stack and initialized with the length.
	 * @param Priority  this class is broken into different object(poor,fair,good,vgood,excellent) and all of them are based on CFR for each country.
	 */

	public static void main(String[] args) {
		Country[] country = new Country[1000];
		int length = loadfile(country);
		Stack stack = new Stack(length);
		Priority poor = new Priority(length);
		Priority fair = new Priority(length);
		Priority good = new Priority(length);
		Priority vgood = new Priority(length);
		Priority excellent = new Priority(length);

		for(int i=0;i<length;i++)
		{
			stack.push(country[i]);
		}
		System.out.printf("\nPrinting original list from CSV file\n");
		stack.printStack();
		while(!stack.isEmpty())
		{
			stack.pop();
		}
		for(int i=0;i<length;i++)
		{
			if(country[i].getCFR()<1.0)
			{
				excellent.insert(country[i]);
			}
			else if(country[i].getCFR()>=1.0 && country[i].getCFR()<2.0)
			{
				vgood.insert(country[i]);
			}
			else if(country[i].getCFR()>=2.0 && country[i].getCFR()<5.0)
			{
				good.insert(country[i]);
			}
			else if(country[i].getCFR()>=5.0 && country[i].getCFR()<10.0)
			{
				fair.insert(country[i]);
			}
			else if(country[i].getCFR()>=10.0)
			{
				poor.insert(country[i]);
			}
		}
		
		System.out.println("\nPoor Priority Queue Contents:");
		poor.print();
		System.out.println("\nFair Priority Queue Contents:");
		fair.print();
		System.out.println("\nGood Priority Queue Contents:");
		good.print();
		System.out.println("\nVgood Priority Queue Contents:");
		vgood.print();
		System.out.println("\nExcellent Priority Queue Contents:");
		excellent.print();
		
		while(!excellent.isEmpty())
		{
			stack.push(excellent.remove());
		}
		while(!vgood.isEmpty())
		{
			stack.push(vgood.remove());
		}
		while(!good.isEmpty())
		{
			stack.push(good.remove());
		}
		while(!fair.isEmpty())
		{
			stack.push(fair.remove());
		}
		while(!poor.isEmpty())
		{
			stack.push(poor.remove());
		}
		System.out.println("\nPrinting stack after printing Priority push in stack");
		stack.printStack();
}
}

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
/**
 * In this project we are creating a hash table where country information is inserted based upon their country name and capitol. The Hash
 * table that we are creating is also separate chaining so it can handle the collision.<p> Also we are using the double ended singly link 
 * list. 
 * @author Saral Desai
 * @version 12/2/2020
 */

public class Project5 {
	/**
	 * The loadfile function ask user the file they want to scan into Hash table class.
	 * <p>I am only transferring country name, capitol and CFR for that country all the other data is not getting transfered to hash table.
	 * 
	 * @param UserFileName ask user to input the file name.
	 * @param line It reads each line from CSV.
	 * @param lines it count the records in the file.
	 * @param list It breaks each line into separate array for country class.
	 * @param HS is a function call so i can transfer data into Hash table.
	 * @return lines this returns a number which tell how much data is in CSV file.
	 * @exception if user puts wrong file name it put throw exception or if the file is empty.
	 */
	public static int loadfile(HashTable HS)
	{
		String UserFileName;
		String line;
		int lines = 0;
		Scanner Input = new Scanner(System.in);
		System.out.println("Hash Tables:");
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
				HS.insert(list[0], list[1], Double.parseDouble(list[5])/Double.parseDouble(list[4]));
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
	 * In this class I create Hash table class. The main class only call all the different method into it. 
	 * <p>From main class I delete country, find and insert them by calling their methods which are in Hash table class. 
	 * From main class i am calling print, delete and find method to show how everything is different after deleting once insert is done. 
	 * @param args
	 * @param lines it tells me how many records are there. 
	 * @param temp is to find if that the country exit in the Hash table or not. If country exit it gives that country information and it CFR.
	 * @param temp1 is to find if that the country exit in the Hash table or not. If country exit it gives that country information and it CFR.
	 * @param temp2 is to find if that the country exit in the Hash table or not. If country exit it gives that country information and it CFR.
	 */
	public static void main(String[] args) {
		HashTable HT = new HashTable();
		int lines = loadfile(HT);
		
		System.out.println();
		System.out.println("There were "+lines+" records read into the hash table");
		System.out.println();
		System.out.println("Hash Table content: ");
		HT.display();
		System.out.println();
		
		HT.delete("Australia","Canberra");
		HT.delete("Tunisia", "Tunis");
		HT.delete("Norway", "Oslo");
		System.out.println();
		
		double temp = HT.find("Sri Lanka", "Colombo");
		if(temp==-1)
		{
			System.out.println("Sri Lanka is not found");
		}
		else
		{
			System.out.printf("Sri Lanka is found with CFR %.6f\n", temp);
		}
		
		double temp1=HT.find("Cyprus","Nicosia");
		if(temp1==-1)
		{
			System.out.println("Cyprus Lanka is not found");
		}
		else
		{
			System.out.printf("Cyprus is found with CFR %.6f\n", temp1);
		}
		
		double temp2= HT.find("Tunisia", "Tunis");
		if(temp2==-1)
		{
			System.out.println("Tunisia is not found");
		}
		else
		{
			System.out.printf("Tunisia is found with CFR %.6f", temp2);
		}
		
		System.out.println();
		HT.delete("Malawi", "Lilongwe");
		HT.delete("Germany", "Berlin");
		HT.delete("Ireland", "Dublin");
		HT.delete("Yemen", "Sanaa");
		HT.delete("India", "New Delhi");
		System.out.println();
		
		System.out.println("Hash Table content: ");
		HT.display();
		System.out.println();
		
		HT.printFreeAndCollisions();
	}
}

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Project1 
{
	/********************************************************************************************
	 * COP 3530: Project 1- Array Searches and Short.											*
	 * 																							*
	 * In this program user upload the csv file which has information which can be shorted 		*
	 * different ways. The File get read from SRC folder in eclipse,							*
	 * 																							*
	 *  I have two different input for user First for user file name to uploading file          *
	 *  and second for selecting from different menu option.								    *
	 *																							*  
	 *  The load file class is where user input the file name that want to upload file into a   *
	 *  code  to get shorted by different option. In this class file get loaded into array for  *
	 *  different shorting method. After the file gets loaded into array it returns an integer 	*
	 *  which counts number of lines of data it has. Also the data get transfer into country    *
	 *  class. If the return is -1 then user has putted the wrong file name.					*
	 *  																						*		
	 *  Student: Saral Desai.																	*
	 *  																						*
	 *  9/10/2020																				*
	 *	  																						*
	 *******************************************************************************************/
	public static int loadfile(Country[] country)
	{
		String UserFileName;
		String line;
		int lines = 0;
		@SuppressWarnings("resource")
		Scanner Input = new Scanner(System.in);
		System.out.println("Array Searches and Sorts");
		System.out.print("Enter your filename:");	
		
		UserFileName=Input.nextLine();
		UserFileName = "src/" + UserFileName;
		try
		{
			BufferedReader br = new BufferedReader(new FileReader(UserFileName));
			br.readLine(); //this reads the first line from the file and removes the information line eg(Country, capital etc);
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
		return lines;
		
	}
/*************************************************************************************************
 * In main class different methods are called. This class also display menu for user to select   *
 * different shorting method. This class also initial the country class array where the data is  *
 * stored form load file. In main class there is a do while loop for user keep shorting data in  *
 * different way. In here there are two scanner option one is to select option from a menu and   *
 * another to input country name for searching method.                                           * 
 * 																								 *
 * In this class there is a try and catch block so if the user put anything other that integer   *
 * the program wont crash and user has an option to put right input afterwards.                  *
 ************************************************************************************************/
	public static void main(String[] args) 
	{
		Country [] country = new Country[1000];
		int length = loadfile(country);
		int UserInput=0;
		int loadperviceinput=0;
		String countryname;
		Scanner Input2 = new Scanner(System.in);
		Scanner Input3 = new Scanner(System.in);
		
		if(length==-1)
		{
			System.out.println("Can not find the file");
		}
		else 
		{
		System.out.println("\nThere were "+length+" records read\n");
		do
		{
			System.out.println("1. Print acountries report");
			System.out.println("2. Sort by Name");
			System.out.println("3. Sort by COVID-19 CFR");
			System.out.println("4. Sort by GDP per capita");
			System.out.println("5. Find and print a given country");
			System.out.println("6. Quit");
			System.out.printf("Enter your choice: ");
			
			try 
			{
				UserInput=Input2.nextInt();
				
			}
			catch(InputMismatchException e)
			{
				Input2.next();
			}
			switch(UserInput)
			{
			case 1:
				printreport(country,length);
				break;
			case 2:
				loadperviceinput=2;
				Short_by_name_Insertion_sort(country,length);
				System.out.println("\nCountries sorted by Name.\n");
				break;
			case 3:
				loadperviceinput=3;
				CFR_report_selection_sort_ascendingly_order(country,length);
				System.out.println("\nCountries sorted by COVID-19 CFR.\n");
				break;
			case 4:
				loadperviceinput=4;
				GDP_per_capita_bubble_shorting_descendingly_order(country,length);
				System.out.println("\nCountries sorted by GDP per capita.\n");
				break;
			case 5:
				System.out.printf("Entery country name: ");
				countryname=Input3.nextLine().trim();
				if(loadperviceinput==2)
				{
					System.out.println("Bianary search\n");
					bianary_search(country,length,countryname);
				}
				else 
				{
					System.out.println("sequential_search\n");
					sequential_search(country,length,countryname);
				}
				break;
			case 6: 
				System.out.println("\nHave a great day.");
				break;
				
			default:
				System.out.println("\nInvalid choice enter 1-6: ");
				break;
			}
		}
		while(UserInput!=6);
		Input2.close();
		Input3.close();
		}
}
/********************************************************************************
 * In this class the csv file data gets printed as it is without making changes *
 * before making any kind of shorting is apply. 								*
 ********************************************************************************/
public static void	printreport(Country[] country, int length)
{
	System.out.printf("\n%-35s %20s %20s %20s %20s %20s","Country_name","Country_capital","Population","GDP","COVID19_Cases","COVID19_Deaths");
	System.out.println("_______________________________________________________________________________________________________________________________________________");
	for(int i=0;i<length;i++) 
	{
		System.out.print(country[i].toString());
	}
	System.out.println();
}
/*******************************************************************************
 * In this class the csv file data gets shorted by name so it goes from A-Z in *
 * that order.																   *
 ******************************************************************************/
public static void Short_by_name_Insertion_sort(Country[] country,int length)
{
	int in,out;
	for(out=0;out<length;out++)
	{	
		for(in=0;in<(length-1);in++)
		{
			if(country[in].compare(country[in+1])>0)
			{
				Country temp = country[in+1];
				country[in+1]=country[in];
				country[in]=temp;
			}
		}
	}
}
/*********************************************************************************
 * In this class the data gets shorted into case fatality rate(CFR) which is get * 
 * from file. It goes from smallest number to biggest number. Also all the get   *
 * function are called from Country class for shorting.    	 				   	 *
 * 																				 *
 * All the information after shorted is store in print for user to see it wont   *
 * just print right after shorting.                                              *
 ********************************************************************************/
public static void CFR_report_selection_sort_ascendingly_order(Country[] country,int length)
{
	int out,in;
	for(out=0;out<length-1;out++)
	{
		int lowest=out;
		for(in=out+1;in<length;in++)
		{
			if(country[in].getCFR()<country[lowest].getCFR())
			{
				lowest=in;
			}
		}

		if(lowest!=out) 
		{
			Country temp = country[lowest];
			country[lowest]=country[out];
			country[out]=temp;
		}
		
	}
}
/*********************************************************************************
 * In this class the data gets shorted by GDP per capita but the order it goes 	 *
 * is from largest to smallest of the country. Also function the get function    *
 * are called from the country class.											 *
 ********************************************************************************/
public static void GDP_per_capita_bubble_shorting_descendingly_order(Country[] country,int length)
{
	int in,out;
	for(in=0;in<length;in++)
	{
		for(out=1;out<length;out++)
		{
			if(country[out-1].getGDPTOTAL()<country[out].getGDPTOTAL())
			{
				Country temp = country[out-1];
				country[out-1]=country[out];
				country[out]=temp;
			}
		}
	}
}
/**********************************************************************************
 * In this class user can ask for particular country information. This is a       *
 * bianary search method . This method get called if user previously selected     *
 * shorted by name method.														  *
 *********************************************************************************/
public static void bianary_search(Country[] country, int length,String name)
{
	int low=0;
	int upper=length-1;
	int mid;
	
	while(low<=upper)
	{
		mid=(low+upper)/2;
		if(country[mid].getCountryname().compareTo(name)==0)
		{
			System.out.printf("%-20s %s\n","Name:",country[mid].getCountryname());
			System.out.printf("%-20s %s\n","Capital:",country[mid].getCapitol());
			System.out.printf("%-20s %s\n","Population:",country[mid].getPopulation());
			System.out.printf("%-20s %s\n","GDP:",country[mid].getGDP());
			System.out.printf("%-20s %s\n","COVID-19 Cases:",country[mid].getCases());
			System.out.printf("%-20s %s\n","COVID-19 Death:",country[mid].getDeath());
			System.out.println();
			break;
		}
		else if(country[mid].getCountryname().compareTo(name)>0)
		{
			upper = mid-1;
		}
		else
		{
			low=mid+1;
		}
		if(upper<low)
		{
			System.out.println("\nError: country "+ name +" not found\n");
		}
	}
}
/**************************************************************************
 * In this class user can ask for particular country information by their *
 * name. For this search method to get called user doesn't have to use any*
 * shorted method to search for any country.                              * 
 *************************************************************************/
public static void sequential_search(Country[]country,int length,String name)
{
	int i=0;
	while(i<length)
	{
		if(country[i].getCountryname().compareTo(name)==0) 
		{
		
			System.out.printf("%-20s %s\n","Name:",country[i].getCountryname());
			System.out.printf("%-20s %s\n","Capital:",country[i].getCapitol());
			System.out.printf("%-20s %s\n","Population:",country[i].getPopulation());
			System.out.printf("%-20s %s\n","GDP:",country[i].getGDP());
			System.out.printf("%-20s %s\n","COVID-19 Cases:",country[i].getCases());
			System.out.printf("%-20s %s\n","COVID-19 Death:",country[i].getDeath());
			System.out.println();
			break;
		}
			i++;
	}
	if(i==length)
	{
		System.out.println("\nError: country "+ name +" not found\n");
	}
}
}

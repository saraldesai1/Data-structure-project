import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Project1 
{
	
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

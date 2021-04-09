import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Project4 {
	/**
	 * The loadfile function ask user the file they want to scan into binary search tree class.
	 * <p>I am only transferring country name and CFR for that country all the other data is not getting transfered to binary search tree.
	 * 
	 *  @param UserFileName ask user to input the file name.
	 *  @param line It reads each line from CSV.
	 *  @param lines it count the records in the file.
	 *  @param list It breaks each line into separate array for country class.
	 *  @param bst is a function call so i can transfer data into Binary search tree.
	 *  @return lines this returns a number which tell how much data is in CSV file.
	 *  @exception if user puts wrong file name it put throw exception or if the file is empty.
	 */
	public static int loadfile(BinarySearchTree bst)
	{
		String UserFileName;
		String line;
		int lines = 0;
		Scanner Input = new Scanner(System.in);
		System.out.println("Binary Search Trees");
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
				bst.insert(list[0],(Double.parseDouble(list[5])/Double.parseDouble(list[4])));
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
	 * From the main method I am creating BinarySearchTree class so I can create an tree based on their name. The only thing main class is doing is call 
	 * BinarySearchTree class for its different method call.<p> In main class there are three different print out for a tree. First is inorder where left node print 
	 * first than right node and after that root. Second is preorder where root print first and left node than right node. Third is postorder where left node print than 
	 * right node and root. After that only top and bottom 20 country are print based on their CFR.<p> There are two different function call where first is delete 
	 * it shows how after deleting the nodes get arranged again. The other is find method it show how long it takes to find one county from the tree. It show how many 
	 * times tree need to be traveled to find that country.
	 * @param args
	 * @param lines it tells me how many records are there. 
	 * @param temp is to find if that the country exit in the tree or not. If country exit it gives that country information and it CFR.
	 * @param temp1 is to find if that the country exit in the tree or not. If country exit it gives that country information and it CFR.
	 * @param temp2 is to find if that the country exit in the tree or not. If country exit it gives that country information and it CFR.
	 * @param temp3 is to find if that the country exit in the tree or not. If country exit it gives that country information and it CFR.
	 */

	public static void main(String[] args) {
		BinarySearchTree binary = new BinarySearchTree();
		int lines = loadfile(binary);
		
		System.out.println("\nInorder Traversal:");
		binary.printInorder();
		
		System.out.println();
		binary.delete("Greece");
		binary.delete("Mongolia");
		binary.delete("Norway");
		
		System.out.println();
		System.out.println("\nPreorderTraversal:");
		binary.printPreorder();
		System.out.println();
		
		System.out.println();
		double temp=binary.find("Mongolia");
		if(temp==-1)
		{
			System.out.println("Mongolia is not found\n");
		}
		else
		{
			System.out.printf("Mongolia is found with CFR %.6\n\nf", temp);
		}
		
		double temp1=binary.find("Cyprus");
		if(temp1==-1)
		{
			System.out.println("Cyprus is not found\n");
		}
		else 
		{
			System.out.printf("Cyprus is found with CFR %.6f\n\n",temp1);
		}
		
		double temp2=binary.find("United States");
		if(temp2==-1)
		{
			System.out.println("United States is not found\n");
		}
		else 
		{
			System.out.printf("United States is found with CFR %.6f\n\n",temp2);
		}
		
		double temp3=binary.find("Norway");
		if(temp3==-1)
		{
			System.out.println("Norway is not found\n");
		}
		else 
		{
			System.out.printf("Norway is found with CFR %.6f\n\n",temp3);
		}
		System.out.println();
		
		System.out.println();
		binary.delete("Qatar");
		binary.delete("Somalia");
		binary.delete("Canada");
		binary.delete("Yemen");
		binary.delete("New Zealand");
		System.out.println();
		
		System.out.println();
		System.out.println("Postorder Traversal:");
		binary.printPostorder();
		System.out.println();
		
		System.out.println("\nBottom twenty countries regarding CFR");
		binary.printBottomTwenty();
		System.out.println("\nTop twenty countries regarding CFR");
		binary.printTopTwenty();
	}

}

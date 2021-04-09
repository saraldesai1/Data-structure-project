
public class HashTable {
	
	private class Node 
	{
		String name;
		String capitol;
		double CFR;
		Node nextNode;
		/**
		 * In this constructor all the public variable gets initialized
		 * @param name this is a name of a country
		 * @param capitol this has each country capitol.
		 * @param CFR this is country CFR
		 */
		public Node(String name, String capitol, double CFR) 
		{
		this.name = name;
		this.capitol = capitol;
		this.CFR = CFR;
		}
		/**
		 * This is just print class that is formated to print.
		 */
		public void printNode() {
		System.out.printf("%-40s %-20s %-30.6f\n", name, capitol, CFR);
		}
	}
	private class shortList
	{
		private Node first;
		private Node last;
		public int freec;
		/**
		 * This is a class which helps all heaptable function to do all the call for nodes. In this class i create double ended 
		 * singly link list by creating first and last nodes.
		 * @param first is the first place for the node
		 * @param last is the last place for the node
		 * @param freec this is so i can calculate how many times the items are inserted country into different key to calculate collision.
		 */
		public shortList()
		{
			first=null;
			last=null;
			freec=0;
		}
		/**
		 * This is a class where all the country are inserted on nodes. So if the first or last node is empty it fill first and last Node.
		 * After that if the link is filled it goes and insert everything to the last nodes. 
		 * @param current is the current country information that I need to insert in the nodes.
		 */
		public void inserts(Node current)
		{
			
			if(first==null)
			{
				first=current;
				freec++;
			}
			if(last==null)
			{
				last=current;
			}
			else 
			{
				last.nextNode=current;
				last=current;
				freec++;
			}
		}
		/**
		 * This is where i find the country information that i am looking for from the hastable.
		 * @param country this is a country name.
		 * @return  CFR this returns CFR for found country or -1 if it is not found.
		 */
		public double find(String country)
		{
			Node current = first;
			if(current.name.compareTo(country)==0)
			{
				return current.CFR;
			}
			while(current != null && current.name.compareTo(country)!=0)
			{
				if(current.name.compareTo(country)==0)
				{
					return current.CFR;
				}
				current=current.nextNode;
			}
			return -1;
		}
		/**
		 * This is where I delete country from the node and table but leave all the other country if they are available.
		 * @param country this is a country name.
		 */
		public void delete(String country)
		{
			Node current = first;
			last=null;
			while(current.name.compareTo(country)!=0 && current!=null)
			{
				
				last=current;
				current=current.nextNode;
				if(current==null)
				{
					System.out.println(country+" not in the hash table");
					return;
				}
			}
			if(last==null)
			{
				first=first.nextNode;
				System.out.println(country+" has been deleted from has table");
				freec--;
			}
			else
			{
				last.nextNode=current.nextNode;
				System.out.println(country+" has been deleted from has table");
				freec--;
			}
		}
		/**
		 * This function display all the country from the Nodes in their key.
		 */
		public void display()
		{
			Node current = first;
			if(current==null)
			{
				System.out.println("	Empty");
			}
			while(current!=null)
			{
				System.out.print("	");
				current.printNode();
				current=current.nextNode;
			}
		}
	}
	private shortList[] arr;
	/**
	 * This is a Hash table constructor.
	 * <p> In this function i fill up all arr before doing anything.
	 * @param arr is they array for shortlist class call.
	 */
	public HashTable()
	{
		arr = new shortList[307];
		for(int i=0;i<307;i++)
		{
			arr[i] = new shortList();
		}
	}
	/**
	 * I insert country based upon their key. Key is get calculate by converting country name and capitol.
	 * <p> the function tochararray() puts the whole thing into their char array and then it converts each character to integer.
	 * <p> After the key is being calculate the arr function is called to insert the country information into their 
	 * proper position in that 
	 * key.
	 * @param country this is a country name 
	 * @param capitol this is a country capitol
	 * @param CFR this is a country CFR
	 * @param hash this is to store total number after converting country string to integer.
	 * @param hash1 this is to store total number after converting country capitol string to integer.
	 * @param a this is where to store all the country string after putting them into array
	 * @param b this is where to store all the country capitol string after putting them into array
	 * @param temp this add hash and hash1 together
	 * @param key this find the key where i need to insert the country that is currently being calculated by their name and capitol.
	 */
	public void insert(String country, String capitol, double CFR) 
	{
		 int hash = 0;
		 int hash1=0;
		 char[] a = country.toCharArray();
		 char[] b = capitol.toCharArray();
		 Node current = new Node(country,capitol,CFR);
		 
	        for (int i=0;i<a.length;i++) 
	        {
	            hash += a[i];
	        }
	        for(int j=0;j<b.length;j++)
	        {
	        	hash1 += b[j];
	        }
	        int temp = hash+hash1;
	        int key =temp % 307;
	        arr[key].inserts(current);
	}
	/**
	 * I find country based upon their key. Key is get calculate by converting country name and capitol.
	 * <p> the function tochararray() puts the whole thing into their char array and then it converts each character to integer.
	 * <p> After the key is being calculate the arr function is called to call find method from shortlist class. I pass country 
	 * name to it so i can compare the country i need to find in that key if it is more than one.
	 * @param country this is a country name 
	 * @param capitol this is a country capitol
	 * @param hash this is to store total number after converting country string to integer.
	 * @param hash1 this is to store total number after converting country capitol string to integer.
	 * @param a this is where to store all the country string after putting them into array
	 * @param b this is where to store all the country capitol string after putting them into array
	 * @param temp this add hash and hash1 together
	 * @param key this find the key where i need to insert the country that is currently being calculated by their name and capitol.
	 * @return  CFR this returns CFR for found country or -1 if it is not found.
	 */
	public double find(String country, String capitol)
	{
		int hash = 0;
		 int hash1=0;
		 char[] a = country.toCharArray();
		 char[] b = capitol.toCharArray();
		 
	        for (int i=0;i<a.length;i++) 
	        {
	            hash += a[i];
	        }
	        for(int j=0;j<b.length;j++)
	        {
	        	hash1 += b[j];
	        }
	        int temp = hash+hash1;
	        int key =temp % 307;
	        double cfrforcounty=0;
	        if(arr[key].first==null)
	        {
	        	return -1;
	        }
	        else 
	        {
	        	cfrforcounty=arr[key].find(country);
	        }
	        if(cfrforcounty==-1)
	        {
	        	return -1;
	        }
	        else
	        	return cfrforcounty;
	        
	}
	/**
	 * I delete country based upon their key. Key is get calculate by converting country name and capitol.
	 * <p> the function tochararray() puts the whole thing into their char array and then it converts each character to integer.
	 * <p> After the key is being calculate the arr function is called to call delete method from shortlist class.
	 * @param country this is a country name 
	 * @param capitol this is a country capitol
	 * @param hash this is to store total number after converting country string to integer.
	 * @param hash1 this is to store total number after converting country capitol string to integer.
	 * @param a this is where to store all the country string after putting them into array
	 * @param b this is where to store all the country capitol string after putting them into array
	 * @param temp this add hash and hash1 together
	 * @param key this find the key where i need to insert the country that is currently being calculated by their name and capitol.
	 */
	public void delete(String country, String capitol)
	{
		 int hash = 0;
		 int hash1=0;
		 char[] a = country.toCharArray();
		 char[] b = capitol.toCharArray();
		 
	        for(int i=0;i<a.length;i++) 
	        {
	            hash += a[i];
	        }
	        for(int j=0;j<b.length;j++)
	        {
	        	hash1 += b[j];
	        }
	        int temp = hash+hash1;
	        int key =temp % 307;
	        if(arr[key].first==null)
	        {
	        	System.out.println("Not found in hash");
	        }
	        else 
	        {
	        	arr[key].delete(country);
	        }
	}
	/**
	 * This is where i call print method to print all keys and with information in it. This shows which key has country and 
	 * which doesnt. It also shows how many countries are in that given key.
	 */
	public void display()
	{
		for(int i=0;i<arr.length;i++)
		{
			System.out.printf("%-3d.",i);
			arr[i].display();
		}
	}
	/**
	 * This is where i calulate how many times do i have collision in the key.
	 * @param free this tells how many keys are empty. 
	 * @param coll this tells how many keys has collision.
	 * @param noncoll this tells how many keys had only one country. 
	 */
	public void printFreeAndCollisions()
	{
		int free=0,coll=0, noncoll=0;
		for(int j=0;j<arr.length;j++)
		{
			if(arr[j].freec>1)
			{
				coll++;
			}
			if(arr[j].freec==0)
			{
				free++;
			}
			if(arr[j].freec==1)
			{
				noncoll++;
			}
		}
		System.out.println("Hash table has "+ free +" empty space and "+ coll+" collisons and "+noncoll+" non colliosn");
	}
}


public class BinarySearchTree 
{
	/**
	 * This node class help tree class to go left or right from the root.
	 *
	 */
	private class Node 
	{
		String names;
		double CFR;
		Node leftChild;
		Node rightChild;
		/**
		 * In this constructor all the public variable gets initialized
		 * @param name this is a name of a country
		 * @param CFR this is country CFR
		 */
		
		public Node(String name, double CFR) 
		{
		this.names = name;
		this.CFR = CFR;
		}
		public void printNode()
		{
		System.out.printf("%-40s%,-30.6f\n", names, CFR);
		}
	}
	private Node root;
	private Node[] array;
	private int items;
	/**
	 * In this constructor all the public variable gets initialized.
	 * @param root is to create tree.
	 * @param array is to hold countries based on their CFR.
	 * @param items is to help array to remember how many data it has. 
	 */
	public BinarySearchTree()
	{
		root =null;
		array = new Node[20];
		items=0;
	}
	/**
	 * This function insert country by comparing to each other so it new country is smaller that the current country it goes left side and if it bigger than 
	 * on right side. So in the end we get a tree where all the small data is on the left side and large data right side.
	 * @param name is country name 
	 * @param CFR this is country death ratio.
	 */
	public void insert(String name, double CFR)
	{
		Node newnode = new Node(name,CFR);
		if(root==null)
		{
			root = newnode;
		}
		else 
		{
			Node current = root;
			Node parent;
			while(true)
			{
				parent = current;
				if(newnode.names.compareTo(parent.names)<0)
				{
					current=current.leftChild;
					if(current==null)
					{
						parent.leftChild=newnode;
						return;
					}
				}
				else
				{
					current=current.rightChild;
					if(current==null)
					{
						parent.rightChild = newnode;
						return;
					}
				}
			}
		}
	}
	/**
	 * This function find the country the user wants and tells how many nodes it visited to find that.
	 * @param name this is a country name.
	 * @return CFR this returns CFR for found country or -1 if it is not found.
	 */
	public double find(String name)
	{
		int i=0;
		Node current = root;
		while(name.compareTo(current.names)!=0)
		{
			if(name.compareTo(current.names)<0)
			{
				current=current.leftChild;
				i++;
			}
			else
			{
				current=current.rightChild;
				i++;
			}
			if(current==null)			
			{
				System.out.println(i + " nodes visited");
				return -1;
			}
		}
		System.out.println(i + " nodes visited");
		return current.CFR;
	}
	/**
	 * In this I delete the county that user wants and replace that successor node if the node has left and right nodes.<P> I have created an helper method for 
	 * finding node. 
	 * @param name this is country name.
	 * @param check this tells me if the node is on left side or right side.
	 */
	public void delete(String name)
	{
		Node current = root;
		Node parent = root;
		boolean check=true;
		while(name.compareTo(current.names)!=0)
		{
			parent=current;
			if(name.compareTo(current.names)<0)
			{
				check=true;
				current=current.leftChild;
			}
			else
			{
				check=false;
				current = current.rightChild;
			}
			if(current==null)
			{
				System.out.print("Country not found.\n");
				return;
			}
		}
		if(current.leftChild==null && current.rightChild==null)
			{
				 if(current==root)
					 root=null;
				 else if(check)
				 {
					parent.leftChild=null;
				 }
				else 
				{
					parent.rightChild=null;
				}
			}
			else if(current.rightChild==null)
			{
				if(current==root)
					root = current.leftChild;
				else if(check)
				{
					parent.leftChild=current.leftChild;
				}
				else
				{
					parent.rightChild=current.leftChild;
				}
			}
			else if(current.leftChild==null)
			{
				if(current==root)
						root = current.rightChild;
				else if (check)
					parent.leftChild=current.rightChild;
				else 
					parent.rightChild=current.rightChild;
			}
			else 
			{
				Node successor = getSuccessor(current);
				if(current==root)
				{
					root=successor;
				}
				else if(check)
				{
					parent.leftChild=successor;
				}
				else
				{
					parent.rightChild=successor;
				}
				successor.leftChild=current.leftChild;
			}
			 System.out.print(name+ " has been deleted from tree\n");
		}
	/**
	 * This helps delete method to find the successor for deleted node.
	 * @param del is the nodes is getting deleted 
	 * @return successor this is to replace the deleted node 
	 */
	private Node getSuccessor(Node del)
	{
		Node successorParent=del;
		Node successor=del;
		Node current = del.rightChild;
		while(current != null)
		{
			successorParent = successor;
			successor=current;
			current = current.leftChild;
		}
		if(successor != del.rightChild)
		{
			successorParent.leftChild = successor.rightChild;
			successor.rightChild = del.rightChild;
		}
		return successor;
	}
	/**
	 * this print entire tree inorder. This class calls helper method to inorder to print entire tree.
	 */
	public void printInorder()
	{
		System.out.println("Name                                    CFR");
		System.out.print("________________________________________________\n");
		inorder(root);
	}
	/**
	 * helps the printInorder method to print.
	 * @param local is a root transfer from printinorder method.
	 */
	private void inorder(Node local)
	{
		if(local != null)
		{
			inorder(local.leftChild);
			local.printNode();
			inorder(local.rightChild);
		}
	}
	/**
	 * this print entire tree Preorder. This class calls helper method to pre_order to print entire tree.
	 */
	public void printPreorder()
	{
		System.out.println("Name                                    CFR");
		System.out.print("________________________________________________\n");
		pre_order(root);
	}
	/**
	 * helps the printPreorder method to print.
	 * @param local is a root transfer from printPreorder method.
	 */
	private void pre_order(Node local)
	{
		if(local != null)
		{
			local.printNode();
			pre_order(local.leftChild);
			pre_order(local.rightChild);
		}
	}
	/**
	 * this print entire tree Postorder. This class calls helper method to post_order to print entire tree.
	 */
	public void printPostorder()
	{
		System.out.println("Name                                    CFR");
		System.out.print("________________________________________________\n");
		post_order(root);
	}
	/**
	 * helps the printPostorder method to print.
	 * @param local is a root transfer from printPostorder method.
	 */
	private void post_order(Node local)
	{
		if(local!=null)
		{
			post_order(local.leftChild);
			post_order(local.rightChild);
			local.printNode();
		}
	}
	/**
	 * This method has two helper method and first helper method loads array with the country and traverse the tree. Second short the array.
	 * <p>Also this method prints out the 20 from the array. This method show which country has the lowest CFR.
	 */
	public void printBottomTwenty()
	{
		
		bottomtwenty(root);
		System.out.println("Name                                    CFR");
		System.out.print("________________________________________________\n");
		for(int j=0;j<20;j++)
		{
			array[j].printNode();
		}
	}
	/**
	 * This method load the array the traverse the tree. Also this is an helper method for printBottomTwenty.
	 * @param local is a local node that is at that given time.
	 */
	private void bottomtwenty(Node local)
	{
		if(items<20)
		{
			array[items++]=local;
			accendind(array);
			
		}
		else
		{
			if(local.CFR<array[19].CFR)
			{
				array[19]=local;
				accendind(array);
			}
		}
		
		if(local.leftChild!=null)
		{
			bottomtwenty(local.leftChild);
		}
		if(local.rightChild!=null)
		{
			bottomtwenty(local.rightChild);
		}
	}
	/**
	 * This shorts the array from smallest to highest based on CFR. This is a helper method for bottomtwenty.
	 * @param array this hold all country information.
	 */
	private void accendind(Node[] array) 
	{
		int in,out;
		for(in=0;in<items;in++)
		{
			for(out=1;out<items;out++)
			{
				if(array[out-1].CFR>array[out].CFR)
				{
					Node temp = array[out-1];
					array[out-1]=array[out];
					array[out]=temp;
				}
			}
		}
	}
	/**
	 * This method has two helper method and first helper method loads array with the country and traverse the tree. Second short the array.
	 * <p>Also this method prints out the 20 from the array. This method show which country has the highest CFR.
	 */
	public void printTopTwenty()
	{
		toptwenty(root);
		System.out.println("Name                                    CFR");
		System.out.print("________________________________________________\n");
		for(int j=0;j<20;j++)
		{
			array[j].printNode();
		}
	}
	/**
	 * This method load the array the traverse the tree. Also this is an helper method for printTopTwenty.
	 * @param local is a local node that is at that given time.
	 */
	private void toptwenty(Node local)
	{
		if(items<20)
		{
			array[items++]=local;
			decscending(array);
		}
		else
		{
			if(local.CFR>array[19].CFR)
			{
				array[19]=local;
				decscending(array);
			}
		}
		if(local.leftChild!=null)
		{
			toptwenty(local.leftChild);
		}
		if(local.rightChild!=null)
		{
			toptwenty(local.rightChild);
		}
	}
	/**
	 * This shorts the array from highest to smallest based on CFR. This is a helper method for toptwenty.
	 * @param array this hold all country information.
	 */
	private void decscending(Node[] array)
	{
		int in,out;
		for(in=0;in<items;in++)
		{
			for(out=1;out<items;out++)
			{
				if(array[out-1].CFR<array[out].CFR)
				{
					Node temp = array[out-1];
					array[out-1]=array[out];
					array[out]=temp;
				}
			}
		}
	}
}

package kruskals_algorithm;

public class main_of_kruskal {

	public static void main(String[] args) {
		int M = 99; // I used bigM in order to define edges that are not in graph
		int [][] distance = //my distance matrix
		{
				{M,	3,	5,	4,	M,	8},
				{M,	M,	6,	5,	3,	4},
				{M,	M,	M,	5,	M,	6},
				{M,	M,	M,	M,	3,	3},
				{M,	M,	M,	M,	M,	7},
				{M,	M,	M,	M,	M,	M},
				
		} ;
		
		int [][] distancedeneme = //another matrix that can be used
			{
					{M,	35,	40,	M,	M},
					{M,	M,	25,	10,	M},
					{M,	M,	M,	20,	15},
					{M,	M,	M,	M,	30},
					{M,	M,	M,	M,	M},
					
			} ;
		
		int [][] distanceCopy=distance; // I have used a copy of my distance matrix to not lose data
		int noden = distance.length; //number of nodes in matrix
		int edgen=0;
		for(int p = 0;p<noden;p++) {for(int t=0;t<noden;t++) {if(distance[p][t] != M) {edgen=edgen+1;}}} // number of edges
		int smallest=M;
		
		int[][] edgeOrder=new int[0][3];
		int[][] forestadd=new int[edgeOrder.length+1][3];
		forestadd=new int[edgeOrder.length+1][3];
		int sour=0;
		int dest=0;
		
	for(int p=0; p<edgen;p++) // ordering the edges from shortest to longest	
	{
		for(int i = 0; i<noden;i++)
		{
			for(int j = 0; j<noden; j++)// finding the smallest one that is nor ordered
			{
				if (distanceCopy[i][j]<=smallest)
				{
					smallest=distanceCopy[i][j];
					sour=i;
					dest=j;
				}
			}
		}
		
		forestadd=new int[edgeOrder.length+1][3];
		for(int h=0;h<edgeOrder.length;h++) {for(int k=0;k<3;k++) {forestadd[h][k]=edgeOrder[h][k];}}
		forestadd[edgeOrder.length][0]=sour;
		forestadd[edgeOrder.length][1]=dest;
		forestadd[edgeOrder.length][2]=smallest;
		edgeOrder=forestadd; //from 55 to 60 adding new element to the ordered matrix
		smallest=M;
		distanceCopy[sour][dest]=M; //smallest one has been added so it has to be subtracted from edges
	}
	
	int[][] forest=new int[0][3]; // our forest with every element's data includes source destination and distance information
	int allconnections=0; //summation of all nodes in connection matrix
	
	int[][] conMatrix=new int[noden][noden]; //keeps the information of nodes in forest that if a node has a connection with another one their matrix
											 //data gets the value of 1.
	while(allconnections<noden*(noden-1)) {	 //When we reach this number every node is connected to the other ones on graph
		
		for(int i=0;i<edgeOrder.length;i++) 
		{
			if(conMatrix[edgeOrder[i][0]][edgeOrder[i][1]] == 0) //for the edge that is smallest one in order, if its nodes are connected in before somehow
			{													 //this if function does not allow it to get in the forest in order to avoid cycles
				forestadd= new int[forest.length+1][3];
				for(int q=0;q<forest.length;q++) {for(int w=0;w<3;w++) {forestadd[q][w]=forest[q][w];}}
				forestadd[forest.length][0]=edgeOrder[i][0];
				forestadd[forest.length][1]=edgeOrder[i][1];
				forestadd[forest.length][2]=edgeOrder[i][2];
				forest=forestadd; //from 76 to 81 adds new element into the forest matrix
				conMatrix[edgeOrder[i][0]][edgeOrder[i][1]] = 1; // that connect the nodes that has not been connected and shortest with their edge in order
				
				
				for (int e=0; e<noden;e++) 		//this for functions control every pair of nodes and their connection.
				{								//if a node is connected with another node that has a connection with one of the other ones
					for(int r=0;r<noden;r++) 	//these for functions connect the first node to other one's connections.
					{
						if(conMatrix[e][r]==1 || conMatrix [r][e]==1)
						{
							for(int t=0;t<noden;t++) 
							{
								if(conMatrix[r][t]==1 || conMatrix[t][r]==1)
								{
									conMatrix[e][t]=1;
									conMatrix[t][e]=1;
								}
							}
						}
					}
				}

				
					for(int x=0;x<noden;x++) // a node cannot have a connection with itself in the forest
					{
						conMatrix[x][x]=0;
					}
			}
		}
		
		
		allconnections=0;
		for(int i=0;i<noden;i++) // calculates the connections of all nodes
		{
			for(int j=0;j<noden;j++) 
			{
				allconnections=allconnections+conMatrix[i][j];
			}
		}
		
		
	}
	
	System.out.println("Number of Edges: " + edgen);
	System.out.println("Number of Nodes: " + noden);
	System.out.println("Number of Edges Should be in MST: " + (noden-1));
	System.out.println();
	System.out.println("Edges in MST: ");

	for(int i=0;i<forest.length;i++) 
	{
		for(int j=0;j<3;j++)
		{
			if(j!=2) 
			{
				System.out.print(forest[i][j] + " - ");
			}
			else
			{
				System.out.print("Cost: " + forest[i][j]);
			}
		}

		System.out.println();
	}
}
}

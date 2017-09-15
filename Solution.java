import java.util.Scanner;
import java.lang.*;
public class Solution 
{ 
	static int b[][][] = new int[9][9][10];    
	public static void main(String[] args)     
	{
		Scanner cin = new Scanner(System.in);
		for(int i = 0; i<9; i++)
		{
			for(int j = 0; j<9; j++)
			{
				b[i][j][0] = cin.nextInt();
				for(int k = 1; k<10; k++)
				{
					b[i][j][k] = 2;
				}
			}
		}
		long start = System.currentTimeMillis();
		int a = solve(b);
		if(a!=0)
			System.out.print(b[i][j][0] + (j%3==2 && j!=8?" || ":" "));
		else
		{
			for(int i = 0; i<9; i++)
			{
				for(int j = 0; j<9; j++)
				{
					System.out.print(b[i][j][0] + " ");
				}
				if(i%3==2 && i!=8)     
					System.out.println();     
				System.out.println(i%3==2 && i!=8?"=======================":"");
			}
		}
		System.out.println((System.currentTimeMillis()-Start))+" Milliseconds");
		cin.close();
	}
	public static int solve(int b[][][])
	{
		boolean solved = false;
		while(!solved)
		{
			boolean impossible = false;
			solved = true;   
			boolean changed = false;
			for(int i = 0; i<9; i++)
			{
				for(int j = 0; j<9; j++)
				{
					if(b[i][j][0]==0)
					{
						int c = 0;
						int n = 0;
						for(int k = 1; k<10; k++)
						{
							boolean can = true;
							for(int l = 0; l<9; l++)
							{
								if(b[i][l][0]==k) 
									can = false;
								if(b[l][j][0]==k)
									can = false;
								if(b[i-i%3+l%3][j-j%3+l/3][0]==k)
									can = false;
							}
							if(can && b[i][j][k] != 0) 
							{
								b[i][j][k] = 1; 
								c++;   
								n = k; 
							}
							else  
							{
								b[i][j][k] = 0;   
							}
						}
						if(c==1)  
						{
							b[i][j][0] = n; 
							b[i][j][n] = 0;  
							changed = true;    
						}
						else if(c==0)         
						{          
							impossible = true; // if unsolved spot has 0 candidates 
							break;
						}
					}
					if(b[i][j][0]==0)
					{
						solved = false;
					}
				}
			}
			for(int k = 1; k<10; k++)  
			{
				for(int i = 0; i<9; i++)//row only can    
				{
					int r = 0;   
					int in = 0;    
					int rn = 0;    
					int srn = 0;  
					int trn = 0;   
					for(int j = 0; j<9; j++) 
					{
						if(b[i][j][k]==1)    
						{
							r++;//count times that k is a candidate in row j      
							trn = srn;   
							srn = rn;   
							rn = j;   
						}
						if(b[i][j][0]==k)   
							in++;  
					}
					if(in>1)    
					{
						impossible = true;//same number in row more than once 
						break;  
					}
					else if(in==1) // if cand already in row then tell row than none will be k 
					{              
						for(int j = 0; j<9; j++) 
							b[i][j][k]=0;    
					}
					else if(r==1)//if row only has 1 possible spot for cand it goes there  
					{     
						b[i][rn][0] = k; 
						for(int m = 1; m<10; m++)   
							b[i][rn][m] = 0;  
						changed = true;  
					}
					else if(r==2 && (srn-srn%3)==(rn-rn%3))//if in a row, k is a cand in 2 places and they're in the same box 
					{
						//tell the rest of the box that they wont have k  
						for(int m = 0; m<9; m++) 
						{
							if(b[i-i%3+m%3][rn-rn%3+m/3][k] != 0 && (rn-rn%3+m/3)!=srn && (rn-rn%3+m/3)!=rn) 
							{
								changed = true;  
								b[i-i%3+m%3][rn-rn%3+m/3][k] = 0;  
							}
						}
					}
					else if(r==3 && (srn-srn%3)==(rn-rn%3) && (rn-rn%3)==(trn-trn%3)) // same as previous but with 3 spots in same box   
					{
						for(int m = 0; m<9; m++)  
						{
							if(b[i-i%3+m%3][rn-rn%3+m/3][k] != 0 && (rn-rn%3+m/3)!=srn && (rn-rn%3+m/3)!=trn && (rn-rn%3+m/3)!=rn)  
							{
								changed = true;     
								b[i-i%3+m%3][rn-rn%3+m/3][k] = 0;    
							}
						}
					}//*/  
				}
				for(int i = 0; i<9; i++)//similar to previous but for columns 
				{
					int c = 0; 
					int in = 0;
					int tcn = 0;
					int scn = 0;
					int cn = 0;  
					for(int j = 0; j<9; j++)
					{
						if(b[j][i][k]==1)
						{
							c++;
							tcn = scn;  
							scn = cn;  
							cn = j; 
						}
						if(b[j][i][0]==k)
							in++;
					}
					if(in>1)  
					{
						impossible = true;
						break;  
					}
					else if(in==1)
					{
						for(int j = 0; j<9; j++)
							b[j][i][k]=0;
					}
					else if(c==1)
					{
						b[cn][i][0] = k; 
						for(int m = 1; m<10; m++)
							b[cn][i][m] = 0;
						changed = true;
					}     
					else if(c==2 && (scn-scn%3)==(cn-cn%3))
					{
						for(int m = 0; m<9; m++)
						{
							if(b[cn-cn%3+m%3][i-i%3+m/3][k] != 0 && (cn-cn%3+m%3)!=scn && (cn-cn%3+m%3)!=cn)
							{
								changed = true;
								b[cn-cn%3+m%3][i-i%3+m/3][k] = 0; 
							} 
						}
					}
					else if(c==3 && (scn-scn%3)==(cn-cn%3) && (scn-scn%3)==(tcn-tcn%3)) 
					{
						for(int m = 0; m<9; m++)
						{
							if(b[cn-cn%3+m%3][i-i%3+m/3][k] != 0 && (cn-cn%3+m%3)!=scn && (cn-cn%3+m%3)!=tcn && (cn-cn%3+m%3)!=cn)       
							{     
								changed = true;
								b[cn-cn%3+m%3][i-i%3+m/3][k] = 0; 
							}
						}
					}//*/
				}
				for(int i = 0; i<9; i+=3)//same as aboce 2 but for boxes   
				{
					for(int j = 0; j<9; j+=3) 
					{
						int bo = 0;  
						int in = 0;
						int tsn = 0;  
						int ssn = 0; 
						int sn = 0;   
						for(int s = 0; s<9; s++)
						{
							if(b[i+s%3][j+s/3][k]==1)
							{
								bo++;    
								tsn = ssn; 
								ssn = sn; 
								sn = s;  
							}
							if(b[i+s%3][j+s/3][0]==k)  
								in++;  
						}
						if(in>1)
						{ 
							impossible = true;
							break; 
						}
						else if(in==1) 
						{
							for(int s = 0; s<9; s++)  
								b[i+s%3][j+s/3][k]=0;
						}  
						else if(bo==1 && in==0) 
						{
							b[i+sn%3][j+sn/3][0] = k;  
							for(int m = 1; m<10; m++)  
								b[i+sn%3][j+sn/3][m] = 0;  
							changed = true;     
						}
						else if(bo==2 && sn%3==ssn%3) 
						{ 
							for(int m = 0; m<9; m++)
							{
								if((m-m%3)!=j && b[i+sn%3][m][k] != 0) 
								{ 
									changed = true; 
									b[i+sn%3][m][k] = 0;    
								}
							}
						}
						else if(bo==3 && sn%3==ssn%3 && ssn%3==tsn%3) 
						{
							for(int m = 0; m<9; m++)
							{
								if((m-m%3)!=j && b[i+sn%3][m][k] != 0)
								{      
									changed = true;  
									b[i+sn%3][m][k] = 0;
								}
							}
						}
						else if(bo==2 && sn/3==ssn/3)
						{ 
							for(int m = 0; m<9; m++)
							{
								if((m-m%3)!=i && b[m][j+sn/3][k] != 0)
								{    
									changed = true;
									b[m][j+sn/3][k] = 0;
								}
							}
						}
						else if(bo==3 && sn/3==ssn/3 && tsn/3==ssn/3)
						{
							for(int m = 0; m<9; m++)
							{
								if((m-m%3)!=i && b[m][j+sn/3][k] != 0)  
								{
									changed = true;     
									b[m][j+sn/3][k] = 0;
								}
							}
						}//*/
					}
				}
			}
			if(impossible)
			{ 
				return 2;
			} 
			if(!changed && !solved)
			{//do backtracking with a clone
				return 1;
			}
		}
		return solved?0:1;  
	}
}
/*
0 2 0 0 8 4 0 0 0
0 0 0 0 5 0 8 0 7
0 7 3 0 0 0 0 0 0
0 0 0 0 0 0 9 0 8
0 8 0 2 0 6 0 7 0
9 0 1 0 0 0 0 0 0
0 0 0 0 0 0 4 9 0
7 0 6 0 1 0 0 0 0
0 0 0 5 4 0 0 6 0

8 0 0 0 0 0 0 0 0
0 0 3 6 0 0 0 0 0 
0 7 0 0 9 0 2 0 0 
0 5 0 0 0 7 0 0 0 
0 0 0 0 4 5 7 0 0 
0 0 0 1 0 0 0 3 0 
0 0 1 0 0 0 0 6 8 
0 0 8 5 0 0 0 1 0 
0 9 0 0 0 0 4 0 0 

7 8 0 0 0 0 5 0 0
0 0 0 0 1 0 0 0 4
0 0 0 0 0 0 0 0 0
0 0 0 2 0 0 9 0 0
1 0 0 0 0 0 0 5 0
3 0 4 0 0 0 0 0 0
0 0 6 0 0 0 0 1 3
0 2 0 8 0 5 0 0 0
0 0 0 0 0 0 0 0 0
*/

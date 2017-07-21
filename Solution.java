package dumbshit;
import java.util.Scanner;

public class Solution {
	static int b[][][] = new int[9][9][10];
    public static void main(String[] args) {
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
        
        boolean solved = false;
        while(!solved)
        {
			boolean tsolved = true; 
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
							if(can && b[i][j][k]!=0)
							{
								b[i][j][k] = 1;
								c++;
								n = k;
							}
						}
						if(c==1)
						{	
							b[i][j][0] = n;
							b[i][j][n] = 0;
							changed = true;
						}
					}
					if(b[i][j][0]==0)
					{
						tsolved = false;
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
							r++;
							trn = srn;
							srn = rn;
							rn = j;
						}
						if(b[i][j][0]==k)
							in++;
					}
					if(r==1 && in==0)
					{
						b[i][rn][0] = k;
						for(int m = 1; m<10; m++)
							b[i][rn][m] = 0;
						changed = true;
					}
					if(r==2 && in==0 && (srn-srn%3)==(rn-rn%3))
					{
						for(int m = 0; m<9; m++)
						{
							if((rn-rn%3+m/3)!=srn && (rn-rn%3+m/3)!=rn)
								b[i-i%3+m%3][rn-rn%3+m/3][k] = 0;
						}
					}
					if(r==3 && in==0 && (srn-srn%3)==(rn-rn%3) && (rn-rn%3)==(trn-trn%3))
					{
						for(int m = 0; m<9; m++)
						{
							if((rn-rn%3+m/3)!=srn && (rn-rn%3+m/3)!=trn && (rn-rn%3+m/3)!=rn)
								b[i-i%3+m%3][rn-rn%3+m/3][k] = 0;
						}
					}//*/
				}
				for(int i = 0; i<9; i++)//col only can
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
					if(c==1 && in==0)
					{
						b[cn][i][0] = k;
						for(int m = 1; m<10; m++)
							b[cn][i][m] = 0;
						changed = true;
					}
					if(c==2 && in==0 && (scn-scn%3)==(cn-cn%3))
					{
						for(int m = 0; m<9; m++)
						{
							if((cn-cn%3+m%3)!=scn && (cn-cn%3+m%3)!=cn)
								b[cn-cn%3+m%3][i-i%3+m/3][k] = 0;
						}
					}
					if(c==3 && in==0 && (scn-scn%3)==(cn-cn%3) && (scn-scn%3)==(tcn-tcn%3))
					{
						for(int m = 0; m<9; m++)
						{
							if((cn-cn%3+m%3)!=scn && (cn-cn%3+m%3)!=tcn && (cn-cn%3+m%3)!=cn)
								b[cn-cn%3+m%3][i-i%3+m/3][k] = 0;
						}
					}//*/
				}
				for(int i = 0; i<9; i+=3)//box only can
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
						if(bo==1 && in==0)
						{
							b[i+sn%3][j+sn/3][0] = k;
							for(int m = 1; m<10; m++)
								b[i+sn%3][j+sn/3][m] = 0;
							changed = true;
						}
						if(bo==2 && in==0 && sn%3==ssn%3)
						{
							for(int m = 0; m<9; m++)
							{
								if((m-m%3)!=j)
									b[i+sn%3][m][k] = 0;
							}
						}
						if(bo==3 && in==0 && sn%3==ssn%3 && ssn%3==tsn%3)
						{
							for(int m = 0; m<9; m++)
							{
								if((m-m%3)!=j)
									b[i+sn%3][m][k] = 0;
							}
						}
						if(bo==2 && in==0 && sn/3==ssn/3)
						{
							for(int m = 0; m<9; m++)
							{
								if((m-m%3)!=i)
									b[m][j+sn/3][k] = 0;
							}
						}
						if(bo==3 && in==0 && sn/3==ssn/3 && tsn/3==ssn/3)
						{
							for(int m = 0; m<9; m++)
							{
								if((m-m%3)!=i)
									b[m][j+sn/3][k] = 0;
							}
						}//*/
					}
				}
			}
			
			solved = tsolved;
			if(!changed)
			{
				solved = true;
			}
        }
        
        for(int i = 0; i<9; i++)
        {
        	for(int j = 0; j<9; j++)
            {
            	System.out.print(b[i][j][0] + " ");
            }
        	System.out.println();
        }
        cin.close();
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
 */

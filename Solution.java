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
							if(can)
							{
								b[i][j][k] = 1;
								c++;
								n = k;
							}
							else
								b[i][j][k] = 0;
						}
						if(c==1)
						{	
							b[i][j][0] = n;
							b[i][j][n] = 0;
							changed = true;
							System.out.println("Only can for spot");
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
					int rn = 0;
					for(int j = 0; j<9; j++)
					{
						if(b[i][j][k]==1)
						{
							r++;
							rn = j;
						}
					}
					if(r==1)
					{
						b[i][rn][0] = k;
						for(int m = 1; m<10; m++)
							b[i][rn][m] = 0;
						changed = true;
						System.out.println("Only spot in row for can");
					}
				}
				for(int i = 0; i<9; i++)//col only can
				{
					int c = 0;
					int cn = 0;
					for(int j = 0; j<9; j++)
					{
						if(b[j][i][k]==1)
						{
							c++;
							cn = j;
						}
					}
					if(c==1)
					{
						b[cn][i][0] = k;
						for(int m = 1; m<10; m++)
							b[cn][i][m] = 0;
						changed = true;
						System.out.println("Only spot in col for can");
					}
				}
				for(int i = 0; i<9; i+=3)//box only can
				{
					for(int j = 0; j<9; j+=3)
					{
						int bo = 0;
						int sn = 0;
						for(int s = 0; s<9; s++)
						{
							if(b[i+s%3][j+s/3][k]==1)
							{
								bo++;
								sn = s;								
							}
						}
						if(bo==1)
						{
							b[i+sn%3][j+sn/3][0] = k;
							for(int m = 1; m<10; m++)
								b[i+sn%3][j+sn/3][m] = 0;
							changed = true;
							System.out.println("Only spot in box for can");
						}
					}
				}
			}
			solved = tsolved;
			if(!changed)
			{
				solved = true;
				System.out.println("Unsolvable");
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
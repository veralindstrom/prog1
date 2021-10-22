import static java.lang.System.out;

import java.util.Scanner;

public class BestamDenKortasteVagen {

	public static void main(String[] args) {
		Scanner    in = new Scanner (System.in);
		
		System.out.println("Antal stationer i zon 2 (U)");
		int n = in.nextInt() + 1;//ex 4
		
		//input antal V
		System.out.println("Antal stationer i zon 3 (V)");
		int m = in.nextInt() + 1;//ex 5
		
		double [] a = new double[n + 1];
		double [][] b = new double[n + 1][m + 1];	
		double [] c = new double[m + 1];
		
		//input för distans mellan stationer
			for(int i = 1; i < n; i++)
			{
				out.println("Väg " + i + " från zon 1 till zon 2:");
				a[i] = in.nextInt();
					for(int j = 1; j < m; j++)
					{	
						out.println("Väg " + j + " från zon 2 till zon 3:");
						b[i][j] =  in.nextInt();
					}
			}
			
			for(int j = 1; j < m; j++) {
				out.println("Väg " + j + " från zon 3 till zon 4:");
				c[j] = in.nextInt();	
			}
			
			//printar ut genom vilka stationer den kortaste vägen är samt längden
			System.out.println(
			  "Kortaste vägen från X till Y är genom station U" + DenKortasteVagen.mellanstationer(a, b, c)[1] 
			  + " och station V" + DenKortasteVagen.mellanstationer(a, b, c)[2]
			  + " med ett avstånd på " + DenKortasteVagen.langd(a, b, c));
			in.close();
	}
}
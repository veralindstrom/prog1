

public class DenKortasteVagen {

	// mellanstationer returnerar en vektor med de mellanstationer som finns på den kortaste
		 // vägen. Ordningsnummer av den första mellanstationen finns på index 1, och ordningsnummer
		 // av den andra mellanstationen på index 2 i vektorn.
		 
		public static int[] mellanstationer (double[] a, double[][] b, double[] c){
		 // koden här
			
			//längd på arrays
			int n = a.length - 1;
			int m = c.length - 1;
			
			//sätter minsta väg till första vägexempel
			double minstaVag = b[1][1] + a[1] + c[1];
			double kortvag = 0;
			int [] mellanstationer = new int[2 + 1];
			
			//tilldelar imdex av första vägexempel till array 
			mellanstationer[1] = 1;
			mellanstationer[2] = 1;
			
			//letar minsta väg
			for(int i = 1; i < n; i++)
			{
				for(int j = 1; j < m; j++)
				{
					kortvag = b[i][j] + a[i] + c[j];
					
					//tilldelar array nytt värde om det är den kortaste vägen
					if(kortvag < minstaVag)
					{
						minstaVag = kortvag;
					    
					    mellanstationer[1] = i;
					    mellanstationer[2] = j;
					}
				}
			}
			return mellanstationer;
		}
		
		 // langd returnerar längden av den kortaste vägen.
		 public static double langd (double[] a, double[][] b, double[] c){
			 
			int n = a.length - 1;
			int m = c.length - 1;
			
			double minstaVag = b[1][1] + a[1] + c[1];
			double kortvag = 0;
			
			for(int i = 1; i < n; i++)
			{
				for(int j = 1; j < m; j++)
				{
					kortvag = b[i][j] + a[i] + c[j];
					
					//tilldelar minstaVäg nytt värde om det är den kortaste vägen
					if(kortvag < minstaVag)
					{
						minstaVag = kortvag;
					}
				}
			}
			return minstaVag;
		}
}

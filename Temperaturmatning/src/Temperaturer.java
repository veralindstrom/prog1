import java.util.*;
import static java.lang.System.out;


public class Temperaturer {
	public static void main (String[] args){
		out.println ("TEMPERATURER\n");
		
		// inmatningsverktyg
		Scanner		in = new Scanner (System.in);
		//in.useLocale (Locale.US);
		
		// mata in uppgifter om antalet veckor och antaletmätningar
		out.print ("antalet veckor: ");
		int    antalVeckor = in.nextInt ();
		
		out.print ("antalet mätningar per vecka: ");
		int    antalMatningarPerVecka = in.nextInt ();
		
		// plats att lagra temperaturer
		double[][]    t = new double[antalVeckor + 1][antalMatningarPerVecka + 1];
	
		// mata in temperaturerna
		for (int vecka = 1; vecka <= antalVeckor; vecka++){
			out.println ("temperaturer -vecka " + vecka+ ":");
			
			for (int matning = 1;matning <= antalMatningarPerVecka; 
					matning++)t[vecka][matning] = in.nextDouble ();
			}
		out.println ();
		
		// visa temperaturerna
		out.println ("temperaturerna:");
		for (int vecka = 1; vecka <= antalVeckor; vecka++){
			for (int matning = 1;matning <= antalMatningarPerVecka; matning++)
				out.print (t[vecka][matning] + " ");
				
			out.println ();
		}
		out.println ();
		
		// den minsta,den största och medeltemperaturen–veckovis
		double[]    minT = new double[antalVeckor + 1];
		double[]    maxT = new double[antalVeckor + 1];
		double[]    sumT = new double[antalVeckor + 1];
		double[]    medelT = new double[antalVeckor + 1];
		// koden ska skrivas här
		// visa den minsta, den största och medeltemperaturen för varje vecka

		//for loop i for loop för 2D array
		for (int j = 1; j <= antalVeckor; j++) { //går igenom veckorna
			minT[j] = t[j][1];
			maxT[j] = t[j][1];
			sumT[j] = 0;
			
			for (int i = 1; i <= antalMatningarPerVecka; i++) { //går igenom varje inmatning
				
				//hitta minsta värde
				if (t[j][i] <= minT[j]) {
					minT[j] = t[j][i];
				}
				//hitta max värde
				if (t[j][i] >= maxT[j]) {
					maxT[j] = t[j][i];
				}	
				//bestäm summa
				sumT[j] += t[j][i];
				
				//bestäm medelvärde
				
			}medelT[j] = sumT[j] / antalMatningarPerVecka;
		}
		//printar ut minst, störst, summa och medelvärde för varje vecka
		for (int v = 1; v <= antalVeckor; v++) {
				out.println("minsta temp -vecka " + v + ": " + minT[v]);
				out.println("största temp -vecka " + v + ": " + maxT[v]);
				out.println("summa av temp -vecka " + v + ": " + sumT[v]);
				out.println("medelvärde av temp -vecka " + v + ": " + medelT[v] + "\n");
			}
		
		// koden ska skrivas här
		// den minsta, den största och medeltemperaturen -helamätperioden
		
		//sorterar arrays i storleksordning från minst till störst
		
		
		//Arrays.sort(minT);
		//Arrays.sort(maxT);
		
		//out.print(Arrays.toString(minT));
		//out.print(Arrays.toString(maxT));
		
		double    minTemp = minT[1]; 
		double    maxTemp = maxT[1]; 
		double    sumTemp = sumT[0];
		double    medelTemp = 0;
		
		// koden ska skrivas här
		for(int k = 1; k <= antalMatningarPerVecka; k++) {
			if (minT[k] < minTemp) {
				minTemp = minT[k];
			}
			if (maxT[k] > maxTemp) {
				maxTemp = maxT[k];
			}
			
			sumTemp += sumT[k];
			
		}
		
		//hitar medeltemperaturen
		medelTemp = sumTemp / (antalVeckor * antalMatningarPerVecka);
		
		out.println("Minsta temp: " + minTemp 
				+"\n Största temp: " + maxTemp 
				+ "\n Medeltemp: " + medelTemp);
		// visa den minsta, den största och medeltemperaturen i hela mätperioden
		//koden ska skrivas här
		
		in.close();
	}
	
	
}

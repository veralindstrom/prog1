
import java.util.Scanner;

public class MinstaHeltal {
	// min returnerar det minsta elementet i en sekventiellsamling.
	// Om samlingen är tom, kastas ett undantagav typen IllegalArgumentException.
	public static int min (int[] element)
			throws IllegalArgumentException{
		if (element.length == 0)
			throw new IllegalArgumentException ("tom samling");
		
		// hör ihop med spårutskriften 2:
		// int    antalVarv = 1;
		int[]    sekvens = element;
		int 	 antaletPar = sekvens.length / 2;
		int      antaletOparadeElement = sekvens.length % 2;
		int      antaletTankbaraElement = antaletPar + antaletOparadeElement;
		int[]    delsekvens = new int[antaletTankbaraElement];
		int      i = 0;
		int      j = 0;
		
		while (antaletPar >= 1){
			// skilj ur en delsekvens med de tänkbara elementen
			i = 0;
			j = 0;
			
			while (j < antaletPar){
				delsekvens[j++] = (sekvens[i] < sekvens[i + 1]) ? sekvens[i] : sekvens[i + 1]; //jämför pos 1 med pos 2
																					
				i += 2; //1 med 2, 3 med 4, 5 med 6
			
			}
			if (antaletOparadeElement == 1)
				delsekvens[j] = sekvens[2 * antaletPar + antaletOparadeElement - 1];
			
			// utgå nu ifrån delsekvensen
			sekvens = delsekvens;
			antaletPar = antaletTankbaraElement / 2;
			antaletOparadeElement = antaletTankbaraElement % 2;
			antaletTankbaraElement = antaletPar + antaletOparadeElement;
			
			// spårutskrift 1 –för att följasekvensen
			
			System.out.println (java.util.Arrays.toString (sekvens));
			
			// spårutskrift 2 -för att avsluta loopen i förväg
			// (för att kunna se vad som händer i början)
			
			//if (antalVarv++ == 10)
		    //System.exit (0);
		}
			// sekvens[0] är det enda återstående tänkbara elementet
			//-det är det minsta elementet
		return sekvens[0];
	}
	
	public static int min2 (int[] element) {
		int [] sekvens = element;
		
		int minst = sekvens[0];
		
		for(int i = 1; i < sekvens.length; i++){
				if(sekvens[i] < minst){
					minst = sekvens[i];
				}
		}
		return minst;
	}
	
	 public static void main(String[] args) {
		 Scanner in = new Scanner(System.in);
			System.out.println("Hur många tal vill du jämföra?");
			int antalTal =  in.nextInt();
			int [] element = new int[antalTal];
			System.out.println("Skriv in dina tal.");
			for(int i = 0; i < antalTal; i++)
			{
				element[i] = in.nextInt();
			}
		int m = min(element);
		in.close();
		System.out.println(m);
	 }
}

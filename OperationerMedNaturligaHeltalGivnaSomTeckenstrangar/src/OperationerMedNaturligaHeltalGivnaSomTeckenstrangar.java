import java.util.*;    // Scanner
import static java.lang.System.out;

public class OperationerMedNaturligaHeltalGivnaSomTeckenstrangar {
	
	public static void main (String[] args){
		
		out.println ("OPERATIONER MED NATURLIGA HELTAL GIVNA SOM TECKENSTRANGAR\n");
		
		// mata in två naturliga heltal
		Scanner    in = new Scanner (System.in);
		
		out.println ("två naturliga heltal:");
		String    tal1 = in.next ();
		String    tal2 = in.next ();
		out.println ();
		
		// addera heltalenoch visa resultatet
		String    summa = addera(tal1, tal2);
		visa(tal1, tal2, summa, '+');
		
		// subtrahera heltalen och visa resultatet
		//koden här
		
		String    differens = subtrahera(tal1, tal2);
		visa(tal1, tal2, differens, '-');
		
		in.close();
	}
	
	// addera tar emot två naturliga heltal givna somteckensträngar, och returnerar deras
	// summa som en teckensträng.
	
	public static String addera(String tal1, String tal2){
		// koden ska skrivas här
		
		StringBuilder sb = new StringBuilder("");
		
		int pos1 = tal1.length() - 1;
		int pos2 = tal2.length() - 1;
		int n1 = 0;
		int n2 = 0;
		
		int minnesSiffra = 0;
		int resultatSiffra = 0;
		
		int sum = 0;
		
		while (pos1 >= 0 && pos2 >= 0) {
			n1 = tal1.charAt(pos1) - 48;
			n2 = tal2.charAt(pos2) - 48;
			
			sum = n1 + n2 + minnesSiffra;
			
			minnesSiffra = sum / 10;
			resultatSiffra = sum % 10;
			
			sb.insert(0, resultatSiffra);
			
			pos1 --;
			pos2 --;
		}
		
		while(pos1 >= 0) {
			n1 = tal1.charAt(pos1) - 48;
	
			sum = n1 + minnesSiffra;
			
			minnesSiffra = sum / 10;
			resultatSiffra = sum % 10;
			
			sb.insert(0, resultatSiffra);
			
			//sb.insert(0, n1);
			pos1--;
		}
		
		while(pos2 >= 0) {
			n2 = tal1.charAt(pos2) - 48;
			
			sum = n2 + minnesSiffra;
			
			minnesSiffra = sum / 10;
			resultatSiffra = sum % 10;
			
			sb.insert(0, resultatSiffra);
			
			//sb.insert(0, n2);
			pos2--;
		}
		
		if(minnesSiffra > 0)
			sb.insert(0, minnesSiffra);
		
		return sb.toString();
		
	}
	
	// subtrahera tar emot två naturliga heltal givna somteckensträngar, och returnerar
	// deras differens somen teckensträng.
	// Det första heltalet är inte mindre än det andra heltalet.
	
	public static String subtrahera(String tal1, String tal2){
		// koden ska skrivas här
		StringBuilder sb = new StringBuilder("");
		int pos1 = tal1.length() - 1;
		int pos2 = tal2.length() - 1;
		int n1 = 0;
		int n2 = 0;
		int minnesSiffra = 0;
		int sum = 0;
		
		while (pos1 >= 0 && pos2 >= 0) {
			n1 = tal1.charAt(pos1) - 48;
			n2 = tal2.charAt(pos2) - 48;
			
			if (n1 != 0) {
					n1 -= minnesSiffra;
					minnesSiffra = 0;
			}
			
			else if (n1 == 0) {
					n1 += 10 - minnesSiffra;
					minnesSiffra = 1;
			}	
			
			if (n1 < n2) {
					n1 += 10;
					minnesSiffra = 1;
				}
			
			sum = n1 - n2;
			sb.insert(0, sum);
			
			pos1 --;
			pos2 --;
		}
		
		while(pos1 >= 0) {
			n1 = tal1.charAt(pos1) - 48;
			
			if (n1 != 0) {
				n1 -= minnesSiffra;
				minnesSiffra = 0;
			}
			else if (n1 == 0) {
				n1 += 10 - minnesSiffra;
				minnesSiffra = 1;
			}
			
			sb.insert(0, n1);
			
			pos1--;
		}
		return sb.toString();
	}
	
	// visa visar två givna naturliga heltal, och resultatet av en aritmetisk operation
	// utförd i samband med hetalen
	public static void visa (String tal1, String tal2,String resultat, char operator){
		// sätt en lämplig längd på heltalen och resultatet
		int    len1 = tal1.length ();
		int    len2 = tal2.length ();
		int    len  = resultat.length ();
		int    maxLen = Math.max (Math.max (len1, len2), len);
		
		tal1 = sattLen (tal1, maxLen -len1);
		tal2 = sattLen (tal2, maxLen -len2);
		resultat = sattLen (resultat, maxLen -len);
		
		// visa heltalen och resultatet
		out.println ("  " + tal1);
		out.println ("" + operator + " " + tal2);
		
		for (int i = 0; i < maxLen + 2; i++)
			out.print ("-");
		
		out.println ();
		out.println ("  " + resultat + "\n");
		
	}
	
	// sattLen lägger till ett angivet antal mellanslag i början av en given sträng
	public static String sattLen (String s, int antal){
		StringBuilder    sb = new StringBuilder (s);
		
		for (int i = 0; i < antal; i++)
			sb.insert (0, " ");
		
		return sb.toString ();
		
	}
}


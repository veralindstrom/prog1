import static java.lang.System.out;

import java.util.Random;

public class Polylinjer {
	public static final Random    rand = new Random ();
	public static final int    ANTAL_POLYLINJER = 10;
	
	public static void main (String[] args){
		
		VPolylinje[]    Vpolylinjer = new VPolylinje[ANTAL_POLYLINJER];
		NPolylinje[]    Npolylinjer = new NPolylinje[ANTAL_POLYLINJER];
		Polylinje[]    polylinjer = new Polylinje[ANTAL_POLYLINJER];
		
		for (int i = 0; i < ANTAL_POLYLINJER; i++) {
			Vpolylinjer[i] = slumpVPolylinje ();
			Npolylinjer[i] = slumpNPolylinje ();
			// visa polylinjerna
			/*out.println(Vpolylinjer[i]);
			out.println(Npolylinjer[i]); */
		}
		
		for (int i = 0; i < ANTAL_POLYLINJER; i+=2) {
			polylinjer[i] = Npolylinjer[i];
			polylinjer[i+1] = Vpolylinjer[i];
		}
		
		out.println("Kortast gula poly för blandad vektor:\n" + Polylinjer.kortastGulaPoly(polylinjer));
		out.println("Kortast gula poly för N-vektor:\n" + Polylinjer.kortastGulaPoly(Npolylinjer));
		out.println("Kortast gula poly för V-vektor:\n" + Polylinjer.kortastGulaPoly(Vpolylinjer));
		
		
	
	}
	
	public static String kortastGulaPoly (Polylinje[] antalPolys) {
				// bestäm den kortaste av de polylinjer som är gula
				//visa den valda polylinjen
				Polylinje valdPolylinje = null;
				String p ="";
				int j = 0;
				double kortast = 0;
				
				for(int i = 0; i < antalPolys.length; i++){
					if(antalPolys[i].getFarg().equals("gul")){
						if(j == 0){
							kortast = antalPolys[i].langd();
							valdPolylinje = antalPolys[i];
							j++;
						}
						
						if(antalPolys[i].langd() < kortast){
							kortast = antalPolys[i].langd();
							valdPolylinje = antalPolys[i];
						}
					}
				}
				
				return p + valdPolylinje +"\nMed längden: " + kortast + "\n"; 
				//om ingen gul, output blir null
	}
	


//slumpPunkt returnerar en punkt med ett slumpmässigt namn,som är en stor bokstav i
	// det engelska alfabetet, och slumpmässiga koordinater.
	public static Punkt slumpPunkt (){
		String    n = "" + (char) (65 + rand.nextInt (26)); //26
		int    x = rand.nextInt (11);
		int    y = rand.nextInt (11);
		
		return new Punkt (n, x, y);
	}
	
					/*--- POLYLINJE ---*/
	public static Polylinje1 slump1Polylinje (){
		// skapa en tom polylinje, och lägg till hörn till den
		Polylinje1 polylinje = new Polylinje1 ();
		int    antalHorn = 2 + rand.nextInt (7);
		int    antalValdaHorn = 0;
		boolean[]    valdaNamn = new boolean[26];
		
		// ett och samma namn kan inte förekomma flera gånger
		Punkt   valdPunkt = null;
		char    valtChar = 0;
		
		while (antalValdaHorn < antalHorn){
			valdPunkt = slumpPunkt();;
			valtChar = valdPunkt.getNamn().charAt(0);
			
			if(!valdaNamn[valtChar - 65]) {
				polylinje.laggTill(valdPunkt);
				valdaNamn[valtChar - 65] = true;
				antalValdaHorn++;
			}	
		}
		
		// sätt färg
		String[] farger = {"blå", "röd", "gul"};
		int i = (new Random().nextInt(3));
		String farg = farger[i];
		polylinje.setFarg(farg);
		
		return polylinje;
	}

	
				/*--- VPOLYLINJE ---*/
	public static VPolylinje slumpVPolylinje (){
		// skapa en tom polylinje, och lägg till hörn till den
		VPolylinje polylinje = new VPolylinje ();
		int    antalHorn = 2 + rand.nextInt (7);
		int    antalValdaHorn = 0;
		boolean[]    valdaNamn = new boolean[26];
		
		// ett och samma namn kan inte förekomma flera gånger
		Punkt   valdPunkt = null;
		char    valtChar = 0;
		
		while (antalValdaHorn < antalHorn){
			valdPunkt = slumpPunkt();;
			valtChar = valdPunkt.getNamn().charAt(0);
			
			if(!valdaNamn[valtChar - 65]) {
				polylinje.laggTill(valdPunkt);
				valdaNamn[valtChar - 65] = true;
				antalValdaHorn++;
			}	
		}
		
		// sätt färg
		String[] farger = {"blå", "röd", "gul"};
		int i = (new Random().nextInt(3));
		String farg = farger[i];
		polylinje.setFarg(farg);
		
		return polylinje;
	}

				/*--- NPOLYLINJE ---*/
	public static NPolylinje slumpNPolylinje (){
		// skapa en tom polylinje, och lägg till hörn till den
		NPolylinje polylinje = new NPolylinje ();
		int    antalHorn = 2 + rand.nextInt (7);
		int    antalValdaHorn = 0;
		boolean[]    valdaNamn = new boolean[26];
		
		// ett och samma namn kan inte förekomma flera gånger
		Punkt   valdPunkt = null;
		char    valtChar = 0;
		
		while (antalValdaHorn < antalHorn){
			valdPunkt = slumpPunkt();;
			valtChar = valdPunkt.getNamn().charAt(0);
			
			if(!valdaNamn[valtChar - 65]) {
				polylinje.laggTill(valdPunkt);
				valdaNamn[valtChar - 65] = true;
				antalValdaHorn++;
			}	
		}
		
		// sätt färg
		String[] farger = {"blå", "röd", "gul"};
		int i = (new Random().nextInt(3));
		String farg = farger[i];
		polylinje.setFarg(farg);
		
		return polylinje;
	}
}

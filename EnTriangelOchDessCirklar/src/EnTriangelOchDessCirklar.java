import static java.lang.System.out;
import java.util.Scanner;

public class EnTriangelOchDessCirklar {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner    in = new Scanner (System.in);
		
		//input för triangelns sido-längder
		out.println("Längd för sida A: ");
		double    aInput = in.nextDouble();
		out.println("Längd för sida B: ");
		double    bInput = in.nextDouble();
		out.println("Längd för sida C: ");
		double    cInput = in.nextDouble();
		
		//input för en vinkel i triangeln (i grader)
		//eller 0 om användare vill att vinkeln ska beräknas
		out.println("Vinkel (i grader) eller 0 (om vinkel är okänd): ");
		double    angle = in.nextDouble();
		
		//hämtar beräknad vinkel från classen Triangel med metoden angle()
		if (angle == 0)
			angle = Triangel.angle(aInput, bInput, cInput);
		
		//skriver it triangelns bisektris
		out.println("\nBisektris: " + Triangel.bisektris(bInput, cInput, angle));
		
		//skriver ut triangelns höjd
		out.println("Höjd: " + Triangel.hojd(aInput, bInput, cInput));
			
		//skriver ut triangelns area
		out.println("Area för triangel: " + Triangel.area(cInput, Triangel.hojd(aInput, bInput, cInput)) + "\n");
		
		//skriver ut radien för triangelns omskrivna cirkel
		out.println("Radie för omskriven cirkel: " + Triangel.omskrivenCirkel(aInput, bInput, cInput));
		
		//skriver ut radien för triangelns inskrivna cirkel
		out.println("Radie för inskriven cirkel: " + Triangel.inskrivenCirkel(aInput, bInput, cInput));
		in.close();
	}

}

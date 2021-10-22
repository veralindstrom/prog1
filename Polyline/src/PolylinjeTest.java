import java.io.*;    // PrintWriter

public class PolylinjeTest {

	public static void main(String[] args) {
		PrintWriter    out = new PrintWriter (System.out, true); 
		Polylinje p = new Polylinje(); //skapa Polylinje objekt
		Punkt[] pTest = new Punkt[5];
	
		pTest[0] = new Punkt("A", 3, 4);
		pTest[1] = new Punkt("B", 1, 2);
		pTest[2] = new Punkt("C", 4, 6);
		pTest[3] = new Punkt("D", 5, 1);
		pTest[4] = new Punkt("E", 4, 3);
		
		//Polylinje poly2 = new Polylinje(pTest);
		
		p.laggTill(new Punkt("A", 3, 4));
		p.laggTill(new Punkt("B", 1, 2));
		p.laggTill(new Punkt("D", 5, 1));
		out.println(p);
		
		p.laggTillFramfor((new Punkt("C", 4, 6)),"D");
		out.println(p);
		
		p.laggTill(new Punkt("E", 4, 3));
		p.laggTill(new Punkt("F", 7, 8));
		
		p.setFarg("rosa");
		p.setBredd(3);
		out.println(p);
		
		
		out.println("Färg: " + p.getFarg());
		out.println("Bredd: " + p.getBredd());
		out.println("Längd: " + p.langd());
		
		out.println("");
		
		for (int i = 0; i < p.getHorn().length; i++)
			out.println("Hörn: " + p.getHorn()[i]); 
		
		p.taBort("C");
		out.println(p);
		
		out.println("");
		Polylinje.PolylinjeIterator iterator = p.new PolylinjeIterator();
		
		while(iterator.finnsHorn()) {
			Punkt aktuellHorn = iterator.horn();
			out.println("Hörn: " + aktuellHorn);
			
			iterator.gaFram();
			
		}
		out.close();	
	}
	
	
}

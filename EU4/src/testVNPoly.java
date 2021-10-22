import java.util.Iterator;

public class testVNPoly {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Polylinje     polylinjeV = null;
		Polylinje     polylinjeN = null;
		polylinjeV = new VPolylinje ();// (1)
		polylinjeN = new NPolylinje ();// (2)
		
		System.out.println("-----V POLYLINJE-----\n");
		polylinjeV.laggTill(new Punkt("A", 3, 4));
		polylinjeV.laggTill(new Punkt("B", 1, 2));
		polylinjeV.laggTill(new Punkt("D", 5, 1));
		
		for (Punkt horn : polylinjeV)
			System.out.println (horn);
		
		polylinjeV.laggTillFramfor(new Punkt("C", 4, 6), "D");
		polylinjeV.taBort("A");
		
		
		Iterator <Punkt> itV = polylinjeV.iterator();
		//Iterator <Punkt> itN = polylinjeN.iterator();
		
		while(itV.hasNext()) {
			System.out.println("ITERATOR "+ itV.next());}
		
			
		
		
		
		System.out.println(polylinjeV.getFarg());
		
		//= new VPolylinje (pTest); // (1)
		//polylinje = new NPolylinje (pTest); // (2)
		
	//	polylinje.laggTillFramfor(new Punkt("E", 4, 3), "A");
		
		System.out.println(polylinjeV.getBredd());
		
		/*for (Punkt horn : polylinje)
			System.out.println (horn); */
		
		polylinjeV.setFarg("rosa");
		
		System.out.println(polylinjeV.getFarg());
		
		polylinjeV.setBredd(5);
		
		System.out.println(polylinjeV.getBredd());
		System.out.println(polylinjeV.langd());
		System.out.println(polylinjeV.getHorn()[2]);
		
		/* ---- N ---- */
		
		System.out.println("\n-----N POLYLINJE-----\n");
		polylinjeN.laggTill(new Punkt("A", 3, 4));
		polylinjeN.laggTill(new Punkt("B", 1, 2));
		polylinjeN.laggTill(new Punkt("D", 5, 1));
		
		for (Punkt horn : polylinjeN)
			System.out.println (horn);
		
		polylinjeN.laggTillFramfor(new Punkt("C", 4, 6), "D");
		polylinjeN.taBort("A");
		
		System.out.println("ITERATOR "+ polylinjeN.iterator());
		
		
		
		System.out.println(polylinjeN.getFarg());
		
		//= new VPolylinje (pTest); // (1)
		//polylinje = new NPolylinje (pTest); // (2)
		
	//	polylinje.laggTillFramfor(new Punkt("E", 4, 3), "A");
		
		System.out.println(polylinjeN.getBredd());
		
		/*for (Punkt horn : polylinje)
			System.out.println (horn); */
		
		polylinjeN.setFarg("rosa");
		
		System.out.println(polylinjeN.getFarg());
		
		polylinjeN.setBredd(5);
		
		System.out.println(polylinjeN.getBredd());
		System.out.println(polylinjeN.langd());
		System.out.println(polylinjeN.getHorn()[3]);
		
	}

}

import java.util.Iterator; 

public class NPolylinje implements Polylinje {
		private static class Nod {
			public Punkt    horn;
			public Nod      nastaNod;
			public Nod (Punkt horn) {
				this.horn = horn; //value
				nastaNod = null; //nextValue
			}
	}
	
	private Nod    horn;
	private String     farg = "svart";
	private int        bredd= 1;  // pixlar
	
	public NPolylinje () {
		this.horn = null; //first
		
	} 
	
	public NPolylinje (Punkt[] horn) {
		if (horn.length > 0) {
			Nod   nod = new Nod (new Punkt (horn[0]));
			this.horn = nod;
			int    pos = 1;
			
			while (pos < horn.length) {
				nod.nastaNod = new Nod (new Punkt (horn[pos++]));
				nod = nod.nastaNod;
				
			}
			
		}
		
	}
	
	public String toString () {
		Nod node = this.horn;
		String s = "{[";
		
		while(node != null) {
			s += node.horn; 
			if(!(node.nastaNod == null))
				s += node.horn;
			node = node.nastaNod;
		}
		return s + "], " + farg + ", " + bredd + "}";
	}
	
	public int size() {
		int size = 0; //storlek från början är 0
		Nod n = this.horn; //lista med hörn från Polylinje klassen
		
		while(n != null) { //sålänge det finns hörn/element
			size++; 		//storleken ökar
			n = n.nastaNod; //itererar genom alla element
		}
		//om lista med hörn är tom returneras size = 0
		//annars returneras size från loop
		return size; 
	}	
	
	public Punkt[] getHorn () {
		int size = 0; //storlek från början är 0
		Nod n = this.horn; //lista med hörn från Polylinje klassen
		
		while(n != null) { //sålänge det finns hörn/element
			size++; 		//storleken ökar
			n = n.nastaNod; //itererar genom alla element
		}
		
		Punkt[]		getHorn = new Punkt[size];	
		n = this.horn;
		
		for (int i = 0; i < size; i++) {
			getHorn[i] = n.horn;
			n = n.nastaNod;
		}
		
		return getHorn;
	}
		
	//hämtar färg
	public String getFarg () {
		return farg; 
	}
		
	//hämtar bredd
	public int getBredd () {
		return bredd;
	}
		
	//sätter färg till ny färg
	public void setFarg(String farg) {
		this.farg = farg;
	}
		
	//sätter bredd till ny bredd
	public void setBredd (int bredd) {
		this.bredd = bredd;
	}
	
	public double langd () {
		//int katetx;
		Nod posNode = this.horn; 
		
		double langd = 0;
		
		for (int i = 0; i < size() - 1; i++) {
			langd += posNode.horn.avstand(posNode.nastaNod.horn);
			
			posNode = posNode.nastaNod;
		}
		return langd;
	}
	
		
	//tar emot en punkt
	public void laggTill(Punkt horn) { 
		Nod node = new Nod(horn); //skapar nod den punkt som ska läggas in (från Polylinje klassen)
		
		if (this.horn == null) //om lista med hörn är tom
			this.horn = node; //lägg in hörn från Polylinje direkt
		
		else { 					//annars
			Nod n = this.horn;		//skapa ny nod n som pekar på listan med hörn
			while (n.nastaNod != null)  //sålänge n pekar på ett hörn (element)
				n = n.nastaNod;		//sätt pekare på nästa hörn (element), iterera genom lista
			n.nastaNod = node; //när pekare är tom/ej pekar på något hörn, alltså i slutet av listan
								//lägg till ny punkt på den platsen n pekar på
		}
		
	}
	
	public void laggTillFramfor(Punkt horn, String hornNamn) {
		Nod nyNode = new Nod(horn); //nytt hörn
		
		Nod posNode = this.horn; //där vi vill lägga nytt hörn
		Nod forgaendeNode = null; //där vi vill lägga nytt hörn framför
		
		while(posNode != null && //om det finns hörn
				!posNode.horn.getNamn().equals(hornNamn)) { //och det inte är det hörn vi vill lägga framför
			
			forgaendeNode = posNode;
			posNode = posNode.nastaNod;  //gå vidare
										//till nästa och kolla igen
		}
		
		if(forgaendeNode == null) { //om vi vill lägga nytt hörn som första element
			nyNode.nastaNod = posNode;
			posNode = nyNode;
			
		}
		else {
			forgaendeNode.nastaNod = nyNode; //annars, lägg till framför hornNamn
			nyNode.nastaNod = posNode;
		}
	}
	
	public void taBort (String hornNamn) {
		//Nod nyNode = null; //hörn som ska tas bort
		
		Nod posNode = this.horn; //nasta nod
		Nod forgaendeNode = null; //det hörn som ligger bakom det vi vill ta bort
		
		while(posNode != null && //sålänge det finns hörn
				!posNode.horn.getNamn().equals(hornNamn)){ //och det inte är det hörn vi vill ta bort
			forgaendeNode = posNode;  //gå vidare
			posNode = posNode.nastaNod; //till nästa och kolla igen
		}
		
		if(forgaendeNode == null) {
			//forgaendeNode.nastaNod = posNode.nastaNod;
			posNode = posNode.nastaNod;
		}
		
		else if (posNode.nastaNod == null) {
			posNode = null;
			forgaendeNode.nastaNod = posNode;
		}
		
		else {
			forgaendeNode.nastaNod = posNode.nastaNod;
			posNode = posNode.nastaNod;
		}
			
		
		
	}
	
	
	private class PolylinjeIterator implements Iterator<Punkt> {
		private Nod aktuell = null;
		
		//om det finns element i array, aktuell = 0, det finns hörn
		public PolylinjeIterator () {
			if (NPolylinje.this.horn != null)
				aktuell = NPolylinje.this.horn ;
		}
		
		//ger true om hörn/element finns i array
		//annars false
		public boolean hasNext () {
			return aktuell != null; 
		}
		
		public Punkt next () 
				throws java.util.NoSuchElementException {
			if (!this.hasNext ()) //om finnsHörn = false skicka exception
				throw new java.util.NoSuchElementException 
				("slut av iterationen");
			
			Punkt    horn = aktuell.horn; 
			aktuell = aktuell.nastaNod;
			
			return horn;
		}
		
		//sålänge akutell = true, alltså finns hörn, och aktuell inte är större än antal hörn så iterera genom lista
		//annars blir aktuell = -1 vilket ger finnsHorn = false
	}
	
	public Iterator<Punkt> iterator() {
		// TODO Auto-generated method stub
		
		return this.new PolylinjeIterator();
	}
}
import java.util.Iterator;

public class VPolylinje implements Polylinje {
	private Punkt[]    horn;
	private String     farg = "svart";
	private int        bredd = 1;
	
	public VPolylinje () {
		this.horn = new Punkt[0];
	}
	
	//skapar en ny polylinje 
	public VPolylinje (Punkt[] horn){
		this.horn = new Punkt[horn.length];
		
		for (int i = 0; i < horn.length; i++)
			this.horn[i] = new Punkt (horn[i]);
		
	}
	
	//skriver ut på formen {([A 3 4][B 1 2][C 2 3][D 5 1]), svart, 1}
	
	public String toString () {
		String polylinje = "{(";
		for(int i = 0; i < this.horn.length; i++) {
			polylinje += horn[i]; //omvandlar varje reference id till string
		}
		return polylinje + "), " + farg + ", " + bredd + "}";
	}
	
	//skapar ny vektor som kopierar alla hörn från vektor horn och returnerar reference id
	public Punkt[] getHorn () {
		Punkt[]		getHorn = new Punkt[this.horn.length];
		for (int i = 0; i < horn.length; i++) {
			getHorn[i] = this.horn[i];
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
	
	//beräknar längd med pythogoras sats
	//beräknas hypotenusan/längden mellan varje punkt
	public double langd () {
		//int katetx;
		//int katety;
		double langd = 0;
		
		for (int i = 0; i < horn.length - 1; i++) {
			/*katetx = horn[i+1].getX() - horn[i].getX(); //B(x) - A(x) => längd för katet på x-axeln
			katety = horn[i+1].getY() - horn[i].getX();  //B(y) - A(y) => längd för katet på y-axeln
			
			langd += Math.sqrt((Math.pow(katetx, 2)) + (Math.pow(katety, 2)));*/
			
			langd += horn[i].avstand(horn[i+1]);
		}
		return langd;
	}
	
	//lägger till en punkt på sista plats genom ny vektor som har en plats mer
	public void laggTill (Punkt horn){
		Punkt[]    h = new Punkt[this.horn.length + 1];
		int    i = 0;
		
		for (i = 0; i < this.horn.length; i++)
			h[i] = this.horn[i];
		
		h[i] = new Punkt (horn);
		this.horn = h;
		
	}
	
	public void laggTillFramfor (Punkt horn, String hornNamn) {
		Punkt[]    h = new Punkt[this.horn.length + 1];
		int    pos = 0;
		
		//letar vilket hörn man vill sätta nytt hörn framför
		//ersätter den platsen med nytt hörn
		for (int i = 0; i < this.horn.length; i++) {
			h[i] = this.horn[i]; //kopierar över vektor horn till vektor h
				if(hornNamn == this.horn[i].getNamn()) { 
					pos = i;
					h[i] = new Punkt (horn);; //ersätter hörnet med hornNamn med nytt hörn horn
				}
		}
		//lägger till resterande horn efter nytt hörn
		for (int i = pos + 1; i < this.horn.length + 1; i++) { 
			h[i] = this.horn[i - 1]; //nästa hörn i horn[] förflyttas ett steg fram i arrayen h[]
		} 
		
		this.horn = h;
	}
	
	//tar bort ett element genom att skapa och returnera ny array 
	public void taBort (String hornNamn) {
		Punkt[]    h = new Punkt[this.horn.length - 1];
		for (int i = 0, k = 0; i < this.horn.length; i++) { 
			//h[i] = this.horn[i];
			//om hornNamn är det man vill ta bort
			if(hornNamn == this.horn[i].getNamn()) 
				continue; //strunta i att kopiera denna
			else
				h[k++] = this.horn[i];
        } 
   
         this.horn = h; 
	}
	
	private class PolylinjeIterator implements Iterator<Punkt> {
		private int    aktuell = -1;
		
		//om det finns element i array, aktuell = 0, det finns hörn
		public PolylinjeIterator () {
			if (VPolylinje.this.horn.length > 0)
				aktuell = 0;
		}
		
		//ger true om hörn/element finns i array
		//annars false
		public boolean hasNext () {
			return aktuell != -1; 
		}
		
		public Punkt next () 
				throws java.util.NoSuchElementException {
			if (!this.hasNext ()) //om finnsHörn = false skicka exception
				throw new java.util.NoSuchElementException 
				("slut av iterationen");
			
			Punkt    horn = VPolylinje.this.horn[aktuell]; 
			
			if (aktuell >= 0  && aktuell < VPolylinje.this.horn.length -1)
				aktuell++;
			else
				aktuell = -1;
			
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

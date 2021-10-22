
public class Polylinje1 {
	private Punkt[]    horn;
	private String     farg = "svart";
	private int        bredd = 1;
	
	public Polylinje1 () {
		this.horn = new Punkt[0];
		
	}
	
	//skapar en ny polylinje 
	public Polylinje1 (Punkt[] horn){
		this.horn = horn;
		
	}
	
	//skriver ut på formen {[(A 3 4)(B 1 2)(C 2 3)(D 5 1)], svart, 1}
	public String toString () {
		String polylinje = "{[ ";
		for(int i = 0; i < this.horn.length; i++) {
			polylinje += horn[i]; //omvandlar varje reference id till string
		}
		return polylinje + "], " + bredd + "," + farg + "}";
	}
	
	//hämtar hörn
	public Punkt[] getHorn1 () {
		return horn;
	}
	
	//hämtar färg
	public String getFarg1 () {
		return farg; 
	}
	
	//hämtar bredd
	public int getBredd1 () {
		return bredd;
	}
	
	//sätter färg till ny färg
	public void setFarg1 (String farg) {
		this.farg = farg;
	}
	
	//sätter bredd till ny bredd
	public void setBredd1 (int bredd) {
		this.bredd = bredd;
	}
	
	//beräknar längd med pythogoras sats
	//beräknas hypotenusan/längden mellan varje punkt
	public double langd1 () {
		int katetx;
		int katety;
		double langd = 0;
		
		for (int i = 0; i < horn.length -1; i++) {
			katetx = horn[i+1].getX() - horn[i].getX(); //B(x) - A(x) => längd för katet på x-axeln
			katety = horn[i+1].getY() - horn[i].getX();  //B(y) - A(y) => längd för katet på y-axeln
			
			langd += Math.sqrt((Math.pow(katetx, 2)) + (Math.pow(katety, 2)));
		}
		return langd;
	}
	
	//lägger till en punkt på sista plats genom ny vektor som har en plats mer
	public void laggTill1 (Punkt horn){
		Punkt[]    h = new Punkt[this.horn.length + 1];
		int    i = 0;
		
		for (i = 0; i < this.horn.length; i++)
			h[i] = this.horn[i];
		
		h[i] = horn;
		this.horn = h;
	}
	
	public void laggTillFramfor1 (Punkt horn, String hornNamn) {
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
	
	public void taBort1 (String hornNamn) {
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
}

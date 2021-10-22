public class Punkt {
	//class variabler
	private String namn;
	private int x;
	private int y;
	
	public Punkt(String namn, int x, int y) {
		this.namn = namn;
		this.x = x;
		this.y = y;
	}
	
	public Punkt(Punkt p) {
		x = p.x;
		y = p.y;
		namn = p.namn;
	}
	
	public String toString() 
	{	
		return "[" + namn + ", " + x + ", " + y + "]"; //annars blir output reference id: "Punkt@7852e922"
														//pga string i class ger vägen inte värdet
	}

	public String getNamn() {
		
		return namn;
	}
	
	public int getX() {
		
		return x;
	}
	
	public int getY() {

		return y;
	}

	public double avstand(Punkt p2) {
		int katetx = p2.getX() - x; //B(x) - A(x) => längd för katet på x-axeln
		int katety = p2.getY() - y;  //B(y) - A(y) => längd för katet på y-axeln
		
		double avstand = Math.sqrt((Math.pow(katetx, 2)) + (Math.pow(katety, 2))); //pythogoras sats för att beräkna hypotenusan,
																					//dvs avståndet mellan punkterna
		return avstand;
		
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}
}


public class Triangel {
 	
	// tar emot längden på alla tre sidor i en triangel 
	// beräknar vinkeln mellan sida b och c
	public static double angle(double a, double b, double c) {
		double    alfa = Math.acos(((b * b) + (c * c) - (a * a)) / (2 * b * c));
		
		return alfa;
	}
	
	// tar emot längden på två sidor av en triangel och vinkeln mellan dessa (i grader)
	// beräknar bisektrisen som delar vinkeln i hälften
	public static double bisektris (double b, double c, double alfa){
		double    p = 2 * b * c * Math.cos(Math.toRadians(alfa / 2));
		double    bis = p / (b + c);
		
		return bis;
	}
	
	// tar emot längden på alla sidor i en triangel
	// beräknar dess höjd
	public static double hojd(double a, double b, double c) {
		double s = (a + b + c) / 2;
		double hojd = (2 / c) * Math.sqrt(s * (s - a) * (s - b) * (s - c));
		
		return hojd;
	}
	
	// tar emot längden på en sida i en triangel och höjden
	// beräknar area
	public static double area (double c, double h) {
		double area = (c * h) / 2;
		
		return area;
	}
	
	// tar emot längden på alla sidor i en triangel
	// beräknar radien på triangelns omskrivna cirkel
	public static double omskrivenCirkel (double a, double b, double c) {
		double s = (a + b + c) / 2;
		double omskrivenR = (a * b * c) / (4 * Math.sqrt(s * (s - a) * (s - b) * (s - c)));
		
		return omskrivenR;
	}
	
	//tar emot längden på alla sidor i en triangel
	//beräknar radien på triangelns inskrivna cirkel
	public static double inskrivenCirkel (double a, double b, double c) {
		double s = (a + b + c) / 2;
		double inskrivenR = Math.sqrt(((s - a) * (s - b) * (s - c)) / s);
		
		return inskrivenR;
	}
}
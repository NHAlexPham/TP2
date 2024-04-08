package utilitaires;


public class Ligne {

	private Vect2D point1;
	private Vect2D point2;
	
	
	public Ligne(Vect2D point1, Vect2D point2) {

		this.point1 = point1;
		this.point2 = point2;
		
	}
	
	public Ligne(double x1, double y1, double x2, double y2) {
		
		point1 = new Vect2D(x1, y1);
		point2 = new Vect2D(x2, y2);
	}
	
	public Vect2D getPoint1() {
		return point1;
	}
	
	public Vect2D getPoint2() {
		return point2;
	}
	
}

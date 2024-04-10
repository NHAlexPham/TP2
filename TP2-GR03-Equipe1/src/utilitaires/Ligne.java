package utilitaires;

/**
 * Classe Ligne 
 * 
 * Cette classe definie ce qu'est une ligne et comment la dessiner
 * 
 * 
 * Services offerts:
 *  - getPoint1
 *  - getPoint2
 * 
 * @author Dyaa Abou Arida, ETS
 * @version Hiver, 2024
 */

public class Ligne {

	private Vect2D point1; //point initial de la ligne
	private Vect2D point2; //point final de la ligne
	
	
	public Ligne(Vect2D point1, Vect2D point2) {

		this.point1 = point1;
		this.point2 = point2;
		
	}
	
	public Ligne(double x1, double y1, double x2, double y2) {
		
		point1 = new Vect2D(x1, y1);
		point2 = new Vect2D(x2, y2);
	}
	
	
	/*
	 * retourne le premier point de la ligne
	 */
	public Vect2D getPoint1() {
		return point1;
	}
	
	/*
	 * retourne le deuxieme point de la ligne
	 */
	public Vect2D getPoint2() {
		return point2;
	}
	
}

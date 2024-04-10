package vue;

/**
 * Classe du Rover graphique
 * 
 * Cette classe sert a definir et dessiner un rover
 * 
 * Services offerts:
 *  - setPosRover
 *  - setRayonConvertie
 *  - setPosConvertie
 * 
 * @author Dyaa Abou Arida, ETS
 * @version Hiver, 2024
 */

import java.awt.Color;
import java.awt.Graphics;

import modele.rover.Rover;
import utilitaires.Vect2D;

public class RoverGraphique {

	private Color couleur;	
	private double rayon;
	private Vect2D posRover;
	
	public RoverGraphique(Vect2D posRover){
		this.posRover = posRover;
		couleur = Color.BLUE;
		this.rayon = 10;
	}
	
	/*
	 * methode qui set la position du rover
	 * @param posRover, position du rover
	 */
	public void setPosRover(Vect2D posRover) {
		this.posRover = posRover;
	}
	
	/*
	 * methode qui set le rayon convertie du cercle
	 * @param rayon, le rayon deja convertie
	 */
	public void setRayonConvertie(double rayon) {
		this.rayon = rayon;	
	}
	
	/*
	 * methode qui set la position convertie du rover
	 * @param posRoverConvertie, la position du rover deja convertie
	 */
	public void setPosConvertie(Vect2D posRoverConvertie) {
		this.posRover = posRoverConvertie;
	}
	
	/*
	 * methode qui dessine le Rover
	 * @param g, parametre graphique
	 */
	public void dessineRover(Graphics g) {
		
		//si la position du rover n'est pas nulle, on dessine le rover
		if(posRover != null) {
			
			int r = (int) rayon;				
			int xc = (int) posRover.getX();
			int yc = (int) posRover.getY();
		
			g.setColor(couleur);
			g.fillOval( xc-r, yc-r, 2*r, 2*r);
        
        
		}
		
		

        
        
	}
}

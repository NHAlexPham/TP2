package vue;

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
	
	
	public void setPosRover(Vect2D posRover) {
		this.posRover = posRover;
	}
	
	public void setRayonConvertie(double rayon) {
	this.rayon = rayon;	
	}
	
	public void setPosConvertie(Vect2D posRoverConvertie) {
		
		this.posRover = posRoverConvertie;
	}
	
	public void dessineRover(Graphics g) {
		
		if(posRover != null) {
			
			int r = (int) rayon;
			int xc = (int) posRover.getX();
			int yc = (int) posRover.getY();
		
			g.setColor(couleur);
			g.fillOval( xc-r, yc-r, 2*r, 2*r);
        
        
		}
		
		

        
        
	}
}

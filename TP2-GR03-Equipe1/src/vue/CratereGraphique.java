package vue;

import java.awt.Color;
import java.awt.Graphics;

import modele.environnement.Cratere;
import utilitaires.Vect2D;



public class CratereGraphique {

	private Cratere cratere; 
	private Color couleur;
	private Vect2D posConvertie;
	private double rayonConvertie;

	private boolean visible = true;
	

	public CratereGraphique(Cratere cratere) {
		this.cratere = cratere;
		this.couleur = Color.BLACK;
	}
	
	public void setPosConvertie(Vect2D posConvertie) {
		
		this.posConvertie = posConvertie;
	}
	
	public void setRayonConvertie(double rayonConvertie) {
		this.rayonConvertie = rayonConvertie;
	}
	
	
	public void dessine(Graphics g) {
		
		int r = (int) rayonConvertie;
		int xc = (int) posConvertie.getX();
		int yc = (int) posConvertie.getY();
		
        g.setColor(couleur);
        g.fillOval( xc-r, yc-r, 2*r, 2*r);
        
        
		int rGris = (int) (rayonConvertie * 0.90);
        
        g.setColor(Color.GRAY);
        g.fillOval( xc-rGris, yc-rGris, 2*rGris, 2*rGris);
        
        
	}
}

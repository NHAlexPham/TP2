package vue;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

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
		
		int r = (int) rayonConvertie - 2;
		int xc = (int) posConvertie.getX();
		int yc = (int) posConvertie.getY();
		

		
		// Définit l'épaisseur du trait à 3 pixels
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(4));
		
        
        
		int rGris = (int) (rayonConvertie * 0.90);
        
		 // Calcule la différence de rayon entre le cercle foncé et le cercle gris
	    int diffRayon = r - rGris;
	    // Calcule les coordonnées du coin supérieur gauche du cercle gris pour le centrer
	    int xGris = xc - rGris;
	    int yGris = yc - rGris;

	    // Définit une couleur grise semi-transparente
	    Color grisSemiTransparent = new Color(128, 128, 128, 128);
	    
	    

	    // Dessine le cercle gris
	    g.setColor(grisSemiTransparent);
        g.fillOval(xGris, yGris, 2 * rGris, 2 * rGris);
        
        

        g.setColor(couleur);
        g.drawOval( xc-r, yc-r, 2*r, 2*r);
        
        
	}
}

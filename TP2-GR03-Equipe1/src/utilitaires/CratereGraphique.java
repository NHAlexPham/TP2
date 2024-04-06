package utilitaires;

import java.awt.Color;
import java.awt.Graphics;

import modele.environnement.Cratere;



public class CratereGraphique {

	private Cratere cratere; 
	private Color couleur;

	private boolean visible = true;
	

	public CratereGraphique(Cratere cratere) {
		this.cratere = cratere;
		this.couleur = Color.BLACK;
	}
	
	public void dessine(Graphics g) {
		
		int r = (int) cratere.getRayon();
		int xc = (int) cratere.getPosition().getX();
		int yc = (int) cratere.getPosition().getY();
		
        g.setColor(couleur);
        g.drawOval( xc-r, yc-r, 2*r, 2*r);
	}
}

package vue;

/**
 * Classe du cratere graphique
 * 
 * Cette classe sert a decrire comment dessiner un objet cratere
 * 
 * 
 * Services offerts:
 *  - setPosConvertie
 *  - setRayonConvertie
 *  - dessine
 * 
 * @author Dyaa Abou Arida, ETS
 * @version Hiver, 2024
 */

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import modele.environnement.Cratere;
import utilitaires.Vect2D;



public class CratereGraphique {
 
	private Color couleur;			//couleur du cercle
	private Vect2D posConvertie;	//position convertie
	private double rayonConvertie;	//rayon convertie

	private boolean visible = true;	//visibilite des crateres
	

	public CratereGraphique() {
		this.couleur = Color.BLACK;
	}
	
	/*
	 * methode qui permet de set la position convertie
	 * @param posConvertie, la position deja convertie
	 */
	public void setPosConvertie(Vect2D posConvertie) {	
		this.posConvertie = posConvertie;
	}
	
	/*
	 * methode qui pertmet de set le rayon du cratere convertie
	 * @param raynConvertie, le rayon deja convertie
	 */
	public void setRayonConvertie(double rayonConvertie) {
		this.rayonConvertie = rayonConvertie;
	}
	
	/*
	 * methode qui dessine le cratere (appele par le paintComponent)
	 * @param g, parametre graphique
	 */
	public void dessine(Graphics g) {
		
		int r = (int) rayonConvertie - 2;		//rayon du cercle externe
		int xc = (int) (posConvertie.getX() * 1.15);		//position x du cercle externe
		int yc = (int) (posConvertie.getY() * 1.15);		//position y du celrcle externe
		

		
		//definit l'epaisseur du trait à 3 pixels du cercle externe
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(4));
		
        
        
		int rGris = (int) (rayonConvertie * 0.90);	//set le rayon du cercle interne
        
		//calcule la difference de rayon entre le cercle externe et le cercle interne
	    int diffRayon = r - rGris;
	    //calcule les coordonné\ees du coin superieur gauche du cercle gris pour le centrer
	    int xGris = xc - rGris;
	    int yGris = yc - rGris;

	    //definit la couleur grise du cercle interne semi transparent
	    Color grisSemiTransparent = new Color(128, 128, 128, 128);
	    
	    

	    //dessine le cercle gris interne
	    g.setColor(grisSemiTransparent);
        g.fillOval(xGris, yGris, 2 * rGris, 2 * rGris);
        
        
        //dessine le cercle noir externe apres le cercle gris pour quil soit au dessus
        g.setColor(couleur);
        g.drawOval( xc-r, yc-r, 2*r, 2*r);
        
        
	}
}

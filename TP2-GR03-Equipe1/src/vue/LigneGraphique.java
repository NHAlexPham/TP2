package vue;

/**
 * Classe du panneau de la ligne graphique
 * 
 * Cette classe sert a definir la ligne et la dessiner
 * 
 * 
 * Services offerts:
 *  - dessine
 * 
 * @author Dyaa Abou Arida, ETS
 * @version Hiver, 2024
 */

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.BasicStroke;

import utilitaires.Ligne;

public class LigneGraphique {

	private Ligne ligne;
	private Color couleur;
	private TexteGraphique text = new TexteGraphique();	//texte qui identifie la position de la ligne 
	
	public LigneGraphique(Ligne ligne) {
	
		this.ligne = ligne;
		this.couleur = Color.BLACK;
		
	}
	
	/*
	 * methode qui permet de dessiner la ligne
	 * @param g, parametre graphique
	 */
	public void dessine(Graphics g) {
		
		//valeur de x et y du point initial
		int x1 = (int) ligne.getPoint1().getX();
		int y1 = (int) ligne.getPoint1().getY();
		
		//valeur de x et y du point final
		int x2 = (int) ligne.getPoint2().getX();
		int y2 = (int) ligne.getPoint2().getY();
		
		//cree uen copie de l'instance Graphics
        Graphics2D g2d = (Graphics2D) g.create();

        //set la ligne a pointille
        Stroke dashed = new BasicStroke(2, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0);
        g2d.setStroke(dashed);

        //dessine la ligne avec la copie
        g2d.setColor(couleur);
        g2d.drawLine(x1, y1, x2, y2);

        //dispose de la copie
        g2d.dispose();
        
        text.dessinerTexte(g);	//dessine le texte (coordonne)
	}
}

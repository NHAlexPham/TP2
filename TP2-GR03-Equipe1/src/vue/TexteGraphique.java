package vue;

/**
 * Classe du texte graphique
 * 
 * Cette classe sert a definir et dessiner le texte de coordonees du grid dans le panneau
 * 
 * Services offerts:
 *  - dessinerTexte
 * 
 * @author Dyaa Abou Arida, ETS
 * @version Hiver, 2024
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class TexteGraphique {
	
	
	// Position du texte
    private int x = 90;
    private int y = 20;


	public void dessinerTexte(Graphics g) {
	    
	    // Définir la police et la taille des nombres a afficher
	    Font font = new Font("Times New Roman", Font.PLAIN, 25);
	    
	    g.setFont(font);
	    
	    // Couleur des nombres a afficher
	    g.setColor(Color.BLACK);
	    	
	    	
	    for(int i = 0; i < 20; i++) {
	    	
	    	// Dessine le texte honrizontal
	    	g.drawString("" + i * 20, i * x, y);
	    }
	    
	    for(int i = 0; i < 20; i++) {
	    	
	    	// Dessine le texte vertical
	    	g.drawString("" + i * 20, y - 20, (i* (x - 25)) - 8);
	    }
	   
	}

}

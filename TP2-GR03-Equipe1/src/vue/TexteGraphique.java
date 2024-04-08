package vue;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class TexteGraphique {

	
	public void dessinerTexte(Graphics g) {
	    // Position du texte
	    int x = 68;
	    int y = 20;

	    // Définir la police des nombres a afficher
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
	    	g.drawString("" + i * 20, y - 20, i* x);
	    }
	   
	}

}

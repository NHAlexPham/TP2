package vue;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.BasicStroke;

import utilitaires.Ligne;

public class LigneGraphique {

	private Ligne ligne;
	private Color couleur;
	
	public LigneGraphique(Ligne ligne) {
	
		this.ligne = ligne;
		this.couleur = Color.BLACK;
		
	}
	
	
	public void dessine(Graphics g) {
		
		int x1 = (int) ligne.getPoint1().getX();
		int y1 = (int) ligne.getPoint1().getY();
		
		int x2 = (int) ligne.getPoint2().getX();
		int y2 = (int) ligne.getPoint2().getY();
		
		// Create a copy of the Graphics instance
        Graphics2D g2d = (Graphics2D) g.create();

        // Set the stroke of the copy to dashed
        Stroke dashed = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0);
        g2d.setStroke(dashed);

        // Draw the line with the copy
        g2d.setColor(couleur);
        g2d.drawLine(x1, y1, x2, y2);

        // Dispose of the copy
        g2d.dispose();
	}
}

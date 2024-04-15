package vue.Graphiques;

import utilitaires.Vect2D;

import java.awt.*;

public class CratereGraphique {
    private double rayon;
    private Vect2D position;
    private Color color;

    public CratereGraphique(Vect2D position, double rayon, Color color){
        this.position = position;
        this.rayon = rayon*2.5;
        this.color = color;
    }

    public void dessine(Graphics g) {
        int positionX = (int) ((int) position.getX() - rayon);
        int positionY = (int) ((int) position.getY() - rayon);
        rayon *=3;
        g.fillOval(positionX,positionY , (int) rayon, (int) rayon);
        g.setColor(color);
    }
}

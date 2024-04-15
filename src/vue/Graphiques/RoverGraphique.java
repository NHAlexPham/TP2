package vue.Graphiques;

import utilitaires.Vect2D;

import java.awt.*;

public class RoverGraphique {
    Vect2D positionRover;
    int rayon;

    public RoverGraphique(Vect2D positionRover){
        this.positionRover = positionRover;
        this.rayon = 2; // toujours 2 de rayon
    }

    public void updatePositionRover(Vect2D vect2D){
        this.positionRover = positionRover;
    }

    public void dessine(Graphics g) {
        rayon *= 6; // plus gros sinon non visible a l'ecran
        g.setColor(Color.BLUE);
        g.fillOval((int) positionRover.getX(), (int) positionRover.getY(), (int) rayon, (int) rayon);
    }
}

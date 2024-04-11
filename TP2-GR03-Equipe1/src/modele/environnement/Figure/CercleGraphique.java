package modele.environnement.Figure;

import java.awt.*;

public class CercleGraphique {
    private Cercle cercle ;
    private Color couleur ;

    private boolean visible = true;

    public CercleGraphique(Cercle cercle, Color couleur) {
        this.cercle = cercle;
        this.couleur = couleur;
    }
    public CercleGraphique(Cercle cercle) {
        this(cercle , Color.blue ) ;
    }

    public void dessine(Graphics g) {
        int r = (int)cercle.getRayon(), xc = (int)cercle.getCentre().getAbscisse(),
                yc = (int)cercle.getCentre().getOrdonnee();

        g.setColor(couleur);
        g.drawOval( xc-r, yc-r, 2*r, 2*r);
    }
}

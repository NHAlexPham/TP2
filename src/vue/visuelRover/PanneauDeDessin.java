package vue.visuelRover;

import controleur.MonObserver.Observable;
import controleur.MonObserver.Observateur;
import modele.centreOperation.CentreOperation;
import modele.environnement.Cratere;
import vue.Graphiques.CratereGraphique;
import modele.environnement.Lune;
import utilitaires.Vect2D;
import vue.Graphiques.RoverGraphique;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PanneauDeDessin extends JPanel implements Observateur {

    ArrayList<Cratere> crateres;
    Lune lune;
    VisuelRover visuel;
    Vect2D positionRover;
    Graphics graphics;
    CentreOperation centreOperation;

    public PanneauDeDessin(VisuelRover visuel){
        this.visuel = visuel;
        lune = Lune.getInstance();
        this.crateres = lune.getCrateres();
        setBackground(Color.WHITE);
        //this.positionRover = CentreOperation.getInstance().getPositionRover();
    }

    /**
     * Méthode appelée pour faire l'affichage graphique (le dessin) du paneau
     *
     */
    @Override
    public void paintComponent(Graphics g) {
        this.graphics = g;
        centreOperation = CentreOperation.getInstance();
        centreOperation.ajouterObservateur(this);
        //Appel de paint() de la classe mère, nécessaire pour dessiner le panneau.
        super.paintComponent(g);
        //gCercle.dessine(g);
        for (int i = 0; i <= this.getWidth(); i+=(this.getWidth())/10) {
            drawDashedLine(g,i,0,i,(int) visuel.convertirLongueurToPixel(this.getHeight()));
        }
        for (int i = 0; i < this.getHeight(); i+=this.getHeight()/10) {
            drawDashedLine(g,0,i,(int) visuel.convertirLongueurToPixel(this.getWidth()),i);
        }

        dessinerLabel(g);
        dessinerCratere(g);
        dessinerRover(g);
    }

    private void dessinerLabel(Graphics g) {
        int counter = 0;
        for (int i = 0; i <= this.getWidth(); i+=getHeight()/10) {
            g.setColor(Color.BLACK);
            g.drawString(""+counter, 0, i);counter+=20;
        }
        int counter2=0;
        for (int i = 0; i <= this.getWidth(); i+=getWidth()/10) {
            g.setColor(Color.BLACK);
            g.drawString(""+counter2, i, 10);
            counter2+=20;
        }
    }

    private void dessinerRover(Graphics g) {
        Vect2D vect2D = centreOperation.getPositionRover();
        if(centreOperation.getPositionRover() != null){
            if(vect2D != null){
                vect2D = visuel.convertirPositionToPixel(vect2D);
                RoverGraphique roverGraphique = new RoverGraphique(vect2D);
                roverGraphique.dessine(g);
            }
        }

    }

    public void dessinerCratere(Graphics g){
        Lune lune = Lune.getInstance();
        ArrayList<Cratere> crateres = lune.getCrateres();
        for (Cratere cratere : crateres){
            Vect2D vect2D = visuel.convertirPositionToPixel(cratere.getPosition());

            CratereGraphique centre = new CratereGraphique(vect2D,
                    cratere.getRayon(),
                    Color.LIGHT_GRAY);
            centre.dessine(g);

            CratereGraphique contour = new CratereGraphique(vect2D,(cratere.getRayon()*0.9),Color.BLACK);
            contour.dessine(g);

        }
    }

    public void drawDashedLine(Graphics g, int x1, int y1, int x2, int y2){

        // Create a copy of the Graphics instance
        Graphics2D g2d = (Graphics2D) g.create();

        // Set the stroke of the copy, not the original
        Stroke dashed = new BasicStroke(2, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL,
                0, new float[]{9}, 0);
        g2d.setStroke(dashed);

        // Draw to the copy
        g2d.drawLine(x1, y1, x2, y2);

        // Get rid of the copy
        g2d.dispose();
    }

    @Override
    public void seMettreAJour(Observable observable) {
        if (observable instanceof CentreOperation){
            CentreOperation centre = (CentreOperation) observable;

            // Position inchanger...
            // Conflit avec Photo...
            if(centre.getPositionRover() != null){
                this.positionRover = centre.getPositionRover();
                dessinerRover(graphics);
            }
        }
        repaint();
    }
}

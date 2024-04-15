package vue.visuelRover;

import modele.environnement.Lune;
import utilitaires.Vect2D;

import javax.swing.*;
import java.awt.*;

public class VisuelRover extends JPanel {

    public VisuelRover(){
        //this.setBackground(Color.lightGray);
        initialiserComposants();
    }

    private void initialiserComposants() {
        this.setLayout(new BorderLayout());
        Lune lune = Lune.getInstance();
        setPreferredSize(new Dimension(900, 800));
        PanneauDeDessin panneau = new PanneauDeDessin(this);
        //setBackground(Color.lightGray);
        add(panneau);
    }


    public Vect2D convertirPositionToPixel(Vect2D vect2D){
        double Xpixel = vect2D.getX() * this.getWidth()/Lune.getInstance().getDimSite().getX();
        double Ypixel = vect2D.getY() * this.getHeight()/Lune.getInstance().getDimSite().getY();
        Vect2D vect = new Vect2D(Xpixel,Ypixel);
        return vect;
    }

    public double convertirLongueurToPixel(double lunaire){
        double longueurPixel;
        Lune lune = Lune.getInstance();
        longueurPixel = lunaire * (((this.getWidth()/lune.getDimSite().getX())+
                (this.getHeight()/lune.getDimSite().getY()))/2);
        return longueurPixel;
    }


}

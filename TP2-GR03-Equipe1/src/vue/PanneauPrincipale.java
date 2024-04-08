package vue;

import controleur.MonObserver.Observable;
import controleur.MonObserver.Observateur;
import vue.centreOperation.Console;

import javax.swing.*;
import java.awt.*;

public class PanneauPrincipale extends JPanel {

    Console console;

    public PanneauPrincipale(){
        initialiserComposants();
    }

    private void initialiserComposants() {
        //this.setBackground(Color.RED);
        console = new Console();
        VisuelRover visuelRover = new VisuelRover();

        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        GridBagConstraints d = new GridBagConstraints();

        // Visuel Rover 75% de la vue
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 0.75;
        c.weighty = 1;


        // Console 25% de la vue
        d.fill = GridBagConstraints.BOTH;
        d.weightx = 0.25;
        d.weighty = 1;
        this.add(visuelRover, c);
        this.add(console, d);
    }

}

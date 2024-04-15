package vue;

import vue.centreOperation.Console;
import vue.visuelRover.VisuelRover;

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
//        c.fill = GridBagConstraints.BOTH;
//        c.weightx = 0.75;
//        c.weighty = 1;
        c.gridx = 0;
        c.gridy = 0;
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.weighty = 1;
        this.add(visuelRover, c);

        // Console 25% de la vue
//        d.fill = GridBagConstraints.BOTH;
//        d.weightx = 0.25;
//        d.weighty = 1;
        c.gridx = 1;
        c.gridheight = 2;
        c.weighty = 1;
        c.weightx = 0;
        c.fill = GridBagConstraints.BOTH;
        this.add(console, c);
    }

}

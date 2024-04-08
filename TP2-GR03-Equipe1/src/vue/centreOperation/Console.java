package vue.centreOperation;

import controleur.MonObserver.Observable;
import controleur.MonObserver.Observateur;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class Console extends JPanel {

    CmdDeplacement cmdDeplacement;

    public Console(){
        initialiserComposants();
    }

    private void initialiserComposants() {
        cmdDeplacement = new CmdDeplacement();
        GestionPhotos gestionPhotos = new GestionPhotos();
        cmdDeplacement.setBackground(Color.DARK_GRAY);
        this.setLayout(new GridLayout(2,1));
        this.add(cmdDeplacement);
        this.add(gestionPhotos);
    }

}

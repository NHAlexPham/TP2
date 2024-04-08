package vue.centreOperation;

import controleur.ControleurPhoto;

import javax.swing.*;
import java.awt.*;

public class GestionPhotos extends JPanel {

    JButton btnPhoto;
    JProgressBar progressBar;
    JList listPhotos;

    public GestionPhotos(){
        initialiserComposants();
    }

    private void initialiserComposants() {
        Initial();
        JPanel main = new JPanel(new BorderLayout(10,10));
        setBackground(Color.DARK_GRAY);
        JPanel panel = new JPanel(new GridLayout(2, 1,10,10));
        panel.add(btnPhoto);
        panel.add(progressBar);
        int eb=10;
        panel.setBorder(BorderFactory.createEmptyBorder(eb, eb, eb, eb));
        JPanel panel2 = new JPanel();
        panel2.add(listPhotos);
        main.add(panel,BorderLayout.NORTH);
        main.add(panel2,BorderLayout.CENTER);

        ControleurPhoto ecouteurPhoto = new ControleurPhoto(btnPhoto,progressBar);

        add(main);
    }

    private void Initial() {
        this.btnPhoto = new JButton("Prendre Photo");
        this.progressBar = new JProgressBar();
        this.listPhotos = new JList();
    }
}

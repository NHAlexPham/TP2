package vue.centreOperation;

import controleur.ControleurPhoto;
import controleur.MonObserver.Observable;
import controleur.MonObserver.Observateur;
import modele.centreOperation.CentreOperation;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class GestionPhotos extends JPanel implements Observateur {

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

        CentreOperation centreOp = CentreOperation.getInstance();
        centreOp.ajouterObservateur(this);

        ControleurPhoto ecouteurPhoto = new ControleurPhoto(btnPhoto,progressBar,listPhotos);
        btnPhoto.addActionListener(ecouteurPhoto);

        add(main);
    }

    private void Initial() {
        this.btnPhoto = new JButton("Prendre Photo");
        this.progressBar = new JProgressBar();
        this.progressBar.setStringPainted(true);
        this.listPhotos = new JList();
        peuplerList();
    }

    private void peuplerList() {
        File folder = new File("photos/");
        File[] listOfFiles = folder.listFiles();

        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                System.out.println("File " + listOfFiles[i].getName());
            } else if (listOfFiles[i].isDirectory()) {
                System.out.println("Directory " + listOfFiles[i].getName());
            }
        }
        this.listPhotos.setListData(listOfFiles);
        updateUI();
    }

    @Override
    public void seMettreAJour(Observable observable) {
        if (observable instanceof CentreOperation){
            CentreOperation centre = (CentreOperation)observable;
            int num = (int) (centre.getProgresFichier()*100);
            //System.out.println("NUM: " + num);
            this.progressBar.setValue(num);
            //int counter = centre.getCompteurPhoto();
            //System.out.println("Count: "+centre.getCompteurPhoto());
            peuplerList();

        }

    }
}

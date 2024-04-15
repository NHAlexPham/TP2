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
        setBackground(Color.DARK_GRAY);
        JPanel main = new JPanel(new BorderLayout(0,5));
        main.setBackground(Color.DARK_GRAY);
        JPanel buttonProgress = new JPanel();
        buttonProgress.setBackground(Color.DARK_GRAY);
        buttonProgress.setPreferredSize(new Dimension(200,60));
        btnPhoto.setFocusable(false);



        JPanel jPanel = new JPanel(new GridLayout(2,1,0,5));
        jPanel.setBackground(Color.DARK_GRAY);

        jPanel.add(btnPhoto);
        jPanel.add(progressBar);
        buttonProgress.add(jPanel);

        //liste.add(listPhotos);

        main.add(buttonProgress,BorderLayout.NORTH);
        main.add(listPhotos,BorderLayout.CENTER);

        add(main);
        this.setBorder(BorderFactory.createLineBorder(Color.WHITE));

        //
        CentreOperation centreOp = CentreOperation.getInstance();
        centreOp.ajouterObservateur(this);
        ControleurPhoto ecouteurPhoto = new ControleurPhoto(btnPhoto,progressBar,listPhotos);
        btnPhoto.addActionListener(ecouteurPhoto);
    }

    private void Initial() {
        this.btnPhoto = new JButton("Prendre Photo");
        this.progressBar = new JProgressBar();
        this.progressBar.setStringPainted(true);
        this.listPhotos = new JList();
        this.listPhotos.setPreferredSize(new Dimension(250,200));
        peuplerList();
    }

    private void peuplerList() {
        File folder = new File("photos/");
        File[] listOfFiles = folder.listFiles();
        this.listPhotos.setListData(listOfFiles);
        updateUI();
    }

    @Override
    public void seMettreAJour(Observable observable) {
        if (observable instanceof CentreOperation){
            if (((CentreOperation) observable).getProgresFichier() != 0){
                CentreOperation centre = (CentreOperation)observable;
                int num = (int) (centre.getProgresFichier()*100);
                this.progressBar.setValue(num);
                peuplerList();
            }

        }

    }
}

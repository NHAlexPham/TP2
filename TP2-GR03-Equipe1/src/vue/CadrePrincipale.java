package vue;

import controleur.MonObserver.Observable;
import controleur.MonObserver.Observateur;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class CadrePrincipale extends JFrame{

    private JPanel panneauPrincipale = new PanneauPrincipale();

    public CadrePrincipale(String title){
        super(title);
        configurerFenetre();
        initialiserComposants();
    }

    private void configurerFenetre() {
        this.setSize(1000,500);
        this.setLocation(100,100);
        //this.setResizable(false);
        JFrame frame = this;
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                int result = JOptionPane.showConfirmDialog(frame,
                        "Voulez-vous quitter ?", "Confirmation :",
                        JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION)
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                else if (result == JOptionPane.NO_OPTION)
                    frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            }
        });
    }

    private void initialiserComposants() {
        PanneauPrincipale panneauPrincipale = new PanneauPrincipale();
        this.setContentPane(panneauPrincipale);
    }
}

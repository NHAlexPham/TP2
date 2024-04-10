package vue;

/**
 * Classe du Panneau Principal
 * 
 * Cette classe sert a creer le panneau principal ou se retoruve tout les autres panneaux
 * 
 * 
 * @author Dyaa Abou Arida, ETS
 * @version Hiver, 2024
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.JPanel;

public class PanneauPrincipale extends JPanel {

    private JPanel console = new Console();
    private JPanel visuelRover = new VisuelRover();

    public PanneauPrincipale() {
    	
        this.setLayout(new BorderLayout());				//set le layout a BorderLayout
        this.add(console, BorderLayout.EAST);			//ajoute la console au panneau
        this.add(visuelRover, BorderLayout.CENTER);		//ajoute le visuel rover au panneau

        //calcul la largeur de la fenetre pour garder la console a 25% de la fenetre et le visuel a 75%
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                int largeurFenetre = getWidth();
                int hauteurFenetre = getHeight();

                int largeurConsole = (int) (largeurFenetre * 0.25);
                int hauteurConsole = hauteurFenetre;

                console.setPreferredSize(new Dimension(largeurConsole, hauteurConsole));
                console.revalidate(); // Indiquer au gestionnaire de disposition de recalculer la taille
            }
        });
    }


}
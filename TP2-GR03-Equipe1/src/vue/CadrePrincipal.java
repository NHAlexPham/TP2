package vue;

/**
 * Classe du Cadre Principal
 * 
 * Cette classe sert a creer la fenetre du cadre principal
 * 
 * 
 * @author Dyaa Abou Arida, ETS
 * @version Hiver, 2024
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import utilitaires.Observable;
import utilitaires.Observateur;

public class CadrePrincipal extends JFrame{

	//creation d'un panneau principal
	private JPanel panneauPrincipale = new PanneauPrincipale();
	
	
	public CadrePrincipal(){
		
		this.add(panneauPrincipale);									//ajoute le panneau principal au cadre principal
		
		this.setTitle("Satellite");										//on affichge le nom la fenetre Satellite
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);		//termine le programme en meme temps quon ferme la fenetre
		this.setSize(1000, 700);										//set la taille de la fenetre
		this.setVisible(true);											//permet a la fenetre d'etre visible
		
		//un ecouteur su la fenetre qui permet d'ouvrir un option Dialog pour confirmer de quitter
		this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent evt) {
                Object[] options = { "Quitter", "Annuler" };	//definit les option dans la fenetre de dialog

                int answer = JOptionPane.showOptionDialog(CadrePrincipal.this, "Voulez-vous quitter? ", "Quitter",
                        JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[1]);
                if (answer == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });
	}
	

	
}

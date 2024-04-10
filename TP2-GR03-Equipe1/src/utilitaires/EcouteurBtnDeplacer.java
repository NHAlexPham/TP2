package utilitaires;

/**
 * Classe qui ecoute le bouton deplacer
 * 
 * Cette classe sert a faire une action lorsque le bouton deplacer rover est clique
 * 
 * 
 * Services offerts:
 *  - actionPerformed
 * 
 * @author Dyaa Abou Arida, ETS
 * @version Hiver, 2024
 */

import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JTextField;

import modele.centreOperation.CentreOperation;

public class EcouteurBtnDeplacer implements ActionListener{

	 private JTextField posCibleX;
	 private JTextField posCibleY;
	 
	 private CentreOperation centreOp;	
	 
	 private int posX;
	 private int posY;

	 public EcouteurBtnDeplacer(JTextField posCibleX, JTextField posCibleY, CentreOperation centreOp) {
		 
	     this.posCibleX = posCibleX;
	     this.posCibleY = posCibleY;
	     
	     this.centreOp = centreOp;
	 }
	
	 /*
	  * methode qui s'execute quand le bouton deplacerRover est active
	  */
	@Override
	public void actionPerformed(ActionEvent e) {
	
		 try {
			 	//on recupere les donnees des textfields
		        posX = Integer.parseInt(posCibleX.getText());
		        posY = Integer.parseInt(posCibleY.getText());
		        
				//appel de la methode deplacerRover du centre de controle pour envoyer un message de deplacement
	            centreOp.deplacerRover(posX, posY);

		    } catch (NumberFormatException ex) {
		    	//si jamais les nombres sont invalides ou si les champs sont vides, print un message d'erreur
		        System.err.println("Entrez des nombres valides!");
		    }
		
	}

}

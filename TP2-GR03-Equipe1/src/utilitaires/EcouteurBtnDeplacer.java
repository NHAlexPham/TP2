package utilitaires;

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
	
	@Override
	public void actionPerformed(ActionEvent e) {

		//juste pour tester les valeurs de sortie
		System.out.println(posCibleX.getText());
		System.out.println(posCibleY.getText());
	
		 try {
		        posX = Integer.parseInt(posCibleX.getText());
		        posY = Integer.parseInt(posCibleY.getText());
		        
				//trouver une facons dappeler la methode deplacer rover dans le centre doperation
	            centreOp.deplacerRover(posX, posY);

		    } catch (NumberFormatException ex) {
		        System.err.println("Entrez des nombres valides!");
		    }
		
	}

}

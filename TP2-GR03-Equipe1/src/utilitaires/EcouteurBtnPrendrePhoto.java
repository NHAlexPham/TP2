package utilitaires;

/**
 * Classe qui ecoute le bouton prendre photo
 * 
 * Cette classe sert a faire une action lorsque le bouton prendre photo est clique
 * 
 * 
 * Services offerts:
 *  - actionPerformed
 * 
 * @author Dyaa Abou Arida, ETS
 * @version Hiver, 2024
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import modele.centreOperation.CentreOperation;

public class EcouteurBtnPrendrePhoto implements ActionListener{

	private CentreOperation centreOp;
	
	public EcouteurBtnPrendrePhoto(CentreOperation centreOp) {
		this.centreOp = centreOp;
	}
	
	
	/*
	 * methode qui s'effectie quand le bouton prendre photo est active  
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

		//appeler la methode prendre photo dans le centre de controle
		centreOp.prendrePhoto();
		
	}

}

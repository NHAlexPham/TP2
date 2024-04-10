package vue;

/**
 * Classe du panneau de la console
 * 
 * Cette classe sert a creer un panneau de console ou se trouve le panneau pour controler le rover et prendre des photos
 * 
 * @author Dyaa Abou Arida, ETS
 * @version Hiver, 2024
 */

import java.awt.GridLayout;

import javax.swing.JPanel;

import utilitaires.Observable;
import utilitaires.Observateur;

public class Console extends JPanel{
	
	//creation des panneaux pour ajouter dans la console
	private JPanel cmdDeplacement = new CmdDeplacement();
	private JPanel gestionPhotos = new GestionPhotos();
	
	public Console() {
		
		this.setLayout(new GridLayout(2, 1));	//set le layout a un BorderLayout
		
		this.add(cmdDeplacement);				//ajoute le panneau de cmdDeplacement 
		this.add(gestionPhotos);				//ajoute le panneau de gestionPhotos 
	}
	

}

package vue;

/**
 * Classe du panneau de commande de deplacement
 * 
 * Cette classe sert a creer un panneau de commande de deplacement et interagir avec le centre de controle pour deplacer le rover
 * 
 * 
 * Services offerts:
 *  - seMettreAJour
 * 
 * @author Dyaa Abou Arida, ETS
 * @version Hiver, 2024
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import modele.centreOperation.CentreOperation;
import utilitaires.EcouteurBtnDeplacer;
import utilitaires.Observable;
import utilitaires.Observateur;

public class CmdDeplacement extends JPanel implements Observateur{
	
	private CentreOperation centreOp = CentreOperation.getInstance();
	
	private JPanel posCible = new JPanel(new GridLayout(3, 1, 0, 3));
	
	private JPanel panPosCourante = new JPanel();
	private JPanel panPosCible = new JPanel();
	
	
	private PosCourante posCourante = new PosCourante();
	private JPanel posCibleX = new PosCible("Pos cible X: ");
	private JPanel posCibleY = new PosCible("Pos cible Y: ");
	
	private JButton deplacerRover = new JButton("Deplacer Rover");
	
	private EcouteurBtnDeplacer ect = new EcouteurBtnDeplacer(((PosCible) posCibleX).getValeur(), ((PosCible) posCibleY).getValeur(), centreOp);
	
	
	
	public CmdDeplacement() {
		
		centreOp.ajouterObservateur((Observateur)this);				//ajouter ce panneau a la liste d'observateur du centre Op
		
		this.setLayout(new BorderLayout());							//set le Layout de ce panneau a BorderLayout
		
		posCible.setBackground(Color.DARK_GRAY);					//set le background a gris fonce du posCible
		posCible.setPreferredSize(new Dimension(200, 100));			//set la size du panneau de position cible
		
		panPosCourante.add(posCourante);							//ajoute le panneau de posCourante au panneauPosCourante
		
		this.add(panPosCourante, BorderLayout.NORTH);	//ajoute la position courante au panneau de commande de deplacement
		posCible.add(posCibleX);						//ajoute la position cible X au panneau de posCible			
		posCible.add(posCibleY);						//ajoute la position cible Y au panneau de posCible	
		posCible.add(deplacerRover);					//ajoute le bouton deplacerrover au panneau de posCible
		
		panPosCible.add(posCible);						//ajoute le posCible au panneau du posCible
		
		panPosCourante.setBackground(Color.DARK_GRAY);	//set le background a gris fonce du panneau posCourante
		panPosCible.setBackground(Color.DARK_GRAY);		//set le background a gris fonce du panneau posCible
		
		this.add(panPosCible, BorderLayout.CENTER);		//ajoute le panneau de grid au panneau de commande de deplacement 
		this.setBackground(Color.DARK_GRAY);			//set le background a gris fonce
		this.setBorder(BorderFactory.createLineBorder(Color.WHITE));	//set la bordure a blanc
		
		deplacerRover.addActionListener(ect);			//ajoute l'ecouteir du bouton au bouton deplacerRover
		
		
	
	}
	
	/*
	 * methode qui permet de se mettre a jour quand l'observable change
	 * @param, observable, l'objet qu'on observe
	 */
	@Override
	public void seMettreAJour(Observable observable) {

		if(observable instanceof CentreOperation) {
			
			CentreOperation centreOp = (CentreOperation) observable;	//recuperer le centre d'operation
			
			
			double posX = centreOp.getPositionRover().getX();			//get la postion x du rover a traver le centre Op
			double posY = centreOp.getPositionRover().getY();			//get la postion y du rover a traver le centre Op
			
			
			//garder 2 chiffres apres la virgule
			posX = Math.round(posX * 100.0) / 100.0;
			posY = Math.round(posY * 100.0) / 100.0;
			
			//update la posCourante (afficher la bonne positions dans les labels)
			posCourante.updatePos(posX, posY);
			

		}
	}
	
	/*
	 * Classe interne qui represente le panneau de la position courante
	 */
	private class PosCourante extends JPanel{
		
		//creation des 2 labels pour x et y
		private JLabel posCourX = new JLabel("Pos courante X: xxx");
		private JLabel posCourY = new JLabel("Pos courante Y: yyy");
		
		public PosCourante() {
			
			this.setLayout(new FlowLayout(FlowLayout.LEFT));	//aligne les labels a gauche dans un FlowLayout
			this.setPreferredSize(new Dimension(150, 45));		//set la dimension de posCourante
			
			this.add(posCourX);			//ajoute le label de X
			this.add(posCourY);			//ajoute le label de Y
			
		}
		
		/*
		 * methode qui set les positions de la pos courante x et y dans les labels
		 * @param posX, posY, les positions x et y du rover
		 */
		public void updatePos(double posX, double posY) {
			
			posCourX.setText("Pos courante X: " + posX);	//set le text pour la position x
			posCourY.setText("Pos courante Y: " + posY);	//set le text pour la position y
		}
		

	}
	
	/*
	 * Classe interne qui represente le panneau de la position cible 
	 */
	private class PosCible extends JPanel{
		
		//creation du label et du texfield qui definit la posCible
		private JLabel posCible;
		private JTextField valeur = new JTextField();
		
		public PosCible(String text) {
			
			posCible  = new JLabel(text);						//set le text du label 
			valeur.setPreferredSize(new Dimension(70, 25));		//set la dimension du texfield
			
			this.add(posCible);		//ajoute le label au panneau
			this.add(valeur);		//ajoute le textfield au panneau
		}
	
		
		/*
		 * methode qui retourne le textfield
		 */
		public JTextField getValeur() {
			return valeur;
		}
		
		
	}


}

















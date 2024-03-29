package vue;

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
import utilitaires.Observable;
import utilitaires.Observateur;

public class CmdDeplacement extends JPanel implements Observateur{
	
	JPanel posCible = new JPanel(new GridLayout(3, 1, 0, 3));
	
	JPanel posCourante = new PosCourante();
	JPanel posCibleX = new PosCible("Pos cible X: ");
	JPanel posCibleY = new PosCible("Pos cible Y: ");
	
	JButton deplacerRover = new JButton("Deplacer Rover");
	
	
	public CmdDeplacement() {
		
		posCible.setBackground(Color.DARK_GRAY);		//set le background a gris fonce
		posCible.setPreferredSize(new Dimension(200, 100));
		
		
		this.add(posCourante);							//ajoute la position courante au panneau de commande de deplacement
		posCible.add(posCibleX);						//ajoute la position cible X au panneau de grid				
		posCible.add(posCibleY);						//ajoute la position cible Y au panneau de grid	
		posCible.add(deplacerRover);					//ajoute le bouton deplacerrover au panneau de grid
		
		this.add(posCible, BorderLayout.CENTER);		//ajoute le panneau de grid au panneau de commande de deplacement 
		
		this.setBackground(Color.DARK_GRAY);			//set le background a gris fonce
		this.setBorder(BorderFactory.createLineBorder(Color.WHITE));	//set la bordure a blanc
		
		
		
	
	}
	
	@Override
	public void seMettreAJour(Observable observable) {

		if(observable instanceof CentreOperation) {
			
			((PosCourante) posCourante).getPosCourX().setText("Pos courante X: " + ((CentreOperation) observable).getPositionRover().getX());
			((PosCourante) posCourante).getPosCourY().setText("Pos courante X: " + ((CentreOperation) observable).getPositionRover().getY());
			
			System.out.println("position modifie");
		}
		
		
	}
	
	
	class PosCourante extends JPanel{
			
		JPanel posCour = new JPanel();
		
		JLabel posCourX = new JLabel("Pos courante X: xxx");
		JLabel posCourY = new JLabel("Pos courante Y: yyy");
		
		public PosCourante() {
			
			posCour.setPreferredSize(new Dimension(130, 45));
			
			posCour.add(posCourX);
			posCour.add(posCourY);
			
			this.add(posCour);
			
		}
		
		public JLabel getPosCourX() {
			return posCourX;
		}
		
		public JLabel getPosCourY() {
			return posCourY;
		}

		
	}
	
	class PosCible extends JPanel{
		
		public PosCible(String text) {
			
			JLabel posCible = new JLabel(text);
			JTextField valeur = new JTextField();
			
			valeur.setPreferredSize(new Dimension(70, 25));
			
			this.add(posCible);
			this.add(valeur);
		}
		
	}


}


















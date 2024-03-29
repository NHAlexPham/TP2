package vue;

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

public class CmdDeplacement extends JPanel{
	
	public CmdDeplacement() {
		
		JPanel posCible = new JPanel(new GridLayout(3, 1, 0, 2));
		
		JPanel posCourante = new PosCourante();
		JPanel posCibleX = new PosCible("Pos cible X: ");
		JPanel posCibleY = new PosCible("Pos cible Y: ");
		
		JButton deplacerRover = new JButton("Deplacer Rover");
		
		posCible.setBackground(Color.DARK_GRAY);		//set le background a gris fonce
		
		
		this.add(posCourante);							//ajoute la position courante au panneau de commande de deplacement
		posCible.add(posCibleX);						//ajoute la position cible X au panneau de grid				
		posCible.add(posCibleY);						//ajoute la position cible Y au panneau de grid	
		posCible.add(deplacerRover);					//ajoute le bouton deplacerrover au panneau de grid
		
		this.add(posCible);								//ajoute le panneau de grid au panneau de commande de deplacement 
		
		this.setBackground(Color.DARK_GRAY);			//set le background a gris fonce
		this.setBorder(BorderFactory.createLineBorder(Color.WHITE));	//set la bordure a blanc
		
		
		
	
	}
	
	class PosCourante extends JPanel{
			
		public PosCourante() {
			
			JPanel posCour = new JPanel();
			
			JLabel posCourX = new JLabel("Pos courante X: xxx");
			JLabel posCourY = new JLabel("Pos courante Y: yyy");
			
			posCour.setPreferredSize(new Dimension(130, 45));
			
			posCour.add(posCourX);
			posCour.add(posCourY);
			
			this.add(posCour);
			
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


















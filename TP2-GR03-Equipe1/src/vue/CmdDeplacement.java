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
		
		centreOp.ajouterObservateur((Observateur)this);
		
		this.setLayout(new BorderLayout());
		
		posCible.setBackground(Color.DARK_GRAY);		//set le background a gris fonce
		posCible.setPreferredSize(new Dimension(200, 100));
		
		panPosCourante.add(posCourante);
		
		this.add(panPosCourante, BorderLayout.NORTH);							//ajoute la position courante au panneau de commande de deplacement
		posCible.add(posCibleX);						//ajoute la position cible X au panneau de grid				
		posCible.add(posCibleY);						//ajoute la position cible Y au panneau de grid	
		posCible.add(deplacerRover);					//ajoute le bouton deplacerrover au panneau de grid
		
		panPosCible.add(posCible);
		
		panPosCourante.setBackground(Color.DARK_GRAY);
		panPosCible.setBackground(Color.DARK_GRAY);
		
		this.add(panPosCible, BorderLayout.CENTER);		//ajoute le panneau de grid au panneau de commande de deplacement 
		this.setBackground(Color.DARK_GRAY);			//set le background a gris fonce
		this.setBorder(BorderFactory.createLineBorder(Color.WHITE));	//set la bordure a blanc
		
		deplacerRover.addActionListener(ect);
		
		
	
	}
	
	
	@Override
	public void seMettreAJour(Observable observable) {

		if(observable instanceof CentreOperation) {
			
			CentreOperation centreOp = (CentreOperation) observable;
			
			
			double posX = centreOp.getPositionRover().getX();
			double posY = centreOp.getPositionRover().getY();
			
			
			//garder 2 chiffres apres la virgule
			posX = Math.round(posX * 100.0) / 100.0;
			posY = Math.round(posY * 100.0) / 100.0;
			
			
			
			posCourante.updatePos(posX, posY);
			

		}
	}
	
	
	class PosCourante extends JPanel{
			
		JLabel posCourX = new JLabel("Pos courante X: xxx");
		JLabel posCourY = new JLabel("Pos courante Y: yyy");
		
		public PosCourante() {
			
			this.setLayout(new FlowLayout(FlowLayout.LEFT));
			this.setPreferredSize(new Dimension(150, 45));
			
			this.add(posCourX);
			this.add(posCourY);
			
		}
		
		public void updatePos(double posX, double posY) {
			
			posCourX.setText("Pos courante X: " + posX);
			posCourY.setText("Pos courante Y: " + posY);
		}
		

	}
	
	class PosCible extends JPanel{
		
		
		JLabel posCible;
		JTextField valeur = new JTextField();
		
		public PosCible(String text) {
			
			posCible  = new JLabel(text);
			valeur.setPreferredSize(new Dimension(70, 25));
			
			this.add(posCible);
			this.add(valeur);
		}
	
		public JTextField getValeur() {
			
			return valeur;
		}
		
		
	}


}

















package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

public class GestionPhotos extends JPanel{

	public GestionPhotos() {
		
		
		JPanel gestion = new JPanel(new BorderLayout());
		JPanel nord = new JPanel(new GridLayout(2, 1, 2, 2));
		
		String[] test = {"test1", "test2", "test3"};
		
		JButton prendrePhoto = new JButton("Prendre Photo");
		JProgressBar progres = new JProgressBar();
		JList liste = new JList(test);
		 
		
		//ajoute le bouton et la barre de progres dans le panneau nord
		nord.add(prendrePhoto);
		nord.add(progres);
		
		
		gestion.add(nord, BorderLayout.NORTH);		//ajoute le panneau nord au nord du panneau gestion
		gestion.add(liste, BorderLayout.CENTER);	//ajoute la list au centre du panneau gestion
		 
		gestion.setBackground(Color.DARK_GRAY);		//set la couleur des background a gris fonce
		nord.setBackground(Color.DARK_GRAY);
		
		this.add(gestion, BorderLayout.CENTER);		//ajoute le panneau de gestion au panneau de GestionPhotos
		
		this.setBackground(Color.DARK_GRAY);		//set la couleur des background a gris fonce
		
		this.setBorder(BorderFactory.createLineBorder(Color.WHITE));	//set la couleur de la bordure a blanc
		
	}
	
	
	
}

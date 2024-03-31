package vue;

import java.awt.GridLayout;

import javax.swing.JPanel;

import utilitaires.Observable;
import utilitaires.Observateur;

public class Console extends JPanel implements Observateur{
	
	
	private JPanel cmdDeplacement = new CmdDeplacement();
	private JPanel gestionPhotos = new GestionPhotos();
	
	public Console() {
		
		this.setLayout(new GridLayout(2, 1));
		
		this.add(cmdDeplacement);
		this.add(gestionPhotos);
	}
	
	public JPanel getCmdDeplacement() {
		return cmdDeplacement;
	}

	@Override
	public void seMettreAJour(Observable observable) {
		
		
		System.out.println("seMettreAJour dans la console");
		
		((Observateur) cmdDeplacement).seMettreAJour(observable);
		
	}

}

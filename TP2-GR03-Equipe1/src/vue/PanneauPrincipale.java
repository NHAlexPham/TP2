package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

import utilitaires.Observable;
import utilitaires.Observateur;

public class PanneauPrincipale extends JPanel implements Observateur{
	
	
	private JPanel console = new Console();
	private JPanel visuelRover = new VisuelRover();
	
	
	public PanneauPrincipale() {
		
		
		JPanel console = new Console();
		JPanel visuelRover = new VisuelRover();
		
		console.setPreferredSize(new Dimension(250, 700));
		visuelRover.setPreferredSize(new Dimension(750, 700));
		
		this.setLayout(new BorderLayout());
		this.add(console, BorderLayout.EAST);
		this.add(visuelRover, BorderLayout.CENTER);
		
		
	}
	
	public JPanel getConsole() {
		return console;
	}

	@Override
	public void seMettreAJour(Observable observable) {
		
		
		System.out.println("seMettreAJour dans le panneau principal");
		
		((Observateur) console).seMettreAJour(observable);
		
		
	}

	
}

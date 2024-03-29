package vue;

import java.awt.GridLayout;

import javax.swing.JPanel;

public class Console extends JPanel{
	
	public Console() {
		
		
		JPanel cmdDeplacement = new CmdDeplacement();
		JPanel gestionPhotos = new GestionPhotos();
		
		this.setLayout(new GridLayout(2, 1));
		
		this.add(cmdDeplacement);
		this.add(gestionPhotos);
	}

}

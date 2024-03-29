package vue;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;

public class PanneauPrincipale extends JPanel{
	
	public PanneauPrincipale() {
		
		
		JPanel console = new Console();
		JPanel visuelRover = new VisuelRover();
		
		console.setPreferredSize(new Dimension(250, 700));
		visuelRover.setPreferredSize(new Dimension(750, 700));
		
		this.setLayout(new BorderLayout());
		this.add(console, BorderLayout.EAST);
		this.add(visuelRover, BorderLayout.CENTER);
		
		
	}

}

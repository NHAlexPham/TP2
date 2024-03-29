package vue;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class CadrePrincipal extends JFrame{

	
	public CadrePrincipal(){
		
		JPanel panneauPrincipale = new PanneauPrincipale();
		
		this.add(panneauPrincipale);
		
		this.setTitle("Satellite");
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setSize(1000,700);
		this.setResizable(false);
		this.setVisible(true);
		
		this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent evt) {
                Object[] options = { "Quitter", "Annuler" };

                int answer = JOptionPane.showOptionDialog(CadrePrincipal.this, "Voulez-vous quitter? ", "Quitter",
                        JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[1]);
                if (answer == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });
	}
	
}

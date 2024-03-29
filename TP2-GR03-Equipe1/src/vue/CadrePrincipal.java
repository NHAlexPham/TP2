package vue;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class CadrePrincipal extends JFrame{

	public static void main(String args[]){
		
		JFrame f = new CadrePrincipal();
		
		
		f.setTitle("Satellite");
		f.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		f.setSize(1000,700);
		f.setResizable(false);
		f.setVisible(true);
		
		f.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent evt) {
                Object[] options = { "Quitter", "Annuler" };

                int answer = JOptionPane.showOptionDialog(f, "Voulez-vous quitter? ", "Quitter",
                        JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[1]);
                if (answer == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });
		
	}
	
	public CadrePrincipal(){
		
		JPanel panneauPrincipale = new PanneauPrincipale();
		
		this.add(panneauPrincipale);
		
		
	}
	
}

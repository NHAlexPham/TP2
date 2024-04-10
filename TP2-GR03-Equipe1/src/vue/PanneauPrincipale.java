package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.JPanel;

public class PanneauPrincipale extends JPanel {

    private JPanel console = new Console();
    private JPanel visuelRover = new VisuelRover();

    public PanneauPrincipale() {
        this.setLayout(new BorderLayout());
        this.add(console, BorderLayout.EAST);
        this.add(visuelRover, BorderLayout.CENTER);

        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                int largeurFenetre = getWidth();
                int hauteurFenetre = getHeight();

                int largeurConsole = (int) (largeurFenetre * 0.25);
                int hauteurConsole = hauteurFenetre;

                console.setPreferredSize(new Dimension(largeurConsole, hauteurConsole));
                console.revalidate(); // Indiquer au gestionnaire de disposition de recalculer la taille
            }
        });
    }

    public JPanel getConsole() {
        return console;
    }

}
package vue;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.ListIterator;

import javax.swing.JPanel;

import modele.environnement.Cratere;
import modele.environnement.Lune;
import utilitaires.CratereGraphique;
import utilitaires.Observable;
import utilitaires.Observateur;
import utilitaires.Vect2D;

public class VisuelRover extends JPanel implements Observateur{
	
	JPanel visuel = new JPanel();

	Lune lune = Lune.getInstance();
	
	ArrayList<CratereGraphique> listeCratereGraphique = new ArrayList<>();
	ArrayList<Cratere> listeCratere = new ArrayList<>();
	
	
	public VisuelRover() {
		
		this.listeCratere = lune.getCrateres();
		
		this.add(visuel);

		this.setBackground(Color.CYAN);
	}
	
	
	private Vect2D convertirPositionToPixel(Vect2D posMetre) {
		
		double xPixel = 0;
		double yPixel = 0;
		
		xPixel = (lune.getDim_Sit().getX() * this.getWidth() / posMetre.getX());
		yPixel = (lune.getDim_Sit().getY() * this.getHeight() / posMetre.getY());
		
		Vect2D posPixel = new Vect2D(xPixel, yPixel);
		
		return posPixel;
	}
	
	private double convertirLongueurToPixel(double longLunaire) {
		
		double longPixel = 0;
		
		longPixel = longLunaire * ((this.getWidth() / lune.getDim_Sit().getX()) + (this.getHeight() / lune.getDim_Sit().getY())) / 2;
		
		return longPixel;
	}

	public void ajouterCratereGraphique() {
		
		ListIterator<Cratere> iterateur = listeCratere.listIterator();
		while (iterateur.hasNext()) {
			
			CratereGraphique crat = new CratereGraphique(iterateur.next());
			listeCratereGraphique.add(crat);
			
		}
	}
	
	
	public void dessinerCratere(Graphics g) {
		
		ListIterator<CratereGraphique> iterateur = listeCratereGraphique.listIterator();
		while (iterateur.hasNext()) {
			
			((CratereGraphique) iterateur).dessine(g);
			
		}
	}
	
	  @Override
	    public void paintComponent(Graphics g) {
	        //Appel de paint() de la classe mère, nécessaire pour dessiner le panneau.
	        super.paintComponent(g);
	        dessinerCratere(g);
	        
	    }
	
	@Override
	public void seMettreAJour(Observable observable) {

		
		
	}
	
	
}

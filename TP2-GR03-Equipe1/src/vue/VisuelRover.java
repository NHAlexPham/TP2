package vue;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.ListIterator;

import javax.swing.JPanel;

import modele.environnement.Cratere;
import modele.environnement.Lune;
import utilitaires.Ligne;
import utilitaires.Observable;
import utilitaires.Observateur;
import utilitaires.Vect2D;

public class VisuelRover extends JPanel{
	

	Lune lune = Lune.getInstance();
	
	ArrayList<CratereGraphique> listeCratereGraphique = new ArrayList<>();
	ArrayList<Cratere> listeCratere = new ArrayList<>();
	
	
	public VisuelRover() {
		
		this.listeCratere = lune.getCrateres();


		
		this.setSize(1000, 750);
		
		ajouterCratereGraphique();
		}
	
	
	private Vect2D convertirPositionToPixel(Vect2D posMetre) {
		
		double xPixel = 0;
		double yPixel = 0;
		
		xPixel = (posMetre.getX() * this.getWidth() / lune.getDim_Sit().getX());
		yPixel = (posMetre.getY() * this.getHeight() / lune.getDim_Sit().getY());
		
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
			
			Cratere cratere = iterateur.next(); // Stockez l'élément actuel pour lutiliser plusieurs fois
		    
		    CratereGraphique cratGraphique = new CratereGraphique(cratere);
		    cratGraphique.setPosConvertie(convertirPositionToPixel(cratere.getPosition()));
		    cratGraphique.setRayonConvertie(convertirLongueurToPixel(cratere.getRayon()));
		    
		    listeCratereGraphique.add(cratGraphique);
			
		}
	}
	
	
	public void dessinerCratere(Graphics g) {
		
		ListIterator<CratereGraphique> iterateur = listeCratereGraphique.listIterator();
		while (iterateur.hasNext()) {
			
			((CratereGraphique) iterateur.next()).dessine(g);
			
		}
	}
	
	
	public void dessinerGrid(Graphics g) {
		
		
		for(int i = 1; i <= 20; i++) {
			
			Ligne ligneH = new Ligne(0, i * 100, 7000, i * 100);
			Ligne ligneV = new Ligne(i * 100, 0, i * 100, 7000);
			
			LigneGraphique ligneGH = new LigneGraphique(ligneH);	
			LigneGraphique ligneGV = new LigneGraphique(ligneV);
			
			ligneGH.dessine(g);
			ligneGV.dessine(g);
		}
		

		

		
	}
	
	  @Override
	    public void paintComponent(Graphics g) {
	        //Appel de paint() de la classe mère, nécessaire pour dessiner le panneau.
	        super.paintComponent(g);
	        
	        dessinerGrid(g);
	        dessinerCratere(g);

	        
	    }
	
	
	
}

package vue;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.ListIterator;

import javax.swing.JPanel;

import modele.centreOperation.CentreOperation;
import modele.environnement.Cratere;
import modele.environnement.Lune;
import modele.rover.Rover;
import utilitaires.EcouteurBtnPrendrePhoto;
import utilitaires.Ligne;
import utilitaires.Observable;
import utilitaires.Observateur;
import utilitaires.Vect2D;

public class VisuelRover extends JPanel implements Observateur{
	

	Lune lune = Lune.getInstance();
	CentreOperation centreOp = CentreOperation.getInstance();
	
	ArrayList<CratereGraphique> listeCratereGraphique = new ArrayList<>();
	ArrayList<Cratere> listeCratere = new ArrayList<>();
	
	RoverGraphique roverG = new RoverGraphique(centreOp.getPositionRover());

	
	public VisuelRover() {
		
		centreOp.ajouterObservateur(this);
		
		this.listeCratere = lune.getCrateres();

		
		this.setSize(1200, 750);
		
		
		
		ajouterCratereGraphique();
		}
	
	
	private Vect2D convertirPositionToPixel(Vect2D posMetre) {
		
		double xPixel = 0;
		double yPixel = 0;
		
		
		int width = getSize().width; // Taille actuelle en pixels du panneau
		int height = getSize().height;

		xPixel = (posMetre.getX() * width / lune.getDim_Sit().getX());
		yPixel = (posMetre.getY() * height / lune.getDim_Sit().getY());
		
		//xPixel = (posMetre.getX() * this.getWidth() / lune.getDim_Sit().getX());
		//yPixel = (posMetre.getY() * this.getHeight() / lune.getDim_Sit().getY());
		
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
		
		
		double conv = 68;
		
		
		for(int i = 1; i <= 20; i++) {
			
			Ligne ligneH = new Ligne(0, i * conv, 3000, i * conv);
			Ligne ligneV = new Ligne(i * conv, 0, i * conv, 3000);
			
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
	     
	     roverG.dessineRover(g);
	     
	
	
	        
	    }


	@Override
	public void seMettreAJour(Observable observable) {
		
		if(observable instanceof CentreOperation) {
			
			Vect2D posConvertie = convertirPositionToPixel(centreOp.getPositionRover());
			
			
	        roverG.setPosRover(posConvertie);
	        repaint(); // Redessiner le panneau pour afficher la nouvelle position du rover
	    }
		
	}
	
	
	
}

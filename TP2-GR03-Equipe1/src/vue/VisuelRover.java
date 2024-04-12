package vue;

/**
 * Classe du panneau du visuel Rover
 * 
 * Cette classe sert a creer un panneau de  visuel Rover pour voir le deplacement et la position du rover en tout temps
 * 
 * 
 * Services offerts:
 *  - convertirPositionToPixel
 *  - convertirLongueurToPixel
 *  - ajouterCratereGraphique
 *  - dessinerCratere
 *  - paintComponent
 *  - seMettreAJour
 * 
 * @author Dyaa Abou Arida, ETS
 * @version Hiver, 2024
 */

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
	

	private Lune lune = Lune.getInstance();
	private CentreOperation centreOp = CentreOperation.getInstance();
	
	private ArrayList<CratereGraphique> listeCratereGraphique = new ArrayList<>();
	private ArrayList<Cratere> listeCratere = new ArrayList<>();
	
	private RoverGraphique roverG = new RoverGraphique(centreOp.getPositionRover());
	
	private int largeurPan = 900;
	private int hauteurPan = 650;
	
	public VisuelRover() {
		
		
		centreOp.ajouterObservateur(this);		//ajoute le panneau visuel rover a la liste des observateur du centre operation
		
		this.listeCratere = lune.getCrateres();	//recuperer la liste des crateres de la lune

		this.setSize(largeurPan, hauteurPan);				//set la taille du panneau
		
		ajouterCratereGraphique();				//ajouter les crateres graphiques au viseul rover
		
		}
	
	/*
	 * methode qui permet de convertir une position lunaire en position pixel
	 * @param posMetre, position en metre a convertir
	 */
	private Vect2D convertirPositionToPixel(Vect2D posMetre) {
		
		double xPixel = 0;
		double yPixel = 0;

		xPixel = (posMetre.getX() * largeurPan / lune.getDim_Sit().getX());
		yPixel = (posMetre.getY() * hauteurPan / lune.getDim_Sit().getY());
		
		Vect2D posPixel = new Vect2D(xPixel, yPixel);
		
		return posPixel;
	}
	
	
	/*
	 * methode qui permet de convertir une longueur lunaire en longueur pixel
	 * @param longLuniare, longueur lunaire a convertir
	 */
	private double convertirLongueurToPixel(double longLunaire) {
		
		double longPixel = 0;
		
		longPixel = longLunaire * ((largeurPan / lune.getDim_Sit().getX()) + (hauteurPan / lune.getDim_Sit().getY())) / 2;		
		
		return longPixel;
	}

	
	/*
	 * methode qui permet de creer et de rajouter tout les crateres graphiques 
	 */
	public void ajouterCratereGraphique() {
		
		ListIterator<Cratere> iterateur = listeCratere.listIterator();
		
		while (iterateur.hasNext()) {
			
			Cratere cratere = iterateur.next(); // Stockez l'élément actuel pour lutiliser plusieurs fois
		    
		    CratereGraphique cratGraphique = new CratereGraphique();
		    cratGraphique.setPosConvertie(convertirPositionToPixel(cratere.getPosition()));
		    cratGraphique.setRayonConvertie(convertirLongueurToPixel(cratere.getRayon()));
		    
		    listeCratereGraphique.add(cratGraphique);
			
		}
	}
	
	
	/*
	 * methode qui permet de dessiner tout les crateres sur le panneau de visuel rover
	 * @param g, parametre graphique
	 */
	public void dessinerCratere(Graphics g) {
		
		//boucle qui passe a travers la liste des crateres graphiques pour les dessiner
		ListIterator<CratereGraphique> iterateur = listeCratereGraphique.listIterator();
		while (iterateur.hasNext()) {
			
			((CratereGraphique) iterateur.next()).dessine(g);
			
		}
	}
	
	/*
	 * methode qui permet de dessiner le grid sur le panneau visuel rover
	 * @param g, parametre graphique
	 */
	public void dessinerGrid(Graphics g) {
		
	
		//boucle qui place les 20 lignes verticales et les 20 lignes horizontales au bons endroits
		for(int i = 1; i <= 20; i++) {
			
			Vect2D conv1 =  convertirPositionToPixel(new Vect2D(0, i *20));	//distance entre chaques lignes
			Vect2D conv2 =  convertirPositionToPixel(new Vect2D(3000, i * 20));	//distance entre chaques lignes
			
			Vect2D conv3 =  convertirPositionToPixel(new Vect2D(i * 20, 0));	//distance entre chaques lignes
			Vect2D conv4 =  convertirPositionToPixel(new Vect2D(i * 20, 3000));	//distance entre chaques lignes
			
			
			Ligne ligneH = new Ligne(conv1, conv2);
			Ligne ligneV = new Ligne(conv3, conv4);
			
			LigneGraphique ligneGH = new LigneGraphique(ligneH);	
			LigneGraphique ligneGV = new LigneGraphique(ligneV);
			
			ligneGH.dessine(g);
			ligneGV.dessine(g);
		}
		
		
	}
	
	/*
	 * redefinition de la methode paintComponent pour dessiner les objets graphiques
	 * @param g, parametre graphique
	 */
	 @Override
	 public void paintComponent(Graphics g) {
		 
		 //Appel de paint() de la classe mère, nécessaire pour dessiner le panneau.
	     super.paintComponent(g);
	        
	     dessinerGrid(g);			//dessine le grid avec les positions sur le panneau
	     dessinerCratere(g);		//dessine les crateres
	     
	     roverG.dessineRover(g);	//dessine le rover
	     
	        
	    }


	 /*
	  * methode redefinie pour se mettre a jour lorsque l'observable notifie l'observateur
	  * @param observable, l'objet que l'on observe
	  */
	@Override
	public void seMettreAJour(Observable observable) {
		
		if(observable instanceof CentreOperation) {
			
			
			//recupere la position du rover pour update sa position sur le panneau
			Vect2D posConvertie = convertirPositionToPixel(centreOp.getPositionRover());
	
	        roverG.setPosRover(posConvertie);	//set la position du rover dans le rover Graphique
	        repaint();	 						// Redessiner le panneau pour afficher la nouvelle position du rover
	    }
		
	}
	
	
	
}

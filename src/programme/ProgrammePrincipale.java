package programme;

import java.io.IOException;

import controleur.MonObserver.Observateur;
import modele.centreOperation.CentreOperation;
import modele.environnement.Lune;
import modele.rover.Rover;
import modele.satelliteRelai.SatelliteRelai;
import utilitaires.Vect2D;
import vue.CadrePrincipale;

import javax.swing.*;

public class ProgrammePrincipale {

	/**
	 * Programme principal, instancie les éléments de la simulation,
	 * les lie entre eux, puis lance la séquence de test.
	 * @param args : pas utilisé
	 */
	public static void main(String[] args){

		JFrame mainFrame = new CadrePrincipale("Devoir 02");

		initialiser(mainFrame);

		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				mainFrame.setVisible(true);
			}
		});
		
	}

	private static void initialiser(JFrame mainFrame) {
		// instantie la lune
		Lune lune = Lune.getInstance();
		// instantie le satellite relai
		SatelliteRelai satellite = new SatelliteRelai();

		// instantie le centre d'opération
		CentreOperation centreOp = CentreOperation.getInstance();
		centreOp.attacherSatellite(satellite);

		//centreOp.ajouterObservateur((Observateur) mainFrame);

		// instantie le Rover
		Rover rover = new Rover(satellite, lune, lune.obtenirPositionAlea());

		// lie rover, centre d'opération et satellite
		satellite.lierCentrOp(centreOp);
		satellite.lierRover(rover);

		// démarre les tâches
		Thread roverTache = new Thread(rover);
		Thread centreOPTache = new Thread(centreOp);
		satellite.start();
		centreOPTache.start();
		roverTache.start();
	}

}

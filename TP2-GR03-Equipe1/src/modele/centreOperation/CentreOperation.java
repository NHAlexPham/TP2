package modele.centreOperation;

/**
 * Simulation du centre d'opération, ou centre de controle de la mission.
 * Cette classe est responsable de gérer les messages envoyés par le Rover
 * et d'envoyer les commandes. Pour l'instant, l'opération du rover se fait
 * par l'entremise de la séquence de test.
 * 
 * Cette classe dérive du transporteur message, responsable de gérer le protocole
 * des communications.
 * 
 * Cette classe est implémenté comme une tâche s'exécutant indépendamment (Thread)
 * Cette classe est également implémenté comme LazySingleton
 * 
 * Services offerts:
 *  - getInstance
 *  - executerSequenceDeTest
 *  - envoyerMessage
 *  - gestionnaireMessage
 * 
 * @author Frederic Simard, ETS
 * @version Hiver, 2024
 */
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

import modele.communication.CmdDeplacerRover;
import modele.communication.Commande;
import modele.communication.Constantes;
import modele.communication.Message;
import modele.communication.MorceauImage;
import modele.communication.Nack;
import modele.communication.Status;
import modele.communication.TransporteurMessage;
import modele.communication.eCommande;
import modele.satelliteRelai.SatelliteRelai;
import utilitaires.CompteurMessage;
import utilitaires.Vect2D;



public class CentreOperation extends TransporteurMessage{
	

	// référence au fichier et stream permettant d'enregistrer les photos
	// reçu dans un fichier
	private File photo = null;
    FileOutputStream streamSortie;
    int compteurPhoto = 0;
    
    Vect2D positionRover = null;
    double progresFichier = 0.0;
    double tailleCourante = 0.0;
    
    // référence sur le centre de contrôle (LazySingleton)
    private static CentreOperation instance = new CentreOperation();
        
    // référence au satellite qui relaye les communications vers le rover
	private SatelliteRelai satellite;
	
	

    /**
     * Constructeur, requiert une référence au satellite
     * @param satellite, reférence au satellite
     */
	private CentreOperation(){
		super();
	}
	
	
	public Vect2D getPositionRover() {
		return positionRover;
	}
	
	public double getProgresFichier() {
		return progresFichier;
	}

	/**
	 * Méthode permettant d'obtenir une référence sur le centre de contrôle
	 * @return une référence sur le centre de contrôle
	 */
	public static CentreOperation getInstance() {
		return instance;
	}
	
	/**
	 * 
	 * @param satellite, reference au satellite
	 */
	public void attacherSatellite(SatelliteRelai satellite) {
		this.satellite = satellite;
	}

	/**
	 * Méthode envoyant la commande de prendre une photo
	 */
	public void prendrePhoto() {
		
		System.out.println("Prendre Photo");
		Commande msgCmd = new Commande(compteurMsg.getCompteActuel(),eCommande.PRENDRE_PHOTOS);
		satellite.envoyerMessageVersRover(msgCmd);
		messageEnvoyes.add(msgCmd);

		progresFichier = 0;
	}
	
	/**
	 * Méthode envoyant la commande de déplacer le Rover
	 * @param posX position en X
	 * @param posY position en Y
	 */
	public void deplacerRover(double posX,double posY) {
		Commande msgCmd = new CmdDeplacerRover(compteurMsg.getCompteActuel(),new Vect2D(posX,posY));
		satellite.envoyerMessageVersRover(msgCmd);
		messageEnvoyes.add(msgCmd);		
	}


    /**
     * Cette méthode envoi un message au satellite relai à destination du Rover
     * @param msg, message à envoyer
     */
	protected void envoyerMessage(Message msg) {
		satellite.envoyerMessageVersRover(msg);
	}

    /**
     * Cette méthode effectue la gestion des messages entrant
     * @param msg, message entrant à traiter
     */
	protected void gestionnaireMessage(Message msg) {

		// vérifie s'il s'agit d'un message contenant un morceau d'image
		if(msg instanceof MorceauImage) {
			
			// oui, on cast le message en MorceauImage
			MorceauImage morceauIm = (MorceauImage)msg; 

			// on écrit le morceau dans un fichier
			try {
				
				// s'il ne s'agit pas de la fin
				if(morceauIm.getFin()==false) {
					
					
					// si aucun fichier n'est ouvert
					if(photo==null) {

						System.out.println("Reception d'une photo, début");
						tailleCourante = 0.0;
						
						// on ouvre un fichier
						photo = new File("photos/image"+compteurPhoto+".jpg");
					    streamSortie = new FileOutputStream(photo);
					}
					// on écrit le morceau dans un fichier ouvert
					streamSortie.write(morceauIm.getMorceau());
					

					tailleCourante += (double)morceauIm.getMorceau().length;
					progresFichier = tailleCourante/morceauIm.getTailleTotale();
					
					this.notifierObservateurs(); //afficher le progres dans le GUI en notifiant l'observateur
					
				}else {
					// ferme le fichier
					streamSortie.close();
					photo = null;
					compteurPhoto++;
					
					System.out.println("Reception d'une photo, terminé");
					
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else if(msg instanceof Status) {
			Status msgStatus = (Status)msg;
			System.out.println("Status reçu");
			System.out.println("    position du Rover: " + msgStatus.getPosition());
			positionRover = msgStatus.getPosition();
			
			
			this.notifierObservateurs(); //afficher la position dans le GUI en notifiant l'observateur
		}
	}
	
}

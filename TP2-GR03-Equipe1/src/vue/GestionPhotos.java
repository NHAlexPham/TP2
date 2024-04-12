package vue;

/**
 * Classe du panneau de gestion de photos
 * 
 * Cette classe sert a creer un panneau de gestion de photos et interagir avec le centre de controle 
 * pour faire prendre des photos au Rover
 * 
 * 
 * Services offerts:
 *  - updateListePhotos
 *  - seMettreAJour
 * 
 * @author Dyaa Abou Arida, ETS
 * @version Hiver, 2024
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.File;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import modele.centreOperation.CentreOperation;
import modele.rover.Rover;
import utilitaires.EcouteurBtnPrendrePhoto;
import utilitaires.Observable;
import utilitaires.Observateur;

public class GestionPhotos extends JPanel implements Observateur{

	
	private JPanel gestion = new JPanel(new BorderLayout());
	private JPanel panneauInterne = new JPanel(new BorderLayout());
	private JPanel nord = new JPanel();
	private JPanel panProgres = new JPanel(new GridLayout(2, 1, 2, 2));
	
	
	private JButton prendrePhoto = new JButton("Prendre Photo");
	private JProgressBar progres = new JProgressBar();
	private JList<String> liste = new JList<String>();
	
	private CentreOperation centreOp = CentreOperation.getInstance();
	private EcouteurBtnPrendrePhoto ect = new EcouteurBtnPrendrePhoto(centreOp);
	
	
	public GestionPhotos() {
		 
		centreOp.ajouterObservateur(this);							//ajouter ce panneau a la liste d'observateur du centre Op
		
		panProgres.add(prendrePhoto);								//ajoute le bouton prendre photo au panneau Progres 
		panProgres.add(progres);									//ajoute la barre de progres prendre photo au panneau Progres 
		
		nord.add(panProgres);										//ajoute le panneau de photoProgres dans le panneau nord
		
		panneauInterne.add(nord, BorderLayout.NORTH);				//ajoute le panneau nord au nord du panneau gestion
		panneauInterne.add(gestion, BorderLayout.CENTER);			//ajoute le panneau Interne au centre du panneau gestion
		
		gestion.add(liste, BorderLayout.CENTER);					//ajoute la list au centre du panneau gestion
		 
		gestion.setBackground(Color.DARK_GRAY);						//set la couleur des background a gris fonce
		nord.setBackground(Color.DARK_GRAY);						//set la couleur des background a gris fonce
		
		this.add(panneauInterne);									//ajoute le panneau interne a ce panneau
		
		panProgres.setBackground(Color.DARK_GRAY);					//set la couleur des background a gris fonce
		
		liste.setPreferredSize(new Dimension(200, 240));			//set la dimension de la liste
		
		this.setBackground(Color.DARK_GRAY);							//set la couleur des background a gris fonce
		this.setBorder(BorderFactory.createLineBorder(Color.WHITE));	//set la couleur de la bordure a blanc
		
		progres.setMinimum(0);					//set la valeur minimale de la barre de progres
		progres.setMaximum(100);				//set la valeur maximale de la barre de progres
		progres.setValue(0); 					//valeur initiale de la barre de progres
		
		updateListePhotos();					//update la liste des photos
		
		prendrePhoto.addActionListener(ect);	//ajoute l'ecouteur au bouton de prendre des photos
		
	}
	
	
	/*
	 * methode qui permet de update la liste des photos 
	 */
	public void updateListePhotos() {
		
		 DefaultListModel<String> listModel = new DefaultListModel<>();

		    try {
		        File[] files = new File("C:\\Users\\dyaa-\\Desktop\\Ecole\\Session 1\\INF111-03\\TP2\\TP2-GR03-Equipe1\\photos").listFiles();
		        for (File file : files) {
		            if (file.isFile()) {
		                listModel.addElement(file.getName());
		            }
		        }
		    } catch (NullPointerException e) {
		        System.err.println("Le répertoire /photos est vide ou n'existe pas.");
		    }

		    liste.setModel(listModel); //associer le modèle de liste à la JList
	
	}
	
	
	/*
	 * methode qui met a jour le panneau quand l'observable notifie
	 * @param observable, l'objet qu'on observe
	 */
	@Override
	public void seMettreAJour(Observable observable) {

		double valProgres = 0;	//valeur initiale du progres

		if(observable instanceof CentreOperation) {
			
			valProgres = ((CentreOperation) observable).getProgresFichier(); //obtient le progres du centre Op
			progres.setStringPainted(true);
			progres.setValue((int) (valProgres * 100)); 					 //affiche le progres dans la progress bar
			

			updateListePhotos(); //update la Jlist			 
		}
		
		 
		
		
	}
	
	
	
	
	
}

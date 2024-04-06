package vue;

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

	
	JPanel gestion = new JPanel(new BorderLayout());
	JPanel panneauInterne = new JPanel(new BorderLayout());
	JPanel nord = new JPanel();
	JPanel photoProgres = new JPanel(new GridLayout(2, 1, 2, 2));
	
	
	JButton prendrePhoto = new JButton("Prendre Photo");
	JProgressBar progres = new JProgressBar();
	JList<String> liste = new JList<String>();
	
	EcouteurBtnPrendrePhoto ect = new EcouteurBtnPrendrePhoto();
	
	
	public GestionPhotos() {
		 
		
		//ajoute le bouton et la barre de progres dans le panneau nord
		photoProgres.add(prendrePhoto);
		photoProgres.add(progres);
		
		nord.add(photoProgres);
		
		panneauInterne.add(nord, BorderLayout.NORTH);//ajoute le panneau nord au nord du panneau gestion
		panneauInterne.add(gestion, BorderLayout.CENTER);
		
		gestion.add(liste, BorderLayout.CENTER);	//ajoute la list au centre du panneau gestion
		 
		gestion.setBackground(Color.DARK_GRAY);		//set la couleur des background a gris fonce
		nord.setBackground(Color.DARK_GRAY);
		
		this.add(panneauInterne);
		
		photoProgres.setBackground(Color.DARK_GRAY);
		
		liste.setPreferredSize(new Dimension(200, 240));
		
		this.setBackground(Color.DARK_GRAY);		//set la couleur des background a gris fonce
		
		this.setBorder(BorderFactory.createLineBorder(Color.WHITE));	//set la couleur de la bordure a blanc
		
		progres.setMinimum(0);
		progres.setMaximum(100);
		progres.setValue(0); // Valeur initiale
		
		listePhotos();
		
		prendrePhoto.addActionListener(ect);
		
	}
	
	public void listePhotos() {
		
		
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

		    liste.setModel(listModel); // Associer le modèle de liste à la JList
		
		
	
	}
	
	

	@Override
	public void seMettreAJour(Observable observable) {


		if(observable instanceof CentreOperation) {
			
			double valProgres;
			
			valProgres = ((CentreOperation) observable).getProgresFichier();
			
			progres.setValue((int)valProgres); // affiche le progres dans la progress bar

			
		}
		else if(observable instanceof Rover) {

			listePhotos(); //update la Jlist 
			
		}
		
	}
	
	
	
	
	
}

package utilitaires;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import modele.centreOperation.CentreOperation;

public class EcouteurBtnPrendrePhoto implements ActionListener{

	private CentreOperation centreOp;
	
	public EcouteurBtnPrendrePhoto(CentreOperation centreOp) {
		this.centreOp = centreOp;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {


		System.out.println("jai pris une photo"); // pour test uniquement
		
		centreOp.prendrePhoto();
		
	}

}

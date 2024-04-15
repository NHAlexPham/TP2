package controleur;

import modele.centreOperation.CentreOperation;
import modele.communication.MorceauImage;
import modele.rover.Rover;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControleurPhoto implements ActionListener {
    JList list;
    JButton btnPhoto;
    JProgressBar bar;

    public ControleurPhoto(JButton btnPhoto,JProgressBar bar,JList list){

        this.btnPhoto = btnPhoto;
        this.bar = bar;
        this.list = list;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.bar.setValue(0);

        Object source = e.getSource();
        System.out.println("BTN ROVER");
        if (source==btnPhoto)
        {
            try{
                CentreOperation centre = CentreOperation.getInstance();centre.prendrePhoto();
            }catch (Exception exp){
                JOptionPane.showMessageDialog(null,
                        exp.getMessage());
            }
        }
    }

}

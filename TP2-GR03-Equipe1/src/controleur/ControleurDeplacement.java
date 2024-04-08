package controleur;

import modele.centreOperation.CentreOperation;
import vue.CadrePrincipale;
import vue.PanneauPrincipale;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class ControleurDeplacement implements ActionListener {

    JTextField txtPosX, txtPosY;
    JButton btnRover;

    public ControleurDeplacement(JTextField txtPosX, JTextField txtPosY, JButton btnRover) {
        this.txtPosX = txtPosX;
        this.txtPosY = txtPosY;
        this.btnRover = btnRover;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        DecimalFormat formateur = new DecimalFormat("0.##");
        DecimalFormatSymbols dfs = new DecimalFormatSymbols();
        dfs.setDecimalSeparator('.');
        //formateur.setDecimalFormatSymbols(dfs);
        Object source = e.getSource();
        System.out.println("BTN ROVER");
        if (source==btnRover)
        {
            try{
                double posX = Double.parseDouble(txtPosX.getText());
                double posY = Double.parseDouble(txtPosY.getText());
                CentreOperation centre = CentreOperation.getInstance();
                centre.deplacerRover(posX,posY);
            }catch (NumberFormatException exp){
                JOptionPane.showMessageDialog(null,
                        "Erreur dans les TextField");
            }
        }
    }
}

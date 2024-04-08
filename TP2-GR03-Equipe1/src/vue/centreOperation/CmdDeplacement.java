package vue.centreOperation;

import controleur.ControleurDeplacement;
import controleur.ControleurPhoto;
import controleur.MonObserver.Observable;
import controleur.MonObserver.Observateur;
import modele.centreOperation.CentreOperation;
import utilitaires.Vect2D;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

import static javax.swing.text.html.HTML.Attribute.PROMPT;

public class CmdDeplacement extends JPanel {

    JButton btnDeplacer;
    ClassPositionCourante classCourante = new ClassPositionCourante();
    ClassDefinirPosition classDefenir = new ClassDefinirPosition();
    public CmdDeplacement(){
        initialiserComposants();
    }

    private void initialiserComposants() {
        // Ajouter Observer !
        CentreOperation centreOp = CentreOperation.getInstance();
        centreOp.ajouterObservateur(classCourante);


        JPanel main = new JPanel(new BorderLayout(5, 5));
        btnDeplacer = new JButton("Button");
        main.add(classCourante, BorderLayout.NORTH);
        main.add(classDefenir, BorderLayout.CENTER);

        //
        ControleurDeplacement ecouteur = new ControleurDeplacement(classDefenir.txtPosX, classDefenir.txtPosY,btnDeplacer);
        btnDeplacer.addActionListener(ecouteur);

        main.add(btnDeplacer, BorderLayout.SOUTH);
        add(main);
        this.setBorder(BorderFactory.createLineBorder(Color.WHITE));
    }

    public class ClassPositionCourante extends JPanel implements Observateur{
        JLabel lblTextPosX, lblTextPosY, lblPosX, lblPosY;

        public ClassPositionCourante(){
            Initial();
            setLayout(new BorderLayout());
            JPanel panel = new JPanel(new GridLayout(1, 2,10,10));
            panel.add(lblTextPosX);
            panel.add(lblPosX);
            JPanel panel2 = new JPanel(new GridLayout(1, 2,10,10));
            panel2.add(lblTextPosY);
            panel2.add(lblPosY);
            add(panel,BorderLayout.NORTH);
            add(panel2,BorderLayout.SOUTH);
        }

        public void ecrit(double posX, double posY){
            this.lblPosX.setText(""+posX);
            this.lblPosY.setText(""+posY);
        }

        private void Initial() {
            this.lblTextPosX = new JLabel("Pos Courante X:");
            this.lblTextPosY = new JLabel("Pos Courante Y");
            this.lblPosX = new JLabel("0");
            this.lblPosY = new JLabel("0");
        }

        public void ecritTexte(String lol, String haha) {
            System.out.println("Ecrit Texte !");
            this.lblPosX.setText("lol");
            this.lblPosY.setText(haha);
        }

        @Override
        public void seMettreAJour(Observable observable) {

            if (observable instanceof CentreOperation){
                CentreOperation centre = (CentreOperation)observable;
                if(centre.getPositionRover() != null){
                    // Formateur de la position
                    DecimalFormat formateur = new DecimalFormat("#.0");
                    DecimalFormatSymbols dfs = new DecimalFormatSymbols();
                    dfs.setDecimalSeparator('.');
                    formateur.setDecimalFormatSymbols(dfs);

                    this.lblPosX.setText(String.valueOf(formateur.format(centre.getPositionRover().getX())));
                    this.lblPosY.setText(String.valueOf(formateur.format(centre.getPositionRover().getY())));
                }else{
                    // Si null...
                    this.lblPosX.setText("xxx");
                    this.lblPosY.setText("yyy");
                }

            }
        }
    }


    public class ClassDefinirPosition extends JPanel{

        JLabel lblPosX, lblPosY;
        JTextField txtPosX, txtPosY;

        public ClassDefinirPosition(){
            Initial();
            setLayout(new BorderLayout());
            JPanel panel = new JPanel(new GridLayout(1, 2,10,10));
            panel.add(lblPosX);
            panel.add(txtPosX);
            //int eb=10;
            //panel.setBorder(BorderFactory.createEmptyBorder(eb, eb, eb, eb));
            JPanel panel2 = new JPanel(new GridLayout(1, 2,10,10));
            panel2.add(lblPosY);
            panel2.add(txtPosY);
            add(panel,BorderLayout.NORTH);
            add(panel2,BorderLayout.SOUTH);
        }

        private void Initial() {
            this.lblPosX = new JLabel("Pos Cible X: ");
            this.lblPosY = new JLabel("Pos Cible Y: ");
            this.txtPosX = new JTextField(5);
            this.txtPosY = new JTextField(5);
        }

    }
}

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
        JPanel main = new JPanel(new GridLayout(3, 1,0,5));
        JPanel label = new JPanel();
        JPanel field = new JPanel(new BorderLayout(0,5));
        JPanel button = new JPanel(new BorderLayout(0,0));
        btnDeplacer = new JButton("Déplacer Rover");
        btnDeplacer.setFocusable(false);

        main.setBackground(Color.DARK_GRAY);

        //label.setPreferredSize(new Dimension(0,0));
        label.setBackground(Color.DARK_GRAY);
        label.add(classCourante);

        field.setPreferredSize(new Dimension(250,50));
        field.setBackground(Color.DARK_GRAY);
        field.add(classDefenir);


        //button.setPreferredSize(new Dimension(250,40));
        button.setBackground(Color.DARK_GRAY);
        button.add(btnDeplacer);


        main.add(label);
        main.add(field);
        main.add(button);
        add(main);

        this.setBorder(BorderFactory.createLineBorder(Color.WHITE));

        // Ajouter Observer !
        ControleurDeplacement ecouteur = new ControleurDeplacement(classDefenir.txtPosX, classDefenir.txtPosY,btnDeplacer);
        btnDeplacer.addActionListener(ecouteur);
        CentreOperation centreOp = CentreOperation.getInstance();
        centreOp.ajouterObservateur(classCourante);
    }

    public class ClassPositionCourante extends JPanel implements Observateur{
        JLabel lblTextPosX, lblTextPosY, lblPosX, lblPosY;

        public ClassPositionCourante(){
            Initial();
            //setBackground(Color.WHITE);
            setLayout(new BorderLayout());
            setPreferredSize(new Dimension(195,40));
            JPanel panel = new JPanel(new GridLayout(1, 2,0,0));
            panel.add(lblTextPosX);
            panel.add(lblPosX);
            JPanel panel2 = new JPanel(new GridLayout(1, 2,0,0));
            panel2.add(lblTextPosY);
            panel2.add(lblPosY);
            add(panel,BorderLayout.NORTH);
            add(panel2,BorderLayout.SOUTH);
        }

        private void Initial() {
            this.lblTextPosX = new JLabel("Pos Courante X: ");
            this.lblTextPosY = new JLabel("Pos Courante Y: ");
            this.lblPosX = new JLabel("0");
            this.lblPosY = new JLabel("0");
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
            setBackground(Color.DARK_GRAY);
            setLayout(new GridLayout(2,1,0,5));
            //setPreferredSize(new Dimension(210,50));
            JPanel panel = new JPanel(new GridLayout(1, 2,0,0));
            panel.add(lblPosX);
            panel.add(txtPosX);
            JPanel panel2 = new JPanel(new GridLayout(1, 2,0,0));
            panel2.add(lblPosY);
            panel2.add(txtPosY);
            add(panel);
            add(panel2);
        }

        private void Initial() {
            this.lblPosX = new JLabel("Pos Cible X: ");
            this.lblPosY = new JLabel("Pos Cible Y: ");
            this.txtPosX = new JTextField(5);
            this.txtPosY = new JTextField(5);
        }

    }
}

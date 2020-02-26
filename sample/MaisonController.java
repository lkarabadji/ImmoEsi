package sample;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.text.TextFlow;
import tp.Poo.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MaisonController implements Initializable {
	private  static   Maison maison = null;
	public  static void setMaison(Maison m)
    {maison = m;}

    private static boolean allowRetour = false;
	public static void setAllowRetour(Boolean v){allowRetour= v;}

	@FXML
    private Button retour;
    @FXML
    private Label typeBien;

    @FXML
    private Label Adresse;

    @FXML
    private Label Wilaya;

    @FXML
    private Label Superficie;

    @FXML
    private Label nbEtages;

    @FXML
    private Label Jardin;

    @FXML
    private Label Piscine;
    @FXML
    private  Label Meuble;
    @FXML
    private Label Garage;
    @FXML
    private Label SuperficieH;

    @FXML
    private Label Transaction;

    @FXML
    private Label Prix;

    @FXML
    private Button AffichButton;
    
    @FXML
    private Label negociable;
    
    @FXML
    private TextArea descriptif;
    @FXML
    public  void retour(ActionEvent event) throws IOException
    {
        StandardController.changerScene(event,"afficherBien.fxml",getClass());
    }
    //@FXML
    /*
    void AfficherDetails() {
    	if(maison != null)
        {
            typeBien.setText(maison.getClass().getSimpleName());
            Transaction.setText(String.valueOf(maison.getTransaction()));
            Wilaya.setText(maison.getwilaya().toString());
            System.out.print("");
            Adresse.setText(maison.getAdresse());
            System.out.print("");
            nbEtages.setText(String.valueOf(maison.getNbetages()));
            System.out.print("");
            Superficie.setText(String.valueOf(maison.getsuperficie()));
            System.out.print("");
            Prix.setText(String.valueOf(maison.getPrix()));
            if(!(maison.getDescriptif().isEmpty()) && !(maison.getDescriptif().isBlank()))
            {
                descriptif.setText(this.maison.getDescriptif());
                System.out.print("");
            }
            if (maison.getNegociable())
                negociable.setText("Bien negociable");
            else
                negociable.setText("Non negociable");

            System.out.print("");
            if (this.maison.isPiscine())
                Piscine.setSelected(true);
            else
                Piscine.setSelected(false);
            System.out.print("");
            if (this.maison.isJardin())
                Jardin.setSelected(true);
            else
                Jardin.setSelected(false);
            System.out.print("");
            if (this.maison.isGarage())
                Garage.setSelected(true);
            else
                Garage.setSelected(false);
                }
        }*/

   
    public Bien getMaison() {
		return maison;
	}

	/*
	public  static  void setMaison(Maison maison) {
	//	System.out.println("SET MAISON");
		MaisonController.maison = maison;
	}*/


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (maison != null)
        {
            typeBien.setText(maison.getClass().getSimpleName());
            Transaction.setText(String.valueOf(maison.getTransaction()));
            Wilaya.setText(maison.getwilaya().toString());
            Adresse.setText(maison.getAdresse());
            nbEtages.setText(String.valueOf(maison.getNbetages()));
            Superficie.setText(String.valueOf(maison.getsuperficie()));
            Prix.setText(String.valueOf(maison.getPrix()));
            if(this.maison.getDescriptif().compareTo("")!=0)
                descriptif.setText(this.maison.getDescriptif());

            if (maison.getNegociable())
                negociable.setText("Bien negociable");
            else
                negociable.setText("Non negociable");

            if (this.maison.isPiscine())
                Piscine.setVisible(true);
            else
                Piscine.setVisible(false);

            if (this.maison.isJardin())
                Jardin.setVisible(true);
            else
                Jardin.setVisible(false);

            Garage.setVisible(maison.isGarage());
            Meuble.setVisible(maison.isMeuble());

        }
        if(allowRetour)
        {
            retour.setVisible(true);
        }
        else
            retour.setVisible(false);


    }
        ///AfficherDetails();


}

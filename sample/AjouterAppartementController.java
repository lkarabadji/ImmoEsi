package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import tp.Poo.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static java.lang.Double.parseDouble;

public class AjouterAppartementController  implements Initializable {

@FXML
    CheckBox ascenceur;
@FXML
    CheckBox meuble;
@FXML
    TextField nbrPieces;
@FXML
    TextField etage;
@FXML
    TextField emplacement;
@FXML
    ChoiceBox wilayaEchange;
@FXML
    TextArea descriptif;
@FXML
    Pane echangePane;
@FXML
    Button finir;

//Variaables statiques:
    private  static Double  prix,superficie;
    static Boolean echange,negociable;
    private   static String wilaya,adresse,transaction;
    private static String wilayaEch;
    private  static Proprietaire p;


    public static void setNegociable(Boolean val)
    {
        negociable = val;
    }

    public static void setP(Proprietaire prop)
    {
        p = prop;
    }

    public static void setPrix(Double prix) {
        AjouterAppartementController.prix = prix;
    }

    public static void setSuperficie(Double superficie) {
        AjouterAppartementController.superficie = superficie;
    }

    public static void setWilaya(String wilaya) {
        AjouterAppartementController.wilaya = wilaya;
    }

    public static void setTransaction(String transaction) {
        AjouterAppartementController.transaction = transaction;
    }

    public static void setAdresse(String adresse) {
        AjouterAppartementController.adresse = adresse;
    }

    public static  void setWilayaEch(String wilayaEch) {
        AjouterAppartementController.wilayaEch = wilayaEch;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        finir.setVisible(false);
        if (transaction.equalsIgnoreCase("Echange"))
        {
            echangePane.setVisible(true);
            wilayaEchange.getItems().setAll(Wilaya.values());
        }
        else
            echangePane.setVisible(false);

    }

    @FXML
    public void confirmerMaison()
    {
        if (nbrPieces.getText().isEmpty())
        {
            StandardController.showAlert(Alert.AlertType.ERROR,"Erreur formulaire","Veuillez introduire le nombre de pieces");
            return;
        }
        if (etage.getText().isEmpty())
        {
            StandardController.showAlert(Alert.AlertType.ERROR,"Erreur formulaire","Veuillez introduire l'etage");
            return;
        }
        if (emplacement.getText().isEmpty())
        {
            StandardController.showAlert(Alert.AlertType.ERROR,"Erreur formumaire","Veillez introduire l'emplacment");
            return;
        }
        if (transaction.equalsIgnoreCase("Echange"))
        {
            if(wilayaEchange.getSelectionModel().isEmpty())
            {
                StandardController.showAlert(Alert.AlertType.ERROR,"Erreur formumaire","Veillez introduire la wilaya d'echange");
                return;
            }
        }

        String s = "bien" + (ImmoEsi.listeBiens.size()+1);
        int nb = Integer.parseInt(nbrPieces.getText());
        int et = Integer.parseInt(etage.getText());
        Bien b = new Appartement(transaction,prix,p,wilaya,superficie,adresse,nb,et,s);
        if( ascenceur.isSelected())
        {
            ((Appartement) b).setAscenseur();
        }

        ((Appartement) b).setMeuble(meuble.isSelected());
        if (!(descriptif.getText().isEmpty()))
        {
            b.ajouterDescriptif(descriptif.getText());
        }
        b.setNegociable(negociable);


        if (ImmoEsi.listeBiens.add(b))
        {
            StandardController.showAlert(Alert.AlertType.CONFIRMATION,"Confirmer","Bien ajouté avec succes !");
            finir.setVisible(true);
        }
        else
            StandardController.showAlert(Alert.AlertType.ERROR,"Bien non insere","ce bien ne peut pas etre inseré");



    }
    @FXML
    public void retour(ActionEvent event) throws IOException
    {
      if (  StandardController.confirmation("jcp","jcp"))
      {
          StandardController.changerScene(event,"ajouterBien.fxml",getClass());
      }
    }
    @FXML
    public void cancel(ActionEvent event) throws  IOException
    {
        if(StandardController.confirmation("jcp","c"))
        {
            StandardController.changerScene(event,"adminAceuil.fxml",getClass());
        }

    }
    @FXML
    public void finir(ActionEvent event) throws  IOException
    {
        ControllerBien.setAdmin(true);
        StandardController.changerScene(event,"afficherBien.fxml",getClass());

    }

}

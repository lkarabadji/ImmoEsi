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

public class AjouterTerrainController implements Initializable {

    @FXML
    TextArea statutJur;
    @FXML
    TextField nbrfacades;
    @FXML
    ChoiceBox<Wilaya>  wilayaEchange;
    @FXML
    TextArea descriptif;
    @FXML
    Button finir;
    @FXML
    Pane echangePane;


    //Variaables statiques:
    private  static Double  prix,superficie;
    static Boolean echange,negociable;
    private   static String wilaya,adresse,transaction;
    private static String wilayaEch;
    private  static Proprietaire p;


    public static void setSuperficie(Double superficie) {
        AjouterTerrainController.superficie = superficie;
    }

    public static void setEchange(Boolean echange) {
        AjouterTerrainController.echange = echange;
    }

    public static void setNegociable(Boolean negociable) {
        AjouterTerrainController.negociable = negociable;
    }

    public static void setWilaya(String wilaya) {
        AjouterTerrainController.wilaya = wilaya;
    }

    public static void setAdresse(String adresse) {
        AjouterTerrainController.adresse = adresse;
    }

    public static void setTransaction(String transaction) {
        AjouterTerrainController.transaction = transaction;
    }

    public static void setWilayaEch(String wilayaEch) {
        AjouterTerrainController.wilayaEch = wilayaEch;
    }

    public static void setP(Proprietaire p) {
        AjouterTerrainController.p = p;
    }

    public static void setPrix(Double prix) {
        AjouterTerrainController.prix = prix;
    }

    @FXML
    public void confirmer()
    {
        if(statutJur.getText().isEmpty())
        {
            StandardController.showAlert(Alert.AlertType.ERROR,"Erreur formulaire","Veuillez introduire le statut juridique du terrain");
            return;
        }
        if(nbrfacades.getText().isEmpty())
        {
            StandardController.showAlert(Alert.AlertType.ERROR,"Erreur formulaire","Veuillez introduire le nombre de façades");
            return;
        }
        if (transaction.equalsIgnoreCase("Echange"))
        {
            if (wilayaEchange.getSelectionModel().isEmpty())
            {

                StandardController.showAlert(Alert.AlertType.ERROR,"Erreur formulaire","Veuillez choisir la wilaya d'echange");
                return;
            }
        }
        int n = ImmoEsi.listeBiens.size()+1;
        String id = "bien"+n;
        Bien b = new Terrain(transaction,prix,wilaya,p,superficie,adresse,Integer.parseInt(nbrfacades.getText()),id);
        //b.setNegociable(negociable);
        if (ImmoEsi.listeBiens.add(b))
        {
            StandardController.showAlert(Alert.AlertType.CONFIRMATION,"Confirmer","Terrain ajouté avec succes !");
            finir.setVisible(true);
        }
        else
            StandardController.showAlert(Alert.AlertType.ERROR,"Bien non insere","ce terrain ne peut pas être inseré");




    }

    @FXML
    private  void handleFinir(ActionEvent event) throws IOException
    {
        StandardController.changerScene(event,"afficherBien.fxml",getClass());
    }

    @FXML
    private  void retour(ActionEvent event) throws  IOException
    {
        if (  StandardController.confirmation("jcp","jcp"))
        {
            StandardController.changerScene(event,"ajouterBien.fxml",getClass());
        }
    }
    @FXML
    private  void cancel(ActionEvent event)throws  IOException
    {
        if(StandardController.confirmation("jcp","c"))
        {
            StandardController.changerScene(event,"adminAceuil.fxml",getClass());
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (transaction.equalsIgnoreCase("Echange"))
        {
            wilayaEchange.getItems().setAll(Wilaya.values());
            echangePane.setVisible(true);
        }
        else echangePane.setVisible(false);
        finir.setVisible(false);

    }
}

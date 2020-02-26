package sample;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import tp.Poo.Bien;
import tp.Poo.ImmoEsi;
import tp.Poo.Proprietaire;

import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;


public class AfficherProp extends Stage implements Initializable  {


    private  static Boolean ajoutBien  = false;
    public static void setAjoutBien(Boolean val)
    {
        ajoutBien = val;
    }
    @FXML
    Label nom;
    @FXML
    Label prenom;
    @FXML
    Label adresse;
    @FXML
    Label email;
    @FXML
    Label numtel;
    @FXML
    ListView listView = new ListView();
    @FXML
    Button selectionner;
    @FXML
    ToolBar toolBar;
    @FXML
    Button retour;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        for(Proprietaire p : ImmoEsi.listeProprietaires)
        {
            listView.getItems().add(p.getId());
        }
        if (ajoutBien)
        {
            toolBar.setVisible(false);
            selectionner.setVisible(true);
            retour.setVisible(false);
        }
        else
        {
            toolBar.setVisible(true);
            selectionner.setVisible(false);
            retour.setVisible(true);
        }

    }
    @FXML
    public void debut()
    {
        for(Proprietaire p : ImmoEsi.listeProprietaires)
        {
            listView.getItems().add(p.getId());
        }
        if (ajoutBien)
        {
            toolBar.setVisible(false);
            selectionner.setVisible(true);
        }
        else
        {
            toolBar.setVisible(true);
            selectionner.setVisible(false);
        }
    }


    @FXML
    public Proprietaire getProprietaire() {
        ObservableList liste2 = listView.getSelectionModel().getSelectedItems();
        Proprietaire p = null;
        for (Object item : liste2) {
            Boolean found = false;
            String s = (String) item;
            Iterator<Proprietaire> iter = ImmoEsi.listeProprietaires.iterator();
            while (iter.hasNext() && !(found)) {
                p = iter.next();
                if (p.getId().compareTo(s) == 0) {
                    found = true;
                }
            }
        }

        if (p != null) {
            //
            this.nom.setText(p.getNom());
            this.adresse.setText(p.getAdresse());
            this.prenom.setText(p.getPrenom());
            this.numtel.setText(p.getNumTel());
            this.email.setText(p.getEmail());
        }

        return p;
    }

    @FXML
    public void ajouterP(ActionEvent event) throws IOException
    {
        if (ajoutBien)
        {
            AjouterProprietaireController.setAjoutBien(true);
        }
        else
            AjouterProprietaireController.setAjoutBien(false);
        Parent home_page_parent = FXMLLoader.load(getClass().getResource("ajouterProprietaire.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        System.out.println("You cliked me !!");
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        System.out.println("");
        app_stage.setScene(home_page_scene);
        app_stage.show();
    }
    @FXML
    public void retour(ActionEvent event) throws IOException
    {

        Parent home_page_parent = FXMLLoader.load(getClass().getResource("adminAceuil.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        System.out.println("You cliked me !!");
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(home_page_scene);
        app_stage.show();
    }

    @FXML
    private void afficherBiensHandle(ActionEvent event) throws IOException
    {
        Proprietaire p = getProprietaire();
        if (p != null)
        {
            BienProprietaireController.setProprietaire(p);
            StandardController.changerScene(event,"bienProprietaire.fxml",getClass());

        }
        else
            StandardController.showAlert(Alert.AlertType.ERROR,"Aucun proprietaire selectionné","Veuillez selectionner un proprietaire pour afficher ses biens");


    }
    @FXML
    public void deleteHandle()
    {

        Proprietaire p = getProprietaire();
        if (p != null) {
            if (confirmation("Supprimer " ,p.getId()))
            {
                listView.getItems().remove(p.getId());
                ImmoEsi.supprimerProp(p);

            }



        } else // Nothing Selected
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            //alert.initOwner();
            alert.setTitle("Aucun proprietaire selectionné !!");
            alert.setHeaderText("Aucun proprietaire selectionné");
            alert.setContentText("Veuillez selectionner un proprietaire !");
            alert.showAndWait();
        }
    }
    private Boolean confirmation(String s, String selection) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, s + " " + selection + " ?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
        alert.setHeaderText("Confirmation");
        alert.setContentText("Voulez vous confirmer cette operation ?");
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {
            return true;
        } else return false;

    }
    public void selection(ActionEvent event)
    {
        Proprietaire p = getProprietaire();
        if (p== null)
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            //alert.initOwner();
            alert.setTitle("Aucun proprietaire selectionné !!");
            alert.setHeaderText("Aucun proprietaire selectionné");
            alert.setContentText("Veuillez selectionner un proprietaire !");
            alert.showAndWait();
            return;
        }
        else
        AjouterBienController.setProProp(p);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.close();

    }


}

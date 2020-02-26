package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import tp.Poo.ImmoEsi;
import tp.Poo.Proprietaire;

import java.awt.*;
import java.awt.desktop.ScreenSleepEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class AjouterProprietaireController  implements Initializable {

    private Stage dialogStage;
    private Proprietaire proprietaire;
    @FXML
    TextField nom;
    @FXML
    TextField prenom;
    @FXML
    TextField adresse;
    @FXML
    TextField numeroTel;
    @FXML
    TextField email;
    @FXML
    Label msg;
    @FXML
    ImageView image;
    @FXML
    Button retourButton;

    private static  Boolean ajoutBien = false;
    public  static void setAjoutBien(Boolean v)
    {
        ajoutBien = v;
    }

    public  boolean valider()
    {
        if((this.nom.getText().isEmpty())||(this.prenom.getText().isEmpty() )||(this.adresse.getText().isEmpty())||(this.numeroTel.getText().isEmpty()))
        {
            return false;
        }
        else return true;
    }

        @FXML
    public void retour(ActionEvent event) throws IOException {
        System.out.println("You cliked me !!");
        Parent home_page_parent = FXMLLoader.load(getClass().getResource("afficherProp.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(home_page_scene);
        app_stage.show();
        ImmoEsi.afficherProprietaire();

    }
    /*@FXML
    public void retour(ActionEvent event) throws IOException {
        System.out.println("You cliked me !!");
        Parent home_page_parent = FXMLLoader.load(getClass().getResource("adminAceuil.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(home_page_scene);
        app_stage.show();
        ImmoEsi.afficherProprietaire();

    }*/


    /*
    @FXML
    public void submitButton(){
        if(nom.getText().isEmpty()){
            showAlert(Alert.AlertType.ERROR,"F")
        }
    }*/
/*

    @FXML
    public void ajouterProp() {

        if (valider())
        {
            String id = new String("Prop"+ Integer.toString(ImmoEsi.listeProprietaires.size()));
            Proprietaire p = new Proprietaire(this.nom.getText(),this.prenom.getText(),this.email.getText(),this.adresse.getText(),this.numeroTel.getText(),id);
            ImmoEsi.listeProprietaires.add(p);
            ImmoEsi.afficherProprietaire();
            msg.setText("Proprietaire ajouté avec succé ! ");
        }
        else {
            msg.setText("Erreur!!");
        }
        */
/*Parent home_page_parent = FXMLLoader.load(getClass().getResource("adminAceuil.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(home_page_scene);
        app_stage.show();  *//*


    }
*/
    public void setDialogStage(Stage dS)
    {
        this.dialogStage = dS;
    }

    @FXML
    private void hundleConfirmer()
    {
        if (nom.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR,"Erreur formulaire","Veuillez introduire le nom du proprietaire");
            return;
        }
        if(email.getText().isEmpty()){
            showAlert(Alert.AlertType.ERROR,"Erreur formulaire","Veuillez introduire l'émail du proprietaire");
            return;

        }
        if(prenom.getText().isEmpty()){
            showAlert(Alert.AlertType.ERROR,"Erreur formulaire","Veuillez introduire le prénom du proprietaire");
            return;
        }
        if(adresse.getText().isEmpty())
        {
            showAlert(Alert.AlertType.ERROR,"Erreur formulaire","Veuillez introduire l'adressse du proprietaire");
            return;
        }
        if(numeroTel.getText().isEmpty())
        {
            showAlert(Alert.AlertType.ERROR,"Erreur formulaire","Veuillez introduire le numero du téléphone du proprietaire");
            return;

        }
        int indice = ImmoEsi.listeProprietaires.size() + 1;
        String s = new String("prop"+ indice);
        Proprietaire p = new Proprietaire(nom.getText(),prenom.getText(),email.getText(),adresse.getText(),numeroTel.getText(),s);
        if (ImmoEsi.ajouterProprietaire(p))
        {
            showAlert(Alert.AlertType.CONFIRMATION,"Registration Successful!",nom.getText()+ " "+ prenom.getText() +" a été ajouté avec succée");
            ImmoEsi.afficherProprietaire();
        }

        else
            showAlert(Alert.AlertType.ERROR,"Registration Failed","Ce proprietaire existe déja");


    }
    private void showAlert(Alert.AlertType type,String title,String message)
    {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (ajoutBien)
        {
            retourButton.setVisible(false);
        }
        else
        {
            retourButton.setVisible(true);
        }
    }
}

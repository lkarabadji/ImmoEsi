package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.Console;
import java.io.IOException;

public class AdminAceuilController {

    @FXML
    Label Admin;

    @FXML
    public void afficherBiens(ActionEvent event) throws IOException {
        System.out.println("You cliked me !!");
        Parent home_page_parent = FXMLLoader.load(getClass().getResource("afficherBien.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        //zjhdvz
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        //dfghej
        System.out.println("I am afficher Bien and you clicked me !! ");
        app_stage.setScene(home_page_scene);
        app_stage.show();


    }
    @FXML
    public void ajouterP(ActionEvent event) throws IOException
    {
        AjouterProprietaireController.setAjoutBien(false);
        StandardController.changerScene(event,"ajouterProprietaire.fxml",getClass());
    }

    @FXML
    public void retour(ActionEvent event) throws IOException
    {
        StandardController.changerScene(event,"FirstPage.fxml",getClass());
    }

    @FXML

    public void ajouterBien(ActionEvent event) throws IOException
    {
        Parent home_page_parent = FXMLLoader.load(getClass().getResource("ajouterBien.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        System.out.println("You cliked me !!");
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        System.out.println("");
        app_stage.setScene(home_page_scene);
        app_stage.show();

    }
    @FXML
    public void afficherArchives(ActionEvent event) throws IOException
    {
        Parent home_page_parent = FXMLLoader.load(getClass().getResource("afficherArchives.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        System.out.println("You cliked me !!");
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        System.out.println("");
        app_stage.setScene(home_page_scene);
        app_stage.show();

    }
    @FXML
    public void afficherProprietaires(ActionEvent event) throws  IOException
    {
        Parent home_page_parent = FXMLLoader.load(getClass().getResource("afficherProp.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        System.out.println("You cliked me !!");
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        System.out.println("");
        app_stage.setScene(home_page_scene);
        app_stage.show();
    }



    @FXML
    private void afficherMsg(ActionEvent event) throws IOException
    {
        StandardController.addWindow(event,"MessageAgence.fxml",getClass(),Modality.WINDOW_MODAL,"Messages");
    }



}
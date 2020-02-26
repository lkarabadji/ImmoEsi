package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import tp.Poo.ImmoEsi;


import java.io.IOException;

public class Controller {

    @FXML
    TextField username;
    @FXML
    PasswordField password;
    @FXML
    Button next;
    @FXML
    public void firstBottonAction(ActionEvent event) throws IOException {
        //System.out.println("You cliked me !!");
        if (username.getText().isEmpty())
        {
            StandardController.showAlert(Alert.AlertType.ERROR,"Username","Veuillez introduire le nom d'utilisateur");
            return;
        }
        if(password.getText().isEmpty())
        {
            StandardController.showAlert(Alert.AlertType.ERROR,"Password","Veuillez introduire le mot de passe");
            return;
        }

        if (password.getText().equals(ImmoEsi.getCode()) && username.getText().equals(ImmoEsi.getAdmin()))
        {
            ControllerBien.setAdmin(true);
            StandardController.showAlert(Alert.AlertType.INFORMATION,"Connected as admin","Bienvenu ! ");
            StandardController.changerScene(event,"adminAceuil.fxml",getClass());
        }
        else
        {
            StandardController.showAlert(Alert.AlertType.ERROR,"Mot de passe incorrect!","Mot de passe incorrect ,Veuillez reessayer !");
        }


    }
    @FXML
    public void secondBottonAction(ActionEvent event) throws IOException {
        ControllerBien.setAdmin(false);
        Parent home_page_parent = FXMLLoader.load(getClass().getResource("afficherBien.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(home_page_scene);
        app_stage.show();

    }
}

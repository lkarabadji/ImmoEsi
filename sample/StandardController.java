package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class StandardController {

    public static Boolean confirmation(String action, String selection) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, action + " " + selection + " ?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
        alert.setHeaderText("Confirmation");
        alert.setContentText("Voulez vous confirmer cette operation ?");
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {
            return true;
        } else return false;

    }

    public static void showAlert(Alert.AlertType type, String title, String message)
    {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void changerScene(ActionEvent event, String fichierfxml,Class c) throws IOException
    //getClass()
    {

        Parent home_page_parent = FXMLLoader.load(c.getResource(fichierfxml));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(home_page_scene);
        app_stage.show();

    }

    public static void addWindow(ActionEvent event,String fxmlFile,Class c,Modality modality,String title)throws IOException
    {
        AfficherProp.setAjoutBien(true);
        Parent home_page_parent = FXMLLoader.load(c.getResource(fxmlFile));

        Scene home_page_scene = new Scene(home_page_parent);

        //new Window
        Stage newWindow = new Stage();
        newWindow.setTitle(title);
        newWindow.setScene(home_page_scene);
        // modality
        newWindow.initModality(modality);
        newWindow.initOwner((Stage) ((Node) event.getSource()).getScene().getWindow());
        newWindow.show();
    }
}

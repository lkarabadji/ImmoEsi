package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import tp.Poo.Proprietaire;

import java.beans.EventHandler;
import java.net.URL;
import java.util.ResourceBundle;

public class CoorPropCrontroller  implements Initializable {

    static  private Proprietaire p = null;
    public static void setProp(Proprietaire prp)
    {
        p = prp;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (p != null)
        {

            this.nom.setText(p.getNom());
            this.adresse.setText(p.getAdresse());
            this.prenom.setText(p.getPrenom());
            this.numtel.setText(p.getNumTel());
            this.email.setText(p.getEmail());
        }

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
    private void handleCancel(ActionEvent event)
    {
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.close();
    }
}

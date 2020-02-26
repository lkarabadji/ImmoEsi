package sample;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import tp.Poo.*;

public class ContactController implements Initializable {

	Bien bien;

    @FXML
    private TextArea message;

    @FXML
    private Button retourButton;

    @FXML
    private Button envoyerButton;

    @FXML
    private Button afficherButton;


    @FXML
    void EnvoyerMessage(ActionEvent event) {
    	String Message = message.getText();
    	ImmoEsi.envoyerMessage(Message,this.bien);
    	if (StandardController.confirmation("envoyer","message"))
        {
            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            app_stage.close();
        }
    }


    @FXML
    void Retour(ActionEvent event) throws IOException {
    	/*FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/TerraiXml.fxml"));
		Parent root = loader.load();
		Scene scene = new Scene(root);
    	Stage StagePage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		StagePage.setScene(scene);
		StagePage.show();*/

    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

}

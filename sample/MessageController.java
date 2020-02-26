package sample;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

import javafx.stage.Stage;
import tp.Poo.ImmoEsi;

public class MessageController implements Initializable {

    @FXML
    private ListView<String> listeMessages;

    @FXML
    private Button retourButton;




    static String[] Messages = ImmoEsi.boiteMessages;

    public void afficherMessages(){
    	for (int i=0; i< ImmoEsi.getNbMsg(); i++) {
    		listeMessages.getItems().add(Messages[i]);
    			}
    	
    }
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.afficherMessages();
		
	}


    @FXML
    private  void Retour(ActionEvent event)
    {
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.close();
    }



}


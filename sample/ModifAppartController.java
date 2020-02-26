package sample;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.SortedSet;
import java.util.TreeSet;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import tp.Poo.*;

public class ModifAppartController implements Initializable{

	private TreeSet<String> Negoc = new TreeSet<String>();
	private TreeSet<String> Typeappartement = new TreeSet<String>();

	private static Appartement appart = null;

	public static void setAppart(Appartement apr){appart = apr;}

    public Appartement getAppart() {
		return this.appart;
	}


    @FXML
    private TextField Prix;

    @FXML
    private Button validerButton;

    @FXML
    private TextField Adresse;

    @FXML
    private Button afficherButton1;

    @FXML
    private TextArea descriptif;

    @FXML
    private ChoiceBox neg;

    @FXML
    private CheckBox ascenseur;

    @FXML
    private ChoiceBox typeappart;

    @FXML
    private CheckBox meuble;

    @FXML
    private ChoiceBox Transac;


	@FXML
	void Retour(ActionEvent event) throws IOException{
		StandardController.changerScene(event,"afficherBien.fxml",getClass());

	}
    @FXML
    void Afficher(ActionEvent event) throws IOException {

    	this.Modif(event);
    	Appartement temp = this.appart;
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("AppartXml.fxml"));
		Parent root = loader.load();
		Scene scene = new Scene(root);
		ModifAppartController controller = loader.getController();
		controller.setAppart(temp);
    	Stage StagePage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		StagePage.setScene(scene);
		StagePage.show();

    }

    @FXML
    void Modif(ActionEvent event) throws IOException {
    	String adr = Adresse.getText();
    	String negociable="" ;
    	if (neg.getSelectionModel().getSelectedItem()!=null){
    	 negociable = neg.getSelectionModel().getSelectedItem().toString(); }
    	String typeappar ="";

    	if(typeappart.getSelectionModel().getSelectedItem()!=null){
    		typeappar =typeappart.getSelectionModel().getSelectedItem().toString();
    	}

    	String transac="" ;
    	if(Transac.getSelectionModel().getSelectedItem()!=null){
    		transac =Transac.getSelectionModel().getSelectedItem().toString();
    	}

    	String prix = Prix.getText();
    	String desc = descriptif.getText();

    	if(adr.compareTo("")!=0){
    	appart.setAdresse(adr);
    	}
    	if(transac.compareTo("")!=0){
        	appart.setTransac(Transaction.valueOf(transac.toUpperCase()));
        	}
    	if(typeappar.compareTo("")!=0){
        	appart.setTypeAppart(typeappar);
        	}
    	if(ascenseur.isSelected()){
    		appart.setAscenseur();
        	}
    	if(meuble.isSelected()){
    		appart.setMeuble(true);
        	}
    	if(prix.compareTo("")!=0){
        	appart.setPrix(Double.parseDouble(prix));
        	}
    	if(negociable.compareTo("")!=0){
    		if (negociable.compareTo("Oui")==0)
        	appart.setNegociable(true);
    		else
    			this.appart.setNegociable(false);
        	}
    	if(desc.compareTo("")!=0){
        	this.appart.ajouterDescriptif(desc);
        	}
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		Negoc.add("Oui"); Negoc.add("Non");
		Typeappartement.add("SIMPLEXE"); Typeappartement.add("DUPLEXE"); Typeappartement.add("STUDIO");
		neg.getItems().setAll(Negoc);
		typeappart.getItems().setAll(Typeappartement);
		Transac.getItems().setAll(Transaction.values());
		Transac.getSelectionModel().select(appart.getTransacS());
		Prix.setText(Double.toString(appart.getPrix()));
		Adresse.setText(appart.getAdresse());
	}
}

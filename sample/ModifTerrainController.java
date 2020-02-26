package sample;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.TreeSet;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import tp.Poo.*;
public class ModifTerrainController implements Initializable  {
	
	private TreeSet<String> Negoc = new TreeSet<String>();

	private static Terrain terrain =null;
	public static void setTerrain(Terrain t){terrain =t;}
	
	  @FXML
	    private TextField Superficie;

	    @FXML
	    private TextField Facades;

	    @FXML
	    private TextField Prix;

	    @FXML
	    private Button validerButton;

	    @FXML
	    private TextField Adresse;

	    @FXML
	    private Button afficherButton1;

	    @FXML
	    private TextField statut;

	    @FXML
	    private ChoiceBox<Transaction> Transac;

	    @FXML
	    private ChoiceBox<String> Neg;

	    @FXML
	    private Button retourButton;

	    @FXML
	    private TextArea descriptif;

  
    @FXML
    void Afficher(ActionEvent event) throws IOException {

    	this.Modif(event);
    	Terrain temp = this.terrain;
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/TerraiXml.fxml"));
		Parent root = loader.load();
		Scene scene = new Scene(root);
		TerrainController controller = loader.getController();
		controller.setTerrain(temp);
    	Stage StagePage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		StagePage.setScene(scene);
		StagePage.show();

    }

    @FXML
    void Modif(ActionEvent event) {
    	
    	String adr = Adresse.getText();
    	String sup = Superficie.getText();
    	String Statut = statut.getText();
    	String facade = Facades.getText();
    	String prix = Prix.getText();
    	String desc = descriptif.getText();
    	String transac = "";
    	if(Transac.getSelectionModel().getSelectedItem()!=null){
    		transac =Transac.getSelectionModel().getSelectedItem().toString();
    	}
    	
       	String negociable="";
    	if (Neg.getSelectionModel().getSelectedItem()!=null){
       	 negociable = Neg.getSelectionModel().getSelectedItem().toString(); }
       	String typeappar ="";

    	if(adr.compareTo("")!=0){
    	this.terrain.setAdresse(adr);
    	}
    	
    	if(sup.compareTo("")!=0){
        	this.terrain.setSuperficie(Double.parseDouble(sup));
        	}
    	if(transac.compareTo("")!=0){
        	this.terrain.setTransac(Transaction.valueOf(transac));
        	}
    	if(Statut.compareTo("")!=0){
        	this.terrain.setStatutJuridique(Statut);
        	}
    	if(facade.compareTo("")!=0){
    		
        	this.terrain.setNbFacades(Integer.parseInt(facade));
        	}
    	if(prix.compareTo("")!=0){
        	terrain.setPrix(Double.parseDouble(prix));
        	}
    	if(negociable.compareTo("")!=0){
    		if (negociable.toLowerCase().compareTo("oui")==0)
        	this.terrain.setNegociable(true);
    		else
    			this.terrain.setNegociable(false);
        	}
    	if(desc.compareTo("")!=0){
        	this.terrain.ajouterDescriptif(desc);
        	}
    }

    @FXML
    void Retour(ActionEvent event) throws IOException{
    	StandardController.changerScene(event,"afficherBien.fxml",getClass());

    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Negoc.add("Oui"); Negoc.add("Non");
		Neg.getItems().setAll(Negoc);
		Transac.getItems().setAll(Transaction.values());
	}
}

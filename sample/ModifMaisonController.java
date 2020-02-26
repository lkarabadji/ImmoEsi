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
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import tp.Poo.*;
public class ModifMaisonController implements Initializable{

	private TreeSet<String> Negoc = new TreeSet<String>();
	private static   Maison maison = null;
	public static void setMaison(Maison m){maison = m;}

	  @FXML
	    private TextField Superficie;

	    @FXML
	    private TextField Etage;

	    @FXML
	    private TextField Prix;

	    @FXML
	    private Button validerButton;

	    @FXML
	    private TextField Adresse;

	    @FXML
	    private CheckBox Jardin;

	    @FXML
	    private CheckBox Piscine;

	    @FXML
	    private CheckBox Garage;

	    @FXML
	    private Button afficherButton1;

	    @FXML
	    private ChoiceBox<String> Neg;

	    @FXML
	    private ChoiceBox<Transaction> Transac;

	    @FXML
	    private Button retourButton;
	    
	    @FXML
	    private TextArea descriptif;


    @Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		Negoc.add("Oui"); Negoc.add("Non");
		Neg.getItems().setAll(Negoc);
		Transac.getItems().setAll(Transaction.values());
	}

    @FXML
    void Afficher(ActionEvent event) throws IOException {
    	this.Modif(event);
    	Maison temp = this.maison;
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("MaisonXml.fxml"));
		Parent root = loader.load();
		Scene scene = new Scene(root);
		MaisonController controller = loader.getController();
		controller.setMaison(temp);
    	Stage StagePage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		StagePage.setScene(scene);
		StagePage.show();

    }

    @FXML
    void Modif(ActionEvent event) {
    	if(StandardController.confirmation("Modifier","maison"))
		{
			String adr = Adresse.getText();
			String sup = Superficie.getText();
			String etage = Etage.getText();
			String desc = descriptif.getText();

			String transac = "";
			if(Transac.getSelectionModel().getSelectedItem()!=null){
				transac =Transac.getSelectionModel().getSelectedItem().toString();
			}

			String prix = Prix.getText();
			String negociable="";
			if (Neg.getSelectionModel().getSelectedItem()!=null){
				negociable = Neg.getSelectionModel().getSelectedItem().toString(); }
			String typeappar ="";


			if(adr.compareTo("")!=0){
				this.maison.setAdresse(adr);
			}

			if(transac.compareTo("")!=0){
				this.maison.setTransac(Transaction.valueOf(transac.toUpperCase()));
			}
			if(sup.compareTo("")!=0){
				this.maison.setSuperficie(Double.parseDouble(sup));
			}
			if(etage.compareTo("")!=0){
				this.maison.setNbetages(Integer.parseInt(etage));
			}
			if(Jardin.isSelected()){
				this.maison.setJardin();
			}
			if(Piscine.isSelected()){
				maison.setPiscine();
			}
			if(Garage.isSelected()){
				this.maison.setGarage();
			}

			if(prix.compareTo("")!=0){
				this.maison.setPrix(Double.parseDouble(prix));
			}

			if(negociable.compareTo("")!=0){
				if (negociable.toLowerCase().compareTo("oui")==0)
					this.maison.setNegociable(true);
				else
					maison.setNegociable(false);
			}
			if(desc.compareTo("")!=0){
				this.maison.ajouterDescriptif(desc);
			}
		}

    }
	  @FXML
	    void Retour(ActionEvent event) throws IOException {
    		StandardController.changerScene(event,"afficherBien.fxml",getClass());

	    }
}

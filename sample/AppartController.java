
package sample;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import tp.Poo.*;
public class AppartController implements Initializable{

	private Bien bienSelect;
	private static boolean allowRetour = false;
	public static void setAllowRetour(Boolean v){allowRetour= v;}
	//private Proprietaire p = new Proprietaire(null, null, null, null, null);
	private static Appartement appart = null;

	@FXML
		Button retour;
	@FXML
    private Label typeBien;

    @FXML
    private Label Adresse;

    @FXML
    private Label Wilaya;

    @FXML
    private Label Superficie;

    @FXML
    private Label nbEtages;

    @FXML
    private Label Transaction;

    @FXML
    private Label Prix;

    @FXML
    private Button AffichButton;

    @FXML
    private Label negociable;

    @FXML
    private Label ascenseur;

    @FXML
    private Label meuble;

    @FXML
    private Label typeappart;

    @FXML
    private TextArea descriptif;


    @Override
	public void initialize(URL location, ResourceBundle resources) {
		if (appart != null)
		{
			AfficherDetails();
			retour.setVisible(allowRetour);
		}
	}
    
   // @FXML
    void AfficherDetails() {
		//String type =bienSelect.getClass().getSimpleName() ; 
    	typeBien.setText(this.appart.getClass().getSimpleName());
    	Transaction.setText(String.valueOf(this.appart.getTransaction()));
    	Wilaya.setText(this.appart.getwilaya().toString());
    	Adresse.setText(this.appart.getAdresse());
    	nbEtages.setText(String.valueOf(this.appart.getEtage()));
    	Superficie.setText(String.valueOf(this.appart.getsuperficie()));
    	Prix.setText(String.valueOf(this.appart.getPrix()));
    	descriptif.setText(this.appart.getDescriptif());
    	
    	if (this.appart.getNegociable())
    		negociable.setText("Bien negociable");
    	else 
    		negociable.setText("Non negociable");

    	ascenseur.setVisible(appart.getAscenseur());
    	meuble.setVisible(appart.isMeuble());

    	
    	if ((this.appart.getTypeAppart()!=null)){
    	typeappart.setText(this.appart.getTypeAppart().toString());
    	}
    	else 
    		typeappart.setText("/");
    		

    }
    
    public Bien getAppart() {
		return this.appart;
	}

	public static void setAppart(Appartement appart2) {
		AppartController.appart = appart2;
	}

	public void setbienSelect(Bien bien) {
		this.bienSelect = bien;
	}

	public String TypeBienSelect(){
		 return this.appart.getClass().getSimpleName();
	}

	@FXML
	private void retour(ActionEvent event)throws IOException
	{
		StandardController.changerScene(event,"afficherBien.fxml",getClass());
	}

}

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
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import tp.Poo.*;

public class TerrainController implements Initializable {

	private static boolean allowRetour = false;
	public static void setAllowRetour(Boolean v){allowRetour= v;}
	//private Proprietaire p = new Proprietaire(null, null, null, null, null);
	private  static Terrain terrain = null;
	public static  void setTerrain(Terrain t)
	{terrain = t;
	}
	@FXML
		private Button retour;
	 @FXML
	    private Label typeBien;

	    @FXML
	    private Label Adresse;

	    @FXML
	    private Label Wilaya;

	    @FXML
	    private Label Superficie;

	    @FXML
	    private Label nbFacades;

	    @FXML
	    private Label Transaction;

	    @FXML
	    private Label Prix;

	    @FXML
	    private Button AffichButton;

	    @FXML
	    private Label Negociable;

	    @FXML
	    private TextArea descriptif;
	    @FXML
	    private Label statut;
	    @FXML
	    private Button contacterButton;

/*    @FXML
    void AfficherDetails(ActionEvent event) {

    	typeBien.setText(terrain.getClass().getSimpleName());
    	Transaction.setText(String.valueOf(terrain.getTransaction()));
    	Wilaya.setText(terrain.getwilaya().toString());
    	Adresse.setText(terrain.getAdresse());
    	nbFacades.setText(String.valueOf(terrain.getNbFacades()));
    	Superficie.setText(String.valueOf(terrain.getsuperficie()));
    	Prix.setText(String.valueOf(terrain.getPrix()));
    	descriptif.setText(this.terrain.getDescriptif());
    	statut.setText(this.terrain.getStatutJuridique());

    	if (terrain.getNegociable())
    		Negociable.setText("Bien negociable");
    	else
    		Negociable.setText("Non negociable");
    }*/

	@FXML
	private  void retour(ActionEvent event) throws IOException
	{
		StandardController.changerScene(event,"afficherBien.fxml",getClass());
	}
    @FXML
    void Contacter(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/Contact.fxml"));
		Parent root = loader.load();
		Scene scene = new Scene(root);
    	Stage StagePage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		StagePage.setScene(scene);
		StagePage.show();
		}

    public Bien getTerrain() {
		return this.terrain;
	}


	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		if (terrain != null)
		{
			typeBien.setText(terrain.getClass().getSimpleName());
			Transaction.setText(String.valueOf(terrain.getTransaction()));
			Wilaya.setText(terrain.getwilaya().toString());
			Adresse.setText(terrain.getAdresse());
			nbFacades.setText(String.valueOf(terrain.getNbFacades()));
			Superficie.setText(String.valueOf(terrain.getsuperficie()));
			Prix.setText(String.valueOf(terrain.getPrix()));
			descriptif.setText(this.terrain.getDescriptif());
			statut.setText(this.terrain.getStatutJuridique());

			if (terrain.getNegociable())
				Negociable.setText("Bien negociable");
			else
				Negociable.setText("Non negociable");
		}
			retour.setVisible(allowRetour);

		}

}


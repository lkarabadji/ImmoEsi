package sample;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import tp.Poo.*;

import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.SortedSet;

public class BienProprietaireController implements Initializable {
    static private Proprietaire proprietaire = null;

    public static void setProprietaire(Proprietaire proprietaire) {
        BienProprietaireController.proprietaire = proprietaire;
    }
    @FXML
    ListView viewBienP = new ListView();
    @FXML

    Label prop;
    @FXML
    Label prix;
    @FXML
    Button supprimerButton;
    @FXML
    Button archiverButton;


    @FXML
    private Label tran;
    @FXML
    Label adress;
    @FXML
    Label typeBien;

    private SortedSet<Bien>  list = proprietaire.getListeBienP();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for(Bien b : list)
        {
            viewBienP.getItems().add(b.getId());
        }
      // viewBienP.getItems().setAll(proprietaire.getListeBienP();
        viewBienP.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

    }

    @FXML
    public void retour(ActionEvent event) throws IOException {
        AfficherProp.setAjoutBien(false);
        StandardController.changerScene(event,"afficherProp.fxml",getClass());
    }


    @FXML
    public Bien getBien() {
        ObservableList liste2 = viewBienP.getSelectionModel().getSelectedItems();
        Bien b = null;
        for (Object item : liste2) {
            Boolean found = false;
            String s = (String) item;
            Iterator<Bien> iter = ImmoEsi.listeBiens.iterator();
            while (iter.hasNext() && !(found)) {
                b = iter.next();
                if (b.getId().compareTo(s) == 0) {
                    found = true;
                }

            }
        }

        if (b != null) {
            //
            this.prix.setText(String.valueOf(b.getPrix()));
            this.adress.setText(b.getAdresse());
            this.prop.setText(b.getProprietaire().getNom() + " " + b.getProprietaire().getPrenom());
            this.typeBien.setText(b.getClass().getSimpleName());

            String s = b.getTransacS().toLowerCase();
            System.out.println(s);
            this.tran.setText(s);
        }

        return b;


    }

    @FXML
    private void handleDeleteBien() {
        Bien b = getBien();
        if (b != null) {
            if (StandardController.confirmation("Supprimer",b.getId()))
            {
                viewBienP.getItems().remove(b.getId());
                ImmoEsi.supprimerBien(b);
            }



        } else // Nothing Selected
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            //alert.initOwner();
            alert.setTitle("Aucun bien selectionné !!");
            alert.setHeaderText("Aucun bien selectionné");
            alert.setContentText("Veuillez selectionner un bien !");
            alert.showAndWait();
        }
    }

    @FXML
    private void handleArchiverBien() {
        Bien b = getBien();
        if (b != null) {
            if( StandardController.confirmation("Archiver",b.getId())) {
                viewBienP.getItems().remove(b.getId());
                ImmoEsi.archiverBien(b);

            }

        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            //alert.initOwner();
            alert.setTitle("Aucun bien selectionné !!");
            alert.setHeaderText("Aucun bien selectionné");
            alert.setContentText("Veuillez selectionner un bien !");
            alert.showAndWait();
        }


    }

    @FXML
    private void details(ActionEvent event) throws IOException {
        Bien b = getBien();
        if (b == null) {
            StandardController.showAlert(Alert.AlertType.ERROR, "Aucun bien selectionné", "Veuillez au moin selectionner un bien");
            return;
        } else {
            if (b.getClass().getSimpleName().equals("Maison")) {
                MaisonController.setAllowRetour(false);
                MaisonController.setMaison((Maison) b);
                StandardController.addWindow(event, "MaisonXML.fxml", getClass(), Modality.WINDOW_MODAL, "Afficher les details");

            } else if (b.getClass().getSimpleName().equals("Appartement")) {
                AppartController.setAllowRetour(false);
                AppartController.setAppart((Appartement) b);
                StandardController.addWindow(event, "AppartXml.fxml", getClass(), Modality.WINDOW_MODAL, "Afficher les details");

            } else if (b.getClass().getSimpleName().equals("Terrain")) {
                TerrainController.setAllowRetour(false);
                TerrainController.setTerrain((Terrain) b);
                StandardController.addWindow(event, "TerrainXml.fxml", getClass(), Modality.WINDOW_MODAL, "Afficher les details");

            }

        }


    }
}

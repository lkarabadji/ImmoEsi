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

public class AfficherArchivesController implements Initializable {


    @FXML
    Label trans;
    @FXML
    Label typeBien;
    @FXML
    Label adresse;
    @FXML
    Label prix;
    @FXML
    Label prop;
    @FXML
    ListView listeArchives = new ListView();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for (Bien b : ImmoEsi.BiensArchives) {
            this.listeArchives.getItems().add(b.getId());
        }
        listeArchives.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }

    @FXML
    private void back(ActionEvent event) throws IOException {
        Parent home_page_parent = FXMLLoader.load(getClass().getResource("adminAceuil.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        System.out.println("You cliked me !!");
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(home_page_scene);
        app_stage.show();
    }

    @FXML
    public Bien getBien() {
        ObservableList liste2 = listeArchives.getSelectionModel().getSelectedItems();
        Bien b = null;
        for (Object item : liste2) {
            Boolean found = false;
            String s = (String) item;
            Iterator<Bien> iter = ImmoEsi.BiensArchives.iterator();
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
            this.adresse.setText(b.getAdresse());
            this.prop.setText(b.getProprietaire().getNom() + " " + b.getProprietaire().getPrenom());
            this.typeBien.setText(b.getClass().getSimpleName());
            System.out.println("");
            String s = b.getTransacS().toLowerCase();
            System.out.println();

            this.trans.setText(s);
        }

        return b;


    }

    @FXML
    public void restaurer() {
        Bien b = getBien();
        if (b != null) {
            if (StandardController.confirmation("Restaurer ", b.getId())) {
                listeArchives.getItems().remove(b.getId());
                ImmoEsi.listeBiens.add(b);
                ImmoEsi.BiensArchives.remove(b);
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
    public void supprimer() {

        Bien b = getBien();
        if (b != null) {
            if (StandardController.confirmation("Supprimer deffinitivement ", b.getId())) {

                if (ImmoEsi.BiensArchives.remove(b)) {
                    listeArchives.getItems().remove(b.getId());
                }
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


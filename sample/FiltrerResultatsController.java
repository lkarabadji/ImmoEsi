package sample;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Modality;
import tp.Poo.*;

import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;

public class FiltrerResultatsController implements Initializable {

    @FXML
    ListView listView;

    @FXML
    Label prop;
    @FXML
    Label prix;
    @FXML
    Label tra;
    @FXML
    Label adress;
    @FXML
    Label typeB;

    @FXML
    public void retour(ActionEvent event) throws IOException {
        StandardController.changerScene(event,"filtrer.fxml",getClass());
    }


    @FXML
    public Bien getBien() {
        ObservableList liste2 = listView.getSelectionModel().getSelectedItems();
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
            this.typeB.setText(b.getClass().getSimpleName());

            String s = b.getTransacS().toLowerCase();
            System.out.println(s);
            this.tra.setText(s);
        }

        return b;


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
                StandardController.addWindow(event, "MaisonXML.fxml", getClass(), Modality.WINDOW_MODAL, "Afficher les details");
                MaisonController.setMaison((Maison) b);
            } else if (b.getClass().getSimpleName().equals("Appartement")) {
                AppartController.setAllowRetour(false);
                StandardController.addWindow(event, "AppartXml.fxml", getClass(), Modality.WINDOW_MODAL, "Afficher les details");
                AppartController.setAppart((Appartement) b);
            } else if (b.getClass().getSimpleName().equals("Terrain")) {
                TerrainController.setAllowRetour(false);
                StandardController.addWindow(event, "TerrainXml.fxml", getClass(), Modality.WINDOW_MODAL, "Afficher les details");
                TerrainController.setTerrain((Terrain) b);
            }

        }


    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for(Bien b: FiltrerController.listePrincipale)
        {
            listView.getItems().add(b.getId());
        }
    }
}

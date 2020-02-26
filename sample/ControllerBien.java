package sample;

import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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

public class ControllerBien implements Initializable {

    public static Boolean admin = false;
    public static Boolean rech = false;

    public static void setrech(Boolean b) {
        admin = b;
    }
    public static void setAdmin(Boolean b) {
        admin = b;
    }
    private static SortedSet<Bien> listeInterne = FiltrerController.listePrincipale;
    public  static void setListeInterne(SortedSet<Bien> l)
    {listeInterne = l;}


    private static String s;
    @FXML
    ToolBar adminTools;
    @FXML

    Label prop;
    @FXML
    Label prix;
    @FXML
    Button supprimerButton;
    @FXML
    Button ajouterBienButton;
    @FXML
    Button archiverButton;

    @FXML
    Label email;
    @FXML
    Label adress;
    @FXML
    Label typeBien;
    @FXML
    Button filtrer;

    @FXML
    ListView<String> listView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //ImmoEsi.consulter(ImmoEsi.listeBiens);
        filtrer.setVisible(true);
        if (admin == true) {
            adminTools.setVisible(true);
            supprimerButton.setVisible(true);
        //    ajouterBienButton.setVisible(true);
            archiverButton.setVisible(true);


        } else {
            adminTools.setVisible(false);
            supprimerButton.setVisible(false);
//            ajouterBienButton.setVisible(false);
            archiverButton.setVisible(false);
        }
        if (rech)
        {
            for (Bien b : listeInterne)
            {
                listView.getItems().add(b.getId());

            }

            adminTools.setVisible(false);
            filtrer.setVisible(false);

        }
        else
        {
            for (Bien b : ImmoEsi.listeBiens) {
                listView.getItems().add(b.getId());

            }
        }



        listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

    }

    @FXML
    public void retour(ActionEvent event) throws IOException {
        System.out.println("You cliked me !!");
        if (admin == true) {
            s = "adminAceuil.fxml";
        } else {
            s = "FirstPage.fxml";
        }
        if(rech)
        {
            s = "filtrer.fxml";
        }
        Parent home_page_parent = FXMLLoader.load(getClass().getResource(s));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        System.out.print("");
        app_stage.setScene(home_page_scene);
        app_stage.show();

    }

    @FXML
    private Label tran;

    @FXML
    public Bien getBien() {
        ObservableList liste2 = listView.getSelectionModel().getSelectedItems();
        Bien b = null;
        for (Object item : liste2) {
            Boolean found = false;
            String s = (String) item;
            Iterator<Bien> iter = ImmoEsi.listeBiens.iterator();
            /*if (rech)
            {
                iter = listeInterne.iterator();
            }*/
            while (iter.hasNext() && !(found)) {
                b = iter.next();
                if (b.getId().compareTo(s) == 0) {
                    found = true;
                }

            }
        }

        if (b != null) {
            //
            this.prix.setText(String.valueOf(b.calculerPrix()));
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
    private void handleCoor(ActionEvent event) throws IOException
    {
        Bien b = getBien();
        if (b != null)
        {
            CoorPropCrontroller.setProp(b.getProprietaire());
            StandardController.addWindow(event,"coorProp.fxml",getClass(),Modality.WINDOW_MODAL,"Coordonné du Proprietaire !");

        }
        else{
            StandardController.showAlert(Alert.AlertType.ERROR,"Aucun bien selectionné","Veuillez selectionner un bien !");
        }
    }
    @FXML
    private void handleDeleteBien() {
        Bien b = getBien();
        if (b != null) {
            if (StandardController.confirmation("Supprimer",b.getId()))
            {
                listView.getItems().remove(b.getId());
                ImmoEsi.supprimerBien(b);
                System.out.println("suppr");
                ImmoEsi.consulter(ImmoEsi.listeBiens);
            }



        } else // Nothing Selected
        {
            StandardController.showAlert(Alert.AlertType.ERROR,"Aucun bien selectionné","Veuillez Selectionner un bien pour le supprimer!");
        }
    }

    @FXML
    private void handleArchiverBien() {
        Bien b = getBien();
        if (b != null) {
            if( StandardController.confirmation("Archiver",b.getId())) {
                listView.getItems().remove(b.getId());
                ImmoEsi.archiverBien(b);
                ImmoEsi.listeBiens.remove(b);
            }

        } else {
            StandardController.showAlert(Alert.AlertType.ERROR,"Aucun bien selectionné","Veuillez Selectionner un bien pour l'archiver !");
        }


    }

    @FXML
    private void filtrer(ActionEvent event) throws IOException
    {
        StandardController.changerScene(event,"filtrer.fxml",getClass());
    }
    @FXML
    private void modifierHandler(ActionEvent event) throws IOException
    {
        Bien b = getBien();
        if (b != null)
        {
            if(b.getClass().getSimpleName().equals("Maison"))
            {
                ModifMaisonController.setMaison((Maison)b);
                StandardController.changerScene(event,"ModifMaison.fxml",getClass());

            }
            else if(b.getClass().getSimpleName().equals("Appartement"))
            {
                ModifAppartController.setAppart((Appartement) b);
                StandardController.changerScene(event,"ModifAppart.fxml",getClass());

            }
            else if(b.getClass().getSimpleName().equals("Terrain"))
            {

                ModifTerrainController.setTerrain((Terrain)b);
                StandardController.changerScene(event,"ModifTerrain.fxml",getClass());

            }
        }
        else{
            StandardController.showAlert(Alert.AlertType.ERROR,"Aucun bien selectionné","Veuillez selectionner un bien pour le modifier");
            return;
        }
    }


    @FXML
    private void detailsHandler(ActionEvent event) throws  IOException
    {
        Bien b = getBien();
        if( b == null)
        {
            StandardController.showAlert(Alert.AlertType.ERROR,"Aucun bien selectionné","Veuillez au moin selectionner un bien");
            return;
        }
        else {
            if(b.getClass().getSimpleName().equals("Maison"))
            {
                MaisonController.setMaison((Maison)b);
                MaisonController.setAllowRetour(true);
                StandardController.changerScene(event,"MaisonXML.fxml",getClass());

            }
            else if(b.getClass().getSimpleName().equals("Appartement"))
            {
                AppartController.setAppart((Appartement) b);
                AppartController.setAllowRetour(true);
                StandardController.changerScene(event,"AppartXML.fxml",getClass());

            }
            else if(b.getClass().getSimpleName().equals("Terrain"))
            {
                TerrainController.setTerrain((Terrain)b);
                TerrainController.setAllowRetour(true);
                StandardController.changerScene(event,"TerrainXML.fxml",getClass());

            }

        }
    }

    @FXML
    private void HandleEnvoyermsg( ActionEvent event) throws IOException
    {
        StandardController.addWindow(event,"Contact.fxml",getClass(),Modality.WINDOW_MODAL,"Contacter");

    }
    @FXML
    private void afficherImages(ActionEvent event)throws IOException
    {
        Bien b = getBien();
        if (b != null)
        {
            AfficherImagesController.setBien(b);
            StandardController.addWindow(event,"afficherImages.fxml",getClass(), Modality.WINDOW_MODAL,"Affichages des images");
            //StandardController.changerScene(event,"afficherImages.fxml",getClass());
        }
        else
        {
            StandardController.showAlert(Alert.AlertType.ERROR,"Aucun bien selectionné","Veuillez selectionner un bien !");
        }

    }
}

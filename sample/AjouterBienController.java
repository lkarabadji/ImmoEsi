package sample;

import javafx.collections.ObservableArray;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import tp.Poo.*;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Set;


public class AjouterBienController implements Initializable {
    //static public Bien bien;
    private  static Proprietaire prop;
    private static Set<Image> simages;
    public  static void setProProp(Proprietaire p)
    {
        prop = p;
    }
    @FXML
    VBox imagesVbox = new VBox();
    @FXML
    TextField imagePath;
    @FXML
    CheckBox negociable;
    @FXML
    TextField prix;
    @FXML
    TextField superficie;
    @FXML
    TextField adresse;

    Set<String> listeImage;

    @FXML
    ChoiceBox<Wilaya> wilaya = new ChoiceBox<>();
    @FXML
    ChoiceBox typeBien = new ChoiceBox();
    @FXML
    ChoiceBox<Transaction> transac = new ChoiceBox<>();

    @FXML
    public void valider(ActionEvent event) throws IOException
    {

        if(this.typeBien.getSelectionModel().isEmpty())
        {
            showAlert(Alert.AlertType.ERROR,"Erreur formulaire","Veuillez choisir le type du bien !");
            return;


        }
        if(this.transac.getSelectionModel().isEmpty())
        {
            showAlert(Alert.AlertType.ERROR,"Erreur formulaire","Veuillez choisir la transaction !");
            return;


        }
        if(this.adresse.getText().isEmpty())
        {
            showAlert(Alert.AlertType.ERROR,"Erreur formulaire","Veuillez inserer l'adresse du bien !");
            return;
        }
        if(this.wilaya.getSelectionModel().isEmpty())
        {
            showAlert(Alert.AlertType.ERROR,"Erreur formulaire","Veuillez choisir la wilaya");
            return;

        }
        if(this.prix.getText().isEmpty())
        {
            showAlert(Alert.AlertType.ERROR,"Erreur formulaire","Veuillez introduire le prix du bien !");
            return;
        }
        if(this.superficie.getText().isEmpty())
        {
            showAlert(Alert.AlertType.ERROR,"Erreur formulaire","Veuillez introduire la superficie du bien !");
            return;
        }
        if(prop == null)
        {
            showAlert(Alert.AlertType.ERROR,"Erreur formulaire","Veuillez choisir un proprietaire !");
            return;
        }
        if (typeBien.getSelectionModel().getSelectedItem().toString().equalsIgnoreCase("Maison"))
        {
            AjouterMaisonController.setP(prop);
            AjouterMaisonController.setAdresse(adresse.getText());
            AjouterMaisonController.setPrix(Double.parseDouble(prix.getText()));
            AjouterMaisonController.setSuperficie(Double.parseDouble(superficie.getText()));
            AjouterMaisonController.setTransaction(transac.getSelectionModel().getSelectedItem().toString());
            AjouterMaisonController.setWilaya(wilaya.getSelectionModel().getSelectedItem().toString());
            AjouterMaisonController.setNegociable(negociable.isSelected());
            AjouterMaisonController.setImages(listeImage);
        }
        else if (typeBien.getSelectionModel().getSelectedItem().toString().equalsIgnoreCase("Appartement"))
        {
            AjouterAppartementController.setP(prop);
            AjouterAppartementController.setAdresse(adresse.getText());
            AjouterAppartementController.setPrix(Double.parseDouble(prix.getText()));
            AjouterAppartementController.setSuperficie(Double.parseDouble(superficie.getText()));
            AjouterAppartementController.setTransaction(transac.getSelectionModel().getSelectedItem().toString());
            AjouterAppartementController.setWilaya(wilaya.getSelectionModel().getSelectedItem().toString());
            AjouterAppartementController.setNegociable(negociable.isSelected());
        }
        else  if (typeBien.getSelectionModel().getSelectedItem().toString().equalsIgnoreCase("Terrain"))
        {
            AjouterTerrainController.setP(prop);
            AjouterTerrainController.setAdresse(adresse.getText());
            AjouterTerrainController.setPrix(Double.parseDouble(prix.getText()));
            AjouterTerrainController.setSuperficie(Double.parseDouble(superficie.getText()));
            AjouterTerrainController.setTransaction(transac.getSelectionModel().getSelectedItem().toString());
            AjouterTerrainController.setWilaya(wilaya.getSelectionModel().getSelectedItem().toString());
            AjouterAppartementController.setNegociable(negociable.isSelected());
        }
        showAlert(Alert.AlertType.CONFIRMATION,"champs validés ","Continuez ..");
        System.out.println(typeBien.getSelectionModel().getSelectedItem().toString());
        Suivant(event,goToBien(typeBien.getSelectionModel().getSelectedItem().toString()));

    }
    /*
    public void suivantHandle()
    {

    }
    */
    private void showAlert(Alert.AlertType type, String title, String message)
    {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


    private Boolean confirmation(String s, String selection) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, s + " " + selection + " ?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
        alert.setHeaderText("Confirmation");
        alert.setContentText("Voulez vous confirmer cette operation ?");
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {
            return true;
        } else return false;

    }
    public String goToBien(String s)
    {
        if (s.equalsIgnoreCase("Maison"))
        {
            return  new String("ajouterMaison.fxml");
        }
        else if (s.equalsIgnoreCase("Terrain"))
            return  "ajouterTerrain.fxml";
        else if (s.equalsIgnoreCase("Appartement"))
            return "ajouterAppartement.fxml";
        return null;
    }

    @FXML
    private  void cancel(ActionEvent event)throws  IOException
    {
        if(StandardController.confirmation("jcp","c"))
        {
            StandardController.changerScene(event,"adminAceuil.fxml",getClass());
        }

    }


    public void Suivant(ActionEvent event,String fichierfxml) throws IOException {

        Parent home_page_parent = FXMLLoader.load(getClass().getResource(fichierfxml));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(home_page_scene);
        app_stage.show();

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ArrayList<String> tempBienList = new ArrayList(3);
        tempBienList.add("Maison");
        tempBienList.add("Terrain");
        tempBienList.add("Appartement");
        typeBien.getItems().setAll(tempBienList);
        wilaya.getItems().setAll(Wilaya.values());
        transac.getItems().setAll(Transaction.values());

    }

    @FXML
    public void selProprietaire(ActionEvent event) throws  IOException
    {
        AfficherProp.setAjoutBien(true);
        Parent home_page_parent = FXMLLoader.load(getClass().getResource("afficherProp.fxml"));

        Scene home_page_scene = new Scene(home_page_parent);

        //new Window
        Stage newWindow = new Stage();
        newWindow.setTitle("seletionner un proprietaire");
        newWindow.setScene(home_page_scene);
        // modality
        newWindow.initModality(Modality.WINDOW_MODAL);
        newWindow.initOwner((Stage) ((Node) event.getSource()).getScene().getWindow());
        newWindow.show();
    }
    /*
    @FXML
    private void ajouterImage()
    {
        final FileChooser fileChooser = new FileChooser();
        configuiringFileChooser(fileChooser);
        TextArea textArea = new TextArea();
        textArea.setMinHeight(70);

        Button button = new Button("Select some pictures");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                //app_stage.close();
                fileChooser.showOpenDialog(app_stage);
            }
        });

    }
    /*
    private void configuiringFileChooser(FileChooser fc)
    {
        //title
        fc.setTitle("selectionnez des images pour le bien !");
        //initial directory
        fc.setInitialDirectory(new File(System.getProperty("C:/Users/lina/Desktop")));
        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JPG","*.jpg" ),
                new FileChooser.ExtensionFilter("PNG","*.png")

        );
    }*/

    @FXML
    public void ajouterImage() throws IOException
    {
        if(imagePath.getText().isEmpty())
        {
            StandardController.showAlert(Alert.AlertType.ERROR,"empty path","Veuillez inserer l'emplacement de l'image");
            return;
        }
        Image image = new Image(new File(imagePath.getText()).toURI().toURL().toExternalForm());
        if (image.isError())
        {
            StandardController.showAlert(Alert.AlertType.ERROR,"Erreur chargement message","Reessayer");
            return;
        }
        else {
            ImageView imgV = new ImageView(image);
            imagesVbox.getChildren().add(imgV);
            //listeImage.add(new File(imagePath.getText()).toURI().toURL().toExternalForm());
        }
    }


}

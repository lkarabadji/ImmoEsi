package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import tp.Poo.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.TreeSet;

import static java.lang.Double.parseDouble;

public class AjouterMaisonController  implements Initializable{

    private  static Double  prix,superficie;
    static Boolean echange,negociable;
    private   static String wilaya,adresse,transaction;
    private static String wilayaEch;
    private  static Proprietaire p;
    private static Set<String> images = new TreeSet<>();
    public static void setImages(Set<String> im)
    {
        images = im;
    }

    public static void setNegociable(Boolean val)
    {
        negociable = val;
    }
    public static void setEchange(Boolean val)
    {
        echange = val;
    }
    public static String getWilayaEch() {
        return wilayaEch;
    }
    public static void setP(Proprietaire prop)
    {
        p = prop;
    }

    public static void setPrix(Double prix) {
        AjouterMaisonController.prix = prix;
    }

    public static void setSuperficie(Double superficie) {
        AjouterMaisonController.superficie = superficie;
    }

    public static void setWilaya(String wilaya) {
        AjouterMaisonController.wilaya = wilaya;
    }

    public static void setTransaction(String transaction) {
        AjouterMaisonController.transaction = transaction;
    }

    public static void setAdresse(String adresse) {
        AjouterMaisonController.adresse = adresse;
    }

    public static  void setWilayaEch(String wilayaEch) {
        wilayaEch = wilayaEch;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        finir.setVisible(true);
        wilayaEchange.getItems().setAll(Wilaya.values());
        if (transaction.equalsIgnoreCase("Echange"))
        {
            echangePane.setVisible(true);
        }
        else
             echangePane.setVisible(false);

    }
    static  public Bien bien;
    //Ajouter Maison:
    @FXML
    ChoiceBox wilayaEchange;
    @FXML
    CheckBox piscine;
    @FXML
    CheckBox garage;
    @FXML
    CheckBox jardin;
    @FXML
    CheckBox meuble;
    @FXML
    Pane echangePane;
    @FXML
    TextField superficieHabitable;
    @FXML
    TextField nbPieces;
    @FXML
    TextArea descriptif;
    @FXML
    Button finir;
    @FXML
    public void confirmerMaison()
    {
        if (superficieHabitable.getText().isEmpty())
        {
            showAlert(Alert.AlertType.ERROR,"Erreur formulaire","Veuillez introduire la superficie habitable de la maison");
            return;

        }
        if (transaction.equalsIgnoreCase("echange"))
        {
            if (wilayaEchange.getSelectionModel().isEmpty())
            {
                showAlert(Alert.AlertType.ERROR,"Erreur formulaire","Veuillez choisir la wilaya d'echange !");
                return;
            }
        }
        if (nbPieces.getText().isEmpty())
        {
            showAlert(Alert.AlertType.ERROR,"Erreur formulaire","Veuillez choisir le nombre de pieces !");
            return;
        }

        Double sH = parseDouble(superficieHabitable.getText());
        if (sH.compareTo(superficie) ==1)
        {
            showAlert(Alert.AlertType.ERROR,"Superficie habitable","La superficie habitable de la maison ne peut pas être supérieur a la superficie totale");
            return;

        }

        String s = "bien" + (ImmoEsi.listeBiens.size()+1);
        int nb = Integer.parseInt(nbPieces.getText());
        Bien b = new Maison(transaction,prix,wilaya,p,superficie,adresse,nb,s);
        if( piscine.isSelected())
        {
            ((Maison) b).setPiscine();
        }
        if(jardin.isSelected())
            ((Maison) b).setJardin();
        if(garage.isSelected())
            ((Maison) b).setJardin();
        ((Maison) b).setMeuble(meuble.isSelected());
        if (!(descriptif.getText().isEmpty()))
        {
            b.ajouterDescriptif(descriptif.getText());
        }
        b.setNegociable(negociable);

            /*
            for (URL i : images)
            {
                b.ajouterPhoto(i);
                System.out.println(i);
            }*/



        if (ImmoEsi.listeBiens.add(b))
        {
            showAlert(Alert.AlertType.CONFIRMATION,"Confirmer","Bien ajouté avec succes !");
            finir.setVisible(true);

        }
        else
            showAlert(Alert.AlertType.ERROR,"Bien non insere","ce bien ne peut pas etre inseré");

    }

    private void showAlert(Alert.AlertType type, String title, String message)
    {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    public void retour(ActionEvent event) throws  IOException
    {
        Suivant(event,"ajouterBien.fxml");
    }

    @FXML
    public void cancel(ActionEvent event) throws IOException
    {
        Suivant(event,"adminAceuil.fxml");
    }
    @FXML
    public void finir(ActionEvent event) throws IOException
    {
        StandardController.changerScene(event,"afficherBien.fxml",getClass());
    }



    public void Suivant(ActionEvent event, String fichierfxml) throws IOException {

        Parent home_page_parent = FXMLLoader.load(getClass().getResource(fichierfxml));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(home_page_scene);
        app_stage.show();

    }
}

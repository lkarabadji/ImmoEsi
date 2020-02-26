package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import tp.Poo.*;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.SortedSet;
import java.util.TreeSet;

public class FiltrerController implements Initializable {
    static SortedSet<Bien> liste = new TreeSet<>();
    static SortedSet<Bien> listePrincipale = ImmoEsi.listeBiens;
    //public SortedSet<Bien> getListePrincipale(){return  listePrincipale;}
    @FXML
    TextField nbrPieces;
    @FXML
    ChoiceBox<Transaction> transactionChoiceBox;
    @FXML
    ChoiceBox<Wilaya> wilayaChoiceBox;
    @FXML
    ChoiceBox typeBien;
    @FXML
    TextField prixMin;
    @FXML
    TextField prixMax;
    @FXML
    Pane habitable;
    @FXML
    TextField superficeMin;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ArrayList<String> tempBienList = new ArrayList(3);
        tempBienList.add("Maison");
        tempBienList.add("Terrain");
        tempBienList.add("Appartement");
        System.out.print("");
        typeBien.getItems().setAll(tempBienList);
        System.out.print("");
        wilayaChoiceBox.getItems().setAll(Wilaya.values());
        transactionChoiceBox.getItems().setAll(Transaction.values());
        listePrincipale = ImmoEsi.listeBiens;



    }

    @FXML
    private void lancerRechercheHandle(ActionEvent event) throws IOException
    {
        if ((nbrPieces.getText().isEmpty()) &&(transactionChoiceBox.getSelectionModel().isEmpty()) &&(wilayaChoiceBox.getSelectionModel().isEmpty()) &&(prixMax.getText().isEmpty())&&(prixMax.getText().isEmpty()) && (prixMin.getText().isEmpty())&& (superficeMin.getText().isEmpty()) && (typeBien.getSelectionModel().isEmpty()))
        {
            StandardController.showAlert(Alert.AlertType.ERROR,"aucun critère","Veuillez selectionner au moin un critère");
            return;
        }
        /*
        if (typeBien.getSelectionModel().getSelectedItem().toString().equalsIgnoreCase("Terrain"))
        {
            habitable.setVisible(false);
            Criteres c = new CritereBien();
            liste=  c.rechercher(listePrincipale);
            ((CritereBien) c).setTypeBien("Terrain");
        }*/
        if (!(typeBien.getSelectionModel().isEmpty()))
        {

            Criteres c = new CritereBien();
            ((CritereBien) c).setTypeBien(typeBien.getSelectionModel().getSelectedItem().toString());
            liste=  c.rechercher(listePrincipale);

            listePrincipale = liste;
        }
        if(!(transactionChoiceBox.getSelectionModel().isEmpty()))
        {
            Criteres c = new CritereTransaction();
            ((CritereTransaction) c).setTransaction(transactionChoiceBox.getSelectionModel().getSelectedItem().toString());
            liste=  c.rechercher(listePrincipale);

            listePrincipale = liste;
        }
        if(!(wilayaChoiceBox.getSelectionModel().isEmpty()))
        {
            Criteres c = new CritereWilaya();
            liste = c.rechercher(listePrincipale);
            ((CritereWilaya) c).setW(wilayaChoiceBox.getSelectionModel().toString());
            listePrincipale = liste;
        }
        if (!(prixMin.getText().isEmpty()))
        {
            Criteres c = new CriterePrixMin();
            ((CriterePrixMin) c).setPrixMin(Double.parseDouble(prixMin.getText()));
            liste = c.rechercher(listePrincipale);

            listePrincipale = liste;

        }
        if (!(prixMax.getText().isEmpty()))
        {
            Criteres c = new CriterePrixMax();
            ((CriterePrixMax) c).setPrixMax(Double.parseDouble(prixMax.getText()));
            liste = c.rechercher(listePrincipale);

            listePrincipale = liste;

        }
        if(!(superficeMin.getText().isEmpty()))
        {


                Criteres c = new CritereSuperficieMinimale();
            ((CritereSuperficieMinimale) c).setSuperficie(Double.parseDouble(superficeMin.getText()));
                liste = c.rechercher(listePrincipale);
                listePrincipale = liste;

        }
        if(!(nbrPieces.getText().isEmpty()) && !(typeBien.getSelectionModel().getSelectedItem().toString().equalsIgnoreCase("terrain")))
        {
            Criteres c = new CritereNbrPieces();
            ((CritereNbrPieces) c).setNbrPieces(Integer.parseInt(nbrPieces.getText()));
            liste = c.rechercher(listePrincipale);
            listePrincipale = liste;

        }
        ImmoEsi.consulter(listePrincipale);

        lancer(event);


    }


    private void lancer(ActionEvent event) throws IOException
    {
        //ControllerBien.setListeInterne(this.listePrincipale);
        ControllerBien.setAdmin(false);
        //ControllerBien.setrech(true);
        StandardController.changerScene(event,"filtrerResultats.fxml",getClass());

    }

    @FXML
    private  void retour(ActionEvent event) throws IOException
    {

        StandardController.changerScene(event,"afficherBien.fxml",getClass());
    }

}

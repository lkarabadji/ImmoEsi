package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import tp.Poo.*;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("firstPage.fxml"));
        primaryStage.setTitle("ImmoEsi");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args) {
        ImmoEsi Immoesi = new ImmoEsi();
        Bien Bien1,Bien2,Bien3,Bien4,Bien5,Bien6,Bien7,Bien8;
        Proprietaire prop1 = new Proprietaire("Hamlaoui","Yasmine", "in_ham@esi.dz", "Alger kouba", "05808063","prop1");
        Proprietaire prop2 = new Proprietaire("Karabadji", "Lina", "il_karabadji@esi.dz", "adress", "0550045","prop2");
        Proprietaire prop3 = new Proprietaire("AMMALI","MOUNA","ia_ammali@gmail.com","adress","0550","prop3");
        Proprietaire prop4 = new Proprietaire("MAMMA","Saly","d_li@esi.dz","adress","0550","prop4");

        //public Appartement(String transac,double prix,Proprietaire p, String w, double sup, int nbpieces,int etage)
        Bien1 = new Appartement("VENTE", 4000000, prop2, "Wilaya2", 120,"@1", 4, 1,"bien1");
        //Maison
        Bien2 = new Maison("VENTE", 10000000, "Wilaya3",prop1, 200,"@2", 4,"bien2" );
        ((Maison)Bien2).setJardin();
        Bien3 = new Terrain("VENTE",20000000, "Wilaya1", prop1, 500,"@3",3,"bien3");
         Bien4 = new Appartement("LOCATION", 40000, prop2, "WILAYA3", 100,"@4", 3, 1,"bien4");
        Bien5 = new Maison("LOCATION", 150000, "Wilaya2",prop3, 160,"@5", 4,"bien5");
        ((Maison)Bien5).setPiscine();
        Bien6 = new Appartement("LOCATION", 60000, prop2, "WILAYA3", 50,"@6", 3, 6,"bien6");
        //((Appartement)Bien6).setAppart("STUDIO");
        //Terrain
        Bien7 = new Terrain("ECHANGE",18000000, "WILAYA1", prop1, 650,"@7",1,"bien7");
        ((Terrain)Bien7).setWilayaEchange("WILAYA3");
        Bien8 = new Maison("ECHANGE", 14000000, "WILAYA2",prop2, 200,"@8", 4,"bien8");
        ((Maison)Bien8).setGarage();
        Bien8.setWilayaEchange("WILAYA2");
        //On ajoute les priotietaires  liste des proprietaires de Immoesi
        Immoesi.ajouterProprietaire(prop1);
        Immoesi.ajouterProprietaire(prop2);
        Immoesi.ajouterProprietaire(prop3);
        Immoesi.ajouterProprietaire(prop4);
        //Ajout des Biens  Immo esi
        Immoesi.ajouterBien(Bien1);
        Immoesi.ajouterBien(Bien2);
        Immoesi.ajouterBien(Bien3);
        Immoesi.ajouterBien(Bien4);
        Immoesi.ajouterBien(Bien5);
        Immoesi.ajouterBien(Bien6);
        Immoesi.ajouterBien(Bien7);
        Immoesi.ajouterBien(Bien8);


        launch(args);
    }
}

package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import tp.Poo.Bien;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AfficherImagesController implements Initializable {
    @FXML
    VBox imagesVbox;
    static private Bien b = null;
    static public void setBien(Bien bien)
    {
        b= bien;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (b!= null)
        {
            for(String imagePath: b.getPhotos())
            {
                try{
                    Image image = new Image(new File(imagePath).toURI().toURL().toExternalForm());
                    ImageView imageView = new ImageView(image);
                    imagesVbox.getChildren().add(imageView);
                }
                catch (IOException e)
                {
                    StandardController.showAlert(Alert.AlertType.ERROR,"ERREUR CAHREGMENR IMAGES","IOERROR !");
                    e.printStackTrace();
                }


            }
        }

    }
}

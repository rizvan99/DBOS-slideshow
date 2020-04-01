/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slideshow.multithread;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

/**
 *
 * @author Rizvan
 */
public class SlideshowUIController implements Initializable
{
    List<Image> images = new ArrayList<>();
    private int imageIndex = 0;
    
    @FXML
    private Button btnLoadImages;
    @FXML
    private Button btnNext;
    @FXML
    private Button btnPrevious;
    @FXML
    private Button btnStart;
    @FXML
    private ImageView imageView;
    @FXML
    private Button btnStop;
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }    
    
    //ImageView
    //FileChooser
    //save image into image list
    //execute command

    @FXML
    private void handleLoadImages(ActionEvent event)
    {
        FileChooser fc = new FileChooser();
        fc.setTitle("Choose images for slideshow");
        fc.getExtensionFilters().add(new ExtensionFilter ("Images", "*.img", "*.jpg", "*.jpeg", "*.png"));
        List<File> files = fc.showOpenMultipleDialog(new Stage());
        
        if (files != null)
        {
            for (File file : files)
            {
                images.add(new Image(file.toURI().toString()));
            }
            imageView.setImage(images.get(0));
        }
        
        
        
    }

    @FXML
    private void handleNextImage(ActionEvent event)
    {
        if (!images.isEmpty())
        {
            imageIndex = (imageIndex - 1 + images.size()) % images.size();
            imageView.setImage(images.get(imageIndex));
        }
    }

    @FXML
    private void handlePreviousImage(ActionEvent event)
    {
        if (!images.isEmpty())
        {
            imageIndex = (imageIndex + 1 + images.size()) % images.size();
            imageView.setImage(images.get(imageIndex));
        }
    }

    @FXML
    private void handleStartSlideshow(ActionEvent event)
    {
       
        
        
    }

    @FXML
    private void handleStopSlideshow(ActionEvent event)
    {
    }
    
}

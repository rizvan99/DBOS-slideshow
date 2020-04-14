/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slideshow.multithread;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
    private final List<Image> images = new ArrayList<>();
    private final List<String> filenames = new ArrayList<>();
    private int imageIndex = 0;
    private ExecutorService executor;
    
    
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
    @FXML
    private Label lblTitle;
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        
    }    
    
    
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
                filenames.add(file.getName());
            }
            lblTitle.setText(filenames.get(0).replace(".png", "").replace(".jpg", ""));
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
            lblTitle.setText(filenames.get(imageIndex).replace(".png", "").replace(".jpg", ""));
        }
    }

    @FXML
    private void handlePreviousImage(ActionEvent event)
    {
        if (!images.isEmpty())
        {
            imageIndex = (imageIndex + 1 + images.size()) % images.size();
            imageView.setImage(images.get(imageIndex));
            lblTitle.setText(filenames.get(imageIndex).replace(".png", "").replace(".jpg", ""));
        }
    }

    @FXML
    private void handleStartSlideshow(ActionEvent event)
    {
       Runnable slideshow = new Slideshow(imageView, images, lblTitle, filenames);
       executor = Executors.newSingleThreadExecutor();
       executor.submit(slideshow);
    }

    @FXML
    private void handleStopSlideshow(ActionEvent event)
    {
        executor.shutdownNow();
        images.clear();
    }
   
}

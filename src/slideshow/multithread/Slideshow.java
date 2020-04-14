/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slideshow.multithread;

import java.util.List;
import java.util.concurrent.TimeUnit;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author Rizvan
 */
public class Slideshow implements Runnable
{

    private final long DELAY = 1;
    private int index = 0;
    private ImageView imageView;
    private List<Image> images;
    private Label lblTitle;
    private List<String> filenames;

    public Slideshow(ImageView imageView, List<Image> images, Label title, List<String> filenames)
    {
        this.imageView = imageView;
        this.images = images;
        this.lblTitle = title;
        this.filenames = filenames;
    }

    @Override
    public void run()
    {
        if (!images.isEmpty())
        {
            try
            {
                while (true)
                {
                    Platform.runLater(() ->
                    {
                        imageView.setImage(images.get(index));
                        lblTitle.setText(filenames.get(index).replace(".png", "").replace(".jpg", ""));
                        
                    });
                    index = (index + 1) % images.size();
                    TimeUnit.SECONDS.sleep(DELAY);
                }
            } catch (InterruptedException ex)
            {
                System.out.println("Slideshow was stopped.");
            }
        }
    }

}

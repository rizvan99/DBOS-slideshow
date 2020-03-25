/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slideshow.multithread;

import java.util.List;
import java.util.concurrent.TimeUnit;
import javafx.scene.image.ImageView;

/**
 *
 * @author Rizvan
 */
public class Slideshow
{
    private final long DELAY = 1;
    private int index = 0;
    private ImageView imgView;
    private List<Image> images;

    public Slideshow(ImageView imgView, List<Image> images)
    {
        this.imgView = imgView;
        this.images = images;
    }
    
    //@Override
    public void run(){
        if (!images.isEmpty()){
            try{
                while (true){
                    //imgView.setImage(images.get(index)); 
                    index = (index + 1) % images.size();
                    TimeUnit.SECONDS.sleep(DELAY);                                        
                }
            }
            catch(InterruptedException ex){
                System.out.println("Slideshow was stopped.");
            }       
        }
    }

    
    
    
}

package edu.wakeforest.drone.video.listener;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import de.yadrone.base.IARDrone;
import de.yadrone.base.video.ImageListener;

public class VideoListener extends JFrame{

  public BufferedImage currentImage = null;
  
  public VideoListener(final IARDrone drone){
    super("Listening for Lines");
    
    setSize(640,360);
    setVisible(true);
    
    drone.getVideoManager().addImageListener(new ImageListener() {
        public void imageUpdated(BufferedImage newImage)
        {
            currentImage = newImage;
            SwingUtilities.invokeLater(new Runnable() {
                public void run()
                {
                    repaint();
                }
            });
        }
    });
    
 // close the 
    addWindowListener(new WindowAdapter() {
        public void windowClosing(WindowEvent e) 
        {
            drone.stop();
            System.exit(0);
        }
    });
    
  }
}
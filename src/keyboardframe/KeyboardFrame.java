package keyboardframe;

import javax.swing.JFrame;

import de.yadrone.apps.controlcenter.plugins.keyboard.KeyboardLayoutPanel;
import de.yadrone.base.IARDrone;

public class KeyboardFrame extends JFrame{

  KeyboardLayoutPanel pannel;
  
  public KeyboardFrame(IARDrone drone){
    
    super("Keyboard");    
    
    pannel = new KeyboardLayoutPanel();
    pannel.activate(drone);
    
    this.getContentPane().add(pannel);
    this.pack();
    this.setLocationByPlatform(true);
    this.setVisible(true);
  }
  

}
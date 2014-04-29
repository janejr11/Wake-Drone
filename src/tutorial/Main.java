package tutorial;

import gui.GUI;
import de.yadrone.base.ARDrone;

public class Main {



	public static void main(String args[]){
			ARDrone drone = new ARDrone();

			
			// create GUI
			GUI gui = new GUI(drone);
			GUI.create(gui);
	}
}

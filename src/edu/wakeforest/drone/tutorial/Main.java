package edu.wakeforest.drone.tutorial;

import de.yadrone.base.ARDrone;
import edu.wakeforest.drone.gui.GUI;

public class Main {



	public static void main(String args[]){
			ARDrone drone = new ARDrone();

			
			// create GUI
			GUI gui = new GUI(drone);
			GUI.create(gui);
	}
}

package edu.wakeforest.drone.approachTape;

import de.yadrone.base.ARDrone;

public class ApproachTape {
	// data for the tape location functions
	int width = 200;
	int height = 100;
	int xCenter = width/2;
	int yCenter = height/2;
	// inCenter defined as middle 5th of the frame
	int centerLBound = width*2/5;
	int centerRBound = width*3/5;
	
	// tape location functions
	public boolean tapeCenter(int tapeX){
		if (tapeX > centerLBound && tapeX < centerRBound)
			return true;
		else
			return false;
	}

	public boolean tapeHigh(int tapeY){
		if (tapeY > yCenter)
			return true;
		else
			return false;
	}

	public boolean tapeLow(int tapeY){
		if (tapeY < yCenter)
			return true;
		else
			return false;
	}

	public boolean tapeLeft(int tapeX){
		if (tapeX < xCenter)
			return true;
		else
			return false;
	}

	public boolean tapeRight(int tapeX){
		if (tapeX > xCenter)
			return true;
		else
			return false;
	}
	
	// charge method
	public void charge(ARDrone drone){
		int i=0;
		int j=0; // for delay in turning left when searching
		// give the program 5 tries to approach the tape
		while(i<5){
			
			// if the tape is in the center of the frame, 
			// first level the copter to the tape then move forward 
			// until the tape is out of frame.
			if (tapeCenter()){
				
				// get the copter on the same level as the tape
				if (tapeHigh()){
					while (tapeHigh()){
						drone.up();
					}
				}
				else if (tapeLow()){
					while (tapeLow()){
						drone.down();
					}
				}
				
				// move forward until the tape goes out of the center
				while (tapeCenter()){
					drone.forward();
				}
			}
			
			// if the tape is to the left (but in frame), turn left until the tape is in the center
			else if (tapeLeft()){
				while (tapeLeft()){
					drone.spinLeft();
				}
			}
			
			// if the tape is to the right (but in frame), turn right until the tape is in the center
			else if (tapeRight()){
				while (tapeRight()){
					drone.spinRight();
				}
			}
			
			// if the tape is not visible, rotate until the tape is in the frame
			// then continue until it is in the center of the frame
			// this assumes the tape is level enough to be seen
			else{
				// spin left until the tape is in frame
				while (!tapeLeft()){
					drone.spinLeft();
					// if you passed it and missed it, turn right until its in the center
					if (tapeRight()){
						while (tapeRight()){
							drone.spinRight();
						}
					}
				}
				
				// spin for a few more moments to make sure the tape in now in frame
				j=0;
				while(j<50){
					drone.spinLeft();
				}
				
				// continue spinning until the tape is in the center
				while (tapeLeft()){
					drone.spinLeft();
				}
			}
			i++;
		}
	}
}

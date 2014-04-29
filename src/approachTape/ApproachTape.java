package approachTape;

import video.listener.VideoListener;
import de.yadrone.base.IARDrone;
import edu.wakeforest.drone.image.processing.Coordinates;
import edu.wakeforest.drone.image.processing.DroneImageProcessor;

public class ApproachTape {
	// data for the tape location functions
	static int width;
	static int height;
	static int xCenter;
	static int yCenter;
	// inCenter defined as middle 5th of the frame
	static int centerLBound = width*2/5;
	static int centerRBound = width*3/5;
	static int x,y; //Target Coordinates of X and Y
	static DroneImageProcessor dip;
	
	public static void calibrate(VideoListener vidzzzzz){
		Coordinates size = vidzzzzz.dip.calibrate();
		width = size.x;
		height = size.y;
		xCenter = size.x/2;
		yCenter = size.y/2;
	}
	
	// tape location functions
	public static boolean tapeCenter(int tapeX){
		if (tapeX > centerLBound && tapeX < centerRBound)
			return true;
		else
			return false;
	}

	public static boolean tapeHigh(int tapeY){
		if (tapeY > yCenter)
			return true;
		else
			return false;
	}

	public static boolean tapeLow(int tapeY){
		if (tapeY < yCenter)
			return true;
		else
			return false;
	}

	public static boolean tapeLeft(int tapeX){
		if (tapeX < xCenter)
			return true;
		else
			return false;
	}

	public static boolean tapeRight(int tapeX){
		if (tapeX > xCenter)
			return true;
		else
			return false;
	}
	
	private static void refreshCoords(){
		Coordinates target = dip.processImage();
		x = target.x;//Target Location X
		y = target.y;//Target Location Y
	}
	
	// charge method
	public static void charge(IARDrone drone, VideoListener vids){
		dip = vids.dip;
		refreshCoords();
		int i=0; // number of tries
		int j=0; // for delay in turning left when searching
		// give the program 5 tries to approach the tape
		while(i<5){
			
			// if the tape is in the center of the frame, 
			// first level the copter to the tape then move forward 
			// until the tape is out of frame.
			if (tapeCenter(x)){
				
				// get the copter on the same level as the tape
				if (tapeHigh(y)){
					while (tapeHigh(y)){
						drone.up();
						refreshCoords();
					}
				}
				else if (tapeLow(y)){
					while (tapeLow(y)){
						drone.down();
						refreshCoords();
					}
				}
				
				// move forward until the tape goes out of the center
				while (tapeCenter(x)){
					drone.forward();
					refreshCoords();
				}
			}
			
			// if the tape is to the left (but in frame), turn left until the tape is in the center
			else if (tapeLeft(x)){
				while (tapeLeft(x)){
					drone.spinLeft();
					refreshCoords();
				}
			}
			
			// if the tape is to the right (but in frame), turn right until the tape is in the center
			else if (tapeRight(x)){
				while (tapeRight(x)){
					drone.spinRight();
					refreshCoords();
				}
			}
			
			// if the tape is not visible, rotate until the tape is in the frame
			// then continue until it is in the center of the frame
			// this assumes the tape is level enough to be seen
			else{
				// spin left until the tape is in frame
				while (!tapeLeft(x)){
					drone.spinLeft();
					refreshCoords();
					// if you passed it and missed it, turn right until its in the center
					if (tapeRight(x)){
						while (tapeRight(x)){
							drone.spinRight();
							refreshCoords();
						}
					}
				}
				
				// spin for a few more moments to make sure the tape in now in frame
				j=0;
				while(j<50){
					drone.spinLeft();
					refreshCoords();
				}
				
				// continue spinning until the tape is in the center
				while (tapeLeft(x)){
					drone.spinLeft();
					refreshCoords();
				}
			}
			i++;
		}
	}
}

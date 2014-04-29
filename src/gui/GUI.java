package gui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import keyboardframe.KeyboardFrame;
import video.listener.VideoListener;
import approachTape.ApproachTape;
import de.yadrone.base.ARDrone;
import de.yadrone.base.IARDrone;
import de.yadrone.base.command.CommandManager;
import de.yadrone.base.video.VideoImage;


public class GUI extends JFrame{
	private JLabel label;
	private JButton buttonTakeoff;
	private JButton buttonLand;
	private JButton buttonLeft;
	private JButton buttonRight;
	private JButton buttonForward;
	private JButton buttonBackwards;
	private JButton buttonBegin;
	private JButton buttonCalibrate;
	private IARDrone drone;
	private BufferedImage img;
	private VideoListener vidzzzzz;
	
	//constructor
	public GUI(ARDrone dronea){
		drone = dronea;
		CommandManager cmd = drone.getCommandManager();
		vidzzzzz = new VideoListener(drone);
		new VideoImage();
		new KeyboardFrame(drone);
		
		// build the GUI for the user
		setLayout(new FlowLayout());
		
		
		buttonTakeoff = new JButton("Takeoff");
		add(buttonTakeoff);
		
		buttonLand = new JButton("Land");
		add(buttonLand);
		
		buttonBegin = new JButton("Begin Search");
		add (buttonBegin);
		
		buttonCalibrate = new JButton("Calibrate");
		add (buttonCalibrate);
		
		label = new JLabel("Waiting for user input");
		add(label);
		
		/*
		buttonRight = new JButton("Right");
		add(buttonRight);
		
		buttonLeft = new JButton("Left");
		add(buttonLeft);
		
		buttonForward = new JButton("Forward");
		add(buttonForward);
		
		buttonBackwards = new JButton("Backward");
		add(buttonBackwards);
		*/
		
		
		takeoffe t = new takeoffe();
		buttonTakeoff.addActionListener(t);
		
		lande l = new lande();
		buttonLand.addActionListener(l);
		
		/*
		righte right = new righte();
		buttonRight.addActionListener(right);
		
		lefte left = new lefte();
		buttonLeft.addActionListener(left);
		
		forwarde forward = new forwarde();
		buttonForward.addActionListener(forward);
		
		backwardse backwards = new backwardse();
		buttonBackwards.addActionListener(backwards);
		*/
		
		begine begin = new begine();
		buttonBegin.addActionListener(begin);
		
		calibratee calibrate = new calibratee();
		buttonCalibrate.addActionListener(calibrate);
	}
	
	public class takeoffe implements ActionListener{
		public void actionPerformed(ActionEvent t){
			label.setText("Taking off");
			drone.takeOff();
			
		}
	}
	
	public class lande implements ActionListener{
		public void actionPerformed(ActionEvent l){
			label.setText("Landing");
			drone.landing();
		}
	}
	
	/*
	public class righte implements ActionListener{
		public void actionPerformed(ActionEvent right){
			label.setText("Moving right");
			drone.goRight();
		}
	}
	
	public class lefte implements ActionListener{
		public void actionPerformed(ActionEvent left){
			label.setText("Moving left");
			drone.goLeft();
		}
	}
	
	public class forwarde implements ActionListener{
		public void actionPerformed(ActionEvent forward){
			label.setText("Moving forward");
			drone.forward();
		}
	}
	
	public class backwardse implements ActionListener{
		public void actionPerformed(ActionEvent backwards){
			label.setText("Moving backwards");
			// move drone back
		}
	}
	*/
	
	public class begine implements ActionListener{
		public void actionPerformed(ActionEvent begin){
			label.setText("Beginning search");
			ApproachTape.charge(drone, vidzzzzz);
		}
	}
	
	public class calibratee implements ActionListener{
		public void actionPerformed(ActionEvent calibrate){
			label.setText("Calibrating search");
			ApproachTape.calibrate(vidzzzzz);
		}
	}
	public static void create(GUI gui){
		gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gui.setSize(170,150);
		gui.setVisible(true);
		gui.setTitle("Controller");
	}
		
}


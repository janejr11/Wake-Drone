package de.yadrone.apps.master;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import keyboard.listener.KeyboarFrame;
import de.yadrone.apps.tutorial.TutorialVideoListener;
import de.yadrone.base.ARDrone;
import de.yadrone.base.IARDrone;
import de.yadrone.base.command.CommandManager;
import de.yadrone.base.video.VideoImage;
import de.yadrone.apps.controlcenter.plugins.keyboard.KeyboardCommandManager;


public class Gui extends JFrame{
	private JLabel label;
	private JButton buttonTakeoff;
	private JButton buttonLand;
	private JButton buttonLeft;
	private JButton buttonRight;
	private JButton buttonForward;
	private JButton buttonBackwards;
	private IARDrone drone;
	private BufferedImage img;
	
	//constructor
		public Gui(ARDrone drone){
			CommandManager cmd = drone.getCommandManager();
			new TutorialVideoListener(drone);
			new VideoImage();
			new KeyboardFrame(drone);
			
			// build the GUI for the user
			setLayout(new FlowLayout());
			
			label = new JLabel("Waiting for user input");
			add(label);
			
			buttonTakeoff = new JButton("Takeoff");
			add(buttonTakeoff);
			
			buttonLand = new JButton("Land");
			add(buttonLand);
			
			buttonRight = new JButton("Right");
			add(buttonRight);
			
			buttonLeft = new JButton("Left");
			add(buttonLeft);
			
			buttonForward = new JButton("Forward");
			add(buttonForward);
			
			buttonBackwards = new JButton("Backward");
			add(buttonBackwards);
			
			event1 t = new event1();
			buttonTakeoff.addActionListener(t);
			
			event2 l = new event2();
			buttonLand.addActionListener(l);
			
			event3 right = new event3();
			buttonLand.addActionListener(right);
			
			event4 left = new event4();
			buttonLand.addActionListener(left);
			
			event5 forward = new event5();
			buttonLand.addActionListener(forward);
			
			event6 backwards = new event6();
			buttonLand.addActionListener(backwards);
		}
		
		public class event1 implements ActionListener{
			public void actionPerformed(ActionEvent t){
				label.setText("Taking off");
				drone.takeOff();
				
			}
		}
		
		public class event2 implements ActionListener{
			public void actionPerformed(ActionEvent l){
				label.setText("Landing");
				drone.landing();
			}
		}
		
		public class event3 implements ActionListener{
			public void actionPerformed(ActionEvent right){
				label.setText("Moving right");
				// move drone right
			}
		}
		
		public class event4 implements ActionListener{
			public void actionPerformed(ActionEvent left){
				label.setText("Moving left");
				// move drone left
			}
		}
		
		public class event5 implements ActionListener{
			public void actionPerformed(ActionEvent forward){
				label.setText("Moving forward");
				// move drone forward
			}
		}
		
		public class event6 implements ActionListener{
			public void actionPerformed(ActionEvent backwards){
				label.setText("Moving backwards");
				// move drone back
			}
		}
		
		/*public static void main(String args[]){
			Gui gui = new Gui();
			gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			gui.setSize(200,200);
			gui.setVisible(true);
			gui.setTitle("Controller");
		}*/
		
	}

package aUI;
//Author: Abdullah Ali
//initial date: feb-6-2014
//Last edit date: feb-26-2014 
//GUI packages

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

//File haneling packes
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class gui extends JFrame {
	

//************* Variables *********************************
	private JPanel mousepanel;
	private JLabel statusBar;
	private JLabel clickBar;
//	file is where the output is dumped from this class for the MouseClickModel to access
	public File file;
//	differenceTime will hold the click duration in milliseconds
	public long differenceTime;
	long startTime ; 
	
//*********************************************************
	
	/**
	 * gui constructor
	 */
	public gui (){
		
//		Calling the JFrame constructor since gui extends JFram
		super("aUI");
		
//****************** Writing to external file ****************	
		try{
//			Write to the file
			 File file = new File("output/mouseOutput.arff");
//			Checking if the file doesn't exists 
			 if (!file.exists()) {
				 FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
				 BufferedWriter bw = new BufferedWriter(fw);
//				 Write the header to the newly created file
				 bw.write("@relation mouseOutput\n\n@attribute clickDuration numeric\n@attribute 'able vs not' {ABLE, NOT}\n\n@data");
				 bw.close();
			 }
			
			}
//			Exception handling
			 catch (IOException e) {
					e.printStackTrace();
				}
//*********************************************************		
		
		
//********************GUI look*****************************		
//		creating a new JPanel, setting the background color, and adding it
		mousepanel = new JPanel();
		mousepanel.setBackground(Color.LIGHT_GRAY);
		add(mousepanel, BorderLayout.CENTER);
	
//		Adding the status bar, this will change based on Mouse behaviors
		statusBar = new JLabel("Hello World!");
		add(statusBar, BorderLayout.SOUTH);
		
		clickBar = new JLabel("Time!");
		add(clickBar, BorderLayout.NORTH);
//*********************************************************		

		
//*****************Handling Mouse Events *****************		
//		The handler class listens to mouse events
		HandlerClass handler = new HandlerClass();

//		Adding the mouse listeners to the handler		
		mousepanel.addMouseListener(handler);
		mousepanel.addMouseMotionListener(handler);
//*********************************************************
		
		
	}//end of gui constructor
	
	
	
	/**
	 * The handler class listens to mouse events
	 * 
	 *
	 */
	private class HandlerClass implements MouseListener ,MouseMotionListener {
		
		
		/**
		 * mouseClicked method
		 */
		public void mouseClicked(MouseEvent event){
//			getClickCount: number of quick, consecutive clicks. returns 2 for a double click
//			getXOnScreen: is the absolute position of the mouse from the left side of the screen, not the window
//			getYOnScreen: is the absolute position of the mouse from the top side of the screen, not the window
//			for future use getButton() method returns which button was pressed
//			 System.out.println("Mouse clicked (# of clicks: "
//	                    + event.getClickCount() + ") at:" + event.getXOnScreen() +" x, " + event.getYOnScreen() + " y");
		}
		
		/**
		 * mousePressed method
		 * 
		 */
		public void mousePressed (MouseEvent event){
			statusBar.setText("You Pressed down the mouse");
			java.util.Date pressed= new java.util.Date();
			String h = "You Pressed down the mouse on: " + new Timestamp(pressed.getTime());
			startTime = System.currentTimeMillis();
			
			
		}
		
		/**
		 * MouseReleased method
		 */
		public void mouseReleased (MouseEvent event){
			statusBar.setText("You released the mouse");
			java.util.Date released= new java.util.Date();
			
			long endTime = System.currentTimeMillis();
			long differenceTime = endTime - startTime;
			String h = "You pressed the mouse for: " +differenceTime;
			clickBar.setText(h);
			
			try{
			//Write to the file
			 File file = new File("output/mouseOutput.arff");
			 FileWriter fw = new FileWriter(file.getAbsoluteFile(), false);
			 BufferedWriter bw = new BufferedWriter(fw);
			 bw.write("@relation mouseOutput\n\n@attribute clickDuration numeric\n@attribute 'able vs not' {ABLE, NOT}\n\n@data");
			 bw.write("\n" + differenceTime + ",ABLE");
			 bw.close();
			 MouseClickModel.main(new String[]{"blah"});
			
			}
			
			 catch (IOException e) {
					e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		
		
		/**
		 * mouseEntered method
		 */
		public void mouseEntered (MouseEvent event){
			statusBar.setText("You entered the area");
		}
		
		/**
		 * mouseExitedmethod
		 */
		public void mouseExited (MouseEvent event){
			statusBar.setText("You left the area");
		}
		
		/**
		 * mouseDragged method
		 */
		public void mouseDragged(MouseEvent event){
			
		}
		
		/**
		 * mouseMoved method
		 */
		public void mouseMoved(MouseEvent event){
			
		}
		
	}//end of HandlerClass
}








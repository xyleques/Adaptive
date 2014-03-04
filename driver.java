package aUI;
import java.io.IOException;

// Author: Abdullah Ali
// Date: feb-6-2014
import javax.swing.JFrame;

public class driver {
	public static void main(String[] args) throws IOException{
		
		gui go = new gui();
		go.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		go.setSize(400,300);
		go.setVisible(true);
		
	}
}
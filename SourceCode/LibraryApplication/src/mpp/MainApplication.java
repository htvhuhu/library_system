package mpp;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Toolkit;

import mpp.view.LoginWindow;
import mpp.view.MainWindow;

public class MainApplication {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					setWindowCentered(window);
					window.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public static void setWindowCentered(Component c) {
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		int screenHeight = toolkit.getScreenSize().height;
		int screenWidth = toolkit.getScreenSize().width;
		int windowHeight = c.getSize().height;
		int windowWidth =  c.getSize().width;
		
		c.setLocation((screenWidth-windowWidth)/2, (screenHeight-windowHeight)/2);
	}
}

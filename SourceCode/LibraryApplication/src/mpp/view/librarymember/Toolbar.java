package mpp.view.librarymember;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;


public class Toolbar extends JPanel implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton helloButton;
	private JButton goodbyeButton;
	
	//private StringListener textListener;
	
	public Toolbar() {
		setBorder(BorderFactory.createEtchedBorder());
		
		helloButton = new JButton("Add Member");
		//goodbyeButton = new JButton("Goodbye");
		
		helloButton.addActionListener(this);
		//goodbyeButton.addActionListener(this);
		
		setLayout(new FlowLayout(FlowLayout.LEFT));
		
		//add(helloButton);
		//add(goodbyeButton);
	}
	
	

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton clicked = (JButton)e.getSource();
		
		if(clicked == helloButton) {
			
		}
		else if(clicked == goodbyeButton) {
			
		}
		
	}
}

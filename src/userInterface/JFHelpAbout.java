package userInterface;

import javax.swing.JFrame;
import javax.swing.JTextArea;

//create a class for the About Mohrs Circle
public class JFHelpAbout extends JFrame {
	public JFHelpAbout() {
		JTextArea jta_about = new JTextArea();
		jta_about.setText("Created by Mark Scannell" + "\nVersion 1.0");
		jta_about.setEditable(false);
		add(jta_about);
	}
}

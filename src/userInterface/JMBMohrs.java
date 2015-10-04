package userInterface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

public class JMBMohrs extends JMenuBar {
	public JMBMohrs() {
		//Create the file menu
		JMenu jm_file = new JMenu("File");
		JMenuItem jmi_file_new = new JMenuItem("New");
		jmi_file_new.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
		JMenuItem jmi_file_open = new JMenuItem("Open");
		jmi_file_open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
		JMenuItem jmi_file_save = new JMenuItem("Save");
		jmi_file_save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
		JMenuItem jmi_file_exit = new JMenuItem("Exit");
		jm_file.add(jmi_file_new);
		jm_file.add(jmi_file_open);
		jm_file.add(jmi_file_save);
		jm_file.add(jmi_file_exit);
		
		//Create the help menu
		JMenu jm_help = new JMenu("Help");
		JMenuItem jmi_help_contents = new JMenuItem("Help Contents");
		JMenuItem jmi_help_about = new JMenuItem("About Mohr's Circle");
		jmi_help_about.setAccelerator(KeyStroke.getKeyStroke("F1"));
		jm_help.add(jmi_help_contents);
		jm_help.add(jmi_help_about);
		
		//Create the menu bar
		add(jm_file);
		add(jm_help);
		
		//Create actionlistener when the new file is selected
		jmi_file_new.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("You want to create a new file");
			}
		});
		
		//Create actionlistener when the open file is selected
		jmi_file_open.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("You want to open a file");
			}
		});
		
		//Create actionlistener when the save file is selected
		jmi_file_save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("You want to save a file");
			}
		});
		
		//Create actionlistener when the About the software is selected
		jmi_file_exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		//Create actionlistener when the Help Contents is selected
		jmi_help_contents.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("You seek help");
			}
		});
		
		//Create actionlistener when the About the software is selected
		jmi_help_about.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFHelpAbout jf_about = new JFHelpAbout();
				jf_about.setTitle("Mohrs Circle Help");
				jf_about.setSize(300, 200);
				jf_about.setLocation(200, 200);
				jf_about.setVisible(true);
			}
		});
				
	}//End Constructor JMB_Mohrs()
}

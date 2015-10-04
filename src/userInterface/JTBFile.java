package userInterface;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;

public class JTBFile extends JToolBar {
	private JButton jbt_new = new JButton(new ImageIcon("C:/eclipse/workspace/Mohrs_Circle/images/new_file.gif"));
	private JButton jbt_open = new JButton(new ImageIcon("C:/eclipse/workspace/Mohrs_Circle/images/open_file.gif"));
	private JButton jbt_save = new JButton(new ImageIcon("C:/eclipse/workspace/Mohrs_Circle/images/save_file.gif"));
	private JButton jbt_help = new JButton(new ImageIcon("C:/eclipse/workspace/Mohrs_Circle/images/save_file.gif"));
	
	public JTBFile() {
		setFloatable(true);
		setOrientation(JToolBar.HORIZONTAL);
		add(jbt_new);
		add(jbt_open);
		add(jbt_save);
		add(jbt_help);
		jbt_new.setBorderPainted(false);
		jbt_open.setBorderPainted(false);
		jbt_save.setBorderPainted(false);
		jbt_help.setBorderPainted(false);
		
		Action exitAction = new AbstractAction("Exit", new ImageIcon("C:/eclipse/workspace/Mohrs_Circle/images/save_file.gif")) {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		};
		
		add(exitAction);
	}
}

package userInterface;

import java.awt.Color;

import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

public class JTAMessage extends JTextArea {
	public JTAMessage() {
		setRows(5);
		setBorder(new TitledBorder("Warnings"));
		setBackground(Color.WHITE);
		setForeground(Color.RED);
		setEditable(false);
	}
}

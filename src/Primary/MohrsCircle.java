package Primary;

import java.awt.*;
import java.awt.event.*;
import java.text.*;
import java.util.*;

import javax.swing.*;
import javax.swing.border.*;

import userInterface.JFHelpAbout;
import userInterface.JFMohrsCircle;
import userInterface.JMBMohrs;
import userInterface.JPCircle;
import userInterface.JPSquare;
import userInterface.JTAMessage;
import userInterface.JTBFile;
import userInterface.JTFResults;

public class MohrsCircle {
	
	public static void main(String[] args) {
		JFMohrsCircle jf_mohrs = new JFMohrsCircle();
		jf_mohrs.setTitle("Mohrs Circle");
		jf_mohrs.setExtendedState(jf_mohrs.getExtendedState() | JFrame.MAXIMIZED_BOTH);
		jf_mohrs.setSize(1200, 600);
		jf_mohrs.setLocation(50, 50);
		jf_mohrs.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf_mohrs.setVisible(true);
	}//End method main()

}//End class Mohrs_Circle

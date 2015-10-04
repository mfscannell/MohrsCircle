package userInterface;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.text.DecimalFormat;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import Primary.SoilParticle;

public class JFMohrsCircle extends JFrame {
	//Create Java Swing components
		JPCircle jp_circle = new JPCircle();
		JPSquare jp_square = new JPSquare();
		JTAMessage jta_message = new JTAMessage();
		private JButton jbt_solve = new JButton("Solve");
		private JButton jbt_theta_incr = new JButton("+");
		private JButton jbt_theta_decr = new JButton("-");
		private JTextField jtf_sigma_x = new JTextField(5);
		private JTextField jtf_sigma_y = new JTextField(5);
		private JTextField jtf_tau_xy = new JTextField(5);
		private JTextField jtf_theta = new JTextField(5);
		private JTextField jtf_cohesion = new JTextField(5);
		private JTextField jtf_int_friction = new JTextField(5);
		private JTFResults jtf_major_stress = new JTFResults();
		private JTFResults jtf_minor_stress = new JTFResults();
		private JTFResults jtf_sigma_x_prime = new JTFResults();
		private JTFResults jtf_sigma_y_prime = new JTFResults();
		private JTFResults jtf_tau_xy_prime = new JTFResults();
		private JTFResults jtf_sigma_avg = new JTFResults();
		private JTFResults jtf_tau_max = new JTFResults();
		private JRadioButton jrb_soil_yes = new JRadioButton("Yes");
		private JRadioButton jrb_soil_no = new JRadioButton("No");
		private JMBMohrs jmb_mohrs = new JMBMohrs();
		private DecimalFormat fixed_two = new DecimalFormat("#.##");
		private JTBFile jtb_file = new JTBFile();
		
		private SoilParticle sp_mohrs = new SoilParticle();
		
		//JF_Mohrs_Circle constructor
		public JFMohrsCircle() {
			
			setJMenuBar(jmb_mohrs);
			
			//Create a panel containing the input and result text Labels
			JPanel jp_input_jl = new JPanel();
			jp_input_jl.setLayout(new GridLayout(16, 1, 4, 4));
			jp_input_jl.add(new JLabel("STRESS INPUT"));
			jp_input_jl.add(new JLabel("Normal Stress on X-Axis (\u03c3x)"));
			jp_input_jl.add(new JLabel("Normal Stress on Y-Axis (\u03c3y)"));
			jp_input_jl.add(new JLabel("Shear Stress on X-Axis (\u03c4xy)"));
			jp_input_jl.add(new JLabel("Angle of Rotation of Particle"));
			jp_input_jl.add(new JLabel("SOIL PARAMETERS"));
			jp_input_jl.add(new JLabel("Soil Cohesion"));
			jp_input_jl.add(new JLabel("Angle of Internal Friction"));
			jp_input_jl.add(new JLabel("RESULTS"));
			jp_input_jl.add(new JLabel("Major Principle Stress"));
			jp_input_jl.add(new JLabel("Minor Principle Stress"));
			jp_input_jl.add(new JLabel("Normal Stress on X'-Axis (\u03c3x')"));
			jp_input_jl.add(new JLabel("Normal Stress on Y'-Axis (\u03c3y')"));
			jp_input_jl.add(new JLabel("Shear Stress on X'-Axis (\u03c4xy')"));
			jp_input_jl.add(new JLabel("Average Normal Stress (\u03c3avg)"));
			jp_input_jl.add(new JLabel("Maximum Shear Stress (\u03c4max)"));
			//Create a panel containing the input and result text boxes
			JPanel jp_input_jta = new JPanel();
			jp_input_jta.setLayout(new GridLayout(16, 1));
			jp_input_jta.add(new JLabel(""));
			jp_input_jta.add(jtf_sigma_x);
			jp_input_jta.add(jtf_sigma_y);
			jp_input_jta.add(jtf_tau_xy);
			jp_input_jta.add(jtf_theta);
			jp_input_jta.add(new JLabel(""));
			jp_input_jta.add(jtf_cohesion);
			jp_input_jta.add(jtf_int_friction);
			jp_input_jta.add(new JLabel(""));
			jp_input_jta.add(jtf_major_stress);
			jp_input_jta.add(jtf_minor_stress);
			jp_input_jta.add(jtf_sigma_x_prime);
			jp_input_jta.add(jtf_sigma_y_prime);
			jp_input_jta.add(jtf_tau_xy_prime);
			jp_input_jta.add(jtf_sigma_avg);
			jp_input_jta.add(jtf_tau_max);
			
			//Create a panel containing the input text fields and output text fields
			JPanel jp_input_results = new JPanel();
			jp_input_results.add(jp_input_jl);
			jp_input_results.add(jp_input_jta);
			
			//Create a panel containing the solve button
			JPanel jp_buttons_solve = new JPanel();
			jp_buttons_solve.setLayout(new BorderLayout(5, 5));
			jp_buttons_solve.add(new JLabel("Show Mohr-Coulomb Failure Criteria?"), BorderLayout.NORTH);
			jp_buttons_solve.add(jrb_soil_yes, BorderLayout.WEST);
			jp_buttons_solve.add(jrb_soil_no, BorderLayout.EAST);
			jp_buttons_solve.add(jbt_solve, BorderLayout.SOUTH);
			ButtonGroup bg_soil = new ButtonGroup();
			bg_soil.add(jrb_soil_yes);
			bg_soil.add(jrb_soil_no);
			jrb_soil_no.setSelected(true);
			
			//Create a panel containing the theta increment and decriment buttons
			JPanel jp_buttons_theta = new JPanel();
			jp_buttons_theta.setLayout(new BorderLayout(5, 5));
			jp_buttons_theta.add(new JLabel("Adjust Theta"), BorderLayout.NORTH);
			jp_buttons_theta.add(jbt_theta_incr, BorderLayout.EAST);
			jp_buttons_theta.add(jbt_theta_decr, BorderLayout.WEST);
			
			//Create a panel containing the solve button and the theta buttons
			JPanel jp_buttons = new JPanel();
			jp_buttons.setLayout(new BorderLayout(5, 5));
			jp_buttons.add(jp_buttons_solve, BorderLayout.NORTH);
			jp_buttons.add(jp_buttons_theta, BorderLayout.CENTER);
			
			/*--------------------------------------------------------------------------------------------
			 * Create a panel containing the buttons and the square representing the particle under stress
			 * This is done by adding the panel containing the buttons and the panel containing the square
			 *--------------------------------------------------------------------------------------------*/
			JPanel jp_buttons_square = new JPanel();
			jp_buttons_square.setLayout(new BorderLayout());
			jp_buttons_square.add(jp_buttons, BorderLayout.NORTH);
			jp_buttons_square.add(jp_square, BorderLayout.CENTER);
			
			/*-------------------------------------------------------------------------------------------
			 * Create a panel containing the inputs, results, interactive buttons, and square particle
			 * This is done by adding the panel containing the input and results and adding the panel 
			 * containing the buttons and square particle
			 *-------------------------------------------------------------------------------------------*/
			JPanel jp_input_results_button_square = new JPanel();
			jp_input_results_button_square.setLayout(new GridLayout(1, 2));
			jp_input_results_button_square.add(jp_input_results);
			jp_input_results_button_square.add(jp_buttons_square);
			
			/*--------------------------------------------------------------------------------------------
			 * Create a panel containing the inputs, results, interactive buttons, square particle, and
			 * warning message text area
			 *--------------------------------------------------------------------------------------------*/
			JPanel jp_input_results_button_square_message = new JPanel();
			jp_input_results_button_square_message.setLayout(new BorderLayout());
			jp_input_results_button_square_message.add(jp_input_results_button_square, BorderLayout.NORTH);
			jp_input_results_button_square_message.add(jta_message, BorderLayout.CENTER);
			
			setLayout(new BorderLayout(5, 5));
			add(jp_input_results_button_square_message, BorderLayout.WEST);
			add(jp_circle, BorderLayout.CENTER);
			add(jtb_file, BorderLayout.NORTH);
			
			//Create an action listener for when the Show Mohr-Columb Failure Envelope RadioButton is shown
			jrb_soil_yes.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						sp_mohrs.setCohesion(Double.parseDouble(jtf_cohesion.getText()));
						sp_mohrs.setIntFriction(Double.parseDouble(jtf_int_friction.getText()));
						jp_circle.setLineShown(true);
						jp_circle.setDisplayCoord(sp_mohrs.getSigmaX(), sp_mohrs.getSigmaY(), sp_mohrs.getTauXY(),
												  sp_mohrs.getMinorStress(), sp_mohrs.getMajorStress(), sp_mohrs.getSigmaAvg(), sp_mohrs.getTauMax(),
												  sp_mohrs.getSigmaXPrime(), sp_mohrs.getSigmaYPrime(), sp_mohrs.getTauXYPrime(),
												  sp_mohrs.getCohesion(), sp_mohrs.getIntFriction());
						jp_square.setDisplayCoord(sp_mohrs.getTheta());
						jta_message.setText("");
						NumberFormatException except_incorrect_input = new NumberFormatException("Incorrect Input");
						throw except_incorrect_input;
					}
					
					catch (NumberFormatException ex) {
						jta_message.setText("You must enter valid input for STRESS INPUT and SOIL PARAMETERS");
					}
					
				}
			});
			
			//Create an action listener for when the Show Mohr-Columb Failure Envelope RadioButton is not shown
			jrb_soil_no.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					jp_circle.setLineShown(false);
					jp_circle.setDisplayCoord(sp_mohrs.getSigmaX(), sp_mohrs.getSigmaY(), sp_mohrs.getTauXY(),
											  sp_mohrs.getMinorStress(), sp_mohrs.getMajorStress(), sp_mohrs.getSigmaAvg(), sp_mohrs.getTauMax(),
											  sp_mohrs.getSigmaXPrime(), sp_mohrs.getSigmaYPrime(), sp_mohrs.getTauXYPrime(),
											  sp_mohrs.getCohesion(), sp_mohrs.getIntFriction());
					jp_square.setDisplayCoord(sp_mohrs.getTheta());
				}
			});
			
			//Create an action listener for when the SOLVE button is pressed
			jbt_solve.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					try {
						sp_mohrs.mohrsSolver(jtf_sigma_x.getText(), jtf_sigma_y.getText(), jtf_tau_xy.getText(), jtf_theta.getText(), jtf_cohesion.getText(), jtf_int_friction.getText());
						printResults();
						
						//Show the Mohr's Circle and set the coordinates for the various inputs and results
						jp_circle.setCircleShown(true);
						jp_circle.setDisplayCoord(sp_mohrs.getSigmaX(), sp_mohrs.getSigmaY(), sp_mohrs.getTauXY(),
												  sp_mohrs.getMinorStress(), sp_mohrs.getMajorStress(), sp_mohrs.getSigmaAvg(), sp_mohrs.getTauMax(),
												  sp_mohrs.getSigmaXPrime(), sp_mohrs.getSigmaYPrime(), sp_mohrs.getTauXYPrime(),
												  sp_mohrs.getCohesion(), sp_mohrs.getIntFriction());
						jp_square.setDisplayCoord(sp_mohrs.getTheta());
						jta_message.setText("");
						
						throw new NumberFormatException("Incorrect Input");
					}
					
					catch (NumberFormatException except_incorrect_input) {
						jta_message.setText("You entered incorrect input");
					}
					
				}
			});
			
			//Create an action listener for when the Theta Increment button is pressed
			jbt_theta_incr.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					sp_mohrs.incrTheta();
					
					jtf_theta.setText("" + Double.valueOf(fixed_two.format(sp_mohrs.getTheta())));
					sp_mohrs.mohrsSolver(jtf_sigma_x.getText(), jtf_sigma_y.getText(), jtf_tau_xy.getText(), jtf_theta.getText(), jtf_cohesion.getText(), jtf_int_friction.getText());
					printResults();
					jp_circle.setDisplayCoord(sp_mohrs.getSigmaX(), sp_mohrs.getSigmaY(), sp_mohrs.getTauXY(),
											  sp_mohrs.getMinorStress(), sp_mohrs.getMajorStress(), sp_mohrs.getSigmaAvg(),
											  sp_mohrs.getTauMax(), sp_mohrs.getSigmaXPrime(), sp_mohrs.getSigmaYPrime(), sp_mohrs.getTauXYPrime(),
											  sp_mohrs.getCohesion(), sp_mohrs.getIntFriction());
					jp_square.setDisplayCoord(sp_mohrs.getTheta());
				}
			});
			
			//Create an action listener for when the Theta Decriment button is pressed
			jbt_theta_decr.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					sp_mohrs.decrTheta();
					jtf_theta.setText("" + Double.valueOf(fixed_two.format(sp_mohrs.getTheta())));
					sp_mohrs.mohrsSolver(jtf_sigma_x.getText(), jtf_sigma_y.getText(), jtf_tau_xy.getText(), jtf_theta.getText(), jtf_cohesion.getText(), jtf_int_friction.getText());
					printResults();
					jp_circle.setDisplayCoord(sp_mohrs.getSigmaX(), sp_mohrs.getSigmaY(), sp_mohrs.getTauXY(),
											  sp_mohrs.getMinorStress(), sp_mohrs.getMajorStress(), sp_mohrs.getSigmaAvg(),
											  sp_mohrs.getTauMax(), sp_mohrs.getSigmaXPrime(), sp_mohrs.getSigmaYPrime(), sp_mohrs.getTauXYPrime(),
											  sp_mohrs.getCohesion(), sp_mohrs.getIntFriction());
					jp_square.setDisplayCoord(sp_mohrs.getTheta());
				}
			});
			
			//Create an action listener for when the Window is resized
			this.addComponentListener(new ComponentListener() {
				public void componentResized(ComponentEvent e) {
					jp_circle.setDisplayCoord(sp_mohrs.getSigmaX(), sp_mohrs.getSigmaY(), sp_mohrs.getTauXY(),
											  sp_mohrs.getMinorStress(), sp_mohrs.getMajorStress(), sp_mohrs.getSigmaAvg(),
											  sp_mohrs.getTauMax(), sp_mohrs.getSigmaXPrime(), sp_mohrs.getSigmaYPrime(), sp_mohrs.getTauXYPrime(),
											  sp_mohrs.getCohesion(), sp_mohrs.getIntFriction());
					jp_square.setDisplayCoord(sp_mohrs.getTheta());
				}
				
				public void componentHidden(ComponentEvent e) {
				}
				
				public void componentMoved(ComponentEvent e) {
				}
				
				public void componentShown(ComponentEvent e) {
				}
				
			});
			
			//Select all text when tabbing to jtf_sigma_x
			jtf_sigma_x.addFocusListener(new FocusAdapter() {
				public void focusGained(FocusEvent e) {
					jtf_sigma_x.selectAll();
				}
			});
			
			//Select all text when tabbing to jtf_sigma_y
			jtf_sigma_y.addFocusListener(new FocusAdapter() {
				public void focusGained(FocusEvent e) {
					jtf_sigma_y.selectAll();
				}
			});
			
			//Select all text when tabbing to jtf_tau_xy
			jtf_tau_xy.addFocusListener(new FocusAdapter() {
				public void focusGained(FocusEvent e) {
					jtf_tau_xy.selectAll();
				}
			});
			
			//Select all text when tabbing to jtf_theta
			jtf_theta.addFocusListener(new FocusAdapter() {
				public void focusGained(FocusEvent e) {
					jtf_theta.selectAll();
				}
			});
			
			//Select all text when tabbing to jtf_cohesion
			jtf_cohesion.addFocusListener(new FocusAdapter() {
				public void focusGained(FocusEvent e) {
					jtf_cohesion.selectAll();
				}
			});
			
			//Select all text when tabbing to jtf_int_friction
			jtf_int_friction.addFocusListener(new FocusAdapter() {
				public void focusGained(FocusEvent e) {
					jtf_int_friction.selectAll();
				}
			});
			
		}//End class constructor JF_Mohrs_Circle()
		
		public void printResults() {
			//Output the results to the window
			jtf_major_stress.setText("" + Double.valueOf(fixed_two.format(sp_mohrs.getMajorStress())));
			jtf_minor_stress.setText("" + Double.valueOf(fixed_two.format(sp_mohrs.getMinorStress())));
			jtf_tau_xy_prime.setText("" + Double.valueOf(fixed_two.format(sp_mohrs.getTauXYPrime())));
			jtf_sigma_avg.setText("" + Double.valueOf(fixed_two.format(sp_mohrs.getSigmaAvg())));
			jtf_sigma_x_prime.setText("" + Double.valueOf(fixed_two.format(sp_mohrs.getSigmaXPrime())));
			jtf_sigma_y_prime.setText("" + Double.valueOf(fixed_two.format(sp_mohrs.getSigmaYPrime())));
			jtf_tau_max.setText("" + Double.valueOf(fixed_two.format(sp_mohrs.getTauMax())));
		}
}

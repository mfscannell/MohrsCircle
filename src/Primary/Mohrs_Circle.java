package Primary;

import java.awt.*;
import java.awt.event.*;
import java.text.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;

public class Mohrs_Circle {
	
	public static void main(String[] args) {
		JF_Mohrs_Circle jf_mohrs = new JF_Mohrs_Circle();
		jf_mohrs.setTitle("Mohrs Circle");
		jf_mohrs.setExtendedState(jf_mohrs.getExtendedState() | JFrame.MAXIMIZED_BOTH);
		jf_mohrs.setSize(1200, 600);
		jf_mohrs.setLocation(50, 50);
		jf_mohrs.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf_mohrs.setVisible(true);
	}//End method main()

}//End class Mohrs_Circle



class JF_Mohrs_Circle extends JFrame {
	//Create Java Swing components
	JP_Circle jp_circle = new JP_Circle();
	JP_Square jp_square = new JP_Square();
	JTA_Message jta_message = new JTA_Message();
	private JButton jbt_solve = new JButton("Solve");
	private JButton jbt_theta_incr = new JButton("+");
	private JButton jbt_theta_decr = new JButton("-");
	private JTextField jtf_sigma_x = new JTextField(5);
	private JTextField jtf_sigma_y = new JTextField(5);
	private JTextField jtf_tau_xy = new JTextField(5);
	private JTextField jtf_theta = new JTextField(5);
	private JTextField jtf_cohesion = new JTextField(5);
	private JTextField jtf_int_friction = new JTextField(5);
	private JTF_Results jtf_major_stress = new JTF_Results();
	private JTF_Results jtf_minor_stress = new JTF_Results();
	private JTF_Results jtf_sigma_x_prime = new JTF_Results();
	private JTF_Results jtf_sigma_y_prime = new JTF_Results();
	private JTF_Results jtf_tau_xy_prime = new JTF_Results();
	private JTF_Results jtf_sigma_avg = new JTF_Results();
	private JTF_Results jtf_tau_max = new JTF_Results();
	private JRadioButton jrb_soil_yes = new JRadioButton("Yes");
	private JRadioButton jrb_soil_no = new JRadioButton("No");
	private JMB_Mohrs jmb_mohrs = new JMB_Mohrs();
	private DecimalFormat fixed_two = new DecimalFormat("#.##");
	private JTB_File jtb_file = new JTB_File();
	
	private Soil_Particle sp_mohrs = new Soil_Particle();
	
	//JF_Mohrs_Circle constructor
	JF_Mohrs_Circle() {
		
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
	
}//End class JF_Mohrs_Circle

/*-----------------------------------------------------------------------------------------
 * Create a class for the panel showing the Mohr's Circle and the Mohr-Coulomb failure line
 *-----------------------------------------------------------------------------------------*/
class JP_Circle extends JPanel {
	//Declare the variables
	private double x_min;
	private double x_max;
	private double y_min;
	private double y_max;
	private double x_at_tau_0;
	private double tau_at_x_max;
	private double disp_aspect_ratio;
	private int x_canvas_circle;
	private int y_canvas_circle;
	private int x_canvas_sx;
	private int y_canvas_tx;
	private int x_canvas_sy;
	private int y_canvas_ty;
	private int x_canvas_sx_prime;
	private int y_canvas_tx_prime;
	private int x_canvas_sy_prime;
	private int y_canvas_ty_prime;
	private int x_canvas_0;
	private int axes_arrow_length;
	private int axes_arrow_height;
	private int x_canvas_line_1;
	private int x_canvas_line_2;
	private int y_canvas_line_1;
	private int y_canvas_line_2;
	private int circle_width;
	private boolean circle_shown = false;
	private boolean line_shown = false;
	
	//Constructor for class JP_Circle
	JP_Circle() {
		setBackground(Color.BLACK);
	} //End constructor JP_Circle()
	
	//Inquire if the Mohr's circle is shown
	public boolean getCircleShown() {
		return circle_shown;
	} //End method getCircleShown()
	
	//Inquire if the Mohr-Coulomb failure line is shown
	public boolean getLineShown() {
		return line_shown;
	} //End method getLineShown()
	
	//Change the visibility state of the Mohr's circle
	public void setCircleShown(boolean circle_shown) {
		this.circle_shown = circle_shown;
		repaint();
	} //End method setCircleShown()
	
	//Change the visibility of the Mohr-Coulomb failure line
	public void setLineShown(boolean line_shown) {
		this.line_shown = line_shown;
		repaint();
	} //End method setLineShown()
	
	//Set the display coordinates for the panel containing the Mohr's Circle
	public void setDisplayCoord(double sigma_x, double sigma_y, double tau_xy, double min_stress, double maj_stress, double sigma_avg, double tau_max, double sigma_x_prime, double sigma_y_prime, double tau_xy_prime, double cohesion, double int_friction) {
		x_max = maj_stress + 0.1 * (maj_stress - min_stress);
		y_min = -1.1 * tau_max;
		x_at_tau_0 = Soil_Particle.mcCalcSigma(cohesion, int_friction, 0);
		tau_at_x_max = Soil_Particle.mcCalcTau(cohesion, int_friction, x_max);
		disp_aspect_ratio = 1.0 * getWidth() / getHeight();
		
		if (disp_aspect_ratio < 1) {
			//Calculate the x_min coordinate first, then calculate the y_max coordinate
		
			if (!(this.getLineShown())) {
				x_min = min_stress - 0.1 * (maj_stress - min_stress);
			} else {
				x_min = Math.min(min_stress, x_at_tau_0) - 0.1 * (maj_stress - min_stress);
			}
			
			y_max = y_min + (x_max - x_min) / disp_aspect_ratio;
		
		} else {
			//Calculate the y_max coordinate first, then calculate the x_min coordinate
			
			if (!(getLineShown())) {
				y_max = 1.1 * tau_max;
			} else {
				y_max = 1.1 * Math.max(tau_max, tau_at_x_max);
			}
			
			x_min = x_max - (y_max - y_min) * disp_aspect_ratio;
		}
		
		//Set the canvas display coordinates for the Mohr's circle
		x_canvas_circle = (int)(Expanded_Math.interpolate(x_min, 0, x_max, getWidth(), sigma_avg));
		y_canvas_circle = (int)(Expanded_Math.interpolate(y_min, getHeight(), y_max, 0, 0));
		circle_width = (int)Expanded_Math.interpolate(0, 0, x_max - x_min, getWidth(), 2 * tau_max);
		//Set the canvas display coordinates for the axes
		x_canvas_0 = (int)(Expanded_Math.interpolate(x_min, 0, x_max, getWidth(), 0));
		axes_arrow_length = (int)(0.02 * getWidth());
		axes_arrow_height = (int)(0.01 * getHeight());
		//Set the canvas display coordinates for the Mohr-Coulomb failure criteria
		x_canvas_line_1 = (int)(Expanded_Math.interpolate(x_min, 0, x_max, getWidth(), x_at_tau_0));
		y_canvas_line_1 = y_canvas_circle;
		x_canvas_line_2 = getWidth();
		y_canvas_line_2 = (int)(Expanded_Math.interpolate(y_min, getHeight(), y_max, 0, tau_at_x_max));
		//Set the canvas display coordinates for the inputed X and Y stresses
		x_canvas_sx = (int)(Expanded_Math.interpolate(x_min, 0, x_max, getWidth(), sigma_x));
		y_canvas_tx = (int)(Expanded_Math.interpolate(y_min, getHeight(), y_max, 0, tau_xy));
		x_canvas_sy = (int)(Expanded_Math.interpolate(x_min, 0, x_max, getWidth(), sigma_y));
		y_canvas_ty = (int)(Expanded_Math.interpolate(y_min, getHeight(), y_max, 0, (-1.0 * tau_xy)));
		//Set the canvas display coordinates for the resulting X' and Y' stresses
		x_canvas_sx_prime = (int)(Expanded_Math.interpolate(x_min, 0, x_max, getWidth(), sigma_x_prime));
		y_canvas_tx_prime = (int)(Expanded_Math.interpolate(y_min, getHeight(), y_max, 0, tau_xy_prime));
		x_canvas_sy_prime = (int)(Expanded_Math.interpolate(x_min, 0, x_max, getWidth(), sigma_y_prime));
		y_canvas_ty_prime = (int)(Expanded_Math.interpolate(y_min, getHeight(), y_max, 0, (-1.0 * tau_xy_prime)));
		repaint();
	}//End method setDisplayCoord()
		
	//Draw the Mohr's Circle in the display
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		if (getCircleShown()) {
			//Draw the Mohr's Circle
			g.setColor(Color.RED);
			g.drawOval(x_canvas_circle - circle_width / 2, y_canvas_circle - circle_width / 2, circle_width, circle_width);
			//Draw the points representing the inputted stresses
			g.setColor(Color.BLUE);
			g.fillOval(x_canvas_sx - 4, y_canvas_tx - 4, 8, 8);
			g.drawString("\u03c3x, \u03c4xy", x_canvas_sx + 4, y_canvas_tx - 4);
			g.fillOval(x_canvas_sy - 4, y_canvas_ty - 4, 8, 8);
			g.drawString("\u03c3y, \u03c4yx", x_canvas_sy + 4, y_canvas_ty - 4);
			//Draw the points representing the stresses on the X' - Y' plane
			g.drawOval(x_canvas_sx_prime - 4, y_canvas_tx_prime - 4, 8, 8);
			g.drawString("\u03c3x', \u03c4x'y'", x_canvas_sx_prime + 4, y_canvas_tx_prime - 4);
			g.drawOval(x_canvas_sy_prime - 4, y_canvas_ty_prime - 4, 8, 8);
			g.drawString("\u03c3y', \u03c4y'x'", x_canvas_sy_prime + 4, y_canvas_ty_prime - 4);
			//Label the Major Principle Stress and Minor Principle Stress and Maximum Shear Stress
			g.drawString("\u03c3maj", x_canvas_circle + circle_width / 2 - 32, y_canvas_circle - 2);
			g.drawString("\u03c3min", x_canvas_circle - circle_width / 2 + 4, y_canvas_circle - 2);
			g.drawString("\u03c3avg, \u03c4max", x_canvas_circle - 70, y_canvas_circle - circle_width / 2 - 2);
			//Draw the coordinate axes representing sigma and tau stresses
			g.setColor(Color.WHITE);
			//Draw horizontal axis (sigma axis)
			g.drawLine(0, y_canvas_circle, getWidth(), y_canvas_circle);
			g.drawLine(getWidth(), y_canvas_circle, getWidth() - axes_arrow_length, y_canvas_circle - axes_arrow_height / 2);
			g.drawLine(getWidth(), y_canvas_circle, getWidth() - axes_arrow_length, y_canvas_circle + axes_arrow_height / 2);
			g.drawString("+\u03c3", (int)(0.97 * getWidth()), y_canvas_circle - (int)(0.01 * getHeight()));
			//Draw vertical axis (tau axis)
			g.drawLine(x_canvas_0, 0, x_canvas_0, getHeight());
			g.drawLine(x_canvas_0, 0, x_canvas_0 - axes_arrow_height / 2, axes_arrow_length);
			g.drawLine(x_canvas_0, 0, x_canvas_0 + axes_arrow_height / 2, axes_arrow_length);
			g.drawString("+\u03c4", x_canvas_0 + 4, (int)(0.02 * getHeight()));
		}
		
		if (getLineShown()) {
			//Draw the Mohr-Coulomb Failure Line
			g.setColor(Color.GREEN);
			g.drawLine(x_canvas_line_1, y_canvas_line_1, x_canvas_line_2, y_canvas_line_2);
		}
		
	}//End paintComponent()
	
}//End class JP_Circle

/*-------------------------------------------------------------------------------------
 * Create a class for the panel showing the representative square particle under stress
 * The display of the square particle and the stresses acting on it is for helping
 * the user understand better the Mohr's Circle
 *-------------------------------------------------------------------------------------*/
class JP_Square extends JPanel {
	//Declare the variables used
	private int x_canvas_square;
	private int y_canvas_square;
	private int rect_width;
	private int x_canvas_point_1;
	private int y_canvas_point_1;
	private int x_canvas_point_2;
	private int y_canvas_point_2;
	private int x_canvas_point_3;
	private int y_canvas_point_3;
	private int x_canvas_point_4;
	private int y_canvas_point_4;
	private double theta = 0;
	
	//Class Constructor
	JP_Square() {
		TitledBorder tb_square = new TitledBorder("Square Particle");
		tb_square.setTitleColor(Color.WHITE);
		setBorder(tb_square);
		setBackground(Color.BLACK);
	}//End constructor JP_Square()
	
	//Change the coordinates of the four corners of the rotated particle
	public void setDisplayCoord(double theta) {
		this.theta = theta;
		x_canvas_point_1 = (int)(x_canvas_square - rect_width * Math.sin(Math.PI / 4.0) * Math.cos(Math.toRadians(45 + theta)));
		y_canvas_point_1 = (int)(y_canvas_square + rect_width * Math.sin(Math.PI / 4.0) * Math.sin(Math.toRadians(45 + theta)));
		x_canvas_point_2 = (int)(x_canvas_square + rect_width * Math.sin(Math.PI / 4.0) * Math.cos(Math.toRadians(45 - theta)));
		y_canvas_point_2 = (int)(y_canvas_square + rect_width * Math.sin(Math.PI / 4.0) * Math.sin(Math.toRadians(45 - theta)));
		x_canvas_point_3 = (int)(x_canvas_square + rect_width * Math.sin(Math.PI / 4.0) * Math.cos(Math.toRadians(45 + theta)));
		y_canvas_point_3 = (int)(y_canvas_square - rect_width * Math.sin(Math.PI / 4.0) * Math.sin(Math.toRadians(45 + theta)));
		x_canvas_point_4 = (int)(x_canvas_square - rect_width * Math.sin(Math.PI / 4.0) * Math.cos(Math.toRadians(45 - theta)));
		y_canvas_point_4 = (int)(y_canvas_square - rect_width * Math.sin(Math.PI / 4.0) * Math.sin(Math.toRadians(45 - theta)));
		repaint();
	}//End method setDisplayCoord()
	
	//Draw the square particle
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		x_canvas_square = getWidth() / 2;
		y_canvas_square = getHeight() / 2;
		rect_width = Math.min(getWidth() / 3, getHeight() / 3);
		g.setColor(Color.BLUE);
		g.fillRect(x_canvas_square - rect_width / 2, y_canvas_square - rect_width / 2, rect_width, rect_width);
		g.drawLine(x_canvas_point_1, y_canvas_point_1, x_canvas_point_2, y_canvas_point_2);
		g.drawLine(x_canvas_point_2, y_canvas_point_2, x_canvas_point_3, y_canvas_point_3);
		g.drawLine(x_canvas_point_3, y_canvas_point_3, x_canvas_point_4, y_canvas_point_4);
		g.drawLine(x_canvas_point_4, y_canvas_point_4, x_canvas_point_1, y_canvas_point_1);
		g.setColor(Color.RED);
		//Draw normal stress lines
		g.drawLine(x_canvas_square - rect_width / 2 - 5, y_canvas_square, x_canvas_square - rect_width, y_canvas_square);
		g.drawLine(x_canvas_square + rect_width / 2 + 5, y_canvas_square, x_canvas_square + rect_width, y_canvas_square);
		g.drawLine(x_canvas_square, y_canvas_square - rect_width / 2 - 5, x_canvas_square, y_canvas_square - rect_width);
		g.drawLine(x_canvas_square, y_canvas_square + rect_width / 2 + 5, x_canvas_square, y_canvas_square + rect_width);
		g.drawString("+\u03c3x", x_canvas_square - rect_width, y_canvas_square - 3);
		g.drawString("+\u03c3y", x_canvas_square, y_canvas_square - rect_width + 10);
		//Draw shear stress lines
		g.drawLine(x_canvas_square - rect_width / 2 + 5, y_canvas_square + rect_width / 2 + 5, x_canvas_square + rect_width / 2 - 5, y_canvas_square + rect_width / 2 + 5);
		g.drawLine(x_canvas_square - rect_width / 2 + 5, y_canvas_square + rect_width / 2 + 5, x_canvas_square - rect_width / 2 + 15, y_canvas_square + rect_width / 2 + 8);
		
		g.drawLine(x_canvas_square - rect_width / 2 + 5, y_canvas_square - rect_width / 2 - 5, x_canvas_square + rect_width / 2 - 5, y_canvas_square - rect_width / 2 - 5);
		g.drawLine(x_canvas_square + rect_width / 2 - 15, y_canvas_square - rect_width / 2 - 8, x_canvas_square + rect_width / 2 - 5, y_canvas_square - rect_width / 2 - 5);
		
		g.drawLine(x_canvas_square - rect_width / 2 - 5, y_canvas_square - rect_width / 2 + 5, x_canvas_square - rect_width / 2 - 5, y_canvas_square + rect_width / 2 - 5);
		g.drawLine(x_canvas_square - rect_width / 2 - 8, y_canvas_square + rect_width / 2 - 15, x_canvas_square - rect_width / 2 - 5, y_canvas_square + rect_width / 2 - 5);
		
		g.drawLine(x_canvas_square + rect_width / 2 + 5, y_canvas_square - rect_width / 2 + 5, x_canvas_square + rect_width / 2 + 5, y_canvas_square + rect_width / 2 - 5);
		g.drawLine(x_canvas_square + rect_width / 2 + 5, y_canvas_square - rect_width / 2 + 5, x_canvas_square + rect_width / 2 + 8, y_canvas_square - rect_width / 2 + 15);
		
		g.drawString("+\u03c4xy", x_canvas_square + rect_width / 2 - 20, y_canvas_square - rect_width / 2 - 10);
	}//End paintComponent()
	
}//End class JP_Square

//Create a class for the warning messages
class JTA_Message extends JTextArea {
	JTA_Message() {
		setRows(5);
		setBorder(new TitledBorder("Warnings"));
		setBackground(Color.WHITE);
		setForeground(Color.RED);
		setEditable(false);
	}//End constructor JTA_Message()
}//End class JTA_Message

//Create a class for the results text fields
class JTF_Results extends JTextField {
	JTF_Results() {
		setColumns(5);
		setBackground(Color.LIGHT_GRAY);
		setEditable(false);
	}//End constructor JTF_Results()
}//End class JTF_Results

//Create a class for the menubar
class JMB_Mohrs extends JMenuBar {
	//Constructor for JMB_Mohrs
	JMB_Mohrs() {
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
				JF_Help_About jf_about = new JF_Help_About();
				jf_about.setTitle("Mohrs Circle Help");
				jf_about.setSize(300, 200);
				jf_about.setLocation(200, 200);
				jf_about.setVisible(true);
			}
		});
				
	}//End Constructor JMB_Mohrs()
	
}//End class JMB_Mohrs

class JTB_File extends JToolBar {
	private JButton jbt_new = new JButton(new ImageIcon("C:/eclipse/workspace/Mohrs_Circle/images/new_file.gif"));
	private JButton jbt_open = new JButton(new ImageIcon("C:/eclipse/workspace/Mohrs_Circle/images/open_file.gif"));
	private JButton jbt_save = new JButton(new ImageIcon("C:/eclipse/workspace/Mohrs_Circle/images/save_file.gif"));
	private JButton jbt_help = new JButton(new ImageIcon("C:/eclipse/workspace/Mohrs_Circle/images/save_file.gif"));
	
	JTB_File() {
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

//create a class for the About Mohrs Circle
class JF_Help_About extends JFrame {
	JF_Help_About() {
		JTextArea jta_about = new JTextArea();
		jta_about.setText("Created by Mark Scannell" + "\nVersion 1.0");
		jta_about.setEditable(false);
		add(jta_about);
	}//End constructor JF_About()
	
}//End class JF_About
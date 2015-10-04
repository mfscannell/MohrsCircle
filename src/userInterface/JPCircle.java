package userInterface;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import Primary.ExpandedMath;
import Primary.SoilParticle;

public class JPCircle extends JPanel {
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
		public JPCircle() {
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
			x_at_tau_0 = SoilParticle.mcCalcSigma(cohesion, int_friction, 0);
			tau_at_x_max = SoilParticle.mcCalcTau(cohesion, int_friction, x_max);
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
			x_canvas_circle = (int)(ExpandedMath.interpolate(x_min, 0, x_max, getWidth(), sigma_avg));
			y_canvas_circle = (int)(ExpandedMath.interpolate(y_min, getHeight(), y_max, 0, 0));
			circle_width = (int)ExpandedMath.interpolate(0, 0, x_max - x_min, getWidth(), 2 * tau_max);
			//Set the canvas display coordinates for the axes
			x_canvas_0 = (int)(ExpandedMath.interpolate(x_min, 0, x_max, getWidth(), 0));
			axes_arrow_length = (int)(0.02 * getWidth());
			axes_arrow_height = (int)(0.01 * getHeight());
			//Set the canvas display coordinates for the Mohr-Coulomb failure criteria
			x_canvas_line_1 = (int)(ExpandedMath.interpolate(x_min, 0, x_max, getWidth(), x_at_tau_0));
			y_canvas_line_1 = y_canvas_circle;
			x_canvas_line_2 = getWidth();
			y_canvas_line_2 = (int)(ExpandedMath.interpolate(y_min, getHeight(), y_max, 0, tau_at_x_max));
			//Set the canvas display coordinates for the inputed X and Y stresses
			x_canvas_sx = (int)(ExpandedMath.interpolate(x_min, 0, x_max, getWidth(), sigma_x));
			y_canvas_tx = (int)(ExpandedMath.interpolate(y_min, getHeight(), y_max, 0, tau_xy));
			x_canvas_sy = (int)(ExpandedMath.interpolate(x_min, 0, x_max, getWidth(), sigma_y));
			y_canvas_ty = (int)(ExpandedMath.interpolate(y_min, getHeight(), y_max, 0, (-1.0 * tau_xy)));
			//Set the canvas display coordinates for the resulting X' and Y' stresses
			x_canvas_sx_prime = (int)(ExpandedMath.interpolate(x_min, 0, x_max, getWidth(), sigma_x_prime));
			y_canvas_tx_prime = (int)(ExpandedMath.interpolate(y_min, getHeight(), y_max, 0, tau_xy_prime));
			x_canvas_sy_prime = (int)(ExpandedMath.interpolate(x_min, 0, x_max, getWidth(), sigma_y_prime));
			y_canvas_ty_prime = (int)(ExpandedMath.interpolate(y_min, getHeight(), y_max, 0, (-1.0 * tau_xy_prime)));
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
}

package userInterface;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class JPSquare extends JPanel {
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
		public JPSquare() {
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
}

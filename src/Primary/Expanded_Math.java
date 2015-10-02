package Primary;

public class Expanded_Math {
	Expanded_Math() {
	}
	
	//This method linearly interpolates between two data points
		public static double interpolate(int x1, int y1, int x2, int y2, int x3) {
			double m = 1.0 * (y2 - y1) / (x2 - x1);
			double y3 = y1 + m * (x3 - x1);
			return y3;
		}
	
	//This method linearly interpolates between two data points
	public static double interpolate(double x1, double y1, double x2, double y2, double x3) {
		double m = (y2 - y1) / (x2 - x1);
		double y3 = y1 + m * (x3 - x1);
		return y3;
	}
	
	//This method returns the log of x to the base y
	public static double logXBaseY(int x, int y) {
		double z = Math.log(x) / Math.log(y);
		return z;
	}
	
	//This method returns the log of x to the base y
	public static double logXBaseY(int x, double y) {
		double z = Math.log(x) / Math.log(y);
		return z;
	}
	
	//This method returns the log of x to the base y
	public static double logXBaseY(double x, int y) {
		double z = Math.log(x) / Math.log(y);
		return z;
	}
	
	//This method returns the log of x to the base y
	public static double logXBaseY(double x, double y) {
		double z = Math.log(x) / Math.log(y);
		return z;
	}
	
	//This method converts the Farenheit temperature to Celsius
	public static double tempConvFToC(int f) {
		double c = (f - 32) * 5.0 / 9.0;
		return c;
	}
	
	//This method converts the Farenheit temperature to Celsius
	public static double tempConvFToC(double f) {
		double c = (f - 32) * 5.0 / 9.0;
		return c;
	}
	
	//This method converts the Celsius temperature to Farenheit
	public static double tempConvCToF(int c) {
		double f = c * 9.0 / 5.0 + 32;
		return f;
	}
	
	//This method converts the Celsius temperature to Farenheit
	public static double tempConvCToF(double c) {
		double f = c * 9.0 / 5.0 + 32;
		return f;
	}
	
	//This method converts the Celsius temperature to Kelvin
	public static double tempConvCToK(int c) {
		double k = 1.0 * c + 273.15;
		return k;
	}
	
	//This method converts the Celsius temperature to Kelvin
	public static double tempConvCToK(double c) {
		double k = c + 273.15;
		return k;
	}
	
	//This method converts the Kelvin temperature to Celsius
	public static double tempConvKToC(int k) {
		double c = 1.0 * k - 273.15;
		return c;
	}
	
	//This method converts the Kelvin temperature to Celsius
	public static double tempConvKToC(double k) {
		double c = k - 273.15;
		return c;
	}
	
	//This method computes the dot product of two 1D matrices (vectors) of int type
	public static int matrixDotProduct(int[] mat_a, int[] mat_b) {
		int dot_product = 0;
		for (int i = 0; i < mat_a.length; i++) {
			dot_product = dot_product + mat_a[i] * mat_b[i];
		}
		return dot_product;
	}
	
	//This method computes the dot product of two 1D matrices (vectors) of double type
	public static double matrixDotProduct(int[] mat_a, double[] mat_b) {
		double dot_product = 0;	
		for (int i = 0; i < mat_a.length; i++) {
			dot_product = dot_product + mat_a[i] * mat_b[i];
		}
		return dot_product;
	}
	
	//This method computes the dot product of two 1D matrices (vectors) of double type
	public static double matrixDotProduct(double[] mat_a, int[] mat_b) {
		double dot_product = 0;	
		for (int i = 0; i < mat_a.length; i++) {
			dot_product = dot_product + mat_a[i] * mat_b[i];
		}
		return dot_product;
	}
	
	
	//This method computes the dot product of two 1D matrices (vectors) of double type
	public static double matrixDotProduct(double[] mat_a, double[] mat_b) {
		double dot_product = 0;	
		for (int i = 0; i < mat_a.length; i++) {
			dot_product = dot_product + mat_a[i] * mat_b[i];
		}
		return dot_product;
	}
	
	//This method multiplies two matrices of int types
	public static int[][] matrixMultiply(int[][] mat_a, int[][] mat_b) {
		int[][] mat_c = new int[mat_a.length][mat_b[1].length];
		
		//Fill the return matrix with values of 0
		for (int i = 0; i < mat_c.length; i++) {
			for (int j = 0; j < mat_c[1].length; j++) {
				mat_c[i][j] = 0;
			}
		}
		
		for (int i = 0; i < mat_c.length; i++) {
			for (int j = 0; j < mat_c[1].length; j++) {
				for (int k = 0; k < mat_a[1].length; k++) {
					mat_c[i][j] = mat_c[i][j] + mat_a[i][k] * mat_b[k][j];
				}
			}
		}
		
		return mat_c;
	}//End method int[][] matrixMultiply(int[][], int[][])
	
	//This method multiplies two matrices of double types
	public static double[][] matrixMultiply(double[][] mat_a, double[][] mat_b) {
		double[][] mat_c = new double[mat_a.length][mat_b[1].length];
			
		//Fill the return matrix with values of 0
		for (int i = 0; i < mat_c.length; i++) {
			for (int j = 0; j < mat_c[1].length; j++) {
				mat_c[i][j] = 0;
			}
		}
			
		for (int i = 0; i < mat_c.length; i++) {
			for (int j = 0; j < mat_c[1].length; j++) {
				for (int k = 0; k < mat_a[1].length; k++) {
					mat_c[i][j] = mat_c[i][j] + mat_a[i][k] * mat_b[k][j];
				}
			}
		}
			
		return mat_c;
	}//End method double[][] matrixMultiply(double[][], double[][])

}//End class Expanded_Math

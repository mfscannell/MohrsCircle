package Primary;

public class Soil_Particle {
	//Declare the variables
	private double sigma_x = 0;
	private double sigma_y = 0;
	private double tau_xy = 0;
	private double theta = 0;
	private double cohesion = 0;
	private double int_friction = 0;
	private double major_stress = 0;
	private double minor_stress = 0;
	private double sigma_avg = 0;
	private double sigma_x_prime = 0;
	private double sigma_y_prime = 0;
	private double tau_xy_prime = 0;
	private double tau_max = 0;
	
	Soil_Particle() {
	}
		
	//Solve for the various Mohr's Circle variables
	public void mohrsSolver(String string_sigma_x, String string_sigma_y, String string_tau_xy,
							String string_theta, String string_cohesion, String string_int_friction) {
			
		//Obtain the inputted values
		sigma_x = Double.parseDouble(string_sigma_x);
		sigma_y = Double.parseDouble(string_sigma_y);
		tau_xy = Double.parseDouble(string_tau_xy);
		theta = Double.parseDouble(string_theta);
		cohesion = Double.parseDouble(string_cohesion);
		int_friction = Double.parseDouble(string_int_friction);
				
		//Calculate the results
		sigma_avg = 0.5 * (sigma_x + sigma_y);
		tau_max = Math.sqrt(Math.pow(0.5 * (sigma_y - sigma_x), 2) + Math.pow(tau_xy, 2));
		major_stress = sigma_avg + tau_max;
		minor_stress = sigma_avg - tau_max;
		sigma_x_prime = sigma_avg + 0.5 * (sigma_x - sigma_y) * Math.cos(Math.toRadians(2 * theta)) + tau_xy * Math.sin(Math.toRadians(2 * theta));
		sigma_y_prime = sigma_avg - 0.5 * (sigma_x - sigma_y) * Math.cos(Math.toRadians(2 * theta)) - tau_xy * Math.sin(Math.toRadians(2 * theta));
		tau_xy_prime = -0.5 * (sigma_x - sigma_y) * Math.sin(Math.toRadians(2 * theta)) + tau_xy * Math.cos(Math.toRadians(2 * theta));
				
	}//End method mohrsSolver()
		
	//Calculate the shear stress based on the normal stress, cohesion, and angle of internal friction
	public static double mcCalcTau(double cohesion, double int_friction, double sigma) {
		double tau = cohesion + sigma * Math.tan(Math.toRadians(int_friction));
		return tau;
	} //End method mc_calc_tau
		
	//Calculate the normal stress based on the cohesion, angle of internal friction, and the shear stress using the Mohr-Coulomb eqn.
	public static double mcCalcSigma(double cohesion, double int_friction, double tau) {
		double sigma = -1.0 * cohesion / Math.tan(Math.toRadians(int_friction));
		return sigma;
	} //End method mc_calc_sigma()
		
	//This method returns the sigma x
	public double getSigmaX() {
		return sigma_x;
	}
		
	//This method returns the sigma y
	public double getSigmaY() {
		return sigma_y;
	}
		
	//This method returns the tau xy
	public double getTauXY() {
		return tau_xy;
	}
		
	//This method returns the major stress
	public double getMajorStress() {
		return major_stress;
	}
		
	//This method returns the minor stress
	public double getMinorStress() {
		return minor_stress;
	}
		
	//This method returns the sigma average
	public double getSigmaAvg() {
		return sigma_avg;
	}
		
	//This method returns the tau_max
	public double getTauMax() {
		return tau_max;
	}
		
	//This method returns the sigma x prime
	public double getSigmaXPrime() {
		return sigma_x_prime;
	}
		
	//This method returns the sigma y prime
	public double getSigmaYPrime() {
		return sigma_y_prime;
	}
		
	//This method returns the tau xy prime
	public double getTauXYPrime() {
		return tau_xy_prime;
	}
		
	//This method returns the cohesion
	public double getCohesion() {
		return cohesion;
	}
		
	//This method returns the angle of internal friction
	public double getIntFriction() {
		return int_friction;
	}
		
	//This method returns the theta
	public double getTheta() {
		return theta;
	}
		
	//This method sets the cohesion
	public void setCohesion(double cohesion) {
		this.cohesion = cohesion;
	}
		
	//This method returns the angle of internal friction
	public void setIntFriction(double int_friction) {
		this.int_friction = int_friction;
	}
		
	//This method increments theta
	public void incrTheta() {
		theta++;
			
		while (theta >= 180) {
			theta = theta - 180;
		}
			
		while (theta < 0) {
			theta = theta + 180;
		}
			
	}//End method incrTheta()
		
	//This method decrements theta
	public void decrTheta() {
		theta--;
			
		while (theta >= 180) {
			theta = theta - 180;
		}
			
		while (theta < 0) {
			theta = theta + 180;
		}
	}//End method decrTheta()

}//End class Soil_Particle

package Primary;

public class SoilParticle {
    //Declare the variables
    private double sigmaX = 0;
    private double sigmaY = 0;
    private double tauXY = 0;
    private double theta = 0;
    private double cohesion = 0;
    private double intFriction = 0;
    private double majorStress = 0;
    private double minorStress = 0;
    private double sigmaAvg = 0;
    private double sigmaXPrime = 0;
    private double sigmaYPrime = 0;
    private double tauXYPrime = 0;
    private double tauMax = 0;
    
    public SoilParticle() {
    }
		
    //Solve for the various Mohr's Circle variables
    public void mohrsSolver(String stringSigmaX, String stringSigmaY, String stringTauXY, String stringTheta, String stringCohesion, String stringIntFriction) {
        //Obtain the inputted values
        sigmaX = Double.parseDouble(stringSigmaX);
        sigmaY = Double.parseDouble(stringSigmaY);
        tauXY = Double.parseDouble(stringTauXY);
        theta = Double.parseDouble(stringTheta);
        cohesion = Double.parseDouble(stringCohesion);
        intFriction = Double.parseDouble(stringIntFriction);
				
        //Calculate the results
        sigmaAvg = 0.5 * (sigmaX + sigmaY);
        tauMax = Math.sqrt(Math.pow(0.5 * (sigmaY - sigmaX), 2) + Math.pow(tauXY, 2));
        majorStress = sigmaAvg + tauMax;
        minorStress = sigmaAvg - tauMax;
        sigmaXPrime = sigmaAvg + 0.5 * (sigmaX - sigmaY) * Math.cos(Math.toRadians(2 * theta)) + tauXY * Math.sin(Math.toRadians(2 * theta));
        sigmaYPrime = sigmaAvg - 0.5 * (sigmaX - sigmaY) * Math.cos(Math.toRadians(2 * theta)) - tauXY * Math.sin(Math.toRadians(2 * theta));
        tauXYPrime = -0.5 * (sigmaX - sigmaY) * Math.sin(Math.toRadians(2 * theta)) + tauXY * Math.cos(Math.toRadians(2 * theta));
    }
		
    //Calculate the shear stress based on the normal stress, cohesion, and angle of internal friction
    public static double mcCalcTau(double cohesion, double intFriction, double sigma) {
        double tau = cohesion + sigma * Math.tan(Math.toRadians(intFriction));
        return tau;
    }
		
    //Calculate the normal stress based on the cohesion, angle of internal friction, and the shear stress using the Mohr-Coulomb eqn.
    public static double mcCalcSigma(double cohesion, double intFriction, double tau) {
        double sigma = -1.0 * cohesion / Math.tan(Math.toRadians(intFriction));
        return sigma;
    } 
	
    public double getSigmaX() {
        return sigmaX;
    }
    
    public double getSigmaY() {
        return sigmaY;
    }
    
    public double getTauXY() {
        return tauXY;
    }
    
    public double getMajorStress() {
        return majorStress;
    }
    
    public double getMinorStress() {
        return minorStress;
    }
    
    public double getSigmaAvg() {
        return sigmaAvg;
    }
    
    public double getTauMax() {
        return tauMax;
    }
    
    public double getSigmaXPrime() {
        return sigmaXPrime;
    }
    
    public double getSigmaYPrime() {
        return sigmaYPrime;
    }
    
    public double getTauXYPrime() {
        return tauXYPrime;
    }
    
    public double getCohesion() {
        return cohesion;
    }
    
    public double getIntFriction() {
        return intFriction;
    }
    
    public double getTheta() {
        return theta;
    }
    
    public void setCohesion(double cohesion) {
        this.cohesion = cohesion;
    }
    
    public void setIntFriction(double int_friction) {
        this.intFriction = int_friction;
    }
    
    public void incrTheta() {
        theta++;
    		
        while (theta >= 180) {
            theta = theta - 180;
        }
    		
        while (theta < 0) {
            theta = theta + 180;
        }
    }
    
    public void decrTheta() {
        theta--;
	
        while (theta >= 180) {
            theta = theta - 180;
        }
	
        while (theta < 0) {
            theta = theta + 180;
        }
    }
}

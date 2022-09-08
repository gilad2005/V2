package frc.lib.util;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;

public class SwerveDriveHelper {
    private Translation2d translationalVector = new Translation2d();
	private Translation2d lastDriveVector = new Translation2d();
	private final Translation2d rotationalVector = new Translation2d();
	private boolean robotCentric = false;
	private double lowPowerScalar = 0.6;

	


	public void setLowPowerScalar(double scalar){
		lowPowerScalar = scalar;
	}

	
	private double maxSpeedFactor = 1.0;
	public void setMaxSpeed(double max){
		maxSpeedFactor = max;
	}

    /* 
    //The swerve's various control states
	public enum ControlState{
		NEUTRAL, MANUAL, POSITION, ROTATION, DISABLED, VECTORIZED,
		TRAJECTORY, VELOCITY, VISION
	}
	private ControlState currentState = ControlState.NEUTRAL;
	public ControlState getState(){
		return currentState;
	}
	public void setState(ControlState newState){
		currentState = newState;
	}
	*/

    public Rotation2d direction(Translation2d translation2d) {
        return new Rotation2d(translation2d.getX(), translation2d.getY());
    }

    public Rotation2d nearestPole(Rotation2d rotation2d){
    	double pole_sin = 0.0;
    	double pole_cos = 0.0;
    	if(Math.abs(rotation2d.getCos()) > Math.abs(rotation2d.getSin())){
    		pole_cos = Math.signum(rotation2d.getCos());
    		pole_sin = 0.0;
    	}else{
    		pole_cos = 0.0;
    		pole_sin = Math.signum(rotation2d.getSin());
    	}
    	return new Rotation2d(pole_cos, pole_sin);
    }

    public Translation2d toTranslation(Rotation2d rotation2d) {
        return new Translation2d(rotation2d.getCos(), rotation2d.getSin());
    }

    public double distance(Rotation2d rotation2d, Rotation2d other) {
        return inverse(rotation2d.rotateBy(other)).getRadians();
    }

    public Rotation2d inverse(Rotation2d rotation2d) {
        return new Rotation2d(rotation2d.getCos(), -rotation2d.getSin());
    }


	/**
	 * Main function used to send manual input during teleop.
	 * @param x forward/backward input
	 * @param y left/right input
	 * @param rotate rotational input
	 * @param robotCentric gyro use
	 * @param lowPower scaled down output
	 */
	public void sendInput(double x, double y, double rotate, boolean robotCentric, boolean lowPower){
		Translation2d translationalInput = new Translation2d(x, y);
		double inputMagnitude = translationalInput.getNorm();
		
		/* Snap the translational input to its nearest pole, if it is within a certain threshold 
		  of it. */
		double threshold = Math.toRadians(10.0);
		if(Math.abs(distance(direction(translationalInput) , nearestPole(direction(translationalInput)))) < threshold){
			translationalInput = toTranslation(nearestPole(direction(translationalInput))).times(inputMagnitude);
		}
		
		double deadband = 0.25;
		if(inputMagnitude < deadband){
			translationalInput = new Translation2d();
			inputMagnitude = 0;
		}
		
		/* Scale x and y by applying a power to the magnitude of the vector they create, in order
		 to make the controls less sensitive at the lower end. */
		final double power = (lowPower) ? 1.75 : 1.5;
		Rotation2d direction = direction(translationalInput);
		double scaledMagnitude = Math.pow(inputMagnitude, power);
		translationalInput = new Translation2d(direction.getCos() * scaledMagnitude,
				direction.getSin() * scaledMagnitude);
		
		rotate = (Math.abs(rotate) < deadband) ? 0 : rotate;
		rotate = Math.pow(Math.abs(rotate), 1.75)*Math.signum(rotate);
		
		translationalInput = translationalInput.times(maxSpeedFactor);
		rotate *= maxSpeedFactor;
				
		translationalVector = translationalInput;
		
		if(lowPower){
			translationalVector = translationalVector.times(lowPowerScalar);
			rotate *= lowPowerScalar;
		}else{
			rotate *= 0.8;
		}

		if(inputMagnitude > 0.3)
			lastDriveVector = new Translation2d(x, y);
		else if(translationalVector.getX() == 0.0 && translationalVector.getY() == 0.0 && rotate != 0.0){
			lastDriveVector = rotationalVector;
		}
		
		this.robotCentric = robotCentric;
	}

    public void sendInput(double x, double y, double rotate){
        double _x = x * x * Math.signum(x);
        double _y = y * y * Math.signum(y);
        double _rotate = rotate * rotate * Math.signum(rotate);
    }

	
}


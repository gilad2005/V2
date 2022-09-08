package frc.lib.util;

import edu.wpi.first.math.controller.PIDController;

public class SwerveHeadingController {
    private PIDController stabalizePidController;
    private PIDController snapPidController;
    public enum State{
		Off, Stabilize, Snap, Vision
	}   

    State headingState = State.Off; 

    public SwerveHeadingController(){
        this.snapPidController = new  PIDController(0.015, 0.0, 0.0);
        this.stabalizePidController = new PIDController(0.005, 0.0, 0.0005);

        snapPidController.enableContinuousInput(-180, 180);
        stabalizePidController.enableContinuousInput(-180, 180);

        snapPidController.setIntegratorRange(9.9*Math.E+30,-1*(9.9*Math.E+30));
        stabalizePidController.setIntegratorRange(9.9*Math.E+30,-1*(9.9*Math.E+30));
    }

    public void setTarget(double angle){
        snapPidController.setSetpoint(angle);
        stabalizePidController.setSetpoint(angle);
    }

    public double getPIDValue(double currentAngle, double setpoint){
        double correction = 0;
        switch(headingState){
            case Off:
                
                break;

            case Snap:
            correction = snapPidController.calculate(currentAngle, setpoint);
            break;
            
            case Stabilize:
            correction = stabalizePidController.calculate(correction, setpoint);
            break;

            case Vision:
                //TODO: add vision logic
                break; 

            default:
                break;
        }
        return correction;
    }
}

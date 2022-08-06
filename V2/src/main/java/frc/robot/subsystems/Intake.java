// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.CANSparkMaxLowLevel.PeriodicFrame;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Intake extends SubsystemBase {
  /** Creates a new Intake. */
  private CANSparkMax angleMotor; 
  private CANSparkMax dribbler;
  private RelativeEncoder angleEncoder;
  private SparkMaxPIDController anglePIDController;
  private static Intake intake = null;
  Rotation2d angleRotation2d;

  public Intake() {
    angleMotor = new CANSparkMax(51, MotorType.kBrushless);
    configAngleMotor();
    anglePIDController = angleMotor.getPIDController();
    configAngleMotorPID();
    dribbler  = new CANSparkMax(50,MotorType.kBrushless);
    configDribblerMotor();
    angleEncoder = angleMotor.getEncoder();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    angleRotation2d = new Rotation2d(Units.degreesToRadians(((angleEncoder.getPosition() * 360)/Constants.IntakeVariables.angleGearRatio))); //updates the angle rotation 2d
  }

  public static Intake getInstance(){ //singelton method
    if(intake == null){
      intake = new Intake();
    }
    return intake;
  }

  public Rotation2d getAngleRotation(){
    return this.angleRotation2d;
  }

  public Rotation2d angleToRotation(double angle){
      return new Rotation2d(((angle*Constants.IntakeVariables.angleGearRatio)/360));
  }

  public void ResetAngle(double wantedResetAngle){
    angleEncoder.setPosition(angleToRotation(wantedResetAngle).getDegrees());
  }

  public void setMotorToAngle(Rotation2d angle){
    anglePIDController.setReference(angleToRotation(angle.getDegrees()).getDegrees(), CANSparkMax.ControlType.kPosition);
  }

  public void StopMotors(){
    angleMotor.set(0);
    dribbler.set(0);
  }

  public void setDribblerSpeed(double speed){
    dribbler.set(speed);
  }

  public double getAngleMotorRPM(){
    return angleEncoder.getVelocity(); //RPM
  }

  public double getAngleMotorVelocity(){
    return (getAngleMotorRPM()/(2*Math.PI)); //units rad per sec
  }

  public SparkMaxPIDController getAnglePIDController(){
    return anglePIDController;
  }

  public double getAngleMotorCurrent(){
    return angleMotor.getOutputCurrent();
  }
  
  private void configAngleMotor(){
    angleMotor.restoreFactoryDefaults();
    angleMotor.setPeriodicFramePeriod(PeriodicFrame.kStatus3,1000); //analog sensor
    angleMotor.setSmartCurrentLimit(12);
  }

  private void configAngleMotorPID(){
    anglePIDController.setP(Constants.IntakeVariables.kP);
    anglePIDController.setI(Constants.IntakeVariables.kI);
    anglePIDController.setD(Constants.IntakeVariables.kD);
    anglePIDController.setIZone(Constants.IntakeVariables.kIz);
    anglePIDController.setFF(Constants.IntakeVariables.kFF);
    anglePIDController.setOutputRange(Constants.IntakeVariables.kMinOutput, Constants.IntakeVariables.kMaxOutput);
  }

  private void configDribblerMotor(){
    dribbler.restoreFactoryDefaults();
    dribbler.setPeriodicFramePeriod(PeriodicFrame.kStatus1, 250); //current
    dribbler.setPeriodicFramePeriod(PeriodicFrame.kStatus3, 1000); //analog sensor
    dribbler.setSmartCurrentLimit(35);
  }

}

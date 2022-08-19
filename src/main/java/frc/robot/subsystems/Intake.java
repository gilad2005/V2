// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.CANSparkMaxLowLevel.PeriodicFrame;
import com.revrobotics.SparkMaxPIDController.ArbFFUnits;
import edu.wpi.first.math.controller.ArmFeedforward;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.MathUtil;

public class Intake extends SubsystemBase {
  /** Creates a new Intake. */
  private CANSparkMax angleMotor; 
  private CANSparkMax dribbler;
  private RelativeEncoder angleEncoder;
  private static Intake intake = null;
  Rotation2d angleRotation2d;
  private SparkMaxPIDController angleMotorController;
  private ArmFeedforward feedforward;

  private Intake() { 
    feedforward = new ArmFeedforward(0, 0, 0, 0); // (0,Kcos,0,0) we only need to use Kcos
    angleMotor = new CANSparkMax(Constants.IntakeVariables.angleMotorID, MotorType.kBrushless);
    angleMotorController = angleMotor.getPIDController();
    configAngleMotor();
    configAngleMotorPID();
    dribbler  = new CANSparkMax(Constants.IntakeVariables.dribblerID,MotorType.kBrushless);
    configDribblerMotor();
    angleEncoder = angleMotor.getEncoder();
    resetAngle(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    angleRotation2d = Rotation2d.fromDegrees((angleEncoder.getPosition() * 360) / Constants.IntakeVariables.angleGearRatio); //updates the angle rotation 2d
    System.out.println(angleRotation2d.getDegrees());
    //System.out.println("Current Rotation "+angleEncoder.getPosition());
    System.out.println("motor set value: " + angleMotor.get());
  }

  public static Intake getInstance(){ //singelton method
    if(intake == null){
      intake = new Intake();
    }
    return intake;
  }

  public Rotation2d getAngleRotation2d(){
    return this.angleRotation2d;
  }

  public double angleToRotation(double angle){
      return ((angle * Constants.IntakeVariables.angleGearRatio) / 360);
  }

  public void resetAngle(double wantedResetAngle){ 
    angleEncoder.setPosition(angleToRotation(wantedResetAngle));
  }

  public void setMotorToAngle(Rotation2d angle){
    getAnglePIDController().setReference(angleToRotation(angle.getDegrees()), CANSparkMax.ControlType.kPosition, Constants.IntakeVariables.PIDSlot, getArbFF(), ArbFFUnits.kVoltage);
  }

  public void stopMotors(){
    angleMotor.stopMotor();
    dribbler.stopMotor();
  }

  public void setDribblerSpeed(double speed){
    dribbler.set(speed);
  }

  public double getAngleMotorRPM(){
    return this.angleEncoder.getVelocity(); //RPM
  }

  public double getAngleMotorAngularVelocity(){
    return (getAngleMotorRPM() / (2 * Math.PI)); //units rad per sec
  }

  public SparkMaxPIDController getAnglePIDController(){
    return this.angleMotor.getPIDController();
  }

  public double getAngleMotorCurrent(){ 
    return this.angleMotor.getOutputCurrent();
  }

  public void setAnglMotorPrecentOutput(double speed){
    angleMotor.set(speed);
  }
  
  private double getArbFF(){
    return feedforward.calculate(getAngleRotation2d().getRadians(), intake.getAngleMotorAngularVelocity()); //set the feed forward to be able to stay at a 
  }

  public boolean atAngleSetpoint(double wantedAngle){
    return MathUtil.inRange(intake.getAngleRotation2d().getDegrees(), Constants.IntakeVariables.angleRange, wantedAngle);
  }

  private void configAngleMotor(){
    this.angleMotor.restoreFactoryDefaults();
    this.angleMotor.setPeriodicFramePeriod(PeriodicFrame.kStatus3,1000); //analog sensor
    this.angleMotor.setSmartCurrentLimit(12);
  }

  private void configAngleMotorPID(){
    this.angleMotorController.setP(Constants.IntakeVariables.kP);
    this.angleMotorController.setI(Constants.IntakeVariables.kI);
    this.angleMotorController.setD(Constants.IntakeVariables.kD);
    this.angleMotorController.setIZone(Constants.IntakeVariables.kIz);
    this.angleMotorController.setFF(Constants.IntakeVariables.kFF);
    this.angleMotorController.setOutputRange(Constants.IntakeVariables.kMinOutput, Constants.IntakeVariables.kMaxOutput);
  }

  private void configDribblerMotor(){
    dribbler.restoreFactoryDefaults();
    dribbler.setPeriodicFramePeriod(PeriodicFrame.kStatus1, 250); //current
    dribbler.setPeriodicFramePeriod(PeriodicFrame.kStatus3, 1000); //analog sensor
    dribbler.setSmartCurrentLimit(35);
  }

}

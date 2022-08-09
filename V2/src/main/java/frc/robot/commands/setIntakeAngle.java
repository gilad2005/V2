// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.math.controller.ArmFeedforward;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.MathUtil;
import frc.robot.subsystems.Intake;

public class setIntakeAngle extends CommandBase {
  private Rotation2d wantedAngle;
  private Intake intake;
  private ArmFeedforward feedforward;
  private boolean inRange = false;
  /** Creates a new setIntakeAngle. */
  public setIntakeAngle(Rotation2d wantedAngle, Intake intake, boolean open) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(intake);
    this.wantedAngle = wantedAngle;
    this.intake = intake;
    if(open){
      intake.setDribblerSpeed(1);
    }
    else{
      intake.setDribblerSpeed(0);
    }
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    intake.setMotorToAngle(wantedAngle);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    intake.setMotorToAngle(wantedAngle);
    inRange = MathUtil.inRange(intake.getAngleRotation().getDegrees(), Constants.IntakeVariables.angleRange, wantedAngle.getDegrees());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    feedforward = new ArmFeedforward(0, 10, 0, 0); // (0,Kcos,0,0) we only need to use Kcos
    intake.getAnglePIDController().setFF(feedforward.calculate(wantedAngle.getRadians(), intake.getAngleMotorVelocity())); //set the feed forward to be able to stay at a 
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(inRange && intake.getAngleMotorCurrent() > 12){
      return true;
    }
    return false;
  }
}

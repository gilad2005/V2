// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Intake;

public class SetIntakeAngle extends CommandBase { 
  private Intake intake;
  private Rotation2d wantedAngle;
  private boolean open = false;
  /** Creates a new setIntakeAngle. */
  public SetIntakeAngle(Intake intake, Rotation2d wantedAngle, boolean open) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(intake);
    this.wantedAngle = wantedAngle;
    this.intake = intake;
    this.open = open;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    if(open){
      intake.setDribblerSpeed(Constants.IntakeVariables.dribblerOpenSpeed);
    }
    else if(intake.getAngleRotation2d().getDegrees() > 15){
        intake.setDribblerSpeed(Constants.IntakeVariables.dribblerOpenSpeed);
    }
    else{
      intake.setDribblerSpeed(0);
      }
    }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    intake.setMotorToAngle(wantedAngle);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    intake.stopMotors();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(!open && intake.getAngleRotation2d().getDegrees() < 10){ //10 is the angle that its starts Homing
      return true;
    }
    return false;
  }
}

// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;


import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Intake;


public class IntakeHoming extends CommandBase {
  /** Creates a new IntakeHoming. */
  Timer timer = new Timer();
  private Intake intake;
  public IntakeHoming(Intake intake) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(intake);
    this.intake = intake;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    timer.reset();
    timer.start();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    intake.setAnglMotorPrecentOutput(Constants.IntakeVariables.intakeHomingSpeed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    intake.resetAngle(0);
    intake.setAnglMotorPrecentOutput(-0.03);
    System.out.println("End");
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return timer.hasElapsed(Constants.IntakeVariables.homingTime) && intake.getAngleMotorCurrent() > 9.5;
  }
}

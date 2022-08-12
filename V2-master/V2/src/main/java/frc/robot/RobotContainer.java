// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.SetIntakeAngle;
import frc.robot.subsystems.Intake;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final Intake intake = Intake.getInstance();

  private final XboxController xboxController = new XboxController(0);
  final JoystickButton aButton = new JoystickButton(xboxController, 1);
  /** The container for the robot. Contains
   * subsystems, OI devices, and commands. */
  public RobotContainer() {
    aButton.whenPressed(new SetIntakeAngle(intake,new Rotation2d(Math.toRadians(45)), true));//open the dribllers
    aButton.whenReleased(new SetIntakeAngle(intake,new Rotation2d(Math.toRadians(15)), false));//close the dribller
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return null;
  }
}
// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.geometry.Rotation2d;
import frc.robot.commands.CloseIntakeAndHoming;
import frc.robot.commands.SetIntakeAngle;
import frc.robot.subsystems.Intake;
import edu.wpi.first.wpilibj2.command.Command;
/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final Intake intake = Intake.getInstance();
  /* 
  private final XboxController xboxController = new XboxController(0);
  private final PS4Controller ps4Controller = new PS4Controller(1);
  final JoystickButton R2 = new JoystickButton(ps4Controller, 8);
  final JoystickButton RT = new JoystickButton(xboxController, 10);
  */
  /** The container for the robot. Contains
   * subsystems, OI devices, and commands. */
  public RobotContainer() {
    configurePS4Bindings();
    configureXboxBindings();
  }

  private void configurePS4Bindings(){
    Joysticks.PS4.R2.whenPressed(new SetIntakeAngle(intake,new Rotation2d(Math.toRadians(Constants.IntakeVariables.openAngle)), true));//open the dribllers
    Joysticks.PS4.R2.whenReleased(new CloseIntakeAndHoming(intake));//close the dribller
  }

  private void configureXboxBindings(){
    Joysticks.Xbox.RT.whenPressed(new SetIntakeAngle(intake,new Rotation2d(Math.toRadians(Constants.IntakeVariables.openAngle)), true));//open the dribllers
    Joysticks.Xbox.RT.whenReleased(new CloseIntakeAndHoming(intake));//close the dribller
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

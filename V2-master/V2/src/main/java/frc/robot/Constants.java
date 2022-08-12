// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {

    public static final class IntakeVariables{
        public static final double angleGearRatio = 75;
        public static final double kP = 0.04/angleGearRatio; 
        public static final double kI = 0;
        public static final double kD = 0; 
        public static final double kIz = 0; 
        public static final double kFF = 0; 
        public static final double kMaxOutput = 0.6; 
        public static final double kMinOutput = -0.35;
        public static final double angleRange = 3;
        public static int dribblerID = 50;
        public static int angleMotorID = 51;
        public static double FFValue = 0; //units are voltage
        public static int PIDSlot = 0;
    }


}

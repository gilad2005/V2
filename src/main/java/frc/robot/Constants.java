package frc.robot;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonFXInvertType;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.math.util.Units;
import frc.lib.util.SwerveModuleConstants;

public final class Constants {
    public static final double stickDeadband = 0.1;

    public static final class Swerve {
        public static final int pigeonID = 2;
        public static final boolean invertGyro = true; // Always ensure Gyro is CCW- CW+

        /* Drivetrain Constants */
        public static final double trackWidth = 0.5957;
        public static final double wheelBase = 0.5957;
        public static final double wheelDiameter = Units.inchesToMeters(4);
        public static final double wheelCircumference = wheelDiameter * Math.PI;

        public static final double openLoopRamp = 0.3;
        public static final double closedLoopRamp = 0.0;

        public static final double driveGearRatio = (400 / 49); //6.86:1
        public static final double angleGearRatio = (12.8 / 1.0); //12.8:1

        public static final SwerveDriveKinematics swerveKinematics = new SwerveDriveKinematics(
                new Translation2d(wheelBase / 2.0, -trackWidth / 2.0), 
                new Translation2d(-wheelBase / 2.0, -trackWidth / 2.0), 
                new Translation2d(-wheelBase / 2.0, trackWidth / 2.0), 
                new Translation2d(wheelBase / 2.0, trackWidth / 2.0)); 

        /* Swerve Current Limiting */
        public static final int angleContinuousCurrentLimit = 25;
        public static final int anglePeakCurrentLimit = 40;
        public static final double anglePeakCurrentDuration = 0.1;
        public static final boolean angleEnableCurrentLimit = true;

        public static final int driveContinuousCurrentLimit = 35;
        public static final int drivePeakCurrentLimit = 60;
        public static final double drivePeakCurrentDuration = 0.1;
        public static final boolean driveEnableCurrentLimit = true;

        /* Angle Motor PID Values */
        public static final double angleKP = 0.2;
        public static final double angleKI = 0.0;
        public static final double angleKD = 0.1;
        public static final double angleKF = 0.0;

        /* Drive Motor PID Values */
        public static final double driveKP = 0.075;
        public static final double driveKI = 0.0;
        public static final double driveKD = 0.1;
        public static final double driveKF = 0.0514819;

        /* Drive Motor Characterization Values */
        public static final double driveKS = (0.667 / 12); //divide by 12 to convert from volts to percent output for CTRE
        public static final double driveKV = (2.44 / 12);
        public static final double driveKA = (0.27 / 12);

        /* Swerve Profiling Values */
        public static final double maxSpeed = 3.7; //meters per second
        public static final double maxAngularVelocity = 8.8;
        public static final double VelocityThresholdPrecent = 0.03;
        

        /* Neutral Modes */
        public static final NeutralMode angleNeutralMode = NeutralMode.Coast;
        public static final NeutralMode driveNeutralMode = NeutralMode.Brake;

        /* Motor Inverts */
        public static final boolean driveMotorInvert = false;
        public static final boolean angleMotorInvert = false;

        /* Angle Encoder Invert */
        public static final boolean canCoderInvert = false;

        /* Module Specific Constants */
        /* Front Left Module - Module 0 */
        public static final class Mod0 {//FR
            public static final int driveMotorID = 10;
            public static final int angleMotorID = 11;
            public static final int canCoderID = 12;
            public static final double angleOffset = 190;
            public static final TalonFXInvertType invertType = TalonFXInvertType.CounterClockwise;
            public static final SwerveModuleConstants constants = 
                new SwerveModuleConstants(driveMotorID, angleMotorID, canCoderID, angleOffset, invertType);
        }

        /* Front Right Module - Module 1 */
        public static final class Mod1 { //BR
            public static final int driveMotorID = 20;
            public static final int angleMotorID = 21;
            public static final int canCoderID = 22;
            public static final double angleOffset = 182.5;
            public static final TalonFXInvertType invertType = TalonFXInvertType.CounterClockwise;
            public static final SwerveModuleConstants constants = 
                new SwerveModuleConstants(driveMotorID, angleMotorID, canCoderID, angleOffset, invertType);
        }
        
        /* Back Left Module - Module 2 */
        public static final class Mod2 { // BL
            public static final int driveMotorID = 30;
            public static final int angleMotorID = 31;
            public static final int canCoderID = 32;
            public static final double angleOffset = 153;
            public static final TalonFXInvertType invertType = TalonFXInvertType.Clockwise;
            public static final SwerveModuleConstants constants = 
                new SwerveModuleConstants(driveMotorID, angleMotorID, canCoderID, angleOffset, invertType);
        }

        /* Back Right Module - Module 3 */
        public static final class Mod3 { //FL
            public static final int driveMotorID = 40;
            public static final int angleMotorID = 41;
            public static final int canCoderID = 42;
            public static final double angleOffset = 235;
            public static final TalonFXInvertType invertType = TalonFXInvertType.Clockwise;
            public static final SwerveModuleConstants constants = 
                new SwerveModuleConstants(driveMotorID, angleMotorID, canCoderID, angleOffset, invertType);
        }

    }

    public static final class AutoConstants {
        public static final double kMaxSpeedMetersPerSecond = 3;
        public static final double kMaxAccelerationMetersPerSecondSquared = 3;
        public static final double kMaxAngularSpeedRadiansPerSecond = Math.PI;
        public static final double kMaxAngularSpeedRadiansPerSecondSquared = Math.PI;
    
        public static final double kPXController = 1;
        public static final double kPYController = 1;
        public static final double kPThetaController = 1;
    
        // Constraint for the motion profilied robot angle controller
        public static final TrapezoidProfile.Constraints kThetaControllerConstraints =
            new TrapezoidProfile.Constraints(
                kMaxAngularSpeedRadiansPerSecond, kMaxAngularSpeedRadiansPerSecondSquared);
      }

}

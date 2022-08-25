package frc.robot;

import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.sensors.CANCoder;

public class SwerveModule {
    private double angleOffset;
    private TalonFX AngleMotor;
    private TalonFX DriveMotor;
    private CANCoder angleEncoder;
}

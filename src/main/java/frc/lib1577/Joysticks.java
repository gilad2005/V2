package frc.lib1577;

import edu.wpi.first.wpilibj.PS4Controller;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

public final class Joysticks {
    public static final class Xbox{
        public final static XboxController xboxController = new XboxController(0);
        public final static JoystickButton A = new JoystickButton(xboxController, 1);
        public final static JoystickButton B = new JoystickButton(xboxController, 2);
        public final static JoystickButton X = new JoystickButton(xboxController, 3);
        public final static JoystickButton Y = new JoystickButton(xboxController, 4);
        public final static JoystickButton LeftBumper = new JoystickButton(xboxController, 5);
        public final static JoystickButton RightBumper = new JoystickButton(xboxController, 6);
        public final static JoystickButton Back = new JoystickButton(xboxController, 7);
        public final static JoystickButton Start = new JoystickButton(xboxController, 8);
        public final static JoystickButton LT = new JoystickButton(xboxController, 9);
        public final static JoystickButton RT = new JoystickButton(xboxController, 10);
        
    }
    public static final class PS4{
        public final static PS4Controller ps4Controller = new PS4Controller(1);
        public final static JoystickButton Square = new JoystickButton(ps4Controller, 1);
        public final static JoystickButton Cross = new JoystickButton(ps4Controller, 2);
        public final static JoystickButton Circle = new JoystickButton(ps4Controller, 3);
        public final static JoystickButton Triangle = new JoystickButton(ps4Controller, 4);
        public final static JoystickButton L1 = new JoystickButton(ps4Controller, 5);
        public final static JoystickButton R1 = new JoystickButton(ps4Controller, 6);
        public final static JoystickButton L2 = new JoystickButton(ps4Controller, 7);
        public final static JoystickButton R2 = new JoystickButton(ps4Controller, 8);
        public final static JoystickButton Share = new JoystickButton(ps4Controller, 9);
        public final static JoystickButton Options = new JoystickButton(ps4Controller, 10);
        public final static JoystickButton L3 = new JoystickButton(ps4Controller, 11);
        public final static JoystickButton R3 = new JoystickButton(ps4Controller, 12);
        public final static JoystickButton PS = new JoystickButton(ps4Controller, 13);
        public final static JoystickButton Touchpad = new JoystickButton(ps4Controller, 14);
    }
}

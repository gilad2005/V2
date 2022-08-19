package frc.robot;

import edu.wpi.first.wpilibj.Timer;

public class MathUtil {
    
    MathUtil(){
    }

    public static boolean inRange(double value ,double range, double wantedValue){
        if(Math.abs(wantedValue-value) < range){
            return true;
        }
        return false;
    }

    public static boolean trueForXTime(double time, boolean input) {
        Timer timer = new Timer();
        final boolean x = input;
        if(timer.hasElapsed(time) && input == x){
            return true;
        }
        return false;
    }
}

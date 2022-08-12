package frc.robot;

public class MathUtil {
    MathUtil(){

    }

    public static boolean inRange(double value ,double range, double wantedValue){
        if(Math.abs(wantedValue-value) < range){
            return true;
        }
        return false;
    }
}

package frc.robot;
import edu.wpi.first.wpilibj.Joystick;
import frc.robot.Constants;

public class OI{
    public Joystick stick = new Joystick(Constants.kJoyStick);
    public OI(){
    }
    public double getLeftAngle(){
        return stick.getDirectionDegrees();
    }

    public double getLeftJoystickAngleRad(){
        return Math.atan2(stick.getRawAxis(1), stick.getRawAxis(0));
    }

    public double getLeftXAxis(){
        return stick.getRawAxis(0);
    }
}

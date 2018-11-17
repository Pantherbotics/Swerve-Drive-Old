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

    public double getLeftJoystickAngle(){
        return Math.toDegrees(Math.atan2(stick.getRawAxis(0), -stick.getRawAxis(1)));
    }

    public double getLeftXAxis(){
        return stick.getRawAxis(0);
    }

    public double getLeftYAxis(){
        return stick.getRawAxis(1);
    }

    public double getRightXAxis(){
        return stick.getRawAxis(2);
    }

    public double getLeftMagnitude(){
        return Math.hypot(stick.getRawAxis(1), stick.getRawAxis(0));
    }
}

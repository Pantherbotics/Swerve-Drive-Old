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
}

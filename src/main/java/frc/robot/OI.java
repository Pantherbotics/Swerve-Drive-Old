package frc.robot;
import edu.wpi.first.wpilibj.Joystick;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class OI{
    public Joystick stick = new Joystick(Constants.kJoyStick);
    public Button driveForward = new JoystickButton(Constants.kDriveForward);
    public OI(){
    }
    public double getLeftAngle(){
        return stick.getDirectionDegrees();
    }
    public double getLeftJoystickAngleRad(){
        return Math.atan2(stick.getRawAxis(1), stick.getRawAxis(0));
    }
}

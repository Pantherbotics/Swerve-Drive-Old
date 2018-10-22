package frc.robot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.buttons.Button;

public class OI{
    public Joystick stick = new Joystick(Constants.kJoyStick);
    public Button toggleDrive = new JoystickButton(stick, Constants.kTestButtton);
    public OI(){

    }
    public double getLeftAngle(){
        return stick.getDirectionDegrees();
    }
}

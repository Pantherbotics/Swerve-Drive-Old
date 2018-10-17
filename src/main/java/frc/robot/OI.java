package frc.robot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.buttons.Button;
import frc.robot.commands.*;

public class OI{
    int Pos = 700;
    int oldPos = 700;
    public Joystick stick = new Joystick(Constants.kJoyStick);
    public Button startPID = new JoystickButton(stick, Constants.kStartPID);
    public Button raisePos = new JoystickButton(stick, Constants.kRaisePos);
    public Button lowerPos = new JoystickButton(stick, Constants.kLowerPos);
    public OI(){
        startPID.whenPressed(new GoToEncoderPos(Pos, Robot.kDrivetrain.getSwerveModule()));
        lowerPos.whenPressed(new GoToEncoderPos(lowerPos(), Robot.kDrivetrain.getSwerveModule()));
        lowerPos.whenPressed(new GoToEncoderPos(raisePos(), Robot.kDrivetrain.getSwerveModule()));
    }
    public int lowerPos(){
        int newPos = oldPos - 100;
        oldPos = newPos;
        return newPos;
    }
    public int raisePos(){
        int newPos = oldPos + 100;
        oldPos = newPos;
        return newPos;
    }
}

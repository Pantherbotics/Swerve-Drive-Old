//this code was made by team 3863 FIRST Robotics, Newbury Park, CA 91320
package frc.robot;
import edu.wpi.first.wpilibj.Joystick;
//import edu.wpi.first.wpilibj.command.PrintCommand;
import frc.robot.Constants;

public class OI{
    public Joystick stick = new Joystick(Constants.kJoystickPort);
    public OI(){ //CONTRUCTOR

    }
    public double getLeftXAxis(){
        return stick.getRawAxis(Constants.kJoystickLeftXAxis);
    }
    public double getLeftYAxis(){
        return stick.getRawAxis(Constants.kJoystickLeftYAxis);
    }
    public double getRightYAxis(){
        return stick.getRawAxis(Constants.kJoystickRightYAxis);
    }
    public double getRightXAxis(){
        return stick.getRawAxis(Constants.kJoystickRightXAxis);
    }
}


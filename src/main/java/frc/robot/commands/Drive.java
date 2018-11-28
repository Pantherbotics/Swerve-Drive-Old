//this code was made by team 3863 FIRST Robotics, Newbury Park, CA 91320
package frc.robot.commands;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class Drive extends Command{
    public Drive(){
        requires(Robot.kDrivetrain);
    }
    protected void initialize(){
        
    }
    protected void execute(){
        double throttle = Robot.oi.getLeftYAxis();
        double steering = Robot.oi.getRightXAxis();
        double left = (throttle - steering);
        double right = (throttle + steering);
        Robot.kDrivetrain.setMotorPower(left, right);
    }
    protected boolean isFinished(){
        return false;
    }
    protected void end(){

    }
    protected void interrupted(){

    }
}


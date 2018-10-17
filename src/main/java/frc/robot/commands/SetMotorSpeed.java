package frc.robot.commands;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.SwerveModule;
import frc.robot.subsystems.Constants;

public class SetMotorSpeed extends Command{
double kSetMotorSpeed;
    public SetMotorSpeed(double speed, SwerveModule swerveModule) {
        kSetMotorSpeed = speed; 
    }
    
    protected void initialize() {
    
    }
    protected void execute() {
        kSetMotorSpeed.SetMotorSpeed();
    }
    
    protected boolean isFinished() {
        return true;
    }
    protected void end() {
    
    };
    protected void interrupted() {
    
    }
    }

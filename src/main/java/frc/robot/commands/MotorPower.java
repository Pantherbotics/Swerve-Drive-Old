package frc.robot.commands;
import frc.robot.SwerveModule;
import edu.wpi.first.wpilibj.command.Command;

public class MotorPower extends Command{
double getMagnitude;
SwerveModule module;
    public MotorPower(double Magnitude,  SwerveModule swerveModule) {
        this.getMagnitude = Magnitude; 
        this.module = swerveModule;
    }
    
    protected void initialize() {
    
    }
    protected void execute() {
        module.setDrive(getMagnitude);
    }
    
    protected boolean isFinished() {
        return true;
    }
    protected void end() {
    
    };
    protected void interrupted() {
    
    }
}
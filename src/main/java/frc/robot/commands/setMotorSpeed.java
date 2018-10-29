package frc.robot.commands;
import frc.robot.SwerveModule;
import edu.wpi.first.wpilibj.command.Command;

public class setMotorSpeed extends Command{
double getMagnitude;
SwerveModule module;
    public setMotorSpeed(double Magnitude,  SwerveModule swerveModule) {
        this.getMagnitude = Magnitude; 
        this.module = swerveModule;
    }
    
    protected void initialize() {
    
    }
    protected void execute() {
        module.setDrivePower(getMagnitude);
    }
    
    protected boolean isFinished() {
        return true;
    }
    protected void end() {
    
    };
    protected void interrupted() {
    
    }
}
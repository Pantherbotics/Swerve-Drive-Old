package frc.robot.subsystems;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Constants;
import frc.robot.ModuleLocation;
import frc.robot.SwerveModule;

public class Drivetrain extends Subsystem {
  private SwerveModule module = new SwerveModule(Constants.kSteeringID, Constants.kDriveID, false, Constants.kSwerveP, Constants.kSwerveI, Constants.kSwerveD);
  public Drivetrain(){
  }
  @Override
  public void initDefaultCommand() {

  }
  public SwerveModule getSwerveModule(ModuleLocation location){
    return module;
  }
}

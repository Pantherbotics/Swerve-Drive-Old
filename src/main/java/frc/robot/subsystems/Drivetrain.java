package frc.robot.subsystems;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Constants;
import frc.robot.SwerveModule;

public class Drivetrain extends Subsystem {
  private SwerveModule module = new SwerveModule(Constants.kSteeringID, Constants.kDriveID);
  public Drivetrain(){
  }
  @Override
  public void initDefaultCommand() {

  }
  public SwerveModule getSwerveModule(){
    return module;
  }
}

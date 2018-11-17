package frc.robot.subsystems;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;
import frc.robot.ModuleLocation;
import frc.robot.SwerveModule;

public class Drivetrain extends Subsystem {
  private SwerveModule moduleFrontLeft = new SwerveModule(Constants.kFrontLeftSteerID, Constants.kFrontLeftDriveID, false, Constants.kFrontLeftOffset, Constants.kSwerveP, Constants.kSwerveI, Constants.kSwerveD);
  private SwerveModule moduleFrontRight = new SwerveModule(Constants.kFrontRightSteerID, Constants.kFrontRightDriveID, false, Constants.kFrontRightOffset, Constants.kSwerveP, Constants.kSwerveI, Constants.kSwerveD);
  private SwerveModule moduleBackLeft = new SwerveModule(Constants.kBackLeftSteerID, Constants.kBackLeftDriveID, false, Constants.kBackLeftOffset, Constants.kSwerveP, Constants.kSwerveI, Constants.kSwerveD);
  private SwerveModule moduleBackRight = new SwerveModule(Constants.kBackRightSteerID, Constants.kBackRightDriveID, false, Constants.kBackRightOffset, Constants.kSwerveP, Constants.kSwerveI, Constants.kSwerveD);
  public Drivetrain(){
  }
  @Override
  public void initDefaultCommand() {

  }
  
  public SwerveModule getSwerveModule(ModuleLocation location){
    switch(location){
      case FRONT_LEFT:
        return moduleFrontLeft;
      case FRONT_RIGHT:
        return moduleFrontRight;
      case BACK_LEFT:
        return moduleBackLeft;
      case BACK_RIGHT:
        return moduleBackRight;
      default:
        return null;
    }
  }

  public void setAllAngle(double degrees){
    moduleFrontLeft.setSteeringDegrees(degrees);
    moduleFrontRight.setSteeringDegrees(degrees);
    moduleBackLeft.setSteeringDegrees(degrees);
    moduleBackRight.setSteeringDegrees(degrees);
  }

  public void updateDashboard(){
    SmartDashboard.putNumber("Front Left Error", moduleFrontLeft.getError());
    SmartDashboard.putNumber("Front Right Error", moduleFrontRight.getError());
    SmartDashboard.putNumber("Back Left Error", moduleBackLeft.getError());
    SmartDashboard.putNumber("Back Right Error", moduleBackRight.getError());

    SmartDashboard.putNumber("Front Left Setpoint", moduleFrontLeft.getSetpointDegrees());
    SmartDashboard.putNumber("Front Right Setpoint", moduleFrontRight.getSetpointDegrees());
    SmartDashboard.putNumber("Back Left Setpoint", moduleBackLeft.getSetpointDegrees());
    SmartDashboard.putNumber("Back Right Setpoint", moduleBackRight.getSetpointDegrees());

    SmartDashboard.putNumber("Front Left Position", moduleFrontLeft.getSteeringDegrees());
    SmartDashboard.putNumber("Front Right Position", moduleFrontRight.getSteeringDegrees());
    SmartDashboard.putNumber("Back Left Position", moduleBackLeft.getSteeringDegrees());
    SmartDashboard.putNumber("Back Right Position", moduleBackRight.getSteeringDegrees());
  }
}
